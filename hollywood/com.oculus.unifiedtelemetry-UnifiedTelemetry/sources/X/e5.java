package X;

public final class e5 {
    public final String A00;
    public final String A01;
    public final String A02;
    public final ci A03;

    public final boolean equals(Object obj) {
        if (obj instanceof e5) {
            e5 e5Var = (e5) obj;
            if (!this.A02.equals(e5Var.A02) || !this.A01.equals(e5Var.A01) || !this.A03.equals(e5Var.A03)) {
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
        return AnonymousClass06.A04(this.A01, this.A03.A08());
    }

    public e5(String str, String str2) {
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
        C0368dq dqVar = new C0368dq();
        this.A00 = (dqVar.A02(null, obj) == AnonymousClass07.A00 ? dqVar.A03() : null).A02;
        if (str2.startsWith("sha1/")) {
            this.A01 = "sha1/";
            i = 5;
        } else if (str2.startsWith("sha256/")) {
            this.A01 = "sha256/";
            i = 7;
        } else {
            str3 = "pins must start with 'sha256/' or 'sha1/': ";
            throw new IllegalArgumentException(AnonymousClass06.A04(str3, str2));
        }
        ci A022 = ci.A02(str2.substring(i));
        this.A03 = A022;
        if (A022 == null) {
            str3 = "pins must be base64: ";
            throw new IllegalArgumentException(AnonymousClass06.A04(str3, str2));
        }
    }
}
