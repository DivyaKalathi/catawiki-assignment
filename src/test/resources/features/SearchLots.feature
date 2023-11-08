Feature: Search for lots in running auctions matching given criteria and verify required information on the page
  @search
  Scenario: Search the lots
    Given catawiki website is opened and cookies are accepted
    When the keyword "train" is entered in the search field
    And magnifier icon is clicked
    Then display of search results page is verified
    When the 2 nd lot in the search results is clicked
    Then verify the lots page is opened
    And fetch the following details of the lot
      | lots name         |
      | favorites counter |
      | current bid       |