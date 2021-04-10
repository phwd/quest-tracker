package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import java.util.BitSet;
import java.util.Objects;

/* renamed from: jd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3234jd0 extends Drawable implements AbstractC5258vT0 {
    public static final String F = C3234jd0.class.getSimpleName();
    public static final Paint G = new Paint(1);
    public C3064id0 H;
    public final AbstractC4918tT0[] I;

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC4918tT0[] f10220J;
    public final BitSet K;
    public boolean L;
    public final Matrix M;
    public final Path N;
    public final Path O;
    public final RectF P;
    public final RectF Q;
    public final Region R;
    public final Region S;
    public C3553lT0 T;
    public final Paint U;
    public final Paint V;
    public final C3041iT0 W;
    public final C2893hd0 X;
    public final C3724mT0 Y;
    public PorterDuffColorFilter Z;
    public PorterDuffColorFilter a0;
    public final RectF b0;
    public boolean c0;

    public C3234jd0() {
        this(new C3553lT0());
    }

    @Override // defpackage.AbstractC5258vT0
    public void a(C3553lT0 lt0) {
        this.H.f10150a = lt0;
        invalidateSelf();
    }

    public final void b(RectF rectF, Path path) {
        c(rectF, path);
        if (this.H.j != 1.0f) {
            this.M.reset();
            Matrix matrix = this.M;
            float f = this.H.j;
            matrix.setScale(f, f, rectF.width() / 2.0f, rectF.height() / 2.0f);
            path.transform(this.M);
        }
        path.computeBounds(this.b0, true);
    }

    public final void c(RectF rectF, Path path) {
        C3724mT0 mt0 = this.Y;
        C3064id0 id0 = this.H;
        mt0.a(id0.f10150a, id0.k, rectF, this.X, path);
    }

    public final PorterDuffColorFilter d(ColorStateList colorStateList, PorterDuff.Mode mode, Paint paint, boolean z) {
        int color;
        int e;
        if (colorStateList == null || mode == null) {
            return (!z || (e = e((color = paint.getColor()))) == color) ? null : new PorterDuffColorFilter(e, PorterDuff.Mode.SRC_IN);
        }
        int colorForState = colorStateList.getColorForState(getState(), 0);
        if (z) {
            colorForState = e(colorForState);
        }
        return new PorterDuffColorFilter(colorForState, mode);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00ef, code lost:
        if ((!r2.f10150a.d(h()) && !r12.N.isConvex() && android.os.Build.VERSION.SDK_INT < 29) != false) goto L_0x00f1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00f8  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x019b  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void draw(android.graphics.Canvas r13) {
        /*
        // Method dump skipped, instructions count: 482
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3234jd0.draw(android.graphics.Canvas):void");
    }

    public final int e(int i) {
        C3064id0 id0 = this.H;
        float f = id0.o + id0.p + id0.n;
        EK ek = id0.b;
        if (ek == null || !ek.f7954a) {
            return i;
        }
        if (!(AbstractC1331Vv.h(i, 255) == ek.c)) {
            return i;
        }
        float f2 = ek.d;
        float f3 = 0.0f;
        if (f2 > 0.0f && f > 0.0f) {
            f3 = Math.min(((((float) Math.log1p((double) (f / f2))) * 4.5f) + 2.0f) / 100.0f, 1.0f);
        }
        return AbstractC1331Vv.h(AbstractC1226Uc0.c(AbstractC1331Vv.h(i, 255), ek.b, f3), Color.alpha(i));
    }

    public final void f(Canvas canvas) {
        if (this.K.cardinality() > 0) {
            Log.w(F, "Compatibility shadow requested but can't be drawn for all operations in this shape.");
        }
        if (this.H.s != 0) {
            canvas.drawPath(this.N, this.W.e);
        }
        for (int i = 0; i < 4; i++) {
            AbstractC4918tT0 tt0 = this.I[i];
            C3041iT0 it0 = this.W;
            int i2 = this.H.r;
            Matrix matrix = AbstractC4918tT0.f11344a;
            tt0.a(matrix, it0, i2, canvas);
            this.f10220J[i].a(matrix, this.W, this.H.r, canvas);
        }
        if (this.c0) {
            int i3 = i();
            int j = j();
            canvas.translate((float) (-i3), (float) (-j));
            canvas.drawPath(this.N, G);
            canvas.translate((float) i3, (float) j);
        }
    }

    public final void g(Canvas canvas, Paint paint, Path path, C3553lT0 lt0, RectF rectF) {
        if (lt0.d(rectF)) {
            float a2 = lt0.f.a(rectF) * this.H.k;
            canvas.drawRoundRect(rectF, a2, a2, paint);
            return;
        }
        canvas.drawPath(path, paint);
    }

    public Drawable.ConstantState getConstantState() {
        return this.H;
    }

    public int getOpacity() {
        return -3;
    }

    public void getOutline(Outline outline) {
        C3064id0 id0 = this.H;
        if (id0.q != 2) {
            if (id0.f10150a.d(h())) {
                outline.setRoundRect(getBounds(), this.H.f10150a.e.a(h()) * this.H.k);
                return;
            }
            b(h(), this.N);
            if (this.N.isConvex() || Build.VERSION.SDK_INT >= 29) {
                outline.setConvexPath(this.N);
            }
        }
    }

    public boolean getPadding(Rect rect) {
        Rect rect2 = this.H.i;
        if (rect2 == null) {
            return super.getPadding(rect);
        }
        rect.set(rect2);
        return true;
    }

    public Region getTransparentRegion() {
        this.R.set(getBounds());
        b(h(), this.N);
        this.S.setPath(this.N, this.R);
        this.R.op(this.S, Region.Op.DIFFERENCE);
        return this.R;
    }

    public RectF h() {
        this.P.set(getBounds());
        return this.P;
    }

    public int i() {
        C3064id0 id0 = this.H;
        return (int) (Math.sin(Math.toRadians((double) id0.t)) * ((double) id0.s));
    }

    public void invalidateSelf() {
        this.L = true;
        super.invalidateSelf();
    }

    public boolean isStateful() {
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        ColorStateList colorStateList3;
        ColorStateList colorStateList4;
        return super.isStateful() || ((colorStateList = this.H.g) != null && colorStateList.isStateful()) || (((colorStateList2 = this.H.f) != null && colorStateList2.isStateful()) || (((colorStateList3 = this.H.e) != null && colorStateList3.isStateful()) || ((colorStateList4 = this.H.d) != null && colorStateList4.isStateful())));
    }

    public int j() {
        C3064id0 id0 = this.H;
        return (int) (Math.cos(Math.toRadians((double) id0.t)) * ((double) id0.s));
    }

    public final float k() {
        if (l()) {
            return this.V.getStrokeWidth() / 2.0f;
        }
        return 0.0f;
    }

    public final boolean l() {
        Paint.Style style = this.H.v;
        return (style == Paint.Style.FILL_AND_STROKE || style == Paint.Style.STROKE) && this.V.getStrokeWidth() > 0.0f;
    }

    public void m(Context context) {
        this.H.b = new EK(context);
        s();
    }

    public Drawable mutate() {
        this.H = new C3064id0(this.H);
        return this;
    }

    public void n(float f) {
        C3064id0 id0 = this.H;
        if (id0.o != f) {
            id0.o = f;
            s();
        }
    }

    public void o(ColorStateList colorStateList) {
        C3064id0 id0 = this.H;
        if (id0.d != colorStateList) {
            id0.d = colorStateList;
            onStateChange(getState());
        }
    }

    public void onBoundsChange(Rect rect) {
        this.L = true;
        super.onBoundsChange(rect);
    }

    public boolean onStateChange(int[] iArr) {
        boolean z = q(iArr) || r();
        if (z) {
            invalidateSelf();
        }
        return z;
    }

    public void p(ColorStateList colorStateList) {
        C3064id0 id0 = this.H;
        if (id0.e != colorStateList) {
            id0.e = colorStateList;
            onStateChange(getState());
        }
    }

    public final boolean q(int[] iArr) {
        boolean z;
        int color;
        int colorForState;
        int color2;
        int colorForState2;
        if (this.H.d == null || color2 == (colorForState2 = this.H.d.getColorForState(iArr, (color2 = this.U.getColor())))) {
            z = false;
        } else {
            this.U.setColor(colorForState2);
            z = true;
        }
        if (this.H.e == null || color == (colorForState = this.H.e.getColorForState(iArr, (color = this.V.getColor())))) {
            return z;
        }
        this.V.setColor(colorForState);
        return true;
    }

    public final boolean r() {
        PorterDuffColorFilter porterDuffColorFilter = this.Z;
        PorterDuffColorFilter porterDuffColorFilter2 = this.a0;
        C3064id0 id0 = this.H;
        this.Z = d(id0.g, id0.h, this.U, true);
        C3064id0 id02 = this.H;
        this.a0 = d(id02.f, id02.h, this.V, false);
        C3064id0 id03 = this.H;
        if (id03.u) {
            this.W.a(id03.g.getColorForState(getState(), 0));
        }
        if (!Objects.equals(porterDuffColorFilter, this.Z) || !Objects.equals(porterDuffColorFilter2, this.a0)) {
            return true;
        }
        return false;
    }

    public final void s() {
        C3064id0 id0 = this.H;
        float f = id0.o + id0.p;
        id0.r = (int) Math.ceil((double) (0.75f * f));
        this.H.s = (int) Math.ceil((double) (f * 0.25f));
        r();
        super.invalidateSelf();
    }

    public void setAlpha(int i) {
        C3064id0 id0 = this.H;
        if (id0.m != i) {
            id0.m = i;
            super.invalidateSelf();
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.H.c = colorFilter;
        super.invalidateSelf();
    }

    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(ColorStateList colorStateList) {
        this.H.g = colorStateList;
        r();
        super.invalidateSelf();
    }

    public void setTintMode(PorterDuff.Mode mode) {
        C3064id0 id0 = this.H;
        if (id0.h != mode) {
            id0.h = mode;
            r();
            super.invalidateSelf();
        }
    }

    public C3234jd0(C3553lT0 lt0) {
        this(new C3064id0(lt0, null));
    }

    public C3234jd0(C3064id0 id0) {
        this.I = new AbstractC4918tT0[4];
        this.f10220J = new AbstractC4918tT0[4];
        this.K = new BitSet(8);
        this.M = new Matrix();
        this.N = new Path();
        this.O = new Path();
        this.P = new RectF();
        this.Q = new RectF();
        this.R = new Region();
        this.S = new Region();
        Paint paint = new Paint(1);
        this.U = paint;
        Paint paint2 = new Paint(1);
        this.V = paint2;
        this.W = new C3041iT0();
        this.Y = new C3724mT0();
        this.b0 = new RectF();
        this.c0 = true;
        this.H = id0;
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        Paint paint3 = G;
        paint3.setColor(-1);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        r();
        q(getState());
        this.X = new C2893hd0(this);
    }
}
