package defpackage;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* renamed from: yf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5797yf0 extends Animation {
    public final /* synthetic */ int F;
    public final /* synthetic */ int G;
    public final /* synthetic */ View H;

    public C5797yf0(DialogC0504If0 if0, int i, int i2, View view) {
        this.F = i;
        this.G = i2;
        this.H = view;
    }

    public void applyTransformation(float f, Transformation transformation) {
        int i = this.F;
        DialogC0504If0.q(this.H, i - ((int) (((float) (i - this.G)) * f)));
    }
}
