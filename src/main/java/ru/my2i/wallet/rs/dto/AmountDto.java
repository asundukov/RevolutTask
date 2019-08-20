package ru.my2i.wallet.rs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.my2i.wallet.service.model.AmountModel;

import java.math.BigDecimal;


public class AmountDto {
    public BigDecimal value;
    public String currency;

    public static AmountDto of(BigDecimal value, String currency) {
        AmountDto dto = new AmountDto();
        dto.currency = currency;
        dto.value = value;
        return dto;
    }

    @JsonIgnore
    public AmountModel getAmountModel() {
        return AmountModel.of(value, currency);
    }
}
