package defpackage;

import android.widget.PopupWindow;

/* renamed from: Rm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1070Rm0 implements PopupWindow.OnDismissListener {
    public final C1375Wm0 F;

    public C1070Rm0(C1375Wm0 wm0) {
        this.F = wm0;
    }

    public void onDismiss() {
        C1375Wm0 wm0 = this.F;
        if (wm0.Q) {
            wm0.P.b();
        }
        wm0.Q = false;
        C3713mO mOVar = wm0.O;
        if (mOVar != null) {
            mOVar.f10418a = null;
            mOVar.b = null;
            mOVar.c = null;
            mOVar.d = null;
        }
        if (wm0.N != null) {
            wm0.H.getAnchorView().removeOnLayoutChangeListener(wm0.N);
        }
    }
}
