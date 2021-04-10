package com.oculus.platforminitexception;

public class PlatformInitException extends RuntimeException {
    public static final long serialVersionUID = 1;

    public PlatformInitException(String str) {
        super(str);
    }
}
