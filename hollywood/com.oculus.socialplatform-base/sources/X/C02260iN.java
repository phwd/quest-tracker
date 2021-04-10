package X;

/* renamed from: X.0iN  reason: invalid class name and case insensitive filesystem */
public final class C02260iN extends AbstractC03630oD {
    public C02260iN A00 = null;
    public String A01;
    public final C02260iN A02;

    public final int A01(String str) {
        if (super.A01 != 2 || this.A01 != null) {
            return 4;
        }
        this.A01 = str;
        if (super.A00 < 0) {
            return 0;
        }
        return 1;
    }

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

    public C02260iN(int i, C02260iN r3) {
        super.A01 = i;
        this.A02 = r3;
        super.A00 = -1;
    }
}
