package com.facebook.imagepipeline.memory;

import X.AbstractC10321rv;
import X.AnonymousClass006;
import X.AnonymousClass0KU;
import X.AnonymousClass1t2;
import X.C03250cX;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.infer.annotation.Nullsafe;
import java.io.Closeable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.STRICT)
public class NativeMemoryChunk implements AbstractC10321rv, Closeable {
    public boolean mIsClosed;
    public final long mNativePtr;
    public final int mSize;

    @DoNotStrip
    public static native long nativeAllocate(int i);

    @DoNotStrip
    public static native void nativeCopyFromByteArray(long j, byte[] bArr, int i, int i2);

    @DoNotStrip
    public static native void nativeCopyToByteArray(long j, byte[] bArr, int i, int i2);

    @DoNotStrip
    public static native void nativeFree(long j);

    @DoNotStrip
    public static native void nativeMemcpy(long j, long j2, int i);

    @DoNotStrip
    public static native byte nativeReadByte(long j);

    @Override // X.AbstractC10321rv, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (!this.mIsClosed) {
            this.mIsClosed = true;
            nativeFree(this.mNativePtr);
        }
    }

    @Override // X.AbstractC10321rv
    @Nullable
    public ByteBuffer getByteBuffer() {
        return null;
    }

    @Override // X.AbstractC10321rv
    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    @Override // X.AbstractC10321rv
    public synchronized int write(int i, byte[] bArr, int i2, int i3) {
        int min;
        if (bArr != null) {
            boolean z = false;
            if (!isClosed()) {
                z = true;
            }
            AnonymousClass0KU.A03(z);
            int i4 = this.mSize;
            min = Math.min(Math.max(0, i4 - i), i3);
            AnonymousClass1t2.A00(i, bArr.length, i2, min, i4);
            nativeCopyFromByteArray(this.mNativePtr + ((long) i), bArr, i2, min);
        } else {
            throw null;
        }
        return min;
    }

    static {
        C03250cX.A01("imagepipeline");
    }

    private void doCopy(int i, AbstractC10321rv r8, int i2, int i3) {
        if (r8 instanceof NativeMemoryChunk) {
            AnonymousClass0KU.A03(!isClosed());
            AnonymousClass0KU.A03(!r8.isClosed());
            AnonymousClass1t2.A00(i, r8.getSize(), i2, i3, this.mSize);
            nativeMemcpy(r8.getNativePtr() + ((long) i2), this.mNativePtr + ((long) i), i3);
            return;
        }
        throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
    }

    @Override // X.AbstractC10321rv
    public void copy(int i, AbstractC10321rv r10, int i2, int i3) {
        if (r10 != null) {
            if (r10.getUniqueId() == getUniqueId()) {
                Log.w("NativeMemoryChunk", AnonymousClass006.A0A("Copying from NativeMemoryChunk ", Integer.toHexString(System.identityHashCode(this)), " to NativeMemoryChunk ", Integer.toHexString(System.identityHashCode(r10)), " which share the same address ", Long.toHexString(this.mNativePtr)));
                AnonymousClass0KU.A01(false);
            }
            if (r10.getUniqueId() < getUniqueId()) {
                synchronized (r10) {
                    synchronized (this) {
                        doCopy(i, r10, i2, i3);
                    }
                }
                return;
            }
            synchronized (this) {
                synchronized (r10) {
                    doCopy(i, r10, i2, i3);
                }
            }
            return;
        }
        throw null;
    }

    @Override // java.lang.Object
    public void finalize() throws Throwable {
        if (!isClosed()) {
            Log.w("NativeMemoryChunk", AnonymousClass006.A07("finalize: Chunk ", Integer.toHexString(System.identityHashCode(this)), " still active. "));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }

    @Override // X.AbstractC10321rv
    public long getNativePtr() {
        return this.mNativePtr;
    }

    @Override // X.AbstractC10321rv
    public int getSize() {
        return this.mSize;
    }

    @Override // X.AbstractC10321rv
    public long getUniqueId() {
        return this.mNativePtr;
    }

    @VisibleForTesting
    public NativeMemoryChunk() {
        this.mSize = 0;
        this.mNativePtr = 0;
        this.mIsClosed = true;
    }

    public NativeMemoryChunk(int i) {
        AnonymousClass0KU.A01(Boolean.valueOf(i > 0));
        this.mSize = i;
        this.mNativePtr = nativeAllocate(i);
        this.mIsClosed = false;
    }

    @Override // X.AbstractC10321rv
    public synchronized byte read(int i) {
        boolean z = true;
        boolean z2 = false;
        if (!isClosed()) {
            z2 = true;
        }
        AnonymousClass0KU.A03(z2);
        boolean z3 = false;
        if (i >= 0) {
            z3 = true;
        }
        AnonymousClass0KU.A01(Boolean.valueOf(z3));
        if (i >= this.mSize) {
            z = false;
        }
        AnonymousClass0KU.A01(Boolean.valueOf(z));
        return nativeReadByte(this.mNativePtr + ((long) i));
    }

    @Override // X.AbstractC10321rv
    public synchronized int read(int i, byte[] bArr, int i2, int i3) {
        int min;
        if (bArr != null) {
            boolean z = false;
            if (!isClosed()) {
                z = true;
            }
            AnonymousClass0KU.A03(z);
            int i4 = this.mSize;
            min = Math.min(Math.max(0, i4 - i), i3);
            AnonymousClass1t2.A00(i, bArr.length, i2, min, i4);
            nativeCopyToByteArray(this.mNativePtr + ((long) i), bArr, i2, min);
        } else {
            throw null;
        }
        return min;
    }
}
