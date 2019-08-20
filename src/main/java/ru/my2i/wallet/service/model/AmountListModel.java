package ru.my2i.wallet.service.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmountListModel {

    private Map<String, BigDecimal> amountByCurrency = new HashMap<>();


    private AmountListModel() {

    }

    public static AmountListModel of(List<AmountModel> amountModelList) {
        AmountListModel amountListModel = new AmountListModel();
        for (AmountModel model : amountModelList) {
            amountListModel.amountByCurrency.put(model.getCurrency(), model.getValue());
        }
        return amountListModel;
    }

    public static AmountListModel single(AmountModel amountModel) {
        return AmountListModel.of(Collections.singletonList(amountModel));
    }

    public static AmountListModel empty() {
        return AmountListModel.of(Collections.emptyList());
    }

    public Collection<String> currencies() {
        return amountByCurrency.keySet();
    }

    public void addAmount(AmountModel addAmount) {
        addByCurrency(addAmount.getCurrency(), addAmount.getValue());
    }

    private void addByCurrency(String currency, BigDecimal value) {
        if (!amountByCurrency.containsKey(currency)) {
            amountByCurrency.put(currency, BigDecimal.ZERO);
        }
        amountByCurrency.put(currency, amountByCurrency.get(currency).add(value));
    }

    public BigDecimal getByCurrency(String currency) {
        return amountByCurrency.getOrDefault(currency, BigDecimal.ZERO);
    }
}
