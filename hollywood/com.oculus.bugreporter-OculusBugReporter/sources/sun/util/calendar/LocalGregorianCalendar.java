package sun.util.calendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import sun.util.calendar.BaseCalendar;

public class LocalGregorianCalendar extends BaseCalendar {
    private Era[] eras;
    private String name;

    public static class Date extends BaseCalendar.Date {
        private int gregorianYear = Integer.MIN_VALUE;

        protected Date() {
        }

        protected Date(TimeZone zone) {
            super(zone);
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
        public Date addYear(int localYear) {
            super.addYear(localYear);
            this.gregorianYear += localYear;
            return this;
        }

        @Override // sun.util.calendar.CalendarDate
        public Date setYear(int localYear) {
            if (getYear() != localYear) {
                super.setYear(localYear);
                this.gregorianYear = Integer.MIN_VALUE;
            }
            return this;
        }

        @Override // sun.util.calendar.BaseCalendar.Date
        public int getNormalizedYear() {
            return this.gregorianYear;
        }

        @Override // sun.util.calendar.BaseCalendar.Date
        public void setNormalizedYear(int normalizedYear) {
            this.gregorianYear = normalizedYear;
        }

        /* access modifiers changed from: package-private */
        public void setLocalEra(Era era) {
            super.setEra(era);
        }

        /* access modifiers changed from: package-private */
        public void setLocalYear(int year) {
            super.setYear(year);
        }

        @Override // sun.util.calendar.CalendarDate
        public String toString() {
            String abbr;
            String time = super.toString();
            String time2 = time.substring(time.indexOf(84));
            StringBuffer sb = new StringBuffer();
            Era era = getEra();
            if (!(era == null || (abbr = era.getAbbreviation()) == null)) {
                sb.append(abbr);
            }
            sb.append(getYear());
            sb.append('.');
            CalendarUtils.sprintf0d(sb, getMonth(), 2).append('.');
            CalendarUtils.sprintf0d(sb, getDayOfMonth(), 2);
            sb.append(time2);
            return sb.toString();
        }
    }

    static LocalGregorianCalendar getLocalGregorianCalendar(String name2) {
        try {
            Properties calendarProps = CalendarSystem.getCalendarProperties();
            String props = calendarProps.getProperty("calendar." + name2 + ".eras");
            LocalGregorianCalendar localGregorianCalendar = null;
            if (props == null) {
                return null;
            }
            List<Era> eras2 = new ArrayList<>();
            StringTokenizer eraTokens = new StringTokenizer(props, ";");
            while (eraTokens.hasMoreTokens()) {
                StringTokenizer itemTokens = new StringTokenizer(eraTokens.nextToken().trim(), ",");
                String eraName = null;
                boolean localTime = true;
                long since = 0;
                String abbr = null;
                while (itemTokens.hasMoreTokens()) {
                    String item = itemTokens.nextToken();
                    int index = item.indexOf(61);
                    if (index == -1) {
                        return localGregorianCalendar;
                    }
                    String key = item.substring(0, index);
                    String value = item.substring(index + 1);
                    if ("name".equals(key)) {
                        eraName = value;
                    } else if ("since".equals(key)) {
                        if (value.endsWith("u")) {
                            since = Long.parseLong(value.substring(0, value.length() - 1));
                            localTime = false;
                        } else {
                            since = Long.parseLong(value);
                        }
                    } else if ("abbr".equals(key)) {
                        abbr = value;
                    } else {
                        throw new RuntimeException("Unknown key word: " + key);
                    }
                    calendarProps = calendarProps;
                    localGregorianCalendar = null;
                }
                eras2.add(new Era(eraName, abbr, since, localTime));
                calendarProps = calendarProps;
                localGregorianCalendar = null;
            }
            if (!eras2.isEmpty()) {
                Era[] eraArray = new Era[eras2.size()];
                eras2.toArray(eraArray);
                return new LocalGregorianCalendar(name2, eraArray);
            }
            throw new RuntimeException("No eras for " + name2);
        } catch (IOException | IllegalArgumentException e) {
            throw new InternalError(e);
        }
    }

    private LocalGregorianCalendar(String name2, Era[] eras2) {
        this.name = name2;
        this.eras = eras2;
        setEras(eras2);
    }

    @Override // sun.util.calendar.CalendarSystem
    public String getName() {
        return this.name;
    }

    @Override // sun.util.calendar.AbstractCalendar, sun.util.calendar.CalendarSystem
    public Date getCalendarDate() {
        return getCalendarDate(System.currentTimeMillis(), (CalendarDate) newCalendarDate());
    }

    @Override // sun.util.calendar.AbstractCalendar, sun.util.calendar.CalendarSystem
    public Date getCalendarDate(long millis) {
        return getCalendarDate(millis, (CalendarDate) newCalendarDate());
    }

    @Override // sun.util.calendar.AbstractCalendar, sun.util.calendar.CalendarSystem
    public Date getCalendarDate(long millis, TimeZone zone) {
        return getCalendarDate(millis, (CalendarDate) newCalendarDate(zone));
    }

