package com.facebook.common.util;

import com.facebook.annotations.DoNotOptimize;
import com.facebook.annotations.DoNotRename;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.infer.annotation.PropagatesNullable;
import java.util.Locale;

@DoNotRename
@Nullsafe(Nullsafe.Mode.LOCAL)
public class StringLocaleUtil {
    @Deprecated
    public static final String formatStrLocaleSafe(String str) {
        return StringFormatUtil.formatStrLocaleSafe(str);
    }

    @Deprecated
    public static final String formatStrLocaleSafe(String str, Object arg) {
        return StringFormatUtil.formatStrLocaleSafe(str, arg);
    }

    @Deprecated
    public static final String formatStrLocaleSafe(String str, Object arg1, Object arg2) {
        return StringFormatUtil.formatStrLocaleSafe(str, arg1, arg2);
    }

    @Deprecated
    public static final String formatStrLocaleSafe(String str, Object arg1, Object arg2, Object arg3) {
        return StringFormatUtil.formatStrLocaleSafe(str, arg1, arg2, arg3);
    }

    @Deprecated
    public static final String formatStrLocaleSafe(String str, Object arg1, Object arg2, Object arg3, Object arg4) {
        return StringFormatUtil.formatStrLocaleSafe(str, arg1, arg2, arg3, arg4);
    }

    @Deprecated
    public static final String formatStrLocaleSafe(String str, Object... args) {
        return StringFormatUtil.formatStrLocaleSafe(str, args);
    }

    public static final String formatStrLocaleSensitive(String str, Object... args) {
        return String.format(Locale.getDefault(), str, args);
    }

    @DoNotOptimize
    public static final String toLowerCaseLocaleSafe(@PropagatesNullable String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase(Locale.US);
    }

    @DoNotOptimize
    public static final String toUpperCaseLocaleSafe(@PropagatesNullable String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase(Locale.US);
    }
}
