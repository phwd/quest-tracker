package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityWindowInfo;

class AccessibilityWindowInfoCompatApi24 {
    AccessibilityWindowInfoCompatApi24() {
    }

    public static CharSequence getTitle(Object obj) {
        return ((AccessibilityWindowInfo) obj).getTitle();
    }

    public static Object getAnchor(Object obj) {
        return ((AccessibilityWindowInfo) obj).getAnchor();
    }
}
