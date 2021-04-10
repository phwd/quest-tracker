package X;

import java.util.StringTokenizer;

/* renamed from: X.0of  reason: invalid class name and case insensitive filesystem */
public final class C07060of extends StringTokenizer {
    public int A00;
    public String A01;
    public final String A02;

    public C07060of(String str) {
        super(str, "<,>", true);
        this.A02 = str;
    }

    public final boolean hasMoreTokens() {
        if (this.A01 != null || super.hasMoreTokens()) {
            return true;
        }
        return false;
    }

    public final String nextToken() {
        String str = this.A01;
        if (str != null) {
            this.A01 = null;
        } else {
            str = super.nextToken();
        }
        this.A00 += str.length();
        return str;
    }
}
