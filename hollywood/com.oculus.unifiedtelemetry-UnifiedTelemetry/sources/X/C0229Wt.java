package X;

/* renamed from: X.Wt  reason: case insensitive filesystem */
public final class C0229Wt extends AbstractC0469q1 {
    public C0229Wt A00 = null;
    public String A01;
    public final C0229Wt A02;

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

    public C0229Wt(int i, C0229Wt wt) {
        super.A01 = i;
        this.A02 = wt;
        super.A00 = -1;
    }
}
