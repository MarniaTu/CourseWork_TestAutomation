package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPaymentPage {

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
        heading.shouldHave(text("Кредит по данным карты")).shouldBe(visible, Duration.ofSeconds(15));
    }

    public void shouldPayByCreditOnFirstCard() {
        cardNumberField.setValue(DataHelper.getValidFirstCardDetails().getNumber());
        monthField.setValue(DataHelper.getValidFirstCardDetails().getMonth());
        yearField.setValue(DataHelper.getValidFirstCardDetails().getYear());
        holderField.setValue(DataHelper.getValidFirstCardDetails().getHolder());
        cvcCodeField.setValue(DataHelper.getValidFirstCardDetails().getCvc());
        continueButton.click();

    }

    public void shouldPayByCreditOnSecondCard() {
        cardNumberField.setValue(DataHelper.getValidSecondCardDetails().getNumber());
        monthField.setValue(DataHelper.getValidSecondCardDetails().getMonth());
        yearField.setValue(DataHelper.getValidFirstCardDetails().getYear());
        holderField.setValue(DataHelper.getValidSecondCardDetails().getHolder());
        cvcCodeField.setValue(DataHelper.getValidSecondCardDetails().getCvc());
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

