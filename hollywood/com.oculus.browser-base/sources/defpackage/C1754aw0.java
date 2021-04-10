package defpackage;

import java.util.Objects;

/* renamed from: aw0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1754aw0 {

    /* renamed from: a  reason: collision with root package name */
    public final Object f9500a;
    public final Object b;

    public C1754aw0(Object obj, Object obj2) {
        this.f9500a = obj;
        this.b = obj2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C1754aw0)) {
            return false;
        }
        C1754aw0 aw0 = (C1754aw0) obj;
        if (!Objects.equals(aw0.f9500a, this.f9500a) || !Objects.equals(aw0.b, this.b)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Object obj = this.f9500a;
        int i = 0;
        int hashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.b;
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("Pair{");
        i.append(this.f9500a);
        i.append(" ");
        i.append(this.b);
        i.append("}");
        return i.toString();
    }
}
