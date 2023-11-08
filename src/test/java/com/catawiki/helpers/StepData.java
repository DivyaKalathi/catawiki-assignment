package com.catawiki.helpers;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

/**
 * This is a common class which stores the data to be accessed by any class within the project.
 */
@Data
@Component
public class StepData {
    private String browser;
    private String browserMode;
    private WebDriver driver;
}
