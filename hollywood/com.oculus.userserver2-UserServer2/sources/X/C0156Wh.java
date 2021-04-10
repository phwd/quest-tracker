package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.Wh  reason: case insensitive filesystem */
public final class C0156Wh {
    public long A00 = 0;
    public long A01;
    public List<C0165Wq> A02;
    public EnumC0166Wr A03 = null;
    public boolean A04;
    public final E8 A05;
    public final int A06;
    public final C0158Wj A07;
    public final E7 A08;
    public final AnonymousClass8l A09 = new AnonymousClass8l(this);
    public final AnonymousClass8l A0A = new AnonymousClass8l(this);
    public final List<C0165Wq> A0B;

    public static boolean A00(C0156Wh wh, EnumC0166Wr wr) {
        synchronized (wh) {
            if (wh.A03 != null || (wh.A08.A01 && wh.A05.A01)) {
                return false;
            }
            wh.A03 = wr;
            wh.notifyAll();
            wh.A07.A02(wh.A06);
            return true;
        }
    }

    public final WG A01() {
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
            E7 e7 = this.A08;
            if (!e7.A01 && e7.A00) {
                E8 e8 = this.A05;
                if (e8.A01 || e8.A00) {
                    z = true;
                    A072 = A07();
                }
            }
            z = false;
            A072 = A07();
        }
        if (z) {
            A05(EnumC0166Wr.CANCEL);
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
            E7 e7 = this.A08;
            if (e7.A01 || e7.A00) {
                E8 e8 = this.A05;
                if ((e8.A01 || e8.A00) && this.A04) {
                }
            }
            return true;
        }
        return false;
    }

    public final void A03() throws IOException {
        String str;
        E8 e8 = this.A05;
        if (e8.A00) {
            str = "stream closed";
        } else if (!e8.A01) {
            EnumC0166Wr wr = this.A03;
            if (wr != null) {
                throw new Wa(wr);
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

    public C0156Wh(int i, C0158Wj wj, boolean z, boolean z2, List<C0165Wq> list) {
        this.A06 = i;
        this.A07 = wj;
        this.A01 = (long) wj.A0G.A00();
        E7 e7 = new E7(this, (long) wj.A04.A00());
        this.A08 = e7;
        E8 e8 = new E8(this);
        this.A05 = e8;
        e7.A01 = z2;
        e8.A01 = z;
        this.A0B = list;
    }

    public final void A05(EnumC0166Wr wr) throws IOException {
        if (A00(this, wr)) {
            C0158Wj wj = this.A07;
            wj.A0E.A03(this.A06, wr);
        }
    }
}
