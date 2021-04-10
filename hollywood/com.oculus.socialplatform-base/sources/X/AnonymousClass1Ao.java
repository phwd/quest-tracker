package X;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* renamed from: X.1Ao  reason: invalid class name */
public final class AnonymousClass1Ao extends AnonymousClass1An {
    public float A00;
    public int A01 = 0;
    public int A02 = 0;
    public PointF A03;
    public boolean A04 = false;
    public final DisplayMetrics A05;
    public final DecelerateInterpolator A06 = new DecelerateInterpolator();
    public final LinearInterpolator A07 = new LinearInterpolator();

    public AnonymousClass1Ao(Context context) {
        this.A05 = context.getResources().getDisplayMetrics();
    }

    public static final int A00(AnonymousClass1Ao r3, int i) {
        float abs = (float) Math.abs(i);
        if (!r3.A04) {
            r3.A00 = 25.0f / ((float) r3.A05.densityDpi);
            r3.A04 = true;
        }
        return (int) Math.ceil((double) (abs * r3.A00));
    }
}
