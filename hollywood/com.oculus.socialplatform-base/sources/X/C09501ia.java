package X;

import android.annotation.TargetApi;
import android.os.SharedMemory;
import android.system.ErrnoException;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import java.io.Closeable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

@TargetApi(27)
/* renamed from: X.1ia  reason: invalid class name and case insensitive filesystem */
public final class C09501ia implements Closeable, AnonymousClass1iY {
    @Nullable
    public SharedMemory A00;
    @Nullable
    public ByteBuffer A01;
    public final long A02;

    @Override // X.AnonymousClass1iY, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        if (!isClosed()) {
            SharedMemory.unmap(this.A01);
            this.A00.close();
            this.A01 = null;
            this.A00 = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0008, code lost:
        if (r2.A00 == null) goto L_0x000a;
     */
    @Override // X.AnonymousClass1iY
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean isClosed() {
        /*
            r2 = this;
            monitor-enter(r2)
            java.nio.ByteBuffer r0 = r2.A01     // Catch:{ all -> 0x000d }
            if (r0 == 0) goto L_0x000a
            android.os.SharedMemory r1 = r2.A00     // Catch:{ all -> 0x000d }
            r0 = 0
            if (r1 != 0) goto L_0x000b
        L_0x000a:
            r0 = 1
        L_0x000b:
            monitor-exit(r2)
            return r0
        L_0x000d:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09501ia.isClosed():boolean");
    }

    @Override // X.AnonymousClass1iY
    public final synchronized int write(int i, byte[] bArr, int i2, int i3) {
        int min;
        if (bArr != null) {
            boolean z = false;
            if (!isClosed()) {
                z = true;
            }
            C00740Ii.A03(z);
            min = Math.min(Math.max(0, getSize() - i), i3);
            AnonymousClass1ig.A00(i, bArr.length, i2, min, getSize());
            this.A01.position(i);
            this.A01.put(bArr, i2, min);
        } else {
            throw null;
        }
        return min;
    }

    private void A00(int i, AnonymousClass1iY r5, int i2, int i3) {
        if (r5 instanceof C09501ia) {
            C00740Ii.A03(!isClosed());
            C00740Ii.A03(!r5.isClosed());
            AnonymousClass1ig.A00(i, r5.getSize(), i2, i3, getSize());
            this.A01.position(i);
            r5.getByteBuffer().position(i2);
            byte[] bArr = new byte[i3];
            this.A01.get(bArr, 0, i3);
            r5.getByteBuffer().put(bArr, 0, i3);
            return;
        }
        throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
    }

    @Override // X.AnonymousClass1iY
    public final void copy(int i, AnonymousClass1iY r11, int i2, int i3) {
        if (r11 != null) {
            long uniqueId = r11.getUniqueId();
            long uniqueId2 = getUniqueId();
            if (uniqueId == uniqueId2) {
                Log.w("AshmemMemoryChunk", AnonymousClass006.A0C("Copying from AshmemMemoryChunk ", Long.toHexString(uniqueId2), " to AshmemMemoryChunk ", Long.toHexString(uniqueId), " which are the same "));
                C00740Ii.A01(false);
            }
            if (r11.getUniqueId() < uniqueId2) {
                synchronized (r11) {
                    synchronized (this) {
                        A00(i, r11, i2, i3);
                    }
                }
                return;
            }
            synchronized (this) {
                synchronized (r11) {
                    A00(i, r11, i2, i3);
                }
            }
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass1iY
    @Nullable
    public final ByteBuffer getByteBuffer() {
        return this.A01;
    }

    @Override // X.AnonymousClass1iY
    public final long getNativePtr() {
        throw new UnsupportedOperationException("Cannot get the pointer of an  AshmemMemoryChunk");
    }

    @Override // X.AnonymousClass1iY
    public final long getUniqueId() {
        return this.A02;
    }

    @Override // X.AnonymousClass1iY
    public final int getSize() {
        C00740Ii.A03(!isClosed());
        return this.A00.getSize();
    }

    @VisibleForTesting
    public C09501ia() {
        this.A00 = null;
        this.A01 = null;
        this.A02 = (long) System.identityHashCode(this);
    }

    public C09501ia(int i) {
        C00740Ii.A01(Boolean.valueOf(i > 0));
        try {
            SharedMemory create = SharedMemory.create("AshmemMemoryChunk", i);
            this.A00 = create;
            this.A01 = create.mapReadWrite();
            this.A02 = (long) System.identityHashCode(this);
        } catch (ErrnoException e) {
            throw new RuntimeException("Fail to create AshmemMemory", e);
        }
    }

    @Override // X.AnonymousClass1iY
    public final synchronized byte read(int i) {
        boolean z = true;
        boolean z2 = false;
        if (!isClosed()) {
            z2 = true;
        }
        C00740Ii.A03(z2);
        boolean z3 = false;
        if (i >= 0) {
            z3 = true;
        }
        C00740Ii.A01(Boolean.valueOf(z3));
        if (i >= getSize()) {
            z = false;
        }
        C00740Ii.A01(Boolean.valueOf(z));
        return this.A01.get(i);
    }

    @Override // X.AnonymousClass1iY
    public final synchronized int read(int i, byte[] bArr, int i2, int i3) {
        int min;
        if (bArr != null) {
            boolean z = false;
            if (!isClosed()) {
                z = true;
            }
            C00740Ii.A03(z);
            min = Math.min(Math.max(0, getSize() - i), i3);
            AnonymousClass1ig.A00(i, bArr.length, i2, min, getSize());
            this.A01.position(i);
            this.A01.get(bArr, i2, min);
        } else {
            throw null;
        }
        return min;
    }
}
