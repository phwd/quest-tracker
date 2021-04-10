package com.facebook.common.string;

import com.facebook.common.stringformat.StringFormatUtil;

public class StringUtil {
    protected StringUtil() {
    }

    public static String formatStrLocaleSafe(String str, Object... objArr) {
        return StringFormatUtil.formatStrLocaleSafe(str, objArr);
    }
}
