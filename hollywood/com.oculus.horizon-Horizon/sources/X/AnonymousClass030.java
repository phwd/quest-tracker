package X;

/* renamed from: X.030  reason: invalid class name */
public final class AnonymousClass030 extends AnonymousClass07G {
    public static final AnonymousClass030 A01 = new AnonymousClass030("");
    public final String A00;

    public static AnonymousClass030 A00(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return A01;
        }
        return new AnonymousClass030(str);
    }

    @Override // X.AbstractC03980gY
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((AnonymousClass030) obj).A00.equals(this.A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AbstractC03980gY, X.AnonymousClass07G
    public final String toString() {
        String str = this.A00;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 2 + (length >> 4));
        sb.append('\"');
        C04950jw.A00(sb, str);
        sb.append('\"');
        return sb.toString();
    }

    public AnonymousClass030(String str) {
        this.A00 = str;
    }

    @Override // X.AbstractC03980gY
    public final String A02() {
        return this.A00;
    }
}
