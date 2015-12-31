Feature: Lesson top page

  Background:
    Given I navigate to the lessons/overview page

  Scenario: Page has the correct title and links
    Then the title of the page is レッスン
    And there is a link that says 出張レッスン
    And there is another link that says 自宅レッスン

  Scenario Outline: Links on page work correctly
    When I click on the link that says <link text>
    Then I am taken to the <target> page
  Examples:
    | link text | target |
    | 出張レッスン | lessons/visit |
    | 自宅レッスン | lessons/home |
