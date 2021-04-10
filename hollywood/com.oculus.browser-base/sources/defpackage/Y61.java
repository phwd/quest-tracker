package defpackage;

import android.widget.PopupWindow;

/* renamed from: Y61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Y61 implements PopupWindow.OnDismissListener {
    public final C1789b71 F;

    public Y61(C1789b71 b71) {
        this.F = b71;
    }

    public void onDismiss() {
        C1789b71 b71 = this.F;
        b71.f9514a.unregisterComponentCallbacks(b71.b);
    }
}
