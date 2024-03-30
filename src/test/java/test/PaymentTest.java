package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.APIHelper;
import data.DataHelper;
import data.SQLHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.CreditPaymentPage;
import page.DebitCardPaymentPage;
import page.TourPage;


import static com.codeborne.selenide.Selenide.open;
import static data.SQLHelper.deleteDataFromTables;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PaymentTest {

    TourPage tourPage;
    DebitCardPaymentPage debitCardPaymentPage;
    CreditPaymentPage creditPaymentPage;


    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void successPaymentByFirstDebitCard() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getValidFirstCardDetails();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayByFirstCard();
        debitCardPaymentPage.shouldGetSuccessNotification("Операция одобрена Банком.");

    }

    @Test
    void getApprovedStatusAndCode200IfPaymentByFirstDebitCard() {
        APIHelper.sendRequestToPayAndGetStatus(DataHelper.getValidFirstCardDetails(), 200, "APPROVED");
    }

    @Test
    void returnApprovedStatus_FromSQL_IfPaymentByFirstDebitCard() {

        APIHelper.sendRequestToPay(DataHelper.getValidFirstCardDetails());
        var status = SQLHelper.getTransactionStatus();
        assertEquals(status, "APPROVED");
    }

    @Test
    void verifyIf_TransactionIdIsEqual_InPaymentsAndOrders_ForFirstDebitCard() {
        APIHelper.sendRequestToPay(DataHelper.getValidFirstCardDetails());
        var iDInPayments = SQLHelper.getTransactionIdFromPaymentEntity();
        var iDInOrders = SQLHelper.getPaymentIdFromOrders();
        assertEquals(iDInPayments, iDInOrders);
    }


    @Test
    void declinePaymentBySecondDebitCard() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getValidSecondCardDetails();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayBySecondCard();
        debitCardPaymentPage.shouldGetErrorNotification("Банк отказал в проведении операции.");

    }

    @Test
    void getDeclinedStatusIfPaymentBySecondDebitCard() {
        APIHelper.sendRequestToPayAndGetStatus(DataHelper.getValidSecondCardDetails(), 400, "DECLINED");
    }

    @Test
    void returnDeclinedStatus_FromSQL_IfPaymentBySecondDebitCard() {

        APIHelper.sendRequestToPay(DataHelper.getValidSecondCardDetails());
        var status = SQLHelper.getTransactionStatus();
        assertEquals(status, "DECLINED");
    }

    @Test
    void verifyIf_TransactionIdIsEqual_InPaymentsAndOrders_ForSecondDebitCard() {
        APIHelper.sendRequestToPay(DataHelper.getValidSecondCardDetails());
        var iDInPayments = SQLHelper.getTransactionIdFromPaymentEntity();
        var iDInOrders = SQLHelper.getPaymentIdFromOrders();
        assertEquals(iDInPayments, iDInOrders);
    }

    @Test
    void successPaymentByCreditOnFirstCard() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getValidFirstCardDetails();
        creditPaymentPage = tourPage.goToCreditPayment();
        creditPaymentPage.shouldVerifyPageIsVisible();
        creditPaymentPage.shouldPayByCreditOnFirstCard();
        creditPaymentPage.shouldGetSuccessNotification("Операция одобрена Банком.");

    }

    @Test
    void getApprovedStatusAndCode200IfCreditPaymentByFirstCard() {
        APIHelper.sendRequestToCreditPayAndGetStatus(DataHelper.getValidFirstCardDetails(), 200, "APPROVED");
    }

    @Test
    void returnApprovedStatus_FromSQL_IfCreditPaymentByFirstCard() {

        APIHelper.sendRequestToCreditPay(DataHelper.getValidFirstCardDetails());
        var expectedStatus = "APPROVED";
        var actualStatus = SQLHelper.getCreditTransactionStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void verifyIf_CreditTransactionIdIsEqual_InCreditEntityAndOrders_ForFirstDebitCard() {
        APIHelper.sendRequestToCreditPay(DataHelper.getValidFirstCardDetails());
        var idInCreditEntity = SQLHelper.getCreditTransactionId();
        var idInOrders = SQLHelper.getCreditTransactionIdFromOrders();
        assertEquals(idInCreditEntity, idInOrders);
    }

    @Test
    void declinePaymentByCreditOnSecondCard() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getValidSecondCardDetails();
        creditPaymentPage = tourPage.goToCreditPayment();
        creditPaymentPage.shouldVerifyPageIsVisible();
        creditPaymentPage.shouldPayByCreditOnSecondCard();
        creditPaymentPage.shouldGetErrorNotification("Банк отказал в проведении операции.");

    }

    @Test
    void getDeclinedStatusIfCreditPaymentBySecondCard() {
        APIHelper.sendRequestToCreditPayAndGetStatus(DataHelper.getValidSecondCardDetails(), 400, "DECLINED");

    }

    @Test
    void returnDeclinedStatus_FromSQL_IfCreditPaymentBySecondCard() {

        APIHelper.sendRequestToCreditPay(DataHelper.getValidSecondCardDetails());
        var expectedStatus = "DECLINED";
        var actualStatus = SQLHelper.getCreditTransactionStatus();
        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    void verifyIf_CreditTransactionIdIsEqual_InCreditEntityAndCreditOrders_ForSecondDebitCard() {
        APIHelper.sendRequestToCreditPay(DataHelper.getValidSecondCardDetails());
        var idInCreditEntity = SQLHelper.getCreditTransactionId();
        var idInOrders = SQLHelper.getCreditTransactionIdFromOrders();
        assertEquals(idInCreditEntity, idInOrders);
    }

    @Test
    void verifyIf_CreditTransactionIdIsNotEqual_InCreditEntityAndPaymentOrders_ForSecondDebitCard() {
        APIHelper.sendRequestToCreditPay(DataHelper.getValidSecondCardDetails());
        var idInCreditEntity = SQLHelper.getCreditTransactionId();
        var idInOrders = SQLHelper.getPaymentIdFromOrders();
        assertNotEquals(idInCreditEntity, idInOrders);
    }

    @Test
    void getDeclinedStatusIfPaymentByOtherDebitCard() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsOtherCardNumber();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayByOtherCard();
        debitCardPaymentPage.shouldGetErrorNotification("Ошибка! Банк отказал в проведении операции.");
    }

    @Test
    void getWrongFormatNotificationIfPaymentByShortDebitCard() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsShortCardNumber();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayByShortCardNumber();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверный формат");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWithoutDebitCardNumber() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsShortCardNumber();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWithoutCardNumber();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверный формат");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWithoutMonth() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getValidFirstCardDetails();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWithoutMonth();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверный формат");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWith0ForMonth() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsWith0ForMonth();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWith0ForMonth();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверный формат");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWith00ForMonth() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsWith00ForMonth();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWith00ForMonth();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверный формат");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWith13ForMonth() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsWith13ForMonth();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWith13ForMonth();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверно указан срок действия карты");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWithoutYear() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getValidFirstCardDetails();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWithoutYear();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверный формат");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWith23ForYear() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsWith23ForYear();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWith23ForYear();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Истёк срок действия карты");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWith02ForMonthAnd24ForYear() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsWith02ForMonthAnd24ForYear();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWith02ForMonth24ForYear();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверно указан срок действия карты");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWith30ForYear() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsWith30ForYear();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWith30ForYear();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверно указан срок действия карты");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWithoutHolder() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getValidFirstCardDetails();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWithoutHolder();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Поле обязательно для заполнения");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWith0ForHolder() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsWith0ForHolder();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWith0ForHolder();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверный формат");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWithSpecialCharactersForHolder() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsWithSpecialCharactersForHolder();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWithSpecialCharactersForHolder();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверный формат");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWithCyrillicCharactersForHolder() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsWithCyrillicCharactersForHolder();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWithCyrillicCharactersForHolder();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверный формат");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWithoutCode() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getValidFirstCardDetails();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWithoutCvcCode();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Поле обязательно для заполнения");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWith0ForCode() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsWith0ForCode();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWith0ForCvcCode();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверный формат");

    }

    @Test
    void getWrongFormatNotificationIfPaymentWith000ForCode() {
        tourPage = open("http://localhost:8080", TourPage.class);
        var cardDetails = DataHelper.getCardDetailsWith000ForCode();
        debitCardPaymentPage = tourPage.goToPaymentByDebitCard();
        debitCardPaymentPage.shouldVerifyPageIsVisible();
        debitCardPaymentPage.shouldPayWith000ForCvcCode();
        debitCardPaymentPage.shouldGetWrongFormatNotification("Неверный формат");

    }

}


