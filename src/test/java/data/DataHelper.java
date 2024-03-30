package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static final Faker fakerEn = new Faker(new Locale("en"));
    private static final Faker fakerRu = new Faker(new Locale("ru"));

    private DataHelper() {

    }

    @Value
    public static class CardDetails {
        String number;
        String month;
        String year;
        String holder;
        String cvc;

    }

    public static CardDetails getValidFirstCardDetails() {

        return new CardDetails("1111 2222 3333 4444", getRandomMonth(), getValidYear(), getRandomNameEn(), getRandomCode());
    }

    public static CardDetails getValidSecondCardDetails() {
        return new CardDetails("5555 6666 7777 8888", getRandomMonth(), getValidYear(), getRandomNameEn(), getRandomCode());
    }

    public static CardDetails getCardDetailsOtherCardNumber() {
        return new CardDetails(getOtherCardNumber(), getRandomMonth(), getValidYear(), getRandomNameEn(), getRandomCode());

    }

    public static CardDetails getCardDetailsShortCardNumber() {
        return new CardDetails(getShortCardNumber(), getRandomMonth(), getValidYear(), getRandomNameEn(), getRandomCode());
    }

    public static CardDetails getCardDetailsWith0ForMonth() {
        return new CardDetails("1111 2222 3333 4444", "0", getValidYear(), getRandomNameEn(), getRandomCode());
    }

    public static CardDetails getCardDetailsWith00ForMonth() {
        return new CardDetails("1111 2222 3333 4444", "00", getValidYear(), getRandomNameEn(), getRandomCode());
    }

    public static CardDetails getCardDetailsWith13ForMonth() {
        return new CardDetails("1111 2222 3333 4444", "13", getValidYear(), getRandomNameEn(), getRandomCode());
    }

    public static CardDetails getCardDetailsWith23ForYear() {
        return new CardDetails("1111 2222 3333 4444", getRandomMonth(), "23", getRandomNameEn(), getRandomCode());
    }

    public static CardDetails getCardDetailsWith02ForMonthAnd24ForYear() {
        return new CardDetails("1111 2222 3333 4444", "02", "24", getRandomNameEn(), getRandomCode());
    }

    public static CardDetails getCardDetailsWith30ForYear() {
        return new CardDetails("1111 2222 3333 4444", getRandomMonth(), "30", getRandomNameEn(), getRandomCode());
    }

    public static CardDetails getCardDetailsWith0ForHolder() {
        return new CardDetails("1111 2222 3333 4444", getRandomMonth(), getValidYear(), "0", getRandomCode());
    }

    public static CardDetails getCardDetailsWithSpecialCharactersForHolder() {
        return new CardDetails("1111 2222 3333 4444", getRandomMonth(), getValidYear(), "#$%^", getRandomCode());
    }

    public static CardDetails getCardDetailsWithCyrillicCharactersForHolder() {
        return new CardDetails("1111 2222 3333 4444", getRandomMonth(), getValidYear(), getRandomNameRu(), getRandomCode());
    }

    public static CardDetails getCardDetailsWith0ForCode() {
        return new CardDetails("1111 2222 3333 4444", getRandomMonth(), getValidYear(), getRandomNameEn(), "0");
    }

    public static CardDetails getCardDetailsWith000ForCode() {
        return new CardDetails("1111 2222 3333 4444", getRandomMonth(), getValidYear(), getRandomNameEn(), "000");
    }

    public static String getRandomMonth() {
        String month;
        final String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        Random rnd = new Random();
        int index = rnd.nextInt(months.length);
        return month = months[index];

    }

    private static String getValidYear() {
        String validYear;
        final String[] years = {"24", "25", "26", "27", "28", "29"};
        Random rnd = new Random();
        int index = rnd.nextInt(years.length);
        return validYear = years[index];
    }

    private static String getRandomNameEn() {
        return fakerEn.name().fullName();
    }

    private static String getRandomNameRu() {
        return fakerRu.name().fullName();
    }

    private static String getRandomCode() {
        return fakerEn.numerify("###");
    }

    private static String getOtherCardNumber() {
        return fakerEn.numerify("#### #### #### ####");
    }

    private static String getShortCardNumber() {
        return fakerEn.numerify("#### #### #### ###");
    }

}

