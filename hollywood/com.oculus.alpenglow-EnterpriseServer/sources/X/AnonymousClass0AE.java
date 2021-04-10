package X;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* renamed from: X.0AE  reason: invalid class name */
public abstract class AnonymousClass0AE {
    public AnonymousClass0AC A00;
    public AnonymousClass0AD A01;
    public final Context A02;

    public abstract View A00();

    public void A02(SubMenu subMenu) {
    }

    public boolean A04() {
        return false;
    }

    public boolean A05() {
        return true;
    }

    public boolean A06() {
        return false;
    }

    public boolean A07() {
        return false;
    }

    public void A03(AnonymousClass0AD r4) {
        if (!(this.A01 == null || r4 == null)) {
            Log.w("ActionProvider(support)", AnonymousClass006.A07("setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this ", getClass().getSimpleName(), " instance while it is still in use somewhere else?"));
        }
        this.A01 = r4;
    }

    public AnonymousClass0AE(Context context) {
        this.A02 = context;
    }

    public View A01(MenuItem menuItem) {
        return A00();
    }
}
