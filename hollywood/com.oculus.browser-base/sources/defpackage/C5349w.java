package defpackage;

import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeProvider;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* renamed from: w  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5349w {

    /* renamed from: a  reason: collision with root package name */
    public static final View.AccessibilityDelegate f11510a = new View.AccessibilityDelegate();
    public final View.AccessibilityDelegate b;
    public final View.AccessibilityDelegate c;

    public C5349w() {
        this.b = f11510a;
        this.c = new C5179v(this);
    }

    public boolean a(View view, AccessibilityEvent accessibilityEvent) {
        return this.b.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public H b(View view) {
        AccessibilityNodeProvider accessibilityNodeProvider = this.b.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return new H(accessibilityNodeProvider);
        }
        return null;
    }

    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.b.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    public void d(View view, D d) {
        this.b.onInitializeAccessibilityNodeInfo(view, d.b);
    }

    public void e(View view, AccessibilityEvent accessibilityEvent) {
        this.b.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    public boolean f(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.b.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    public boolean g(View view, int i, Bundle bundle) {
        boolean z;
        WeakReference weakReference;
        boolean z2;
        String str;
        List list = (List) view.getTag(R.id.tag_accessibility_actions);
        if (list == null) {
            list = Collections.emptyList();
        }
        boolean z3 = false;
        int i2 = 0;
        while (true) {
            if (i2 >= list.size()) {
                break;
            }
            A a2 = (A) list.get(i2);
            if (a2.a() != i) {
                i2++;
            } else if (a2.l != null) {
                Class cls = a2.k;
                if (cls != null) {
                    try {
                        C5859z.a(cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
                        throw null;
                    } catch (Exception e) {
                        Class cls2 = a2.k;
                        if (cls2 == null) {
                            str = "null";
                        } else {
                            str = cls2.getName();
                        }
                        Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + str, e);
                    }
                }
                z = a2.l.a(view, null);
            }
        }
        z = false;
        if (!z) {
            z = this.b.performAccessibilityAction(view, i, bundle);
        }
        if (z || i != R.id.accessibility_action_clickable_span) {
            return z;
        }
        int i3 = bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1);
        SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
        if (!(sparseArray == null || (weakReference = (WeakReference) sparseArray.get(i3)) == null)) {
            ClickableSpan clickableSpan = (ClickableSpan) weakReference.get();
            if (clickableSpan != null) {
                ClickableSpan[] d = D.d(view.createAccessibilityNodeInfo().getText());
                int i4 = 0;
                while (true) {
                    if (d == null || i4 >= d.length) {
                        break;
                    } else if (clickableSpan.equals(d[i4])) {
                        z2 = true;
                        break;
                    } else {
                        i4++;
                    }
                }
            }
            z2 = false;
            if (z2) {
                clickableSpan.onClick(view);
                z3 = true;
            }
        }
        return z3;
    }

    public void h(View view, int i) {
        this.b.sendAccessibilityEvent(view, i);
    }

    public void i(View view, AccessibilityEvent accessibilityEvent) {
        this.b.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }

    public C5349w(View.AccessibilityDelegate accessibilityDelegate) {
        this.b = accessibilityDelegate;
        this.c = new C5179v(this);
    }
}
