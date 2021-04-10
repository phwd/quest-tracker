package defpackage;

import android.view.ViewGroup;

/* renamed from: B6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B6 extends AbstractC0705Lm0 {
    public final Runnable G = new RunnableC5878z6(this);
    public final A6 H;
    public float I;

    public B6(ViewGroup viewGroup) {
        super(viewGroup);
        A6 a6 = new A6(viewGroup.getContext());
        this.H = a6;
        a6.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    }

    @Override // defpackage.AbstractC0705Lm0
    public void b(float f) {
        this.I = f;
        this.H.F.onPull((-(f - this.I)) / ((float) this.F.getWidth()));
    }

    @Override // defpackage.AbstractC0705Lm0
    public void d(float f, float f2) {
        this.F.removeCallbacks(this.G);
        if (this.H.getParent() == null) {
            this.F.addView(this.H);
        }
    }

    @Override // defpackage.AbstractC0705Lm0
    public void f() {
        this.H.F.onRelease();
        this.H.postInvalidateOnAnimation();
        if (this.H.getParent() != null) {
            this.F.postDelayed(this.G, 500);
        }
        this.I = 0.0f;
    }

    @Override // defpackage.AbstractC0705Lm0
    public void g() {
        f();
    }
}
