package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;

/* renamed from: fd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2551fd0 extends Drawable implements Animatable {
    public static final Interpolator F = new LinearInterpolator();
    public static final Interpolator G = new animation.InterpolatorC2176dO();
    public final int[] H;
    public final ArrayList I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public final C2380ed0 f9935J;
    public float K;
    public Resources L;
    public View M;
    public Animation N;
    public float O;
    public double P;
    public double Q;
    public boolean R;
    public final Drawable.Callback S;

    public C2551fd0(Context context, View view) {
        int[] iArr = {-16777216};
        this.H = iArr;
        C2210dd0 dd0 = new C2210dd0(this);
        this.S = dd0;
        this.M = view;
        this.L = context.getResources();
        C2380ed0 ed0 = new C2380ed0(dd0);
        this.f9935J = ed0;
        ed0.j = iArr;
        ed0.c(0);
        a(40.0d, 40.0d, 8.75d, 2.5d, 10.0f, 5.0f);
        C1868bd0 bd0 = new C1868bd0(this, ed0);
        bd0.setRepeatCount(-1);
        bd0.setRepeatMode(1);
        bd0.setInterpolator(F);
        bd0.setAnimationListener(new animation.Animation$AnimationListenerC2039cd0(this, ed0));
        this.N = bd0;
    }

    public final void a(double d, double d2, double d3, double d4, float f, float f2) {
        double d5;
        C2380ed0 ed0 = this.f9935J;
        float f3 = this.L.getDisplayMetrics().density;
        double d6 = (double) f3;
        this.P = d * d6;
        this.Q = d2 * d6;
        float f4 = ((float) d4) * f3;
        ed0.h = f4;
        ed0.b.setStrokeWidth(f4);
        ed0.a();
        ed0.r = d3 * d6;
        ed0.c(0);
        ed0.s = (int) (f * f3);
        ed0.t = (int) (f2 * f3);
        float min = (float) Math.min((int) this.P, (int) this.Q);
        double d7 = ed0.r;
        if (d7 <= 0.0d || min < 0.0f) {
            d5 = Math.ceil((double) (ed0.h / 2.0f));
        } else {
            d5 = ((double) (min / 2.0f)) - d7;
        }
        ed0.i = (float) d5;
    }

    public final void b(float f, C2380ed0 ed0) {
        if (f > 0.75f) {
            float f2 = (f - 0.75f) / 0.25f;
            int[] iArr = ed0.j;
            int i = ed0.k;
            int i2 = iArr[i];
            int i3 = iArr[(i + 1) % iArr.length];
            int intValue = Integer.valueOf(i2).intValue();
            int i4 = (intValue >> 24) & 255;
            int i5 = (intValue >> 16) & 255;
            int i6 = (intValue >> 8) & 255;
            int i7 = intValue & 255;
            int intValue2 = Integer.valueOf(i3).intValue();
            ed0.x = ((i4 + ((int) (((float) (((intValue2 >> 24) & 255) - i4)) * f2))) << 24) | ((i5 + ((int) (((float) (((intValue2 >> 16) & 255) - i5)) * f2))) << 16) | ((i6 + ((int) (((float) (((intValue2 >> 8) & 255) - i6)) * f2))) << 8) | (i7 + ((int) (f2 * ((float) ((intValue2 & 255) - i7)))));
        }
    }

    public void draw(Canvas canvas) {
        boolean z = true;
        if (this.M.getLayoutDirection() != 1) {
            z = false;
        }
        Rect bounds = getBounds();
        int save = canvas.save();
        if (z) {
            canvas.scale(-1.0f, 1.0f, bounds.exactCenterX(), bounds.exactCenterY());
        }
        canvas.rotate(this.K, bounds.exactCenterX(), bounds.exactCenterY());
        C2380ed0 ed0 = this.f9935J;
        RectF rectF = ed0.f9867a;
        rectF.set(bounds);
        float f = ed0.i;
        rectF.inset(f, f);
        float f2 = ed0.e;
        float f3 = ed0.g;
        float f4 = (f2 + f3) * 360.0f;
        float f5 = ((ed0.f + f3) * 360.0f) - f4;
        ed0.b.setColor(ed0.x);
        canvas.drawArc(rectF, f4, f5, false, ed0.b);
        if (ed0.o) {
            Path path = ed0.p;
            if (path == null) {
                Path path2 = new Path();
                ed0.p = path2;
                path2.setFillType(Path.FillType.EVEN_ODD);
            } else {
                path.reset();
            }
            float f6 = (((float) ((int) ed0.i)) / 2.0f) * ed0.q;
            double d = ed0.r;
            double d2 = ed0.r;
            ed0.p.moveTo(0.0f, 0.0f);
            ed0.p.lineTo(((float) ed0.s) * ed0.q, 0.0f);
            Path path3 = ed0.p;
            float f7 = ed0.q;
            path3.lineTo((((float) ed0.s) * f7) / 2.0f, ((float) ed0.t) * f7);
            ed0.p.offset(((float) ((Math.cos(0.0d) * d) + ((double) bounds.exactCenterX()))) - f6, (float) ((Math.sin(0.0d) * d2) + ((double) bounds.exactCenterY())));
            ed0.p.close();
            ed0.c.setColor(ed0.x);
            canvas.rotate((f4 + f5) - 5.0f, bounds.exactCenterX(), bounds.exactCenterY());
            canvas.drawPath(ed0.p, ed0.c);
        }
        if (ed0.u < 255) {
            ed0.v.setColor(ed0.w);
            ed0.v.setAlpha(255 - ed0.u);
            canvas.drawCircle(bounds.exactCenterX(), bounds.exactCenterY(), ((float) bounds.width()) / 2.0f, ed0.v);
        }
        if (z) {
            canvas.scale(-1.0f, 1.0f, bounds.exactCenterX(), bounds.exactCenterY());
        }
        canvas.restoreToCount(save);
    }

    public int getAlpha() {
        return this.f9935J.u;
    }

    public int getIntrinsicHeight() {
        return (int) this.Q;
    }

    public int getIntrinsicWidth() {
        return (int) this.P;
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        ArrayList arrayList = this.I;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Animation animation = (Animation) arrayList.get(i);
            if (animation.hasStarted() && !animation.hasEnded()) {
                return true;
            }
        }
        return false;
    }

    public void setAlpha(int i) {
        C2380ed0 ed0 = this.f9935J;
        if (ed0.u != i) {
            ed0.u = i;
            ed0.a();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        C2380ed0 ed0 = this.f9935J;
        ed0.b.setColorFilter(colorFilter);
        ed0.a();
    }

    public void start() {
        this.N.reset();
        C2380ed0 ed0 = this.f9935J;
        float f = ed0.e;
        ed0.l = f;
        float f2 = ed0.f;
        ed0.m = f2;
        ed0.n = ed0.g;
        if (f2 != f) {
            this.R = true;
            this.N.setDuration(666);
            this.M.startAnimation(this.N);
            return;
        }
        ed0.c(0);
        this.f9935J.b();
        this.N.setDuration(1332);
        this.M.startAnimation(this.N);
    }

    public void stop() {
        this.M.clearAnimation();
        this.K = 0.0f;
        invalidateSelf();
        this.f9935J.d(false);
        this.f9935J.c(0);
        this.f9935J.b();
    }
}
