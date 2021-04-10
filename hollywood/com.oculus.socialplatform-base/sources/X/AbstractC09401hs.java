package X;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.annotation.VisibleForTesting;
import com.facebook.imagepipeline.memory.BufferMemoryChunkPool;
import com.facebook.imagepipeline.memory.NativeMemoryChunk;
import com.facebook.imagepipeline.memory.NativeMemoryChunkPool;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.1hs  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09401hs<V> implements AbstractC04990rf<V> {
    public boolean A00;
    public final AnonymousClass0JS A01;
    @VisibleForTesting
    public final Set<V> A02;
    @VisibleForTesting
    public final SparseArray<C09391hr<V>> A03;
    @GuardedBy("this")
    @VisibleForTesting
    public final AnonymousClass1hw A04;
    @GuardedBy("this")
    @VisibleForTesting
    public final AnonymousClass1hw A05;
    public final AnonymousClass1i0 A06;
    public final AnonymousClass1i3 A07;
    public final Class<?> A08 = getClass();

    public AbstractC09401hs(AnonymousClass0JS r11, AnonymousClass1i0 r12, AnonymousClass1i3 r13) {
        if (r11 != null) {
            this.A01 = r11;
            if (r12 != null) {
                this.A06 = r12;
                if (r13 != null) {
                    this.A07 = r13;
                    this.A03 = new SparseArray<>();
                    SparseIntArray sparseIntArray = new SparseIntArray(0);
                    synchronized (this) {
                        SparseArray<C09391hr<V>> sparseArray = this.A03;
                        sparseArray.clear();
                        SparseIntArray sparseIntArray2 = this.A06.A03;
                        if (sparseIntArray2 != null) {
                            for (int i = 0; i < sparseIntArray2.size(); i++) {
                                int keyAt = sparseIntArray2.keyAt(i);
                                sparseArray.put(keyAt, new C09391hr<>(keyAt, sparseIntArray2.valueAt(i), sparseIntArray.get(keyAt, 0)));
                            }
                            this.A00 = false;
                        } else {
                            this.A00 = true;
                        }
                    }
                    this.A02 = Collections.newSetFromMap(new IdentityHashMap());
                    this.A04 = new AnonymousClass1hw();
                    this.A05 = new AnonymousClass1hw();
                    return;
                }
                throw null;
            }
            throw null;
        }
        throw null;
    }

    @VisibleForTesting
    private final synchronized C09391hr<V> A00(int i) {
        C09391hr<V> r2;
        SparseArray<C09391hr<V>> sparseArray = this.A03;
        r2 = sparseArray.get(i);
        if (r2 == null && this.A00) {
            if (!(this instanceof C09441hy)) {
                r2 = new C09391hr<>(i, Integer.MAX_VALUE, 0);
            } else {
                r2 = new C09381hq<>(i, this.A06.A00);
            }
            sparseArray.put(i, r2);
        }
        return r2;
    }

    @VisibleForTesting
    private final synchronized void A01(int i) {
        int i2 = this.A05.A01;
        AnonymousClass1hw r4 = this.A04;
        int i3 = r4.A01;
        int min = Math.min((i2 + i3) - i, i3);
        if (min > 0) {
            int i4 = 0;
            while (true) {
                SparseArray<C09391hr<V>> sparseArray = this.A03;
                if (i4 >= sparseArray.size() || min <= 0) {
                    break;
                }
                C09391hr<V> valueAt = sparseArray.valueAt(i4);
                while (true) {
                    V A002 = valueAt.A00();
                    if (A002 == null) {
                        break;
                    }
                    A06(A002);
                    min -= valueAt.A01;
                    r4.A00(valueAt.A01);
                    if (min <= 0) {
                        break;
                    }
                }
                i4++;
            }
        }
    }

    @VisibleForTesting
    private final synchronized boolean A02() {
        boolean z;
        z = false;
        if (this.A05.A01 + this.A04.A01 > this.A06.A02) {
            z = true;
        }
        return z;
    }

    @Nullable
    public synchronized V A05(C09391hr<V> r3) {
        V A002;
        A002 = r3.A00();
        if (A002 != null) {
            r3.A00++;
        }
        return A002;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00be  */
    @Override // X.AbstractC04990rf, X.AbstractC00840Jw
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A8y(V r8) {
        /*
        // Method dump skipped, instructions count: 251
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC09401hs.A8y(java.lang.Object):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b6, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        r5 = A04(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00bc, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00bd, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r2.A00(r10);
        r3 = A00(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00c5, code lost:
        if (r3 != null) goto L_0x00c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00c7, code lost:
        r2 = r3.A00;
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00cb, code lost:
        if (r2 > 0) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00cd, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x00ce, code lost:
        X.C00740Ii.A03(r0);
        r3.A00 = r2 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00d5, code lost:
        X.C00770Im.A01(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        if (r9.A04.A01 == 0) goto L_0x000f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x004c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x00f7  */
    @Override // X.AbstractC04990rf
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V get(int r10) {
        /*
        // Method dump skipped, instructions count: 277
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC09401hs.get(int):java.lang.Object");
    }

    private final int A03(V v) {
        if (this instanceof AbstractC09461i1) {
            V v2 = v;
            if (v2 != null) {
                return v2.getSize();
            }
            throw null;
        } else if (!(this instanceof C09451hz)) {
            V v3 = v;
            if (v3 != null) {
                return v3.getAllocationByteCount();
            }
            throw null;
        } else {
            byte[] bArr = (byte[]) v;
            if (bArr != null) {
                return bArr.length;
            }
            throw null;
        }
    }

    @VisibleForTesting
    private final void A06(V v) {
        if (this instanceof AbstractC09461i1) {
            V v2 = v;
            if (v2 != null) {
                v2.close();
                return;
            }
            throw null;
        } else if (!(this instanceof C09451hz)) {
            V v3 = v;
            if (v3 != null) {
                v3.recycle();
                return;
            }
            throw null;
        } else if (v == null) {
            throw null;
        }
    }

    public V A04(int i) {
        if (this instanceof C09451hz) {
            return (V) new byte[i];
        }
        if (this instanceof C09411ht) {
            return (V) Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Bitmap.Config.RGB_565);
        }
        AbstractC09461i1 r1 = (AbstractC09461i1) this;
        return !(r1 instanceof NativeMemoryChunkPool) ? !(r1 instanceof BufferMemoryChunkPool) ? (V) r1.A07(i) : (V) new AnonymousClass1ib(i) : (V) new NativeMemoryChunk(i);
    }
}
