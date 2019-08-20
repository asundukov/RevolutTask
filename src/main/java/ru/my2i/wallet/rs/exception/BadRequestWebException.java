package ru.my2i.wallet.rs.exception;

public class BadRequestWebException extends WebException {
    public BadRequestWebException(Exception e) {
        super(e);
    }
}
