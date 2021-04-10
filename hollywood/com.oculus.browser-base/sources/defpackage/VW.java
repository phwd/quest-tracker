package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* renamed from: VW  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class VW extends AbstractC4993tu1 {
    public Runnable c;
    public OverScroller d;
    public boolean e;
    public int f = -1;
    public int g;
    public int h = -1;
    public VelocityTracker i;

    public VW() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x009c  */
    @Override // defpackage.AbstractC4993tu1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean f(androidx.coordinatorlayout.widget.CoordinatorLayout r8, android.view.View r9, android.view.MotionEvent r10) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.VW.f(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f0 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    @Override // defpackage.AbstractC4993tu1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean p(androidx.coordinatorlayout.widget.CoordinatorLayout r21, android.view.View r22, android.view.MotionEvent r23) {
        /*
        // Method dump skipped, instructions count: 245
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.VW.p(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public abstract int r();

    public final int s(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4) {
        return u(coordinatorLayout, view, r() - i2, i3, i4);
    }

    public int t(CoordinatorLayout coordinatorLayout, View view, int i2) {
        return u(coordinatorLayout, view, i2, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public abstract int u(CoordinatorLayout coordinatorLayout, View view, int i2, int i3, int i4);

    public VW(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
