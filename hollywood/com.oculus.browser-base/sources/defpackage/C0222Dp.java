package defpackage;

import android.app.Activity;
import android.view.accessibility.AccessibilityManager;
import java.util.Objects;
import org.chromium.base.ApplicationStatus;

/* renamed from: Dp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0222Dp implements Z9 {
    public final /* synthetic */ C0283Ep F;

    public C0222Dp(C0283Ep ep, AbstractC0161Cp cp) {
        this.F = ep;
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (ApplicationStatus.f()) {
            C0283Ep ep = this.F;
            C1322Vq0 vq0 = ep.c;
            if (vq0 != null) {
                vq0.clear();
            }
            if (ep.d != null) {
                AccessibilityManager b = ep.b();
                b.removeAccessibilityStateChangeListener(ep.d);
                b.removeTouchExplorationStateChangeListener(ep.d);
            }
            C0222Dp dp = ep.f;
            if (dp != null) {
                ApplicationStatus.h(dp);
                ep.f = null;
                return;
            }
            return;
        }
        Objects.requireNonNull(this.F);
        Objects.requireNonNull(this.F);
        if (i == 3) {
            this.F.g();
        }
    }
}
