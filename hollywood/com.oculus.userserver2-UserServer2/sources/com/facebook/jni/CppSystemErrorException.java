package com.facebook.jni;

import com.facebook.jni.annotations.DoNotStrip;

@DoNotStrip
public class CppSystemErrorException extends CppException {
    public int errorCode;

    @DoNotStrip
    public CppSystemErrorException(String str, int i) {
        super(str);
        this.errorCode = i;
    }
}
