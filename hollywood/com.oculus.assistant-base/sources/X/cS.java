package X;

import java.io.IOException;
import java.util.List;

public final class cS {
    public long A00 = 0;
    public long A01;
    public List A02;
    public cI A03 = null;
    public boolean A04;
    public final C1121tJ A05;
    public final int A06;
    public final C0584cQ A07;
    public final C1120tI A08;
    public final AnonymousClass34 A09 = new AnonymousClass34(this);
    public final AnonymousClass34 A0A = new AnonymousClass34(this);
    public final List A0B;

    public static boolean A00(cS cSVar, cI cIVar) {
        synchronized (cSVar) {
            if (cSVar.A03 != null || (cSVar.A08.A01 && cSVar.A05.A01)) {
                return false;
            }
            cSVar.A03 = cIVar;
            cSVar.notifyAll();
            cSVar.A07.A02(cSVar.A06);
            return true;
        }
    }

    public final AbstractC0608cr A01() {
        synchronized (this) {
            if (!this.A04 && !A06()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.A05;
    }

    public final void A02() {
        boolean z;
        boolean A072;
        synchronized (this) {
            C1120tI tIVar = this.A08;
            if (!tIVar.A01 && tIVar.A00) {
                C1121tJ tJVar = this.A05;
                if (tJVar.A01 || tJVar.A00) {
                    z = true;
                    A072 = A07();
                }
            }
            z = false;
            A072 = A07();
        }
        if (z) {
            A05(cI.CANCEL);
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
            C1120tI tIVar = this.A08;
            if (tIVar.A01 || tIVar.A00) {
                C1121tJ tJVar = this.A05;
                if ((tJVar.A01 || tJVar.A00) && this.A04) {
                }
            }
            return true;
        }
        return false;
    }

    public final void A03() {
        C1121tJ tJVar = this.A05;
        if (tJVar.A00) {
            throw new IOException("stream closed");
        } else if (!tJVar.A01) {
            cI cIVar = this.A03;
            if (cIVar != null) {
                throw new C0591cZ(cIVar);
            }
        } else {
            throw new IOException("stream finished");
        }
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

    public cS(int i, C0584cQ cQVar, boolean z, boolean z2, List list) {
        this.A06 = i;
        this.A07 = cQVar;
        this.A01 = (long) cQVar.A0G.A00();
        C1120tI tIVar = new C1120tI(this, (long) cQVar.A04.A00());
        this.A08 = tIVar;
        C1121tJ tJVar = new C1121tJ(this);
        this.A05 = tJVar;
        tIVar.A01 = z2;
        tJVar.A01 = z;
        this.A0B = list;
    }

    public final void A05(cI cIVar) {
        if (A00(this, cIVar)) {
            C0584cQ cQVar = this.A07;
            cQVar.A0E.A03(this.A06, cIVar);
        }
    }
}
