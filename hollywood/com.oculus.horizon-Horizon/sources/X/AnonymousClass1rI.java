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

/* renamed from: X.1rI  reason: invalid class name */
public abstract class AnonymousClass1rI<V> implements AnonymousClass1sV<V> {
    public boolean A00;
    public final AbstractC10671uj A01;
    @VisibleForTesting
    public final Set<V> A02;
    @VisibleForTesting
    public final SparseArray<C10151rM<V>> A03;
    @GuardedBy("this")
    @VisibleForTesting
    public final AnonymousClass1sO A04;
    @GuardedBy("this")
    @VisibleForTesting
    public final AnonymousClass1sO A05;
    public final C10471su A06;
    public final AbstractC10691uo A07;
    public final Class<?> A08 = getClass();

    public AnonymousClass1rI(AbstractC10671uj r11, C10471su r12, AbstractC10691uo r13) {
        if (r11 != null) {
            this.A01 = r11;
            if (r12 != null) {
                this.A06 = r12;
                if (r13 != null) {
                    this.A07 = r13;
                    this.A03 = new SparseArray<>();
                    SparseIntArray sparseIntArray = new SparseIntArray(0);
                    synchronized (this) {
                        SparseArray<C10151rM<V>> sparseArray = this.A03;
                        sparseArray.clear();
                        SparseIntArray sparseIntArray2 = this.A06.A03;
                        if (sparseIntArray2 != null) {
                            for (int i = 0; i < sparseIntArray2.size(); i++) {
                                int keyAt = sparseIntArray2.keyAt(i);
                                sparseArray.put(keyAt, new C10151rM<>(keyAt, sparseIntArray2.valueAt(i), sparseIntArray.get(keyAt, 0)));
                            }
                            this.A00 = false;
                        } else {
                            this.A00 = true;
                        }
                    }
                    this.A02 = Collections.newSetFromMap(new IdentityHashMap());
                    this.A04 = new AnonymousClass1sO();
                    this.A05 = new AnonymousClass1sO();
                    return;
                }
            }
        }
        throw null;
    }

    @VisibleForTesting
    private final synchronized C10151rM<V> A00(int i) {
        C10151rM<V> r2;
        SparseArray<C10151rM<V>> sparseArray = this.A03;
        r2 = sparseArray.get(i);
        if (r2 == null && this.A00) {
            if (!(this instanceof AnonymousClass1rf)) {
                r2 = new C10151rM<>(i, Integer.MAX_VALUE, 0);
            } else {
                r2 = new AnonymousClass1rQ<>(i, this.A06.A00);
            }
            sparseArray.put(i, r2);
        }
        return r2;
    }

    @VisibleForTesting
    private final synchronized void A01(int i) {
        int i2 = this.A05.A01;
        AnonymousClass1sO r4 = this.A04;
        int i3 = r4.A01;
        int min = Math.min((i2 + i3) - i, i3);
        if (min > 0) {
            int i4 = 0;
            while (true) {
                SparseArray<C10151rM<V>> sparseArray = this.A03;
                if (i4 >= sparseArray.size() || min <= 0) {
                    break;
                }
                C10151rM<V> valueAt = sparseArray.valueAt(i4);
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
    public synchronized V A05(C10151rM<V> r3) {
        V A002;
        A002 = r3.A00();
        if (A002 != null) {
            r3.A00++;
        }
        return A002;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c4  */
    @Override // X.AnonymousClass1sV, X.AnonymousClass1ou
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A86(V r8) {
        /*
        // Method dump skipped, instructions count: 255
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1rI.A86(java.lang.Object):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ad, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r5 = A04(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00b3, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00b4, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        r2.A00(r10);
        r3 = A00(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00bc, code lost:
        if (r3 != null) goto L_0x00be;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00be, code lost:
        r2 = r3.A00;
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00c2, code lost:
        if (r2 > 0) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00c4, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00c5, code lost:
        X.AnonymousClass0KU.A03(r0);
        r3.A00 = r2 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00cc, code lost:
        X.AnonymousClass0KY.A01(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000d, code lost:
        if (r9.A04.A01 == 0) goto L_0x000f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0042 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x00f0  */
    @Override // X.AnonymousClass1sV
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V get(int r10) {
        /*
        // Method dump skipped, instructions count: 268
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1rI.get(int):java.lang.Object");
    }

    private final int A03(V v) {
        if (this instanceof AbstractC10131rK) {
            V v2 = v;
            if (v2 != null) {
                return v2.getSize();
            }
            throw null;
        } else if (!(this instanceof C10141rL)) {
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
        if (this instanceof AbstractC10131rK) {
            V v2 = v;
            if (v2 != null) {
                v2.close();
                return;
            }
        } else if (!(this instanceof C10141rL)) {
            V v3 = v;
            if (v3 != null) {
                v3.recycle();
                return;
            }
        } else if (v != null) {
            return;
        }
        throw null;
    }

    public V A04(int i) {
        if (this instanceof C10141rL) {
            return (V) new byte[i];
        }
        if (this instanceof AnonymousClass1rH) {
            return (V) Bitmap.createBitmap(1, (int) Math.ceil(((double) i) / 2.0d), Bitmap.Config.RGB_565);
        }
        AbstractC10131rK r1 = (AbstractC10131rK) this;
        return !(r1 instanceof NativeMemoryChunkPool) ? !(r1 instanceof BufferMemoryChunkPool) ? (V) r1.A07(i) : (V) new C10011qo(i) : (V) new NativeMemoryChunk(i);
    }
}
