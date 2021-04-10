package X;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.viewpager.widget.ViewPager;

/* renamed from: X.0rJ  reason: invalid class name and case insensitive filesystem */
public class C07160rJ extends AnonymousClass06n {
    public final /* synthetic */ ViewPager A00;

    public C07160rJ(ViewPager viewPager) {
        this.A00 = viewPager;
    }

    @Override // X.AnonymousClass06n
    public final void A00(View view, AccessibilityEvent accessibilityEvent) {
        super.A00(view, accessibilityEvent);
        accessibilityEvent.setClassName(ViewPager.ACCESSIBILITY_CLASS_NAME);
        accessibilityEvent.setScrollable(false);
        accessibilityEvent.getEventType();
    }

    @Override // X.AnonymousClass06n
    public final void A01(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.A01(view, accessibilityNodeInfoCompat);
        AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.A00;
        accessibilityNodeInfo.setClassName(ViewPager.ACCESSIBILITY_CLASS_NAME);
        accessibilityNodeInfo.setScrollable(false);
        ViewPager viewPager = this.A00;
        if (viewPager.canScrollHorizontally(1)) {
            accessibilityNodeInfo.addAction(4096);
        }
        if (viewPager.canScrollHorizontally(-1)) {
            accessibilityNodeInfo.addAction(8192);
        }
    }

    @Override // X.AnonymousClass06n
    public final boolean A02(View view, int i, Bundle bundle) {
        ViewPager viewPager;
        int i2;
        if (!super.A02(view, i, bundle)) {
            if (i != 4096) {
                if (i == 8192) {
                    viewPager = this.A00;
                    if (viewPager.canScrollHorizontally(-1)) {
                        i2 = viewPager.mCurItem - 1;
                    }
                }
                return false;
            }
            viewPager = this.A00;
            if (viewPager.canScrollHorizontally(1)) {
                i2 = viewPager.mCurItem + 1;
            }
            return false;
            viewPager.setCurrentItem(i2);
        }
        return true;
    }
}
