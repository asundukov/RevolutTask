package ru.my2i.wallet.store;

import ru.my2i.wallet.service.model.AccountModel;
import ru.my2i.wallet.service.model.AmountListModel;

import java.util.HashMap;
import java.util.Map;

public class AccountStore {
    private Map<AccountModel, AmountListModel> map = new HashMap<>();
    private static AccountStore instance;

    public static AccountStore getInstance() {
        if (instance == null) {
            instance = new AccountStore();
        }
        return instance;
    }

    public AmountListModel getOrCreateAmountByAccount(AccountModel accountModel) {
        AmountListModel amountListModel = map.get(accountModel);
        if (amountListModel == null) {
            amountListModel = AmountListModel.empty();
            map.put(accountModel, amountListModel);
        }
        return amountListModel;
    }

    public void save(AccountModel accountModel, AmountListModel newAmounts) {
        map.put(accountModel, newAmounts);
    }
}
