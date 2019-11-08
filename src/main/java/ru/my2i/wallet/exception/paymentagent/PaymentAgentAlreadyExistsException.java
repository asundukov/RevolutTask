package ru.my2i.wallet.exception.paymentagent;

import ru.my2i.wallet.model.PaymentAgent;

import static java.lang.String.format;

public class PaymentAgentAlreadyExistsException extends PaymentAgentGeneralException {
    private PaymentAgent existed;
    public PaymentAgentAlreadyExistsException(PaymentAgent existed) {
        super(format("PaymentAgent [%s] already exists", existed.getCode()));
        this.existed = existed;
    }
}
