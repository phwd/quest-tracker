package defpackage;

import android.view.View;

/* renamed from: W7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class W7 implements Runnable {
    public final /* synthetic */ LayoutInflater$Factory2C3156j8 F;

    public W7(LayoutInflater$Factory2C3156j8 j8Var) {
        this.F = j8Var;
    }

    public void run() {
        LayoutInflater$Factory2C3156j8 j8Var = this.F;
        j8Var.Z.showAtLocation(j8Var.Y, 55, 0, 0);
        this.F.y();
        if (this.F.L()) {
            this.F.Y.setAlpha(0.0f);
            LayoutInflater$Factory2C3156j8 j8Var2 = this.F;
            Zu1 a2 = AbstractC1920bu1.a(j8Var2.Y);
            a2.a(1.0f);
            j8Var2.b0 = a2;
            Zu1 zu1 = this.F.b0;
            V7 v7 = new V7(this);
            View view = (View) zu1.f9382a.get();
            if (view != null) {
                zu1.e(view, v7);
                return;
            }
            return;
        }
        this.F.Y.setAlpha(1.0f);
        this.F.Y.setVisibility(0);
    }
}
