package X;

import java.io.IOException;

/* renamed from: X.07d  reason: invalid class name and case insensitive filesystem */
public final class C007007d extends AnonymousClass0E2 {
    public static final C007007d A01 = new C007007d("");
    public final String A00;

    public static C007007d A00(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return A01;
        }
        return new C007007d(str);
    }

    @Override // X.AnonymousClass0aF
    public final String A02() {
        return this.A00;
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r2, AnonymousClass0a8 r3) throws IOException, C05910ld {
        String str = this.A00;
        if (str == null) {
            r2.A0D();
        } else {
            r2.A0S(str);
        }
    }

    @Override // X.AnonymousClass0aF
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((C007007d) obj).A00.equals(this.A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AnonymousClass0aF, X.AnonymousClass0E2
    public final String toString() {
        String str = this.A00;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 2 + (length >> 4));
        sb.append('\"');
        C06060lt.A00(sb, str);
        sb.append('\"');
        return sb.toString();
    }

    public C007007d(String str) {
        this.A00 = str;
    }
}
