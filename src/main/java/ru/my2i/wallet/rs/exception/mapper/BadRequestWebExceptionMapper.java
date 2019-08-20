package ru.my2i.wallet.rs.exception.mapper;

import ru.my2i.wallet.rs.exception.BadRequestWebException;

import javax.ws.rs.core.Response;

public class BadRequestWebExceptionMapper extends AbstractWebExceptionMapper<BadRequestWebException> {
    @Override
    public Response toResponse(BadRequestWebException exception) {
        return super.toResponse(exception, 400);
    }
}
