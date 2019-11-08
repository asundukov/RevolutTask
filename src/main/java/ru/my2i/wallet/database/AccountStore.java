package ru.my2i.wallet.database;

import ru.my2i.wallet.exception.account.AccountAlreadyExistsException;
import ru.my2i.wallet.exception.account.AccountNotFoundException;
import ru.my2i.wallet.model.Account;
import ru.my2i.wallet.model.AccountId;
import ru.my2i.wallet.model.PaymentAgent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AccountStore {
    private Map<AccountId, Account> accounts = new ConcurrentHashMap<>();
    private static AccountStore instance;

    public static AccountStore getInstance() {
        if (instance == null) {
            instance = new AccountStore();
        }
        return instance;
    }

    public Account add(PaymentAgent paymentAgent, String accountName) {
        Account account = Account.of(paymentAgent, accountName);
        AccountId id = account.getId();
        Account existed = accounts.putIfAbsent(id, account);
        if (existed != null) {
            throw new AccountAlreadyExistsException(account);
        }
        return account;
    }

    public Account getAccount(String accountName, PaymentAgent paymentAgent) {
        AccountId id = new AccountId(paymentAgent, accountName);
        Account existed = accounts.get(id);
        if (existed == null) {
            throw new AccountNotFoundException(accountName, paymentAgent);
        }
        return existed;
    }
}
