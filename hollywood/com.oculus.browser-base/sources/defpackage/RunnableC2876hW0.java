package defpackage;

import java.util.Iterator;
import org.chromium.chrome.browser.signin.SigninManagerImpl;

/* renamed from: hW0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2876hW0 implements Runnable {
    public final SigninManagerImpl F;

    public RunnableC2876hW0(SigninManagerImpl signinManagerImpl) {
        this.F = signinManagerImpl;
    }

    public void run() {
        Iterator it = this.F.M.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC2193dW0) uq0.next()).c();
            } else {
                return;
            }
        }
    }
}
