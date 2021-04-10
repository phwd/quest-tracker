package X;

/* renamed from: X.dj  reason: case insensitive filesystem */
public final class C0362dj {
    public final Object A00;
    public final String A01;
    public final C0369ds A02;
    public final C0367dp A03;
    public final AbstractC0361di A04;
    public volatile e7 A05;

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

    public C0362dj(C0363dk dkVar) {
        this.A03 = dkVar.A04;
        this.A01 = dkVar.A00;
        this.A02 = new C0369ds(dkVar.A03);
        this.A04 = dkVar.A01;
        Object obj = dkVar.A02;
        this.A00 = obj != null ? obj : this;
    }
}
