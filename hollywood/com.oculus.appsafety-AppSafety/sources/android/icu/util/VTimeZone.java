package android.icu.util;

import android.icu.impl.Grego;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

public class VTimeZone extends BasicTimeZone {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String COLON = ":";
    private static final String COMMA = ",";
    private static final int DEF_DSTSAVINGS = 3600000;
    private static final long DEF_TZSTARTTIME = 0;
    private static final String EQUALS_SIGN = "=";
    private static final int ERR = 3;
    private static final String ICAL_BEGIN = "BEGIN";
    private static final String ICAL_BEGIN_VTIMEZONE = "BEGIN:VTIMEZONE";
    private static final String ICAL_BYDAY = "BYDAY";
    private static final String ICAL_BYMONTH = "BYMONTH";
    private static final String ICAL_BYMONTHDAY = "BYMONTHDAY";
    private static final String ICAL_DAYLIGHT = "DAYLIGHT";
    private static final String[] ICAL_DOW_NAMES = {"SU", "MO", "TU", "WE", "TH", "FR", "SA"};
    private static final String ICAL_DTSTART = "DTSTART";
    private static final String ICAL_END = "END";
    private static final String ICAL_END_VTIMEZONE = "END:VTIMEZONE";
    private static final String ICAL_FREQ = "FREQ";
    private static final String ICAL_LASTMOD = "LAST-MODIFIED";
    private static final String ICAL_RDATE = "RDATE";
    private static final String ICAL_RRULE = "RRULE";
    private static final String ICAL_STANDARD = "STANDARD";
    private static final String ICAL_TZID = "TZID";
    private static final String ICAL_TZNAME = "TZNAME";
    private static final String ICAL_TZOFFSETFROM = "TZOFFSETFROM";
    private static final String ICAL_TZOFFSETTO = "TZOFFSETTO";
    private static final String ICAL_TZURL = "TZURL";
    private static final String ICAL_UNTIL = "UNTIL";
    private static final String ICAL_VTIMEZONE = "VTIMEZONE";
    private static final String ICAL_YEARLY = "YEARLY";
    private static final String ICU_TZINFO_PROP = "X-TZINFO";
    private static String ICU_TZVERSION = null;
    private static final int INI = 0;
    private static final long MAX_TIME = Long.MAX_VALUE;
    private static final long MIN_TIME = Long.MIN_VALUE;
    private static final int[] MONTHLENGTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final String NEWLINE = "\r\n";
    private static final String SEMICOLON = ";";
    private static final int TZI = 2;
    private static final int VTZ = 1;
    private static final long serialVersionUID = -6851467294127795902L;
    private volatile transient boolean isFrozen = false;
    private Date lastmod = null;
    private String olsonzid = null;
    private BasicTimeZone tz;
    private String tzurl = null;
    private List<String> vtzlines;

    static {
        try {
            ICU_TZVERSION = TimeZone.getTZDataVersion();
        } catch (MissingResourceException e) {
            ICU_TZVERSION = null;
        }
    }

    public static VTimeZone create(String tzid) {
        BasicTimeZone basicTimeZone = TimeZone.getFrozenICUTimeZone(tzid, true);
        if (basicTimeZone == null) {
            return null;
        }
        VTimeZone vtz = new VTimeZone(tzid);
        vtz.tz = (BasicTimeZone) basicTimeZone.cloneAsThawed();
        vtz.olsonzid = vtz.tz.getID();
        return vtz;
    }

    public static VTimeZone create(Reader reader) {
        VTimeZone vtz = new VTimeZone();
        if (vtz.load(reader)) {
            return vtz;
        }
        return null;
    }

