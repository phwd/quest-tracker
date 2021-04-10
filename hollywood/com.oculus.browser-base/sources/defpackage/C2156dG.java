package defpackage;

import java.util.Objects;

/* renamed from: dG  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2156dG {

    /* renamed from: a  reason: collision with root package name */
    public final Object f9764a;

    public C2156dG(Object obj) {
        this.f9764a = obj;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2156dG.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f9764a, ((C2156dG) obj).f9764a);
    }

    public int hashCode() {
        Object obj = this.f9764a;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("DisplayCutoutCompat{");
        i.append(this.f9764a);
        i.append("}");
        return i.toString();
    }
}
