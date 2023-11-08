package com.catawiki.stepDefinitions;

import com.catawiki.config.Config;
import com.catawiki.helpers.GuiHelper;
import com.catawiki.helpers.StepData;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class SearchSteps {
    private final GuiHelper guiHelper;
    @Autowired
    Config config;
    @FindBy(id = "cookie_bar_agree_button")
    WebElement acceptCookies;
    @FindBy(xpath = "//*[contains(@class,'c-header__search') and contains(@class,'u-hide-print')]//*[@data-testid='search-field']")
    WebElement searchBox;
    @FindBy(xpath = "//*[contains(@class,'c-header__search') and contains(@class,'u-hide-print')]//*[@data-testid='search-field']/parent::div/preceding-sibling::*[@data-testid='text-field-prefix']/button")
    WebElement magnifierButton;
    @FindBy(id = "search-list-container")
    WebElement searchResultsPage;
    String chooseFromAuctionList = "(//*[@id='search-list-container']//*[@class='c-extended-lot-card'])[%s]";

    @FindBy(xpath = "//*[@class='lot-details-page-wrapper']")
    WebElement lotsPage;
    @FindBy(xpath = "//*[@class='lot-details-page-wrapper']//h1")
    WebElement lotName;
    @FindBy(xpath = "//*[@class='lot-details-page-wrapper']//*[contains(@class,'be-lot-details-main-section__reference')]/following-sibling::div/button//span")
    WebElement favoriteCounter;
    @FindBy(xpath = "//*[@class='lot-details-page-wrapper']//*[@class='be-lot-core-bidding-panel-wrapper']//*[@class='be-lot-core-bidding-panel']//*[contains(@class,'bid-amount')]")
    WebElement currentBid;

    public SearchSteps(StepData stepData) throws IOException {
        PageFactory.initElements(stepData.getDriver(), this);
        guiHelper = new GuiHelper(stepData.getDriver());
    }

    @Given("^catawiki website is opened and cookies are accepted")
    public void openCatawikiWebsite() {
        guiHelper.openPage(config.getUrl());
        guiHelper.waitForPageLoad(config.getPageLoadTimeoutWait());
        guiHelper.waitUntilVisibleToClick(acceptCookies);
        acceptCookies.click();
    }

    @When("the keyword {string} is entered in the search field")
    public void iEnterTheKeywordInTheSearchField(String keyword) {
        guiHelper.enterText(searchBox, keyword);
    }

    @And("magnifier icon is clicked")
    public void iClickOnMagnifierButton() {
        magnifierButton.click();
        guiHelper.waitForPageLoad(config.getPageLoadTimeoutWait());
    }

    @Then("display of search results page is verified")
    public void iVerifyTheSearchResultsPageIsOpened() {
        guiHelper.waitUntil(searchResultsPage);
        Assertions.assertThat(searchResultsPage.isDisplayed()).as("search results page is not displayed");
    }

    @When("the {} {} lot in the search results is clicked")
    public void iClickOnSecondLotInTheSearchResults(String lotTobeClicked, String suffix) {
        guiHelper.click(chooseFromAuctionList.replace("%s", lotTobeClicked));
        guiHelper.waitUntil(lotsPage);
    }

    @Then("verify the lots page is opened")
    public void iVerifyTheLotsPageIsOpened() {
        guiHelper.waitUntil(lotsPage);
        Assertions.assertThat(lotsPage.isDisplayed()).as("lot page is not opened");
    }

    @Then("fetch the following details of the lot")
    public void iFetchTheFollowingDetailsOfTheLot(DataTable dataTable) {

        var fieldList = dataTable.asList();
        System.out.println("-----------------------------");
        fieldList.forEach(fieldName -> {
            switch (fieldName) {
                case "lots name" ->  System.out.println("Lot's Name : " + lotName.getText());
                case "favorites counter" -> System.out.println("Favorites Counter : " + favoriteCounter.getText());
                case "current bid" ->   System.out.println("Current Bid : " + currentBid.getText());
            }
        });
        System.out.println("-----------------------------");

    }
}
