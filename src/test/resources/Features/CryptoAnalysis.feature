
Feature: Test coinmarketcap cryptocurrency trading features


  Scenario: verify the number of results displayed based on our selection

    Given Launch the coinmarketcap website
    When Click on show rows
    And Select fifty from the dropdown list
    Then Verify that fifty results should be displayed


  Scenario: Add any cryptocurrencies to watchlist randomly and verify whether addition to watchlist is successful

    Given Launch the coinmarketcap website
    When Click on Add to watchlist icon for five random crypto currencies
    And Open the Watchlist in different tab
    Then Verify the all five currencies selected are added into watchlist


    Scenario:
