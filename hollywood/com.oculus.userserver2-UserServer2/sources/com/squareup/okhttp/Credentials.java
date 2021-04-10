package com.squareup.okhttp;

import X.AnonymousClass06;
import X.WM;
import java.io.UnsupportedEncodingException;

public final class Credentials {
    public static String basic(String str, String str2) {
        try {
            return AnonymousClass06.A03("Basic ", WM.A05(AnonymousClass06.A04(str, ":", str2).getBytes("ISO-8859-1")).A08());
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError();
        }
    }
}
