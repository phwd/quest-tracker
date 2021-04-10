package X;

import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0cF  reason: invalid class name and case insensitive filesystem */
public final class C03410cF implements AnonymousClass0GW, AnonymousClass0GX {
    @VisibleForTesting
    public static final TreeMap<Integer, C03410cF> A08 = new TreeMap<>();
    @VisibleForTesting
    public int A00;
    @VisibleForTesting
    public final int A01;
    @VisibleForTesting
    public final double[] A02;
    @VisibleForTesting
    public final long[] A03;
    @VisibleForTesting
    public final String[] A04;
    @VisibleForTesting
    public final byte[][] A05;
    public final int[] A06;
    public volatile String A07;

    @Override // X.AnonymousClass0GX
    public final void A1X(AnonymousClass0GW r5) {
        for (int i = 1; i <= this.A00; i++) {
            int i2 = this.A06[i];
            if (i2 == 1) {
                r5.A1U(i);
            } else if (i2 == 2) {
                r5.A1Q(i, this.A03[i]);
            } else if (i2 == 3) {
                r5.A1P(i, this.A02[i]);
            } else if (i2 == 4) {
                r5.A1W(i, this.A04[i]);
            } else if (i2 == 5) {
                r5.A1L(i, this.A05[i]);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    public static C03410cF A00(String str, int i) {
        TreeMap<Integer, C03410cF> treeMap = A08;
        synchronized (treeMap) {
            Map.Entry<Integer, C03410cF> ceilingEntry = treeMap.ceilingEntry(Integer.valueOf(i));
            if (ceilingEntry != null) {
                treeMap.remove(ceilingEntry.getKey());
                C03410cF value = ceilingEntry.getValue();
                value.A07 = str;
                value.A00 = i;
                return value;
            }
            C03410cF r0 = new C03410cF(i);
            r0.A07 = str;
            r0.A00 = i;
            return r0;
        }
    }

    public final void A01() {
        TreeMap<Integer, C03410cF> treeMap = A08;
        synchronized (treeMap) {
            treeMap.put(Integer.valueOf(this.A01), this);
            if (treeMap.size() > 15) {
                int size = treeMap.size() - 10;
                Iterator<Integer> it = treeMap.descendingKeySet().iterator();
                while (true) {
                    int i = size - 1;
                    if (size <= 0) {
                        break;
                    }
                    it.next();
                    it.remove();
                    size = i;
                }
            }
        }
    }

    @Override // X.AnonymousClass0GW
    public final void A1L(int i, byte[] bArr) {
        this.A06[i] = 5;
        this.A05[i] = bArr;
    }

    @Override // X.AnonymousClass0GW
    public final void A1P(int i, double d) {
        this.A06[i] = 3;
        this.A02[i] = d;
    }

    @Override // X.AnonymousClass0GW
    public final void A1Q(int i, long j) {
        this.A06[i] = 2;
        this.A03[i] = j;
    }

    @Override // X.AnonymousClass0GW
    public final void A1U(int i) {
        this.A06[i] = 1;
    }

    @Override // X.AnonymousClass0GW
    public final void A1W(int i, String str) {
        this.A06[i] = 4;
        this.A04[i] = str;
    }

    @Override // X.AnonymousClass0GX
    public final String A4W() {
        return this.A07;
    }

    public C03410cF(int i) {
        this.A01 = i;
        int i2 = i + 1;
        this.A06 = new int[i2];
        this.A03 = new long[i2];
        this.A02 = new double[i2];
        this.A04 = new String[i2];
        this.A05 = new byte[i2][];
    }
}
