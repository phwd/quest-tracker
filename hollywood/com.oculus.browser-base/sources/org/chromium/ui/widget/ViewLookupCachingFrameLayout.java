package org.chromium.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewLookupCachingFrameLayout extends OptimizedFrameLayout {
    public final SparseArray G = new SparseArray();
    public final ViewGroup.OnHierarchyChangeListener H;

    public ViewLookupCachingFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ViewGroup$OnHierarchyChangeListenerC4141ou1 ou1 = new ViewGroup$OnHierarchyChangeListenerC4141ou1(this);
        this.H = ou1;
        setOnHierarchyChangeListener(ou1);
    }

    public View d(int i) {
        WeakReference weakReference = (WeakReference) this.G.get(i);
        View view = weakReference != null ? (View) weakReference.get() : null;
        if (view == null) {
            view = findViewById(i);
        }
        if (view != null) {
            this.G.put(i, new WeakReference(view));
        }
        return view;
    }

    public final void e(View view, ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            viewGroup.setOnHierarchyChangeListener(onHierarchyChangeListener);
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                e(viewGroup.getChildAt(i), onHierarchyChangeListener);
            }
        }
    }

    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        super.setOnHierarchyChangeListener(onHierarchyChangeListener);
    }
}
