package defpackage;

import android.widget.PopupWindow;

/* renamed from: j81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3158j81 implements PopupWindow.OnDismissListener {
    public final Tm1 F;
    public final String G;

    public C3158j81(Tm1 tm1, String str) {
        this.F = tm1;
        this.G = str;
    }

    public void onDismiss() {
        this.F.dismissed(this.G);
    }
}
