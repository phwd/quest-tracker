package X;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: X.1B3  reason: invalid class name */
public final class AnonymousClass1B3 extends AnonymousClass06v {
    public final RecyclerView A00;
    public final AnonymousClass1B4 A01;

    public AnonymousClass1B3(@NonNull RecyclerView recyclerView) {
        this.A00 = recyclerView;
        AnonymousClass1B4 r0 = this.A01;
        this.A01 = r0 == null ? new AnonymousClass1B4(this) : r0;
    }

    @Override // X.AnonymousClass06v
    public final void A02(View view, AccessibilityEvent accessibilityEvent) {
        AnonymousClass1Ag r0;
        super.A02(view, accessibilityEvent);
        if ((view instanceof RecyclerView) && !this.A00.hasPendingAdapterUpdates() && (r0 = ((RecyclerView) view).mLayout) != null) {
            r0.onInitializeAccessibilityEvent(accessibilityEvent);
        }
    }

    @Override // X.AnonymousClass06v
    public final void A05(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AnonymousClass1Ag r0;
        super.A05(view, accessibilityNodeInfoCompat);
        RecyclerView recyclerView = this.A00;
        if (!recyclerView.hasPendingAdapterUpdates() && (r0 = recyclerView.mLayout) != null) {
            r0.onInitializeAccessibilityNodeInfo(accessibilityNodeInfoCompat);
        }
    }

    @Override // X.AnonymousClass06v
    public final boolean A06(View view, int i, Bundle bundle) {
        AnonymousClass1Ag r0;
        if (super.A06(view, i, bundle)) {
            return true;
        }
        RecyclerView recyclerView = this.A00;
        if (recyclerView.hasPendingAdapterUpdates() || (r0 = recyclerView.mLayout) == null) {
            return false;
        }
        return r0.performAccessibilityAction(i, bundle);
    }
}
