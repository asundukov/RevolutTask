package ru.my2i.wallet.rs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my2i.wallet.rs.dto.TransferDto;
import ru.my2i.wallet.rs.dto.UserDto;
import ru.my2i.wallet.rs.exception.BadRequestWebException;

public class TransferRsTest extends AbstractRsTest {

    private UserDto userFrom;
    private UserDto userTo;

    @BeforeMethod
    public void beforeTest() {
        this.userFrom = generateNewUser("testTransferUserFrom");
        this.userTo = generateNewUser("testTransferUserTo");
    }

    @Test
    public void testTransferFunds() {
        addFunds(userFrom, "15.5", "USD");
        addFunds(userTo, "1", "USD");

        transfer("4.3", "USD");

        checkBalance(userFrom, "11.2", "USD");
        checkBalance(userTo, "5.3", "USD");
    }

    @Test
    public void testTransferFundsNotAffectOtherCurrencies() {
        addFunds(userFrom, "10.9", "USD");
        addFunds(userFrom, "100", "RUB");
        addFunds(userTo, "400", "RUB");

        transfer("1", "USD");

        checkBalance(userFrom, "100", "RUB");
        checkBalance(userTo, "400", "RUB");
    }

    @Test(expectedExceptions = BadRequestWebException.class)
    public void testNotEnoughFundsException() {
        addFunds(userFrom, "5", "USD");
        transfer("10", "USD");
    }

    @Test
    public void testNotEnoughFundsExceptionNotAffectBalances() {
        addFunds(userFrom, "5", "USD");
        try {
            transfer("10", "USD");
        } catch (BadRequestWebException ignored) {
        }
        checkBalance(userFrom, "5", "USD");
        checkBalance(userTo, "0", "USD");
    }

    @Test(expectedExceptions = BadRequestWebException.class)
    public void testNegativeTransferException() {
        addFunds(userFrom, "5", "USD");
        transfer("-1", "USD");
    }

    @Test
    public void testNegativeTransferNotAffectBalances() {
        addFunds(userFrom, "5", "USD");
        try {
            transfer("-1", "USD");
        } catch (BadRequestWebException ignored) {
        }
        checkBalance(userFrom, "5", "USD");
        checkBalance(userTo, "0", "USD");
    }

    private void transfer(String value, String currency) {
        TransferDto dto = new TransferDto();
        dto.from = userFrom;
        dto.to = userTo;
        dto.amount = amount(value, currency);
        walletRs.transferFunds(dto);
    }


}