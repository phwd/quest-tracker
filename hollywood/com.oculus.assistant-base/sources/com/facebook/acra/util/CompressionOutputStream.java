package com.facebook.acra.util;

import java.io.FilterOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;

public class CompressionOutputStream extends FilterOutputStream {
    public static boolean triedLoadingZstd;
    public static Class zstdImpl;
    public boolean mUseZstd;

    public static OutputStream getUnderlyingStream(OutputStream outputStream, int i, boolean z) {
        if (z && getZstdImpl() != null) {
            try {
                Class zstdImpl2 = getZstdImpl();
                Class<?> cls = Integer.TYPE;
                return (OutputStream) zstdImpl2.getConstructor(OutputStream.class, cls, cls).newInstance(outputStream, Integer.valueOf(i), 13);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
                zstdImpl = null;
            }
        }
        return new GZIPOutputStream(outputStream);
    }

    public static Class getZstdImpl() {
        Class<?> cls;
        if (triedLoadingZstd) {
            return zstdImpl;
        }
        try {
            cls = Class.forName("com.facebook.zstd.ZstdOutputStream");
            zstdImpl = cls;
        } catch (ClassNotFoundException | ExceptionInInitializerError unused) {
            cls = null;
            zstdImpl = null;
        }
        triedLoadingZstd = true;
        return cls;
    }

    public void finish() {
        if (!this.mUseZstd || getZstdImpl() == null) {
            ((DeflaterOutputStream) this.out).finish();
            return;
        }
        throw new NullPointerException("finish");
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
    }

    public CompressionOutputStream(OutputStream outputStream, int i, boolean z) {
        super(getUnderlyingStream(outputStream, i, z));
        this.mUseZstd = z;
    }
}
