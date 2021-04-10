package com.facebook.common.util;

import com.facebook.debug.log.LoggingUtil;
import com.google.common.base.Joiner;
import java.util.Map;
import javax.annotation.Nullable;

public class MoreMaps {
    private static final Joiner STANDARD_JOINER = Joiner.on(", ");
    private static final Joiner.MapJoiner STANDARD_MAP_JOINER = STANDARD_JOINER.withKeyValueSeparator("=").useForNull(LoggingUtil.NO_HASHCODE);

    private MoreMaps() {
    }

    public static String toString(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        STANDARD_MAP_JOINER.appendTo(sb, map);
        sb.append('}');
        return sb.toString();
    }

    public static boolean isNullOrEmpty(@Nullable Map<?, ?> map) {
        return map == null || map.isEmpty();
    }
}
