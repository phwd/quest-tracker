package defpackage;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;

@Deprecated
/* renamed from: IQ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class IQ {
    public static FingerprintManager a(Context context) {
        if (context.getPackageManager().hasSystemFeature("android.hardware.fingerprint")) {
            return (FingerprintManager) context.getSystemService(FingerprintManager.class);
        }
        return null;
    }
}
