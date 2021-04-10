package defpackage;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: Fv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0356Fv {
    public float A;
    public float B;
    public int[] C;
    public boolean D;
    public final TextPaint E;
    public final TextPaint F;
    public TimeInterpolator G;
    public TimeInterpolator H;
    public float I;

    /* renamed from: J  reason: collision with root package name */
    public float f8049J;
    public float K;
    public ColorStateList L;
    public StaticLayout M;
    public float N;
    public float O;
    public float P;
    public CharSequence Q;

    /* renamed from: a  reason: collision with root package name */
    public final View f8050a;
    public boolean b;
    public float c;
    public final Rect d;
    public final Rect e;
    public final RectF f;
    public int g = 16;
    public int h = 16;
    public float i = 15.0f;
    public float j = 15.0f;
    public ColorStateList k;
    public ColorStateList l;
    public float m;
    public float n;
    public float o;
    public float p;
    public float q;
    public float r;
    public Typeface s;
    public Typeface t;
    public Typeface u;
    public C2577fm v;
    public CharSequence w;
    public CharSequence x;
    public boolean y;
    public Bitmap z;

    public C0356Fv(View view) {
        this.f8050a = view;
        TextPaint textPaint = new TextPaint(129);
        this.E = textPaint;
        this.F = new TextPaint(textPaint);
        this.e = new Rect();
        this.d = new Rect();
        this.f = new RectF();
    }

    public static int a(int i2, int i3, float f2) {
        float f3 = 1.0f - f2;
        return Color.argb((int) ((((float) Color.alpha(i3)) * f2) + (((float) Color.alpha(i2)) * f3)), (int) ((((float) Color.red(i3)) * f2) + (((float) Color.red(i2)) * f3)), (int) ((((float) Color.green(i3)) * f2) + (((float) Color.green(i2)) * f3)), (int) ((((float) Color.blue(i3)) * f2) + (((float) Color.blue(i2)) * f3)));
    }

    public static float h(float f2, float f3, float f4, TimeInterpolator timeInterpolator) {
        if (timeInterpolator != null) {
            f4 = timeInterpolator.getInterpolation(f4);
        }
        TimeInterpolator timeInterpolator2 = P6.f8667a;
        return AbstractC2531fV.a(f3, f2, f4, f2);
    }

    public static boolean k(Rect rect, int i2, int i3, int i4, int i5) {
        return rect.left == i2 && rect.top == i3 && rect.right == i4 && rect.bottom == i5;
    }

    public float b() {
        if (this.w == null) {
            return 0.0f;
        }
        TextPaint textPaint = this.F;
        textPaint.setTextSize(this.j);
        textPaint.setTypeface(this.s);
        TextPaint textPaint2 = this.F;
        CharSequence charSequence = this.w;
        return textPaint2.measureText(charSequence, 0, charSequence.length());
    }

    public final boolean c(CharSequence charSequence) {
        View view = this.f8050a;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        boolean z2 = true;
        if (view.getLayoutDirection() != 1) {
            z2 = false;
        }
        return (z2 ? AbstractC3244jg1.d : AbstractC3244jg1.c).b(charSequence, 0, charSequence.length());
    }

    public final void d(float f2) {
        this.f.left = h((float) this.d.left, (float) this.e.left, f2, this.G);
        this.f.top = h(this.m, this.n, f2, this.G);
        this.f.right = h((float) this.d.right, (float) this.e.right, f2, this.G);
        this.f.bottom = h((float) this.d.bottom, (float) this.e.bottom, f2, this.G);
        this.q = h(this.o, this.p, f2, this.G);
        this.r = h(this.m, this.n, f2, this.G);
        o(h(this.i, this.j, f2, this.H));
        TimeInterpolator timeInterpolator = P6.b;
        this.N = 1.0f - h(0.0f, 1.0f, 1.0f - f2, timeInterpolator);
        View view = this.f8050a;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        view.postInvalidateOnAnimation();
        this.O = h(1.0f, 0.0f, f2, timeInterpolator);
        this.f8050a.postInvalidateOnAnimation();
        ColorStateList colorStateList = this.l;
        ColorStateList colorStateList2 = this.k;
        if (colorStateList != colorStateList2) {
            this.E.setColor(a(g(colorStateList2), g(this.l), f2));
        } else {
            this.E.setColor(g(colorStateList));
        }
        this.E.setShadowLayer(h(0.0f, this.I, f2, null), h(0.0f, this.f8049J, f2, null), h(0.0f, this.K, f2, null), a(g(null), g(this.L), f2));
        this.f8050a.postInvalidateOnAnimation();
    }

    public final void e(float f2) {
        float f3;
        boolean z2;
        StaticLayout staticLayout;
        if (this.w != null) {
            float width = (float) this.e.width();
            float width2 = (float) this.d.width();
            if (Math.abs(f2 - this.j) < 0.001f) {
                f3 = this.j;
                this.A = 1.0f;
                Typeface typeface = this.u;
                Typeface typeface2 = this.s;
                if (typeface != typeface2) {
                    this.u = typeface2;
                    z2 = true;
                } else {
                    z2 = false;
                }
            } else {
                float f4 = this.i;
                Typeface typeface3 = this.u;
                Typeface typeface4 = this.t;
                if (typeface3 != typeface4) {
                    this.u = typeface4;
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (Math.abs(f2 - f4) < 0.001f) {
                    this.A = 1.0f;
                } else {
                    this.A = f2 / this.i;
                }
                float f5 = this.j / this.i;
                width = width2 * f5 > width ? Math.min(width / f5, width2) : width2;
                f3 = f4;
            }
            if (width > 0.0f) {
                z2 = this.B != f3 || this.D || z2;
                this.B = f3;
                this.D = false;
            }
            if (this.x == null || z2) {
                this.E.setTextSize(this.B);
                this.E.setTypeface(this.u);
                this.E.setLinearText(this.A != 1.0f);
                boolean c2 = c(this.w);
                this.y = c2;
                try {
                    CharSequence charSequence = this.w;
                    TextPaint textPaint = this.E;
                    int length = charSequence.length();
                    Layout.Alignment alignment = Layout.Alignment.ALIGN_NORMAL;
                    TextUtils.TruncateAt truncateAt = TextUtils.TruncateAt.END;
                    Layout.Alignment alignment2 = Layout.Alignment.ALIGN_NORMAL;
                    int max = Math.max(0, (int) width);
                    CharSequence ellipsize = TextUtils.ellipsize(charSequence, textPaint, (float) max, truncateAt);
                    int min = Math.min(ellipsize.length(), length);
                    if (c2) {
                        alignment2 = Layout.Alignment.ALIGN_OPPOSITE;
                    }
                    StaticLayout.Builder obtain = StaticLayout.Builder.obtain(ellipsize, 0, min, textPaint, max);
                    obtain.setAlignment(alignment2);
                    obtain.setIncludePad(false);
                    obtain.setTextDirection(c2 ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR);
                    if (truncateAt != null) {
                        obtain.setEllipsize(truncateAt);
                    }
                    obtain.setMaxLines(1);
                    staticLayout = obtain.build();
                } catch (E11 e2) {
                    Log.e("CollapsingTextHelper", e2.getCause().getMessage(), e2);
                    staticLayout = null;
                }
                Objects.requireNonNull(staticLayout);
                this.M = staticLayout;
                this.x = staticLayout.getText();
            }
        }
    }

    public float f() {
        TextPaint textPaint = this.F;
        textPaint.setTextSize(this.j);
        textPaint.setTypeface(this.s);
        return -this.F.ascent();
    }

    public final int g(ColorStateList colorStateList) {
        if (colorStateList == null) {
            return 0;
        }
        int[] iArr = this.C;
        if (iArr != null) {
            return colorStateList.getColorForState(iArr, 0);
        }
        return colorStateList.getDefaultColor();
    }

    public void i() {
        this.b = this.e.width() > 0 && this.e.height() > 0 && this.d.width() > 0 && this.d.height() > 0;
    }

    public void j() {
        StaticLayout staticLayout;
        if (this.f8050a.getHeight() > 0 && this.f8050a.getWidth() > 0) {
            float f2 = this.B;
            e(this.j);
            CharSequence charSequence = this.x;
            if (!(charSequence == null || (staticLayout = this.M) == null)) {
                this.Q = TextUtils.ellipsize(charSequence, this.E, (float) staticLayout.getWidth(), TextUtils.TruncateAt.END);
            }
            CharSequence charSequence2 = this.Q;
            float measureText = charSequence2 != null ? this.E.measureText(charSequence2, 0, charSequence2.length()) : 0.0f;
            int absoluteGravity = Gravity.getAbsoluteGravity(this.h, this.y ? 1 : 0);
            StaticLayout staticLayout2 = this.M;
            if (staticLayout2 != null) {
                staticLayout2.getHeight();
            }
            int i2 = absoluteGravity & 112;
            if (i2 == 48) {
                this.n = ((float) this.e.top) - this.E.ascent();
            } else if (i2 != 80) {
                this.n = ((float) this.e.centerY()) + (((this.E.descent() - this.E.ascent()) / 2.0f) - this.E.descent());
            } else {
                this.n = (float) this.e.bottom;
            }
            int i3 = absoluteGravity & 8388615;
            if (i3 == 1) {
                this.p = ((float) this.e.centerX()) - (measureText / 2.0f);
            } else if (i3 != 5) {
                this.p = (float) this.e.left;
            } else {
                this.p = ((float) this.e.right) - measureText;
            }
            e(this.i);
            CharSequence charSequence3 = this.x;
            float measureText2 = charSequence3 != null ? this.E.measureText(charSequence3, 0, charSequence3.length()) : 0.0f;
            StaticLayout staticLayout3 = this.M;
            this.P = staticLayout3 != null ? staticLayout3.getLineLeft(0) : 0.0f;
            int absoluteGravity2 = Gravity.getAbsoluteGravity(this.g, this.y ? 1 : 0);
            int i4 = absoluteGravity2 & 112;
            if (i4 == 48) {
                this.m = ((float) this.d.top) - this.E.ascent();
            } else if (i4 != 80) {
                this.m = ((float) this.d.centerY()) + (((this.E.descent() - this.E.ascent()) / 2.0f) - this.E.descent());
            } else {
                this.m = ((float) this.d.bottom) - 0.0f;
            }
            int i5 = absoluteGravity2 & 8388615;
            if (i5 == 1) {
                this.o = ((float) this.d.centerX()) - (measureText2 / 2.0f);
            } else if (i5 != 5) {
                this.o = (float) this.d.left;
            } else {
                this.o = ((float) this.d.right) - measureText2;
            }
            Bitmap bitmap = this.z;
            if (bitmap != null) {
                bitmap.recycle();
                this.z = null;
            }
            o(f2);
            d(this.c);
        }
    }

    public void l(ColorStateList colorStateList) {
        if (this.l != colorStateList) {
            this.l = colorStateList;
            j();
        }
    }

    public void m(int i2) {
        if (this.h != i2) {
            this.h = i2;
            j();
        }
    }

    public void n(float f2) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        if (f2 != this.c) {
            this.c = f2;
            d(f2);
        }
    }

    public final void o(float f2) {
        e(f2);
        View view = this.f8050a;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        view.postInvalidateOnAnimation();
    }
}
