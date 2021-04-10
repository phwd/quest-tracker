package java.text;

/* access modifiers changed from: package-private */
public class CalendarBuilder {
    private static final int COMPUTED = 1;
    public static final int ISO_DAY_OF_WEEK = 1000;
    private static final int MAX_FIELD = 18;
    private static final int MINIMUM_USER_STAMP = 2;
    private static final int UNSET = 0;
    public static final int WEEK_YEAR = 17;
    private final int[] field = new int[36];
    private int maxFieldIndex = -1;
    private int nextStamp = 2;

    CalendarBuilder() {
    }

    /* access modifiers changed from: package-private */
    public CalendarBuilder set(int index, int value) {
        if (index == 1000) {
            index = 7;
            value = toCalendarDayOfWeek(value);
        }
        int[] iArr = this.field;
        int i = this.nextStamp;
        this.nextStamp = i + 1;
        iArr[index] = i;
        iArr[index + 18] = value;
        if (index > this.maxFieldIndex && index < 17) {
            this.maxFieldIndex = index;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public CalendarBuilder addYear(int value) {
        int[] iArr = this.field;
        iArr[19] = iArr[19] + value;
        iArr[35] = iArr[35] + value;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean isSet(int index) {
        if (index == 1000) {
            index = 7;
        }
        return this.field[index] > 0;
    }

    /* access modifiers changed from: package-private */
    public CalendarBuilder clear(int index) {
        if (index == 1000) {
            index = 7;
        }
        int[] iArr = this.field;
        iArr[index] = 0;
        iArr[index + 18] = 0;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0024  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Calendar establish(java.util.Calendar r8) {
        /*
        // Method dump skipped, instructions count: 157
        */
        throw new UnsupportedOperationException("Method not decompiled: java.text.CalendarBuilder.establish(java.util.Calendar):java.util.Calendar");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CalendarBuilder:[");
        for (int i = 0; i < this.field.length; i++) {
            if (isSet(i)) {
                sb.append(i);
                sb.append('=');
                sb.append(this.field[i + 18]);
                sb.append(',');
            }
        }
        int lastIndex = sb.length() - 1;
        if (sb.charAt(lastIndex) == ',') {
            sb.setLength(lastIndex);
        }
        sb.append(']');
        return sb.toString();
    }

    static int toISODayOfWeek(int calendarDayOfWeek) {
        if (calendarDayOfWeek == 1) {
            return 7;
        }
        return calendarDayOfWeek - 1;
    }

    static int toCalendarDayOfWeek(int isoDayOfWeek) {
        if (!isValidDayOfWeek(isoDayOfWeek)) {
            return isoDayOfWeek;
        }
        if (isoDayOfWeek == 7) {
            return 1;
        }
        return isoDayOfWeek + 1;
    }

    static boolean isValidDayOfWeek(int dayOfWeek) {
        return dayOfWeek > 0 && dayOfWeek <= 7;
    }
}
