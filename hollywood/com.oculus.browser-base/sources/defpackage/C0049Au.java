package defpackage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import java.util.Objects;

/* renamed from: Au  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0049Au extends Drawable implements Animatable {
    public static final Interpolator F = new LinearInterpolator();
    public static final Interpolator G = new animation.InterpolatorC2176dO();
    public static final int[] H = {-16777216};
    public final C6011zu I;

    /* renamed from: J  reason: collision with root package name */
    public float f7703J;
    public Resources K;
    public Animator L;
    public float M;
    public boolean N;

    public C0049Au(Context context) {
        Objects.requireNonNull(context);
        this.K = context.getResources();
        C6011zu zuVar = new C6011zu();
        this.I = zuVar;
        zuVar.i = H;
        zuVar.a(0);
        zuVar.h = 2.5f;
        zuVar.b.setStrokeWidth(2.5f);
        invalidateSelf();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.addUpdateListener(new C5671xu(this, zuVar));
        ofFloat.setRepeatCount(-1);
        ofFloat.setRepeatMode(1);
        ofFloat.setInterpolator(F);
        ofFloat.addListener(new C5841yu(this, zuVar));
        this.L = ofFloat;
    }

    public void a(float f, C6011zu zuVar, boolean z) {
        float f2;
        float f3;
        if (this.N) {
            c(f, zuVar);
            float floor = (float) (Math.floor((double) (zuVar.m / 0.8f)) + 1.0d);
            float f4 = zuVar.k;
            float f5 = zuVar.l;
            zuVar.e = (((f5 - 0.01f) - f4) * f) + f4;
            zuVar.f = f5;
            float f6 = zuVar.m;
            zuVar.g = AbstractC2531fV.a(floor, f6, f, f6);
        } else if (f != 1.0f || z) {
            float f7 = zuVar.m;
            if (f < 0.5f) {
                f2 = zuVar.k;
                f3 = (G.getInterpolation(f / 0.5f) * 0.79f) + 0.01f + f2;
            } else {
                float f8 = zuVar.k + 0.79f;
                f2 = f8 - (((1.0f - G.getInterpolation((f - 0.5f) / 0.5f)) * 0.79f) + 0.01f);
                f3 = f8;
            }
            zuVar.e = f2;
            zuVar.f = f3;
            zuVar.g = (0.20999998f * f) + f7;
            this.f7703J = (f + this.M) * 216.0f;
        }
    }

    public final void b(float f, float f2, float f3, float f4) {
        C6011zu zuVar = this.I;
        float f5 = this.K.getDisplayMetrics().density;
        float f6 = f2 * f5;
        zuVar.h = f6;
        zuVar.b.setStrokeWidth(f6);
        zuVar.q = f * f5;
        zuVar.a(0);
        zuVar.r = (int) (f3 * f5);
        zuVar.s = (int) (f4 * f5);
    }

    public void c(float f, C6011zu zuVar) {
        if (f > 0.75f) {
            float f2 = (f - 0.75f) / 0.25f;
            int[] iArr = zuVar.i;
            int i = zuVar.j;
            int i2 = iArr[i];
            int i3 = iArr[(i + 1) % iArr.length];
            int i4 = (i2 >> 24) & 255;
            int i5 = (i2 >> 16) & 255;
            int i6 = (i2 >> 8) & 255;
            int i7 = i2 & 255;
            zuVar.u = ((i4 + ((int) (((float) (((i3 >> 24) & 255) - i4)) * f2))) << 24) | ((i5 + ((int) (((float) (((i3 >> 16) & 255) - i5)) * f2))) << 16) | ((i6 + ((int) (((float) (((i3 >> 8) & 255) - i6)) * f2))) << 8) | (i7 + ((int) (f2 * ((float) ((i3 & 255) - i7)))));
            return;
        }
        zuVar.u = zuVar.i[zuVar.j];
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        canvas.save();
        canvas.rotate(this.f7703J, bounds.exactCenterX(), bounds.exactCenterY());
        C6011zu zuVar = this.I;
        RectF rectF = zuVar.f11777a;
        float f = zuVar.q;
        float f2 = (zuVar.h / 2.0f) + f;
        if (f <= 0.0f) {
            f2 = (((float) Math.min(bounds.width(), bounds.height())) / 2.0f) - Math.max((((float) zuVar.r) * zuVar.p) / 2.0f, zuVar.h / 2.0f);
        }
        rectF.set(((float) bounds.centerX()) - f2, ((float) bounds.centerY()) - f2, ((float) bounds.centerX()) + f2, ((float) bounds.centerY()) + f2);
        float f3 = zuVar.e;
        float f4 = zuVar.g;
        float f5 = (f3 + f4) * 360.0f;
        float f6 = ((zuVar.f + f4) * 360.0f) - f5;
        zuVar.b.setColor(zuVar.u);
        zuVar.b.setAlpha(zuVar.t);
        float f7 = zuVar.h / 2.0f;
        rectF.inset(f7, f7);
        canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, zuVar.d);
        float f8 = -f7;
        rectF.inset(f8, f8);
        canvas.drawArc(rectF, f5, f6, false, zuVar.b);
        if (zuVar.n) {
            Path path = zuVar.o;
            if (path == null) {
                Path path2 = new Path();
                zuVar.o = path2;
                path2.setFillType(Path.FillType.EVEN_ODD);
            } else {
                path.reset();
            }
            zuVar.o.moveTo(0.0f, 0.0f);
            zuVar.o.lineTo(((float) zuVar.r) * zuVar.p, 0.0f);
            Path path3 = zuVar.o;
            float f9 = zuVar.p;
            path3.lineTo((((float) zuVar.r) * f9) / 2.0f, ((float) zuVar.s) * f9);
            zuVar.o.offset((rectF.centerX() + (Math.min(rectF.width(), rectF.height()) / 2.0f)) - ((((float) zuVar.r) * zuVar.p) / 2.0f), (zuVar.h / 2.0f) + rectF.centerY());
            zuVar.o.close();
            zuVar.c.setColor(zuVar.u);
            zuVar.c.setAlpha(zuVar.t);
            canvas.save();
            canvas.rotate(f5 + f6, rectF.centerX(), rectF.centerY());
            canvas.drawPath(zuVar.o, zuVar.c);
            canvas.restore();
        }
        canvas.restore();
    }

    public int getAlpha() {
        return this.I.t;
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        return this.L.isRunning();
    }

    public void setAlpha(int i) {
        this.I.t = i;
        invalidateSelf();
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.I.b.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void start() {
        this.L.cancel();
        C6011zu zuVar = this.I;
        float f = zuVar.e;
        zuVar.k = f;
        float f2 = zuVar.f;
        zuVar.l = f2;
        zuVar.m = zuVar.g;
        if (f2 != f) {
            this.N = true;
            this.L.setDuration(666);
            this.L.start();
            return;
        }
        zuVar.a(0);
        C6011zu zuVar2 = this.I;
        zuVar2.k = 0.0f;
        zuVar2.l = 0.0f;
        zuVar2.m = 0.0f;
        zuVar2.e = 0.0f;
        zuVar2.f = 0.0f;
        zuVar2.g = 0.0f;
        this.L.setDuration(1332);
        this.L.start();
    }

    public void stop() {
        this.L.cancel();
        this.f7703J = 0.0f;
        C6011zu zuVar = this.I;
        if (zuVar.n) {
            zuVar.n = false;
        }
        zuVar.a(0);
        C6011zu zuVar2 = this.I;
        zuVar2.k = 0.0f;
        zuVar2.l = 0.0f;
        zuVar2.m = 0.0f;
        zuVar2.e = 0.0f;
        zuVar2.f = 0.0f;
        zuVar2.g = 0.0f;
        invalidateSelf();
    }
}
