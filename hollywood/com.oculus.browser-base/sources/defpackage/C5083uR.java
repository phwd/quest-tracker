package defpackage;

import android.animation.ValueAnimator;
import android.view.View;

/* renamed from: uR  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5083uR implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ View F;
    public final /* synthetic */ int G;
    public final /* synthetic */ int H;
    public final /* synthetic */ int I;

    public C5083uR(C5763yR yRVar, View view, int i, int i2, int i3) {
        this.F = view;
        this.G = i;
        this.H = i2;
        this.I = i3;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        float f = 1.0f - floatValue;
        this.F.setTranslationY(((float) this.G) * f);
        int i = this.H;
        int i2 = this.I;
        if (i != i2) {
            View view = this.F;
            view.setBottom(view.getTop() + ((int) ((((float) i2) * floatValue) + (((float) i) * f))));
        }
    }
}
