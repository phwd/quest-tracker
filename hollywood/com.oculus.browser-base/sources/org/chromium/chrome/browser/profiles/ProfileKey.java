package org.chromium.chrome.browser.profiles;

import J.N;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ProfileKey {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f10753a;
    public long b;

    public ProfileKey(long j) {
        this.b = j;
        this.f10753a = N.MdejbNQu(j);
    }

    public static ProfileKey a() {
        return (ProfileKey) N.MZXDYv9T();
    }

    public static ProfileKey create(long j) {
        return new ProfileKey(j);
    }

    public final long getNativePointer() {
        return this.b;
    }

    public final void onNativeDestroyed() {
        this.b = 0;
    }
}
