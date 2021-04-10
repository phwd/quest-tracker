package X;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.ActionMenuView;

/* renamed from: X.1tK  reason: invalid class name and case insensitive filesystem */
public class C11891tK implements AnonymousClass1tQ {
    public final /* synthetic */ ActionMenuView A00;

    public C11891tK(ActionMenuView actionMenuView) {
        this.A00 = actionMenuView;
    }

    @Override // X.AnonymousClass1tQ
    public final boolean A7I(@NonNull C11581sN r3, @NonNull MenuItem menuItem) {
        AbstractC12021tm r0 = this.A00.A05;
        if (r0 == null || !r0.onMenuItemClick(menuItem)) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass1tQ
    public final void A7J(@NonNull C11581sN r2) {
        AnonymousClass1tQ r0 = this.A00.A00;
        if (r0 != null) {
            r0.A7J(r2);
        }
    }
}
