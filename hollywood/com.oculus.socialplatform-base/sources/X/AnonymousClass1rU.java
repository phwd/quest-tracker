package X;

import android.view.Menu;
import android.view.MenuItem;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1rU  reason: invalid class name */
public class AnonymousClass1rU implements AbstractC11461s1 {
    public AbstractC11461s1 A00;
    public final /* synthetic */ AnonymousClass1rJ A01;

    public AnonymousClass1rU(AnonymousClass1rJ r1, AbstractC11461s1 r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    @Override // X.AbstractC11461s1
    public final boolean A6g(AbstractC11301rk r2, MenuItem menuItem) {
        return this.A00.A6g(r2, menuItem);
    }

    @Override // X.AbstractC11461s1
    public final boolean A6u(AbstractC11301rk r2, Menu menu) {
        return this.A00.A6u(r2, menu);
    }

    @Override // X.AbstractC11461s1
    public final void A6z(AbstractC11301rk r4) {
        this.A00.A6z(r4);
        AnonymousClass1rJ r2 = this.A01;
        if (r2.A09 != null) {
            r2.A08.getDecorView().removeCallbacks(r2.A0N);
        }
        if (r2.A0J != null) {
            C003007j r0 = r2.A0L;
            if (r0 != null) {
                r0.A00();
            }
            C003007j A02 = AnonymousClass07f.A02(r2.A0J);
            A02.A01(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            r2.A0L = A02;
            A02.A04(new C11281rc(this));
        }
        r2.A0I = null;
        r2.A07.requestApplyInsets();
    }

    @Override // X.AbstractC11461s1
    public final boolean A7V(AbstractC11301rk r2, Menu menu) {
        this.A01.A07.requestApplyInsets();
        return this.A00.A7V(r2, menu);
    }
}
