package com.facebook.acra.util;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.zstd.AbstractZstdOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class CompressionOutputStream extends FilterOutputStream {
    public static boolean triedLoadingZstd;
    @Nullable
    public static Class<? extends AbstractZstdOutputStream> zstdImpl;
    public boolean mUseZstd;

    public static OutputStream getUnderlyingStream(OutputStream outputStream, int i, boolean z) throws IOException {
        if (z && getZstdImpl() != null) {
            try {
                Class<? extends AbstractZstdOutputStream> zstdImpl2 = getZstdImpl();
                Class<?> cls = Integer.TYPE;
                return (OutputStream) zstdImpl2.getConstructor(OutputStream.class, cls, cls).newInstance(outputStream, Integer.valueOf(i), 13);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
                zstdImpl = null;
            }
        }
        return new GZIPOutputStream(outputStream);
    }

    /* JADX DEBUG: Type inference failed for r1v2. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<? extends com.facebook.zstd.AbstractZstdOutputStream> */
    @Nullable
    public static Class<? extends AbstractZstdOutputStream> getZstdImpl() {
        Class cls;
        if (triedLoadingZstd) {
            return zstdImpl;
        }
        try {
            Class cls2 = Class.forName("com.facebook.zstd.ZstdOutputStream");
            zstdImpl = cls2;
            cls = cls2;
        } catch (ClassNotFoundException | ExceptionInInitializerError unused) {
            cls = null;
            zstdImpl = null;
        }
        triedLoadingZstd = true;
        return cls;
    }

    public void finish() throws IOException {
        if (!this.mUseZstd || getZstdImpl() == null) {
            ((DeflaterOutputStream) this.out).finish();
            return;
        }
        throw null;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    public CompressionOutputStream(OutputStream outputStream, int i, boolean z) throws IOException {
        super(getUnderlyingStream(outputStream, i, z));
        this.mUseZstd = z;
    }
}
