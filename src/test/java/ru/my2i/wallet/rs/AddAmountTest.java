package ru.my2i.wallet.rs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.my2i.wallet.rs.dto.UserDto;

import java.util.UUID;

public class AddAmountTest extends AbstractRsTest {
    private UserDto currentUserId;

    @BeforeMethod
    public void beforeTest() {
        this.currentUserId = new UserDto();
        this.currentUserId.service = "testAddAmount";
        this.currentUserId.id = UUID.randomUUID().toString();
    }


    @Test
    public void testAddAmount() {
        addFunds(currentUserId, "10", "USD");
        checkBalance(currentUserId, "10", "USD");
    }

    @Test
    public void testTwiceAddAmount() {
        addFunds(currentUserId, "10", "USD");
        addFunds(currentUserId, "5", "USD");
        checkBalance(currentUserId, "15", "USD");
    }

    @Test
    public void testAddNegativeAmount() {
        addFunds(currentUserId, "10.2", "USD");
        addFunds(currentUserId, "-5.5", "USD");
        checkBalance(currentUserId, "4.7", "USD");
    }
}
