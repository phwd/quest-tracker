package X;

import java.io.IOException;
import java.net.ProtocolException;

/* renamed from: X.Wt  reason: case insensitive filesystem */
public final class C0168Wt {
    public final int A00;
    public final String A01;
    public final XP A02;

    public static C0168Wt A00(String str) throws IOException {
        XP xp;
        String str2;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() >= 9 && str.charAt(8) == ' ') {
                int charAt = str.charAt(7) - '0';
                if (charAt == 0) {
                    xp = XP.HTTP_1_0;
                } else if (charAt == 1) {
                    xp = XP.HTTP_1_1;
                }
            }
            throw new ProtocolException(AnonymousClass06.A03("Unexpected status line: ", str));
        }
        if (str.startsWith("ICY ")) {
            xp = XP.HTTP_1_0;
            i = 4;
        }
        throw new ProtocolException(AnonymousClass06.A03("Unexpected status line: ", str));
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
                return new C0168Wt(xp, parseInt, str2);
            } catch (NumberFormatException unused) {
                throw new ProtocolException(AnonymousClass06.A03("Unexpected status line: ", str));
            }
        }
        throw new ProtocolException(AnonymousClass06.A03("Unexpected status line: ", str));
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.A02 == XP.HTTP_1_0) {
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

    public C0168Wt(XP xp, int i, String str) {
        this.A02 = xp;
        this.A00 = i;
        this.A01 = str;
    }
}
