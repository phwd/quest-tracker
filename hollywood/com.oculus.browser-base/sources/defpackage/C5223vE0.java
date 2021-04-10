package defpackage;

import J.N;
import org.chromium.components.policy.PolicyService;

/* renamed from: vE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5223vE0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C5563xE0 f11470a;

    public C5223vE0(C5563xE0 xe0) {
        this.f11470a = xe0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C5563xE0 xe0 = this.f11470a;
        PolicyService policyService = (PolicyService) obj;
        if (xe0.G.get() == null) {
            if (N.MCCtS0px(policyService.f10882a, policyService)) {
                xe0.a();
                return;
            }
            C5393wE0 we0 = new C5393wE0(xe0, policyService);
            xe0.I = we0;
            if (policyService.b.isEmpty()) {
                N.M4YsjnbO(policyService.f10882a, policyService);
            }
            policyService.b.b(we0);
        }
    }
}
