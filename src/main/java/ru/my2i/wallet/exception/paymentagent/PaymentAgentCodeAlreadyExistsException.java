package ru.my2i.wallet.exception.paymentagent;

import ru.my2i.wallet.model.PaymentAgent;

import static java.lang.String.format;

public class PaymentAgentCodeAlreadyExistsException extends PaymentAgentGeneralException {
    public PaymentAgentCodeAlreadyExistsException(PaymentAgent existed) {
        super(format("Payment agent code [%s] already used for agent [%s]", existed.getCode(), existed.getTitle()));
    }
}
