package com.squareup.okhttp;

import X.AnonymousClass006;
import java.io.UnsupportedEncodingException;
import okio.ByteString;

public final class Credentials {
    public static String basic(String str, String str2) {
        try {
            return AnonymousClass006.A07("Basic ", ByteString.of(AnonymousClass006.A09(str, ":", str2).getBytes("ISO-8859-1")).base64());
        } catch (UnsupportedEncodingException unused) {
            throw new AssertionError();
        }
    }
}
