package defpackage;

import android.widget.PopupWindow;
import java.util.Iterator;

/* renamed from: M4  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class M4 implements PopupWindow.OnDismissListener {
    public final /* synthetic */ O4 F;

    public M4(O4 o4) {
        this.F = o4;
    }

    public void onDismiss() {
        O4 o4 = this.F;
        if (!o4.a0) {
            o4.I.removeCallbacks(o4.M);
            Iterator it = this.F.P.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((PopupWindow.OnDismissListener) uq0.next()).onDismiss();
                } else {
                    this.F.L.c();
                    return;
                }
            }
        }
    }
}
