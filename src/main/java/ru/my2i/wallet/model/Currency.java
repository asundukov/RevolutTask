package ru.my2i.wallet.model;

import java.util.Objects;

public class Currency {
    private final String code;
    private final int fractionDigits;
    private final String title;

    private Currency(String code, int fractionDigits, String title) {
        this.code = code;
        this.fractionDigits = fractionDigits;
        this.title = title;
    }

    public static Currency of(String code, int fractionCount, String title) {
        return new Currency(code, fractionCount, title);
    }

    public String getCode() {
        return code;
    }

    public int getFractionDigits() {
        return fractionDigits;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return fractionDigits == currency.fractionDigits &&
                Objects.equals(code, currency.code) &&
                Objects.equals(title, currency.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, fractionDigits, title);
    }
}
