package ru.my2i.wallet.service.exception;

import ru.my2i.wallet.service.model.AmountModel;

public class NegativeTransferException extends RuntimeException {

    public NegativeTransferException(AmountModel amount) {
        super(String.format("You cannot transfer funds with negative value [%f]", amount.getValue()));
    }
}
