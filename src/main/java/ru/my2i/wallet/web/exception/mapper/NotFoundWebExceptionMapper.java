package ru.my2i.wallet.web.exception.mapper;

import ru.my2i.wallet.web.exception.BadRequestWebException;

import javax.ws.rs.core.Response;

public class NotFoundWebExceptionMapper extends AbstractWebExceptionMapper<BadRequestWebException> {
    @Override
    public Response toResponse(BadRequestWebException exception) {
        return super.toResponse(exception, 404);
    }
}
