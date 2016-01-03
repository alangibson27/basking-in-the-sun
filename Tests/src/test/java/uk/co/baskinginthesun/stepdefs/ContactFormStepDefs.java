package uk.co.baskinginthesun.stepdefs;

import com.google.common.collect.ImmutableList;
import cucumber.api.Delimiter;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.capitalize;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static uk.co.baskinginthesun.util.BrowserDriver.findElementByPath;
import static uk.co.baskinginthesun.util.BrowserDriver.findLinkByText;
import static uk.co.baskinginthesun.util.BrowserDriver.getPageSource;

public class ContactFormStepDefs {
    private static final String LABEL_XPATH = "//form/div[@class='form-group']/label[@for='%s']";
    private static final String SUBMIT_SUCCESS_MSG = "ありがとうございます。メールは無事に送信されました。";
    private static final String SUBMIT_FAILURE_MSG = "メッセージが送れませんでした。もう一度お送りください。";
    private static final String INPUT_XPATH = "//form/div[@class='form-group']/input[@id='%s']";

    private static final ImmutableList<String> TEXT_FIELDS = ImmutableList.of("name", "email", "message");
    private String emptyFieldName;

    @And("^the contact email address is correct$")
    public void the_contact_email_address_is_correct() {
        final WebElement mailLink = findLinkByText("baskinginthesun22@gmail.com");
        assertThat(mailLink).isNotNull();
        assertThat(mailLink.getAttribute("href")).isEqualTo("mailto:baskinginthesun22@gmail.com");
    }

    @And("^the contact telephone number is correct$")
    public void the_contact_telephone_number_is_correct() {
        assertThat(getPageSource()).contains("07454 005 647");
    }

    @And("^the (.+) field on the form has the label (\\S+)$")
    public void the_field_on_the_form_has_the_label(final String fieldName, final String label) {
        verifyFormTextField(fieldIdFor(fieldName), label);
    }

    @And("^the (.+) field on the form has the label (.+) and the options (.+)$")
    public void the_field_on_the_form_has_the_label_and_the_options(
            final String fieldName,
            final String label,
            @Delimiter(", ") final List<String> options
    ) {
        final String fieldId = fieldIdFor(fieldName);
        final WebElement inputLabel = findElementByPath(format(LABEL_XPATH, fieldId));
        assertThat(inputLabel).isNotNull();
        assertThat(inputLabel.getText()).isEqualTo(label);

        final WebElement selectElement = findElementByPath(format("//form/div[@class='form-group']/select[@id='%s']", fieldId));
        assertThat(selectElement).isNotNull();
        final List<String> selectOptions = selectElement.findElements(By.tagName("option")).stream()
                .map(x -> x.getText())
                .collect(toList());
        AssertionsForInterfaceTypes.assertThat(options).containsExactlyElementsOf(selectOptions);
    }

    @And("^the message box has the label (.+)$")
    public void the_message_box_has_the_label(final String label) throws Throwable {
        final WebElement messageLabel = findElementByPath(format(LABEL_XPATH, "message"));
        assertThat(messageLabel).isNotNull();
        assertThat(messageLabel.getText()).isEqualTo(label);
    }

    @And("^the submit button has the label (.+)$")
    public void the_submit_button_has_the_label(final String label) throws Throwable {
        final WebElement submitButton = submitButton();
        assertThat(submitButton).isNotNull();
        assertThat(submitButton.getText()).isEqualTo(label);
    }

    @And("^I do not enter any values in the form$")
    public void I_do_not_enter_any_values_in_the_form() {
        // NOP
    }

    @When("^I click the submit button$")
    public void I_click_the_submit_button() {
        submitButton().click();
    }

    @Then("^I see three error messages$")
    public void I_see_an_error_message() {
        final WebElement nameFeedbackElement = nameFeedbackElement();
        assertThat(nameFeedbackElement).isNotNull();
        assertThat(nameFeedbackElement.getText()).isNotEmpty();

        final WebElement emailFeedbackElement = emailFeedbackElement();
        assertThat(emailFeedbackElement).isNotNull();
        assertThat(emailFeedbackElement.getText()).isNotEmpty();

        final WebElement messageResponseElement = messageResponseElement();
        assertThat(messageResponseElement).isNotNull();
        assertThat(messageResponseElement.getText())
                .isNotEmpty()
                .isNotEqualTo(SUBMIT_SUCCESS_MSG)
                .isNotEqualTo(SUBMIT_FAILURE_MSG);
    }

    @And("^I do not enter a value for the (.+) field$")
    public void I_do_not_enter_a_value_for_the_field(final String emptyFieldName) {
        this.emptyFieldName = emptyFieldName;
        for (String fieldName: TEXT_FIELDS.stream().filter(x -> !emptyFieldName.equals(x)).collect(toList())) {
            final WebElement field;
            if ("message".equals(fieldName)) {
                field = findElementByPath("//form/div[@class='form-group']/textarea[@id='message']");
            } else {
                field = findElementByPath(format(INPUT_XPATH, fieldIdFor(fieldName)));
            }

            field.sendKeys("a@b.c");
        }
    }

    @Then("^I see an error message for the empty field$")
    public void I_see_an_error_message_for_the_empty_field() {
        if ("name".equals(this.emptyFieldName)) {
            assertThat(nameFeedbackElement().getText()).isNotEmpty();
        } else {
            assertThat(nameFeedbackElement().getText()).isEmpty();
        }

        if ("email".equals(this.emptyFieldName)) {
            assertThat(emailFeedbackElement().getText()).isNotEmpty();
        } else {
            assertThat(emailFeedbackElement().getText()).isEmpty();
        }

        if ("message".equals(this.emptyFieldName)) {
            assertThat(messageResponseElement().getText()).isNotEmpty();
        } else {
            assertThat(messageResponseElement().getText()).isEmpty();
        }
    }

    private WebElement messageResponseElement() {
        return findElementByPath("//form/span[@id='response']");
    }

    private WebElement emailFeedbackElement() {
        return findElementByPath("//form/div/span[@id='emailFeedback']");
    }

    private WebElement nameFeedbackElement() {
        return findElementByPath("//form/div/span[@id='nameFeedback']");
    }

    private WebElement submitButton() {
        return findElementByPath("//form/button[@id='submit']");
    }

    private void verifyFormTextField(String fieldId, String fieldLabel) {
        final WebElement inputLabel = findElementByPath(format(LABEL_XPATH, fieldId));
        assertThat(inputLabel).isNotNull();
        assertThat(inputLabel.getText()).isEqualTo(fieldLabel);

        final WebElement inputElement = findElementByPath(format(INPUT_XPATH, fieldId));
        assertThat(inputElement).isNotNull();
        assertThat(inputElement.getAttribute("placeholder")).isEqualTo(fieldLabel);
    }

    private String fieldIdFor(final String fieldName) {
        return "input" + asList(fieldName.split(" ")).stream().map(x -> capitalize(x)).collect(joining());
    }
}
