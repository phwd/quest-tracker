package libraries.marauder.analytics.utils;

import android.text.TextUtils;

public class StringUtil {
    public static String formatStrLocaleSafe(String str, Object... objArr) {
        return String.format(null, str, objArr);
    }

    public static boolean isEmptyOrNull(String str) {
        return TextUtils.isEmpty(str);
    }
}
