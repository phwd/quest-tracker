package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: X1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class X1 implements Runnable {
    public void run() {
        C5949zZ.a().b(Profile.b()).c(false);
        C1851bW0 a2 = AbstractC2022cW0.a();
        Objects.requireNonNull(a2);
        AccountManagerFacadeProvider.getInstance().e(new VV0(a2, true));
    }
}
