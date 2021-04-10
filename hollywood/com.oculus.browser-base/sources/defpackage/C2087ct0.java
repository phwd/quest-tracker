package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.optimization_guide.OptimizationGuideBridge;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: ct0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2087ct0 implements AbstractC1818bH0 {
    public final /* synthetic */ C2257dt0 F;

    public C2087ct0(C2257dt0 dt0) {
        this.F = dt0;
    }

    @Override // defpackage.AbstractC1818bH0
    public void f(Profile profile) {
    }

    @Override // defpackage.AbstractC1818bH0
    public void i(Profile profile) {
        if (this.F.f9816a.containsKey(profile)) {
            OptimizationGuideBridge optimizationGuideBridge = (OptimizationGuideBridge) this.F.f9816a.get(profile);
            Objects.requireNonNull(optimizationGuideBridge);
            Object obj = ThreadUtils.f10596a;
            long j = optimizationGuideBridge.f10727a;
            if (j != 0) {
                N.M2siX4Rz(j);
                optimizationGuideBridge.f10727a = 0;
            }
            this.F.f9816a.remove(profile);
        }
    }
}
