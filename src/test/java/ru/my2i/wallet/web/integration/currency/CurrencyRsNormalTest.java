package ru.my2i.wallet.web.integration.currency;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.my2i.wallet.web.dto.AddCurrencyRequest;
import ru.my2i.wallet.web.dto.CurrencyResponse;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsCollectionContaining.hasItem;

public class CurrencyRsNormalTest extends CurrencyRsCommonTest {

    private static AddCurrencyRequest req1 = addRequest("EUR", 2, "Euro");
    private static AddCurrencyRequest req2 = addRequest("RUB", 0, "Ruble");
    private static AddCurrencyRequest req3 = addRequest("USD", 2, "Dollar");

    private static CurrencyResponse expected1 = responseFromRequest(req1);
    private static CurrencyResponse expected2 = responseFromRequest(req2);
    private static CurrencyResponse expected3 = responseFromRequest(req3);

    @Test
    public void testListZero() {
        List<CurrencyResponse> actual = currencyRs.getAvailable();

        assertThat(actual, empty());
    }

    @Test
    public void testAddSingle() {
        currencyRs.add(req1);

        List<CurrencyResponse> actual = currencyRs.getAvailable();

        assertThat(actual, hasItem(expected1));
        assertThat(actual, hasSize(1));
    }

    @Test
    public void testAddMultipleCheckList() {
        currencyRs.add(req1);
        currencyRs.add(req2);
        currencyRs.add(req3);

        List<CurrencyResponse> actual = currencyRs.getAvailable();

        assertThat(actual, hasItem(expected1));
        assertThat(actual, hasItem(expected2));
        assertThat(actual, hasItem(expected3));
        assertThat(actual, hasSize(3));
    }

    @Test
    public void testAddMultipleCheckSingle() {
        currencyRs.add(req1);
        currencyRs.add(req2);
        currencyRs.add(req3);

        assertThat(currencyRs.getByCode(req1.code), equalTo(expected1));
        assertThat(currencyRs.getByCode(req2.code), equalTo(expected2));
        assertThat(currencyRs.getByCode(req3.code), equalTo(expected3));
    }

    @Test
    public void testTheSameAdding() {
        AddCurrencyRequest req = addRequest("EUR", 2, "Euro");
        currencyRs.add(req);
        currencyRs.add(req);
        CurrencyResponse expected = responseFromRequest(req);
        List<CurrencyResponse> actual = currencyRs.getAvailable();
        assertThat(actual, hasItem(expected));
        assertThat(actual, hasSize(1));
    }
}
