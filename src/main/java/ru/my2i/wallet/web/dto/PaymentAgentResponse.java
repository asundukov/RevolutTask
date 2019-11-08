package ru.my2i.wallet.web.dto;

import ru.my2i.wallet.model.PaymentAgent;

public class PaymentAgentResponse {

    private String code;
    private String title;

    public PaymentAgentResponse(PaymentAgent paymentAgent) {
        this.code = paymentAgent.getCode();
        this.title = paymentAgent.getTitle();
    }
}
