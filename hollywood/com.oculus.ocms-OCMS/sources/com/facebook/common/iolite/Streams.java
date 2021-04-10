package com.facebook.common.iolite;

import com.facebook.infer.annotation.Nullsafe;
import java.io.OutputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Streams {
    public static OutputStream nonClosing(OutputStream outputStream) {
        return new NonClosingOutputStream(outputStream);
    }
}
