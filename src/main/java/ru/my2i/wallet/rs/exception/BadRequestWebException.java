package com.exenium.core.resource.common.webexception;

public class BadRequestWebException extends WebException {
    public BadRequestWebException(Exception e) {
        super(e);
    }

    public BadRequestWebException(String message) {
        super(message);
    }
}
