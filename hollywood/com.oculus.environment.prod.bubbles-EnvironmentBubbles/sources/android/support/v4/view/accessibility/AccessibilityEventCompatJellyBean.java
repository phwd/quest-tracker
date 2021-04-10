package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

class AccessibilityEventCompatJellyBean {
    AccessibilityEventCompatJellyBean() {
    }

    public static void setMovementGranularity(AccessibilityEvent accessibilityEvent, int i) {
        accessibilityEvent.setMovementGranularity(i);
    }

    public static int getMovementGranularity(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getMovementGranularity();
    }

    public static void setAction(AccessibilityEvent accessibilityEvent, int i) {
        accessibilityEvent.setAction(i);
    }

    public static int getAction(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getAction();
    }
}
