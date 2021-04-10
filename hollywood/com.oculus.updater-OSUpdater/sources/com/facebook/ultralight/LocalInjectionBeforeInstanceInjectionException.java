package com.facebook.ultralight;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class LocalInjectionBeforeInstanceInjectionException extends RuntimeException {
    public LocalInjectionBeforeInstanceInjectionException() {
    }

    public LocalInjectionBeforeInstanceInjectionException(String str) {
        super(str);
    }
}
