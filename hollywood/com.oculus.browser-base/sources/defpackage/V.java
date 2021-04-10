package defpackage;

import android.view.accessibility.AccessibilityManager;

/* renamed from: V  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class V implements AccessibilityManager.AccessibilityStateChangeListener, AccessibilityManager.TouchExplorationStateChangeListener {
    public final /* synthetic */ X F;

    public V(X x, U u) {
        this.F = x;
    }

    public final void onAccessibilityStateChanged(boolean z) {
        this.F.g();
    }

    public void onTouchExplorationStateChanged(boolean z) {
        this.F.g();
    }
}
