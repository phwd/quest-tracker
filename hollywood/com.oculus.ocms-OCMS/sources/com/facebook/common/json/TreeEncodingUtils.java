package com.facebook.common.json;

import com.facebook.common.stringformat.StringFormatUtil;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

class TreeEncodingUtils {
    TreeEncodingUtils() {
    }

    static String formatTypeTagPrefix(int i) {
        return StringFormatUtil.formatStrLocaleSafe("type_tag:%08x;", Integer.valueOf(i));
    }

    static int getTypeTag(@Nullable String str) {
        if (str == null) {
            return 0;
        }
        Matcher matcher = Pattern.compile("^type_tag:([0-9a-zA-Z]{8});").matcher(str);
        if (!matcher.find() || matcher.groupCount() != 1) {
            return 0;
        }
        return (int) Long.parseLong(matcher.group(1), 16);
    }

    @Nullable
    static String stripTypeTagPrefix(@Nullable String str) {
        return (str != null && str.startsWith("type_tag:")) ? str.substring(18) : str;
    }
}
