package X;

import android.view.View;

/* renamed from: X.1rw  reason: invalid class name and case insensitive filesystem */
public class C11411rw extends C05430vh {
    public int A00 = 0;
    public boolean A01 = false;
    public final /* synthetic */ AnonymousClass1rT A02;

    public C11411rw(AnonymousClass1rT r2) {
        this.A02 = r2;
    }

    @Override // X.C05430vh, X.AbstractC003107k
    public final void A6i(View view) {
        int i = this.A00 + 1;
        this.A00 = i;
        AnonymousClass1rT r2 = this.A02;
        if (i == r2.A04.size()) {
            AbstractC003107k r1 = r2.A02;
            if (r1 != null) {
                r1.A6i(null);
            }
            this.A00 = 0;
            this.A01 = false;
            r2.A03 = false;
        }
    }

    @Override // X.C05430vh, X.AbstractC003107k
    public final void A6k(View view) {
        if (!this.A01) {
            this.A01 = true;
            AbstractC003107k r1 = this.A02.A02;
            if (r1 != null) {
                r1.A6k(null);
            }
        }
    }
}
