package X;

import android.util.Log;
import java.io.Closeable;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

/* renamed from: X.1qo  reason: invalid class name and case insensitive filesystem */
public final class C10011qo implements AbstractC10321rv, Closeable {
    public ByteBuffer A00;
    public final int A01;
    public final long A02 = ((long) System.identityHashCode(this));

    @Override // X.AbstractC10321rv, java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() {
        this.A00 = null;
    }

    @Override // X.AbstractC10321rv
    @Nullable
    public final synchronized ByteBuffer getByteBuffer() {
        return this.A00;
    }

    @Override // X.AbstractC10321rv
    public final synchronized boolean isClosed() {
        boolean z;
        z = false;
        if (this.A00 == null) {
            z = true;
        }
        return z;
    }

    @Override // X.AbstractC10321rv
    public final synchronized int write(int i, byte[] bArr, int i2, int i3) {
        int min;
        if (bArr != null) {
            boolean z = false;
            if (!isClosed()) {
                z = true;
            }
            AnonymousClass0KU.A03(z);
            int i4 = this.A01;
            min = Math.min(Math.max(0, i4 - i), i3);
            AnonymousClass1t2.A00(i, bArr.length, i2, min, i4);
            this.A00.position(i);
            this.A00.put(bArr, i2, min);
        } else {
            throw null;
        }
        return min;
    }

    private void A00(int i, AbstractC10321rv r5, int i2, int i3) {
        if (r5 instanceof C10011qo) {
            AnonymousClass0KU.A03(!isClosed());
            AnonymousClass0KU.A03(!r5.isClosed());
            AnonymousClass1t2.A00(i, r5.getSize(), i2, i3, this.A01);
            this.A00.position(i);
            r5.getByteBuffer().position(i2);
            byte[] bArr = new byte[i3];
            this.A00.get(bArr, 0, i3);
            r5.getByteBuffer().put(bArr, 0, i3);
            return;
        }
        throw new IllegalArgumentException("Cannot copy two incompatible MemoryChunks");
    }

    @Override // X.AbstractC10321rv
    public final void copy(int i, AbstractC10321rv r11, int i2, int i3) {
        if (r11 != null) {
            long uniqueId = r11.getUniqueId();
            long uniqueId2 = getUniqueId();
            if (uniqueId == uniqueId2) {
                Log.w("BufferMemoryChunk", AnonymousClass006.A09("Copying from BufferMemoryChunk ", Long.toHexString(uniqueId2), " to BufferMemoryChunk ", Long.toHexString(uniqueId), " which are the same "));
                AnonymousClass0KU.A01(false);
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

    @Override // X.AbstractC10321rv
    public final long getNativePtr() {
        throw new UnsupportedOperationException("Cannot get the pointer of a BufferMemoryChunk");
    }

    public C10011qo(int i) {
        this.A00 = ByteBuffer.allocateDirect(i);
        this.A01 = i;
    }

    @Override // X.AbstractC10321rv
    public final int getSize() {
        return this.A01;
    }

    @Override // X.AbstractC10321rv
    public final long getUniqueId() {
        return this.A02;
    }

    @Override // X.AbstractC10321rv
    public final synchronized byte read(int i) {
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
        if (i >= this.A01) {
            z = false;
        }
        AnonymousClass0KU.A01(Boolean.valueOf(z));
        return this.A00.get(i);
    }

    @Override // X.AbstractC10321rv
    public final synchronized int read(int i, byte[] bArr, int i2, int i3) {
        int min;
        if (bArr != null) {
            boolean z = false;
            if (!isClosed()) {
                z = true;
            }
            AnonymousClass0KU.A03(z);
            int i4 = this.A01;
            min = Math.min(Math.max(0, i4 - i), i3);
            AnonymousClass1t2.A00(i, bArr.length, i2, min, i4);
            this.A00.position(i);
            this.A00.get(bArr, i2, min);
        } else {
            throw null;
        }
        return min;
    }
}
