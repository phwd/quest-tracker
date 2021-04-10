package X;

public final class XN {
    public final Object A00;
    public final String A01;
    public final XW A02;
    public final XT A03;
    public final XM A04;
    public volatile C0186Xl A05;

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

    public XN(XO xo) {
        this.A03 = xo.A04;
        this.A01 = xo.A01;
        this.A02 = new XW(xo.A03);
        this.A04 = xo.A02;
        Object obj = xo.A00;
        this.A00 = obj != null ? obj : this;
    }
}
