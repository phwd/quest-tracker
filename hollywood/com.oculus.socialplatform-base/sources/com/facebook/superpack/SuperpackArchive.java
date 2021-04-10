package com.facebook.superpack;

import X.AnonymousClass0l0;
import com.facebook.infer.annotation.Nullsafe;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class SuperpackArchive implements Iterator<SuperpackFile>, Closeable {
    public int mDecompressedFiles = 0;
    public long mPtr;

    public static native void appendNative(long j, long j2);

    public static native void closeNative(long j);

    public static native long createNative();

    public static native long extractNextNative(long j, String[] strArr);

    public static native boolean isEmptyNative(long j);

    public static native long nextNative(long j);

    public static native long readNative(InputStream inputStream, String str);

    public static native void setPackingOptionsNative(long j, boolean z, boolean z2);

    public static native void setStorageNative(long j, String str, int i);

    public static native void writeNative(long j, OutputStream outputStream) throws IOException;

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

    public boolean hasNext() {
        boolean isEmptyNative;
        synchronized (this) {
            long j = this.mPtr;
            if (j != 0) {
                isEmptyNative = isEmptyNative(j);
            } else {
                throw new IllegalStateException();
            }
        }
        return !isEmptyNative;
    }

    static {
        AnonymousClass0l0.A06("superpack-jni");
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

    public SuperpackArchive(long j, @Nullable String[] strArr) {
        if (j != 0) {
            this.mPtr = j;
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override // java.util.Iterator
    public synchronized SuperpackFile next() {
        long nextNative;
        long j = this.mPtr;
        if (j != 0) {
            nextNative = nextNative(j);
            if (nextNative != 0) {
                this.mDecompressedFiles++;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            throw new IllegalStateException();
        }
        return new SuperpackFile(nextNative);
    }
}
