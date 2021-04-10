package defpackage;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;
import java.util.Objects;

/* renamed from: E  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class E extends AccessibilityNodeProvider {

    /* renamed from: a  reason: collision with root package name */
    public final H f7931a;

    public E(H h) {
        this.f7931a = h;
    }

    public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
        D a2 = this.f7931a.a(i);
        if (a2 == null) {
            return null;
        }
        return a2.b;
    }

    @Override // android.view.accessibility.AccessibilityNodeProvider
    public List findAccessibilityNodeInfosByText(String str, int i) {
        Objects.requireNonNull(this.f7931a);
        return null;
    }

    public boolean performAction(int i, int i2, Bundle bundle) {
        return this.f7931a.c(i, i2, bundle);
    }
}
