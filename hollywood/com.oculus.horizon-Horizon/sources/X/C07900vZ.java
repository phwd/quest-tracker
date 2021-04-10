package X;

import java.io.IOException;
import java.util.List;

/* renamed from: X.0vZ  reason: invalid class name and case insensitive filesystem */
public final class C07900vZ {
    public long A00 = 0;
    public long A01;
    public List<C07990vi> A02;
    public EnumC08000vj A03 = null;
    public boolean A04;
    public final AnonymousClass0MC A05;
    public final int A06;
    public final C07920vb A07;
    public final AnonymousClass0MB A08;
    public final AnonymousClass0BA A09 = new AnonymousClass0BA(this);
    public final AnonymousClass0BA A0A = new AnonymousClass0BA(this);
    public final List<C07990vi> A0B;

    public static boolean A00(C07900vZ r2, EnumC08000vj r3) {
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

    public final AbstractC07640v7 A01() {
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
            AnonymousClass0MB r1 = this.A08;
            if (!r1.A01 && r1.A00) {
                AnonymousClass0MC r12 = this.A05;
                if (r12.A01 || r12.A00) {
                    z = true;
                    A072 = A07();
                }
            }
            z = false;
            A072 = A07();
        }
        if (z) {
            A05(EnumC08000vj.CANCEL);
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
            AnonymousClass0MB r1 = this.A08;
            if (r1.A01 || r1.A00) {
                AnonymousClass0MC r12 = this.A05;
                if ((r12.A01 || r12.A00) && this.A04) {
                }
            }
            return true;
        }
        return false;
    }

    public final void A03() throws IOException {
        String str;
        AnonymousClass0MC r1 = this.A05;
        if (r1.A00) {
            str = "stream closed";
        } else if (!r1.A01) {
            EnumC08000vj r12 = this.A03;
            if (r12 != null) {
                throw new C07840vS(r12);
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

    public C07900vZ(int i, C07920vb r5, boolean z, boolean z2, List<C07990vi> list) {
        this.A06 = i;
        this.A07 = r5;
        this.A01 = (long) r5.A0G.A00();
        AnonymousClass0MB r2 = new AnonymousClass0MB(this, (long) r5.A04.A00());
        this.A08 = r2;
        AnonymousClass0MC r0 = new AnonymousClass0MC(this);
        this.A05 = r0;
        r2.A01 = z2;
        r0.A01 = z;
        this.A0B = list;
    }

    public final void A05(EnumC08000vj r3) throws IOException {
        if (A00(this, r3)) {
            C07920vb r0 = this.A07;
            r0.A0F.A03(this.A06, r3);
        }
    }
}
