package ru.my2i.wallet.web.integration.currency;

import org.testng.annotations.BeforeMethod;
import ru.my2i.wallet.model.Currency;
import ru.my2i.wallet.web.CurrencyRs;
import ru.my2i.wallet.web.dto.AddCurrencyRequest;
import ru.my2i.wallet.web.dto.CurrencyResponse;

abstract class CurrencyRsCommonTest {
    CurrencyRs currencyRs;

    @BeforeMethod
    public void before() {
        currencyRs = new CurrencyRs(new CurrencyServiceBuildFactory());
    }

    static AddCurrencyRequest addRequest(String code, int fractionDigits, String title) {
        AddCurrencyRequest request = new AddCurrencyRequest();
        request.code = code;
        request.fractionDigits = fractionDigits;
        request.title = title;
        return request;
    }

    static CurrencyResponse responseFromRequest(AddCurrencyRequest request) {
        return new CurrencyResponse(Currency.of(
                request.code,
                request.fractionDigits,
                request.title
        ));
    }
}
