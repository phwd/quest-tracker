package defpackage;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/* renamed from: I41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class I41 extends Animation {
    public final /* synthetic */ K41 F;

    public I41(K41 k41) {
        this.F = k41;
    }

    public void applyTransformation(float f, Transformation transformation) {
        K41.a(this.F, f);
    }
}
