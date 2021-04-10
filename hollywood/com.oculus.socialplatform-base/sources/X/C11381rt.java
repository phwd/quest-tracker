package X;

import android.view.Window;
import androidx.annotation.NonNull;

/* renamed from: X.1rt  reason: invalid class name and case insensitive filesystem */
public final class C11381rt implements AbstractC11941tc {
    public final /* synthetic */ AnonymousClass1rJ A00;

    public C11381rt(AnonymousClass1rJ r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC11941tc
    public final void A6r(@NonNull C11581sN r2, boolean z) {
        this.A00.A0Q(r2);
    }

    @Override // X.AbstractC11941tc
    public final boolean A7T(@NonNull C11581sN r3) {
        Window.Callback callback = this.A00.A08.getCallback();
        if (callback == null) {
            return true;
        }
        callback.onMenuOpened(108, r3);
        return true;
    }
}
