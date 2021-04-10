package com.squareup.okhttp;

import X.AnonymousClass06;
import X.ci;
import java.io.UnsupportedEncodingException;

public final class Credentials {
    public static String basic(String str, String str2) {
        try {
            return AnonymousClass06.A04("Basic ", ci.A05(AnonymousClass06.A05(str, ":", str2).getBytes("ISO-8859-1")).A08());
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError();
        }
    }
}
