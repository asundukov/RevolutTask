package ru.my2i.wallet.exception.currency;

import ru.my2i.wallet.model.Currency;

import static java.lang.String.format;

public class CurrencyCodeAlreadyUsedException extends CurrencyGeneralException {

    public CurrencyCodeAlreadyUsedException(Currency existed) {
        super(format("Currency code [%s] already used for currency [%s]", existed.getCode(), existed.getTitle()));
    }
}
