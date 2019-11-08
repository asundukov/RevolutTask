package ru.my2i.wallet.model;

import java.math.BigDecimal;
import java.util.*;

public class Balance {

    private BigDecimal amount = BigDecimal.ZERO;

    private Balance() {
    }

    public static Balance create() {
        return new Balance();
    }
}
