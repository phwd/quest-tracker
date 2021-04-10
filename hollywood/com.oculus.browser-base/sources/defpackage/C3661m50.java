package defpackage;

import android.widget.PopupWindow;

/* renamed from: m50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3661m50 implements PopupWindow.OnDismissListener {
    public final Tm1 F;
    public final String G;

    public C3661m50(Tm1 tm1, String str) {
        this.F = tm1;
        this.G = str;
    }

    public void onDismiss() {
        this.F.dismissed(this.G);
    }
}
