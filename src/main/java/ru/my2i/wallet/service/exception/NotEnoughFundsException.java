package ru.my2i.wallet.service.exception;

import java.math.BigDecimal;

public class NotEnoughFundsException extends RuntimeException {
    public NotEnoughFundsException(BigDecimal balance) {
        super(String.format("Not enough funds in the wallet. Current available [%f]", balance));
    }
}
