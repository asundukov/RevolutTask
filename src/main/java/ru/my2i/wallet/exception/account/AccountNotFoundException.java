package ru.my2i.wallet.exception.account;

import ru.my2i.wallet.model.PaymentAgent;

import static java.lang.String.format;

public class AccountNotFoundException extends AccountGeneralException {
    public AccountNotFoundException(String name, PaymentAgent paymentAgent) {
        super(format("Account [%s] not found for service [%s]", name, paymentAgent.getCode()));
    }
}
