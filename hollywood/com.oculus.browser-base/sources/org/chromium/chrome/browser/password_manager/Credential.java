package org.chromium.chrome.browser.password_manager;

import android.graphics.drawable.Drawable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Credential {

    /* renamed from: a  reason: collision with root package name */
    public final String f10736a;
    public final String b;
    public final String c;
    public final String d;
    public final int e;
    public Drawable f = null;

    public Credential(String str, String str2, String str3, String str4, int i) {
        this.f10736a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = i;
    }

    public static Credential createCredential(String str, String str2, String str3, String str4, int i) {
        return new Credential(str, str2, str3, str4, i);
    }

    public static Credential[] createCredentialArray(int i) {
        return new Credential[i];
    }
}
