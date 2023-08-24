@homepage
Feature: CoverGo homepage

  Scenario: Page title
    Given I am on the CoverGo homepage
    Then the page title should be equal to "The Ultimate No-Code Insurance Platform | CoverGo"

  Scenario: Main message
    Given I am on the CoverGo homepage
    When I looked at the "top header" element
    Then I see "The ultimate no-code insurance platform for maximum flexibility" text

  Scenario: Top Menu Items List
    Given I am on the CoverGo homepage
    When I looked at the "top menu items list" element
    Then It consists of the following valuable items:
      | Why CoverGo  |
      | Our Platform |
      | Our Clients  |
      | Resources    |
      | About Us     |
      | REQUEST DEMO |

  Scenario: Going to Request Demo page
    Given I am on the CoverGo homepage
    When I clicked at 'REQUEST DEMO' menu item element
    Then I see "Contact Us" page is opened
    And Page Url Path ends with "about-us/contact-us"

  Scenario: Going to Our Client page
    Given I am on the CoverGo homepage
    When I clicked at 'Our Clients' menu item element
    Then I see "Our Clients" page is opened
    And Page Url Path ends with "our-clients"
    And I looked at the "Get In Touch" element
    And It has text in uppercase

  Scenario: Going to About Us page
    Given I am on the CoverGo homepage
    When I clicked at 'About Us' menu item element
    Then I see "About Us" page is opened
    And Page Url Path ends with "about-us"
    And I looked at the "Get In Touch" element
    And It has text in uppercase
    And There is a video on the page
