package ru.my2i.wallet.exception.account;

import ru.my2i.wallet.model.Account;

import static java.lang.String.format;

public class AccountAlreadyExistsException extends AccountGeneralException {
    public AccountAlreadyExistsException(Account account) {
        super(format("Account [%s] for payment agent [%s] already exists",
                account.getName(), account.getPaymentAgent().getCode()));
    }
}
