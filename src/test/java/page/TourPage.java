package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$;

public class TourPage {

    private final SelenideElement purchaseButton = $$("button").first();
    private final SelenideElement creditPurchaseButton = $$("button").last();

    public DebitCardPaymentPage goToPaymentByDebitCard() {
        purchaseButton.click();
        return new DebitCardPaymentPage();
    }

    public CreditPaymentPage goToCreditPayment() {
        creditPurchaseButton.click();
        return new CreditPaymentPage();
    }
}
