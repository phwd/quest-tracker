package defpackage;

import android.view.animation.Interpolator;

/* renamed from: II0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class II0 implements Interpolator {

    /* renamed from: a  reason: collision with root package name */
    public final Interpolator f8216a;

    public II0(Interpolator interpolator) {
        this.f8216a = interpolator;
    }

    public float getInterpolation(float f) {
        double d = (double) f;
        if (d < 0.2d) {
            return this.f8216a.getInterpolation(f / 0.2f);
        }
        if (d < 0.6d) {
            return 1.0f;
        }
        return this.f8216a.getInterpolation(1.0f - ((f - 0.6f) / 0.4f));
    }
}
