package defpackage;

import android.view.View;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;

/* renamed from: kl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnLayoutChangeListenerC3429kl0 implements View.OnLayoutChangeListener {
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = ModalDialogView.I;
        boolean z = true;
        if (!view.canScrollVertically(-1) && !view.canScrollVertically(1)) {
            z = false;
        }
        view.setFocusable(z);
    }
}
