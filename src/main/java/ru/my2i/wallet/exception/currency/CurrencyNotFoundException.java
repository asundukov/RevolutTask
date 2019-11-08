package ru.my2i.wallet.exception.currency;

import static java.lang.String.format;

public class CurrencyNotFoundException extends CurrencyGeneralException {
    public CurrencyNotFoundException(String code) {
        super(format("Currency [%s] not found", code));
    }
}
