package defpackage;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import org.chromium.chrome.browser.password_manager.settings.PasswordReauthenticationFragment;

/* renamed from: hK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2852hK0 {

    /* renamed from: a  reason: collision with root package name */
    public static Long f10064a;
    public static int b;

    public static boolean a(int i) {
        int i2 = b;
        return f10064a != null && (i == i2 || i2 == 1) && System.currentTimeMillis() - f10064a.longValue() < 60000;
    }

    public static void b(int i, int i2, KS ks, int i3) {
        PasswordReauthenticationFragment passwordReauthenticationFragment = new PasswordReauthenticationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("description", i);
        bundle.putInt("scope", i3);
        passwordReauthenticationFragment.U0(bundle);
        C0317Fe fe = new C0317Fe(ks);
        if (i2 == -1) {
            fe.i(0, passwordReauthenticationFragment, "reauthentication-manager-fragment", 1);
        } else if (i2 != 0) {
            fe.i(i2, passwordReauthenticationFragment, "reauthentication-manager-fragment", 2);
        } else {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        if (fe.h) {
            fe.g = true;
            fe.i = null;
            fe.e();
            return;
        }
        throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    }

    public static boolean c(Context context) {
        return ((KeyguardManager) context.getSystemService("keyguard")).isKeyguardSecure();
    }
}
