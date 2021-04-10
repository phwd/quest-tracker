package X;

public final class L3 {
    public final StringBuilder A00;

    public final void A00(String str) {
        StringBuilder sb = this.A00;
        sb.append('|');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == 0 || charAt == '\r' || charAt == ';' || charAt == '|' || charAt == '\t' || charAt == '\n') {
                charAt = ' ';
            }
            sb.append(charAt);
        }
    }

    public final void A01(String[] strArr, int i) {
        StringBuilder sb = this.A00;
        sb.append('|');
        for (int i2 = 1; i2 < i; i2 += 2) {
            String str = strArr[i2 - 1];
            String str2 = strArr[i2];
            sb.append(str);
            sb.append('=');
            sb.append(str2);
            if (i2 < i - 1) {
                sb.append(';');
            }
        }
    }

    public final String toString() {
        return this.A00.toString();
    }

    public L3(char c) {
        StringBuilder sb = new StringBuilder(1024);
        this.A00 = sb;
        sb.append(c);
    }
}
