package X;

/* renamed from: X.Xj  reason: case insensitive filesystem */
public final class C0184Xj {
    public final String A00;
    public final String A01;
    public final String A02;
    public final WM A03;

    public final boolean equals(Object obj) {
        if (obj instanceof C0184Xj) {
            C0184Xj xj = (C0184Xj) obj;
            if (!this.A02.equals(xj.A02) || !this.A01.equals(xj.A01) || !this.A03.equals(xj.A03)) {
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
        return AnonymousClass06.A03(this.A01, this.A03.A08());
    }

    public C0184Xj(String str, String str2) {
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
        XU xu = new XU();
        this.A00 = (xu.A02(null, obj) == AnonymousClass07.A00 ? xu.A03() : null).A02;
        if (str2.startsWith("sha1/")) {
            this.A01 = "sha1/";
            i = 5;
        } else if (str2.startsWith("sha256/")) {
            this.A01 = "sha256/";
            i = 7;
        } else {
            str3 = "pins must start with 'sha256/' or 'sha1/': ";
            throw new IllegalArgumentException(AnonymousClass06.A03(str3, str2));
        }
        WM A022 = WM.A02(str2.substring(i));
        this.A03 = A022;
        if (A022 == null) {
            str3 = "pins must be base64: ";
            throw new IllegalArgumentException(AnonymousClass06.A03(str3, str2));
        }
    }
}
