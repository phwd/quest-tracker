package defpackage;

import android.view.View;

/* renamed from: Nk1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Nk1 implements View.OnLayoutChangeListener {
    public final Ok1 F;

    public Nk1(Ok1 ok1) {
        this.F = ok1;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Ok1 ok1 = this.F;
        if (ok1.G.L.getBackground() == null) {
            Uk1 uk1 = ok1.G;
            Uk1.h(uk1, Uk1.g(uk1));
            ok1.G.L.removeOnLayoutChangeListener(ok1.F);
            ok1.F = null;
        }
    }
}
