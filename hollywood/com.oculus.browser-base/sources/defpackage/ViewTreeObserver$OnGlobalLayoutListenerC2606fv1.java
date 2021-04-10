package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: fv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewTreeObserver$OnGlobalLayoutListenerC2606fv1 extends C4390qK0 implements ViewTreeObserver.OnGlobalLayoutListener, View.OnAttachStateChangeListener, ViewTreeObserver.OnPreDrawListener {
    public final int[] H;
    public final Rect I = new Rect();

    /* renamed from: J  reason: collision with root package name */
    public final View f9965J;
    public ViewTreeObserver K;
    public boolean L;

    public ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(View view) {
        int[] iArr = new int[2];
        this.H = iArr;
        this.f9965J = view;
        iArr[0] = -1;
        iArr[1] = -1;
    }

    @Override // defpackage.C4390qK0
    public void b(AbstractC4219pK0 pk0) {
        this.f9965J.addOnAttachStateChangeListener(this);
        ViewTreeObserver viewTreeObserver = this.f9965J.getViewTreeObserver();
        this.K = viewTreeObserver;
        viewTreeObserver.addOnGlobalLayoutListener(this);
        this.K.addOnPreDrawListener(this);
        d();
        this.G = pk0;
    }

    @Override // defpackage.C4390qK0
    public void c() {
        this.f9965J.removeOnAttachStateChangeListener(this);
        ViewTreeObserver viewTreeObserver = this.K;
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            this.K.removeOnGlobalLayoutListener(this);
            this.K.removeOnPreDrawListener(this);
        }
        this.K = null;
        this.G = null;
    }

    public final void d() {
        int i;
        int i2;
        int[] iArr = this.H;
        boolean z = false;
        int i3 = iArr[0];
        int i4 = iArr[1];
        this.f9965J.getLocationInWindow(iArr);
        int[] iArr2 = this.H;
        iArr2[0] = Math.max(iArr2[0], 0);
        int[] iArr3 = this.H;
        iArr3[1] = Math.max(iArr3[1], 0);
        int[] iArr4 = this.H;
        if (iArr4[0] != i3 || iArr4[1] != i4) {
            Rect rect = this.F;
            int i5 = iArr4[0];
            rect.left = i5;
            rect.top = iArr4[1];
            rect.right = this.f9965J.getWidth() + i5;
            Rect rect2 = this.F;
            rect2.bottom = this.f9965J.getHeight() + rect2.top;
            Rect rect3 = this.F;
            int i6 = rect3.left;
            Rect rect4 = this.I;
            rect3.left = i6 + rect4.left;
            rect3.top += rect4.top;
            rect3.right -= rect4.right;
            rect3.bottom -= rect4.bottom;
            if (!this.L) {
                if (this.f9965J.getLayoutDirection() == 1) {
                    z = true;
                }
                Rect rect5 = this.F;
                int i7 = rect5.left;
                View view = this.f9965J;
                if (z) {
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    i = view.getPaddingEnd();
                } else {
                    AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                    i = view.getPaddingStart();
                }
                rect5.left = i7 + i;
                Rect rect6 = this.F;
                int i8 = rect6.right;
                if (z) {
                    i2 = this.f9965J.getPaddingStart();
                } else {
                    i2 = this.f9965J.getPaddingEnd();
                }
                rect6.right = i8 - i2;
                Rect rect7 = this.F;
                rect7.top = this.f9965J.getPaddingTop() + rect7.top;
                this.F.bottom -= this.f9965J.getPaddingBottom();
            }
            Rect rect8 = this.F;
            rect8.right = Math.max(rect8.left, rect8.right);
            Rect rect9 = this.F;
            rect9.bottom = Math.max(rect9.top, rect9.bottom);
            Rect rect10 = this.F;
            rect10.right = Math.min(rect10.right, this.f9965J.getRootView().getWidth());
            Rect rect11 = this.F;
            rect11.bottom = Math.min(rect11.bottom, this.f9965J.getRootView().getHeight());
            AbstractC4219pK0 pk0 = this.G;
            if (pk0 != null) {
                pk0.a();
            }
        }
    }

    public void e(int i, int i2, int i3, int i4) {
        this.I.set(i, i2, i3, i4);
        d();
    }

    public void onGlobalLayout() {
        if (!this.f9965J.isShown()) {
            a();
        }
    }

    public boolean onPreDraw() {
        if (!this.f9965J.isShown()) {
            a();
            return true;
        }
        d();
        return true;
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        a();
    }
}
