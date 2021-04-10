package X;

/* renamed from: X.0mb  reason: invalid class name and case insensitive filesystem */
public final class C06340mb {
    public final String A00;
    public final String A01;
    public final String A02;
    public final C04610h7 A03;

    public final boolean equals(Object obj) {
        if (obj instanceof C06340mb) {
            C06340mb r3 = (C06340mb) obj;
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

    public C06340mb(String str, String str2) {
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
        String sb2 = sb.toString();
        C06000lm r3 = new C06000lm();
        this.A00 = (r3.A02(null, sb2) == AnonymousClass007.A00 ? r3.A03() : null).A02;
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
        C04610h7 A022 = C04610h7.A02(str2.substring(i));
        this.A03 = A022;
        if (A022 == null) {
            str3 = "pins must be base64: ";
            throw new IllegalArgumentException(AnonymousClass006.A05(str3, str2));
        }
    }
}
