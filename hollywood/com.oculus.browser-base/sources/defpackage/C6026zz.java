package defpackage;

import android.widget.PopupWindow;

/* renamed from: zz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C6026zz implements PopupWindow.OnDismissListener {
    public final C0059Az F;
    public final Tm1 G;

    public C6026zz(C0059Az az, Tm1 tm1) {
        this.F = az;
        this.G = tm1;
    }

    public void onDismiss() {
        C0059Az az = this.F;
        this.G.dismissed(az.e);
        az.f = false;
        az.c = null;
    }
}
