package okhttp3;

import X.AnonymousClass006;
import java.nio.charset.Charset;
import okio.ByteString;

public final class Credentials {
    public static String basic(String str, String str2) {
        return basic(str, str2, Charset.forName("ISO-8859-1"));
    }

    public static String basic(String str, String str2, Charset charset) {
        return AnonymousClass006.A07("Basic ", ByteString.of(AnonymousClass006.A09(str, ":", str2).getBytes(charset)).base64());
    }
}
