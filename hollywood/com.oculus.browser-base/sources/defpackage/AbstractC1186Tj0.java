package defpackage;

import java.util.List;
import java.util.Map;

/* renamed from: Tj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1186Tj0 {
    public static final String a(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }

    public static final void b(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                b(sb, i, str, obj2);
            }
        } else if (obj instanceof Map) {
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                b(sb, i, str, entry);
            }
        } else {
            sb.append('\n');
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                sb.append(' ');
            }
            sb.append(str);
            if (obj instanceof String) {
                sb.append(": \"");
                AbstractC1248Uk uk = AbstractC1248Uk.F;
                sb.append(AbstractC3928ng1.a(new C1309Vk(((String) obj).getBytes(F30.f7992a))));
                sb.append('\"');
            } else if (obj instanceof AbstractC1248Uk) {
                sb.append(": \"");
                sb.append(AbstractC3928ng1.a((AbstractC1248Uk) obj));
                sb.append('\"');
            } else if (obj instanceof AbstractC2360eV) {
                sb.append(" {");
                c((AbstractC2360eV) obj, sb, i + 2);
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else if (obj instanceof Map.Entry) {
                sb.append(" {");
                Map.Entry entry2 = (Map.Entry) obj;
                int i4 = i + 2;
                b(sb, i4, "key", entry2.getKey());
                b(sb, i4, "value", entry2.getValue());
                sb.append("\n");
                while (i2 < i) {
                    sb.append(' ');
                    i2++;
                }
                sb.append("}");
            } else {
                sb.append(": ");
                sb.append(obj.toString());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01f7, code lost:
        if (((java.lang.Integer) r4).intValue() == 0) goto L_0x0255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0208, code lost:
        if (((java.lang.Float) r4).floatValue() == 0.0f) goto L_0x0255;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x021a, code lost:
        if (((java.lang.Double) r4).doubleValue() == 0.0d) goto L_0x0255;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(defpackage.AbstractC1125Sj0 r13, java.lang.StringBuilder r14, int r15) {
        /*
        // Method dump skipped, instructions count: 658
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1186Tj0.c(Sj0, java.lang.StringBuilder, int):void");
    }
}
