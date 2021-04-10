package X;

import org.apache.commons.cli.HelpFormatter;

/* renamed from: X.1Sj  reason: invalid class name */
public final class AnonymousClass1Sj {
    public final String A00;

    /* JADX WARNING: Removed duplicated region for block: B:15:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0101  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass1Sj(android.content.Context r24, com.facebook.msys.mci.AppInfo r25) {
        /*
        // Method dump skipped, instructions count: 315
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1Sj.<init>(android.content.Context, com.facebook.msys.mci.AppInfo):void");
    }

    public static String A00(String str) {
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
}
