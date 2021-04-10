package defpackage;

import android.widget.PopupWindow;

/* renamed from: DJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DJ implements PopupWindow.OnDismissListener {
    public final /* synthetic */ EJ F;

    public DJ(EJ ej) {
        this.F = ej;
    }

    public void onDismiss() {
        this.F.L.K.dismiss();
        EJ ej = this.F;
        ej.G.removeOnLayoutChangeListener(ej.f7953J);
        this.F.G.setTag(null);
    }
}
