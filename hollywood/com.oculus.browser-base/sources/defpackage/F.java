package defpackage;

import android.view.accessibility.AccessibilityNodeInfo;

/* renamed from: F  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F extends E {
    public F(H h) {
        super(h);
    }

    public AccessibilityNodeInfo findFocus(int i) {
        D b = this.f7931a.b(i);
        if (b == null) {
            return null;
        }
        return b.b;
    }
}
