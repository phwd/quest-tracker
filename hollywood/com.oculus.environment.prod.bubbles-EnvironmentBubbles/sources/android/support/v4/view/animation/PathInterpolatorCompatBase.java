package android.support.v4.view.animation;

import android.graphics.Path;
import android.view.animation.Interpolator;

class PathInterpolatorCompatBase {
    private PathInterpolatorCompatBase() {
    }

    public static Interpolator create(Path path) {
        return new PathInterpolatorGingerbread(path);
    }

    public static Interpolator create(float f, float f2) {
        return new PathInterpolatorGingerbread(f, f2);
    }

    public static Interpolator create(float f, float f2, float f3, float f4) {
        return new PathInterpolatorGingerbread(f, f2, f3, f4);
    }
}
