package defpackage;

import android.widget.PopupWindow;

/* renamed from: Sf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1114Sf1 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ C1175Tf1 F;

    public C1114Sf1(C1175Tf1 tf1) {
        this.F = tf1;
    }

    public void onDismiss() {
        C1175Tf1.F.remove(this.F);
    }
}
