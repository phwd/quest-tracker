package org.chromium.components.browser_ui.bottomsheet;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BottomSheet extends FrameLayout implements AbstractC0758Mj, View.OnLayoutChangeListener {
    public static Callback F;
    public final Interpolator G = new DecelerateInterpolator(1.0f);
    public final C1322Vq0 H = new C1322Vq0();
    public final Rect I = new Rect();

    /* renamed from: J  reason: collision with root package name */
    public final int[] f10813J = new int[2];
    public final float K = ((float) getResources().getDimensionPixelSize(R.dimen.f16860_resource_name_obfuscated_RES_2131165305));
    public final int L = getResources().getDimensionPixelOffset(R.dimen.f16890_resource_name_obfuscated_RES_2131165308);
    public ViewGroup M;
    public C0819Nj N;
    public ValueAnimator O;
    public int P;
    public int Q;
    public int R;
    public float S = -1.0f;
    public float T;
    public int U = 0;
    public int V = -1;
    public int W = -1;
    public AbstractC4277pj a0;
    public TouchRestrictingFrameLayout b0;
    public float c0;
    public TouchRestrictingFrameLayout d0;
    public View e0;
    public boolean f0;
    public boolean g0;
    public boolean h0;
    public float i0;
    public X j0;
    public int k0;

    public BottomSheet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.N = new C0819Nj(context, this);
        this.f0 = true;
    }

    public final void a() {
        ValueAnimator valueAnimator = this.O;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            this.O = null;
        }
    }

    public final void b() {
        if (this.S == -1.0f) {
            this.a0.g().measure(View.MeasureSpec.makeMeasureSpec(this.R, 1073741824), View.MeasureSpec.makeMeasureSpec(this.Q, Integer.MIN_VALUE));
            this.S = (float) this.a0.g().getMeasuredHeight();
        }
    }

    public final void c(boolean z) {
        int i = this.U;
        if (i != 0 && i != 1) {
            if (r() || this.U != 4) {
                v(this.U, z, 0);
            }
        }
    }

    public float d() {
        AbstractC4277pj pjVar;
        if (this.Q <= 0 || (pjVar = this.a0) == null) {
            return 0.0f;
        }
        float h = pjVar.h();
        if (o()) {
            b();
            return (Math.min((float) this.Q, this.S) + ((float) this.L)) / ((float) this.k0);
        } else if (h == 0.0f) {
            return 1.0f;
        } else {
            return h;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
        if ((r1 == null || r1.u()) != false) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int e(boolean r6, float r7) {
        /*
            r5 = this;
            int r0 = r5.h()
            boolean r1 = r5.p()
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x001c
            pj r1 = r5.a0
            if (r1 == 0) goto L_0x0019
            boolean r1 = r1.u()
            if (r1 == 0) goto L_0x0017
            goto L_0x0019
        L_0x0017:
            r1 = r3
            goto L_0x001a
        L_0x0019:
            r1 = r2
        L_0x001a:
            if (r1 == 0) goto L_0x001d
        L_0x001c:
            r3 = r2
        L_0x001d:
            int r1 = r0 + 1
        L_0x001f:
            r4 = 3
            if (r1 >= r4) goto L_0x0047
            if (r1 != r2) goto L_0x002b
            boolean r4 = r5.q()
            if (r4 != 0) goto L_0x002b
            goto L_0x0044
        L_0x002b:
            r4 = 2
            if (r1 != r4) goto L_0x0031
            if (r3 == 0) goto L_0x0031
            goto L_0x0044
        L_0x0031:
            float r4 = r5.l(r1)
            int r4 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x0043
            float r4 = r5.l(r1)
            int r4 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r4 != 0) goto L_0x0044
            if (r6 != 0) goto L_0x0044
        L_0x0043:
            r0 = r1
        L_0x0044:
            int r1 = r1 + 1
            goto L_0x001f
        L_0x0047:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.bottomsheet.BottomSheet.e(boolean, float):int");
    }

    public float f() {
        return d() * ((float) this.k0);
    }

    public float g() {
        return (z() ? 0.0f : k()) * ((float) this.k0);
    }

    public int h() {
        return (z() || !q()) ? 0 : 1;
    }

    public final float i() {
        AbstractC4277pj pjVar = this.a0;
        if (pjVar == null || !pjVar.t() || !q()) {
            return 0.0f;
        }
        return k() * ((float) this.k0) * this.i0;
    }

    public int j() {
        if (this.a0 == null) {
            return 0;
        }
        if (q()) {
            return 1;
        }
        return p() ? 2 : 3;
    }

    public float k() {
        ViewGroup.LayoutParams layoutParams;
        if (this.Q <= 0 || !q()) {
            return 0.0f;
        }
        AbstractC4277pj pjVar = this.a0;
        if (pjVar != null && pjVar.j() != 0) {
            return ((float) this.a0.j()) / ((float) this.k0);
        }
        View n = n();
        int height = n.getHeight();
        if (height == 0 && (layoutParams = n.getLayoutParams()) != null && (height = layoutParams.height) <= 0) {
            n.measure(View.MeasureSpec.makeMeasureSpec(this.P, 1073741824), View.MeasureSpec.makeMeasureSpec(this.Q, Integer.MIN_VALUE));
            height = n.getMeasuredHeight();
        }
        return ((float) (height + this.L)) / ((float) this.k0);
    }

    public final float l(int i) {
        if (o() && i == 3) {
            b();
        }
        float f = 0.0f;
        if (i != 0) {
            if (i == 1) {
                f = k();
            } else if (i != 2) {
                if (i == 3) {
                    f = d();
                } else {
                    throw new IllegalArgumentException(AbstractC2531fV.w("Invalid state: ", i));
                }
            } else if (this.Q > 0 && p()) {
                float i2 = this.a0.i();
                if (i2 == 0.0f) {
                    i2 = 0.75f;
                }
                f = i2;
            }
        }
        return f * ((float) this.k0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00a2, code lost:
        if (r9 > r5) goto L_0x00a4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int m(float r9, float r10) {
        /*
        // Method dump skipped, instructions count: 169
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.bottomsheet.BottomSheet.m(float, float):int");
    }

    public final View n() {
        AbstractC4277pj pjVar = this.a0;
        if (pjVar == null || pjVar.p() == null) {
            return this.e0;
        }
        return this.a0.p();
    }

    public final boolean o() {
        AbstractC4277pj pjVar = this.a0;
        return pjVar != null && pjVar.h() == -1.0f;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = true;
        if (!(motionEvent.getY() > ((float) this.L)) && motionEvent.getActionMasked() == 0) {
            return false;
        }
        if (!this.f0) {
            return true;
        }
        if (this.O == null || this.V != 0) {
            z = false;
        }
        if (z) {
            return false;
        }
        C0819Nj nj = this.N;
        GestureDetector gestureDetector = nj.f8570a;
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        obtain.setLocation(motionEvent.getRawX(), motionEvent.getRawY());
        gestureDetector.onTouchEvent(obtain);
        return nj.d;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.R = i3 - i;
        this.S = -1.0f;
        c(true);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(i2) + this.L, o() ? Integer.MIN_VALUE : 1073741824));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!(motionEvent.getY() > ((float) this.L)) && motionEvent.getActionMasked() == 0) {
            return false;
        }
        if (!this.f0) {
            return true;
        }
        C0819Nj nj = this.N;
        Objects.requireNonNull(nj);
        if (motionEvent.getActionMasked() != 0) {
            GestureDetector gestureDetector = nj.f8570a;
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setLocation(motionEvent.getRawX(), motionEvent.getRawY());
            gestureDetector.onTouchEvent(obtain);
        }
        if (nj.d && (motionEvent.getActionMasked() == 1 || motionEvent.getActionMasked() == 3)) {
            nj.d = false;
            nj.c.computeCurrentVelocity(1000);
            float f = (((-nj.c.getYVelocity()) * 218.0f) / 2000.0f) + ((BottomSheet) nj.b).T;
            BottomSheet bottomSheet = (BottomSheet) nj.b;
            bottomSheet.t(AbstractC4089od0.b(f, bottomSheet.g(), ((BottomSheet) nj.b).f()), true);
        }
        return true;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            requestLayout();
        }
    }

    public final boolean p() {
        AbstractC4277pj pjVar = this.a0;
        if (pjVar == null) {
            return false;
        }
        if ((((float) this.k0) * 0.25f < this.K) || pjVar.i() == -2.0f || this.a0.h() == -1.0f) {
            return false;
        }
        return true;
    }

    public boolean q() {
        AbstractC4277pj pjVar = this.a0;
        return (pjVar == null || pjVar.j() == -2) ? false : true;
    }

    public final boolean r() {
        return this.O != null;
    }

    public final void s(int i) {
        int i2;
        int i3 = this.U;
        if (i != i3) {
            AbstractC4277pj pjVar = this.a0;
            if (pjVar == null && i != 0) {
                AbstractC1220Ua0.d("BottomSheet", "Content null while open! ", new Object[0]);
                PostTask.b(C3070if1.b, new RunnableC3422kj(new Throwable("This is not a crash. See https://crbug.com/1126872 for details.")), 0);
                v(0, false, 0);
            } else if (i == -1) {
                v(m(this.T, 0.0f), false, 0);
            } else {
                if (i != 4 || i3 == 4) {
                    i3 = -1;
                }
                this.W = i3;
                this.U = i;
                if (i == 2 || i == 3) {
                    if (i == 3) {
                        i2 = pjVar.n();
                    } else {
                        i2 = pjVar.o();
                    }
                    announceForAccessibility(getResources().getString(i2));
                    setFocusable(true);
                    setFocusableInTouchMode(true);
                    String string = getResources().getString(this.a0.m());
                    if (this.a0.v()) {
                        StringBuilder j = AbstractC2531fV.j(string, ". ");
                        j.append(getResources().getString(R.string.f48300_resource_name_obfuscated_RES_2131952147));
                        string = j.toString();
                    }
                    setContentDescription(string);
                    if (getFocusedChild() == null) {
                        requestFocus();
                    }
                }
                Iterator it = this.H.iterator();
                while (true) {
                    C1261Uq0 uq0 = (C1261Uq0) it;
                    if (uq0.hasNext()) {
                        ((AbstractC0576Jj) uq0.next()).i(this.U);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public void t(float f, boolean z) {
        a();
        if (this.a0 != null) {
            if (z) {
                v(m(f, -(this.T - f)), true, 1);
                return;
            }
            s(4);
            u(f, 1, true);
        }
    }

    public void u(float f, int i, boolean z) {
        float f2;
        this.T = f;
        float i2 = i() + (((float) this.Q) - f);
        if (!this.g0 || !AbstractC4089od0.a(i2, getTranslationY())) {
            setTranslationY(i2);
            if (z) {
                int h = h();
                if (q() && (!this.g0 || this.V == 1)) {
                    h = 1;
                }
                float l = l(h);
                boolean a2 = AbstractC4089od0.a(this.T, l);
                boolean z2 = this.T < l;
                boolean z3 = !q() && this.V == 0;
                boolean z4 = this.g0;
                if (!z4 || (!z2 && !a2 && !z3)) {
                    if (!z4 && this.V != 0 && this.T > l && !z4) {
                        this.g0 = true;
                        Iterator it = this.H.iterator();
                        while (true) {
                            C1261Uq0 uq0 = (C1261Uq0) it;
                            if (!uq0.hasNext()) {
                                break;
                            }
                            ((AbstractC0576Jj) uq0.next()).g(i);
                        }
                    }
                } else if (z4) {
                    this.g0 = false;
                    Iterator it2 = this.H.iterator();
                    while (true) {
                        C1261Uq0 uq02 = (C1261Uq0) it2;
                        if (!uq02.hasNext()) {
                            break;
                        }
                        ((AbstractC0576Jj) uq02.next()).k(i);
                    }
                    if (this.a0 != null) {
                        announceForAccessibility(getResources().getString(this.a0.l()));
                    }
                    clearFocus();
                    setFocusable(false);
                    setFocusableInTouchMode(false);
                    setContentDescription(null);
                }
            }
            float i3 = this.T - i();
            float f3 = 0.0f;
            if (i3 > l(0) || this.c0 > 0.0f) {
                int i4 = this.Q;
                float f4 = i4 > 0 ? i3 / ((float) i4) : 0.0f;
                float d = d() - 0.0f;
                if (d == 0.0f) {
                    f2 = 0.0f;
                } else {
                    f2 = AbstractC4089od0.b((f4 - 0.0f) / d, 0.0f, 1.0f);
                }
                if (i3 < l(0)) {
                    this.c0 = 0.0f;
                } else {
                    if (!AbstractC4089od0.a(f2, 0.0f)) {
                        f3 = f2;
                    }
                    this.c0 = f3;
                }
                Iterator it3 = this.H.iterator();
                while (true) {
                    C1261Uq0 uq03 = (C1261Uq0) it3;
                    if (!uq03.hasNext()) {
                        break;
                    }
                    ((AbstractC0576Jj) uq03.next()).f(this.c0, this.T);
                }
                if (q() && AbstractC4089od0.a(i3, l(1))) {
                    Iterator it4 = this.H.iterator();
                    while (true) {
                        C1261Uq0 uq04 = (C1261Uq0) it4;
                        if (uq04.hasNext()) {
                            ((AbstractC0576Jj) uq04.next()).j();
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    public void v(int i, boolean z, int i2) {
        StringBuilder i3 = AbstractC2531fV.i("Setting sheet state: state: ");
        i3.append(this.U);
        i3.append(", content null: ");
        boolean z2 = true;
        i3.append(this.a0 == null);
        AbstractC1220Ua0.d("BottomSheet", i3.toString(), new Object[0]);
        if (i != 4) {
            if (i == 2 && !p()) {
                i = 3;
            }
            a();
            this.V = i;
            if (!z || i == this.U) {
                u(l(i), i2, true);
                s(this.V);
                this.V = -1;
                return;
            }
            this.V = i;
            ValueAnimator ofFloat = ValueAnimator.ofFloat(this.T, l(i));
            this.O = ofFloat;
            ofFloat.setDuration(218L);
            this.O.setInterpolator(this.G);
            this.O.addListener(new C3935nj(this, i, i2));
            this.O.addUpdateListener(new C4106oj(this, i2));
            StringBuilder sb = new StringBuilder();
            sb.append("Starting settle animation: target: ");
            sb.append(i);
            sb.append(", content null: ");
            if (this.a0 != null) {
                z2 = false;
            }
            sb.append(z2);
            AbstractC1220Ua0.d("BottomSheet", sb.toString(), new Object[0]);
            s(4);
            this.O.start();
        }
    }

    public boolean w(MotionEvent motionEvent, MotionEvent motionEvent2) {
        if (this.T < l(1) || i() > 0.0f) {
            return false;
        }
        if (this.g0 || this.j0.d()) {
            return true;
        }
        float width = (float) (n().getWidth() + this.I.left);
        if (motionEvent2.getRawX() <= ((float) this.I.left) || motionEvent2.getRawX() >= width) {
            return false;
        }
        return true;
    }

    public void x(AbstractC4277pj pjVar) {
        if (this.a0 != pjVar) {
            StringBuilder i = AbstractC2531fV.i("Setting sheet content: state: ");
            i.append(this.U);
            i.append(", content null: ");
            i.append(pjVar == null);
            AbstractC1220Ua0.d("BottomSheet", i.toString(), new Object[0]);
            if (pjVar == null) {
                Thread.dumpStack();
            }
            AbstractC4277pj pjVar2 = this.a0;
            View view = null;
            if (pjVar2 != null) {
                pjVar2.g().removeOnLayoutChangeListener(this);
            }
            if (pjVar != null && getParent() == null) {
                this.M.addView(this);
            } else if (pjVar == null) {
                if (this.M.getParent() != null) {
                    this.M.removeView(this);
                } else {
                    throw new RuntimeException("Attempting to detach sheet that was not in the hierarchy!");
                }
            }
            View g = pjVar != null ? pjVar.g() : null;
            AbstractC4277pj pjVar3 = this.a0;
            y(g, pjVar3 != null ? pjVar3.g() : null, this.b0);
            View p = pjVar != null ? pjVar.p() : null;
            AbstractC4277pj pjVar4 = this.a0;
            if (pjVar4 != null) {
                view = pjVar4.p();
            }
            y(p, view, this.d0);
            this.e0.setVisibility(p != null ? 8 : 0);
            this.a0 = pjVar;
            if (pjVar != null && o()) {
                pjVar.g().addOnLayoutChangeListener(this);
                this.S = -1.0f;
                c(true);
                if (this.U == 2) {
                    v(3, true, 0);
                }
            }
            Iterator it = this.H.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((AbstractC0576Jj) uq0.next()).h(pjVar);
                } else {
                    this.d0.setBackgroundColor(0);
                    return;
                }
            }
        }
    }

    public final void y(View view, View view2, ViewGroup viewGroup) {
        if (!(view2 == null || view2.getParent() == null)) {
            viewGroup.removeView(view2);
        }
        if (view != null && viewGroup != view.getParent()) {
            viewGroup.addView(view);
        }
    }

    public final boolean z() {
        AbstractC4277pj pjVar = this.a0;
        if (pjVar != null) {
            return pjVar.v();
        }
        return true;
    }
}
