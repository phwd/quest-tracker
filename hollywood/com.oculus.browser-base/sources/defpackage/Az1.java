package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/* renamed from: Az1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Az1 extends BroadcastReceiver {
    public Az1(AbstractC6028zz1 zz1) {
    }

    public void onReceive(Context context, Intent intent) {
        boolean z;
        if (Build.VERSION.SDK_INT >= 26) {
            z = "android.security.action.KEYCHAIN_CHANGED".equals(intent.getAction()) || "android.security.action.TRUST_STORE_CHANGED".equals(intent.getAction()) || ("android.security.action.KEY_ACCESS_CHANGED".equals(intent.getAction()) && !intent.getBooleanExtra("android.security.extra.KEY_ACCESSIBLE", false));
        } else {
            z = "android.security.STORAGE_CHANGED".equals(intent.getAction());
        }
        if (z) {
            try {
                Dz1.a();
            } catch (CertificateException e) {
                AbstractC1220Ua0.a("X509Util", "Unable to reload the default TrustManager", e);
            } catch (KeyStoreException e2) {
                AbstractC1220Ua0.a("X509Util", "Unable to reload the default TrustManager", e2);
            } catch (NoSuchAlgorithmException e3) {
                AbstractC1220Ua0.a("X509Util", "Unable to reload the default TrustManager", e3);
            }
        }
    }
}
