package X;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.annotation.NonNull;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.08J  reason: invalid class name */
public abstract class AnonymousClass08J implements View.OnTouchListener {
    public static final int A0G = ViewConfiguration.getTapTimeout();
    public boolean A00;
    public int A01;
    public int A02;
    public Runnable A03;
    public boolean A04;
    public boolean A05;
    public boolean A06;
    public boolean A07;
    public float[] A08 = {Float.MAX_VALUE, Float.MAX_VALUE};
    public float[] A09 = {Float.MAX_VALUE, Float.MAX_VALUE};
    public float[] A0A = {AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z};
    public float[] A0B = {AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z};
    public float[] A0C = {AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z};
    public final View A0D;
    public final Interpolator A0E = new AccelerateInterpolator();
    public final AnonymousClass08H A0F = new AnonymousClass08H();

    private float A00(float f, float f2) {
        if (f2 != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            int i = this.A02;
            if (i == 0 || i == 1) {
                if (f < f2) {
                    if (f >= AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                        return 1.0f - (f / f2);
                    }
                    if (!this.A05 || i != 1) {
                        return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                    }
                    return 1.0f;
                }
            } else if (i == 2 && f < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                return f / (-f2);
            }
        }
        return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    }

    public abstract void A03(int i, int i2);

    public abstract boolean A05(int i);

    public abstract boolean A06(int i);

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005f, code lost:
        if (r1 == com.oculus.vrshell.panels.AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) goto L_0x0061;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private float A01(int r7, float r8, float r9, float r10) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass08J.A01(int, float, float, float):float");
    }

    public static void A02(AnonymousClass08J r7) {
        if (r7.A07) {
            r7.A05 = false;
            return;
        }
        AnonymousClass08H r72 = r7.A0F;
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i = (int) (currentAnimationTimeMillis - r72.A07);
        int i2 = r72.A04;
        if (i <= i2) {
            i2 = i;
            if (i < 0) {
                i2 = 0;
            }
        }
        r72.A03 = i2;
        r72.A00 = AnonymousClass08H.A00(r72, currentAnimationTimeMillis);
        r72.A08 = currentAnimationTimeMillis;
    }

    public final boolean A04() {
        AnonymousClass08H r3 = this.A0F;
        float f = r3.A02;
        int abs = (int) (f / Math.abs(f));
        float f2 = r3.A01;
        int abs2 = (int) (f2 / Math.abs(f2));
        if (abs != 0 && A06(abs)) {
            return true;
        }
        if (abs2 == 0 || !A05(abs2)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        if (r1 != 3) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouch(android.view.View r8, android.view.MotionEvent r9) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass08J.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public AnonymousClass08J(@NonNull View view) {
        this.A0D = view;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float[] fArr = this.A09;
        float f2 = ((float) ((int) ((1575.0f * f) + 0.5f))) / 1000.0f;
        fArr[0] = f2;
        fArr[1] = f2;
        float[] fArr2 = this.A0A;
        float f3 = ((float) ((int) ((f * 315.0f) + 0.5f))) / 1000.0f;
        fArr2[0] = f3;
        fArr2[1] = f3;
        this.A02 = 1;
        float[] fArr3 = this.A08;
        fArr3[0] = Float.MAX_VALUE;
        fArr3[1] = Float.MAX_VALUE;
        float[] fArr4 = this.A0B;
        fArr4[0] = 0.2f;
        fArr4[1] = 0.2f;
        float[] fArr5 = this.A0C;
        float f4 = 1.0f / 1000.0f;
        fArr5[0] = f4;
        fArr5[1] = f4;
        this.A01 = A0G;
        AnonymousClass08H r1 = this.A0F;
        r1.A05 = 500;
        r1.A04 = 500;
    }
}
