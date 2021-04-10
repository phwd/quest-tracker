package X;

import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.oculus.alpenglow.R;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* renamed from: X.0AB  reason: invalid class name */
public class AnonymousClass0AB {
    public static final View.AccessibilityDelegate A02 = new View.AccessibilityDelegate();
    public final View.AccessibilityDelegate A00 = new AnonymousClass0AA(this);
    public final View.AccessibilityDelegate A01 = A02;

    public void A00(View view, AccessibilityEvent accessibilityEvent) {
        this.A01.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void A01(View view, AnonymousClass0BK r4) {
        this.A01.onInitializeAccessibilityNodeInfo(view, r4.A00);
    }

    public boolean A02(View view, int i, Bundle bundle) {
        WeakReference weakReference;
        ClickableSpan clickableSpan;
        List list = (List) view.getTag(R.id.tag_accessibility_actions);
        if (list == null) {
            list = Collections.emptyList();
        }
        int i2 = 0;
        while (i2 < list.size() && ((AccessibilityNodeInfo.AccessibilityAction) ((AnonymousClass0BF) list.get(i2)).A00).getId() != i) {
            i2++;
        }
        boolean performAccessibilityAction = this.A01.performAccessibilityAction(view, i, bundle);
        if (performAccessibilityAction || i != R.id.accessibility_action_clickable_span) {
            return performAccessibilityAction;
        }
        int i3 = bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1);
        SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
        if (sparseArray == null || (weakReference = (WeakReference) sparseArray.get(i3)) == null || (clickableSpan = (ClickableSpan) weakReference.get()) == null) {
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
}
