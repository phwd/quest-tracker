package defpackage;

import com.oculus.os.Version;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.EnumMap;

/* renamed from: dB  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2141dB extends EnumMap {
    public final Object F = new Object();

    public C2141dB() {
        super(XL0.class);
    }

    public final void a(Appendable appendable, String str, boolean z) {
        int i;
        int length = str.length();
        if (z || length <= 0 || str.charAt(0) != ' ') {
            i = 0;
        } else {
            appendable.append("\\ ");
            i = 1;
        }
        while (i < length) {
            char charAt = str.charAt(i);
            switch (charAt) {
                case Version.VERSION_9:
                    appendable.append("\\t");
                    break;
                case Version.VERSION_10:
                    appendable.append("\\n");
                    break;
                case Version.VERSION_11:
                default:
                    if ((z && charAt == ' ') || charAt == '\\' || charAt == '#' || charAt == '!' || charAt == ':') {
                        appendable.append('\\');
                    }
                    if (charAt < ' ' || charAt > '~') {
                        String hexString = Integer.toHexString(charAt);
                        appendable.append("\\u");
                        for (int i2 = 0; i2 < 4 - hexString.length(); i2++) {
                            appendable.append('0');
                        }
                        appendable.append(hexString);
                        break;
                    } else {
                        appendable.append(charAt);
                        break;
                    }
                    break;
                case Version.VERSION_12:
                    appendable.append("\\f");
                    break;
                case Version.VERSION_13:
                    appendable.append("\\r");
                    break;
            }
            i++;
        }
    }

    public String b(XL0 xl0) {
        return (String) get(xl0);
    }

    public void c(InputStream inputStream) {
        boolean z;
        synchronized (this.F) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedInputStream.mark(Integer.MAX_VALUE);
            while (true) {
                byte read = (byte) bufferedInputStream.read();
                z = false;
                if (read != -1 && read != 35 && read != 10) {
                    if (read != 61) {
                        if (read == 21) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            bufferedInputStream.reset();
            if (!z) {
                d(new InputStreamReader(bufferedInputStream, "ISO8859-1"));
            } else {
                d(new InputStreamReader(bufferedInputStream));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:104:0x010a, code lost:
        if (r11 != 3) goto L_0x010d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(java.io.Reader r18) {
        /*
        // Method dump skipped, instructions count: 414
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2141dB.d(java.io.Reader):void");
    }

    public void e(C2141dB dBVar) {
        for (XL0 xl0 : dBVar.keySet()) {
            if (b(xl0) == null) {
                put((Enum) xl0, (Object) dBVar.b(xl0));
            }
        }
    }

    public String f(XL0 xl0, String str, Writer writer) {
        String str2 = (String) put((Enum) xl0, (Object) str);
        if (writer != null) {
            synchronized (this.F) {
                String str3 = xl0.toString();
                if (str == null) {
                    str = "";
                }
                int length = str3.length() + str.length() + 1;
                StringBuilder sb = new StringBuilder(length + (length / 5));
                a(sb, str3, true);
                sb.append('=');
                a(sb, str, false);
                sb.append("\n");
                writer.write(sb.toString());
                writer.flush();
            }
        }
        return str2;
    }
}
