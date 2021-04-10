package com.facebook.acra.util;

import com.facebook.zstd.AbstractZstdOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.zip.GZIPOutputStream;

public class CompressionOutputStream extends FilterOutputStream {
    private static boolean triedLoadingZstd = false;
    private static Class<? extends AbstractZstdOutputStream> zstdImpl;
    private boolean mUseZstd;

    /* JADX DEBUG: Type inference failed for r1v5. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<? extends com.facebook.zstd.AbstractZstdOutputStream> */
    private static Class<? extends AbstractZstdOutputStream> getZstdImpl() {
        if (triedLoadingZstd) {
            return zstdImpl;
        }
        try {
            zstdImpl = Class.forName("com.facebook.zstd.ZstdOutputStream");
        } catch (ClassNotFoundException | ExceptionInInitializerError e) {
            zstdImpl = null;
        }
        triedLoadingZstd = true;
        return zstdImpl;
    }

    private static OutputStream getUnderlyingStream(OutputStream raw, int blockSize, boolean useZstd) throws IOException {
        if (useZstd && getZstdImpl() != null) {
            try {
                return (OutputStream) getZstdImpl().getConstructor(OutputStream.class, Integer.TYPE, Integer.TYPE).newInstance(raw, Integer.valueOf(blockSize), 13);
            } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                zstdImpl = null;
            }
        }
        return new GZIPOutputStream(raw);
    }

    public CompressionOutputStream(OutputStream raw, int blockSize, boolean useZstd) throws IOException {
        super(getUnderlyingStream(raw, blockSize, useZstd));
        this.mUseZstd = useZstd;
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream
    public void write(byte[] b, int off, int len) throws IOException {
        this.out.write(b, off, len);
    }

    public void finish() throws IOException {
        if (!this.mUseZstd || getZstdImpl() == null) {
            ((GZIPOutputStream) this.out).finish();
        } else {
            ((AbstractZstdOutputStream) this.out).finish();
        }
    }
}
