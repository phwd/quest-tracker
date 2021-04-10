package X;

/* renamed from: X.bo  reason: case insensitive filesystem */
public final class C0551bo {
    public final Object A00;
    public final String A01;
    public final C0542bf A02;
    public final C0544bh A03;
    public final AbstractC0552bp A04;
    public volatile C0527bQ A05;

    public final String toString() {
        StringBuilder sb = new StringBuilder("Request{method=");
        sb.append(this.A01);
        sb.append(", url=");
        sb.append(this.A03);
        sb.append(", tag=");
        Object obj = this.A00;
        if (obj == this) {
            obj = null;
        }
        sb.append(obj);
        sb.append('}');
        return sb.toString();
    }

    public C0551bo(C0550bn bnVar) {
        this.A03 = bnVar.A04;
        this.A01 = bnVar.A01;
        this.A02 = new C0542bf(bnVar.A03);
        this.A04 = bnVar.A02;
        Object obj = bnVar.A00;
        this.A00 = obj != null ? obj : this;
    }
}
