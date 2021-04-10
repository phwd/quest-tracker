package X;

import android.view.View;
import android.view.Window;

/* renamed from: X.05e  reason: invalid class name and case insensitive filesystem */
public class View$OnClickListenerC004705e implements View.OnClickListener {
    public final AnonymousClass0eg A00;
    public final /* synthetic */ C04030dq A01;

    public View$OnClickListenerC004705e(C04030dq r4) {
        this.A01 = r4;
        this.A00 = new AnonymousClass0eg(r4.A08.getContext(), r4.A0B);
    }

    public final void onClick(View view) {
        C04030dq r0 = this.A01;
        Window.Callback callback = r0.A00;
        if (callback != null && r0.A01) {
            callback.onMenuItemSelected(0, this.A00);
        }
    }
}
