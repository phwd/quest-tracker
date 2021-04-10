package org.chromium.components.policy;

import J.N;
import java.util.Iterator;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PolicyService {

    /* renamed from: a  reason: collision with root package name */
    public long f10882a;
    public final C1322Vq0 b = new C1322Vq0();

    public PolicyService(long j) {
        this.f10882a = j;
    }

    public final void onPolicyServiceInitialized() {
        Iterator it = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                C5393wE0 we0 = (C5393wE0) ((AbstractC5733yE0) uq0.next());
                PolicyService policyService = we0.f11530a;
                policyService.b.c(we0.b.I);
                if (policyService.b.isEmpty()) {
                    N.MU0pXsSP(policyService.f10882a, policyService);
                }
                C5563xE0 xe0 = we0.b;
                xe0.I = null;
                xe0.a();
            } else {
                return;
            }
        }
    }

    public final void onPolicyUpdated(PolicyMap policyMap, PolicyMap policyMap2) {
        Iterator it = this.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                Objects.requireNonNull((AbstractC5733yE0) uq0.next());
            } else {
                return;
            }
        }
    }
}
