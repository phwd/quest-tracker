package X;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: X.1B4  reason: invalid class name */
public class AnonymousClass1B4 extends AnonymousClass06v {
    public Map<View, AnonymousClass06v> A00 = new WeakHashMap();
    public final AnonymousClass1B3 A01;

    @Override // X.AnonymousClass06v
    @Nullable
    public final AnonymousClass084 A00(@NonNull View view) {
        AnonymousClass06v r0 = this.A00.get(view);
        if (r0 != null) {
            return r0.A00(view);
        }
        return super.A00(view);
    }

    @Override // X.AnonymousClass06v
    public final void A01(@NonNull View view, int i) {
        AnonymousClass06v r0 = this.A00.get(view);
        if (r0 != null) {
            r0.A01(view, i);
        } else {
            super.A01(view, i);
        }
    }

    @Override // X.AnonymousClass06v
    public final void A02(@NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
        AnonymousClass06v r0 = this.A00.get(view);
        if (r0 != null) {
            r0.A02(view, accessibilityEvent);
        } else {
            super.A02(view, accessibilityEvent);
        }
    }

    @Override // X.AnonymousClass06v
    public final void A03(@NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
        AnonymousClass06v r0 = this.A00.get(view);
        if (r0 != null) {
            r0.A03(view, accessibilityEvent);
        } else {
            super.A03(view, accessibilityEvent);
        }
    }

    @Override // X.AnonymousClass06v
    public final void A04(@NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
        AnonymousClass06v r0 = this.A00.get(view);
        if (r0 != null) {
            r0.A04(view, accessibilityEvent);
        } else {
            super.A04(view, accessibilityEvent);
        }
    }

    @Override // X.AnonymousClass06v
    public final void A05(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        AnonymousClass1Ag r0;
        RecyclerView recyclerView = this.A01.A00;
        if (!recyclerView.hasPendingAdapterUpdates() && (r0 = recyclerView.mLayout) != null) {
            r0.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            AnonymousClass06v r02 = this.A00.get(view);
            if (r02 != null) {
                r02.A05(view, accessibilityNodeInfoCompat);
                return;
            }
        }
        super.A05(view, accessibilityNodeInfoCompat);
    }

    @Override // X.AnonymousClass06v
    public final boolean A06(View view, int i, Bundle bundle) {
        boolean A06;
        RecyclerView recyclerView = this.A01.A00;
        if (recyclerView.hasPendingAdapterUpdates() || recyclerView.mLayout == null) {
            return super.A06(view, i, bundle);
        }
        AnonymousClass06v r0 = this.A00.get(view);
        if (r0 != null) {
            A06 = r0.A06(view, i, bundle);
        } else {
            A06 = super.A06(view, i, bundle);
        }
        if (A06) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass06v
    public final boolean A07(@NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
        AnonymousClass06v r0 = this.A00.get(view);
        if (r0 != null) {
            return r0.A07(view, accessibilityEvent);
        }
        return super.A07(view, accessibilityEvent);
    }

    @Override // X.AnonymousClass06v
    public final boolean A08(@NonNull ViewGroup viewGroup, @NonNull View view, @NonNull AccessibilityEvent accessibilityEvent) {
        AnonymousClass06v r0 = this.A00.get(viewGroup);
        if (r0 != null) {
            return r0.A08(viewGroup, view, accessibilityEvent);
        }
        return super.A08(viewGroup, view, accessibilityEvent);
    }

    public AnonymousClass1B4(@NonNull AnonymousClass1B3 r2) {
        this.A01 = r2;
    }
}
