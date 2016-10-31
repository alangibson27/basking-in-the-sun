Feature: Navigation on a computer

  Background:
    Given I am browsing on a computer
    And I navigate to the home page

  Scenario Outline: Home page loads correctly and all links work
    When I click on the link that says <link text>
    Then I am taken to the <target> page
  Examples:
    | link text       | target           |
    | ホーム             | home             |
    | インファンとマッサージーとは？ | about            |
    | レッスン            | lessons/visit |
    | プロフィール          | profile          |
    | ママの声            | testimonial      |
    | FAQ             | faq              |
    | お問い合わせ          | contact          |

