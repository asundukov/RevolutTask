package ru.my2i.wallet.rs;

import ru.my2i.wallet.rs.dto.AddAmountDto;
import ru.my2i.wallet.service.WalletService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/service")
@Produces(MediaType.APPLICATION_JSON)
public class ServiceAccountRs {
    private final WalletService walletService;

    public ServiceAccountRs() {
        this.walletService = WalletService.getInstance();
    }

    @POST
    @Path("/account/add-amounts")
    public void addAmounts(AddAmountDto dto) {
        walletService.addAmounts(dto.user.getUserModel(), dto.amount.getAmountListModel());
    }

}
