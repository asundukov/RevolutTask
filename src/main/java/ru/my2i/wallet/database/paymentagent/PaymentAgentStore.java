package ru.my2i.wallet.database.paymentagent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ru.my2i.wallet.exception.paymentagent.PaymentAgentAlreadyExistsException;
import ru.my2i.wallet.exception.paymentagent.PaymentAgentCodeAlreadyExistsException;
import ru.my2i.wallet.model.PaymentAgent;
import ru.my2i.wallet.exception.paymentagent.PaymentAgentNotFoundException;

public class PaymentAgentStore {
    private Map<String, PaymentAgent> map = new ConcurrentHashMap<>();

    public PaymentAgent getByCode(String code) {
        PaymentAgent agent = map.get(code);
        if (agent == null) {
            throw new PaymentAgentNotFoundException(code);
        }
        return agent;
    }

    public PaymentAgent add(String code, String title) {
        PaymentAgent agent = PaymentAgent.of(code, title);
        PaymentAgent existed = map.putIfAbsent(code, agent);
        if (existed == null) {
            return agent;
        }
        if (existed.equals(agent)) {
            throw new PaymentAgentAlreadyExistsException(agent);
        }
        throw new PaymentAgentCodeAlreadyExistsException(agent);
    }
}
