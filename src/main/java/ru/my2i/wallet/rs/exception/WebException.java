package ru.my2i.wallet.rs.exception;

public class WebException extends RuntimeException {
    WebException(Exception e) {
        super(e.getMessage(), e);
    }
}
