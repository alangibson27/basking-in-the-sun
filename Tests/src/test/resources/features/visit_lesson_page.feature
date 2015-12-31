Feature: Visiting lesson page

  Scenario: Page has the correct title and links
    When I navigate to the lessons/visit page
    Then the title of the page is 出張レッスン
    And there is a link that says スケジュールは下記のカレンダーをご覧ください。
    And there is another link that says ご予約、ご質問等ございましたら、こちらへどうぞ　☎

  Scenario: Clicking on link to the calendar scrolls to the part of the page showing the calendar
    When I navigate to the lessons/visit page
    And I click on the link that says スケジュールは下記のカレンダーをご覧ください。
    Then the page scrolls to the calendar

  Scenario: Clicking on the contact link navigates to the contact page
    When I navigate to the lessons/visit page
    And I click on the link that says ご予約、ご質問等ございましたら、こちらへどうぞ　☎
    Then I am taken to the contact page

  Scenario: When browsing on a computer the calendar is shown in table format
    Given I am browsing on a computer
    When I navigate to the lessons/visit page
    Then the calendar is shown in table format

  Scenario: When browsing on a phone the calendar is shown in agenda format
    Given I am browsing on a phone
    When I navigate to the lessons/visit page
    Then the calendar is shown in agenda format