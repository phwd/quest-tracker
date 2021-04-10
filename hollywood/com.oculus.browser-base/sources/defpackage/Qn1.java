package defpackage;

import J.N;
import org.chromium.chrome.browser.sync.TrustedVaultClient;

/* renamed from: Qn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Qn1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final long f8785a;

    public Qn1(long j) {
        this.f8785a = j;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        long j = this.f8785a;
        Boolean bool = (Boolean) obj;
        if (TrustedVaultClient.b(j)) {
            N.M7rNqXkX(j, bool.booleanValue());
        }
    }
}
