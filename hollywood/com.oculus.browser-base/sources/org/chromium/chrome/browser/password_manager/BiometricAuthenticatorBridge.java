package org.chromium.chrome.browser.password_manager;

import android.content.Context;
import android.hardware.biometrics.BiometricManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BiometricAuthenticatorBridge {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10735a = ContextUtils.getApplicationContext();

    public BiometricAuthenticatorBridge(WindowAndroid windowAndroid) {
        Objects.requireNonNull(windowAndroid);
    }

    public static BiometricAuthenticatorBridge create(WindowAndroid windowAndroid) {
        return new BiometricAuthenticatorBridge(windowAndroid);
    }

    public int canAuthenticate() {
        boolean z = true;
        if (Build.VERSION.SDK_INT >= 29) {
            int canAuthenticate = ((BiometricManager) this.f10735a.getSystemService(BiometricManager.class)).canAuthenticate();
            if (canAuthenticate != 0) {
                return canAuthenticate != 11 ? 1 : 2;
            }
            return 0;
        }
        Context context = this.f10735a;
        FingerprintManager a2 = IQ.a(context);
        if (!(a2 != null && a2.isHardwareDetected())) {
            return 1;
        }
        FingerprintManager a3 = IQ.a(context);
        if (a3 == null || !a3.hasEnrolledFingerprints()) {
            z = false;
        }
        return !z ? 2 : 0;
    }
}
