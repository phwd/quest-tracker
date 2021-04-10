package X;

import android.view.Window;
import androidx.annotation.NonNull;

/* renamed from: X.0eu  reason: invalid class name and case insensitive filesystem */
public final class C04440eu implements AbstractC000503a {
    public final /* synthetic */ LayoutInflater$Factory2C04430et A00;

    public C04440eu(LayoutInflater$Factory2C04430et r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC000503a
    public final void A5x(@NonNull C04280eZ r10, boolean z) {
        C04280eZ r0;
        C04280eZ A04 = r10.A04();
        boolean z2 = false;
        if (A04 != r10) {
            z2 = true;
        }
        LayoutInflater$Factory2C04430et r5 = this.A00;
        if (z2) {
            r10 = A04;
        }
        AnonymousClass02x[] r4 = r5.A0b;
        if (r4 != null) {
            for (AnonymousClass02x r2 : r4) {
                if (r2 != null && (r0 = r2.A0A) == r10) {
                    if (z2) {
                        int i = r2.A03;
                        if (A04 == null) {
                            A04 = r0;
                        }
                        if (r2.A0C && !r5.A0V) {
                            ((AnonymousClass03L) r5.A0E).A00.onPanelClosed(i, A04);
                        }
                        r5.A0f(r2, true);
                        return;
                    } else {
                        r5.A0f(r2, z);
                        return;
                    }
                }
            }
        }
    }

    @Override // X.AbstractC000503a
    public final boolean A6L(@NonNull C04280eZ r4) {
        Window.Callback callback;
        if (r4 != r4.A04()) {
            return true;
        }
        LayoutInflater$Factory2C04430et r2 = this.A00;
        if (!r2.A01 || (callback = r2.A0B.getCallback()) == null || r2.A0V) {
            return true;
        }
        callback.onMenuOpened(108, r4);
        return true;
    }
}
