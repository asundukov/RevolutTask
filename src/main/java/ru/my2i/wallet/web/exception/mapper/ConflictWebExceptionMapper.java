package ru.my2i.wallet.web.exception.mapper;

import ru.my2i.wallet.web.exception.BadRequestWebException;
import ru.my2i.wallet.web.exception.ConflictWebException;

import javax.ws.rs.core.Response;

public class ConflictWebExceptionMapper extends AbstractWebExceptionMapper<ConflictWebException> {
    @Override
    public Response toResponse(ConflictWebException exception) {
        return super.toResponse(exception, 409);
    }
}