package X;

import android.view.View;

/* renamed from: X.0Mz  reason: invalid class name and case insensitive filesystem */
public class C01910Mz extends C03750dB {
    public int A00 = 0;
    public boolean A01 = false;
    public final /* synthetic */ AnonymousClass03K A02;

    public C01910Mz(AnonymousClass03K r2) {
        this.A02 = r2;
    }

    @Override // X.AnonymousClass0B1, X.C03750dB
    public final void A5q(View view) {
        int i = this.A00 + 1;
        this.A00 = i;
        AnonymousClass03K r2 = this.A02;
        if (i == r2.A04.size()) {
            AnonymousClass0B1 r1 = r2.A02;
            if (r1 != null) {
                r1.A5q(null);
            }
            this.A00 = 0;
            this.A01 = false;
            r2.A03 = false;
        }
    }

    @Override // X.AnonymousClass0B1, X.C03750dB
    public final void A5r(View view) {
        if (!this.A01) {
            this.A01 = true;
            AnonymousClass0B1 r1 = this.A02.A02;
            if (r1 != null) {
                r1.A5r(null);
            }
        }
    }
}
