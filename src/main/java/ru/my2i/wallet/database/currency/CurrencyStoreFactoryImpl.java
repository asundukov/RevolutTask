package ru.my2i.wallet.database.currency;

public class CurrencyStoreFactoryImpl implements CurrencyStoreFactory {
    private static CurrencyStore instance = new CurrencyStore();

    public CurrencyStore getInstance() {
        if (instance == null) {
            instance = new CurrencyStore();
        }
        return instance;
    }

}
