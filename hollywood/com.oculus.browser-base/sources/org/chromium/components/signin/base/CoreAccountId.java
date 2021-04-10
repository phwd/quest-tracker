package org.chromium.components.signin.base;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CoreAccountId {

    /* renamed from: a  reason: collision with root package name */
    public final String f10891a;

    public CoreAccountId(String str) {
        this.f10891a = str;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CoreAccountId)) {
            return false;
        }
        return this.f10891a.equals(((CoreAccountId) obj).f10891a);
    }

    public String getId() {
        return this.f10891a;
    }

    public int hashCode() {
        return this.f10891a.hashCode();
    }

    public String toString() {
        return this.f10891a;
    }
}
