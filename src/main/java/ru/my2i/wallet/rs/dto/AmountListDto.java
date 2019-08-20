package ru.my2i.wallet.rs.dto;

import ru.my2i.wallet.service.model.AmountListModel;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class AmountListDto extends ArrayList<AmountDto> {

    public AmountListDto() {

    }

    public static AmountListDto of(AmountListModel amountListModel) {
        AmountListDto dto = new AmountListDto();
        for (String currency : amountListModel.currencies()) {
            dto.add(AmountDto.of(amountListModel.getByCurrency(currency), currency));
        }
        return dto;
    }

    public AmountListModel getAmountListModel() {
        return AmountListModel.of(this.stream()
                .map(AmountDto::getAmountModel)
                .collect(Collectors.toList()));
    }
}
