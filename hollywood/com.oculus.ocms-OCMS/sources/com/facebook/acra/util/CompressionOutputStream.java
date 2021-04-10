package com.facebook.acra.util;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.zstd.AbstractZstdOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.GZIPOutputStream;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class CompressionOutputStream extends FilterOutputStream {
    private static boolean triedLoadingZstd = false;
    @Nullable
    private static Class<? extends AbstractZstdOutputStream> zstdImpl;
    private boolean mUseZstd;

    /* JADX DEBUG: Type inference failed for r0v5. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<? extends com.facebook.zstd.AbstractZstdOutputStream> */
    @Nullable
    private static Class<? extends AbstractZstdOutputStream> getZstdImpl() {
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
        super(getUnderlyingStream(outputStream, i, z));
        this.mUseZstd = z;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    public void finish() throws IOException {
        if (!this.mUseZstd || getZstdImpl() == null) {
            ((GZIPOutputStream) this.out).finish();
        } else {
            ((AbstractZstdOutputStream) this.out).finish();
        }
    }
}
