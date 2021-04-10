package X;

import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.annotation.RequiresApi;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: X.07S  reason: invalid class name */
public class AnonymousClass07S implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener {
    public WeakHashMap<View, Boolean> A00 = new WeakHashMap<>();

    public final void onViewDetachedFromWindow(View view) {
    }

    @RequiresApi(19)
    public final void onGlobalLayout() {
        for (Map.Entry<View, Boolean> entry : this.A00.entrySet()) {
            View key = entry.getKey();
            boolean booleanValue = entry.getValue().booleanValue();
            boolean z = false;
            if (key.getVisibility() == 0) {
                z = true;
            }
            if (booleanValue != z) {
                if (z && ((AccessibilityManager) key.getContext().getSystemService("accessibility")).isEnabled()) {
                    boolean z2 = false;
                    if (new C05460vl().A00(key) != null) {
                        z2 = true;
                    }
                    if (key.getAccessibilityLiveRegion() != 0 || (z2 && key.getVisibility() == 0)) {
                        AccessibilityEvent obtain = AccessibilityEvent.obtain();
                        int i = 2048;
                        if (z2) {
                            i = 32;
                        }
                        obtain.setEventType(i);
                        obtain.setContentChangeTypes(16);
                        key.sendAccessibilityEventUnchecked(obtain);
                    } else if (key.getParent() != null) {
                        try {
                            key.getParent().notifySubtreeAccessibilityStateChanged(key, key, 16);
                        } catch (AbstractMethodError e) {
                            Log.e("ViewCompat", AnonymousClass006.A07(key.getParent().getClass().getSimpleName(), " does not fully implement ViewParent"), e);
                        }
                    }
                }
                this.A00.put(key, Boolean.valueOf(z));
            }
        }
    }

    @RequiresApi(19)
    public final void onViewAttachedToWindow(View view) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }
}
