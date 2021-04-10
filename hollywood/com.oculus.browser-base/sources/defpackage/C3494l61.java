package defpackage;

import java.util.Collections;
import java.util.List;

/* renamed from: l61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3494l61 {

    /* renamed from: a  reason: collision with root package name */
    public final int f10324a;
    public final List b;

    public C3494l61(int i, List list) {
        this.f10324a = i;
        this.b = Collections.unmodifiableList(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof C3494l61)) {
            return false;
        }
        C3494l61 l61 = (C3494l61) obj;
        if (this.f10324a == l61.f10324a && this.b == null) {
            return l61.b == null;
        }
        return this.b.equals(l61.b);
    }

    public int hashCode() {
        List list = this.b;
        return ((list == null ? 0 : list.hashCode()) * 31 * 31) + this.f10324a;
    }
}
