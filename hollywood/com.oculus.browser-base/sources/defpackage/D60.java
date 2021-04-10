package defpackage;

import android.view.accessibility.AccessibilityManager;

/* renamed from: D60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class D60 implements AccessibilityManager.AccessibilityStateChangeListener {
    public final E60 F;

    public D60(E60 e60) {
        this.F = e60;
    }

    public void onAccessibilityStateChanged(boolean z) {
        E60 e60 = this.F;
        e60.c = z;
        e60.d.F.b();
    }
}
