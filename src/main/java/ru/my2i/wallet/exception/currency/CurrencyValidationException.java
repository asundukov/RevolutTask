package ru.my2i.wallet.exception.currency;

public class CurrencyValidationException extends CurrencyGeneralException {

    public static final String CODE_EMPTY = "Currency field [code] cannot be empty";
    public static final String TITLE_EMPTY = "Currency field [title] cannot be empty";
    public static final String FRACTION_DIGITS_NEGATIVE = "Currency field [fraction_digits] cannot be negative";

    public CurrencyValidationException(String message) {
        super(message);
    }
}
