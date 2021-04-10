package X;

import android.view.Menu;
import android.view.MenuItem;

/* renamed from: X.0ez  reason: invalid class name and case insensitive filesystem */
public class C04490ez implements AnonymousClass03C {
    public AnonymousClass03C A00;
    public final /* synthetic */ LayoutInflater$Factory2C04430et A01;

    public C04490ez(LayoutInflater$Factory2C04430et r1, AnonymousClass03C r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AnonymousClass03C
    public final boolean A5o(AnonymousClass03D r2, MenuItem menuItem) {
        return this.A00.A5o(r2, menuItem);
    }

    @Override // X.AnonymousClass03C
    public final boolean A62(AnonymousClass03D r2, Menu menu) {
        return this.A00.A62(r2, menu);
    }

    @Override // X.AnonymousClass03C
    public final void A63(AnonymousClass03D r4) {
        this.A00.A63(r4);
        LayoutInflater$Factory2C04430et r2 = this.A01;
        if (r2.A0C != null) {
            r2.A0B.getDecorView().removeCallbacks(r2.A0O);
        }
        if (r2.A0K != null) {
            AnonymousClass0B0 r0 = r2.A0M;
            if (r0 != null) {
                r0.A00();
            }
            AnonymousClass0B0 A002 = AnonymousClass0Aw.A00(r2.A0K);
            A002.A01(0.0f);
            r2.A0M = A002;
            A002.A04(new AnonymousClass0N2(this));
        }
        r2.A0J = null;
        r2.A0A.requestApplyInsets();
    }

    @Override // X.AnonymousClass03C
    public final boolean A6N(AnonymousClass03D r2, Menu menu) {
        this.A01.A0A.requestApplyInsets();
        return this.A00.A6N(r2, menu);
    }
}
