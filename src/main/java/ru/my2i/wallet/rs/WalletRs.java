package ru.my2i.wallet.rs;


import ru.my2i.wallet.rs.dto.TransferDto;
import ru.my2i.wallet.rs.dto.WalletDto;
import ru.my2i.wallet.rs.exception.BadRequestWebException;
import ru.my2i.wallet.service.WalletService;
import ru.my2i.wallet.service.exception.NegativeTransferException;
import ru.my2i.wallet.service.exception.NotEnoughFundsException;
import ru.my2i.wallet.service.model.AccountModel;
import ru.my2i.wallet.service.model.AmountModel;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class AccountRs {

    private final WalletService walletService;

    public AccountRs() {
        this.walletService = WalletService.getInstance();
    }

    @GET
    @Path("/wallet")
    @Produces(MediaType.APPLICATION_JSON)
    public WalletDto getParam(@QueryParam("service") String service, @QueryParam("id") String id) {
        return WalletDto.of(walletService.getWallet(AccountModel.of(service, id)));
    }

    @POST
    @Path("/transfer")
    @Produces(MediaType.APPLICATION_JSON)
    public void getParam(TransferDto dto) {
        AccountModel from = dto.from.getUserModel();
        AccountModel to = dto.to.getUserModel();
        AmountModel amount = dto.amount.getAmountModel();
        try {
            walletService.transfer(from, to, amount);
        } catch (NegativeTransferException | NotEnoughFundsException e) {
            throw new BadRequestWebException(e);
        }
    }
}
