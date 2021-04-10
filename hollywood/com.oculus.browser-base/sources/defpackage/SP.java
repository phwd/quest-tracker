package defpackage;

import java.io.File;

/* renamed from: SP  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class SP {

    /* renamed from: a  reason: collision with root package name */
    public final int f8893a;
    public final String b;
    public final File c;

    public SP(TP tp, int i, String str) {
        this.f8893a = i;
        this.b = str;
        this.c = TP.e(i, str);
    }

    public abstract AbstractC2032cb a();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SP)) {
            return false;
        }
        SP sp = (SP) obj;
        return this.f8893a == sp.f8893a && this.b.equals(sp.b) && this.c.equals(sp.c);
    }

    public int hashCode() {
        int hashCode = this.b.hashCode();
        return this.c.hashCode() + ((hashCode + ((527 + this.f8893a) * 31)) * 31);
    }
}
