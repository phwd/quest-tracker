package X;

import java.security.cert.Certificate;
import java.util.List;

public final class XY {
    public final List<Certificate> A00;
    public final C0182Xh A01;
    public final List<Certificate> A02;
    public final XH A03;

    public final boolean equals(Object obj) {
        if (!(obj instanceof XY)) {
            return false;
        }
        XY xy = (XY) obj;
        C0182Xh xh = this.A01;
        C0182Xh xh2 = xy.A01;
        if (!XD.A09(xh, xh2) || !xh.equals(xh2) || !this.A00.equals(xy.A00) || !this.A02.equals(xy.A02)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i;
        XH xh = this.A03;
        if (xh != null) {
            i = xh.hashCode();
        } else {
            i = 0;
        }
        return ((((((527 + i) * 31) + this.A01.hashCode()) * 31) + this.A00.hashCode()) * 31) + this.A02.hashCode();
    }

    public XY(XH xh, C0182Xh xh2, List<Certificate> list, List<Certificate> list2) {
        this.A03 = xh;
        this.A01 = xh2;
        this.A00 = list;
        this.A02 = list2;
    }
}
