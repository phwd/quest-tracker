package com.oculus.vrcast.googlecast;

public class SessionSetupException extends Exception {
    public SessionSetupException() {
    }

    public SessionSetupException(String str) {
        super(str);
    }

    public SessionSetupException(Throwable th) {
        super(th);
    }

    public SessionSetupException(String str, Throwable th) {
        super(str, th);
    }
}
