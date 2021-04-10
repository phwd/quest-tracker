package android.support.v4.widget;

import android.widget.OverScroller;

/* access modifiers changed from: package-private */
public class ScrollerCompatIcs {
    ScrollerCompatIcs() {
    }

    public static float getCurrVelocity(Object obj) {
        return ((OverScroller) obj).getCurrVelocity();
    }
}
