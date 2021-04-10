package X;

import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.annotation.RestrictTo;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.oculus.socialplatform.R;
import java.lang.ref.Reference;
import java.util.Collections;
import java.util.List;

/* renamed from: X.06v  reason: invalid class name */
public class AnonymousClass06v {
    public static final View.AccessibilityDelegate A02 = new View.AccessibilityDelegate();
    public final View.AccessibilityDelegate A00;
    public final View.AccessibilityDelegate A01;

    public AnonymousClass084 A00(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.A01.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new AnonymousClass084(accessibilityNodeProvider);
        }
        return null;
    }

    public void A01(View view, int i) {
        this.A01.sendAccessibilityEvent(view, i);
    }

    public void A02(View view, AccessibilityEvent accessibilityEvent) {
        this.A01.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void A03(View view, AccessibilityEvent accessibilityEvent) {
        this.A01.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public void A04(View view, AccessibilityEvent accessibilityEvent) {
        this.A01.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public void A05(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        this.A01.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat.A00);
    }

    public boolean A07(View view, AccessibilityEvent accessibilityEvent) {
        return this.A01.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean A08(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.A01.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean A06(View view, int i, Bundle bundle) {
        Reference reference;
        ClickableSpan clickableSpan;
        List list = (List) view.getTag(R.id.tag_accessibility_actions);
        if (list == null) {
            list = Collections.emptyList();
        }
        int i2 = 0;
        while (i2 < list.size() && ((AccessibilityNodeInfo.AccessibilityAction) ((AnonymousClass07x) list.get(i2)).A00).getId() != i) {
            i2++;
        }
        boolean performAccessibilityAction = this.A01.performAccessibilityAction(view, i, bundle);
        if (performAccessibilityAction || i != R.id.accessibility_action_clickable_span) {
            return performAccessibilityAction;
        }
        int i3 = bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1);
        SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
        if (sparseArray == null || (reference = (Reference) sparseArray.get(i3)) == null || (clickableSpan = (ClickableSpan) reference.get()) == null) {
            return false;
        }
        CharSequence text = view.createAccessibilityNodeInfo().getText();
        if (!(text instanceof Spanned)) {
            return false;
        }
        ClickableSpan[] clickableSpanArr = (ClickableSpan[]) ((Spanned) text).getSpans(0, text.length(), ClickableSpan.class);
        if (clickableSpanArr == null) {
            return false;
        }
        for (ClickableSpan clickableSpan2 : clickableSpanArr) {
            if (clickableSpan.equals(clickableSpan2)) {
                clickableSpan.onClick(view);
                return true;
            }
        }
        return false;
    }

    public AnonymousClass06v() {
        this(A02);
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public AnonymousClass06v(View.AccessibilityDelegate accessibilityDelegate) {
        this.A01 = accessibilityDelegate;
        this.A00 = new C002706u(this);
    }
}
