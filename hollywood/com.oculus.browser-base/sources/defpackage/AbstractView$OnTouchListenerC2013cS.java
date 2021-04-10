package defpackage;

import android.view.View;
import android.view.ViewConfiguration;

/* renamed from: cS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractView$OnTouchListenerC2013cS implements View.OnTouchListener, View.OnAttachStateChangeListener {
    public final float F;
    public final int G;
    public final int H;
    public final View I;

    /* renamed from: J  reason: collision with root package name */
    public Runnable f9607J;
    public Runnable K;
    public boolean L;
    public int M;
    public final int[] N = new int[2];

    public AbstractView$OnTouchListenerC2013cS(View view) {
        this.I = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.F = (float) ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.G = tapTimeout;
        this.H = (ViewConfiguration.getLongPressTimeout() + tapTimeout) / 2;
    }

    public final void a() {
        Runnable runnable = this.K;
        if (runnable != null) {
            this.I.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f9607J;
        if (runnable2 != null) {
            this.I.removeCallbacks(runnable2);
        }
    }

    public abstract AbstractC3386kV0 b();

    public abstract boolean c();

    public boolean d() {
        AbstractC3386kV0 b = b();
        if (b == null || !b.b()) {
            return true;
        }
        b.dismiss();
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0087, code lost:
        if (r4 != 3) goto L_0x0079;
     */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0113  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(android.view.View r12, android.view.MotionEvent r13) {
        /*
        // Method dump skipped, instructions count: 305
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractView$OnTouchListenerC2013cS.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public void onViewAttachedToWindow(View view) {
    }

    public void onViewDetachedFromWindow(View view) {
        this.L = false;
        this.M = -1;
        Runnable runnable = this.f9607J;
        if (runnable != null) {
            this.I.removeCallbacks(runnable);
        }
    }
}
