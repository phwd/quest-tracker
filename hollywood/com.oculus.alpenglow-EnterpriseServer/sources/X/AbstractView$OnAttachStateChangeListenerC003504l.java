package X;

import android.view.View;
import android.view.ViewConfiguration;
import androidx.annotation.RestrictTo;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.04l  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractView$OnAttachStateChangeListenerC003504l implements View.OnAttachStateChangeListener, View.OnTouchListener {
    public int A00;
    public Runnable A01;
    public Runnable A02;
    public boolean A03;
    public final float A04;
    public final int A05;
    public final int A06;
    public final View A07;
    public final int[] A08 = new int[2];

    public abstract AbstractC000903e A01();

    public final void onViewAttachedToWindow(View view) {
    }

    public final void onViewDetachedFromWindow(View view) {
        this.A03 = false;
        this.A00 = -1;
        Runnable runnable = this.A01;
        if (runnable != null) {
            this.A07.removeCallbacks(runnable);
        }
    }

    public static void A00(AbstractView$OnAttachStateChangeListenerC003504l r2) {
        Runnable runnable = r2.A02;
        if (runnable != null) {
            r2.A07.removeCallbacks(runnable);
        }
        Runnable runnable2 = r2.A01;
        if (runnable2 != null) {
            r2.A07.removeCallbacks(runnable2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0056, code lost:
        if (r2 == 3) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005b, code lost:
        if (r0 != false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x005d, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        if (A03() == false) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0080, code lost:
        if (r1 != 3) goto L_0x0082;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouch(android.view.View r15, android.view.MotionEvent r16) {
        /*
        // Method dump skipped, instructions count: 273
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractView$OnAttachStateChangeListenerC003504l.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public AbstractView$OnAttachStateChangeListenerC003504l(View view) {
        this.A07 = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.A04 = (float) ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.A06 = tapTimeout;
        this.A05 = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    public boolean A02() {
        AbstractC000903e A012 = A01();
        if (A012 == null || A012.A5a()) {
            return true;
        }
        A012.A8P();
        return true;
    }

    public boolean A03() {
        AbstractC000903e A012 = A01();
        if (A012 == null || !A012.A5a()) {
            return true;
        }
        A012.dismiss();
        return true;
    }
}
