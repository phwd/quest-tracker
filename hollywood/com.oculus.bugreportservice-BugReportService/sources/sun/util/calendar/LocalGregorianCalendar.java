package sun.util.calendar;

import java.util.TimeZone;
import sun.util.calendar.BaseCalendar;

public class LocalGregorianCalendar extends BaseCalendar {
    private Era[] eras;
    private String name;

    public static class Date extends BaseCalendar.Date {
        private int gregorianYear = Integer.MIN_VALUE;

        protected Date() {
        }

        protected Date(TimeZone timeZone) {
            super(timeZone);
        }

        @Override // sun.util.calendar.CalendarDate
        public Date setEra(Era era) {
            if (getEra() != era) {
                super.setEra(era);
                this.gregorianYear = Integer.MIN_VALUE;
            }
            return this;
        }

        @Override // sun.util.calendar.CalendarDate
        public Date addYear(int i) {
            super.addYear(i);
            this.gregorianYear += i;
            return this;
        }

        @Override // sun.util.calendar.CalendarDate
        public Date setYear(int i) {
            if (getYear() != i) {
                super.setYear(i);
                this.gregorianYear = Integer.MIN_VALUE;
            }
            return this;
        }

        @Override // sun.util.calendar.BaseCalendar.Date
        public int getNormalizedYear() {
            return this.gregorianYear;
        }

        @Override // sun.util.calendar.BaseCalendar.Date
        public void setNormalizedYear(int i) {
            this.gregorianYear = i;
        }

        /* access modifiers changed from: package-private */
        public void setLocalEra(Era era) {
            super.setEra(era);
        }

        /* access modifiers changed from: package-private */
        public void setLocalYear(int i) {
            super.setYear(i);
        }

