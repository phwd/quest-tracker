package X;

import java.util.Locale;

/* renamed from: X.92  reason: invalid class name */
public final class AnonymousClass92 implements Comparable {
    public final float A00;
    public final String A01;

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AnonymousClass92)) {
            return false;
        }
        AnonymousClass92 r4 = (AnonymousClass92) obj;
        return r4.A00 == this.A00 && r4.A01.compareTo(this.A01) == 0;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        float f = this.A00 - ((AnonymousClass92) obj).A00;
        if (f < 0.0f) {
            return -1;
        }
        if (f > 0.0f) {
            return 1;
        }
        return 0;
    }

    public final int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.A01);
        sb.append(this.A00);
        return sb.toString().hashCode();
    }

    public final String toString() {
        return String.format(Locale.US, "%s, %f", this.A01, Float.valueOf(this.A00));
    }

    public AnonymousClass92(String str, float f) {
        this.A01 = str;
        this.A00 = f;
    }
}
