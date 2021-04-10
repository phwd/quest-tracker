package X;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ScrollView;
import androidx.core.widget.NestedScrollView;

/* renamed from: X.0cy  reason: invalid class name and case insensitive filesystem */
public class C03690cy extends AnonymousClass0AB {
    @Override // X.AnonymousClass0AB
    public final void A00(View view, AccessibilityEvent accessibilityEvent) {
        super.A00(view, accessibilityEvent);
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

    @Override // X.AnonymousClass0AB
    public final void A01(View view, AnonymousClass0BK r5) {
        int scrollRange;
        super.A01(view, r5);
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        String name = ScrollView.class.getName();
        AccessibilityNodeInfo accessibilityNodeInfo = r5.A00;
        accessibilityNodeInfo.setClassName(name);
        if (nestedScrollView.isEnabled() && (scrollRange = nestedScrollView.getScrollRange()) > 0) {
            accessibilityNodeInfo.setScrollable(true);
            if (nestedScrollView.getScrollY() > 0) {
                r5.A01(AnonymousClass0BF.A0P);
                r5.A01(AnonymousClass0BF.A0V);
            }
            if (nestedScrollView.getScrollY() < scrollRange) {
                r5.A01(AnonymousClass0BF.A0R);
                r5.A01(AnonymousClass0BF.A0Q);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        if (r7 != 16908346) goto L_0x0022;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005c  */
    @Override // X.AnonymousClass0AB
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A02(android.view.View r6, int r7, android.os.Bundle r8) {
        /*
        // Method dump skipped, instructions count: 106
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C03690cy.A02(android.view.View, int, android.os.Bundle):boolean");
    }
}
