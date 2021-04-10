package com.facebook.ultralight;

public class LocalInjectionBeforeInstanceInjectionException extends RuntimeException {
    public LocalInjectionBeforeInstanceInjectionException() {
    }

    public LocalInjectionBeforeInstanceInjectionException(String message) {
        super(message);
    }
}
