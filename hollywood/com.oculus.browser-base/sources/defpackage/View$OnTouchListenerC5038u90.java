package defpackage;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ListView;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: u90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnTouchListenerC5038u90 implements View.OnTouchListener {
    public static final int F = ViewConfiguration.getTapTimeout();
    public final C3401kc G;
    public final Interpolator H = new AccelerateInterpolator();
    public final View I;

    /* renamed from: J  reason: collision with root package name */
    public Runnable f11392J;
    public float[] K = {0.0f, 0.0f};
    public float[] L = {Float.MAX_VALUE, Float.MAX_VALUE};
    public int M;
    public int N;
    public float[] O = {0.0f, 0.0f};
    public float[] P = {0.0f, 0.0f};
    public float[] Q = {Float.MAX_VALUE, Float.MAX_VALUE};
    public boolean R;
    public boolean S;
    public boolean T;
    public boolean U;
    public boolean V;
    public final ListView W;

    public View$OnTouchListenerC5038u90(ListView listView) {
        C3401kc kcVar = new C3401kc();
        this.G = kcVar;
        this.I = listView;
        float f = Resources.getSystem().getDisplayMetrics().density;
        float[] fArr = this.Q;
        float f2 = ((float) ((int) ((1575.0f * f) + 0.5f))) / 1000.0f;
        fArr[0] = f2;
        fArr[1] = f2;
        float[] fArr2 = this.P;
        float f3 = ((float) ((int) ((f * 315.0f) + 0.5f))) / 1000.0f;
        fArr2[0] = f3;
        fArr2[1] = f3;
        this.M = 1;
        float[] fArr3 = this.L;
        fArr3[0] = Float.MAX_VALUE;
        fArr3[1] = Float.MAX_VALUE;
        float[] fArr4 = this.K;
        fArr4[0] = 0.2f;
        fArr4[1] = 0.2f;
        float[] fArr5 = this.O;
        fArr5[0] = 0.001f;
        fArr5[1] = 0.001f;
        this.N = F;
        kcVar.f10289a = 500;
        kcVar.b = 500;
        this.W = listView;
    }

    public static float b(float f, float f2, float f3) {
        return f > f3 ? f3 : f < f2 ? f2 : f;
    }

    public final float a(int i, float f, float f2, float f3) {
        float d = d(this.K[i], f2, this.L[i], f);
        int i2 = (d > 0.0f ? 1 : (d == 0.0f ? 0 : -1));
        if (i2 == 0) {
            return 0.0f;
        }
        float f4 = this.O[i];
        float f5 = this.P[i];
        float f6 = this.Q[i];
        float f7 = f4 * f3;
        if (i2 > 0) {
            return b(d * f7, f5, f6);
        }
        return -b((-d) * f7, f5, f6);
    }

    public final float c(float f, float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        int i = this.M;
        if (i == 0 || i == 1) {
            if (f >= f2) {
                return 0.0f;
            }
            if (f >= 0.0f) {
                return 1.0f - (f / f2);
            }
            return (!this.U || i != 1) ? 0.0f : 1.0f;
        } else if (i == 2 && f < 0.0f) {
            return f / (-f2);
        } else {
            return 0.0f;
        }
    }

    public final float d(float f, float f2, float f3, float f4) {
        float f5;
        float b = b(f * f2, 0.0f, f3);
        float c = c(f2 - f4, b) - c(f4, b);
        if (c < 0.0f) {
            f5 = -this.H.getInterpolation(-c);
        } else if (c <= 0.0f) {
            return 0.0f;
        } else {
            f5 = this.H.getInterpolation(c);
        }
        return b(f5, -1.0f, 1.0f);
    }

    public final void e() {
        int i = 0;
        if (this.S) {
            this.U = false;
            return;
        }
        C3401kc kcVar = this.G;
        Objects.requireNonNull(kcVar);
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        int i2 = (int) (currentAnimationTimeMillis - kcVar.e);
        int i3 = kcVar.b;
        if (i2 > i3) {
            i = i3;
        } else if (i2 >= 0) {
            i = i2;
        }
        kcVar.k = i;
        kcVar.j = kcVar.a(currentAnimationTimeMillis);
        kcVar.i = currentAnimationTimeMillis;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean f() {
        /*
            r8 = this;
            kc r0 = r8.G
            float r1 = r0.d
            float r2 = java.lang.Math.abs(r1)
            float r1 = r1 / r2
            int r1 = (int) r1
            float r0 = r0.c
            java.lang.Math.abs(r0)
            r0 = 1
            r2 = 0
            if (r1 == 0) goto L_0x004e
            android.widget.ListView r3 = r8.W
            int r4 = r3.getCount()
            if (r4 != 0) goto L_0x001c
            goto L_0x004b
        L_0x001c:
            int r5 = r3.getChildCount()
            int r6 = r3.getFirstVisiblePosition()
            int r7 = r6 + r5
            if (r1 <= 0) goto L_0x003a
            if (r7 < r4) goto L_0x0049
            int r5 = r5 - r0
            android.view.View r1 = r3.getChildAt(r5)
            int r1 = r1.getBottom()
            int r3 = r3.getHeight()
            if (r1 > r3) goto L_0x0049
            goto L_0x004b
        L_0x003a:
            if (r1 >= 0) goto L_0x004b
            if (r6 > 0) goto L_0x0049
            android.view.View r1 = r3.getChildAt(r2)
            int r1 = r1.getTop()
            if (r1 < 0) goto L_0x0049
            goto L_0x004b
        L_0x0049:
            r1 = r0
            goto L_0x004c
        L_0x004b:
            r1 = r2
        L_0x004c:
            if (r1 != 0) goto L_0x004f
        L_0x004e:
            r0 = r2
        L_0x004f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.View$OnTouchListenerC5038u90.f():boolean");
    }

    public final void g() {
        int i;
        if (this.f11392J == null) {
            this.f11392J = new RunnableC3572lc(this);
        }
        this.U = true;
        this.S = true;
        if (this.R || (i = this.N) <= 0) {
            this.f11392J.run();
        } else {
            View view = this.I;
            Runnable runnable = this.f11392J;
            long j = (long) i;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            view.postOnAnimationDelayed(runnable, j);
        }
        this.R = true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r0 != 3) goto L_0x0059;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.V
            r1 = 0
            if (r0 != 0) goto L_0x0006
            goto L_0x0059
        L_0x0006:
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L_0x001a
            if (r0 == r2) goto L_0x0016
            r3 = 2
            if (r0 == r3) goto L_0x001e
            r6 = 3
            if (r0 == r6) goto L_0x0016
            goto L_0x0059
        L_0x0016:
            r5.e()
            goto L_0x0059
        L_0x001a:
            r5.T = r2
            r5.R = r1
        L_0x001e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            float r3 = (float) r3
            android.view.View r4 = r5.I
            int r4 = r4.getWidth()
            float r4 = (float) r4
            float r0 = r5.a(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            float r6 = (float) r6
            android.view.View r3 = r5.I
            int r3 = r3.getHeight()
            float r3 = (float) r3
            float r6 = r5.a(r2, r7, r6, r3)
            kc r7 = r5.G
            r7.c = r0
            r7.d = r6
            boolean r6 = r5.U
            if (r6 != 0) goto L_0x0059
            boolean r6 = r5.f()
            if (r6 == 0) goto L_0x0059
            r5.g()
        L_0x0059:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.View$OnTouchListenerC5038u90.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }
}
