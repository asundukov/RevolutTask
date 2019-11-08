package ru.my2i.wallet.web.integration.currency;

import ru.my2i.wallet.database.currency.CurrencyStore;
import ru.my2i.wallet.database.currency.CurrencyStoreFactory;

class CurrencyStoreBuildFactory implements CurrencyStoreFactory {
    public CurrencyStore getInstance() {
        return new CurrencyStore();
    }

}
