package ru.my2i.wallet.web;

import ru.my2i.wallet.exception.paymentagent.PaymentAgentAlreadyExistsException;
import ru.my2i.wallet.exception.paymentagent.PaymentAgentCodeAlreadyExistsException;
import ru.my2i.wallet.exception.paymentagent.PaymentAgentNotFoundException;
import ru.my2i.wallet.exception.paymentagent.PaymentAgentValidationException;
import ru.my2i.wallet.service.paymentagent.PaymentAgentService;
import ru.my2i.wallet.service.paymentagent.PaymentAgentServiceFactory;
import ru.my2i.wallet.web.dto.AddPaymentAgentRequest;
import ru.my2i.wallet.web.dto.PaymentAgentResponse;
import ru.my2i.wallet.web.exception.BadRequestWebException;
import ru.my2i.wallet.web.exception.ConflictWebException;
import ru.my2i.wallet.web.exception.NotFoundWebException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/paymentagent")
@Produces(MediaType.APPLICATION_JSON)
public class PaymentAgentRs {
    private final PaymentAgentService paymentAgentService;

    public PaymentAgentRs(PaymentAgentServiceFactory currencyServiceFactory) {
        this.paymentAgentService = currencyServiceFactory.getInstance();
    }

    @GET
    @Path("/{code}")
    public PaymentAgentResponse getByCode(@PathParam("code") String code) {
        try {
            return new PaymentAgentResponse(paymentAgentService.getByCode(code));
        } catch (PaymentAgentNotFoundException e) {
            throw new NotFoundWebException(e);
        }
    }

    @POST
    public void add(AddPaymentAgentRequest request) {
        try {
            paymentAgentService.add(request.code, request.title);
        } catch (PaymentAgentAlreadyExistsException ignored) {

        } catch (PaymentAgentValidationException e) {
            throw new BadRequestWebException(e);
        } catch (PaymentAgentCodeAlreadyExistsException e) {
            throw new ConflictWebException(e);
        }
    }

}
