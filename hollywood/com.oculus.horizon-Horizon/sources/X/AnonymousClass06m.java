package X;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.RequiresApi;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.oculus.horizon.R;
import java.util.Collections;
import java.util.List;

/* renamed from: X.06m  reason: invalid class name */
public final class AnonymousClass06m extends View.AccessibilityDelegate {
    public final AnonymousClass06n A00;

    public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.A00.A01.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @RequiresApi(16)
    public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.A00.A01.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return (AccessibilityNodeProvider) new AnonymousClass086(accessibilityNodeProvider).A00;
        }
        return null;
    }

    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.A00.A00(view, accessibilityEvent);
    }

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean booleanValue;
        boolean booleanValue2;
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(accessibilityNodeInfo);
        Boolean bool = (Boolean) new AnonymousClass0sT().A00(view);
        if (bool == null) {
            booleanValue = false;
        } else {
            booleanValue = bool.booleanValue();
        }
        accessibilityNodeInfoCompat.A04(booleanValue);
        Boolean bool2 = (Boolean) new C07400sR().A00(view);
        if (bool2 == null) {
            booleanValue2 = false;
        } else {
            booleanValue2 = bool2.booleanValue();
        }
        accessibilityNodeInfoCompat.A03(booleanValue2);
        accessibilityNodeInfoCompat.A01((CharSequence) new C07410sS().A00(view));
        this.A00.A01(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.A02(accessibilityNodeInfo.getText(), view);
        List list = (List) view.getTag(R.id.tag_accessibility_actions);
        if (list == null) {
            list = Collections.emptyList();
        }
        for (int i = 0; i < list.size(); i++) {
            accessibilityNodeInfoCompat.A00.addAction((AccessibilityNodeInfo.AccessibilityAction) ((AnonymousClass07z) list.get(i)).A00);
        }
    }

    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.A00.A01.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.A00.A01.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return this.A00.A02(view, i, bundle);
    }

    public final void sendAccessibilityEvent(View view, int i) {
        this.A00.A01.sendAccessibilityEvent(view, i);
    }

    public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.A00.A01.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public AnonymousClass06m(AnonymousClass06n r1) {
        this.A00 = r1;
    }
}
