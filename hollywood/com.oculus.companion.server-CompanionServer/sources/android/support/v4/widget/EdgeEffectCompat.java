package android.support.v4.widget;

import android.os.Build;
import android.widget.EdgeEffect;

public final class EdgeEffectCompat {
    public static void onPull(EdgeEffect edgeEffect, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 21) {
            edgeEffect.onPull(f, f2);
        } else {
            edgeEffect.onPull(f);
        }
    }
}
