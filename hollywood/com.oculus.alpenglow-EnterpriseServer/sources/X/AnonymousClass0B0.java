package X;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.lang.ref.WeakReference;

/* renamed from: X.0B0  reason: invalid class name */
public final class AnonymousClass0B0 {
    public WeakReference<View> A00;

    public final void A00() {
        View view = this.A00.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public final void A01(float f) {
        View view = this.A00.get();
        if (view != null) {
            view.animate().alpha(f);
        }
    }

    public final void A02(float f) {
        View view = this.A00.get();
        if (view != null) {
            view.animate().translationY(f);
        }
    }

    public final void A03(long j) {
        View view = this.A00.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
    }

    public final void A04(AnonymousClass0B1 r4) {
        ViewPropertyAnimator animate;
        AnonymousClass0Ay r0;
        View view = this.A00.get();
        if (view != null) {
            if (r4 != null) {
                animate = view.animate();
                r0 = new AnonymousClass0Ay(this, r4, view);
            } else {
                animate = view.animate();
                r0 = null;
            }
            animate.setListener(r0);
        }
    }

    public AnonymousClass0B0(View view) {
        this.A00 = new WeakReference<>(view);
    }
}
