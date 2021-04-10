package defpackage;

import android.view.View;
import android.view.ViewGroup;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* renamed from: ou1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewGroup$OnHierarchyChangeListenerC4141ou1 implements ViewGroup.OnHierarchyChangeListener {
    public final /* synthetic */ ViewLookupCachingFrameLayout F;

    public ViewGroup$OnHierarchyChangeListenerC4141ou1(ViewLookupCachingFrameLayout viewLookupCachingFrameLayout) {
        this.F = viewLookupCachingFrameLayout;
    }

    public void onChildViewAdded(View view, View view2) {
        this.F.G.remove(view2.getId());
        this.F.e(view2, this);
    }

    public void onChildViewRemoved(View view, View view2) {
        this.F.G.remove(view2.getId());
        this.F.e(view2, null);
    }
}
