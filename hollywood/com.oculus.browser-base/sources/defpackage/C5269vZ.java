package defpackage;

import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* renamed from: vZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5269vZ extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C5609xZ f11484a;

    public C5269vZ(C5609xZ xZVar) {
        this.f11484a = xZVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C5609xZ xZVar = this.f11484a;
        Profile profile = (Profile) obj;
        IdentityManager identityManager = xZVar.f11614J;
        if (identityManager != null) {
            identityManager.b.c(xZVar);
        }
        if (profile.g()) {
            xZVar.f11614J = null;
            return;
        }
        IdentityManager c = C5949zZ.a().c(profile);
        xZVar.f11614J = c;
        c.b.b(xZVar);
        xZVar.W(true);
    }
}
