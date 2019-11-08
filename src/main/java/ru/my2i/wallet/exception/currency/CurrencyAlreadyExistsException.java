package ru.my2i.wallet.exception.currency;

import ru.my2i.wallet.model.Currency;

import static java.lang.String.format;

public class CurrencyAlreadyExistsException extends CurrencyGeneralException {

    private Currency existedCurrency;

    public CurrencyAlreadyExistsException(Currency existedCurrency) {
        super(format("Currency [%s] already exists", existedCurrency.getCode()));
        this.existedCurrency = existedCurrency;
    }

    public Currency getExistedCurrency() {
        return existedCurrency;
    }
}
