package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: lv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3631lv1 {
    public int F;
    public int G;
    public Context H;
    public ViewGroup I;

    /* renamed from: J  reason: collision with root package name */
    public IJ f10386J;
    public View$OnLayoutChangeListenerC2948hv1 K;
    public View L;
    public boolean M;
    public boolean N;
    public boolean O;
    public ViewTreeObserver$OnDrawListenerC3460kv1 P;

    public AbstractC3631lv1(int i, int i2, Context context, ViewGroup viewGroup, IJ ij) {
        this.F = i;
        this.G = i2;
        this.H = context;
        this.I = viewGroup;
        this.f10386J = ij;
    }

    public void a() {
        if (this.L != null) {
            IJ ij = this.f10386J;
            if (ij != null) {
                ij.d(this.G);
            }
            this.K = null;
            b();
            this.L = null;
            this.F = -1;
            this.G = -1;
            this.H = null;
            this.I = null;
            this.f10386J = null;
        }
    }

    public final void b() {
        if (this.O) {
            if (this.P != null) {
                this.L.getViewTreeObserver().removeOnDrawListener(this.P);
                this.P = null;
            }
            this.I.removeView(this.L);
            this.O = false;
        }
    }

    public int c() {
        return this.L.getMeasuredHeight();
    }

    public int d() {
        return View.MeasureSpec.makeMeasureSpec(0, 0);
    }

    public void e() {
        if (this.L == null) {
            this.L = LayoutInflater.from(this.H).inflate(this.F, this.I, false);
            j();
            if (this.K == null) {
                this.K = new C3289jv1(this, this.L.findViewById(this.G));
            }
            IJ ij = this.f10386J;
            if (ij != null) {
                ij.c.put(this.G, this.K);
            }
            this.M = true;
        }
    }

    public void f(boolean z) {
        if (this.L == null) {
            e();
        }
        this.N = true;
        if (!this.O && k() && !this.O) {
            this.I.addView(this.L);
            this.O = true;
            if (this.P == null) {
                this.P = new ViewTreeObserver$OnDrawListenerC3460kv1(this, null);
                this.L.getViewTreeObserver().addOnDrawListener(this.P);
            }
        }
        if (!this.O) {
            h();
            g();
        } else if (z || this.M) {
            int d = d();
            int i = -2;
            int size = View.MeasureSpec.getMode(d) == 1073741824 ? View.MeasureSpec.getSize(d) : -2;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            if (View.MeasureSpec.getMode(makeMeasureSpec) == 1073741824) {
                i = View.MeasureSpec.getSize(makeMeasureSpec);
            }
            ViewGroup.LayoutParams layoutParams = this.L.getLayoutParams();
            layoutParams.width = size;
            layoutParams.height = i;
            this.L.setLayoutParams(layoutParams);
        }
        this.M = false;
    }

    public final void g() {
        View$OnLayoutChangeListenerC2948hv1 hv1;
        if (this.N && this.L != null && (hv1 = this.K) != null) {
            this.N = false;
            hv1.f(null);
        }
    }

    public void h() {
        this.L.measure(d(), View.MeasureSpec.makeMeasureSpec(0, 0));
        View view = this.L;
        view.layout(0, 0, view.getMeasuredWidth(), c());
    }

    public void i() {
        if (!(this instanceof C3675mA)) {
            b();
        }
    }

    public void j() {
    }

    public boolean k() {
        return !(this instanceof C5176uz);
    }
}
