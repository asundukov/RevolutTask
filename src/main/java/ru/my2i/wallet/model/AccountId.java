package ru.my2i.wallet.model;

import java.util.Objects;

public class AccountId {
    private final PaymentAgent agent;
    private final String name;

    public AccountId(PaymentAgent agent, String name) {
        this.name = name;
        this.agent = agent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountId accountId = (AccountId) o;
        return Objects.equals(name, accountId.name) &&
                Objects.equals(agent, accountId.agent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, agent);
    }
}
