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
    public static final String formatStrLocaleSafe(String str, Object obj) {
        return StringFormatUtil.formatStrLocaleSafe(str, obj);
    }

    @Deprecated
    public static final String formatStrLocaleSafe(String str, Object obj, Object obj2) {
        return StringFormatUtil.formatStrLocaleSafe(str, obj, obj2);
    }

    @Deprecated
    public static final String formatStrLocaleSafe(String str, Object obj, Object obj2, Object obj3) {
        return StringFormatUtil.formatStrLocaleSafe(str, obj, obj2, obj3);
    }

    @Deprecated
    public static final String formatStrLocaleSafe(String str, Object obj, Object obj2, Object obj3, Object obj4) {
        return StringFormatUtil.formatStrLocaleSafe(str, obj, obj2, obj3, obj4);
    }

    @Deprecated
    public static final String formatStrLocaleSafe(String str, Object... objArr) {
        return StringFormatUtil.formatStrLocaleSafe(str, objArr);
    }

    public static final String formatStrLocaleSensitive(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
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
