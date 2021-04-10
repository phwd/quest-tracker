package com.facebook.acra.util;

import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.GZIPOutputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class CompressionOutputStream extends FilterOutputStream {
    private static boolean triedLoadingZstd = false;
    private static Class<? extends Object> zstdImpl;
    private boolean mUseZstd;

    /* JADX DEBUG: Type inference failed for r0v5. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<? extends java.lang.Object> */
    private static Class<? extends Object> getZstdImpl() {
        if (triedLoadingZstd) {
            return zstdImpl;
        }
        try {
            zstdImpl = Class.forName("com.facebook.zstd.ZstdOutputStream");
        } catch (ClassNotFoundException | ExceptionInInitializerError unused) {
            zstdImpl = null;
        }
        triedLoadingZstd = true;
        return zstdImpl;
    }

    private static OutputStream getUnderlyingStream(OutputStream outputStream, int i, boolean z) throws IOException {
        if (z && getZstdImpl() != null) {
            try {
                return (OutputStream) getZstdImpl().getConstructor(OutputStream.class, Integer.TYPE, Integer.TYPE).newInstance(outputStream, Integer.valueOf(i), 13);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
                zstdImpl = null;
            }
        }
        return new GZIPOutputStream(outputStream);
    }

    public CompressionOutputStream(OutputStream outputStream, int i, boolean z) throws IOException {
        super(getUnderlyingStream(outputStream, 8192, z));
        this.mUseZstd = z;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    public final void finish() throws IOException {
        if (!this.mUseZstd || getZstdImpl() == null) {
            ((GZIPOutputStream) this.out).finish();
        } else {
            OutputStream outputStream = this.out;
        }
    }
}
