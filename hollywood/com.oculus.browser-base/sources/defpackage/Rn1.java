package defpackage;

import J.N;
import org.chromium.chrome.browser.sync.TrustedVaultClient;

/* renamed from: Rn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Rn1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final long f8853a;

    public Rn1(long j) {
        this.f8853a = j;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        long j = this.f8853a;
        Exception exc = (Exception) obj;
        if (TrustedVaultClient.b(j)) {
            N.M7rNqXkX(j, true);
        }
    }
}
