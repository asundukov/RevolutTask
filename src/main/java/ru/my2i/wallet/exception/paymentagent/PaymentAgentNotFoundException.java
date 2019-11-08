package ru.my2i.wallet.exception.paymentagent;

import static java.lang.String.format;

public class PaymentAgentNotFoundException extends RuntimeException {
    public PaymentAgentNotFoundException(String name) {
        super(format("Payment service [%s] does not exist", name));
    }
}
