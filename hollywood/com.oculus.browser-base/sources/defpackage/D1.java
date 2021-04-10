package defpackage;

import J.N;
import android.app.Activity;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.signin.services.WebSigninBridge;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.signin.base.GoogleServiceAuthError;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: D1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D1 implements WebSigninBridge.Listener, AbstractC5863z1 {

    /* renamed from: a  reason: collision with root package name */
    public final WindowAndroid f7856a;
    public final Activity b;
    public final Tab c;
    public final Sx1 d;
    public final String e;
    public final SigninManager f = C5949zZ.a().d(Profile.b());
    public final IdentityManager g = C5949zZ.a().c(Profile.b());
    public WebSigninBridge h;
    public Callback i;

    public D1(WindowAndroid windowAndroid, Tab tab, Sx1 sx1, String str) {
        this.f7856a = windowAndroid;
        this.b = (Activity) windowAndroid.s0().get();
        this.c = tab;
        this.d = sx1;
        this.e = str;
    }

    @Override // org.chromium.chrome.browser.signin.services.WebSigninBridge.Listener
    public void a() {
        Object obj = ThreadUtils.f10596a;
        this.c.c(new LoadUrlParams(this.e, 0));
    }

    @Override // org.chromium.chrome.browser.signin.services.WebSigninBridge.Listener
    public void b(GoogleServiceAuthError googleServiceAuthError) {
        Object obj = ThreadUtils.f10596a;
        this.i.onResult(googleServiceAuthError);
    }

    public final void c() {
        WebSigninBridge webSigninBridge = this.h;
        if (webSigninBridge != null) {
            N.MFd8dsZQ(webSigninBridge.f10764a);
            webSigninBridge.f10764a = 0;
            this.h = null;
        }
    }
}
