package defpackage;

import android.app.Activity;
import com.oculus.browser.R;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.password_manager.CredentialLeakDialogBridge;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: iB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2995iB implements Runnable {
    public final CredentialLeakDialogBridge F;

    public RunnableC2995iB(CredentialLeakDialogBridge credentialLeakDialogBridge) {
        this.F = credentialLeakDialogBridge;
    }

    public void run() {
        CredentialLeakDialogBridge credentialLeakDialogBridge = this.F;
        if (credentialLeakDialogBridge.c.get() != null) {
            C2535fX.a().b((Activity) credentialLeakDialogBridge.c.get(), ((ChromeActivity) credentialLeakDialogBridge.c.get()).getString(R.string.f52570_resource_name_obfuscated_RES_2131952574), Profile.a(((ChromeActivity) credentialLeakDialogBridge.c.get()).W0.H.l()), null);
        }
    }
}
