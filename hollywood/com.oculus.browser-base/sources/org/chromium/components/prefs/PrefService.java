package org.chromium.components.prefs;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class PrefService {

    /* renamed from: a  reason: collision with root package name */
    public long f10883a;

    public PrefService(long j) {
        this.f10883a = j;
    }

    public static PrefService create(long j) {
        return new PrefService(j);
    }

    public final void clearNativePtr() {
        this.f10883a = 0;
    }
}
