package ru.my2i.wallet.service;

import ru.my2i.wallet.database.AccountStore;
import ru.my2i.wallet.model.Account;
import ru.my2i.wallet.model.Balance;
import ru.my2i.wallet.model.PaymentAgent;

public class AccountService {
    private static AccountService instance;

    private final AccountStore accountStore;

    private AccountService() {
        this.accountStore = AccountStore.getInstance();
    }

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    public void add(PaymentAgent paymentAgent, String name) {
        Account account = accountStore.add(paymentAgent, name);
    }
}
