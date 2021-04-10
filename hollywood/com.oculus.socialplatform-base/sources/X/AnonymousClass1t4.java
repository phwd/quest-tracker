package X;

import android.view.View;
import android.view.Window;

/* renamed from: X.1t4  reason: invalid class name */
public class AnonymousClass1t4 implements View.OnClickListener {
    public final C11631sW A00;
    public final /* synthetic */ AnonymousClass1sI A01;

    public AnonymousClass1t4(AnonymousClass1sI r4) {
        this.A01 = r4;
        this.A00 = new C11631sW(r4.A08.getContext(), r4.A0B);
    }

    public final void onClick(View view) {
        AnonymousClass1sI r0 = this.A01;
        Window.Callback callback = r0.A00;
        if (callback != null && r0.A01) {
            callback.onMenuItemSelected(0, this.A00);
        }
    }
}
