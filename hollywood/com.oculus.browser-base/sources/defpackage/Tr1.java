package defpackage;

import android.view.View;
import android.widget.PopupWindow;

/* renamed from: Tr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Tr1 implements PopupWindow.OnDismissListener {
    public final Vr1 F;
    public final String G;
    public final Tm1 H;
    public final XY I;

    /* renamed from: J  reason: collision with root package name */
    public final View f8990J;

    public Tr1(Vr1 vr1, String str, Tm1 tm1, XY xy, View view) {
        this.F = vr1;
        this.G = str;
        this.H = tm1;
        this.I = xy;
        this.f8990J = view;
    }

    public void onDismiss() {
        this.F.b.postDelayed(new Ur1(this.G, this.H, this.I, this.f8990J), 200);
    }
}
