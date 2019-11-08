package ru.my2i.wallet.service.currency;

import ru.my2i.wallet.database.currency.CurrencyStoreFactoryImpl;

public class CurrencyServiceFactoryImpl implements CurrencyServiceFactory {
    private static CurrencyService instance = new CurrencyService(new CurrencyStoreFactoryImpl());
    public CurrencyService getInstance() {
        return instance;
    }

}
