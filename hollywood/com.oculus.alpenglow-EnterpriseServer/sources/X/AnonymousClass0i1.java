package X;

import java.io.IOException;
import java.net.ProtocolException;

/* renamed from: X.0i1  reason: invalid class name */
public final class AnonymousClass0i1 {
    public final int A00;
    public final String A01;
    public final AnonymousClass0kh A02;

    public static AnonymousClass0i1 A00(String str) throws IOException {
        AnonymousClass0kh r4;
        String str2;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() >= 9 && str.charAt(8) == ' ') {
                int charAt = str.charAt(7) - '0';
                if (charAt == 0) {
                    r4 = AnonymousClass0kh.HTTP_1_0;
                } else if (charAt == 1) {
                    r4 = AnonymousClass0kh.HTTP_1_1;
                }
            }
            throw new ProtocolException(AnonymousClass006.A05("Unexpected status line: ", str));
        }
        if (str.startsWith("ICY ")) {
            r4 = AnonymousClass0kh.HTTP_1_0;
            i = 4;
        }
        throw new ProtocolException(AnonymousClass006.A05("Unexpected status line: ", str));
        int length = str.length();
        int i2 = i + 3;
        if (length >= i2) {
            try {
                int parseInt = Integer.parseInt(str.substring(i, i2));
                if (length <= i2) {
                    str2 = "";
                } else if (str.charAt(i2) == ' ') {
                    str2 = str.substring(i + 4);
                }
                return new AnonymousClass0i1(r4, parseInt, str2);
            } catch (NumberFormatException unused) {
                throw new ProtocolException(AnonymousClass006.A05("Unexpected status line: ", str));
            }
        }
        throw new ProtocolException(AnonymousClass006.A05("Unexpected status line: ", str));
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.A02 == AnonymousClass0kh.HTTP_1_0) {
            str = "HTTP/1.0";
        } else {
            str = "HTTP/1.1";
        }
        sb.append(str);
        sb.append(' ');
        sb.append(this.A00);
        String str2 = this.A01;
        if (str2 != null) {
            sb.append(' ');
            sb.append(str2);
        }
        return sb.toString();
    }

    public AnonymousClass0i1(AnonymousClass0kh r1, int i, String str) {
        this.A02 = r1;
        this.A00 = i;
        this.A01 = str;
    }
}
