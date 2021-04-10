package defpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* renamed from: Nq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0834Nq0 {

    /* renamed from: a  reason: collision with root package name */
    public final List f8579a = new ArrayList();
    public final Object b;

    public C0834Nq0(Object obj, AbstractC3188jI1 ji1) {
        Objects.requireNonNull(obj, "null reference");
        this.b = obj;
    }

    public final C0834Nq0 a(String str, Object obj) {
        List list = this.f8579a;
        String valueOf = String.valueOf(obj);
        StringBuilder sb = new StringBuilder(valueOf.length() + str.length() + 1);
        sb.append(str);
        sb.append("=");
        sb.append(valueOf);
        list.add(sb.toString());
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append(this.b.getClass().getSimpleName());
        sb.append('{');
        int size = this.f8579a.size();
        for (int i = 0; i < size; i++) {
            sb.append((String) this.f8579a.get(i));
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }
}
