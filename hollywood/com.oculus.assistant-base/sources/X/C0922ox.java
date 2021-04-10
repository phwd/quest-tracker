package X;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.ox  reason: case insensitive filesystem */
public final class C0922ox implements Ib {
    public Map A00;
    public final /* synthetic */ Ic A01;
    public final /* synthetic */ Map A02;

    public C0922ox(Ic ic, Map map) {
        this.A01 = ic;
        this.A02 = map;
    }

    @Override // X.Ib
    public final void A5O(String str, double d) {
        this.A00.put(str, String.valueOf(d));
    }

    @Override // X.Ib
    public final void A5P(String str, int i) {
        this.A00.put(str, String.valueOf(i));
    }

    @Override // X.Ib
    public final void A5Q(String str, long j) {
        this.A00.put(str, String.valueOf(j));
    }

    @Override // X.Ib
    public final void A5R(String str, String str2) {
        this.A00.put(str, str2);
    }

    @Override // X.Ib
    public final void A5S(String str, boolean z) {
        this.A00.put(str, String.valueOf(z));
    }

    @Override // X.Ib
    public final void A5T(String str, int[] iArr) {
        this.A00.put(str, Arrays.toString(iArr));
    }

    @Override // X.Ib
    public final void A5U(String str, long[] jArr) {
        this.A00.put(str, Arrays.toString(jArr));
    }

    @Override // X.Ib
    public final void A5V(String str, String[] strArr) {
        this.A00.put(str, Arrays.toString(strArr));
    }

    @Override // X.Ib
    public final void A5W(String str) {
        HashMap hashMap = new HashMap();
        this.A00 = hashMap;
        this.A02.put(str, hashMap);
    }
}
