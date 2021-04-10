package android.icu.util;

import android.icu.impl.Grego;
import java.util.Date;

public class AnnualTimeZoneRule extends TimeZoneRule {
    public static final int MAX_YEAR = Integer.MAX_VALUE;
    private static final long serialVersionUID = -8870666707791230688L;
    private final DateTimeRule dateTimeRule;
    private final int endYear;
    private final int startYear;

    public AnnualTimeZoneRule(String name, int rawOffset, int dstSavings, DateTimeRule dateTimeRule2, int startYear2, int endYear2) {
        super(name, rawOffset, dstSavings);
        this.dateTimeRule = dateTimeRule2;
        this.startYear = startYear2;
        this.endYear = endYear2;
    }

    public DateTimeRule getRule() {
        return this.dateTimeRule;
    }

    public int getStartYear() {
        return this.startYear;
    }

    public int getEndYear() {
        return this.endYear;
    }

    public Date getStartInYear(int year, int prevRawOffset, int prevDSTSavings) {
        long ruleDay;
        long ruleDay2;
        int delta;
        if (year < this.startYear || year > this.endYear) {
            return null;
        }
        int type = this.dateTimeRule.getDateRuleType();
        if (type == 0) {
            ruleDay = Grego.fieldsToDay(year, this.dateTimeRule.getRuleMonth(), this.dateTimeRule.getRuleDayOfMonth());
        } else {
            boolean after = true;
            if (type == 1) {
                int weeks = this.dateTimeRule.getRuleWeekInMonth();
                if (weeks > 0) {
                    ruleDay2 = Grego.fieldsToDay(year, this.dateTimeRule.getRuleMonth(), 1) + ((long) ((weeks - 1) * 7));
                } else {
                    after = false;
                    ruleDay2 = Grego.fieldsToDay(year, this.dateTimeRule.getRuleMonth(), Grego.monthLength(year, this.dateTimeRule.getRuleMonth())) + ((long) ((weeks + 1) * 7));
                }
            } else {
                int month = this.dateTimeRule.getRuleMonth();
                int dom = this.dateTimeRule.getRuleDayOfMonth();
                if (type == 3) {
                    after = false;
                    if (month == 1 && dom == 29 && !Grego.isLeapYear(year)) {
                        dom--;
                    }
                }
                ruleDay2 = Grego.fieldsToDay(year, month, dom);
            }
            int delta2 = this.dateTimeRule.getRuleDayOfWeek() - Grego.dayOfWeek(ruleDay2);
            if (after) {
                delta = delta2 < 0 ? delta2 + 7 : delta2;
            } else {
                delta = delta2 > 0 ? delta2 - 7 : delta2;
            }
            ruleDay = ruleDay2 + ((long) delta);
        }
        long ruleTime = (86400000 * ruleDay) + ((long) this.dateTimeRule.getRuleMillisInDay());
        if (this.dateTimeRule.getTimeRuleType() != 2) {
            ruleTime -= (long) prevRawOffset;
        }
        if (this.dateTimeRule.getTimeRuleType() == 0) {
            ruleTime -= (long) prevDSTSavings;
        }
        return new Date(ruleTime);
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getFirstStart(int prevRawOffset, int prevDSTSavings) {
        return getStartInYear(this.startYear, prevRawOffset, prevDSTSavings);
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getFinalStart(int prevRawOffset, int prevDSTSavings) {
        int i = this.endYear;
        if (i == Integer.MAX_VALUE) {
            return null;
        }
        return getStartInYear(i, prevRawOffset, prevDSTSavings);
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getNextStart(long base, int prevRawOffset, int prevDSTSavings, boolean inclusive) {
        int year = Grego.timeToFields(base, null)[0];
        if (year < this.startYear) {
            return getFirstStart(prevRawOffset, prevDSTSavings);
        }
        Date d = getStartInYear(year, prevRawOffset, prevDSTSavings);
        if (d == null) {
            return d;
        }
        if (d.getTime() < base || (!inclusive && d.getTime() == base)) {
            return getStartInYear(year + 1, prevRawOffset, prevDSTSavings);
        }
        return d;
    }

    @Override // android.icu.util.TimeZoneRule
    public Date getPreviousStart(long base, int prevRawOffset, int prevDSTSavings, boolean inclusive) {
        int year = Grego.timeToFields(base, null)[0];
        if (year > this.endYear) {
            return getFinalStart(prevRawOffset, prevDSTSavings);
        }
        Date d = getStartInYear(year, prevRawOffset, prevDSTSavings);
        if (d == null) {
            return d;
        }
        if (d.getTime() > base || (!inclusive && d.getTime() == base)) {
            return getStartInYear(year - 1, prevRawOffset, prevDSTSavings);
        }
        return d;
    }

    @Override // android.icu.util.TimeZoneRule
    public boolean isEquivalentTo(TimeZoneRule other) {
        if (!(other instanceof AnnualTimeZoneRule)) {
            return false;
        }
        AnnualTimeZoneRule otherRule = (AnnualTimeZoneRule) other;
        if (this.startYear == otherRule.startYear && this.endYear == otherRule.endYear && this.dateTimeRule.equals(otherRule.dateTimeRule)) {
            return super.isEquivalentTo(other);
        }
        return false;
    }

    @Override // android.icu.util.TimeZoneRule
    public boolean isTransitionRule() {
        return true;
    }

    @Override // android.icu.util.TimeZoneRule
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(super.toString());
        buf.append(", rule={" + ((Object) this.dateTimeRule) + "}");
        StringBuilder sb = new StringBuilder();
        sb.append(", startYear=");
        sb.append(this.startYear);
        buf.append(sb.toString());
        buf.append(", endYear=");
        int i = this.endYear;
        if (i == Integer.MAX_VALUE) {
            buf.append("max");
        } else {
            buf.append(i);
        }
        return buf.toString();
    }
}
