package ru.my2i.wallet.web.exception.mapper;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;

public class NotAllowedExceptionMapper extends AbstractWebExceptionMapper<NotAllowedException> {
    @Override
    public Response toResponse(NotAllowedException exception) {
        return super.toResponse(exception, 405);
    }
}
