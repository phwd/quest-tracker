package X;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.RequiresApi;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.oculus.socialplatform.R;
import java.util.Collections;
import java.util.List;

/* renamed from: X.06u  reason: invalid class name and case insensitive filesystem */
public final class C002706u extends View.AccessibilityDelegate {
    public final AnonymousClass06v A00;

    public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.A00.A07(view, accessibilityEvent);
    }

    @RequiresApi(16)
    public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        AnonymousClass084 A002 = this.A00.A00(view);
        if (A002 != null) {
            return (AccessibilityNodeProvider) A002.A00;
        }
        return null;
    }

    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.A00.A02(view, accessibilityEvent);
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean booleanValue;
        boolean booleanValue2;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
        Boolean bool = (Boolean) new C05470vm().A00(view);
        if (bool == null) {
            booleanValue = false;
        } else {
            booleanValue = bool.booleanValue();
        }
        accessibilityNodeInfoCompat.A05(booleanValue);
        Boolean bool2 = (Boolean) new C05450vk().A00(view);
        if (bool2 == null) {
            booleanValue2 = false;
        } else {
            booleanValue2 = bool2.booleanValue();
        }
        accessibilityNodeInfoCompat.A04(booleanValue2);
        accessibilityNodeInfoCompat.A02((CharSequence) new C05460vl().A00(view));
        this.A00.A05(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.A03(accessibilityNodeInfo.getText(), view);
        List list = (List) view.getTag(R.id.tag_accessibility_actions);
        if (list == null) {
            list = Collections.emptyList();
        }
        for (int i = 0; i < list.size(); i++) {
            accessibilityNodeInfoCompat.A01((AnonymousClass07x) list.get(i));
        }
    }

    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.A00.A03(view, accessibilityEvent);
    }

    public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.A00.A08(viewGroup, view, accessibilityEvent);
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return this.A00.A06(view, i, bundle);
    }

    public final void sendAccessibilityEvent(View view, int i) {
        this.A00.A01(view, i);
    }

    public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.A00.A04(view, accessibilityEvent);
    }

    public C002706u(AnonymousClass06v r1) {
        this.A00 = r1;
    }
}
