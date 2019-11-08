package ru.my2i.wallet.exception.paymentagent;

public class PaymentAgentValidationException extends PaymentAgentGeneralException {
    public static final String CODE_EMPTY = "Payment agent [code] cannot be empty";
    public static final String TITLE_EMPTY = "Payment agent [title] cannot be empty";
    public PaymentAgentValidationException(String message) {
        super(message);
    }
}
