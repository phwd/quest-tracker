package com.facebook.jni;

import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class CppException extends RuntimeException {
    @DoNotStrip
    public CppException(String str) {
        super(str);
    }
}
