package ru.my2i.wallet.web.integration.currency;

import ru.my2i.wallet.service.currency.CurrencyService;
import ru.my2i.wallet.service.currency.CurrencyServiceFactory;

class CurrencyServiceBuildFactory implements CurrencyServiceFactory {
    public CurrencyService getInstance() {
        return new CurrencyService(new CurrencyStoreBuildFactory());
    }
}
