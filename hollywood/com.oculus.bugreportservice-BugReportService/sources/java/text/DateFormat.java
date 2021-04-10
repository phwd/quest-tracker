package java.text;

import java.io.InvalidObjectException;
import java.text.Format;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.TimeZone;

public abstract class DateFormat extends Format {
    public static Boolean is24Hour = null;
    private static final long serialVersionUID = 7218322306649953788L;
    protected Calendar calendar;
    protected NumberFormat numberFormat;

    public abstract StringBuffer format(Date date, StringBuffer stringBuffer, FieldPosition fieldPosition);

    public abstract Date parse(String str, ParsePosition parsePosition);

    @Override // java.text.Format
    public final StringBuffer format(Object obj, StringBuffer stringBuffer, FieldPosition fieldPosition) {
        if (obj instanceof Date) {
            return format((Date) obj, stringBuffer, fieldPosition);
        }
        if (obj instanceof Number) {
            return format(new Date(((Number) obj).longValue()), stringBuffer, fieldPosition);
        }
        throw new IllegalArgumentException("Cannot format given Object as a Date");
    }

    public final String format(Date date) {
        return format(date, new StringBuffer(), DontCareFieldPosition.INSTANCE).toString();
    }

    public static final DateFormat getTimeInstance(int i, Locale locale) {
        return get(i, 0, 1, locale);
    }

    public static final DateFormat getDateInstance(int i, Locale locale) {
        return get(0, i, 2, locale);
    }

    public static final DateFormat getDateTimeInstance(int i, int i2, Locale locale) {
        return get(i2, i, 3, locale);
    }

    public void setTimeZone(TimeZone timeZone) {
        this.calendar.setTimeZone(timeZone);
    }

    public TimeZone getTimeZone() {
        return this.calendar.getTimeZone();
    }

    public void setLenient(boolean z) {
        this.calendar.setLenient(z);
    }

    public boolean isLenient() {
        return this.calendar.isLenient();
    }

    public int hashCode() {
        return this.numberFormat.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DateFormat dateFormat = (DateFormat) obj;
        return this.calendar.getFirstDayOfWeek() == dateFormat.calendar.getFirstDayOfWeek() && this.calendar.getMinimalDaysInFirstWeek() == dateFormat.calendar.getMinimalDaysInFirstWeek() && this.calendar.isLenient() == dateFormat.calendar.isLenient() && this.calendar.getTimeZone().equals(dateFormat.calendar.getTimeZone()) && this.numberFormat.equals(dateFormat.numberFormat);
    }

    @Override // java.text.Format
    public Object clone() {
        DateFormat dateFormat = (DateFormat) super.clone();
        dateFormat.calendar = (Calendar) this.calendar.clone();
        dateFormat.numberFormat = (NumberFormat) this.numberFormat.clone();
        return dateFormat;
    }

    private static DateFormat get(int i, int i2, int i3, Locale locale) {
        if ((i3 & 1) == 0) {
            i = -1;
        } else if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Illegal time style " + i);
        }
        if ((i3 & 2) == 0) {
            i2 = -1;
        } else if (i2 < 0 || i2 > 3) {
            throw new IllegalArgumentException("Illegal date style " + i2);
        }
        try {
            return new SimpleDateFormat(i, i2, locale);
        } catch (MissingResourceException unused) {
            return new SimpleDateFormat("M/d/yy h:mm a");
        }
    }

    protected DateFormat() {
    }

    public static class Field extends Format.Field {
        public static final Field AM_PM = new Field("am pm", 9);
        public static final Field DAY_OF_MONTH = new Field("day of month", 5);
        public static final Field DAY_OF_WEEK = new Field("day of week", 7);
        public static final Field DAY_OF_WEEK_IN_MONTH = new Field("day of week in month", 8);
        public static final Field DAY_OF_YEAR = new Field("day of year", 6);
        public static final Field ERA = new Field("era", 0);
        public static final Field HOUR0 = new Field("hour", 10);
        public static final Field HOUR1 = new Field("hour 1", -1);
        public static final Field HOUR_OF_DAY0 = new Field("hour of day", 11);
        public static final Field HOUR_OF_DAY1 = new Field("hour of day 1", -1);
        public static final Field MILLISECOND = new Field("millisecond", 14);
        public static final Field MINUTE = new Field("minute", 12);
        public static final Field MONTH = new Field("month", 2);
        public static final Field SECOND = new Field("second", 13);
        public static final Field TIME_ZONE = new Field("time zone", -1);
        public static final Field WEEK_OF_MONTH = new Field("week of month", 4);
        public static final Field WEEK_OF_YEAR = new Field("week of year", 3);
        public static final Field YEAR = new Field("year", 1);
        private static final Field[] calendarToFieldMapping = new Field[17];
        private static final Map instanceMap = new HashMap(18);
        private static final long serialVersionUID = 7441350119349544720L;
        private int calendarField;

        protected Field(String str, int i) {
            super(str);
            this.calendarField = i;
            if (Field.class == Field.class) {
                instanceMap.put(str, this);
                if (i >= 0) {
                    calendarToFieldMapping[i] = this;
                }
            }
        }

        /* access modifiers changed from: protected */
        @Override // java.text.AttributedCharacterIterator.Attribute
        public Object readResolve() {
            if (Field.class == Field.class) {
                Object obj = instanceMap.get(getName());
                if (obj != null) {
                    return obj;
                }
                throw new InvalidObjectException("unknown attribute name");
            }
            throw new InvalidObjectException("subclass didn't correctly implement readResolve");
        }
    }
}
