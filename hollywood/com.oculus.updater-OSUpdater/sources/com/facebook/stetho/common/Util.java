package com.facebook.stetho.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Util {
    public static <T> T throwIfNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static void copy(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
