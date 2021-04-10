package X;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.ActionMenuView;

/* renamed from: X.0eI  reason: invalid class name */
public class AnonymousClass0eI implements AnonymousClass03V {
    public final /* synthetic */ ActionMenuView A00;

    public AnonymousClass0eI(ActionMenuView actionMenuView) {
        this.A00 = actionMenuView;
    }

    @Override // X.AnonymousClass03V
    public final boolean A6D(@NonNull C04280eZ r3, @NonNull MenuItem menuItem) {
        AbstractC002303v r0 = this.A00.A05;
        if (r0 == null || !r0.onMenuItemClick(menuItem)) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass03V
    public final void A6E(@NonNull C04280eZ r2) {
        AnonymousClass03V r0 = this.A00.A00;
        if (r0 != null) {
            r0.A6E(r2);
        }
    }
}
