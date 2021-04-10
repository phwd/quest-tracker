package defpackage;

/* renamed from: w90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5378w90 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f11524a;
    public final String b;

    public C5378w90(Object obj, String str) {
        this.f11524a = obj;
        this.b = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C5378w90)) {
            return false;
        }
        C5378w90 w90 = (C5378w90) obj;
        return this.f11524a == w90.f11524a && this.b.equals(w90.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (System.identityHashCode(this.f11524a) * 31);
    }
}
