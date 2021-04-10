package X;

/* renamed from: X.p2  reason: case insensitive filesystem */
public final class C0927p2 implements Ib {
    public Object A00;
    public final /* synthetic */ C0935pA A01;
    public final /* synthetic */ Object A02;

    public C0927p2(C0935pA pAVar, Object obj) {
        this.A01 = pAVar;
        this.A02 = obj;
    }

    @Override // X.Ib
    public final void A5O(String str, double d) {
        C0847jr.A01((C0847jr) this.A00, str, Double.valueOf(d));
    }

    @Override // X.Ib
    public final void A5P(String str, int i) {
        this.A01.A00(this.A00, str, i);
    }

    @Override // X.Ib
    public final void A5Q(String str, long j) {
        this.A01.A01(this.A00, str, j);
    }

    @Override // X.Ib
    public final void A5R(String str, String str2) {
        C0847jr.A01((C0847jr) this.A00, str, str2);
    }

    @Override // X.Ib
    public final void A5S(String str, boolean z) {
        C0847jr.A01((C0847jr) this.A00, str, Boolean.valueOf(z));
    }

    @Override // X.Ib
    public final void A5T(String str, int[] iArr) {
        C0846jq A04 = ((C0847jr) this.A00).A04(str);
        for (int i : iArr) {
            C0846jq.A01(A04, Integer.valueOf(i));
        }
    }

    @Override // X.Ib
    public final void A5U(String str, long[] jArr) {
        C0846jq A04 = ((C0847jr) this.A00).A04(str);
        for (long j : jArr) {
            C0846jq.A01(A04, Long.valueOf(j));
        }
    }

    @Override // X.Ib
    public final void A5V(String str, String[] strArr) {
        this.A01.A05(this.A00, str, strArr);
    }

    @Override // X.Ib
    public final void A5W(String str) {
        this.A00 = ((C0847jr) this.A02).A05(str);
    }
}
