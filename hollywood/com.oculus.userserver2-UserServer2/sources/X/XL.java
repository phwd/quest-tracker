package X;

public class XL {
    public int A00;
    public long A01;
    public long A02;
    public String A03;
    public XY A04;
    public XX A05;
    public XP A06;
    public XN A07;
    public XK A08;
    public XK A09;
    public XK A0A;
    public XJ A0B;

    public final XK A00() {
        String str;
        if (this.A07 == null) {
            str = "request == null";
        } else if (this.A06 != null) {
            int i = this.A00;
            if (i >= 0) {
                return new XK(this);
            }
            str = AnonymousClass06.A01("code < 0: ", i);
        } else {
            str = "protocol == null";
        }
        throw new IllegalStateException(str);
    }

    public XL() {
        this.A00 = -1;
        this.A05 = new XX();
    }

    public XL(XK xk) {
        this.A00 = -1;
        this.A07 = xk.A07;
        this.A06 = xk.A00;
        this.A00 = xk.A01;
        this.A03 = xk.A04;
        this.A04 = xk.A05;
        this.A05 = xk.A06.A01();
        this.A0B = xk.A0B;
        this.A09 = xk.A09;
        this.A08 = xk.A08;
        this.A0A = xk.A0A;
        this.A02 = xk.A03;
        this.A01 = xk.A02;
    }
}