        @Override // sun.util.calendar.CalendarDate
        public String toString() {
            String abbreviation;
            String calendarDate = super.toString();
            String substring = calendarDate.substring(calendarDate.indexOf(84));
            StringBuffer stringBuffer = new StringBuffer();
            Era era = getEra();
            if (!(era == null || (abbreviation = era.getAbbreviation()) == null)) {
                stringBuffer.append(abbreviation);
            }
            stringBuffer.append(getYear());
            stringBuffer.append('.');
            CalendarUtils.sprintf0d(stringBuffer, getMonth(), 2);
            stringBuffer.append('.');
            CalendarUtils.sprintf0d(stringBuffer, getDayOfMonth(), 2);
            stringBuffer.append(substring);
            return stringBuffer.toString();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b9, code lost:
        r2.add(new sun.util.calendar.Era(r8, r9, r10, r12));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static sun.util.calendar.LocalGregorianCalendar getLocalGregorianCalendar(java.lang.String r14) {
        /*
        // Method dump skipped, instructions count: 247
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.util.calendar.LocalGregorianCalendar.getLocalGregorianCalendar(java.lang.String):sun.util.calendar.LocalGregorianCalendar");
    }

    private LocalGregorianCalendar(String str, Era[] eraArr) {
        this.name = str;
        this.eras = eraArr;
        setEras(eraArr);
    }

    @Override // sun.util.calendar.AbstractCalendar
    public Date getCalendarDate() {
        return getCalendarDate(System.currentTimeMillis(), (CalendarDate) newCalendarDate());
    }

    @Override // sun.util.calendar.AbstractCalendar
    public Date getCalendarDate(long j, TimeZone timeZone) {
        return getCalendarDate(j, (CalendarDate) newCalendarDate(timeZone));
    }

    @Override // sun.util.calendar.AbstractCalendar
    public Date getCalendarDate(long j, CalendarDate calendarDate) {
        super.getCalendarDate(j, calendarDate);
        Date date = (Date) calendarDate;
        adjustYear(date, j, date.getZoneOffset());
        return date;
    }

    private Date adjustYear(Date date, long j, int i) {
        int length = this.eras.length - 1;
        while (true) {
            if (length < 0) {
                break;
            }
            Era era = this.eras[length];
            long since = era.getSince(null);
            if (era.isLocalTime()) {
                since -= (long) i;
            }
            if (j >= since) {
                date.setLocalEra(era);
                date.setLocalYear((date.getNormalizedYear() - era.getSinceDate().getYear()) + 1);
                break;
            }
            length--;
        }
        if (length < 0) {
            date.setLocalEra(null);
            date.setLocalYear(date.getNormalizedYear());
        }
        date.setNormalized(true);
        return date;
    }

    @Override // sun.util.calendar.CalendarSystem
    public Date newCalendarDate() {
        return new Date();
    }

    @Override // sun.util.calendar.CalendarSystem
    public Date newCalendarDate(TimeZone timeZone) {
        return new Date(timeZone);
    }

    @Override // sun.util.calendar.BaseCalendar
    public boolean validate(CalendarDate calendarDate) {
        Date date = (Date) calendarDate;
        Era era = date.getEra();
        if (era != null) {
            if (!validateEra(era)) {
                return false;
            }
            date.setNormalizedYear((era.getSinceDate().getYear() + date.getYear()) - 1);
            Date newCalendarDate = newCalendarDate(calendarDate.getZone());
            newCalendarDate.setEra(era);
            newCalendarDate.setDate(calendarDate.getYear(), calendarDate.getMonth(), calendarDate.getDayOfMonth());
            normalize(newCalendarDate);
            if (newCalendarDate.getEra() != era) {
                return false;
            }
        } else if (calendarDate.getYear() >= this.eras[0].getSinceDate().getYear()) {
            return false;
        } else {
            date.setNormalizedYear(date.getYear());
        }
        return super.validate(date);
    }

    private boolean validateEra(Era era) {
        int i = 0;
        while (true) {
            Era[] eraArr = this.eras;
            if (i >= eraArr.length) {
                return false;
            }
            if (era == eraArr[i]) {
                return true;
            }
            i++;
        }
    }

    @Override // sun.util.calendar.BaseCalendar, sun.util.calendar.CalendarSystem
    public boolean normalize(CalendarDate calendarDate) {
        if (calendarDate.isNormalized()) {
            return true;
        }
        normalizeYear(calendarDate);
        Date date = (Date) calendarDate;
        super.normalize(date);
        boolean z = false;
        long j = 0;
        int normalizedYear = date.getNormalizedYear();
        int length = this.eras.length - 1;
        Era era = null;
        while (true) {
            if (length < 0) {
                break;
            }
            era = this.eras[length];
            if (era.isLocalTime()) {
                CalendarDate sinceDate = era.getSinceDate();
                int year = sinceDate.getYear();
                if (normalizedYear <= year) {
                    if (normalizedYear == year) {
                        int month = date.getMonth();
                        int month2 = sinceDate.getMonth();
                        if (month <= month2) {
                            if (month == month2) {
                                int dayOfMonth = date.getDayOfMonth();
                                int dayOfMonth2 = sinceDate.getDayOfMonth();
                                if (dayOfMonth > dayOfMonth2) {
                                    break;
                                } else if (dayOfMonth == dayOfMonth2) {
                                    if (date.getTimeOfDay() < sinceDate.getTimeOfDay()) {
                                        length--;
                                    }
                                }
                            } else {
                                continue;
                            }
                        } else {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    break;
                }
            } else {
                if (!z) {
                    j = super.getTime(calendarDate);
                    z = true;
                }
                if (j >= era.getSince(calendarDate.getZone())) {
                    break;
                }
            }
            length--;
        }
        if (length >= 0) {
            date.setLocalEra(era);
            date.setLocalYear((date.getNormalizedYear() - era.getSinceDate().getYear()) + 1);
        } else {
            date.setEra((Era) null);
            date.setLocalYear(normalizedYear);
            date.setNormalizedYear(normalizedYear);
        }
        date.setNormalized(true);
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // sun.util.calendar.BaseCalendar
    public void normalizeMonth(CalendarDate calendarDate) {
        normalizeYear(calendarDate);
        super.normalizeMonth(calendarDate);
    }

    /* access modifiers changed from: package-private */
    public void normalizeYear(CalendarDate calendarDate) {
        Date date = (Date) calendarDate;
        Era era = date.getEra();
        if (era == null || !validateEra(era)) {
            date.setNormalizedYear(date.getYear());
        } else {
            date.setNormalizedYear((era.getSinceDate().getYear() + date.getYear()) - 1);
        }
    }

    @Override // sun.util.calendar.BaseCalendar
    public boolean isLeapYear(int i) {
        return CalendarUtils.isGregorianLeapYear(i);
    }

    @Override // sun.util.calendar.AbstractCalendar, sun.util.calendar.BaseCalendar
    public void getCalendarDateFromFixedDate(CalendarDate calendarDate, long j) {
        Date date = (Date) calendarDate;
        super.getCalendarDateFromFixedDate(date, j);
        adjustYear(date, (j - 719163) * 86400000, 0);
    }
}
