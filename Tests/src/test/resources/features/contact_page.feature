Feature: Contact page

  Scenario: Page has the correct title, contact details and form fields
    When I navigate to the contact page
    Then the title of the page is ご予約・お問い合わせ
    And the contact email address is correct
    And the contact telephone number is correct
    And the name field on the form has the label お名前
    And the email field on the form has the label メールアドレス
    And the lesson type field on the form has the label ご希望のレッスン and the options プライベートレッスン, グループレッスン, どちらでも良い
    And the message box has the label メッセージ
    And the submit button has the label 送信

  Scenario: Contact form submission fails if no values are supplied
    Given I navigate to the contact page
    And I do not enter any values in the form
    When I click the submit button
    Then I see three error messages

  Scenario Outline: Contact form submission fails if one field is empty
    Given I navigate to the contact page
    And I do not enter a value for the <field> field
    When I click the submit button
    Then I see an error message for the empty field
  Examples:
    | field   |
    | name    |
    | email   |
    | message |