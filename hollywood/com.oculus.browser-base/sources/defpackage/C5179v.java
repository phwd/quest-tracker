package defpackage;

import android.os.Build;
import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: v  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5179v extends View.AccessibilityDelegate {

    /* renamed from: a  reason: collision with root package name */
    public final C5349w f11453a;

    public C5179v(C5349w wVar) {
        this.f11453a = wVar;
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.f11453a.a(view, accessibilityEvent);
    }

    public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        H b = this.f11453a.b(view);
        if (b != null) {
            return (AccessibilityNodeProvider) b.f8127a;
        }
        return null;
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.f11453a.c(view, accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean z;
        boolean z2;
        int i;
        D d = new D(accessibilityNodeInfo);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        Boolean bool = (Boolean) new Ut1(R.id.tag_screen_reader_focusable, Boolean.class, 28).c(view);
        if (bool == null) {
            z = false;
        } else {
            z = bool.booleanValue();
        }
        int i2 = Build.VERSION.SDK_INT;
        boolean z3 = true;
        if (i2 >= 28) {
            accessibilityNodeInfo.setScreenReaderFocusable(z);
        } else {
            d.h(1, z);
        }
        Boolean bool2 = (Boolean) new Xt1(R.id.tag_accessibility_heading, Boolean.class, 28).c(view);
        if (bool2 == null) {
            z2 = false;
        } else {
            z2 = bool2.booleanValue();
        }
        if (i2 >= 28) {
            accessibilityNodeInfo.setHeading(z2);
        } else {
            d.h(2, z2);
        }
        CharSequence f = AbstractC1920bu1.f(view);
        if (i2 >= 28) {
            accessibilityNodeInfo.setPaneTitle(f);
        } else {
            accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", f);
        }
        CharSequence charSequence = (CharSequence) new Wt1(R.id.tag_state_description, CharSequence.class, 64, 30).c(view);
        if (i2 < 30 && !Build.VERSION.CODENAME.equals("R")) {
            z3 = false;
        }
        if (z3) {
            accessibilityNodeInfo.setStateDescription(charSequence);
        } else {
            accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequence);
        }
        this.f11453a.d(view, d);
        CharSequence text = accessibilityNodeInfo.getText();
        if (i2 < 26) {
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            accessibilityNodeInfo.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
            SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
            if (sparseArray != null) {
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                    if (((WeakReference) sparseArray.valueAt(i3)).get() == null) {
                        arrayList.add(Integer.valueOf(i3));
                    }
                }
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    sparseArray.remove(((Integer) arrayList.get(i4)).intValue());
                }
            }
            ClickableSpan[] d2 = D.d(text);
            if (d2 != null && d2.length > 0) {
                d.f().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", R.id.accessibility_action_clickable_span);
                SparseArray sparseArray2 = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
                if (sparseArray2 == null) {
                    sparseArray2 = new SparseArray();
                    view.setTag(R.id.tag_accessibility_clickable_spans, sparseArray2);
                }
                for (int i5 = 0; i5 < d2.length; i5++) {
                    ClickableSpan clickableSpan = d2[i5];
                    int i6 = 0;
                    while (true) {
                        if (i6 >= sparseArray2.size()) {
                            i = D.f7853a;
                            D.f7853a = i + 1;
                            break;
                        } else if (clickableSpan.equals((ClickableSpan) ((WeakReference) sparseArray2.valueAt(i6)).get())) {
                            i = sparseArray2.keyAt(i6);
                            break;
                        } else {
                            i6++;
                        }
                    }
                    sparseArray2.put(i, new WeakReference(d2[i5]));
                    ClickableSpan clickableSpan2 = d2[i5];
                    Spanned spanned = (Spanned) text;
                    d.b("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan2)));
                    d.b("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan2)));
                    d.b("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan2)));
                    d.b("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i));
                }
            }
        }
        List list = (List) view.getTag(R.id.tag_accessibility_actions);
        if (list == null) {
            list = Collections.emptyList();
        }
        for (int i7 = 0; i7 < list.size(); i7++) {
            d.a((A) list.get(i7));
        }
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.f11453a.e(view, accessibilityEvent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f11453a.f(viewGroup, view, accessibilityEvent);
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
        return this.f11453a.g(view, i, bundle);
    }

    public void sendAccessibilityEvent(View view, int i) {
        this.f11453a.h(view, i);
    }

    public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.f11453a.i(view, accessibilityEvent);
    }
}
