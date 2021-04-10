package org.chromium.components.policy;

import java.util.ArrayList;
import java.util.List;
import org.chromium.base.ThreadUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CombinedPolicyProvider {

    /* renamed from: a  reason: collision with root package name */
    public static CombinedPolicyProvider f10880a;
    public long b;
    public PolicyConverter c;
    public final List d = new ArrayList();
    public final List e = new ArrayList();
    public final List f = new ArrayList();

    public static CombinedPolicyProvider a() {
        if (f10880a == null) {
            f10880a = new CombinedPolicyProvider();
        }
        return f10880a;
    }

    public static CombinedPolicyProvider linkNative(long j, PolicyConverter policyConverter) {
        Object obj = ThreadUtils.f10596a;
        CombinedPolicyProvider a2 = a();
        a2.b = j;
        a2.c = policyConverter;
        if (j != 0) {
            for (AbstractC2448f fVar : a2.d) {
                fVar.b();
            }
        }
        return a();
    }

    public void refreshPolicies() {
        for (int i = 0; i < this.e.size(); i++) {
            this.e.set(i, null);
        }
        for (AbstractC2448f fVar : this.d) {
            fVar.b();
        }
    }
}
