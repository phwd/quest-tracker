package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.net.ProtocolException;

public final class cG {
    public final int A00;
    public final String A01;
    public final EnumC0549bm A02;

    public static cG A00(String str) {
        EnumC0549bm bmVar;
        String str2;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException(AnonymousClass08.A04("Unexpected status line: ", str));
            }
            int charAt = str.charAt(7) - '0';
            if (charAt == 0) {
                bmVar = EnumC0549bm.HTTP_1_0;
            } else if (charAt == 1) {
                bmVar = EnumC0549bm.HTTP_1_1;
            } else {
                throw new ProtocolException(AnonymousClass08.A04("Unexpected status line: ", str));
            }
        } else if (str.startsWith("ICY ")) {
            bmVar = EnumC0549bm.HTTP_1_0;
            i = 4;
        } else {
            throw new ProtocolException(AnonymousClass08.A04("Unexpected status line: ", str));
        }
        int length = str.length();
        int i2 = i + 3;
        if (length >= i2) {
            try {
                int parseInt = Integer.parseInt(str.substring(i, i2));
                if (length <= i2) {
                    str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
                } else if (str.charAt(i2) == ' ') {
                    str2 = str.substring(i + 4);
                } else {
                    throw new ProtocolException(AnonymousClass08.A04("Unexpected status line: ", str));
                }
                return new cG(bmVar, parseInt, str2);
            } catch (NumberFormatException unused) {
                throw new ProtocolException(AnonymousClass08.A04("Unexpected status line: ", str));
            }
        } else {
            throw new ProtocolException(AnonymousClass08.A04("Unexpected status line: ", str));
        }
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.A02 == EnumC0549bm.HTTP_1_0) {
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

    public cG(EnumC0549bm bmVar, int i, String str) {
        this.A02 = bmVar;
        this.A00 = i;
        this.A01 = str;
    }
}
