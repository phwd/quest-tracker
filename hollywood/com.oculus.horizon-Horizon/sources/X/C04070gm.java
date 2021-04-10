package X;

/* renamed from: X.0gm  reason: invalid class name and case insensitive filesystem */
public final class C04070gm extends AnonymousClass0jh {
    public C04070gm A00 = null;
    public String A01;
    public final C04070gm A02;

    public final String toString() {
        char c;
        StringBuilder sb = new StringBuilder(64);
        int i = super.A01;
        if (i == 2) {
            sb.append('{');
            String str = this.A01;
            char c2 = '?';
            if (str != null) {
                c2 = '\"';
                sb.append('\"');
                sb.append(str);
            }
            sb.append(c2);
            c = '}';
        } else if (i == 1) {
            sb.append('[');
            int i2 = super.A00;
            if (i2 < 0) {
                i2 = 0;
            }
            sb.append(i2);
            c = ']';
        } else {
            sb.append("/");
            return sb.toString();
        }
        sb.append(c);
        return sb.toString();
    }

    public C04070gm(int i, C04070gm r3) {
        super.A01 = i;
        this.A02 = r3;
        super.A00 = -1;
    }
}
