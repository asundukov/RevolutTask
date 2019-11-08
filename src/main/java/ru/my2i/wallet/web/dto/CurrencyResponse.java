package ru.my2i.wallet.web.dto;

import ru.my2i.wallet.model.Currency;

import java.util.Objects;

public class CurrencyResponse {
    public final String code;
    public final int fractionDigits;
    public final String title;

    public CurrencyResponse(Currency currency) {
        this.code = currency.getCode();
        this.fractionDigits = currency.getFractionDigits();
        this.title = currency.getTitle();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyResponse that = (CurrencyResponse) o;
        return fractionDigits == that.fractionDigits &&
                Objects.equals(code, that.code) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, fractionDigits, title);
    }
}
