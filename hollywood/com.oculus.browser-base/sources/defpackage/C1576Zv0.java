package defpackage;

/* renamed from: Zv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1576Zv0 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f9384a;
    public final Object b;

    public C1576Zv0(Object obj, Object obj2) {
        this.f9384a = obj;
        this.b = obj2;
    }

    public boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (!(obj instanceof C1576Zv0)) {
            return false;
        }
        C1576Zv0 zv0 = (C1576Zv0) obj;
        Object obj2 = this.f9384a;
        Object obj3 = zv0.f9384a;
        if (obj2 == null) {
            z = obj3 == null;
        } else {
            z = obj2.equals(obj3);
        }
        if (!z) {
            return false;
        }
        Object obj4 = this.b;
        Object obj5 = zv0.b;
        if (obj4 == null) {
            z2 = obj5 == null;
        } else {
            z2 = obj4.equals(obj5);
        }
        if (z2) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Object obj = this.f9384a;
        int i = 0;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.b;
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode ^ i;
    }
}
