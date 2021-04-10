package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

/* renamed from: mp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewGroup$OnHierarchyChangeListenerC3782mp implements ViewGroup.OnHierarchyChangeListener {
    public ViewGroup.OnHierarchyChangeListener F;
    public final /* synthetic */ ChipGroup G;

    public ViewGroup$OnHierarchyChangeListenerC3782mp(ChipGroup chipGroup, AbstractC3269jp jpVar) {
        this.G = chipGroup;
    }

    public void onChildViewAdded(View view, View view2) {
        if (view == this.G && (view2 instanceof Chip)) {
            if (view2.getId() == -1) {
                view2.setId(View.generateViewId());
            }
            ((Chip) view2).O = this.G.N;
        }
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.F;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewAdded(view, view2);
        }
    }

    public void onChildViewRemoved(View view, View view2) {
        if (view == this.G && (view2 instanceof Chip)) {
            ((Chip) view2).O = null;
        }
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.F;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewRemoved(view, view2);
        }
    }
}
