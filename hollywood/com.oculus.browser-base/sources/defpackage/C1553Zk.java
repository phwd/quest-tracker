package defpackage;

import org.chromium.chrome.browser.webauthn.CableAuthenticatorModuleProvider;

/* renamed from: Zk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1553Zk implements AbstractC1593a20 {

    /* renamed from: a  reason: collision with root package name */
    public final CableAuthenticatorModuleProvider f9364a;

    public C1553Zk(CableAuthenticatorModuleProvider cableAuthenticatorModuleProvider) {
        this.f9364a = cableAuthenticatorModuleProvider;
    }

    @Override // defpackage.AbstractC1593a20
    public void a(boolean z) {
        CableAuthenticatorModuleProvider cableAuthenticatorModuleProvider = this.f9364a;
        if (!z) {
            cableAuthenticatorModuleProvider.z0.setText("Failed to install.");
        } else {
            cableAuthenticatorModuleProvider.e1();
        }
    }
}
