package X;

/* renamed from: X.qk  reason: case insensitive filesystem */
public final class C1016qk extends NW {
    public int A00;
    public int A01;
    public String A02;
    public C1016qk A03 = null;
    public final C1016qk A04;

    public final C1016qk A01(int i, int i2) {
        C1016qk qkVar = this.A03;
        if (qkVar == null) {
            C1016qk qkVar2 = new C1016qk(this, 1, i, i2);
            this.A03 = qkVar2;
            return qkVar2;
        }
        ((NW) qkVar).A01 = 1;
        ((NW) qkVar).A00 = -1;
        qkVar.A01 = i;
        qkVar.A00 = i2;
        qkVar.A02 = null;
        return qkVar;
    }

    public final C1016qk A02(int i, int i2) {
        C1016qk qkVar = this.A03;
        if (qkVar == null) {
            C1016qk qkVar2 = new C1016qk(this, 2, i, i2);
            this.A03 = qkVar2;
            return qkVar2;
        }
        ((NW) qkVar).A01 = 2;
        ((NW) qkVar).A00 = -1;
        qkVar.A01 = i;
        qkVar.A00 = i2;
        qkVar.A02 = null;
        return qkVar;
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
                    C0245Ne.A00(sb, str);
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

    public C1016qk(C1016qk qkVar, int i, int i2, int i3) {
        super.A01 = i;
        this.A04 = qkVar;
        this.A01 = i2;
        this.A00 = i3;
        super.A00 = -1;
    }
}
