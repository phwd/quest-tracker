package X;

/* renamed from: X.0ji  reason: invalid class name */
public class AnonymousClass0ji {
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

    public final String toString() {
        return this.A00.toString();
    }

    public AnonymousClass0ji(char c) {
        StringBuilder sb = new StringBuilder(1024);
        this.A00 = sb;
        sb.append(c);
    }
}
