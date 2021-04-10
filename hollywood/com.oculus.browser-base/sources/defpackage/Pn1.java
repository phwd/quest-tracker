package defpackage;

import J.N;
import org.chromium.chrome.browser.sync.TrustedVaultClient;
import org.chromium.components.signin.base.CoreAccountInfo;

/* renamed from: Pn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Pn1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final long f8714a;
    public final CoreAccountInfo b;

    public Pn1(long j, CoreAccountInfo coreAccountInfo) {
        this.f8714a = j;
        this.b = coreAccountInfo;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        long j = this.f8714a;
        CoreAccountInfo coreAccountInfo = this.b;
        Exception exc = (Exception) obj;
        if (TrustedVaultClient.b(j)) {
            N.M0S8oNZH(j, coreAccountInfo.getGaiaId(), new byte[0][]);
        }
    }
}
