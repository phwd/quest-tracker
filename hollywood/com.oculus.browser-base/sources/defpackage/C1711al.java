package defpackage;

import J.N;
import org.chromium.chrome.browser.webauthn.CableAuthenticatorModuleProvider;

/* renamed from: al  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1711al implements AbstractC1593a20 {

    /* renamed from: a  reason: collision with root package name */
    public final long f9448a;
    public final long b;
    public final long c;

    public C1711al(long j, long j2, long j3) {
        this.f9448a = j;
        this.b = j2;
        this.c = j3;
    }

    @Override // defpackage.AbstractC1593a20
    public void a(boolean z) {
        long j = this.f9448a;
        long j2 = this.b;
        long j3 = this.c;
        int i = CableAuthenticatorModuleProvider.y0;
        if (!z) {
            N.M6bGbuGo(j);
        } else {
            AbstractC2062cl.a().b(j, j2, j3, "org.chromium.chrome.browser.webauth.authenticator.CableAuthenticatorActivity");
        }
    }
}
