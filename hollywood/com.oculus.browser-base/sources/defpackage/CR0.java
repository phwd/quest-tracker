package defpackage;

/* renamed from: CR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CR0 {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f7809a;
    public final String b;
    public final boolean c;

    public CR0(boolean z, String str, boolean z2) {
        this.f7809a = z;
        this.b = str;
        this.c = z2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CR0)) {
            return false;
        }
        CR0 cr0 = (CR0) obj;
        if (cr0.f7809a == this.f7809a && cr0.b.equals(this.b) && cr0.c == this.c) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        return String.format("State(%b, %s, %b)", Boolean.valueOf(this.f7809a), this.b, Boolean.valueOf(this.c));
    }
}
