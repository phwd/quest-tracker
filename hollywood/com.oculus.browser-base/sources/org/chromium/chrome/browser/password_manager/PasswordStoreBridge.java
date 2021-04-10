package org.chromium.chrome.browser.password_manager;

import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordStoreBridge {
    public static PasswordStoreCredential createPasswordStoreCredential(GURL gurl, String str, String str2) {
        return new PasswordStoreCredential(gurl, str, str2);
    }

    public static void insertCredential(PasswordStoreCredential[] passwordStoreCredentialArr, int i, GURL gurl, String str, String str2) {
        passwordStoreCredentialArr[i] = new PasswordStoreCredential(gurl, str, str2);
    }

    public final void onEditCredential(PasswordStoreCredential passwordStoreCredential) {
        throw null;
    }

    public final void passwordListAvailable(int i) {
        throw null;
    }
}
