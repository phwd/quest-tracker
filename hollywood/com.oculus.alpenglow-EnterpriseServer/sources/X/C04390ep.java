package X;

import android.view.Window;
import androidx.annotation.NonNull;

/* renamed from: X.0ep  reason: invalid class name and case insensitive filesystem */
public final class C04390ep implements AbstractC000503a {
    public boolean A00;
    public final /* synthetic */ C04370em A01;

    public C04390ep(C04370em r1) {
        this.A01 = r1;
    }

    @Override // X.AbstractC000503a
    public final void A5x(@NonNull C04280eZ r3, boolean z) {
        if (!this.A00) {
            this.A00 = true;
            C04370em r1 = this.A01;
            r1.A02.A28();
            Window.Callback callback = r1.A01;
            if (callback != null) {
                callback.onPanelClosed(108, r3);
            }
            this.A00 = false;
        }
    }

    @Override // X.AbstractC000503a
    public final boolean A6L(@NonNull C04280eZ r3) {
        Window.Callback callback = this.A01.A01;
        if (callback == null) {
            return false;
        }
        callback.onMenuOpened(108, r3);
        return true;
    }
}
