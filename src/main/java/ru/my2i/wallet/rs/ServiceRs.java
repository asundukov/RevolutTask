package ru.my2i.wallet.rs;

import ru.my2i.wallet.rs.dto.AddAmountDto;
import ru.my2i.wallet.service.WalletService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
public class ServiceRs {
    private final WalletService walletService;

    public ServiceRs() {
        this.walletService = WalletService.getInstance();
    }

    @POST
    @Path("/wallet/add-amount")
    public void addAmount(AddAmountDto dto) {
        walletService.addAmount(dto.user.getUserModel(), dto.amount.getAmountModel());
    }

}
