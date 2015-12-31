Feature: FAQ page

  Scenario: Page has the correct title
    When I navigate to the faq page
    Then the title of the page is FAQ
    And there is a link that says 他にもご質問がありましたら、こちらへどうぞ　☎

  Scenario: Clicking on the contact link navigates to the contact page
    When I navigate to the faq page
    And I click on the link that says 他にもご質問がありましたら、こちらへどうぞ　☎
    Then I am taken to the contact page
