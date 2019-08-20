package ru.my2i.wallet.rs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.my2i.wallet.service.model.AccountModel;

public class UserDto {
    public String service;
    public String id;

    @JsonIgnore
    public AccountModel getUserModel() {
        return AccountModel.of(service, id);
    }
}
