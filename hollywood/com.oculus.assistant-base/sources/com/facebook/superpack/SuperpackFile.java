package com.facebook.superpack;

import X.KJ;
import java.io.Closeable;
import java.io.InputStream;

public final class SuperpackFile implements Closeable {
    public int mLength;
    public long mPtr;

    public static native void closeNative(long j);

    public static native long createSuperpackFileNative(String str, InputStream inputStream);

    public static native long createSuperpackFileNative(String str, byte[] bArr);

    public static native int getLengthNative(long j);

    public static native String getNameNative(long j);

    public static native void readBytesNative(long j, int i, int i2, byte[] bArr, int i3);

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        long j = this.mPtr;
        if (j != 0) {
            closeNative(j);
            this.mPtr = 0;
        } else {
            throw new IllegalStateException();
        }
    }

    static {
        KJ.A05("superpack-jni", 0);
    }

    @Override // java.lang.Object
    public void finalize() {
        long j = this.mPtr;
        if (j != 0) {
            closeNative(j);
            this.mPtr = 0;
            throw new IllegalStateException();
        }
    }

    public SuperpackFile(long j) {
        if (j != 0) {
            this.mPtr = j;
            this.mLength = getLengthNative(j);
            return;
        }
        throw null;
    }
}
