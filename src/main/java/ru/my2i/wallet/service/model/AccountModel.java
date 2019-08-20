package ru.my2i.wallet.service.model;

import java.util.Objects;

public class AccountModel {

    private final String service;
    private final String id;

    public AccountModel(String service, String id) {
        this.service = service;
        this.id = id;
    }

    public static AccountModel of(String service, String id) {
        return new AccountModel(service, id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountModel that = (AccountModel) o;
        return Objects.equals(service, that.service) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service, id);
    }
}
