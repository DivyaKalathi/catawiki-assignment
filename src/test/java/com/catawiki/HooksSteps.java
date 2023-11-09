package com.catawiki;

import com.catawiki.config.Config;
import com.catawiki.helpers.StepData;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.spring.CucumberContextConfiguration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

/**
 * This class specifies what to do before and after cucumber scenario.
 * In the before hooks, browser specific webdriver is initialised and in after hooks screenshot for failed scenario is added and the driver is closed 
 */

@SpringBootTest(classes = CatawikiApplication.class)
@CucumberContextConfiguration
public class HooksSteps {
    @Autowired
    StepData stepData;
    @Autowired
    Config config;

    @Before
    public void setUp(Scenario scenario) {
        String browser = System.getProperty("test.browser");
        String browserMode = System.getProperty("test.browsermode");
        if(browser==null) {
            browser = "firefox";
        }
        switch (browser) {
            case "chrome":
                ChromeOptions option = new ChromeOptions();
                option.addArguments("--remote-allow-origins=*");
                 String chromeDriverPath=HooksSteps.class.getClassLoader().getResource("chromedriver_116.exe").getPath();
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                if (browserMode != null && browserMode.equalsIgnoreCase("HEADLESS")) {
                    option.addArguments("--headless");
                }
                WebDriverManager.chromedriver().setup();
                stepData.setDriver(new ChromeDriver(option));
                break;
            case "firefox":
                String firefoxDriverPath=HooksSteps.class.getClassLoader().getResource("geckodriver.exe").getPath();
                System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (browserMode != null && browserMode.equalsIgnoreCase("HEADLESS")) {
                    firefoxOptions.addArguments("--headless");                }
                stepData.setDriver(new FirefoxDriver(firefoxOptions));
                break;

            default:
                break;

        }
        stepData.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(config.getImplicitWait()));
        stepData.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            var screenshot = "target/screenshots/" + scenario.getName().replaceAll("[ ]", "-" + ".png");
            File screenshotFile = ((TakesScreenshot) stepData.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File(screenshot));
        }
        stepData.getDriver().close();
    }

}
