package org.chromium.chrome.browser.signin.services;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.base.GoogleServiceAuthError;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WebSigninBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10764a;

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public interface Listener {
        void a();

        void b(GoogleServiceAuthError googleServiceAuthError);
    }

    public WebSigninBridge(Profile profile, CoreAccountInfo coreAccountInfo, Listener listener, Rx1 rx1) {
        this.f10764a = N.Mbi2nhML(profile, coreAccountInfo, listener);
    }

    public static void onSigninFailed(Listener listener, GoogleServiceAuthError googleServiceAuthError) {
        listener.b(googleServiceAuthError);
    }

    public static void onSigninSucceeded(Listener listener) {
        listener.a();
    }
}
