package defpackage;

import android.content.pm.Signature;
import com.oculus.browser.FacebookLoginManager;

/* renamed from: NN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NN implements Runnable {
    public final /* synthetic */ FacebookLoginManager F;

    public NN(FacebookLoginManager facebookLoginManager) {
        this.F = facebookLoginManager;
    }

    public void run() {
        FacebookLoginManager facebookLoginManager = this.F;
        Signature signature = FacebookLoginManager.f9699a;
        facebookLoginManager.g();
    }
}
