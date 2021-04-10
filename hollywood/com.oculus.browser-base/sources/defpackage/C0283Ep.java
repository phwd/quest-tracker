package defpackage;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityManager;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.TraceEvent;

/* renamed from: Ep  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0283Ep extends X {
    public static C0283Ep e;
    public C0222Dp f;

    public static C0283Ep h() {
        if (e == null) {
            e = new C0283Ep();
        }
        return e;
    }

    @Override // defpackage.X
    public boolean d() {
        boolean z;
        if (this.f == null) {
            C0222Dp dp = new C0222Dp(this, null);
            this.f = dp;
            ApplicationStatus.g.b(dp);
        }
        if (this.d == null) {
            e();
        }
        Boolean bool = this.f9183a;
        if (bool != null) {
            return bool.booleanValue();
        }
        TraceEvent.Y("AccessibilityManager::isAccessibilityEnabled", null);
        AccessibilityManager b = b();
        boolean z2 = true;
        boolean z3 = b != null && b.isEnabled() && b.isTouchExplorationEnabled();
        this.b = Boolean.valueOf(z3);
        if (b != null && b.isEnabled() && !z3) {
            Iterator<AccessibilityServiceInfo> it = b.getEnabledAccessibilityServiceList(-1).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if ((it.next().getCapabilities() & 32) != 0) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    break;
                }
            }
            this.f9183a = Boolean.valueOf(z2);
            TraceEvent.f0("AccessibilityManager::isAccessibilityEnabled");
            return this.f9183a.booleanValue();
        }
        z2 = z3;
        this.f9183a = Boolean.valueOf(z2);
        TraceEvent.f0("AccessibilityManager::isAccessibilityEnabled");
        return this.f9183a.booleanValue();
    }

    public boolean i() {
        if (this.f == null) {
            C0222Dp dp = new C0222Dp(this, null);
            this.f = dp;
            ApplicationStatus.g.b(dp);
        }
        if (this.d == null) {
            e();
        }
        Boolean bool = this.b;
        if (bool != null) {
            return bool.booleanValue();
        }
        TraceEvent.Y("AccessibilityManager::isTouchExplorationEnabled", null);
        AccessibilityManager b = b();
        this.b = Boolean.valueOf(b != null && b.isEnabled() && b.isTouchExplorationEnabled());
        TraceEvent.f0("AccessibilityManager::isTouchExplorationEnabled");
        return this.b.booleanValue();
    }
}
