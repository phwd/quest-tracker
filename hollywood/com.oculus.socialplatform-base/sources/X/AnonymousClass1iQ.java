package X;

import android.util.SparseIntArray;
import javax.annotation.concurrent.Immutable;

@Immutable
/* renamed from: X.1iQ  reason: invalid class name */
public final class AnonymousClass1iQ {
    public final AnonymousClass0JS A00;
    public final AnonymousClass1i0 A01 = AnonymousClass1iO.A00();
    public final AnonymousClass1i0 A02;
    public final AnonymousClass1i0 A03;
    public final AnonymousClass1i0 A04;
    public final AnonymousClass1i3 A05 = C09531il.A00();
    public final AnonymousClass1i3 A06;
    public final AnonymousClass1i3 A07;

    public AnonymousClass1iQ() {
        int i;
        int i2;
        C01060Pq.A00();
        int i3 = AnonymousClass1e7.A00;
        int i4 = i3 * 4194304;
        int i5 = 131072;
        SparseIntArray sparseIntArray = new SparseIntArray();
        do {
            sparseIntArray.put(i5, i3);
            i5 <<= 1;
        } while (i5 <= 4194304);
        this.A02 = new AnonymousClass1i0(4194304, i4, sparseIntArray, i3);
        this.A00 = C05010rh.A00();
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(1024, 5);
        sparseIntArray2.put(2048, 5);
        sparseIntArray2.put(4096, 5);
        sparseIntArray2.put(8192, 5);
        sparseIntArray2.put(16384, 5);
        sparseIntArray2.put(32768, 5);
        sparseIntArray2.put(65536, 5);
        sparseIntArray2.put(131072, 5);
        sparseIntArray2.put(262144, 2);
        sparseIntArray2.put(524288, 2);
        sparseIntArray2.put(1048576, 2);
        int min = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min < 16777216) {
            i = 3145728;
        } else {
            i = 12582912;
            if (min < 33554432) {
                i = 6291456;
            }
        }
        int min2 = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (min2 < 16777216) {
            i2 = min2 >> 1;
        } else {
            i2 = (min2 >> 2) * 3;
        }
        this.A03 = new AnonymousClass1i0(i, i2, sparseIntArray2, -1);
        this.A06 = C09531il.A00();
        SparseIntArray sparseIntArray3 = new SparseIntArray();
        sparseIntArray3.put(16384, 5);
        this.A04 = new AnonymousClass1i0(81920, 1048576, sparseIntArray3, -1);
        this.A07 = C09531il.A00();
        C01060Pq.A00();
    }
}
