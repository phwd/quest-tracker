package X;

/* renamed from: X.p8  reason: case insensitive filesystem */
public final class C0933p8 implements Id {
    public boolean A00 = true;
    public final /* synthetic */ C0934p9 A01;

    public C0933p8(C0934p9 p9Var) {
        this.A01 = p9Var;
    }

    @Override // X.Id
    public final void A5X(String str, String str2, int i) {
        if (this.A00) {
            this.A00 = false;
        } else {
            this.A01.A02.append(',');
        }
        StringBuilder sb = this.A01.A02;
        sb.append('\"');
        sb.append(YE.A02(str));
        sb.append("\":\"");
        sb.append(YE.A02(str2));
        sb.append('\"');
    }
}
