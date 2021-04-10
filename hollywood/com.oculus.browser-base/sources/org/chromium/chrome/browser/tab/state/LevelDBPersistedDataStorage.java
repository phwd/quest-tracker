package org.chromium.chrome.browser.tab.state;

import J.N;
import org.chromium.chrome.browser.profiles.Profile;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LevelDBPersistedDataStorage {

    /* renamed from: a  reason: collision with root package name */
    public long f10776a;
    public String b;

    public LevelDBPersistedDataStorage(Profile profile, String str) {
        this.b = str;
        N.MkxB9Tdj(this, profile);
    }

    public final String a(String str) {
        return String.format("%s_%s", this.b, str);
    }

    public final void setNativePtr(long j) {
        this.f10776a = j;
    }
}
