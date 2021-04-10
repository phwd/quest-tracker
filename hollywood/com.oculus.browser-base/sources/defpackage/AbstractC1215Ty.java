package defpackage;

/* renamed from: Ty  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1215Ty {
    public static String a(String[] strArr, String str) {
        if (strArr == null) {
            return null;
        }
        String g = AbstractC2531fV.g("--", str, "=");
        for (String str2 : strArr) {
            if (str2 != null && str2.startsWith(g)) {
                return str2.substring(g.length());
            }
        }
        return null;
    }
}
