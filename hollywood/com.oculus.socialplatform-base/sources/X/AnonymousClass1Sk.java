package X;

import android.content.Context;
import org.apache.commons.cli.HelpFormatter;

/* renamed from: X.1Sk  reason: invalid class name */
public final class AnonymousClass1Sk {
    public final Context A00;
    public final AnonymousClass1Qn A01;
    public final String A02;

    private String A00(String str) {
        String str2;
        String str3;
        if (str != null) {
            int length = str.length();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt == '&') {
                    str3 = "&amp;";
                } else if (charAt < ' ' || charAt > '~') {
                    sb.append("&#");
                    sb.append(Integer.toString(charAt));
                    str3 = ";";
                } else {
                    sb.append(charAt);
                }
                sb.append(str3);
            }
            str2 = sb.toString();
        } else {
            str2 = "";
        }
        return str2.replace("/", HelpFormatter.DEFAULT_OPT_PREFIX).replace(";", HelpFormatter.DEFAULT_OPT_PREFIX);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0127  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String A01() {
        /*
        // Method dump skipped, instructions count: 298
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1Sk.A01():java.lang.String");
    }

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;LX/1Qn;Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;Ljava/lang/String;)V */
    public AnonymousClass1Sk(Context context, AnonymousClass1Qn r2, String str) {
        this.A00 = context;
        this.A01 = r2;
        this.A02 = str;
    }
}
