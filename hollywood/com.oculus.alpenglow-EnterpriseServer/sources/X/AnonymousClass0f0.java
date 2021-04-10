package X;

import android.view.Window;
import androidx.annotation.NonNull;

/* renamed from: X.0f0  reason: invalid class name */
public final class AnonymousClass0f0 implements AbstractC000503a {
    public final /* synthetic */ LayoutInflater$Factory2C04430et A00;

    public AnonymousClass0f0(LayoutInflater$Factory2C04430et r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC000503a
    public final void A5x(@NonNull C04280eZ r2, boolean z) {
        this.A00.A0g(r2);
    }

    @Override // X.AbstractC000503a
    public final boolean A6L(@NonNull C04280eZ r3) {
        Window.Callback callback = this.A00.A0B.getCallback();
        if (callback == null) {
            return true;
        }
        callback.onMenuOpened(108, r3);
        return true;
    }
}
