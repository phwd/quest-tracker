package defpackage;

import J.N;
import java.util.List;
import org.chromium.chrome.browser.sync.TrustedVaultClient;
import org.chromium.components.signin.base.CoreAccountInfo;

/* renamed from: On1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class On1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final long f8650a;
    public final CoreAccountInfo b;

    public On1(long j, CoreAccountInfo coreAccountInfo) {
        this.f8650a = j;
        this.b = coreAccountInfo;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        long j = this.f8650a;
        CoreAccountInfo coreAccountInfo = this.b;
        List list = (List) obj;
        if (TrustedVaultClient.b(j)) {
            N.M0S8oNZH(j, coreAccountInfo.getGaiaId(), (byte[][]) list.toArray(new byte[0][]));
        }
    }
}
