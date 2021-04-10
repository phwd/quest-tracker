package defpackage;

import android.view.View;

/* renamed from: Ur1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Ur1 implements Runnable {
    public final String F;
    public final Tm1 G;
    public final XY H;
    public final View I;

    public Ur1(String str, Tm1 tm1, XY xy, View view) {
        this.F = str;
        this.G = tm1;
        this.H = xy;
        this.I = view;
    }

    public void run() {
        String str = this.F;
        Tm1 tm1 = this.G;
        XY xy = this.H;
        View view = this.I;
        if (str != null) {
            tm1.dismissed(str);
        }
        xy.h.run();
        if (xy.e) {
            AbstractC3628lu1.b(view);
        }
    }
}
