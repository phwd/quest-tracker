package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.0hS  reason: invalid class name and case insensitive filesystem */
public final class C04760hS {
    public long A00 = 0;
    public long A01;
    public List<C04870hr> A02;
    public EnumC04880hs A03 = null;
    public boolean A04;
    public final C01950Ov A05;
    public final int A06;
    public final C04780hU A07;
    public final C01930Os A08;
    public final AnonymousClass0HS A09 = new AnonymousClass0HS(this);
    public final AnonymousClass0HS A0A = new AnonymousClass0HS(this);
    public final List<C04870hr> A0B;

    public static boolean A00(C04760hS r2, EnumC04880hs r3) {
        synchronized (r2) {
            if (r2.A03 != null || (r2.A08.A01 && r2.A05.A01)) {
                return false;
            }
            r2.A03 = r3;
            r2.notifyAll();
            r2.A07.A02(r2.A06);
            return true;
        }
    }

    public final AnonymousClass0h1 A01() {
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
            C01930Os r1 = this.A08;
            if (!r1.A01 && r1.A00) {
                C01950Ov r12 = this.A05;
                if (r12.A01 || r12.A00) {
                    z = true;
                    A072 = A07();
                }
            }
            z = false;
            A072 = A07();
        }
        if (z) {
            A05(EnumC04880hs.CANCEL);
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
            C01930Os r1 = this.A08;
            if (r1.A01 || r1.A00) {
                C01950Ov r12 = this.A05;
                if ((r12.A01 || r12.A00) && this.A04) {
                }
            }
            return true;
        }
        return false;
    }

    public final void A03() throws IOException {
        String str;
        C01950Ov r1 = this.A05;
        if (r1.A00) {
            str = "stream closed";
        } else if (!r1.A01) {
            EnumC04880hs r12 = this.A03;
            if (r12 != null) {
                throw new C04710hL(r12);
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

    public C04760hS(int i, C04780hU r5, boolean z, boolean z2, List<C04870hr> list) {
        this.A06 = i;
        this.A07 = r5;
        this.A01 = (long) r5.A0G.A00();
        C01930Os r2 = new C01930Os(this, (long) r5.A04.A00());
        this.A08 = r2;
        C01950Ov r0 = new C01950Ov(this);
        this.A05 = r0;
        r2.A01 = z2;
        r0.A01 = z;
        this.A0B = list;
    }

    public final void A05(EnumC04880hs r3) throws IOException {
        if (A00(this, r3)) {
            C04780hU r0 = this.A07;
            r0.A0F.A03(this.A06, r3);
        }
    }
}
