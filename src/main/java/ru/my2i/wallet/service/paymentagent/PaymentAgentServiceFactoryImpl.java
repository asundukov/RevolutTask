package ru.my2i.wallet.service.paymentagent;

import ru.my2i.wallet.database.paymentagent.PaymentAgentStoreFactoryImpl;

public class PaymentAgentServiceFactoryImpl implements PaymentAgentServiceFactory {
    private static PaymentAgentService instance = new PaymentAgentService(new PaymentAgentStoreFactoryImpl());

    @Override
    public PaymentAgentService getInstance() {
        return instance;
    }
}
