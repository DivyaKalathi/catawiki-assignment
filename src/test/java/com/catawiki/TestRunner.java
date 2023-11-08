package com.catawiki;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/catawiki/stepDefinitions",
        tags = "@search",
        plugin = {"json:target/cucumber-reports/cucumber/cucumber-html-reports/Cucumber.json",
                "de.monochromata.cucumber.report.PrettyReports:target/cucumber-reports/cucumber", "pretty"},
        monochrome = true,
        publish = true

)
public class TestRunner {
}
