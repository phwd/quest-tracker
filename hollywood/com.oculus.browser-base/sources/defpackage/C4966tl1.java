package defpackage;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: tl1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4966tl1 extends ImageView {
    public final ColorDrawable F;
    public final animation.InterpolatorC5286vf G = animation.InterpolatorC5286vf.f;
    public float H;
    public final AnimatorSet I;

    /* renamed from: J  reason: collision with root package name */
    public final ValueAnimator f11368J;
    public final ValueAnimator K;
    public boolean L;
    public boolean M;
    public C4796sl1 N;
    public float O;
    public ValueAnimator P;
    public float Q;

    public C4966tl1(Context context, FrameLayout.LayoutParams layoutParams) {
        super(context);
        setLayoutParams(layoutParams);
        this.L = true;
        this.M = LocalizationUtils.isLayoutRtl();
        this.Q = getResources().getDisplayMetrics().density;
        ColorDrawable colorDrawable = new ColorDrawable(-1);
        this.F = colorDrawable;
        setImageDrawable(colorDrawable);
        this.N = new C4796sl1(this, null);
        AnimatorSet animatorSet = new AnimatorSet();
        this.I = animatorSet;
        ValueAnimator valueAnimator = new ValueAnimator();
        this.K = valueAnimator;
        valueAnimator.setFloatValues(0.0f, 1.0f);
        valueAnimator.addUpdateListener(this.N);
        ValueAnimator valueAnimator2 = new ValueAnimator();
        this.f11368J = valueAnimator2;
        valueAnimator2.setFloatValues(0.0f, 1.0f);
        valueAnimator2.addUpdateListener(this.N);
        c();
        animatorSet.playSequentially(valueAnimator, valueAnimator2);
        C4626rl1 rl1 = new C4626rl1(this);
        valueAnimator.addListener(rl1);
        valueAnimator2.addListener(rl1);
    }

    public void a(float f) {
        this.H = f;
        b(this.P, this.O);
    }

    public final void b(ValueAnimator valueAnimator, float f) {
        if (!this.L) {
            float interpolation = this.G.getInterpolation(f);
            boolean z = this.M;
            float f2 = 0.0f;
            float f3 = z ? -this.H : 0.0f;
            if (!z) {
                f2 = this.H;
            }
            float f4 = 0.3f;
            if (valueAnimator == this.K && f <= 0.6f) {
                f4 = ((f / 0.6f) * 0.20000002f) + 0.1f;
            }
            float min = Math.min(this.Q * 400.0f, this.H * f4);
            float f5 = min / 2.0f;
            float f6 = ((this.H + min) * interpolation) - f5;
            if (this.M) {
                f6 *= -1.0f;
            }
            float f7 = f6 + f5;
            float f8 = f6 - f5;
            if (f7 > f2) {
                float f9 = f7 - f2;
                min -= Math.abs(f9);
                f6 -= Math.abs(f9) / 2.0f;
            } else if (f8 < f3) {
                float f10 = f8 - f3;
                min -= Math.abs(f10);
                f6 += Math.abs(f10) / 2.0f;
            }
            setScaleX(min);
            setTranslationX(f6);
        }
    }

    public final void c() {
        float f = this.H;
        if (f > 0.0f) {
            long log = ((long) (Math.log((double) (f / this.Q)) / Math.log(2.718281828459045d))) * 260;
            if (log > 0) {
                float f2 = (float) log;
                this.K.setDuration((long) (0.6f * f2));
                this.f11368J.setStartDelay((long) (0.02f * f2));
                this.f11368J.setDuration((long) (f2 * 0.38f));
            }
        }
    }
}
