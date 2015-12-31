Feature: Navigation on a computer

  Background:
    Given I am browsing on a computer
    And I navigate to the home page

  Scenario Outline: Home page loads correctly and all links work
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
    When I hover over the link that says レッスン
    Then a submenu pops up
    And it contains the following options
      | 出張レッスン |
      | 自宅レッスン |

  Scenario Outline: Links can be selected on レッスン submenu
    When I hover over the link that says レッスン
    And I click on the link that says <link text>
    Then I am taken to the <target> page
  Examples:
    | link text | target |
    | 出張レッスン | lessons/visit |
    | 自宅レッスン | lessons/home |

   Scenario: Moving away from レッスン correctly hides menu
     When I hover over the link that says レッスン
     And I move away from the link
     Then the submenu disappears
