package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: WZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WZ0 implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ YZ0 F;

    public WZ0(YZ0 yz0) {
        this.F = yz0;
    }

    public void onGlobalLayout() {
        if (this.F.b()) {
            YZ0 yz0 = this.F;
            if (!yz0.N.f0) {
                View view = yz0.S;
                if (view == null || !view.isShown()) {
                    this.F.dismiss();
                } else {
                    this.F.N.d();
                }
            }
        }
    }
}
