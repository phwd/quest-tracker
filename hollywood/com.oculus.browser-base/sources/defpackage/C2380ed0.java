package defpackage;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* renamed from: ed0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2380ed0 {

    /* renamed from: a  reason: collision with root package name */
    public final RectF f9867a = new RectF();
    public final Paint b;
    public final Paint c;
    public final Drawable.Callback d;
    public float e;
    public float f;
    public float g;
    public float h;
    public float i;
    public int[] j;
    public int k;
    public float l;
    public float m;
    public float n;
    public boolean o;
    public Path p;
    public float q;
    public double r;
    public int s;
    public int t;
    public int u;
    public final Paint v;
    public int w;
    public int x;

    public C2380ed0(Drawable.Callback callback) {
        Paint paint = new Paint();
        this.b = paint;
        Paint paint2 = new Paint();
        this.c = paint2;
        this.e = 0.0f;
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 5.0f;
        this.i = 2.5f;
        this.v = new Paint();
        this.d = callback;
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setAntiAlias(true);
    }

    public final void a() {
        this.d.invalidateDrawable(null);
    }

    public void b() {
        this.l = 0.0f;
        this.m = 0.0f;
        this.n = 0.0f;
        this.e = 0.0f;
        a();
        this.f = 0.0f;
        a();
        this.g = 0.0f;
        a();
    }

    public void c(int i2) {
        this.k = i2;
        this.x = this.j[i2];
    }

    public void d(boolean z) {
        if (this.o != z) {
            this.o = z;
            a();
        }
    }
}
