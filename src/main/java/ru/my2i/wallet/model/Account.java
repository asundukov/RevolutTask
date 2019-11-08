package ru.my2i.wallet.model;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Account {

    private final PaymentAgent paymentAgent;
    private final String name;
    private final Map<Currency, Balance> wallet = new ConcurrentHashMap<>();

    private Account(PaymentAgent paymentAgent, String name) {
        this.paymentAgent = paymentAgent;
        this.name = name;
    }

    public static Account of(PaymentAgent paymentAgent, String name) {
        return new Account(paymentAgent, name);
    }

    public PaymentAgent getPaymentAgent() {
        return paymentAgent;
    }

    public String getName() {
        return name;
    }

    public AccountId getId() {
        return new AccountId(paymentAgent, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(paymentAgent, account.paymentAgent) &&
                Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paymentAgent, name);
    }
}
