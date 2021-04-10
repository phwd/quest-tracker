package okhttp3.internal.http;

import X.AnonymousClass006;
import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Protocol;
import okhttp3.Response;

public final class StatusLine {
    public static final int HTTP_CONTINUE = 100;
    public static final int HTTP_PERM_REDIRECT = 308;
    public static final int HTTP_TEMP_REDIRECT = 307;
    public final int code;
    public final String message;
    public final Protocol protocol;

    public static StatusLine get(Response response) {
        return new StatusLine(response.protocol, response.code, response.message);
    }

    public static StatusLine parse(String str) throws IOException {
        Protocol protocol2;
        String str2;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException(AnonymousClass006.A07("Unexpected status line: ", str));
            }
            int charAt = str.charAt(7) - '0';
            if (charAt == 0) {
                protocol2 = Protocol.HTTP_1_0;
            } else if (charAt == 1) {
                protocol2 = Protocol.HTTP_1_1;
            } else {
                throw new ProtocolException(AnonymousClass006.A07("Unexpected status line: ", str));
            }
        } else if (str.startsWith("ICY ")) {
            protocol2 = Protocol.HTTP_1_0;
            i = 4;
        } else {
            throw new ProtocolException(AnonymousClass006.A07("Unexpected status line: ", str));
        }
        int length = str.length();
        int i2 = i + 3;
        if (length >= i2) {
            try {
                int parseInt = Integer.parseInt(str.substring(i, i2));
                if (length <= i2) {
                    str2 = "";
                } else if (str.charAt(i2) == ' ') {
                    str2 = str.substring(i + 4);
                } else {
                    throw new ProtocolException(AnonymousClass006.A07("Unexpected status line: ", str));
                }
                return new StatusLine(protocol2, parseInt, str2);
            } catch (NumberFormatException unused) {
                throw new ProtocolException(AnonymousClass006.A07("Unexpected status line: ", str));
            }
        } else {
            throw new ProtocolException(AnonymousClass006.A07("Unexpected status line: ", str));
        }
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.protocol == Protocol.HTTP_1_0) {
            str = "HTTP/1.0";
        } else {
            str = "HTTP/1.1";
        }
        sb.append(str);
        sb.append(' ');
        sb.append(this.code);
        String str2 = this.message;
        if (str2 != null) {
            sb.append(' ');
            sb.append(str2);
        }
        return sb.toString();
    }

    public StatusLine(Protocol protocol2, int i, String str) {
        this.protocol = protocol2;
        this.code = i;
        this.message = str;
    }
}
