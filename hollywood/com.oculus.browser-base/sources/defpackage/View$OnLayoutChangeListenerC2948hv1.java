package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.SystemClock;
import android.view.View;
import org.chromium.base.TraceEvent;

/* renamed from: hv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC2948hv1 extends HJ implements View.OnLayoutChangeListener {
    public final View F;
    public final Rect G;
    public Bitmap H;
    public Rect I = new Rect();

    /* renamed from: J  reason: collision with root package name */
    public float f10109J = 1.0f;
    public long K;

    public View$OnLayoutChangeListenerC2948hv1(View view) {
        Rect rect = new Rect();
        this.G = rect;
        this.F = view;
        view.addOnLayoutChangeListener(this);
        rect.set(0, 0, view.getWidth(), view.getHeight());
    }

    @Override // defpackage.AbstractC3197jM0
    public Rect a() {
        return this.I;
    }

    @Override // defpackage.AbstractC3197jM0
    public final C3783mp0 b() {
        return null;
    }

    @Override // defpackage.AbstractC3197jM0
    public Bitmap c() {
        if (this.K > 0) {
            AbstractC3364kK0.i("ViewResourceAdapter.GetBitmapInterval", SystemClock.elapsedRealtime() - this.K);
        }
        Rect rect = null;
        TraceEvent.Y("ViewResourceAdapter:getBitmap", null);
        int width = (int) (((float) this.F.getWidth()) * this.f10109J);
        int height = (int) (((float) this.F.getHeight()) * this.f10109J);
        boolean z = width == 0 || height == 0;
        if (z) {
            width = 1;
            height = 1;
        }
        Bitmap bitmap = this.H;
        if (!(bitmap == null || (bitmap.getWidth() == width && this.H.getHeight() == height))) {
            this.H.recycle();
            this.H = null;
        }
        if (this.H == null) {
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            this.H = createBitmap;
            createBitmap.setHasAlpha(true);
            this.I.set(0, 0, this.F.getWidth(), this.F.getHeight());
            this.G.set(this.I);
        }
        if (!z) {
            Canvas canvas = new Canvas(this.H);
            if (!this.G.isEmpty()) {
                rect = this.G;
            }
            h(canvas, rect);
            if (!this.G.isEmpty()) {
                canvas.clipRect(this.G);
            }
            canvas.save();
            float f = this.f10109J;
            canvas.scale(f, f);
            this.F.draw(canvas);
            canvas.restore();
            g();
        } else {
            this.H.setPixel(0, 0, 0);
        }
        this.G.setEmpty();
        TraceEvent.f0("ViewResourceAdapter:getBitmap");
        this.K = SystemClock.elapsedRealtime();
        return this.H;
    }

    @Override // defpackage.AbstractC3197jM0
    public long d() {
        return AbstractC4052oM0.a(null);
    }

    @Override // defpackage.HJ
    public boolean e() {
        return !this.G.isEmpty();
    }

    public void f(Rect rect) {
        if (rect == null) {
            this.G.set(0, 0, this.F.getWidth(), this.F.getHeight());
        } else {
            this.G.union(rect);
        }
    }

    public void g() {
    }

    public void h(Canvas canvas, Rect rect) {
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int i9 = i3 - i;
        int i10 = i4 - i2;
        int i11 = i8 - i6;
        if (i9 != i7 - i5 || i10 != i11) {
            this.G.set(0, 0, i9, i10);
        }
    }
}
