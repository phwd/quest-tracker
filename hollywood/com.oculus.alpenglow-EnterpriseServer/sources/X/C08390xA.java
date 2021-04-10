package X;

import android.content.Context;

/* renamed from: X.0xA  reason: invalid class name and case insensitive filesystem */
public final class C08390xA {
    public final Context A00;
    public final C08090wY A01;
    public final String A02 = "MQTT";

    /* JADX WARN: Incorrect args count in method signature: (Landroid/content/Context;LX/0wY;Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;Ljava/lang/String;)V */
    public C08390xA(Context context, C08090wY r3) {
        this.A00 = context;
        this.A01 = r3;
    }

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
        return str2.replace("/", "-").replace(";", "-");
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
        throw new UnsupportedOperationException("Method not decompiled: X.C08390xA.A01():java.lang.String");
    }
}
