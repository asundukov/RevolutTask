package ru.my2i.wallet.model;

import java.util.Objects;

public class PaymentAgent {
    private final String code;
    private final String title;

    private PaymentAgent(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public static PaymentAgent of(String code, String title) {
        return new PaymentAgent(code, title);
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentAgent agent = (PaymentAgent) o;
        return Objects.equals(code, agent.code) &&
                Objects.equals(title, agent.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, title);
    }
}
