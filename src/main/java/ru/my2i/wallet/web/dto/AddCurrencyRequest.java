package ru.my2i.wallet.web.dto;

import javax.validation.constraints.NotNull;

public class AddCurrencyRequest {
    @NotNull
    public String code;
    @NotNull
    public Integer fractionDigits;
    @NotNull
    public String title;
}
