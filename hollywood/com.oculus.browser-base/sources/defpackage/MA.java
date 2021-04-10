package defpackage;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* renamed from: MA  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MA implements ViewGroup.OnHierarchyChangeListener {
    public final /* synthetic */ CoordinatorLayout F;

    public MA(CoordinatorLayout coordinatorLayout) {
        this.F = coordinatorLayout;
    }

    public void onChildViewAdded(View view, View view2) {
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.F.d0;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewAdded(view, view2);
        }
    }

    public void onChildViewRemoved(View view, View view2) {
        this.F.p(2);
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.F.d0;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewRemoved(view, view2);
        }
    }
}
