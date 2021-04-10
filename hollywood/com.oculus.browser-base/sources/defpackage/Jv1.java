package defpackage;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

/* renamed from: Jv1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Jv1 {

    /* renamed from: a  reason: collision with root package name */
    public final Iv1 f8327a;
    public final Hv1 b;
    public final Set c;
    public final Set d;

    public Jv1(Iv1 iv1, Hv1 hv1, Set set, Set set2) {
        this.f8327a = iv1;
        this.b = hv1;
        this.c = set;
        this.d = set2;
    }

    public final boolean a() {
        Set set = this.c;
        Set set2 = this.d;
        return this.f8327a == null && this.b == null && (set == null || set.size() == 0) && (set2 == null || set2.size() == 0);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Jv1)) {
            return false;
        }
        Jv1 jv1 = (Jv1) obj;
        if (!Objects.equals(this.f8327a, jv1.f8327a) || !Objects.equals(this.b, jv1.b) || !Objects.equals(this.c, jv1.c) || !Objects.equals(this.d, jv1.d)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        Object[] objArr = new Object[4];
        int i = 0;
        objArr[0] = this.f8327a;
        objArr[1] = this.b;
        Set set = this.c;
        objArr[2] = Integer.valueOf(set != null ? set.hashCode() : 0);
        Set set2 = this.d;
        if (set2 != null) {
            i = set2.hashCode();
        }
        objArr[3] = Integer.valueOf(i);
        return Arrays.hashCode(objArr);
    }
}
