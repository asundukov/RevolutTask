package ru.my2i.wallet.web.exception.mapper;

import ru.my2i.wallet.web.exception.BadRequestWebException;

import javax.ws.rs.core.Response;

public class GeneralWebExceptionMapper extends AbstractWebExceptionMapper<Exception> {
    @Override
    public Response toResponse(Exception exception) {
        return super.toResponse(exception, 500);
    }
}
