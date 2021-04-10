package defpackage;

import java.util.Objects;

/* renamed from: Iz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Iz1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f8262a;
    public final int b;
    public final String c;

    public Iz1(int i, int i2, String str) {
        this.f8262a = i;
        this.b = i2;
        Objects.requireNonNull(str, "Null packageName");
        this.c = str;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Iz1) {
            Iz1 iz1 = (Iz1) obj;
            return this.f8262a == iz1.f8262a && this.b == iz1.b && this.c.equals(iz1.c);
        }
    }

    public final int hashCode() {
        return ((((this.f8262a ^ 1000003) * 1000003) ^ this.b) * 1000003) ^ this.c.hashCode();
    }

    public final String toString() {
        int i = this.f8262a;
        int i2 = this.b;
        String str = this.c;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 83);
        sb.append("InstallState{installStatus=");
        sb.append(i);
        sb.append(", installErrorCode=");
        sb.append(i2);
        sb.append(", packageName=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
