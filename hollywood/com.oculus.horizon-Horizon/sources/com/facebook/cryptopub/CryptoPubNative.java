package com.facebook.cryptopub;

import X.C03160cK;

public class CryptoPubNative {
    private native String decryptNative(int i, String str, String str2, String str3, String str4);

    private native byte[] encryptNative(int i, String str, String str2, String str3);

    static {
        C03160cK.A05("cryptopub-jni", 0);
    }

    public byte[] encrypt(int i, String str, String str2, String str3) throws Exception {
        return encryptNative(i, str, str2, str3);
    }
}
