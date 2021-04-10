package defpackage;

import android.view.View;
import android.widget.PopupWindow;

/* renamed from: l50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3490l50 implements PopupWindow.OnDismissListener {
    public final View F;

    public C3490l50(View view) {
        this.F = view;
    }

    public void onDismiss() {
        this.F.setSelected(false);
    }
}
