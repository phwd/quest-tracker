package X;

import android.view.animation.Interpolator;

/* renamed from: X.0Ci  reason: invalid class name and case insensitive filesystem */
public class animation.InterpolatorC00680Ci implements Interpolator {
    public final float getInterpolation(float f) {
        float f2 = f - 1.0f;
        return (f2 * f2 * f2 * f2 * f2) + 1.0f;
    }
}
