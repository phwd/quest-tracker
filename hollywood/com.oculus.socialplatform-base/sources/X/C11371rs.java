package X;

import android.view.Window;
import androidx.annotation.NonNull;

/* renamed from: X.1rs  reason: invalid class name and case insensitive filesystem */
public final class C11371rs implements AbstractC11941tc {
    public boolean A00;
    public final /* synthetic */ AnonymousClass1rR A01;

    public C11371rs(AnonymousClass1rR r1) {
        this.A01 = r1;
    }

    @Override // X.AbstractC11941tc
    public final void A6r(@NonNull C11581sN r3, boolean z) {
        if (!this.A00) {
            this.A00 = true;
            AnonymousClass1rR r1 = this.A01;
            r1.A02.A2d();
            Window.Callback callback = r1.A01;
            if (callback != null) {
                callback.onPanelClosed(108, r3);
            }
            this.A00 = false;
        }
    }

    @Override // X.AbstractC11941tc
    public final boolean A7T(@NonNull C11581sN r3) {
        Window.Callback callback = this.A01.A01;
        if (callback == null) {
            return false;
        }
        callback.onMenuOpened(108, r3);
        return true;
    }
}
