package ru.my2i.wallet.service;

import ru.my2i.wallet.service.model.AccountModel;
import ru.my2i.wallet.service.model.AmountListModel;
import ru.my2i.wallet.store.AccountStore;

public class AccountService {
    private static AccountService instance;

    public static AccountService getInstance() {
        if (instance == null) {
            instance = new AccountService();
        }
        return instance;
    }

    private AccountStore accountStore;

    public AccountService() {
        this.accountStore = AccountStore.getInstance();
    }

    public void addAmounts(AccountModel accountModel, AmountListModel addAmounts) {
        AmountListModel existed = accountStore.getOrCreateAmountByAccount(accountModel);

        for (String currency : addAmounts.currencies()) {
            existed.addByCurrency(currency, addAmounts.getByCurrency(currency));
        }

        accountStore.save(accountModel, existed);
    }

    public AmountListModel getWallet(AccountModel accountModel) {
        return accountStore.getOrCreateAmountByAccount(accountModel);
    }
}
