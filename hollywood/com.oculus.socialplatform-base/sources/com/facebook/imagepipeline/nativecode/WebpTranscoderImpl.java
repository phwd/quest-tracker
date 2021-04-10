package com.facebook.imagepipeline.nativecode;

import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class WebpTranscoderImpl {
    @DoNotStrip
    public static native void nativeTranscodeWebpToJpeg(InputStream inputStream, OutputStream outputStream, int i) throws IOException;

    @DoNotStrip
    public static native void nativeTranscodeWebpToPng(InputStream inputStream, OutputStream outputStream) throws IOException;
}
