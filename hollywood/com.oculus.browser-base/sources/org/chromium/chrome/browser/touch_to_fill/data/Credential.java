package org.chromium.chrome.browser.touch_to_fill.data;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Credential {

    /* renamed from: a  reason: collision with root package name */
    public final String f10792a;
    public final String b;
    public final String c;
    public final boolean d;
    public final boolean e;

    public Credential(String str, String str2, String str3, String str4, boolean z, boolean z2) {
        this.f10792a = str;
        this.b = str2;
        this.c = str4;
        this.d = z;
        this.e = z2;
    }

    public String getOriginUrl() {
        return this.c;
    }

    public String getPassword() {
        return this.b;
    }

    public String getUsername() {
        return this.f10792a;
    }

    public boolean isAffiliationBasedMatch() {
        return this.e;
    }

    public boolean isPublicSuffixMatch() {
        return this.d;
    }
}