    @Override // sun.util.calendar.AbstractCalendar, sun.util.calendar.CalendarSystem
    public Date getCalendarDate(long millis, CalendarDate date) {
        Date ldate = (Date) super.getCalendarDate(millis, date);
        return adjustYear(ldate, millis, ldate.getZoneOffset());
    }

    private Date adjustYear(Date ldate, long millis, int zoneOffset) {
        int i = this.eras.length - 1;
        while (true) {
            if (i < 0) {
                break;
            }
            Era era = this.eras[i];
            long since = era.getSince(null);
            if (era.isLocalTime()) {
                since -= (long) zoneOffset;
            }
            if (millis >= since) {
                ldate.setLocalEra(era);
                ldate.setLocalYear((ldate.getNormalizedYear() - era.getSinceDate().getYear()) + 1);
                break;
            }
            i--;
        }
        if (i < 0) {
            ldate.setLocalEra(null);
            ldate.setLocalYear(ldate.getNormalizedYear());
        }
        ldate.setNormalized(true);
        return ldate;
    }

    @Override // sun.util.calendar.CalendarSystem
    public Date newCalendarDate() {
        return new Date();
    }

    @Override // sun.util.calendar.CalendarSystem
    public Date newCalendarDate(TimeZone zone) {
        return new Date(zone);
    }

    @Override // sun.util.calendar.BaseCalendar, sun.util.calendar.CalendarSystem
    public boolean validate(CalendarDate date) {
        Date ldate = (Date) date;
        Era era = ldate.getEra();
        if (era != null) {
            if (!validateEra(era)) {
                return false;
            }
            ldate.setNormalizedYear((era.getSinceDate().getYear() + ldate.getYear()) - 1);
            Date tmp = newCalendarDate(date.getZone());
            tmp.setEra(era).setDate(date.getYear(), date.getMonth(), date.getDayOfMonth());
            normalize(tmp);
            if (tmp.getEra() != era) {
                return false;
            }
        } else if (date.getYear() >= this.eras[0].getSinceDate().getYear()) {
            return false;
        } else {
            ldate.setNormalizedYear(ldate.getYear());
        }
        return super.validate(ldate);
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
    public boolean normalize(CalendarDate date) {
        if (date.isNormalized()) {
            return true;
        }
        normalizeYear(date);
        Date ldate = (Date) date;
        super.normalize(ldate);
        boolean hasMillis = false;
        long millis = 0;
        int year = ldate.getNormalizedYear();
        Era era = null;
        int i = this.eras.length - 1;
        while (true) {
            if (i < 0) {
                break;
            }
            era = this.eras[i];
            if (era.isLocalTime()) {
                CalendarDate sinceDate = era.getSinceDate();
                int sinceYear = sinceDate.getYear();
                if (year <= sinceYear) {
                    if (year == sinceYear) {
                        int month = ldate.getMonth();
                        int sinceMonth = sinceDate.getMonth();
                        if (month <= sinceMonth) {
                            if (month == sinceMonth) {
                                int day = ldate.getDayOfMonth();
                                int sinceDay = sinceDate.getDayOfMonth();
                                if (day > sinceDay) {
                                    break;
                                } else if (day == sinceDay) {
                                    if (ldate.getTimeOfDay() < sinceDate.getTimeOfDay()) {
                                        i--;
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
                if (!hasMillis) {
                    millis = super.getTime(date);
                    hasMillis = true;
                }
                if (millis >= era.getSince(date.getZone())) {
                    break;
                }
            }
            i--;
        }
        if (i >= 0) {
            ldate.setLocalEra(era);
            ldate.setLocalYear((ldate.getNormalizedYear() - era.getSinceDate().getYear()) + 1);
        } else {
            ldate.setEra((Era) null);
            ldate.setLocalYear(year);
            ldate.setNormalizedYear(year);
        }
        ldate.setNormalized(true);
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // sun.util.calendar.BaseCalendar
    public void normalizeMonth(CalendarDate date) {
        normalizeYear(date);
        super.normalizeMonth(date);
    }

    /* access modifiers changed from: package-private */
    public void normalizeYear(CalendarDate date) {
        Date ldate = (Date) date;
        Era era = ldate.getEra();
        if (era == null || !validateEra(era)) {
            ldate.setNormalizedYear(ldate.getYear());
        } else {
            ldate.setNormalizedYear((era.getSinceDate().getYear() + ldate.getYear()) - 1);
        }
    }

    @Override // sun.util.calendar.BaseCalendar
    public boolean isLeapYear(int gregorianYear) {
        return CalendarUtils.isGregorianLeapYear(gregorianYear);
    }

    public boolean isLeapYear(Era era, int year) {
        if (era == null) {
            return isLeapYear(year);
        }
        return isLeapYear((era.getSinceDate().getYear() + year) - 1);
    }

    @Override // sun.util.calendar.AbstractCalendar, sun.util.calendar.BaseCalendar
    public void getCalendarDateFromFixedDate(CalendarDate date, long fixedDate) {
        Date ldate = (Date) date;
        super.getCalendarDateFromFixedDate(ldate, fixedDate);
        adjustYear(ldate, (fixedDate - 719163) * 86400000, 0);
    }
}
