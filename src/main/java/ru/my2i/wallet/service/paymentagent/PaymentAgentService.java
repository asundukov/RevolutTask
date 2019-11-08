package ru.my2i.wallet.service.paymentagent;

import ru.my2i.wallet.database.paymentagent.PaymentAgentStore;
import ru.my2i.wallet.database.paymentagent.PaymentAgentStoreFactory;
import ru.my2i.wallet.exception.currency.CurrencyValidationException;
import ru.my2i.wallet.exception.paymentagent.PaymentAgentValidationException;
import ru.my2i.wallet.model.PaymentAgent;

import static java.lang.String.format;
import static ru.my2i.wallet.exception.paymentagent.PaymentAgentValidationException.CODE_EMPTY;
import static ru.my2i.wallet.exception.paymentagent.PaymentAgentValidationException.TITLE_EMPTY;

public class PaymentAgentService {
    private static final int MAX_CODE_LENGTH = 8;
    private PaymentAgentStore paymentAgentStore;

    public PaymentAgentService(PaymentAgentStoreFactory factory) {
        this.paymentAgentStore = factory.getInstance();
    }

    public PaymentAgent getByCode(String code) {
        return paymentAgentStore.getByCode(code);
    }

    public PaymentAgent add(String code, String title) {
        validateCode(code);
        validateTitle(title);
        return paymentAgentStore.add(code, title);
    }

    private static void validateCode(String code) {
        if (code == null || code.isEmpty()) {
            throw new PaymentAgentValidationException(CODE_EMPTY);
        }
        if (code.length() > MAX_CODE_LENGTH) {
            throw new PaymentAgentValidationException(format("Code length cannot be more than %d", MAX_CODE_LENGTH));
        }
    }

    private static void validateTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new PaymentAgentValidationException(TITLE_EMPTY);
        }
    }

}
