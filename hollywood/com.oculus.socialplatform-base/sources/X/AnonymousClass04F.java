package X;

import java.io.IOException;

/* renamed from: X.04F  reason: invalid class name */
public final class AnonymousClass04F extends AnonymousClass0Cj {
    public static final AnonymousClass04F A01 = new AnonymousClass04F("");
    public final String A00;

    public static AnonymousClass04F A00(String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return A01;
        }
        return new AnonymousClass04F(str);
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9c(AbstractC02300iS r2, AbstractC02120i3 r3) throws IOException, C03620oC {
        String str = this.A00;
        if (str == null) {
            r2.A0G();
        } else {
            r2.A0U(str);
        }
    }

    @Override // X.AbstractC02170iC
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return ((AnonymousClass04F) obj).A00.equals(this.A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AbstractC02170iC, X.AnonymousClass0Cj
    public final String toString() {
        String str = this.A00;
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 2 + (length >> 4));
        sb.append('\"');
        C03730oR.A00(sb, str);
        sb.append('\"');
        return sb.toString();
    }

    public AnonymousClass04F(String str) {
        this.A00 = str;
    }

    @Override // X.AbstractC02170iC
    public final String A02() {
        return this.A00;
    }
}
