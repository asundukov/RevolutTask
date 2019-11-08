package ru.my2i.wallet.web.exception;

public class WebException extends RuntimeException {
    WebException(Exception e) {
        super(e.getMessage(), e);
    }
}
