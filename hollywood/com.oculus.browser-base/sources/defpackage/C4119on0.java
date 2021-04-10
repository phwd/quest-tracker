package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ScrollView;
import androidx.core.widget.NestedScrollView;

/* renamed from: on0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4119on0 extends C5349w {
    @Override // defpackage.C5349w
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        this.b.onInitializeAccessibilityEvent(view, accessibilityEvent);
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        accessibilityEvent.setClassName(ScrollView.class.getName());
        accessibilityEvent.setScrollable(nestedScrollView.r() > 0);
        accessibilityEvent.setScrollX(nestedScrollView.getScrollX());
        accessibilityEvent.setScrollY(nestedScrollView.getScrollY());
        accessibilityEvent.setMaxScrollX(nestedScrollView.getScrollX());
        accessibilityEvent.setMaxScrollY(nestedScrollView.r());
    }

    @Override // defpackage.C5349w
    public void d(View view, D d) {
        int r;
        this.b.onInitializeAccessibilityNodeInfo(view, d.b);
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        d.b.setClassName(ScrollView.class.getName());
        if (nestedScrollView.isEnabled() && (r = nestedScrollView.r()) > 0) {
            d.b.setScrollable(true);
            if (nestedScrollView.getScrollY() > 0) {
                d.a(A.c);
                d.a(A.g);
            }
            if (nestedScrollView.getScrollY() < r) {
                d.a(A.b);
                d.a(A.h);
            }
        }
    }

    @Override // defpackage.C5349w
    public boolean g(View view, int i, Bundle bundle) {
        if (super.g(view, i, bundle)) {
            return true;
        }
        NestedScrollView nestedScrollView = (NestedScrollView) view;
        if (!nestedScrollView.isEnabled()) {
            return false;
        }
        if (i != 4096) {
            if (i == 8192 || i == 16908344) {
                int max = Math.max(nestedScrollView.getScrollY() - ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), 0);
                if (max == nestedScrollView.getScrollY()) {
                    return false;
                }
                nestedScrollView.C(0 - nestedScrollView.getScrollX(), max - nestedScrollView.getScrollY(), 250, true);
                return true;
            } else if (i != 16908346) {
                return false;
            }
        }
        int min = Math.min(nestedScrollView.getScrollY() + ((nestedScrollView.getHeight() - nestedScrollView.getPaddingBottom()) - nestedScrollView.getPaddingTop()), nestedScrollView.r());
        if (min == nestedScrollView.getScrollY()) {
            return false;
        }
        nestedScrollView.C(0 - nestedScrollView.getScrollX(), min - nestedScrollView.getScrollY(), 250, true);
        return true;
    }
}
