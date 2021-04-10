package defpackage;

import java.util.Set;
import java.util.regex.PatternSyntaxException;

/* renamed from: Yp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1503Yp0 {
    public static C1442Xp0 a(String str) {
        Set<String> k;
        C1442Xp0 xp0;
        if (str == null || (k = NU0.f8549a.k("send_tab_to_self.notification.active", null)) == null) {
            return null;
        }
        for (String str2 : k) {
            try {
                String[] split = str2.split("_");
                if (split.length != 3) {
                    xp0 = null;
                    if (xp0 == null && str.equals(xp0.b)) {
                        return xp0;
                    }
                } else {
                    xp0 = new C1442Xp0(Integer.parseInt(split[0]), Integer.parseInt(split[1]), split[2]);
                    if (xp0 == null) {
                    }
                }
            } catch (NumberFormatException | PatternSyntaxException unused) {
            }
        }
        return null;
    }

    public static String b(C1442Xp0 xp0) {
        return xp0.c + "_" + xp0.f9236a + "_" + xp0.b;
    }
}
