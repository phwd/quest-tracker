package X;

import android.view.animation.Interpolator;

/* renamed from: X.1BP  reason: invalid class name */
public class AnonymousClass1BP implements Interpolator {
    public final float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (f2 * f2 * f2 * f2 * f2) + 1.0f;
    }
}
