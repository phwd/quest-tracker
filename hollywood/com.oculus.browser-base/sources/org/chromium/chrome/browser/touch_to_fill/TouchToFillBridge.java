package org.chromium.chrome.browser.touch_to_fill;

import J.N;
import org.chromium.chrome.browser.touch_to_fill.data.Credential;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TouchToFillBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10791a;

    public TouchToFillBridge(long j) {
        this.f10791a = j;
    }

    public static TouchToFillBridge create(long j, WindowAndroid windowAndroid) {
        return new TouchToFillBridge(j);
    }

    public static Credential[] createCredentialArray(int i) {
        return new Credential[i];
    }

    public static void insertCredential(Credential[] credentialArr, int i, String str, String str2, String str3, String str4, boolean z, boolean z2) {
        credentialArr[i] = new Credential(str, str2, str3, str4, z, z2);
    }

    public final void destroy() {
        this.f10791a = 0;
    }

    public final void showCredentials(String str, boolean z, Credential[] credentialArr) {
        if (credentialArr.length > 0) {
            N.MW5teN_W(this.f10791a, credentialArr[0]);
        }
    }
}
