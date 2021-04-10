package X;

/* renamed from: X.0aR  reason: invalid class name and case insensitive filesystem */
public final class C02610aR extends AbstractC05920le {
    public C02610aR A00 = null;
    public String A01;
    public final C02610aR A02;

    public final String toString() {
        char c;
        StringBuilder sb = new StringBuilder(64);
        int i = super.A01;
        if (i != 0) {
            if (i == 1) {
                sb.append('[');
                int i2 = super.A00;
                if (i2 < 0) {
                    i2 = 0;
                }
                sb.append(i2);
                c = ']';
            } else if (i == 2) {
                sb.append('{');
                String str = this.A01;
                char c2 = '?';
                if (str != null) {
                    c2 = '\"';
                    sb.append('\"');
                    C06060lt.A00(sb, str);
                }
                sb.append(c2);
                c = '}';
            }
            sb.append(c);
        } else {
            sb.append("/");
        }
        return sb.toString();
    }

    public C02610aR(C02610aR r2, int i) {
        super.A01 = i;
        this.A02 = r2;
        super.A00 = -1;
    }
}