    @Override // android.icu.util.TimeZone
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int milliseconds) {
        return this.tz.getOffset(era, year, month, day, dayOfWeek, milliseconds);
    }

    @Override // android.icu.util.TimeZone
    public void getOffset(long date, boolean local, int[] offsets) {
        this.tz.getOffset(date, local, offsets);
    }

    @Override // android.icu.util.BasicTimeZone
    @Deprecated
    public void getOffsetFromLocal(long date, int nonExistingTimeOpt, int duplicatedTimeOpt, int[] offsets) {
        this.tz.getOffsetFromLocal(date, nonExistingTimeOpt, duplicatedTimeOpt, offsets);
    }

    @Override // android.icu.util.TimeZone
    public int getRawOffset() {
        return this.tz.getRawOffset();
    }

    @Override // android.icu.util.TimeZone
    public boolean inDaylightTime(Date date) {
        return this.tz.inDaylightTime(date);
    }

    @Override // android.icu.util.TimeZone
    public void setRawOffset(int offsetMillis) {
        if (!isFrozen()) {
            this.tz.setRawOffset(offsetMillis);
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen VTimeZone instance.");
    }

    @Override // android.icu.util.TimeZone
    public boolean useDaylightTime() {
        return this.tz.useDaylightTime();
    }

    @Override // android.icu.util.TimeZone
    public boolean observesDaylightTime() {
        return this.tz.observesDaylightTime();
    }

    @Override // android.icu.util.TimeZone
    public boolean hasSameRules(TimeZone other) {
        if (this == other) {
            return true;
        }
        if (other instanceof VTimeZone) {
            return this.tz.hasSameRules(((VTimeZone) other).tz);
        }
        return this.tz.hasSameRules(other);
    }

    public String getTZURL() {
        return this.tzurl;
    }

    public void setTZURL(String url) {
        if (!isFrozen()) {
            this.tzurl = url;
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen VTimeZone instance.");
    }

    public Date getLastModified() {
        return this.lastmod;
    }

    public void setLastModified(Date date) {
        if (!isFrozen()) {
            this.lastmod = date;
            return;
        }
        throw new UnsupportedOperationException("Attempt to modify a frozen VTimeZone instance.");
    }

    public void write(Writer writer) throws IOException {
        BufferedWriter bw = new BufferedWriter(writer);
        List<String> list = this.vtzlines;
        if (list != null) {
            for (String line : list) {
                if (line.startsWith("TZURL:")) {
                    if (this.tzurl != null) {
                        bw.write(ICAL_TZURL);
                        bw.write(COLON);
                        bw.write(this.tzurl);
                        bw.write(NEWLINE);
                    }
                } else if (!line.startsWith("LAST-MODIFIED:")) {
                    bw.write(line);
                    bw.write(NEWLINE);
                } else if (this.lastmod != null) {
                    bw.write(ICAL_LASTMOD);
                    bw.write(COLON);
                    bw.write(getUTCDateTimeString(this.lastmod.getTime()));
                    bw.write(NEWLINE);
                }
            }
            bw.flush();
            return;
        }
        String[] customProperties = null;
        if (!(this.olsonzid == null || ICU_TZVERSION == null)) {
            customProperties = new String[]{"X-TZINFO:" + this.olsonzid + "[" + ICU_TZVERSION + "]"};
        }
        writeZone(writer, this.tz, customProperties);
    }

    public void write(Writer writer, long start) throws IOException {
        TimeZoneRule[] rules = this.tz.getTimeZoneRules(start);
        RuleBasedTimeZone rbtz = new RuleBasedTimeZone(this.tz.getID(), (InitialTimeZoneRule) rules[0]);
        for (int i = 1; i < rules.length; i++) {
            rbtz.addTransitionRule(rules[i]);
        }
        String[] customProperties = null;
        if (!(this.olsonzid == null || ICU_TZVERSION == null)) {
            customProperties = new String[]{"X-TZINFO:" + this.olsonzid + "[" + ICU_TZVERSION + "/Partial@" + start + "]"};
        }
        writeZone(writer, rbtz, customProperties);
    }

    public void writeSimple(Writer writer, long time) throws IOException {
        TimeZoneRule[] rules = this.tz.getSimpleTimeZoneRulesNear(time);
        RuleBasedTimeZone rbtz = new RuleBasedTimeZone(this.tz.getID(), (InitialTimeZoneRule) rules[0]);
        for (int i = 1; i < rules.length; i++) {
            rbtz.addTransitionRule(rules[i]);
        }
        String[] customProperties = null;
        if (!(this.olsonzid == null || ICU_TZVERSION == null)) {
            customProperties = new String[]{"X-TZINFO:" + this.olsonzid + "[" + ICU_TZVERSION + "/Simple@" + time + "]"};
        }
        writeZone(writer, rbtz, customProperties);
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneTransition getNextTransition(long base, boolean inclusive) {
        return this.tz.getNextTransition(base, inclusive);
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneTransition getPreviousTransition(long base, boolean inclusive) {
        return this.tz.getPreviousTransition(base, inclusive);
    }

    @Override // android.icu.util.BasicTimeZone
    public boolean hasEquivalentTransitions(TimeZone other, long start, long end) {
        if (this == other) {
            return true;
        }
        return this.tz.hasEquivalentTransitions(other, start, end);
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneRule[] getTimeZoneRules() {
        return this.tz.getTimeZoneRules();
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneRule[] getTimeZoneRules(long start) {
        return this.tz.getTimeZoneRules(start);
    }

    @Override // android.icu.util.TimeZone
    public Object clone() {
        if (isFrozen()) {
            return this;
        }
        return cloneAsThawed();
    }

    private VTimeZone() {
    }

    private VTimeZone(String tzid) {
        super(tzid);
    }

    private boolean load(Reader reader) {
        try {
            this.vtzlines = new LinkedList();
            boolean eol = false;
            boolean start = false;
            boolean success = false;
            StringBuilder line = new StringBuilder();
            while (true) {
                int ch = reader.read();
                if (ch == -1) {
                    if (start && line.toString().startsWith(ICAL_END_VTIMEZONE)) {
                        this.vtzlines.add(line.toString());
                        success = true;
                    }
                } else if (ch != 13) {
                    if (eol) {
                        if (!(ch == 9 || ch == 32)) {
                            if (start && line.length() > 0) {
                                this.vtzlines.add(line.toString());
                            }
                            line.setLength(0);
                            if (ch != 10) {
                                line.append((char) ch);
                            }
                        }
                        eol = false;
                    } else if (ch == 10) {
                        eol = true;
                        if (start) {
                            if (line.toString().startsWith(ICAL_END_VTIMEZONE)) {
                                this.vtzlines.add(line.toString());
                                success = true;
                                break;
                            }
                        } else if (line.toString().startsWith(ICAL_BEGIN_VTIMEZONE)) {
                            this.vtzlines.add(line.toString());
                            line.setLength(0);
                            start = true;
                            eol = false;
                        }
                    } else {
                        line.append((char) ch);
                    }
                }
            }
            if (!success) {
                return false;
            }
            return parse();
        } catch (IOException e) {
            return false;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r15v4 'from'  java.lang.String: [D('from' java.lang.String), D('line' java.lang.String)] */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01c7  */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x01cc  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x026a  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x0265 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean parse() {
        /*
        // Method dump skipped, instructions count: 965
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.VTimeZone.parse():boolean");
    }

    private static String getDefaultTZName(String tzid, boolean isDST) {
        if (isDST) {
            return tzid + "(DST)";
        }
        return tzid + "(STD)";
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v9, types: [android.icu.util.TimeZoneRule] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.icu.util.TimeZoneRule createRuleByRRULE(java.lang.String r29, int r30, int r31, long r32, java.util.List<java.lang.String> r34, int r35) {
        /*
        // Method dump skipped, instructions count: 540
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.VTimeZone.createRuleByRRULE(java.lang.String, int, int, long, java.util.List, int):android.icu.util.TimeZoneRule");
    }

    /* JADX INFO: Multiple debug info for r0v34 int: [D('nthDayOfWeek' int), D('n' int)] */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x011f, code lost:
        r7 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int[] parseRRULE(java.lang.String r21, long[] r22) {
        /*
        // Method dump skipped, instructions count: 411
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.VTimeZone.parseRRULE(java.lang.String, long[]):int[]");
    }

    private static TimeZoneRule createRuleByRDATE(String tzname, int rawOffset, int dstSavings, long start, List<String> dates, int fromOffset) {
        long[] times;
        if (dates == null || dates.size() == 0) {
            times = new long[]{start};
        } else {
            times = new long[dates.size()];
            int idx = 0;
            try {
                for (String date : dates) {
                    int idx2 = idx + 1;
                    try {
                        times[idx] = parseDateTimeString(date, fromOffset);
                        idx = idx2;
                    } catch (IllegalArgumentException e) {
                        return null;
                    }
                }
            } catch (IllegalArgumentException e2) {
                return null;
            }
        }
        return new TimeArrayTimeZoneRule(tzname, rawOffset, dstSavings, times, 2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r44v8, resolved type: boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r44v10, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: Multiple debug info for r35v1 int: [D('stdToOffset' int), D('t' long)] */
    /* JADX INFO: Multiple debug info for r23v6 'dstToOffset'  int: [D('stdMonth' int), D('dstToOffset' int)] */
    /* JADX INFO: Multiple debug info for r28v6 'dstName'  java.lang.String: [D('dstName' java.lang.String), D('stdFromOffset' int)] */
    /* JADX INFO: Multiple debug info for r23v13 'stdMonth'  int: [D('stdMonth' int), D('dstToOffset' int)] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0362  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x03be  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeZone(java.io.Writer r66, android.icu.util.BasicTimeZone r67, java.lang.String[] r68) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 1569
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.util.VTimeZone.writeZone(java.io.Writer, android.icu.util.BasicTimeZone, java.lang.String[]):void");
    }

    private static boolean isEquivalentDateRule(int month, int weekInMonth, int dayOfWeek, DateTimeRule dtrule) {
        if (month != dtrule.getRuleMonth() || dayOfWeek != dtrule.getRuleDayOfWeek() || dtrule.getTimeRuleType() != 0) {
            return false;
        }
        if (dtrule.getDateRuleType() == 1 && dtrule.getRuleWeekInMonth() == weekInMonth) {
            return true;
        }
        int ruleDOM = dtrule.getRuleDayOfMonth();
        if (dtrule.getDateRuleType() == 2) {
            if (ruleDOM % 7 == 1 && (ruleDOM + 6) / 7 == weekInMonth) {
                return true;
            }
            if (month != 1) {
                int[] iArr = MONTHLENGTH;
                if ((iArr[month] - ruleDOM) % 7 == 6 && weekInMonth == (((iArr[month] - ruleDOM) + 1) / 7) * -1) {
                    return true;
                }
            }
        }
        if (dtrule.getDateRuleType() == 3) {
            if (ruleDOM % 7 == 0 && ruleDOM / 7 == weekInMonth) {
                return true;
            }
            if (month != 1) {
                int[] iArr2 = MONTHLENGTH;
                if ((iArr2[month] - ruleDOM) % 7 == 0 && weekInMonth == (((iArr2[month] - ruleDOM) / 7) + 1) * -1) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private static void writeZonePropsByTime(Writer writer, boolean isDst, String tzname, int fromOffset, int toOffset, long time, boolean withRDATE) throws IOException {
        beginZoneProps(writer, isDst, tzname, fromOffset, toOffset, time);
        if (withRDATE) {
            writer.write(ICAL_RDATE);
            writer.write(COLON);
            writer.write(getDateTimeString(((long) fromOffset) + time));
            writer.write(NEWLINE);
        }
        endZoneProps(writer, isDst);
    }

    private static void writeZonePropsByDOM(Writer writer, boolean isDst, String tzname, int fromOffset, int toOffset, int month, int dayOfMonth, long startTime, long untilTime) throws IOException {
        beginZoneProps(writer, isDst, tzname, fromOffset, toOffset, startTime);
        beginRRULE(writer, month);
        writer.write(ICAL_BYMONTHDAY);
        writer.write(EQUALS_SIGN);
        writer.write(Integer.toString(dayOfMonth));
        if (untilTime != Long.MAX_VALUE) {
            appendUNTIL(writer, getDateTimeString(untilTime + ((long) fromOffset)));
        }
        writer.write(NEWLINE);
        endZoneProps(writer, isDst);
    }

    private static void writeZonePropsByDOW(Writer writer, boolean isDst, String tzname, int fromOffset, int toOffset, int month, int weekInMonth, int dayOfWeek, long startTime, long untilTime) throws IOException {
        beginZoneProps(writer, isDst, tzname, fromOffset, toOffset, startTime);
        beginRRULE(writer, month);
        writer.write(ICAL_BYDAY);
        writer.write(EQUALS_SIGN);
        writer.write(Integer.toString(weekInMonth));
        writer.write(ICAL_DOW_NAMES[dayOfWeek - 1]);
        if (untilTime != Long.MAX_VALUE) {
            appendUNTIL(writer, getDateTimeString(untilTime + ((long) fromOffset)));
        }
        writer.write(NEWLINE);
        endZoneProps(writer, isDst);
    }

    private static void writeZonePropsByDOW_GEQ_DOM(Writer writer, boolean isDst, String tzname, int fromOffset, int toOffset, int month, int dayOfMonth, int dayOfWeek, long startTime, long untilTime) throws IOException {
        int currentMonthDays;
        int startDay;
        if (dayOfMonth % 7 == 1) {
            writeZonePropsByDOW(writer, isDst, tzname, fromOffset, toOffset, month, (dayOfMonth + 6) / 7, dayOfWeek, startTime, untilTime);
            return;
        }
        if (month != 1) {
            int[] iArr = MONTHLENGTH;
            if ((iArr[month] - dayOfMonth) % 7 == 6) {
                writeZonePropsByDOW(writer, isDst, tzname, fromOffset, toOffset, month, (((iArr[month] - dayOfMonth) + 1) / 7) * -1, dayOfWeek, startTime, untilTime);
                return;
            }
        }
        beginZoneProps(writer, isDst, tzname, fromOffset, toOffset, startTime);
        int startDay2 = dayOfMonth;
        int currentMonthDays2 = 7;
        int prevMonth = 11;
        if (dayOfMonth <= 0) {
            int prevMonthDays = 1 - dayOfMonth;
            currentMonthDays2 = 7 - prevMonthDays;
            if (month - 1 >= 0) {
                prevMonth = month - 1;
            }
            writeZonePropsByDOW_GEQ_DOM_sub(writer, prevMonth, -prevMonthDays, dayOfWeek, prevMonthDays, Long.MAX_VALUE, fromOffset);
            startDay2 = 1;
        } else {
            int i = dayOfMonth + 6;
            int[] iArr2 = MONTHLENGTH;
            if (i > iArr2[month]) {
                int nextMonthDays = (dayOfMonth + 6) - iArr2[month];
                int currentMonthDays3 = 7 - nextMonthDays;
                writeZonePropsByDOW_GEQ_DOM_sub(writer, month + 1 > 11 ? 0 : month + 1, 1, dayOfWeek, nextMonthDays, Long.MAX_VALUE, fromOffset);
                startDay = startDay2;
                currentMonthDays = currentMonthDays3;
                writeZonePropsByDOW_GEQ_DOM_sub(writer, month, startDay, dayOfWeek, currentMonthDays, untilTime, fromOffset);
                endZoneProps(writer, isDst);
            }
        }
        startDay = startDay2;
        currentMonthDays = currentMonthDays2;
        writeZonePropsByDOW_GEQ_DOM_sub(writer, month, startDay, dayOfWeek, currentMonthDays, untilTime, fromOffset);
        endZoneProps(writer, isDst);
    }

    private static void writeZonePropsByDOW_GEQ_DOM_sub(Writer writer, int month, int dayOfMonth, int dayOfWeek, int numDays, long untilTime, int fromOffset) throws IOException {
        int startDayNum = dayOfMonth;
        boolean isFeb = month == 1;
        if (dayOfMonth < 0 && !isFeb) {
            startDayNum = MONTHLENGTH[month] + dayOfMonth + 1;
        }
        beginRRULE(writer, month);
        writer.write(ICAL_BYDAY);
        writer.write(EQUALS_SIGN);
        writer.write(ICAL_DOW_NAMES[dayOfWeek - 1]);
        writer.write(SEMICOLON);
        writer.write(ICAL_BYMONTHDAY);
        writer.write(EQUALS_SIGN);
        writer.write(Integer.toString(startDayNum));
        for (int i = 1; i < numDays; i++) {
            writer.write(COMMA);
            writer.write(Integer.toString(startDayNum + i));
        }
        if (untilTime != Long.MAX_VALUE) {
            appendUNTIL(writer, getDateTimeString(((long) fromOffset) + untilTime));
        }
        writer.write(NEWLINE);
    }

    private static void writeZonePropsByDOW_LEQ_DOM(Writer writer, boolean isDst, String tzname, int fromOffset, int toOffset, int month, int dayOfMonth, int dayOfWeek, long startTime, long untilTime) throws IOException {
        if (dayOfMonth % 7 == 0) {
            writeZonePropsByDOW(writer, isDst, tzname, fromOffset, toOffset, month, dayOfMonth / 7, dayOfWeek, startTime, untilTime);
            return;
        }
        if (month != 1) {
            int[] iArr = MONTHLENGTH;
            if ((iArr[month] - dayOfMonth) % 7 == 0) {
                writeZonePropsByDOW(writer, isDst, tzname, fromOffset, toOffset, month, (((iArr[month] - dayOfMonth) / 7) + 1) * -1, dayOfWeek, startTime, untilTime);
                return;
            }
        }
        if (month == 1 && dayOfMonth == 29) {
            writeZonePropsByDOW(writer, isDst, tzname, fromOffset, toOffset, 1, -1, dayOfWeek, startTime, untilTime);
        } else {
            writeZonePropsByDOW_GEQ_DOM(writer, isDst, tzname, fromOffset, toOffset, month, dayOfMonth - 6, dayOfWeek, startTime, untilTime);
        }
    }

    private static void writeFinalRule(Writer writer, boolean isDst, AnnualTimeZoneRule rule, int fromRawOffset, int fromDSTSavings, long startTime) throws IOException {
        long startTime2;
        DateTimeRule dtrule = toWallTimeRule(rule.getRule(), fromRawOffset, fromDSTSavings);
        int timeInDay = dtrule.getRuleMillisInDay();
        if (timeInDay < 0) {
            startTime2 = startTime + ((long) (0 - timeInDay));
        } else if (timeInDay >= 86400000) {
            startTime2 = startTime - ((long) (timeInDay - 86399999));
        } else {
            startTime2 = startTime;
        }
        int toOffset = rule.getRawOffset() + rule.getDSTSavings();
        int dateRuleType = dtrule.getDateRuleType();
        if (dateRuleType == 0) {
            writeZonePropsByDOM(writer, isDst, rule.getName(), fromRawOffset + fromDSTSavings, toOffset, dtrule.getRuleMonth(), dtrule.getRuleDayOfMonth(), startTime2, Long.MAX_VALUE);
        } else if (dateRuleType == 1) {
            writeZonePropsByDOW(writer, isDst, rule.getName(), fromRawOffset + fromDSTSavings, toOffset, dtrule.getRuleMonth(), dtrule.getRuleWeekInMonth(), dtrule.getRuleDayOfWeek(), startTime2, Long.MAX_VALUE);
        } else if (dateRuleType == 2) {
            writeZonePropsByDOW_GEQ_DOM(writer, isDst, rule.getName(), fromRawOffset + fromDSTSavings, toOffset, dtrule.getRuleMonth(), dtrule.getRuleDayOfMonth(), dtrule.getRuleDayOfWeek(), startTime2, Long.MAX_VALUE);
        } else if (dateRuleType == 3) {
            writeZonePropsByDOW_LEQ_DOM(writer, isDst, rule.getName(), fromRawOffset + fromDSTSavings, toOffset, dtrule.getRuleMonth(), dtrule.getRuleDayOfMonth(), dtrule.getRuleDayOfWeek(), startTime2, Long.MAX_VALUE);
        }
    }

    private static DateTimeRule toWallTimeRule(DateTimeRule rule, int rawOffset, int dstSavings) {
        if (rule.getTimeRuleType() == 0) {
            return rule;
        }
        int wallt = rule.getRuleMillisInDay();
        if (rule.getTimeRuleType() == 2) {
            wallt += rawOffset + dstSavings;
        } else if (rule.getTimeRuleType() == 1) {
            wallt += dstSavings;
        }
        int dshift = 0;
        if (wallt < 0) {
            dshift = -1;
            wallt += Grego.MILLIS_PER_DAY;
        } else if (wallt >= 86400000) {
            dshift = 1;
            wallt -= Grego.MILLIS_PER_DAY;
        }
        int month = rule.getRuleMonth();
        int dom = rule.getRuleDayOfMonth();
        int dow = rule.getRuleDayOfWeek();
        int dtype = rule.getDateRuleType();
        if (dshift != 0) {
            if (dtype == 1) {
                int wim = rule.getRuleWeekInMonth();
                if (wim > 0) {
                    dtype = 2;
                    dom = ((wim - 1) * 7) + 1;
                } else {
                    dtype = 3;
                    dom = MONTHLENGTH[month] + ((wim + 1) * 7);
                }
            }
            dom += dshift;
            int i = 11;
            if (dom == 0) {
                int month2 = month - 1;
                if (month2 >= 0) {
                    i = month2;
                }
                month = i;
                dom = MONTHLENGTH[month];
            } else if (dom > MONTHLENGTH[month]) {
                int month3 = month + 1;
                month = month3 > 11 ? 0 : month3;
                dom = 1;
            }
            if (dtype != 0) {
                dow += dshift;
                if (dow < 1) {
                    dow = 7;
                } else if (dow > 7) {
                    dow = 1;
                }
            }
        }
        if (dtype == 0) {
            return new DateTimeRule(month, dom, wallt, 0);
        }
        return new DateTimeRule(month, dom, dow, dtype == 2, wallt, 0);
    }

    private static void beginZoneProps(Writer writer, boolean isDst, String tzname, int fromOffset, int toOffset, long startTime) throws IOException {
        writer.write(ICAL_BEGIN);
        writer.write(COLON);
        if (isDst) {
            writer.write(ICAL_DAYLIGHT);
        } else {
            writer.write(ICAL_STANDARD);
        }
        writer.write(NEWLINE);
        writer.write(ICAL_TZOFFSETTO);
        writer.write(COLON);
        writer.write(millisToOffset(toOffset));
        writer.write(NEWLINE);
        writer.write(ICAL_TZOFFSETFROM);
        writer.write(COLON);
        writer.write(millisToOffset(fromOffset));
        writer.write(NEWLINE);
        writer.write(ICAL_TZNAME);
        writer.write(COLON);
        writer.write(tzname);
        writer.write(NEWLINE);
        writer.write(ICAL_DTSTART);
        writer.write(COLON);
        writer.write(getDateTimeString(((long) fromOffset) + startTime));
        writer.write(NEWLINE);
    }

    private static void endZoneProps(Writer writer, boolean isDst) throws IOException {
        writer.write(ICAL_END);
        writer.write(COLON);
        if (isDst) {
            writer.write(ICAL_DAYLIGHT);
        } else {
            writer.write(ICAL_STANDARD);
        }
        writer.write(NEWLINE);
    }

    private static void beginRRULE(Writer writer, int month) throws IOException {
        writer.write(ICAL_RRULE);
        writer.write(COLON);
        writer.write(ICAL_FREQ);
        writer.write(EQUALS_SIGN);
        writer.write(ICAL_YEARLY);
        writer.write(SEMICOLON);
        writer.write(ICAL_BYMONTH);
        writer.write(EQUALS_SIGN);
        writer.write(Integer.toString(month + 1));
        writer.write(SEMICOLON);
    }

    private static void appendUNTIL(Writer writer, String until) throws IOException {
        if (until != null) {
            writer.write(SEMICOLON);
            writer.write(ICAL_UNTIL);
            writer.write(EQUALS_SIGN);
            writer.write(until);
        }
    }

    private void writeHeader(Writer writer) throws IOException {
        writer.write(ICAL_BEGIN);
        writer.write(COLON);
        writer.write(ICAL_VTIMEZONE);
        writer.write(NEWLINE);
        writer.write(ICAL_TZID);
        writer.write(COLON);
        writer.write(this.tz.getID());
        writer.write(NEWLINE);
        if (this.tzurl != null) {
            writer.write(ICAL_TZURL);
            writer.write(COLON);
            writer.write(this.tzurl);
            writer.write(NEWLINE);
        }
        if (this.lastmod != null) {
            writer.write(ICAL_LASTMOD);
            writer.write(COLON);
            writer.write(getUTCDateTimeString(this.lastmod.getTime()));
            writer.write(NEWLINE);
        }
    }

    private static void writeFooter(Writer writer) throws IOException {
        writer.write(ICAL_END);
        writer.write(COLON);
        writer.write(ICAL_VTIMEZONE);
        writer.write(NEWLINE);
    }

    private static String getDateTimeString(long time) {
        int[] fields = Grego.timeToFields(time, null);
        StringBuilder sb = new StringBuilder(15);
        sb.append(numToString(fields[0], 4));
        sb.append(numToString(fields[1] + 1, 2));
        sb.append(numToString(fields[2], 2));
        sb.append('T');
        int t = fields[5];
        int hour = t / 3600000;
        int t2 = t % 3600000;
        sb.append(numToString(hour, 2));
        sb.append(numToString(t2 / 60000, 2));
        sb.append(numToString((t2 % 60000) / 1000, 2));
        return sb.toString();
    }

    private static String getUTCDateTimeString(long time) {
        return getDateTimeString(time) + "Z";
    }

    private static long parseDateTimeString(String str, int offset) {
        int length;
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int min = 0;
        int sec = 0;
        boolean isUTC = false;
        boolean isValid = false;
        if (str != null && (((length = str.length()) == 15 || length == 16) && str.charAt(8) == 'T')) {
            if (length == 16) {
                if (str.charAt(15) == 'Z') {
                    isUTC = true;
                }
            }
            try {
                year = Integer.parseInt(str.substring(0, 4));
                month = Integer.parseInt(str.substring(4, 6)) - 1;
                day = Integer.parseInt(str.substring(6, 8));
                hour = Integer.parseInt(str.substring(9, 11));
                min = Integer.parseInt(str.substring(11, 13));
                sec = Integer.parseInt(str.substring(13, 15));
                int maxDayOfMonth = Grego.monthLength(year, month);
                if (year >= 0 && month >= 0 && month <= 11 && day >= 1 && day <= maxDayOfMonth && hour >= 0 && hour < 24 && min >= 0 && min < 60 && sec >= 0 && sec < 60) {
                    isValid = true;
                }
            } catch (NumberFormatException e) {
            }
        }
        if (isValid) {
            long time = (Grego.fieldsToDay(year, month, day) * 86400000) + ((long) ((3600000 * hour) + (60000 * min) + (sec * 1000)));
            if (!isUTC) {
                return time - ((long) offset);
            }
            return time;
        }
        throw new IllegalArgumentException("Invalid date time string format");
    }

    private static int offsetStrToMillis(String str) {
        int length;
        boolean isValid = false;
        int sign = 0;
        int hour = 0;
        int min = 0;
        int sec = 0;
        if (str != null && ((length = str.length()) == 5 || length == 7)) {
            char s = str.charAt(0);
            if (s == '+') {
                sign = 1;
            } else if (s == '-') {
                sign = -1;
            }
            try {
                hour = Integer.parseInt(str.substring(1, 3));
                min = Integer.parseInt(str.substring(3, 5));
                if (length == 7) {
                    sec = Integer.parseInt(str.substring(5, 7));
                }
                isValid = true;
            } catch (NumberFormatException e) {
            }
        }
        if (isValid) {
            return ((((hour * 60) + min) * 60) + sec) * sign * 1000;
        }
        throw new IllegalArgumentException("Bad offset string");
    }

    /* JADX INFO: Multiple debug info for r1v2 int: [D('t' int), D('min' int)] */
    private static String millisToOffset(int millis) {
        StringBuilder sb = new StringBuilder(7);
        if (millis >= 0) {
            sb.append('+');
        } else {
            sb.append('-');
            millis = -millis;
        }
        int t = millis / 1000;
        int sec = t % 60;
        int t2 = (t - sec) / 60;
        sb.append(numToString(t2 / 60, 2));
        sb.append(numToString(t2 % 60, 2));
        sb.append(numToString(sec, 2));
        return sb.toString();
    }

    private static String numToString(int num, int width) {
        String str = Integer.toString(num);
        int len = str.length();
        if (len >= width) {
            return str.substring(len - width, len);
        }
        StringBuilder sb = new StringBuilder(width);
        for (int i = len; i < width; i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }

    @Override // android.icu.util.Freezable, android.icu.util.TimeZone
    public boolean isFrozen() {
        return this.isFrozen;
    }

    @Override // android.icu.util.Freezable, android.icu.util.TimeZone, android.icu.util.TimeZone
    public TimeZone freeze() {
        this.isFrozen = true;
        return this;
    }

    @Override // android.icu.util.Freezable, android.icu.util.TimeZone, android.icu.util.TimeZone
    public TimeZone cloneAsThawed() {
        VTimeZone vtz = (VTimeZone) super.cloneAsThawed();
        vtz.tz = (BasicTimeZone) this.tz.cloneAsThawed();
        vtz.isFrozen = false;
        return vtz;
    }
}
