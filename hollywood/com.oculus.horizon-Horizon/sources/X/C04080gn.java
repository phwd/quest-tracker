package X;

/* renamed from: X.0gn  reason: invalid class name and case insensitive filesystem */
public final class C04080gn extends AnonymousClass0jh {
    public int A00;
    public int A01;
    public String A02;
    public C04080gn A03 = null;
    public final C04080gn A04;

    public final C04080gn A01(int i, int i2) {
        C04080gn r1 = this.A03;
        if (r1 == null) {
            C04080gn r12 = new C04080gn(this, 1, i, i2);
            this.A03 = r12;
            return r12;
        }
        ((AnonymousClass0jh) r1).A01 = 1;
        ((AnonymousClass0jh) r1).A00 = -1;
        r1.A01 = i;
        r1.A00 = i2;
        r1.A02 = null;
        return r1;
    }

    public final C04080gn A02(int i, int i2) {
        C04080gn r1 = this.A03;
        if (r1 == null) {
            C04080gn r12 = new C04080gn(this, 2, i, i2);
            this.A03 = r12;
            return r12;
        }
        ((AnonymousClass0jh) r1).A01 = 2;
        ((AnonymousClass0jh) r1).A00 = -1;
        r1.A01 = i;
        r1.A00 = i2;
        r1.A02 = null;
        return r1;
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
                    C04950jw.A00(sb, str);
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

    public C04080gn(C04080gn r2, int i, int i2, int i3) {
        super.A01 = i;
        this.A04 = r2;
        this.A01 = i2;
        this.A00 = i3;
        super.A00 = -1;
    }
}
