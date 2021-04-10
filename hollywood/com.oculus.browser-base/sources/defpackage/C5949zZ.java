package defpackage;

import J.N;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.signin.AccountTrackerService;
import org.chromium.components.signin.identitymanager.IdentityManager;

/* renamed from: zZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5949zZ {

    /* renamed from: a  reason: collision with root package name */
    public static C5949zZ f11750a;

    public static C5949zZ a() {
        if (f11750a == null) {
            f11750a = new C5949zZ();
        }
        return f11750a;
    }

    public AccountTrackerService b(Profile profile) {
        Object obj = ThreadUtils.f10596a;
        return (AccountTrackerService) N.Mxrf_LNZ(profile);
    }

    public IdentityManager c(Profile profile) {
        Object obj = ThreadUtils.f10596a;
        return (IdentityManager) N.MjWAsIev(profile);
    }

    public SigninManager d(Profile profile) {
        Object obj = ThreadUtils.f10596a;
        return (SigninManager) N.MOZZ$5wu(profile);
    }
}
