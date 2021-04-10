package defpackage;

import android.view.accessibility.AccessibilityManager;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: My1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class My1 implements AccessibilityManager.TouchExplorationStateChangeListener {
    public final /* synthetic */ Ny1 F;

    public My1(Ny1 ny1, WindowAndroid windowAndroid) {
        this.F = ny1;
    }

    public void onTouchExplorationStateChanged(boolean z) {
        WindowAndroid windowAndroid = this.F.b;
        windowAndroid.P = windowAndroid.N.isTouchExplorationEnabled();
        this.F.b.A0();
    }
}
