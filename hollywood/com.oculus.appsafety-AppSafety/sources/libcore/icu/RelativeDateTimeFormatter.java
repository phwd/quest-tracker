package libcore.icu;

import android.icu.text.ArabicShaping;
import android.icu.text.DisplayContext;
import android.icu.text.RelativeDateTimeFormatter;
import android.icu.util.Calendar;
import android.icu.util.ULocale;
import java.util.Locale;
import java.util.TimeZone;
import libcore.util.BasicLruCache;

public final class RelativeDateTimeFormatter {
    private static final FormatterCache CACHED_FORMATTERS = new FormatterCache();
    public static final long DAY_IN_MILLIS = 86400000;
    private static final int DAY_IN_MS = 86400000;
    private static final int EPOCH_JULIAN_DAY = 2440588;
    public static final long HOUR_IN_MILLIS = 3600000;
    public static final long MINUTE_IN_MILLIS = 60000;
    public static final long SECOND_IN_MILLIS = 1000;
    public static final long WEEK_IN_MILLIS = 604800000;
    public static final long YEAR_IN_MILLIS = 31449600000L;

    /* access modifiers changed from: package-private */
    public static class FormatterCache extends BasicLruCache<String, android.icu.text.RelativeDateTimeFormatter> {
        FormatterCache() {
            super(8);
        }
    }

    private RelativeDateTimeFormatter() {
    }

    public static String getRelativeTimeSpanString(Locale locale, TimeZone tz, long time, long now, long minResolution, int flags) {
        return getRelativeTimeSpanString(locale, tz, time, now, minResolution, flags, DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE);
    }

    public static String getRelativeTimeSpanString(Locale locale, TimeZone tz, long time, long now, long minResolution, int flags, DisplayContext displayContext) {
        if (locale == null) {
            throw new NullPointerException("locale == null");
        } else if (tz != null) {
            return getRelativeTimeSpanString(ULocale.forLocale(locale), DateUtilsBridge.icuTimeZone(tz), time, now, minResolution, flags, displayContext);
        } else {
            throw new NullPointerException("tz == null");
        }
    }

