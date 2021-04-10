package X;

/* renamed from: X.Wu  reason: case insensitive filesystem */
public final class C0230Wu extends AbstractC0469q1 {
    public int A00;
    public int A01;
    public String A02;
    public C0230Wu A03 = null;
    public final C0230Wu A04;

    public final C0230Wu A01(int i, int i2) {
        C0230Wu wu = this.A03;
        if (wu == null) {
            C0230Wu wu2 = new C0230Wu(this, 1, i, i2);
            this.A03 = wu2;
            return wu2;
        }
        ((AbstractC0469q1) wu).A01 = 1;
        ((AbstractC0469q1) wu).A00 = -1;
        wu.A01 = i;
        wu.A00 = i2;
        wu.A02 = null;
        return wu;
    }

    public final C0230Wu A02(int i, int i2) {
        C0230Wu wu = this.A03;
        if (wu == null) {
            C0230Wu wu2 = new C0230Wu(this, 2, i, i2);
            this.A03 = wu2;
            return wu2;
        }
        ((AbstractC0469q1) wu).A01 = 2;
        ((AbstractC0469q1) wu).A00 = -1;
        wu.A01 = i;
        wu.A00 = i2;
        wu.A02 = null;
        return wu;
    }

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
                String str = this.A02;
                char c2 = '?';
                if (str != null) {
                    c2 = '\"';
                    sb.append('\"');
                    C0450md.A00(sb, str);
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

    public C0230Wu(C0230Wu wu, int i, int i2, int i3) {
        super.A01 = i;
        this.A04 = wu;
        this.A01 = i2;
        this.A00 = i3;
        super.A00 = -1;
    }
}
