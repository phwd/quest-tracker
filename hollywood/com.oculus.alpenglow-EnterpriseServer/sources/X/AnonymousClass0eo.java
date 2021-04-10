package X;

import android.view.MenuItem;
import androidx.annotation.NonNull;

/* renamed from: X.0eo  reason: invalid class name */
public final class AnonymousClass0eo implements AnonymousClass03V {
    public final /* synthetic */ C04370em A00;

    @Override // X.AnonymousClass03V
    public final boolean A6D(@NonNull C04280eZ r2, @NonNull MenuItem menuItem) {
        return false;
    }

    public AnonymousClass0eo(C04370em r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass03V
    public final void A6E(@NonNull C04280eZ r6) {
        C04370em r4 = this.A00;
        if (r4.A01 == null) {
            return;
        }
        if (r4.A02.A5X()) {
            r4.A01.onPanelClosed(108, r6);
        } else if (r4.A01.onPreparePanel(0, null, r6)) {
            r4.A01.onMenuOpened(108, r6);
        }
    }
}
