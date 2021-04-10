package defpackage;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: Jm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewTreeObserver$OnGlobalLayoutListenerC0582Jm implements ViewTreeObserver.OnGlobalLayoutListener {
    public final /* synthetic */ View$OnKeyListenerC0886Om F;

    public ViewTreeObserver$OnGlobalLayoutListenerC0582Jm(View$OnKeyListenerC0886Om om) {
        this.F = om;
    }

    public void onGlobalLayout() {
        if (this.F.b() && this.F.N.size() > 0 && !((C0825Nm) this.F.N.get(0)).f8573a.f0) {
            View view = this.F.U;
            if (view == null || !view.isShown()) {
                this.F.dismiss();
                return;
            }
            for (C0825Nm nm : this.F.N) {
                nm.f8573a.d();
            }
        }
    }
}
