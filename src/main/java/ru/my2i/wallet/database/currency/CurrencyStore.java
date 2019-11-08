package ru.my2i.wallet.database.currency;

import ru.my2i.wallet.exception.currency.CurrencyAlreadyExistsException;
import ru.my2i.wallet.exception.currency.CurrencyCodeAlreadyUsedException;
import ru.my2i.wallet.exception.currency.CurrencyNotFoundException;
import ru.my2i.wallet.model.Currency;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrencyStore {
    private Map<String, Currency> currencies = new ConcurrentHashMap<>();

    public Currency add(String code, int fractionDigits, String title) {
        Currency currency = Currency.of(code, fractionDigits, title);
        Currency existed = currencies.putIfAbsent(code, currency);
        if (existed == null) {
            return currency;
        }
        if (existed.equals(currency)) {
            throw new CurrencyAlreadyExistsException(existed);
        }
        throw new CurrencyCodeAlreadyUsedException(existed);
    }

    public Currency getByCode(String code) {
        Currency currency = currencies.get(code);
        if (currency == null) {
            throw new CurrencyNotFoundException(code);
        }
        return currency;
    }

    public Collection<Currency> getAvailableCurrencies() {
        return Collections.unmodifiableCollection(currencies.values());
    }
}
