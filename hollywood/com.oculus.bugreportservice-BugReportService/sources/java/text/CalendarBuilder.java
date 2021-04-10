package java.text;

/* access modifiers changed from: package-private */
public class CalendarBuilder {
    private final int[] field = new int[36];
    private int maxFieldIndex = -1;
    private int nextStamp = 2;

    static boolean isValidDayOfWeek(int i) {
        return i > 0 && i <= 7;
    }

    static int toISODayOfWeek(int i) {
        if (i == 1) {
            return 7;
        }
        return i - 1;
    }

    CalendarBuilder() {
    }

    /* access modifiers changed from: package-private */
    public CalendarBuilder set(int i, int i2) {
        if (i == 1000) {
            i = 7;
            i2 = toCalendarDayOfWeek(i2);
        }
        int[] iArr = this.field;
        int i3 = this.nextStamp;
        this.nextStamp = i3 + 1;
        iArr[i] = i3;
        iArr[i + 18] = i2;
        if (i > this.maxFieldIndex && i < 17) {
            this.maxFieldIndex = i;
        }
        return this;
    }

    /* access modifiers changed from: package-private */
    public CalendarBuilder addYear(int i) {
        int[] iArr = this.field;
        iArr[19] = iArr[19] + i;
        iArr[35] = iArr[35] + i;
        return this;
    }

    /* access modifiers changed from: package-private */
    public boolean isSet(int i) {
        if (i == 1000) {
            i = 7;
        }
        return this.field[i] > 0;
    }

    /* access modifiers changed from: package-private */
    public CalendarBuilder clear(int i) {
        if (i == 1000) {
            i = 7;
        }
        int[] iArr = this.field;
        iArr[i] = 0;
        iArr[i + 18] = 0;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Calendar establish(java.util.Calendar r9) {
        /*
        // Method dump skipped, instructions count: 156
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
        int length = sb.length() - 1;
        if (sb.charAt(length) == ',') {
            sb.setLength(length);
        }
        sb.append(']');
        return sb.toString();
    }

    static int toCalendarDayOfWeek(int i) {
        if (!isValidDayOfWeek(i)) {
            return i;
        }
        if (i == 7) {
            return 1;
        }
        return 1 + i;
    }
}
