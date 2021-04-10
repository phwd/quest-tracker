package defpackage;

import android.view.View;

/* renamed from: r41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC4513r41 implements View.OnLayoutChangeListener {
    public final /* synthetic */ AbstractC4854t41 F;

    public View$OnLayoutChangeListenerC4513r41(AbstractC4854t41 t41) {
        this.F = t41;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        AbstractC4854t41 t41 = this.F;
        t41.removeOnLayoutChangeListener(t41.G);
        AbstractC4854t41 t412 = this.F;
        t412.setTranslationY((float) t412.M);
        AbstractC4854t41 t413 = this.F;
        t413.N = true;
        t413.c(true);
    }
}
