package X;

/* renamed from: X.0ke  reason: invalid class name and case insensitive filesystem */
public final class C05700ke {
    public final Object A00;
    public final String A01;
    public final C06020lp A02;
    public final C05890la A03;
    public final AbstractC05690kc A04;
    public volatile C06770np A05;

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

    public C05700ke(C05710kf r3) {
        this.A03 = r3.A04;
        this.A01 = r3.A01;
        this.A02 = new C06020lp(r3.A03);
        this.A04 = r3.A02;
        Object obj = r3.A00;
        this.A00 = obj != null ? obj : this;
    }
}
