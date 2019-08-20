package ru.my2i.wallet.rs.exception;

import com.exenium.core.resource.common.webexception.BadRequestWebException;

import javax.ws.rs.core.Response;

public class BadRequestWebExceptionMapper extends AbstractWebExceptionMapper<BadRequestWebException> {
    @Override
    public Response toResponse(BadRequestWebException exception) {
        return super.toResponse(exception, 400);
    }
}
