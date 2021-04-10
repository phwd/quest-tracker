package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.d3  reason: case insensitive filesystem */
public final class C0335d3 {
    public long A00 = 0;
    public long A01;
    public List<C0343dC> A02;
    public dD A03 = null;
    public boolean A04;
    public final C0112Ki A05;
    public final int A06;
    public final C0337d5 A07;
    public final C0111Kh A08;
    public final AnonymousClass99 A09 = new AnonymousClass99(this);
    public final AnonymousClass99 A0A = new AnonymousClass99(this);
    public final List<C0343dC> A0B;

    public static boolean A00(C0335d3 d3Var, dD dDVar) {
        synchronized (d3Var) {
            if (d3Var.A03 != null || (d3Var.A08.A01 && d3Var.A05.A01)) {
                return false;
            }
            d3Var.A03 = dDVar;
            d3Var.notifyAll();
            d3Var.A07.A02(d3Var.A06);
            return true;
        }
    }

    public final AbstractC0313cc A01() {
        synchronized (this) {
            if (!this.A04 && !A06()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.A05;
    }

    public final void A02() throws IOException {
        boolean z;
        boolean A072;
        synchronized (this) {
            C0111Kh kh = this.A08;
            if (!kh.A01 && kh.A00) {
                C0112Ki ki = this.A05;
                if (ki.A01 || ki.A00) {
                    z = true;
                    A072 = A07();
                }
            }
            z = false;
            A072 = A07();
        }
        if (z) {
            A05(dD.CANCEL);
        } else if (!A072) {
            this.A07.A02(this.A06);
        }
    }

    public final void A04() {
        boolean A072;
        synchronized (this) {
            this.A08.A01 = true;
            A072 = A07();
            notifyAll();
        }
        if (!A072) {
            this.A07.A02(this.A06);
        }
    }

    public final synchronized boolean A07() {
        if (this.A03 == null) {
            C0111Kh kh = this.A08;
            if (kh.A01 || kh.A00) {
                C0112Ki ki = this.A05;
                if ((ki.A01 || ki.A00) && this.A04) {
                }
            }
            return true;
        }
        return false;
    }

    public final void A03() throws IOException {
        String str;
        C0112Ki ki = this.A05;
        if (ki.A00) {
            str = "stream closed";
        } else if (!ki.A01) {
            dD dDVar = this.A03;
            if (dDVar != null) {
                throw new C0329cw(dDVar);
            }
            return;
        } else {
            str = "stream finished";
        }
        throw new IOException(str);
    }

    public final boolean A06() {
        boolean z = false;
        if ((this.A06 & 1) == 1) {
            z = true;
        }
        if (this.A07.A0H != z) {
            return false;
        }
        return true;
    }

    public C0335d3(int i, C0337d5 d5Var, boolean z, boolean z2, List<C0343dC> list) {
        this.A06 = i;
        this.A07 = d5Var;
        this.A01 = (long) d5Var.A0G.A00();
        C0111Kh kh = new C0111Kh(this, (long) d5Var.A04.A00());
        this.A08 = kh;
        C0112Ki ki = new C0112Ki(this);
        this.A05 = ki;
        kh.A01 = z2;
        ki.A01 = z;
        this.A0B = list;
    }

    public final void A05(dD dDVar) throws IOException {
        if (A00(this, dDVar)) {
            C0337d5 d5Var = this.A07;
            d5Var.A0F.A03(this.A06, dDVar);
        }
    }
}
