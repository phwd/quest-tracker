package com.squareup.okhttp;

import X.AnonymousClass006;
import X.C04610h7;
import java.io.UnsupportedEncodingException;

public final class Credentials {
    public static String basic(String str, String str2) {
        try {
            return AnonymousClass006.A05("Basic ", C04610h7.A05(AnonymousClass006.A07(str, ":", str2).getBytes("ISO-8859-1")).A08());
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError();
        }
    }
}
