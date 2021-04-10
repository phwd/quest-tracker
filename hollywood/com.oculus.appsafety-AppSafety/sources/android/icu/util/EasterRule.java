package android.icu.util;

import java.util.Date;

/* access modifiers changed from: package-private */
/* compiled from: EasterHoliday */
public class EasterRule implements DateRule {
    private GregorianCalendar calendar = new GregorianCalendar();
    private int daysAfterEaster;

    public EasterRule(int daysAfterEaster2, boolean isOrthodox) {
        this.daysAfterEaster = daysAfterEaster2;
        if (isOrthodox) {
            this.calendar.setGregorianChange(new Date((long) Long.MAX_VALUE));
        }
    }

    @Override // android.icu.util.DateRule
    public Date firstAfter(Date start) {
        return doFirstBetween(start, null);
    }

    @Override // android.icu.util.DateRule
    public Date firstBetween(Date start, Date end) {
        return doFirstBetween(start, end);
    }

    @Override // android.icu.util.DateRule
    public boolean isOn(Date date) {
        boolean z;
        synchronized (this.calendar) {
            this.calendar.setTime(date);
            int dayOfYear = this.calendar.get(6);
            this.calendar.setTime(computeInYear(this.calendar.getTime(), this.calendar));
            z = this.calendar.get(6) == dayOfYear;
        }
        return z;
    }

    @Override // android.icu.util.DateRule
    public boolean isBetween(Date start, Date end) {
        return firstBetween(start, end) != null;
    }

    private Date doFirstBetween(Date start, Date end) {
        synchronized (this.calendar) {
            Date result = computeInYear(start, this.calendar);
            if (result.before(start)) {
                this.calendar.setTime(start);
                this.calendar.get(1);
                this.calendar.add(1, 1);
                result = computeInYear(this.calendar.getTime(), this.calendar);
            }
            if (end == null || result.before(end)) {
                return result;
            }
            return null;
        }
    }

    private Date computeInYear(Date date, GregorianCalendar cal) {
        int j;
        int i;
        Date time;
        if (cal == null) {
            cal = this.calendar;
        }
        synchronized (cal) {
            cal.setTime(date);
            int year = cal.get(1);
            int g = year % 19;
            if (cal.getTime().after(cal.getGregorianChange())) {
                int c = year / 100;
                int h = ((((c - (c / 4)) - (((c * 8) + 13) / 25)) + (g * 19)) + 15) % 30;
                i = h - ((h / 28) * (1 - (((h / 28) * (29 / (h + 1))) * ((21 - g) / 11))));
                j = ((((((year / 4) + year) + i) + 2) - c) + (c / 4)) % 7;
            } else {
                i = ((g * 19) + 15) % 30;
                j = (((year / 4) + year) + i) % 7;
            }
            int l = i - j;
            int m = ((l + 40) / 44) + 3;
            cal.clear();
            cal.set(0, 1);
            cal.set(1, year);
            cal.set(2, m - 1);
            cal.set(5, (l + 28) - ((m / 4) * 31));
            cal.getTime();
            cal.add(5, this.daysAfterEaster);
            time = cal.getTime();
        }
        return time;
    }
}
