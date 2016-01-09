Feature: Navigation on a phone

  Background:
    Given I am browsing on a phone
    And I navigate to the home page
    
  Scenario: Navigation menu displays when menu is clicked
    When I click on the link that says メニュー
    Then the navigation menu expands

  Scenario: Navigation menu hides when menu is clicked a second time
    When I click on the link that says メニュー
    And I click again on the link that says メニュー
    Then the navigation menu hides
    
  Scenario Outline: All links on navigation menu are available and work
    When I click on the link that says メニュー
    And I then click on the link that says <link text>
    Then I am taken to the <target> page
  Examples:
    | link text       | target           |
    | ホーム             | home             |
    | インファンとマッサージーとは？ | about            |
    | レッスン            | lessons/overview |
    | 出張レッスン | lessons/visit |
    | 自宅レッスン | lessons/home |
    | プロフィール          | profile          |
    | ママの声            | testimonial      |
    | FAQ             | faq              |
    | お問い合わせ          | contact          |
