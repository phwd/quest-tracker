package org.chromium.chrome.browser.keyboard_accessory.data;

import org.chromium.base.Callback;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class UserInfoField {

    /* renamed from: a  reason: collision with root package name */
    public final String f10690a;
    public final String b;
    public final String c;
    public final boolean d;
    public final Callback e;

    public UserInfoField(String str, String str2, String str3, boolean z, Callback callback) {
        this.f10690a = str;
        this.b = str2;
        this.c = str3;
        this.d = z;
        this.e = callback;
    }

    public void a() {
        Callback callback = this.e;
        if (callback != null) {
            callback.onResult(this);
        }
    }

    public String getA11yDescription() {
        return this.b;
    }

    public String getDisplayText() {
        return this.f10690a;
    }

    public String getId() {
        return this.c;
    }

    public boolean isObfuscated() {
        return this.d;
    }

    public boolean isSelectable() {
        return this.e != null;
    }
}
