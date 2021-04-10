package X;

import android.view.Window;
import androidx.annotation.NonNull;

/* renamed from: X.1ri  reason: invalid class name */
public final class AnonymousClass1ri implements AbstractC11941tc {
    public final /* synthetic */ AnonymousClass1rJ A00;

    public AnonymousClass1ri(AnonymousClass1rJ r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC11941tc
    public final void A6r(@NonNull C11581sN r10, boolean z) {
        C11581sN r0;
        C11581sN A04 = r10.A04();
        boolean z2 = false;
        if (A04 != r10) {
            z2 = true;
        }
        AnonymousClass1rJ r5 = this.A00;
        if (z2) {
            r10 = A04;
        }
        AnonymousClass1rh[] r4 = r5.A0g;
        if (r4 != null) {
            for (AnonymousClass1rh r2 : r4) {
                if (r2 != null && (r0 = r2.A0A) == r10) {
                    if (z2) {
                        int i = r2.A02;
                        if (A04 == null) {
                            A04 = r0;
                        }
                        if (r2.A0C && !r5.A0Y) {
                            ((Window$CallbackC11241rW) r5.A0C).A00.onPanelClosed(i, A04);
                        }
                        r5.A0P(r2, true);
                        return;
                    } else {
                        r5.A0P(r2, z);
                        return;
                    }
                }
            }
        }
    }

    @Override // X.AbstractC11941tc
    public final boolean A7T(@NonNull C11581sN r4) {
        Window.Callback callback;
        if (r4 != r4.A04()) {
            return true;
        }
        AnonymousClass1rJ r2 = this.A00;
        if (!r2.A0W || (callback = r2.A08.getCallback()) == null || r2.A0Y) {
            return true;
        }
        callback.onMenuOpened(108, r4);
        return true;
    }
}
