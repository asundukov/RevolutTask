package ru.my2i.wallet.database.paymentagent;

public class PaymentAgentStoreFactoryImpl implements PaymentAgentStoreFactory {
    private static PaymentAgentStore instance = new PaymentAgentStore();

    public PaymentAgentStore getInstance() {
        if (instance == null) {
            instance = new PaymentAgentStore();
        }
        return instance;
    }
}
