package X;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;

public final class CH {
    public static void A00(Class cls, AbstractC1014qi qiVar, Exception exc) {
        String str;
        Object obj;
        try {
            if (qiVar instanceof VD) {
                obj = ((VD) qiVar).A00.A0a();
            } else if (!(qiVar instanceof AnonymousClass7v)) {
                obj = null;
            } else {
                obj = ((AnonymousClass7v) qiVar).A01;
            }
            StringBuilder sb = new StringBuilder("current token: ");
            sb.append(qiVar.A0p());
            sb.append("\n");
            if (obj instanceof InputStream) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.flush();
                char[] charArray = byteArrayOutputStream.toString().toCharArray();
                sb.append(charArray, 0, Math.min(charArray.length, 100 - sb.length()));
                byteArrayOutputStream.close();
                InputStream inputStream = (InputStream) obj;
                while (true) {
                    int read = inputStream.read();
                    if (read == -1 || sb.length() >= 100) {
                        break;
                    }
                    sb.append((char) read);
                }
            } else if (obj instanceof Reader) {
                StringWriter stringWriter = new StringWriter();
                if (qiVar instanceof AnonymousClass7v) {
                    AnonymousClass7v r3 = (AnonymousClass7v) qiVar;
                    int i = ((AnonymousClass2L) r3).A03;
                    int i2 = ((AnonymousClass2L) r3).A04;
                    int i3 = i - i2;
                    if (i3 >= 1) {
                        stringWriter.write(r3.A03, i2, i3);
                    }
                }
                stringWriter.flush();
                char[] charArray2 = stringWriter.toString().toCharArray();
                sb.append(charArray2, 0, Math.min(charArray2.length, 100 - sb.length()));
                stringWriter.close();
                Reader reader = (Reader) obj;
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
        throw new C1013qh(AnonymousClass08.A06("Failed to deserialize to instance ", cls.getSimpleName(), "\n", str), qiVar.A0R(), exc);
    }
}
