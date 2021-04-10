package X;

import java.util.concurrent.TimeUnit;

public final class IW {
    public int A00;
    public int A01;
    public int[] A02 = new int[5];
    public long[] A03 = new long[5];
    public Ie[] A04 = new Ie[5];
    public Ix[] A05 = new Ix[5];
    public String[] A06 = new String[5];

    public final void A00(IV iv) {
        for (int i = 0; i < this.A01; i++) {
            iv.A5N(TimeUnit.NANOSECONDS.toMillis(this.A03[i]), this.A03[i], this.A02[i], this.A06[i], this.A04[i], this.A05[i]);
        }
    }
}
