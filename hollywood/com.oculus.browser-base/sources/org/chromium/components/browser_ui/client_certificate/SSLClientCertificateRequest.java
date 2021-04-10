package org.chromium.components.browser_ui.client_certificate;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SSLClientCertificateRequest {
    public static boolean oculusSelectClientCertificate(long j, WindowAndroid windowAndroid, String[] strArr, byte[][] bArr, String str, int i) {
        Object obj = ThreadUtils.f10596a;
        Uri.Builder appendQueryParameter = new Uri.Builder().scheme("vrdesktop").authority("com.oculus.browser").appendPath(".CertPickerActivity").appendQueryParameter("nativePtr", String.valueOf(j)).appendQueryParameter("hostName", str).appendQueryParameter("port", String.valueOf(i));
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setComponent(new ComponentName("com.oculus.vrshell", "com.oculus.vrshell.MainActivity"));
        intent.putExtra("uri", appendQueryParameter.build().toString());
        intent.setData(Uri.parse("apk://com.oculus.vrshell.desktop"));
        ContextUtils.getApplicationContext().startActivity(intent);
        return true;
    }
}
