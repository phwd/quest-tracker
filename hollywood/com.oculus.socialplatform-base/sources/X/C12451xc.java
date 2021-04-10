package X;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: X.1xc  reason: invalid class name and case insensitive filesystem */
public final class C12451xc<T> implements AbstractC13591zq<T> {
    public static final int A08 = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    public static final Object A09 = new Object();
    public int A00;
    public long A01;
    public AtomicReferenceArray<Object> A02;
    public AtomicReferenceArray<Object> A03;
    public final int A04;
    public final int A05;
    public final AtomicLong A06 = new AtomicLong();
    public final AtomicLong A07 = new AtomicLong();

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        if (this.A07.get() == this.A06.get()) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC13481zf
    public final boolean offer(T t) {
        long j;
        if (t != null) {
            AtomicReferenceArray<Object> atomicReferenceArray = this.A03;
            AtomicLong atomicLong = this.A07;
            long j2 = atomicLong.get();
            int i = this.A05;
            int i2 = ((int) j2) & i;
            if (j2 < this.A01) {
                atomicReferenceArray.lazySet(i2, t);
                j = j2 + 1;
            } else {
                long j3 = ((long) this.A00) + j2;
                if (atomicReferenceArray.get(((int) j3) & i) == null) {
                    this.A01 = j3 - 1;
                    atomicReferenceArray.lazySet(i2, t);
                    j = j2 + 1;
                } else {
                    long j4 = 1 + j2;
                    if (atomicReferenceArray.get(((int) j4) & i) == null) {
                        atomicReferenceArray.lazySet(i2, t);
                    } else {
                        long j5 = (long) i;
                        AtomicReferenceArray<Object> atomicReferenceArray2 = new AtomicReferenceArray<>(atomicReferenceArray.length());
                        this.A03 = atomicReferenceArray2;
                        this.A01 = (j5 + j2) - 1;
                        atomicReferenceArray2.lazySet(i2, t);
                        atomicReferenceArray.lazySet(atomicReferenceArray.length() - 1, atomicReferenceArray2);
                        atomicReferenceArray.lazySet(i2, A09);
                    }
                    atomicLong.lazySet(j4);
                    return true;
                }
            }
            atomicLong.lazySet(j);
            return true;
        }
        throw new NullPointerException("Null is not a valid element");
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003b  */
    @Override // X.AbstractC13481zf, X.AbstractC13591zq
    @io.reactivex.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final T poll() {
        /*
            r10 = this;
            java.util.concurrent.atomic.AtomicReferenceArray<java.lang.Object> r9 = r10.A02
            java.util.concurrent.atomic.AtomicLong r7 = r10.A06
            long r3 = r7.get()
            int r8 = r10.A04
            int r6 = (int) r3
            r6 = r6 & r8
            java.lang.Object r2 = r9.get(r6)
            java.lang.Object r1 = X.C12451xc.A09
            r0 = 0
            if (r2 != r1) goto L_0x0016
            r0 = 1
        L_0x0016:
            r5 = 0
            if (r2 == 0) goto L_0x0025
            if (r0 != 0) goto L_0x0028
            r9.lazySet(r6, r5)
        L_0x001e:
            r0 = 1
            long r3 = r3 + r0
            r7.lazySet(r3)
        L_0x0024:
            return r2
        L_0x0025:
            if (r0 != 0) goto L_0x0028
            return r5
        L_0x0028:
            int r1 = r8 + 1
            java.lang.Object r0 = r9.get(r1)
            java.util.concurrent.atomic.AtomicReferenceArray r0 = (java.util.concurrent.atomic.AtomicReferenceArray) r0
            r9.lazySet(r1, r5)
            r10.A02 = r0
            java.lang.Object r2 = r0.get(r6)
            if (r2 == 0) goto L_0x0024
            r0.lazySet(r6, r5)
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C12451xc.poll():java.lang.Object");
    }

    public C12451xc(int i) {
        int numberOfLeadingZeros = 1 << (32 - Integer.numberOfLeadingZeros(Math.max(8, i) - 1));
        int i2 = numberOfLeadingZeros - 1;
        AtomicReferenceArray<Object> atomicReferenceArray = new AtomicReferenceArray<>(numberOfLeadingZeros + 1);
        this.A03 = atomicReferenceArray;
        this.A05 = i2;
        this.A00 = Math.min(numberOfLeadingZeros >> 2, A08);
        this.A02 = atomicReferenceArray;
        this.A04 = i2;
        this.A01 = (long) (i2 - 1);
        this.A07.lazySet(0);
    }

    @Override // X.AbstractC13481zf
    public final void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }
}
