package ru.my2i.wallet.service.currency;

import ru.my2i.wallet.database.currency.CurrencyStore;
import ru.my2i.wallet.database.currency.CurrencyStoreFactory;
import ru.my2i.wallet.exception.currency.CurrencyValidationException;
import ru.my2i.wallet.model.Currency;

import java.util.Collection;

import static java.lang.String.format;
import static ru.my2i.wallet.exception.currency.CurrencyValidationException.CODE_EMPTY;
import static ru.my2i.wallet.exception.currency.CurrencyValidationException.FRACTION_DIGITS_NEGATIVE;
import static ru.my2i.wallet.exception.currency.CurrencyValidationException.TITLE_EMPTY;

public class CurrencyService {
    private static final int MAX_CODE_LENGTH = 8;
    private final CurrencyStore currencyStore;

    public CurrencyService(CurrencyStoreFactory factory) {
        this.currencyStore = factory.getInstance();
    }

    public Currency add(String code, int fractionDigits, String title) {
        validateCode(code);
        validateFraction(fractionDigits);
        validateTitle(title);
        return currencyStore.add(code, fractionDigits, title);
    }

    public Currency getByCode(String code) {
        return currencyStore.getByCode(code);
    }

    public Collection<Currency> getAvailableCurrencies() {
        return currencyStore.getAvailableCurrencies();
    }

    private static void validateCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new CurrencyValidationException(CODE_EMPTY);
        }
        if (code.length() > MAX_CODE_LENGTH) {
            throw new CurrencyValidationException(format("Code length cannot be more than %d", MAX_CODE_LENGTH));
        }
    }

    private static void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new CurrencyValidationException(TITLE_EMPTY);
        }
    }

    private static void validateFraction(int fractionDigits) {
        if (fractionDigits < 0) {
            throw new CurrencyValidationException(FRACTION_DIGITS_NEGATIVE);
        }
    }

}
