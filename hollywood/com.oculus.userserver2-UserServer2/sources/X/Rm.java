package X;

public final class Rm extends AnonymousClass9q {
    public Rm A00 = null;
    public String A01;
    public final Rm A02;

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
                    int[] iArr = AnonymousClass8C.A07;
                    int length = iArr.length;
                    int length2 = str.length();
                    for (int i3 = 0; i3 < length2; i3++) {
                        char charAt = str.charAt(i3);
                        if (charAt < length && iArr[charAt] != 0) {
                            sb.append('\\');
                            int i4 = iArr[charAt];
                            if (i4 < 0) {
                                sb.append('u');
                                sb.append('0');
                                sb.append('0');
                                char[] cArr = AnonymousClass8C.A06;
                                sb.append(cArr[charAt >> 4]);
                                charAt = cArr[charAt & 15];
                            } else {
                                charAt = (char) i4;
                            }
                        }
                        sb.append(charAt);
                    }
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

    public Rm(Rm rm, int i) {
        super.A01 = i;
        this.A02 = rm;
        super.A00 = -1;
    }
}
