package org.chromium.chrome.browser.password_manager;

import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PasswordStoreCredential {

    /* renamed from: a  reason: collision with root package name */
    public final GURL f10740a;
    public final String b;
    public final String c;

    public PasswordStoreCredential(GURL gurl, String str, String str2) {
        this.f10740a = gurl;
        this.b = str;
        this.c = str2;
    }

    public String getPassword() {
        return this.c;
    }

    public GURL getUrl() {
        return this.f10740a;
    }

    public String getUsername() {
        return this.b;
    }
}
