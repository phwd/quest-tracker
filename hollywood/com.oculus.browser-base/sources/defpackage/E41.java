package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/* renamed from: E41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E41 extends Animation {
    public final /* synthetic */ K41 F;

    public E41(K41 k41) {
        this.F = k41;
    }

    public void applyTransformation(float f, Transformation transformation) {
        K41.b(this.F, f);
    }
}
