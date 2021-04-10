package com.facebook.common.string;

import com.facebook.common.stringformat.StringFormatUtil;

public class StringUtil {
    public static String formatStrLocaleSafe(String str, Object... args) {
        return StringFormatUtil.formatStrLocaleSafe(str, args);
    }
}
