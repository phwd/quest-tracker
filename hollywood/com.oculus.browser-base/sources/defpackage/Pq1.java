package defpackage;

import java.util.HashSet;

/* renamed from: Pq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Pq1 {

    /* renamed from: a  reason: collision with root package name */
    public static final HashSet f8716a = AbstractC0417Gv.d("about", "data", "file", "ftp", "inline", "javascript", "chrome");
    public static final HashSet b = AbstractC0417Gv.d("about", "data", "file", "ftp", "http", "https", "inline", "javascript", "chrome");
    public static final Pq1 c = c("");
    public final String d;
    public final CharSequence e;
    public final String f;
    public final int g;
    public final int h;

    public Pq1(String str, CharSequence charSequence, int i, int i2, String str2) {
        this.d = str;
        this.e = charSequence;
        this.g = i;
        this.h = i2;
        this.f = str2;
    }

    public static Pq1 a(String str, CharSequence charSequence, int i, int i2, String str2) {
        return new Pq1(str, charSequence, i, i2, str2);
    }

    public static int b(CharSequence charSequence, int i) {
        while (i < charSequence.length()) {
            char charAt = charSequence.charAt(i);
            if (charAt != ':' && charAt != '/') {
                return i;
            }
            i++;
        }
        return charSequence.length();
    }

    public static Pq1 c(String str) {
        return a(null, str, 0, 0, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static defpackage.Pq1 d(java.lang.String r5, java.lang.CharSequence r6, java.lang.String r7) {
        /*
        // Method dump skipped, instructions count: 142
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.Pq1.d(java.lang.String, java.lang.CharSequence, java.lang.String):Pq1");
    }
}
