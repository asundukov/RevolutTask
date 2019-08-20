package ru.my2i.wallet.service.model;

import java.math.BigDecimal;

public class AmountModel {
    private BigDecimal value;
    private String currency;

    private AmountModel(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public static AmountModel of(BigDecimal value, String currency) {
        return new AmountModel(value, currency);
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public AmountModel negate() {
        return AmountModel.of(value.negate(), currency);
    }
}
