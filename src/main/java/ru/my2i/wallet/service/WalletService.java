package ru.my2i.wallet.service;

import ru.my2i.wallet.service.exception.NegativeTransferException;
import ru.my2i.wallet.service.exception.NotEnoughFundsException;
import ru.my2i.wallet.service.model.AccountModel;
import ru.my2i.wallet.service.model.AmountListModel;
import ru.my2i.wallet.service.model.AmountModel;
import ru.my2i.wallet.store.AccountStore;

import java.math.BigDecimal;

import static java.math.BigDecimal.ZERO;

public class WalletService {
    private static WalletService instance;

    public static WalletService getInstance() {
        if (instance == null) {
            instance = new WalletService();
        }
        return instance;
    }

    private AccountStore accountStore;

    public WalletService() {
        this.accountStore = AccountStore.getInstance();
    }

    public void addAmount(AccountModel accountModel, AmountModel addAmount) {
        AmountListModel existed = getWallet(accountModel);
        existed.addAmount(addAmount);
        accountStore.save(accountModel, existed);
    }

    public AmountListModel getWallet(AccountModel accountModel) {
        return accountStore.getOrCreateAmountByAccount(accountModel);
    }

    synchronized
    public void transfer(AccountModel from, AccountModel to, AmountModel amount) {

        if (amount.getValue().compareTo(ZERO) <= 0) {
            throw new NegativeTransferException(amount);
        }

        BigDecimal fromAmount = getWallet(from).getByCurrency(amount.getCurrency());
        BigDecimal fromResult = fromAmount.add(amount.getValue().negate());

        if (fromResult.compareTo(ZERO) < 0) {
            throw new NotEnoughFundsException(fromAmount);
        }

        addAmount(from, amount.negate());
        addAmount(to, amount);
    }

}
