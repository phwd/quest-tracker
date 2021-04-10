package X;

import java.util.Arrays;

/* renamed from: X.0FU  reason: invalid class name */
public class AnonymousClass0FU {
    public boolean A00;
    public boolean A01;
    public final int[] A02;
    public final long[] A03;
    public final boolean[] A04;

    public AnonymousClass0FU(int i) {
        long[] jArr = new long[i];
        this.A03 = jArr;
        this.A04 = new boolean[i];
        this.A02 = new int[i];
        Arrays.fill(jArr, 0L);
        Arrays.fill(this.A04, false);
    }
}
