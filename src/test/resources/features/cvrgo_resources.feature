@resources
Feature: CoverGo 'Resources' page

  Background:
    Given I am on the CoverGo homepage
    When I clicked at 'Resources' menu item element
    Then I see 'Case Studies' page is opened

  Scenario: Page title
    Then the page title should be equal to "Case Studies | CoverGo"
    And Page Url Path ends with "case-studies"

  Scenario: Main message
    When I looked at the "top header" element
    Then I see "CASE STUDIES" text

  Scenario: Top Menu Items List
    When I looked at the "top menu items list" element
    Then It consists of the following valuable items:
      | Why CoverGo  |
      | Our Platform |
      | Our Clients  |
      | Resources    |
      | About Us     |
      | REQUEST DEMO |

  Scenario: Back to homepage by logo
    When I clicked at logo
    Then I see 'Homepage' page is opened
