package X;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ScrollView;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.NestedScrollView;

/* renamed from: X.0vR  reason: invalid class name */
public class AnonymousClass0vR extends AnonymousClass06v {
    @Override // X.AnonymousClass06v
    public final void A02(View view, AccessibilityEvent accessibilityEvent) {
        super.A02(view, accessibilityEvent);
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        accessibilityEvent.setClassName(ScrollView.class.getName());
        boolean z = false;
        if (nestedScrollView.getScrollRange() > 0) {
            z = true;
        }
        accessibilityEvent.setScrollable(z);
        accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
        accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
        accessibilityEvent.setMaxScrollX(nestedScrollView.getScrollX());
        accessibilityEvent.setMaxScrollY(nestedScrollView.getScrollRange());
    }

    @Override // X.AnonymousClass06v
    public final void A05(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        int scrollRange;
        super.A05(view, accessibilityNodeInfoCompat);
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        String name = ScrollView.class.getName();
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.A00;
        accessibilityNodeInfo.setClassName(name);
        if (nestedScrollView.isEnabled() && (scrollRange = nestedScrollView.getScrollRange()) > 0) {
            accessibilityNodeInfo.setScrollable(true);
            if (nestedScrollView.getScrollY() > 0) {
                accessibilityNodeInfoCompat.A01(AnonymousClass07x.A0P);
                accessibilityNodeInfoCompat.A01(AnonymousClass07x.A0V);
            }
            if (nestedScrollView.getScrollY() < scrollRange) {
                accessibilityNodeInfoCompat.A01(AnonymousClass07x.A0R);
                accessibilityNodeInfoCompat.A01(AnonymousClass07x.A0Q);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if (r7 != 16908346) goto L_0x0022;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005c  */
    @Override // X.AnonymousClass06v
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A06(android.view.View r6, int r7, android.os.Bundle r8) {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0vR.A06(android.view.View, int, android.os.Bundle):boolean");
    }
}
