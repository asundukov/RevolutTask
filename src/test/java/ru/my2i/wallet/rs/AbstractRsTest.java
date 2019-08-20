package ru.my2i.wallet.rs;

import ru.my2i.wallet.rs.dto.AddAmountDto;
import ru.my2i.wallet.rs.dto.AmountDto;
import ru.my2i.wallet.rs.dto.AmountListDto;
import ru.my2i.wallet.rs.dto.UserDto;

import java.math.BigDecimal;
import java.util.UUID;

import static org.testng.Assert.assertEquals;

public abstract class AbstractRsTest {
    final WalletRs walletRs;
    final ServiceRs serviceRs;

    AbstractRsTest() {
        this.walletRs = new WalletRs();
        this.serviceRs = new ServiceRs();
    }

    static UserDto generateNewUser(String serviceName) {
        UserDto dto = new UserDto();
        dto.id = UUID.randomUUID().toString();
        dto.service = serviceName;
        return dto;
    }

    AmountDto amount(String value, String currency) {
        return AmountDto.of(new BigDecimal(value), currency);
    }

    void addFunds(UserDto user, String value, String currency) {
        AddAmountDto dto = new AddAmountDto();
        dto.user = user;
        dto.amount = amount(value, currency);
        serviceRs.addAmount(dto);
    }


    void checkBalance(UserDto userDto, String expected, String currency) {
        AmountDto expectedAmount = amount(expected, currency);
        AmountListDto actualList = walletRs.getAmount(userDto.service, userDto.id);
        BigDecimal value = actualList.getAmountListModel().getByCurrency(expectedAmount.currency);
        assertEquals(value, expectedAmount.value);
    }
}
