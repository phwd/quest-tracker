package X;

/* renamed from: X.2x  reason: invalid class name */
public final class AnonymousClass2x extends AnonymousClass7I {
    public static final AnonymousClass2x A01 = new AnonymousClass2x("");
    public final String A00;

    public static AnonymousClass2x A00(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return A01;
        }
        return new AnonymousClass2x(str);
    }

    @Override // X.AbstractC0222Wi
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((AnonymousClass2x) obj).A00.equals(this.A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AnonymousClass7I, X.AbstractC0222Wi
    public final String toString() {
        String str = this.A00;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 2 + (length >> 4));
        sb.append('\"');
        C0450md.A00(sb, str);
        sb.append('\"');
        return sb.toString();
    }

    public AnonymousClass2x(String str) {
        this.A00 = str;
    }

    @Override // X.AbstractC0222Wi
    public final Integer A04() {
        return AnonymousClass07.A08;
    }

    @Override // X.AbstractC0222Wi
    public final String A05() {
        return this.A00;
    }

    @Override // X.AbstractC0222Wi
    public final String A06() {
        return this.A00;
    }
}
