package com.facebook;

public class FacebookException extends RuntimeException {
    public static final long serialVersionUID = 1;

    public String toString() {
        return getMessage();
    }

    public FacebookException() {
    }

    public FacebookException(String str) {
        super(str);
    }

    public FacebookException(String str, Throwable th) {
        super(str, th);
    }

    public FacebookException(String str, Object... objArr) {
        super(String.format(str, objArr));
    }

    public FacebookException(Throwable th) {
        super(th);
    }
}
