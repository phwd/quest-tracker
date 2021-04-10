package defpackage;

import android.view.MenuItem;
import androidx.appcompat.widget.ActionMenuView;

/* renamed from: w2  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5356w2 implements AbstractC4275pi0 {
    public final /* synthetic */ ActionMenuView F;

    public C5356w2(ActionMenuView actionMenuView) {
        this.F = actionMenuView;
    }

    @Override // defpackage.AbstractC4275pi0
    public boolean a(C4616ri0 ri0, MenuItem menuItem) {
        C3937nj1 nj1 = this.F.i0;
        if (nj1 == null) {
            return false;
        }
        AbstractC4790sj1 sj1 = nj1.f10510a.o0;
        if (sj1 != null ? sj1.onMenuItemClick(menuItem) : false) {
            return true;
        }
        return false;
    }

    @Override // defpackage.AbstractC4275pi0
    public void b(C4616ri0 ri0) {
        AbstractC4275pi0 pi0 = this.F.d0;
        if (pi0 != null) {
            pi0.b(ri0);
        }
    }
}
