package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.policy.EnterpriseInfo;

/* renamed from: pL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4220pL extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final EnterpriseInfo f11062a;

    public C4220pL(EnterpriseInfo enterpriseInfo) {
        this.f11062a = enterpriseInfo;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4731sL sLVar = (C4731sL) obj;
        Objects.requireNonNull(this.f11062a);
        if (sLVar != null) {
            AbstractC3100ip1.f10165a.a("EnterpriseCheck.IsManaged2", sLVar.b);
            AbstractC3100ip1.f10165a.a("EnterpriseCheck.IsFullyManaged2", sLVar.f11267a);
        }
    }
}
