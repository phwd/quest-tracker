package com.squareup.okhttp;

import X.AnonymousClass006;
import X.C07700vD;
import java.io.UnsupportedEncodingException;

public final class Credentials {
    public static String basic(String str, String str2) {
        try {
            return AnonymousClass006.A05("Basic ", C07700vD.A05(AnonymousClass006.A07(str, ":", str2).getBytes("ISO-8859-1")).A08());
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError();
        }
    }
}
