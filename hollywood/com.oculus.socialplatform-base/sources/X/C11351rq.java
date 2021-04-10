package X;

import android.view.MenuItem;
import androidx.annotation.NonNull;

/* renamed from: X.1rq  reason: invalid class name and case insensitive filesystem */
public final class C11351rq implements AnonymousClass1tQ {
    public final /* synthetic */ AnonymousClass1rR A00;

    @Override // X.AnonymousClass1tQ
    public final boolean A7I(@NonNull C11581sN r2, @NonNull MenuItem menuItem) {
        return false;
    }

    public C11351rq(AnonymousClass1rR r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1tQ
    public final void A7J(@NonNull C11581sN r6) {
        AnonymousClass1rR r4 = this.A00;
        if (r4.A01 == null) {
            return;
        }
        if (r4.A02.A67()) {
            r4.A01.onPanelClosed(108, r6);
        } else if (r4.A01.onPreparePanel(0, null, r6)) {
            r4.A01.onMenuOpened(108, r6);
        }
    }
}
