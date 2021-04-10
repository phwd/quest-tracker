package defpackage;

import java.util.Arrays;
import java.util.Objects;

/* renamed from: KE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class KE1 implements Comparable {
    public final String F;

    public KE1(String str) {
        Objects.requireNonNull(str);
        this.F = str;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        KE1 ke1 = (KE1) obj;
        Objects.requireNonNull(ke1);
        if (this.F.length() != ke1.F.length()) {
            return this.F.length() - ke1.F.length();
        }
        return this.F.compareTo(ke1.F);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && KE1.class == obj.getClass()) {
            return this.F.equals(((KE1) obj).F);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{3, this.F});
    }

    public final String toString() {
        String str = this.F;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 2);
        sb.append("\"");
        sb.append(str);
        sb.append("\"");
        return sb.toString();
    }
}
