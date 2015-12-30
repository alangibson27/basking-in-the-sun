Feature: Home page on a computer

  Background:
    Given I am browsing on a computer

  Scenario Outline: Home page loads correctly and all links work
    Given I navigate to the home page
    When I click on the link that says <link text>
    Then I am taken to the <target> page
  Examples:
    | link text       | target           |
    | ホーム             | <home>           |
    | インファンとマッサージーとは？ | about            |
    | レッスン            | lessons/overview |
    | プロフィール          | profile          |
    | ママの声            | testimonial      |
    | FAQ             | faq              |
    | お問い合わせ          | contact          |

  Scenario: Hovering over レッスン correctly pops up menu
    Given I navigate to the home page
    When I hover over the link that says レッスン
    Then a submenu pops up
    And it contains the following options
      | 出張レッスン |
      | 自宅レッスン |

  Scenario Outline: Links can be selected on レッスン submenu
    Given I navigate to the home page
    And I hover over the link that says レッスン
    When I click on the link that says <link text>
    Then I am taken to the <target> page
  Examples:
    | link text | target |
    | 出張レッスン | lessons/visit |
    | 自宅レッスン | lessons/home |

   Scenario: Moving away from レッスン correctly hides menu
     Given I navigate to the home page
     And I hover over the link that says レッスン
     When I move away from the link
     Then the submenu disappears
