package X;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;

/* renamed from: X.0Ix  reason: invalid class name and case insensitive filesystem */
public final class C00780Ix {
    public static void A00(Class<?> cls, AbstractC02280iQ r10, Exception exc) throws IOException, C02290iR {
        String str;
        try {
            Object A0O = r10.A0O();
            StringBuilder sb = new StringBuilder("current token: ");
            sb.append(r10.A0m());
            sb.append("\n");
            if (A0O instanceof InputStream) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.flush();
                char[] charArray = byteArrayOutputStream.toString().toCharArray();
                sb.append(charArray, 0, Math.min(charArray.length, 100 - sb.length()));
                byteArrayOutputStream.close();
                InputStream inputStream = (InputStream) A0O;
                while (true) {
                    int read = inputStream.read();
                    if (read == -1 || sb.length() >= 100) {
                        break;
                    }
                    sb.append((char) read);
                }
            } else if (A0O instanceof Reader) {
                StringWriter stringWriter = new StringWriter();
                r10.A0M(stringWriter);
                stringWriter.flush();
                char[] charArray2 = stringWriter.toString().toCharArray();
                sb.append(charArray2, 0, Math.min(charArray2.length, 100 - sb.length()));
                stringWriter.close();
                Reader reader = (Reader) A0O;
                while (true) {
                    int read2 = reader.read();
                    if (read2 == -1 || sb.length() >= 100) {
                        reader.close();
                    } else {
                        sb.append((char) read2);
                    }
                }
                reader.close();
            }
            if (sb.length() == 100) {
                sb.append("...");
            }
            str = sb.toString();
        } catch (Exception unused) {
            str = "failed to get parser text";
        }
        throw new C02290iR(AnonymousClass006.A0B("Failed to deserialize to instance ", cls.getSimpleName(), "\n", str), r10.A0V(), exc);
    }
}
