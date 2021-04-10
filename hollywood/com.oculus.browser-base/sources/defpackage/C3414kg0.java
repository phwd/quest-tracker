package defpackage;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* renamed from: kg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3414kg0 extends Animation {
    public final /* synthetic */ int F;
    public final /* synthetic */ int G;
    public final /* synthetic */ View H;

    public C3414kg0(C4950tg0 tg0, int i, int i2, View view) {
        this.F = i;
        this.G = i2;
        this.H = view;
    }

    public void applyTransformation(float f, Transformation transformation) {
        int i = this.F;
        int i2 = this.G;
        DialogC5460wg0.f(this.H, i2 + ((int) (((float) (i - i2)) * f)));
    }
}
