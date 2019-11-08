package ru.my2i.wallet.web.exception.mapper;

import ru.my2i.wallet.web.exception.NotFoundWebException;

import javax.ws.rs.core.Response;

public class NotFoundWebExceptionMapper extends AbstractWebExceptionMapper<NotFoundWebException> {
    @Override
    public Response toResponse(NotFoundWebException exception) {
        return super.toResponse(exception, 404);
    }
}
