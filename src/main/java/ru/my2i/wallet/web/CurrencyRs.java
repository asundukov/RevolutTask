package ru.my2i.wallet.web;

import ru.my2i.wallet.exception.currency.CurrencyAlreadyExistsException;
import ru.my2i.wallet.exception.currency.CurrencyCodeAlreadyUsedException;
import ru.my2i.wallet.exception.currency.CurrencyNotFoundException;
import ru.my2i.wallet.exception.currency.CurrencyValidationException;
import ru.my2i.wallet.service.currency.CurrencyService;
import ru.my2i.wallet.service.currency.CurrencyServiceFactory;
import ru.my2i.wallet.web.dto.AddCurrencyRequest;
import ru.my2i.wallet.web.dto.CurrencyResponse;
import ru.my2i.wallet.web.exception.BadRequestWebException;
import ru.my2i.wallet.web.exception.ConflictWebException;
import ru.my2i.wallet.web.exception.NotFoundWebException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/currency")
@Produces(MediaType.APPLICATION_JSON)
public class CurrencyRs {

    private final CurrencyService currencyService;

    public CurrencyRs(CurrencyServiceFactory currencyServiceFactory) {
        this.currencyService = currencyServiceFactory.getInstance();
    }

    @GET
    public List<CurrencyResponse> getAvailable() {
        return currencyService.getAvailableCurrencies().stream()
                .map(CurrencyResponse::new)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{code}")
    public CurrencyResponse getByCode(@PathParam("code") String code) {
        try {
            return new CurrencyResponse(currencyService.getByCode(code));
        } catch (CurrencyNotFoundException e) {
            throw new NotFoundWebException(e);
        }
    }

    @POST
    public void add(@Valid @NotNull AddCurrencyRequest request) {
        try {
            currencyService.add(request.code, request.fractionDigits, request.title);
        } catch (CurrencyAlreadyExistsException ignored) {

        } catch (CurrencyValidationException e) {
            throw new BadRequestWebException(e);
        } catch (CurrencyCodeAlreadyUsedException e) {
            throw new ConflictWebException(e);
        }
    }
}
