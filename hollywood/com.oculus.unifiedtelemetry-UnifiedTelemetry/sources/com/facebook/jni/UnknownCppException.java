package com.facebook.jni;

import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class UnknownCppException extends CppException {
    @DoNotStrip
    public UnknownCppException() {
        super("Unknown");
    }

    @DoNotStrip
    public UnknownCppException(String str) {
        super(str);
    }
}
