package com.facebook.acra.anr;

public class ANRException extends Exception {
    public ANRException() {
    }

    public ANRException(String detailMessage) {
        super(detailMessage);
    }
}
