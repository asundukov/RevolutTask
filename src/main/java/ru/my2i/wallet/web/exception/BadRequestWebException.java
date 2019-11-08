package ru.my2i.wallet.web.exception;

public class BadRequestWebException extends WebException {
    public BadRequestWebException(Exception e) {
        super(e);
    }
}
