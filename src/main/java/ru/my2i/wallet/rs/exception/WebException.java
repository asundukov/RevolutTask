package com.exenium.core.resource.common.webexception;

public class WebException extends RuntimeException {
    public WebException() {
        this("Something went wrong");
    }

    public WebException(String message) {
        super(message);
    }

    WebException(String message, Exception e) {
        super(message, e);
    }

    WebException(Exception e) {
        super(e);
    }
}
