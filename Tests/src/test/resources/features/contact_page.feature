Feature: Contact page

  Scenario: Page has the correct title and contact details
    When I navigate to the contact page
    Then the title of the page is ご予約・お問い合わせ
    And the contact email address is correct
    And the contact telephone number is correct