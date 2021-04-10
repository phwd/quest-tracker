package defpackage;

import android.text.TextUtils;
import java.util.Locale;

/* renamed from: tH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4891tH {
    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 6;
        }
        Integer num = str.equals("application/ogg") ? 3 : null;
        if (num != null) {
            return num.intValue();
        }
        String[] split = str.toLowerCase(Locale.getDefault()).split("/");
        if (split.length != 2) {
            return 6;
        }
        if ("video".equals(split[0])) {
            return 2;
        }
        if ("audio".equals(split[0])) {
            return 3;
        }
        if ("image".equals(split[0])) {
            return 4;
        }
        if ("text".equals(split[0])) {
            return 5;
        }
        return 6;
    }
}
