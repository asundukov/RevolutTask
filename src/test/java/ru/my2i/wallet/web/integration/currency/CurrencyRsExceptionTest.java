package ru.my2i.wallet.web.integration.currency;

import org.testng.annotations.Test;
import ru.my2i.wallet.web.dto.AddCurrencyRequest;
import ru.my2i.wallet.web.dto.CurrencyResponse;
import ru.my2i.wallet.web.exception.BadRequestWebException;
import ru.my2i.wallet.web.exception.ConflictWebException;
import ru.my2i.wallet.web.exception.NotFoundWebException;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class CurrencyRsExceptionTest extends CurrencyRsCommonTest {
    private static AddCurrencyRequest req = addRequest("EUR", 2, "Euro");

    private static CurrencyResponse expected = responseFromRequest(req);

    @Test(expectedExceptions = ConflictWebException.class)
    public void testTheSameCodeAddingException() {
        AddCurrencyRequest req = addRequest("EUR", 0, "Euro");

        currencyRs.add(CurrencyRsExceptionTest.req);
        currencyRs.add(req);
    }

    @Test
    public void testTheSameCodeAdding() {
        AddCurrencyRequest req = addRequest("EUR", 0, "Euro");

        currencyRs.add(CurrencyRsExceptionTest.req);
        try {
            currencyRs.add(req);
        } catch (ConflictWebException ignored) {}

        List<CurrencyResponse> actual = currencyRs.getAvailable();
        assertThat(actual, hasItem(expected));
        assertThat(actual, hasSize(1));
    }

    @Test(expectedExceptions = BadRequestWebException.class)
    public void testEmptyCode() {
        AddCurrencyRequest req = addRequest("", 0, "Dollar");
        currencyRs.add(req);
    }

    @Test(expectedExceptions = BadRequestWebException.class)
    public void testLongCode() {
        AddCurrencyRequest req = addRequest("USDUSDUSD", 0, "Dollar");
        currencyRs.add(req);
    }

    @Test(expectedExceptions = BadRequestWebException.class)
    public void testNegativeFraction() {
        AddCurrencyRequest req = addRequest("USD", -1, "Dollar");
        currencyRs.add(req);
    }

    @Test(expectedExceptions = BadRequestWebException.class)
    public void testEmptyTitle() {
        AddCurrencyRequest req = addRequest("USD", 2, "");
        currencyRs.add(req);
    }

    @Test(expectedExceptions = NotFoundWebException.class)
    public void testNotFound() {
        currencyRs.getByCode("USD");
    }

}
