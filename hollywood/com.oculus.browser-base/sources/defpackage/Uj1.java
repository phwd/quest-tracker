package defpackage;

import android.view.View;

/* renamed from: Uj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Uj1 implements View.OnLayoutChangeListener {
    public final /* synthetic */ Wj1 F;

    public Uj1(Wj1 wj1) {
        this.F = wj1;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Wj1 wj1 = this.F;
        if (wj1.M && wj1.L.getParent() != null) {
            this.F.L.g();
        }
        this.F.removeOnLayoutChangeListener(this);
    }
}
