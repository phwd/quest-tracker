package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.animation.PathInterpolator;
import com.oculus.browser.R;

/* renamed from: HI0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HI0 extends Drawable implements Animatable {
    public final Runnable F = new CI0(this);
    public final Paint G = new Paint(1);
    public final Rect H = new Rect();
    public final Rect I = new Rect();

    /* renamed from: J  reason: collision with root package name */
    public final Rect f8150J = new Rect();
    public GI0 K;
    public boolean L;
    public boolean M;
    public long N;
    public final EI0 O;

    public HI0(GI0 gi0, EI0 ei0) {
        this.K = gi0;
        this.O = ei0;
    }

    public static HI0 a(Context context) {
        return b(context, new BI0(context.getResources().getDimensionPixelSize(R.dimen.f20070_resource_name_obfuscated_RES_2131165626)), new EI0(null));
    }

    public static HI0 b(Context context, DI0 di0, EI0 ei0) {
        HI0 hi0 = new HI0(new GI0(new PathInterpolator(0.8f, 0.0f, 0.6f, 1.0f), new C5915zI0(di0)), ei0);
        hi0.d(context.getResources(), false);
        hi0.setAlpha(76);
        return hi0;
    }

    public static HI0 c(Context context, int i) {
        HI0 hi0 = new HI0(new GI0(G30.c, new AI0(i)), new EI0(null));
        hi0.d(context.getResources(), false);
        return hi0;
    }

    public void d(Resources resources, boolean z) {
        int color = resources.getColor(z ? R.color.f11250_resource_name_obfuscated_RES_2131099815 : R.color.f11230_resource_name_obfuscated_RES_2131099813);
        if (this.K.b != color) {
            int alpha = getAlpha();
            GI0 gi0 = this.K;
            gi0.f8083a = color;
            gi0.b = color;
            setAlpha(alpha);
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        this.G.setColor(this.K.f8083a);
        GI0 gi0 = this.K;
        gi0.f.b(this, this.G, canvas, gi0.d);
    }

    public int getAlpha() {
        return this.K.f8083a >>> 24;
    }

    public Drawable.ConstantState getConstantState() {
        return this.K;
    }

    public int getOpacity() {
        return -3;
    }

    public boolean isRunning() {
        return this.M;
    }

    public Drawable mutate() {
        if (!this.L && super.mutate() == this) {
            this.K = new GI0(this.K);
            this.L = true;
        }
        return this;
    }

    public void setAlpha(int i) {
        GI0 gi0 = this.K;
        int i2 = gi0.b;
        int i3 = ((((i2 >>> 24) * (i + (i >> 7))) >> 8) << 24) | ((i2 << 8) >>> 8);
        if (gi0.f8083a != i3) {
            gi0.f8083a = i3;
            invalidateSelf();
        }
    }

    public void setBounds(int i, int i2, int i3, int i4) {
        this.I.set(i, i2, i3, i4);
        Rect rect = this.f8150J;
        Rect rect2 = this.H;
        rect.set(i + rect2.left, i2 + rect2.top, i3 - rect2.right, i4 - rect2.bottom);
        Rect rect3 = this.f8150J;
        super.setBounds(rect3.left, rect3.top, rect3.right, rect3.bottom);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.G.setColorFilter(colorFilter);
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!z) {
            stop();
        } else if (visible || z2) {
            start();
        }
        return visible;
    }

    public void start() {
        if (this.M) {
            unscheduleSelf(this.F);
            scheduleSelf(this.F, SystemClock.uptimeMillis() + 16);
            return;
        }
        this.M = true;
        GI0 gi0 = this.K;
        if (gi0.c == 0) {
            gi0.c = SystemClock.uptimeMillis();
            this.N = this.K.c;
        }
        this.F.run();
    }

    public void stop() {
        this.M = false;
        this.K.c = 0;
        unscheduleSelf(this.F);
    }
}
