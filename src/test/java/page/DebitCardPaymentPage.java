package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitCardPaymentPage {

    private final SelenideElement creditPaymentButton = $$("button.button_view_extra").first();
    private final SelenideElement heading = $$("h3").last();
    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final SelenideElement holderField = $$("input.input__control").get(3);
    private final SelenideElement cvcCodeField = $("input[placeholder='999']");
    private final SelenideElement continueButton = $$("button.button_view_extra").last();
    private final SelenideElement successNotification = $("div.notification_status_ok .notification__content");
    private final SelenideElement errorNotification = $("div.notification_status_error .notification__content");
    private final SelenideElement wrongFormatNotification = $("div span.input__sub");


    public void shouldVerifyPageIsVisible() {
        heading.shouldHave(text("Оплата по карте")).shouldBe(visible, Duration.ofSeconds(15));
    }

    public void shouldPayByFirstCard() {
        cardNumberField.setValue(DataHelper.getValidFirstCardDetails().getNumber());
        monthField.setValue(DataHelper.getValidFirstCardDetails().getMonth());
        yearField.setValue(DataHelper.getValidFirstCardDetails().getYear());
        holderField.setValue(DataHelper.getValidFirstCardDetails().getHolder());
        cvcCodeField.setValue(DataHelper.getValidFirstCardDetails().getCvc());
        continueButton.click();

    }

    public void shouldPayBySecondCard() {
        cardNumberField.setValue(DataHelper.getValidSecondCardDetails().getNumber());
        monthField.setValue(DataHelper.getValidSecondCardDetails().getMonth());
        yearField.setValue(DataHelper.getValidFirstCardDetails().getYear());
        holderField.setValue(DataHelper.getValidSecondCardDetails().getHolder());
        cvcCodeField.setValue(DataHelper.getValidSecondCardDetails().getCvc());
        continueButton.click();
    }

    public void shouldPayByOtherCard() {
        cardNumberField.setValue(DataHelper.getCardDetailsOtherCardNumber().getNumber());
        monthField.setValue(DataHelper.getCardDetailsOtherCardNumber().getMonth());
        yearField.setValue(DataHelper.getCardDetailsOtherCardNumber().getYear());
        holderField.setValue(DataHelper.getCardDetailsOtherCardNumber().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsOtherCardNumber().getCvc());
        continueButton.click();

    }

    public void shouldPayByShortCardNumber() {
        cardNumberField.setValue(DataHelper.getCardDetailsShortCardNumber().getNumber());
        monthField.setValue(DataHelper.getCardDetailsShortCardNumber().getMonth());
        yearField.setValue(DataHelper.getCardDetailsShortCardNumber().getYear());
        holderField.setValue(DataHelper.getCardDetailsShortCardNumber().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsShortCardNumber().getCvc());
        continueButton.click();

    }

    public void shouldPayWithoutCardNumber() {
        monthField.setValue(DataHelper.getCardDetailsShortCardNumber().getMonth());
        yearField.setValue(DataHelper.getCardDetailsShortCardNumber().getYear());
        holderField.setValue(DataHelper.getCardDetailsShortCardNumber().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsShortCardNumber().getCvc());
        continueButton.click();

    }

    public void shouldPayWithoutMonth() {
        cardNumberField.setValue(DataHelper.getValidFirstCardDetails().getNumber());
        yearField.setValue(DataHelper.getValidFirstCardDetails().getYear());
        holderField.setValue(DataHelper.getValidFirstCardDetails().getHolder());
        cvcCodeField.setValue(DataHelper.getValidFirstCardDetails().getCvc());
        continueButton.click();
    }

    public void shouldPayWith0ForMonth() {
        cardNumberField.setValue(DataHelper.getCardDetailsWith0ForMonth().getNumber());
        monthField.setValue(DataHelper.getCardDetailsWith0ForMonth().getMonth());
        yearField.setValue(DataHelper.getCardDetailsWith0ForMonth().getYear());
        holderField.setValue(DataHelper.getCardDetailsWith0ForMonth().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsWith0ForMonth().getCvc());
        continueButton.click();
    }

    public void shouldPayWith00ForMonth() {
        cardNumberField.setValue(DataHelper.getCardDetailsWith00ForMonth().getNumber());
        monthField.setValue(DataHelper.getCardDetailsWith00ForMonth().getMonth());
        yearField.setValue(DataHelper.getCardDetailsWith00ForMonth().getYear());
        holderField.setValue(DataHelper.getCardDetailsWith00ForMonth().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsWith00ForMonth().getCvc());
        continueButton.click();
    }

    public void shouldPayWith13ForMonth() {
        cardNumberField.setValue(DataHelper.getCardDetailsWith13ForMonth().getNumber());
        monthField.setValue(DataHelper.getCardDetailsWith13ForMonth().getMonth());
        yearField.setValue(DataHelper.getCardDetailsWith13ForMonth().getYear());
        holderField.setValue(DataHelper.getCardDetailsWith13ForMonth().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsWith13ForMonth().getCvc());
        continueButton.click();
    }

    public void shouldPayWithoutYear() {
        cardNumberField.setValue(DataHelper.getValidFirstCardDetails().getNumber());
        monthField.setValue(DataHelper.getValidFirstCardDetails().getMonth());
        holderField.setValue(DataHelper.getValidFirstCardDetails().getHolder());
        cvcCodeField.setValue(DataHelper.getValidFirstCardDetails().getCvc());
        continueButton.click();
    }

    public void shouldPayWith23ForYear() {
        cardNumberField.setValue(DataHelper.getCardDetailsWith23ForYear().getNumber());
        monthField.setValue(DataHelper.getCardDetailsWith23ForYear().getMonth());
        yearField.setValue(DataHelper.getCardDetailsWith23ForYear().getYear());
        holderField.setValue(DataHelper.getCardDetailsWith23ForYear().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsWith23ForYear().getCvc());
        continueButton.click();
    }

    public void shouldPayWith02ForMonth24ForYear() {
        cardNumberField.setValue(DataHelper.getCardDetailsWith02ForMonthAnd24ForYear().getNumber());
        monthField.setValue(DataHelper.getCardDetailsWith02ForMonthAnd24ForYear().getMonth());
        yearField.setValue(DataHelper.getCardDetailsWith02ForMonthAnd24ForYear().getYear());
        holderField.setValue(DataHelper.getCardDetailsWith02ForMonthAnd24ForYear().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsWith02ForMonthAnd24ForYear().getCvc());
        continueButton.click();
    }

    public void shouldPayWith30ForYear() {
        cardNumberField.setValue(DataHelper.getCardDetailsWith30ForYear().getNumber());
        monthField.setValue(DataHelper.getCardDetailsWith30ForYear().getMonth());
        yearField.setValue(DataHelper.getCardDetailsWith30ForYear().getYear());
        holderField.setValue(DataHelper.getCardDetailsWith30ForYear().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsWith30ForYear().getCvc());
        continueButton.click();
    }

    public void shouldPayWithoutHolder() {
        cardNumberField.setValue(DataHelper.getValidFirstCardDetails().getNumber());
        monthField.setValue(DataHelper.getValidFirstCardDetails().getMonth());
        yearField.setValue(DataHelper.getValidFirstCardDetails().getYear());
        cvcCodeField.setValue(DataHelper.getValidFirstCardDetails().getCvc());
        continueButton.click();
    }

    public void shouldPayWith0ForHolder() {
        cardNumberField.setValue(DataHelper.getCardDetailsWith0ForHolder().getNumber());
        monthField.setValue(DataHelper.getCardDetailsWith0ForHolder().getMonth());
        yearField.setValue(DataHelper.getCardDetailsWith0ForHolder().getYear());
        holderField.setValue(DataHelper.getCardDetailsWith0ForHolder().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsWith0ForHolder().getCvc());
        continueButton.click();

    }

    public void shouldPayWithSpecialCharactersForHolder() {
        cardNumberField.setValue(DataHelper.getCardDetailsWithSpecialCharactersForHolder().getNumber());
        monthField.setValue(DataHelper.getCardDetailsWithSpecialCharactersForHolder().getMonth());
        yearField.setValue(DataHelper.getCardDetailsWithSpecialCharactersForHolder().getYear());
        holderField.setValue(DataHelper.getCardDetailsWithSpecialCharactersForHolder().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsWithSpecialCharactersForHolder().getCvc());
        continueButton.click();

    }

    public void shouldPayWithCyrillicCharactersForHolder() {
        cardNumberField.setValue(DataHelper.getCardDetailsWithCyrillicCharactersForHolder().getNumber());
        monthField.setValue(DataHelper.getCardDetailsWithCyrillicCharactersForHolder().getMonth());
        yearField.setValue(DataHelper.getCardDetailsWithCyrillicCharactersForHolder().getYear());
        holderField.setValue(DataHelper.getCardDetailsWithCyrillicCharactersForHolder().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsWithCyrillicCharactersForHolder().getCvc());
        continueButton.click();
    }

    public void shouldPayWithoutCvcCode() {
        cardNumberField.setValue(DataHelper.getValidFirstCardDetails().getNumber());
        monthField.setValue(DataHelper.getValidFirstCardDetails().getMonth());
        yearField.setValue(DataHelper.getValidFirstCardDetails().getYear());
        holderField.setValue(DataHelper.getValidFirstCardDetails().getHolder());
        continueButton.click();
    }

    public void shouldPayWith0ForCvcCode() {
        cardNumberField.setValue(DataHelper.getCardDetailsWith0ForCode().getNumber());
        monthField.setValue(DataHelper.getCardDetailsWith0ForCode().getMonth());
        yearField.setValue(DataHelper.getCardDetailsWith0ForCode().getYear());
        holderField.setValue(DataHelper.getCardDetailsWith0ForCode().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsWith0ForCode().getCvc());
        continueButton.click();
    }

    public void shouldPayWith000ForCvcCode() {
        cardNumberField.setValue(DataHelper.getCardDetailsWith000ForCode().getNumber());
        monthField.setValue(DataHelper.getCardDetailsWith000ForCode().getMonth());
        yearField.setValue(DataHelper.getCardDetailsWith000ForCode().getYear());
        holderField.setValue(DataHelper.getCardDetailsWith000ForCode().getHolder());
        cvcCodeField.setValue(DataHelper.getCardDetailsWith000ForCode().getCvc());
        continueButton.click();
    }

    public void shouldGetSuccessNotification(String expectedText) {
        successNotification.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText(expectedText));

    }

    public void shouldGetErrorNotification(String expectedText) {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText(expectedText));
    }

    public void shouldGetWrongFormatNotification(String expectedText) {
        wrongFormatNotification.shouldBe(visible, Duration.ofSeconds(15)).shouldHave(exactText(expectedText));
    }
}
