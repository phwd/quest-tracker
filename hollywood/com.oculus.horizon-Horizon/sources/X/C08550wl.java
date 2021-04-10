package X;

/* renamed from: X.0wl  reason: invalid class name and case insensitive filesystem */
public final class C08550wl {
    public final String A00;
    public final String A01;
    public final String A02;
    public final C07700vD A03;

    public final boolean equals(Object obj) {
        if (obj instanceof C08550wl) {
            C08550wl r3 = (C08550wl) obj;
            if (!this.A02.equals(r3.A02) || !this.A01.equals(r3.A01) || !this.A03.equals(r3.A03)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return ((((527 + this.A02.hashCode()) * 31) + this.A01.hashCode()) * 31) + this.A03.hashCode();
    }

    public final String toString() {
        return AnonymousClass006.A05(this.A01, this.A03.A08());
    }

    public C08550wl(String str, String str2) {
        StringBuilder sb;
        String str3;
        int i;
        this.A02 = str;
        if (str.startsWith("*.")) {
            sb = new StringBuilder();
            sb.append("http://");
            str = str.substring(2);
        } else {
            sb = new StringBuilder();
            sb.append("http://");
        }
        sb.append(str);
        String obj = sb.toString();
        C08410wV r3 = new C08410wV();
        this.A00 = (r3.A02(null, obj) == AnonymousClass007.A00 ? r3.A03() : null).A02;
        if (str2.startsWith("sha1/")) {
            this.A01 = "sha1/";
            i = 5;
        } else if (str2.startsWith("sha256/")) {
            this.A01 = "sha256/";
            i = 7;
        } else {
            str3 = "pins must start with 'sha256/' or 'sha1/': ";
            throw new IllegalArgumentException(AnonymousClass006.A05(str3, str2));
        }
        C07700vD A022 = C07700vD.A02(str2.substring(i));
        this.A03 = A022;
        if (A022 == null) {
            str3 = "pins must be base64: ";
            throw new IllegalArgumentException(AnonymousClass006.A05(str3, str2));
        }
    }
}
