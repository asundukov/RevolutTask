package ru.my2i.wallet.rs.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


abstract class AbstractWebExceptionMapper<T extends Exception> implements ExceptionMapper<T> {
    Response toResponse(T exception, int code) {
        ErrorDto dto = new ErrorDto();
        dto.code = code;
        dto.codeDescription = Response.Status.fromStatusCode(code).getReasonPhrase();
        dto.message = exception.getMessage();
        return Response.status(code).entity(dto).build();
    }
}
