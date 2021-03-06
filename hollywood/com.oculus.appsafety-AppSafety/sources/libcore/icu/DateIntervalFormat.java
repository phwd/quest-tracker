package libcore.icu;

import android.icu.util.Calendar;
import android.icu.util.ULocale;
import java.text.FieldPosition;
import java.util.TimeZone;
import libcore.util.BasicLruCache;

public final class DateIntervalFormat {
    private static final BasicLruCache<String, android.icu.text.DateIntervalFormat> CACHED_FORMATTERS = new BasicLruCache<>(8);

    private DateIntervalFormat() {
    }

    public static String formatDateRange(long startMs, long endMs, int flags, String olsonId) {
        if ((flags & 8192) != 0) {
            olsonId = "UTC";
        }
        return formatDateRange(ULocale.getDefault(), DateUtilsBridge.icuTimeZone(olsonId != null ? TimeZone.getTimeZone(olsonId) : TimeZone.getDefault()), startMs, endMs, flags);
    }

    public static String formatDateRange(ULocale icuLocale, android.icu.util.TimeZone icuTimeZone, long startMs, long endMs, int flags) {
        Calendar endCalendar;
        String stringBuffer;
        Calendar startCalendar = DateUtilsBridge.createIcuCalendar(icuTimeZone, icuLocale, startMs);
        if (startMs == endMs) {
            endCalendar = startCalendar;
        } else {
            endCalendar = DateUtilsBridge.createIcuCalendar(icuTimeZone, icuLocale, endMs);
        }
        if (isExactlyMidnight(endCalendar)) {
            boolean endsDayAfterStart = true;
            boolean showTime = (flags & 1) == 1;
            if (DateUtilsBridge.dayDistance(startCalendar, endCalendar) != 1) {
                endsDayAfterStart = false;
            }
            if ((!showTime && startMs != endMs) || (endsDayAfterStart && !DateUtilsBridge.isDisplayMidnightUsingSkeleton(startCalendar))) {
                endCalendar.add(5, -1);
            }
        }
        String skeleton = DateUtilsBridge.toSkeleton(startCalendar, endCalendar, flags);
        synchronized (CACHED_FORMATTERS) {
            stringBuffer = getFormatter(skeleton, icuLocale, icuTimeZone).format(startCalendar, endCalendar, new StringBuffer(), new FieldPosition(0)).toString();
        }
        return stringBuffer;
    }

    private static android.icu.text.DateIntervalFormat getFormatter(String skeleton, ULocale locale, android.icu.util.TimeZone icuTimeZone) {
        String key = skeleton + "\t" + ((Object) locale) + "\t" + ((Object) icuTimeZone);
        android.icu.text.DateIntervalFormat formatter = CACHED_FORMATTERS.get(key);
        if (formatter != null) {
            return formatter;
        }
        android.icu.text.DateIntervalFormat formatter2 = android.icu.text.DateIntervalFormat.getInstance(skeleton, locale);
        formatter2.setTimeZone(icuTimeZone);
        CACHED_FORMATTERS.put(key, formatter2);
        return formatter2;
    }

    private static boolean isExactlyMidnight(Calendar c) {
        return c.get(11) == 0 && c.get(12) == 0 && c.get(13) == 0 && c.get(14) == 0;
    }
}