    private static String getRelativeTimeSpanString(ULocale icuLocale, android.icu.util.TimeZone icuTimeZone, long time, long now, long minResolution, int flags, DisplayContext displayContext) {
        RelativeDateTimeFormatter.Style style;
        RelativeDateTimeFormatter.Direction direction;
        RelativeDateTimeFormatter.RelativeUnit unit;
        boolean relative;
        RelativeDateTimeFormatter.AbsoluteUnit aunit;
        RelativeDateTimeFormatter.Direction direction2;
        int count;
        int flags2;
        RelativeDateTimeFormatter.RelativeUnit unit2;
        String str;
        long duration = Math.abs(now - time);
        boolean past = now >= time;
        if ((flags & ArabicShaping.TASHKEEL_REPLACE_BY_TATWEEL) != 0) {
            style = RelativeDateTimeFormatter.Style.SHORT;
        } else {
            style = RelativeDateTimeFormatter.Style.LONG;
        }
        if (past) {
            direction = RelativeDateTimeFormatter.Direction.LAST;
        } else {
            direction = RelativeDateTimeFormatter.Direction.NEXT;
        }
        if (duration < MINUTE_IN_MILLIS && minResolution < MINUTE_IN_MILLIS) {
            aunit = null;
            direction2 = direction;
            relative = true;
            unit = RelativeDateTimeFormatter.RelativeUnit.SECONDS;
            count = (int) (duration / 1000);
        } else if (duration < HOUR_IN_MILLIS && minResolution < HOUR_IN_MILLIS) {
            int count2 = (int) (duration / MINUTE_IN_MILLIS);
            aunit = null;
            direction2 = direction;
            relative = true;
            unit = RelativeDateTimeFormatter.RelativeUnit.MINUTES;
            count = count2;
        } else if (duration < 86400000 && minResolution < 86400000) {
            int count3 = (int) (duration / HOUR_IN_MILLIS);
            aunit = null;
            direction2 = direction;
            relative = true;
            unit = RelativeDateTimeFormatter.RelativeUnit.HOURS;
            count = count3;
        } else if (duration < WEEK_IN_MILLIS && minResolution < WEEK_IN_MILLIS) {
            int count4 = Math.abs(dayDistance(icuTimeZone, time, now));
            RelativeDateTimeFormatter.RelativeUnit unit3 = RelativeDateTimeFormatter.RelativeUnit.DAYS;
            if (count4 == 2) {
                if (past) {
                    synchronized (CACHED_FORMATTERS) {
                        try {
                            try {
                                unit2 = unit3;
                                str = getFormatter(icuLocale, style, displayContext).format(RelativeDateTimeFormatter.Direction.LAST_2, RelativeDateTimeFormatter.AbsoluteUnit.DAY);
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                } else {
                    unit2 = unit3;
                    synchronized (CACHED_FORMATTERS) {
                        str = getFormatter(icuLocale, style, displayContext).format(RelativeDateTimeFormatter.Direction.NEXT_2, RelativeDateTimeFormatter.AbsoluteUnit.DAY);
                    }
                }
                if (str != null && !str.isEmpty()) {
                    return str;
                }
            } else {
                unit2 = unit3;
                if (count4 == 1) {
                    relative = false;
                    count = count4;
                    aunit = RelativeDateTimeFormatter.AbsoluteUnit.DAY;
                    unit = unit2;
                    direction2 = direction;
                } else if (count4 == 0) {
                    RelativeDateTimeFormatter.AbsoluteUnit aunit2 = RelativeDateTimeFormatter.AbsoluteUnit.DAY;
                    relative = false;
                    direction2 = RelativeDateTimeFormatter.Direction.THIS;
                    count = count4;
                    aunit = aunit2;
                    unit = unit2;
                }
            }
            count = count4;
            aunit = null;
            unit = unit2;
            direction2 = direction;
            relative = true;
        } else if (minResolution == WEEK_IN_MILLIS) {
            int count5 = (int) (duration / WEEK_IN_MILLIS);
            aunit = null;
            direction2 = direction;
            relative = true;
            unit = RelativeDateTimeFormatter.RelativeUnit.WEEKS;
            count = count5;
        } else {
            Calendar timeCalendar = DateUtilsBridge.createIcuCalendar(icuTimeZone, icuLocale, time);
            if ((flags & 12) == 0) {
                if (timeCalendar.get(1) != DateUtilsBridge.createIcuCalendar(icuTimeZone, icuLocale, now).get(1)) {
                    flags2 = flags | 4;
                } else {
                    flags2 = flags | 8;
                }
            } else {
                flags2 = flags;
            }
            return DateTimeFormat.format(icuLocale, timeCalendar, flags2, displayContext);
        }
        synchronized (CACHED_FORMATTERS) {
            try {
                android.icu.text.RelativeDateTimeFormatter formatter = getFormatter(icuLocale, style, displayContext);
                if (relative) {
                    return formatter.format((double) count, direction2, unit);
                }
                return formatter.format(direction2, aunit);
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    public static String getRelativeDateTimeString(Locale locale, TimeZone tz, long time, long now, long minResolution, long transitionResolution, int flags) {
        long transitionResolution2;
        RelativeDateTimeFormatter.Style style;
        RelativeDateTimeFormatter.Style style2;
        Calendar timeCalendar;
        int i;
        String dateClause;
        String combineDateAndTime;
        int flags2;
        long minResolution2;
        if (locale == null) {
            throw new NullPointerException("locale == null");
        } else if (tz != null) {
            ULocale icuLocale = ULocale.forLocale(locale);
            android.icu.util.TimeZone icuTimeZone = DateUtilsBridge.icuTimeZone(tz);
            long duration = Math.abs(now - time);
            if (transitionResolution > WEEK_IN_MILLIS) {
                transitionResolution2 = 604800000;
            } else {
                transitionResolution2 = transitionResolution;
            }
            if ((flags & ArabicShaping.TASHKEEL_REPLACE_BY_TATWEEL) != 0) {
                style = RelativeDateTimeFormatter.Style.SHORT;
            } else {
                style = RelativeDateTimeFormatter.Style.LONG;
            }
            Calendar timeCalendar2 = DateUtilsBridge.createIcuCalendar(icuTimeZone, icuLocale, time);
            Calendar nowCalendar = DateUtilsBridge.createIcuCalendar(icuTimeZone, icuLocale, now);
            int days = Math.abs(DateUtilsBridge.dayDistance(timeCalendar2, nowCalendar));
            if (duration < transitionResolution2) {
                if (days <= 0 || minResolution >= 86400000) {
                    minResolution2 = minResolution;
                } else {
                    minResolution2 = 86400000;
                }
                i = 1;
                timeCalendar = timeCalendar2;
                style2 = style;
                dateClause = getRelativeTimeSpanString(icuLocale, icuTimeZone, time, now, minResolution2, flags, DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE);
            } else {
                i = 1;
                timeCalendar = timeCalendar2;
                style2 = style;
                if (timeCalendar.get(1) != nowCalendar.get(1)) {
                    flags2 = 131092;
                } else {
                    flags2 = 65560;
                }
                dateClause = DateTimeFormat.format(icuLocale, timeCalendar, flags2, DisplayContext.CAPITALIZATION_FOR_BEGINNING_OF_SENTENCE);
            }
            String timeClause = DateTimeFormat.format(icuLocale, timeCalendar, i, DisplayContext.CAPITALIZATION_NONE);
            DisplayContext capitalizationContext = DisplayContext.CAPITALIZATION_NONE;
            synchronized (CACHED_FORMATTERS) {
                combineDateAndTime = getFormatter(icuLocale, style2, capitalizationContext).combineDateAndTime(dateClause, timeClause);
            }
            return combineDateAndTime;
        } else {
            throw new NullPointerException("tz == null");
        }
    }

    private static android.icu.text.RelativeDateTimeFormatter getFormatter(ULocale locale, RelativeDateTimeFormatter.Style style, DisplayContext displayContext) {
        String key = ((Object) locale) + "\t" + ((Object) style) + "\t" + ((Object) displayContext);
        android.icu.text.RelativeDateTimeFormatter formatter = (android.icu.text.RelativeDateTimeFormatter) CACHED_FORMATTERS.get(key);
        if (formatter != null) {
            return formatter;
        }
        android.icu.text.RelativeDateTimeFormatter formatter2 = android.icu.text.RelativeDateTimeFormatter.getInstance(locale, null, style, displayContext);
        CACHED_FORMATTERS.put(key, formatter2);
        return formatter2;
    }

    private static int dayDistance(android.icu.util.TimeZone icuTimeZone, long startTime, long endTime) {
        return julianDay(icuTimeZone, endTime) - julianDay(icuTimeZone, startTime);
    }

    private static int julianDay(android.icu.util.TimeZone icuTimeZone, long time) {
        return ((int) ((((long) icuTimeZone.getOffset(time)) + time) / 86400000)) + EPOCH_JULIAN_DAY;
    }
}
