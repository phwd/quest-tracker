package android.icu.util;

import android.icu.impl.Grego;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class RuleBasedTimeZone extends BasicTimeZone {
    private static final long serialVersionUID = 7580833058949327935L;
    private AnnualTimeZoneRule[] finalRules;
    private List<TimeZoneRule> historicRules;
    private transient List<TimeZoneTransition> historicTransitions;
    private final InitialTimeZoneRule initialRule;
    private volatile transient boolean isFrozen = false;
    private transient boolean upToDate;

    public RuleBasedTimeZone(String id, InitialTimeZoneRule initialRule2) {
        super(id);
        this.initialRule = initialRule2;
    }

    public void addTransitionRule(TimeZoneRule rule) {
        if (isFrozen()) {
            throw new UnsupportedOperationException("Attempt to modify a frozen RuleBasedTimeZone instance.");
        } else if (rule.isTransitionRule()) {
            if (!(rule instanceof AnnualTimeZoneRule) || ((AnnualTimeZoneRule) rule).getEndYear() != Integer.MAX_VALUE) {
                if (this.historicRules == null) {
                    this.historicRules = new ArrayList();
                }
                this.historicRules.add(rule);
            } else {
                AnnualTimeZoneRule[] annualTimeZoneRuleArr = this.finalRules;
                if (annualTimeZoneRuleArr == null) {
                    this.finalRules = new AnnualTimeZoneRule[2];
                    this.finalRules[0] = (AnnualTimeZoneRule) rule;
                } else if (annualTimeZoneRuleArr[1] == null) {
                    annualTimeZoneRuleArr[1] = (AnnualTimeZoneRule) rule;
                } else {
                    throw new IllegalStateException("Too many final rules");
                }
            }
            this.upToDate = false;
        } else {
            throw new IllegalArgumentException("Rule must be a transition rule");
        }
    }

    @Override // android.icu.util.TimeZone
    public int getOffset(int era, int year, int month, int day, int dayOfWeek, int milliseconds) {
        int year2;
        if (era == 0) {
            year2 = 1 - year;
        } else {
            year2 = year;
        }
        int[] offsets = new int[2];
        getOffset((Grego.fieldsToDay(year2, month, day) * 86400000) + ((long) milliseconds), true, 3, 1, offsets);
        return offsets[0] + offsets[1];
    }

    @Override // android.icu.util.TimeZone
    public void getOffset(long time, boolean local, int[] offsets) {
        getOffset(time, local, 4, 12, offsets);
    }

    @Override // android.icu.util.BasicTimeZone
    @Deprecated
    public void getOffsetFromLocal(long date, int nonExistingTimeOpt, int duplicatedTimeOpt, int[] offsets) {
        getOffset(date, true, nonExistingTimeOpt, duplicatedTimeOpt, offsets);
    }

    @Override // android.icu.util.TimeZone
    public int getRawOffset() {
        int[] offsets = new int[2];
        getOffset(System.currentTimeMillis(), false, offsets);
        return offsets[0];
    }

    @Override // android.icu.util.TimeZone
    public boolean inDaylightTime(Date date) {
        int[] offsets = new int[2];
        getOffset(date.getTime(), false, offsets);
        if (offsets[1] != 0) {
            return true;
        }
        return false;
    }

    @Override // android.icu.util.TimeZone
    public void setRawOffset(int offsetMillis) {
        throw new UnsupportedOperationException("setRawOffset in RuleBasedTimeZone is not supported.");
    }

    @Override // android.icu.util.TimeZone
    public boolean useDaylightTime() {
        long now = System.currentTimeMillis();
        int[] offsets = new int[2];
        getOffset(now, false, offsets);
        if (offsets[1] != 0) {
            return true;
        }
        TimeZoneTransition tt = getNextTransition(now, false);
        if (tt == null || tt.getTo().getDSTSavings() == 0) {
            return false;
        }
        return true;
    }

    @Override // android.icu.util.TimeZone
    public boolean observesDaylightTime() {
        long time = System.currentTimeMillis();
        int[] offsets = new int[2];
        getOffset(time, false, offsets);
        if (offsets[1] != 0) {
            return true;
        }
        AnnualTimeZoneRule[] annualTimeZoneRuleArr = this.finalRules;
        BitSet checkFinals = annualTimeZoneRuleArr == null ? null : new BitSet(annualTimeZoneRuleArr.length);
        while (true) {
            TimeZoneTransition tt = getNextTransition(time, false);
            if (tt == null) {
                break;
            }
            TimeZoneRule toRule = tt.getTo();
            if (toRule.getDSTSavings() != 0) {
                return true;
            }
            if (checkFinals != null) {
                int i = 0;
                while (true) {
                    AnnualTimeZoneRule[] annualTimeZoneRuleArr2 = this.finalRules;
                    if (i >= annualTimeZoneRuleArr2.length) {
                        break;
                    }
                    if (annualTimeZoneRuleArr2[i].equals(toRule)) {
                        checkFinals.set(i);
                    }
                    i++;
                }
                if (checkFinals.cardinality() == this.finalRules.length) {
                    break;
                }
            }
            time = tt.getTime();
        }
        return false;
    }

    @Override // android.icu.util.TimeZone
    public boolean hasSameRules(TimeZone other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RuleBasedTimeZone)) {
            return false;
        }
        RuleBasedTimeZone otherRBTZ = (RuleBasedTimeZone) other;
        if (!this.initialRule.isEquivalentTo(otherRBTZ.initialRule)) {
            return false;
        }
        if (this.finalRules != null && otherRBTZ.finalRules != null) {
            int i = 0;
            while (true) {
                AnnualTimeZoneRule[] annualTimeZoneRuleArr = this.finalRules;
                if (i >= annualTimeZoneRuleArr.length) {
                    break;
                }
                if (annualTimeZoneRuleArr[i] != null || otherRBTZ.finalRules[i] != null) {
                    AnnualTimeZoneRule[] annualTimeZoneRuleArr2 = this.finalRules;
                    if (annualTimeZoneRuleArr2[i] == null) {
                        break;
                    }
                    AnnualTimeZoneRule[] annualTimeZoneRuleArr3 = otherRBTZ.finalRules;
                    if (annualTimeZoneRuleArr3[i] == null || !annualTimeZoneRuleArr2[i].isEquivalentTo(annualTimeZoneRuleArr3[i])) {
                        break;
                    }
                }
                i++;
            }
            return false;
        } else if (!(this.finalRules == null && otherRBTZ.finalRules == null)) {
            return false;
        }
        List<TimeZoneRule> list = this.historicRules;
        if (list == null || otherRBTZ.historicRules == null) {
            if (!(this.historicRules == null && otherRBTZ.historicRules == null)) {
                return false;
            }
        } else if (list.size() != otherRBTZ.historicRules.size()) {
            return false;
        } else {
            for (TimeZoneRule rule : this.historicRules) {
                boolean foundSameRule = false;
                Iterator<TimeZoneRule> it = otherRBTZ.historicRules.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (rule.isEquivalentTo(it.next())) {
                            foundSameRule = true;
                            continue;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (!foundSameRule) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneRule[] getTimeZoneRules() {
        int size = 1;
        List<TimeZoneRule> list = this.historicRules;
        if (list != null) {
            size = 1 + list.size();
        }
        AnnualTimeZoneRule[] annualTimeZoneRuleArr = this.finalRules;
        if (annualTimeZoneRuleArr != null) {
            if (annualTimeZoneRuleArr[1] != null) {
                size += 2;
            } else {
                size++;
            }
        }
        TimeZoneRule[] rules = new TimeZoneRule[size];
        rules[0] = this.initialRule;
        int idx = 1;
        if (this.historicRules != null) {
            while (idx < this.historicRules.size() + 1) {
                rules[idx] = this.historicRules.get(idx - 1);
                idx++;
            }
        }
        AnnualTimeZoneRule[] annualTimeZoneRuleArr2 = this.finalRules;
        if (annualTimeZoneRuleArr2 != null) {
            int idx2 = idx + 1;
            rules[idx] = annualTimeZoneRuleArr2[0];
            if (annualTimeZoneRuleArr2[1] != null) {
                rules[idx2] = annualTimeZoneRuleArr2[1];
            }
        }
        return rules;
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneTransition getNextTransition(long base, boolean inclusive) {
        long tt;
        TimeZoneTransition tzt;
        complete();
        List<TimeZoneTransition> list = this.historicTransitions;
        if (list == null) {
            return null;
        }
        boolean isFinal = false;
        TimeZoneTransition tzt2 = list.get(0);
        long tt2 = tzt2.getTime();
        if (tt2 <= base && (!inclusive || tt2 != base)) {
            int idx = this.historicTransitions.size() - 1;
            TimeZoneTransition tzt3 = this.historicTransitions.get(idx);
            long tt3 = tzt3.getTime();
            if (inclusive && tt3 == base) {
                tzt2 = tzt3;
            } else if (tt3 <= base) {
                AnnualTimeZoneRule[] annualTimeZoneRuleArr = this.finalRules;
                if (annualTimeZoneRuleArr == null) {
                    return null;
                }
                Date start0 = annualTimeZoneRuleArr[0].getNextStart(base, annualTimeZoneRuleArr[1].getRawOffset(), this.finalRules[1].getDSTSavings(), inclusive);
                AnnualTimeZoneRule[] annualTimeZoneRuleArr2 = this.finalRules;
                Date start1 = annualTimeZoneRuleArr2[1].getNextStart(base, annualTimeZoneRuleArr2[0].getRawOffset(), this.finalRules[0].getDSTSavings(), inclusive);
                if (start1.after(start0)) {
                    long time = start0.getTime();
                    AnnualTimeZoneRule[] annualTimeZoneRuleArr3 = this.finalRules;
                    tzt = new TimeZoneTransition(time, annualTimeZoneRuleArr3[1], annualTimeZoneRuleArr3[0]);
                } else {
                    long time2 = start1.getTime();
                    AnnualTimeZoneRule[] annualTimeZoneRuleArr4 = this.finalRules;
                    tzt = new TimeZoneTransition(time2, annualTimeZoneRuleArr4[0], annualTimeZoneRuleArr4[1]);
                }
                isFinal = true;
                tzt2 = tzt;
            } else {
                int idx2 = idx - 1;
                tzt2 = tzt3;
                while (true) {
                    if (idx2 <= 0) {
                        tt = tt3;
                        break;
                    }
                    TimeZoneTransition tzt4 = this.historicTransitions.get(idx2);
                    tt3 = tzt4.getTime();
                    if (tt3 < base || (!inclusive && tt3 == base)) {
                        tt = tt3;
                    } else {
                        idx2--;
                        tzt2 = tzt4;
                    }
                }
                tt = tt3;
            }
        }
        TimeZoneRule from = tzt2.getFrom();
        TimeZoneRule to = tzt2.getTo();
        if (from.getRawOffset() != to.getRawOffset() || from.getDSTSavings() != to.getDSTSavings()) {
            return tzt2;
        }
        if (isFinal) {
            return null;
        }
        return getNextTransition(tzt2.getTime(), false);
    }

    @Override // android.icu.util.BasicTimeZone
    public TimeZoneTransition getPreviousTransition(long base, boolean inclusive) {
        TimeZoneTransition result;
        TimeZoneTransition tzt;
        TimeZoneTransition tzt2;
        complete();
        List<TimeZoneTransition> list = this.historicTransitions;
        if (list == null) {
            return null;
        }
        TimeZoneTransition tzt3 = list.get(0);
        long tt = tzt3.getTime();
        if (inclusive && tt == base) {
            result = tzt3;
        } else if (tt >= base) {
            return null;
        } else {
            int idx = this.historicTransitions.size() - 1;
            TimeZoneTransition tzt4 = this.historicTransitions.get(idx);
            long tt2 = tzt4.getTime();
            if (inclusive && tt2 == base) {
                result = tzt4;
            } else if (tt2 < base) {
                AnnualTimeZoneRule[] annualTimeZoneRuleArr = this.finalRules;
                if (annualTimeZoneRuleArr != null) {
                    Date start0 = annualTimeZoneRuleArr[0].getPreviousStart(base, annualTimeZoneRuleArr[1].getRawOffset(), this.finalRules[1].getDSTSavings(), inclusive);
                    AnnualTimeZoneRule[] annualTimeZoneRuleArr2 = this.finalRules;
                    Date start1 = annualTimeZoneRuleArr2[1].getPreviousStart(base, annualTimeZoneRuleArr2[0].getRawOffset(), this.finalRules[0].getDSTSavings(), inclusive);
                    if (start1.before(start0)) {
                        long time = start0.getTime();
                        AnnualTimeZoneRule[] annualTimeZoneRuleArr3 = this.finalRules;
                        tzt2 = new TimeZoneTransition(time, annualTimeZoneRuleArr3[1], annualTimeZoneRuleArr3[0]);
                    } else {
                        long time2 = start1.getTime();
                        AnnualTimeZoneRule[] annualTimeZoneRuleArr4 = this.finalRules;
                        tzt2 = new TimeZoneTransition(time2, annualTimeZoneRuleArr4[0], annualTimeZoneRuleArr4[1]);
                    }
                } else {
                    tzt2 = tzt4;
                }
                result = tzt2;
            } else {
                int idx2 = idx - 1;
                while (true) {
                    if (idx2 < 0) {
                        tzt = tzt4;
                        break;
                    }
                    tzt4 = this.historicTransitions.get(idx2);
                    tt2 = tzt4.getTime();
                    if (tt2 < base || (inclusive && tt2 == base)) {
                        tzt = tzt4;
                    } else {
                        idx2--;
                    }
                }
                tzt = tzt4;
                result = tzt;
            }
        }
        TimeZoneRule from = result.getFrom();
        TimeZoneRule to = result.getTo();
        if (from.getRawOffset() == to.getRawOffset() && from.getDSTSavings() == to.getDSTSavings()) {
            return getPreviousTransition(result.getTime(), false);
        }
        return result;
    }

    @Override // android.icu.util.TimeZone
    public Object clone() {
        if (isFrozen()) {
            return this;
        }
        return cloneAsThawed();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:96:0x00f1 */
    /* JADX DEBUG: Multi-variable search result rejected for r9v3, resolved type: android.icu.util.AnnualTimeZoneRule[] */
    /* JADX DEBUG: Multi-variable search result rejected for r15v6, resolved type: android.icu.util.InitialTimeZoneRule */
    /* JADX WARN: Multi-variable type inference failed */
    private void complete() {
        boolean z;
        long lastTransitionTime;
        long nextTransitionTime;
        Date d;
        if (!this.upToDate) {
            AnnualTimeZoneRule[] annualTimeZoneRuleArr = this.finalRules;
            if (annualTimeZoneRuleArr == null || annualTimeZoneRuleArr[1] != null) {
                if (this.historicRules == null && this.finalRules == null) {
                    z = true;
                } else {
                    TimeZoneRule curRule = this.initialRule;
                    long lastTransitionTime2 = Grego.MIN_MILLIS;
                    List<TimeZoneRule> list = this.historicRules;
                    if (list != null) {
                        BitSet done = new BitSet(list.size());
                        while (true) {
                            int curStdOffset = curRule.getRawOffset();
                            int curDstSavings = curRule.getDSTSavings();
                            long nextTransitionTime2 = 183882168921600000L;
                            TimeZoneRule nextRule = null;
                            for (int i = 0; i < this.historicRules.size(); i++) {
                                if (!done.get(i)) {
                                    TimeZoneRule r = this.historicRules.get(i);
                                    Date d2 = r.getNextStart(lastTransitionTime2, curStdOffset, curDstSavings, false);
                                    if (d2 == null) {
                                        done.set(i);
                                    } else if (!(r == curRule || (r.getName().equals(curRule.getName()) && r.getRawOffset() == curRule.getRawOffset() && r.getDSTSavings() == curRule.getDSTSavings()))) {
                                        long tt = d2.getTime();
                                        if (tt < nextTransitionTime2) {
                                            nextTransitionTime2 = tt;
                                            nextRule = r;
                                        }
                                    }
                                }
                            }
                            if (nextRule == null) {
                                boolean bDoneAll = true;
                                int j = 0;
                                while (true) {
                                    if (j >= this.historicRules.size()) {
                                        break;
                                    } else if (!done.get(j)) {
                                        bDoneAll = false;
                                        break;
                                    } else {
                                        j++;
                                    }
                                }
                                if (bDoneAll) {
                                    break;
                                }
                            }
                            if (this.finalRules != null) {
                                for (int i2 = 0; i2 < 2; i2++) {
                                    TimeZoneRule[] timeZoneRuleArr = this.finalRules;
                                    if (!(timeZoneRuleArr[i2] == curRule || (d = timeZoneRuleArr[i2].getNextStart(lastTransitionTime2, curStdOffset, curDstSavings, false)) == null)) {
                                        long tt2 = d.getTime();
                                        if (tt2 < nextTransitionTime2) {
                                            nextTransitionTime2 = tt2;
                                            nextRule = this.finalRules[i2];
                                        }
                                    }
                                }
                                nextTransitionTime = nextTransitionTime2;
                            } else {
                                nextTransitionTime = nextTransitionTime2;
                            }
                            if (nextRule == null) {
                                break;
                            }
                            if (this.historicTransitions == null) {
                                this.historicTransitions = new ArrayList();
                            }
                            this.historicTransitions.add(new TimeZoneTransition(nextTransitionTime, curRule, nextRule));
                            lastTransitionTime2 = nextTransitionTime;
                            curRule = nextRule;
                        }
                        lastTransitionTime = lastTransitionTime2;
                    } else {
                        lastTransitionTime = -184303902528000000L;
                    }
                    if (this.finalRules != null) {
                        if (this.historicTransitions == null) {
                            this.historicTransitions = new ArrayList();
                        }
                        Date d0 = this.finalRules[0].getNextStart(lastTransitionTime, curRule.getRawOffset(), curRule.getDSTSavings(), false);
                        Date d1 = this.finalRules[1].getNextStart(lastTransitionTime, curRule.getRawOffset(), curRule.getDSTSavings(), false);
                        if (d1.after(d0)) {
                            this.historicTransitions.add(new TimeZoneTransition(d0.getTime(), curRule, this.finalRules[0]));
                            Date d12 = this.finalRules[1].getNextStart(d0.getTime(), this.finalRules[0].getRawOffset(), this.finalRules[0].getDSTSavings(), false);
                            List<TimeZoneTransition> list2 = this.historicTransitions;
                            long time = d12.getTime();
                            AnnualTimeZoneRule[] annualTimeZoneRuleArr2 = this.finalRules;
                            z = true;
                            list2.add(new TimeZoneTransition(time, annualTimeZoneRuleArr2[0], annualTimeZoneRuleArr2[1]));
                        } else {
                            this.historicTransitions.add(new TimeZoneTransition(d1.getTime(), curRule, this.finalRules[1]));
                            Date d02 = this.finalRules[0].getNextStart(d1.getTime(), this.finalRules[1].getRawOffset(), this.finalRules[1].getDSTSavings(), false);
                            List<TimeZoneTransition> list3 = this.historicTransitions;
                            long time2 = d02.getTime();
                            AnnualTimeZoneRule[] annualTimeZoneRuleArr3 = this.finalRules;
                            z = true;
                            list3.add(new TimeZoneTransition(time2, annualTimeZoneRuleArr3[1], annualTimeZoneRuleArr3[0]));
                        }
                    } else {
                        z = true;
                    }
                }
                this.upToDate = z;
                return;
            }
            throw new IllegalStateException("Incomplete final rules");
        }
    }

    private void getOffset(long time, boolean local, int NonExistingTimeOpt, int DuplicatedTimeOpt, int[] offsets) {
        complete();
        TimeZoneRule rule = null;
        List<TimeZoneTransition> list = this.historicTransitions;
        if (list == null) {
            rule = this.initialRule;
        } else if (time < getTransitionTime(list.get(0), local, NonExistingTimeOpt, DuplicatedTimeOpt)) {
            rule = this.initialRule;
        } else {
            int idx = this.historicTransitions.size() - 1;
            if (time > getTransitionTime(this.historicTransitions.get(idx), local, NonExistingTimeOpt, DuplicatedTimeOpt)) {
                if (this.finalRules != null) {
                    rule = findRuleInFinal(time, local, NonExistingTimeOpt, DuplicatedTimeOpt);
                }
                if (rule == null) {
                    rule = this.historicTransitions.get(idx).getTo();
                }
            } else {
                while (idx >= 0 && time < getTransitionTime(this.historicTransitions.get(idx), local, NonExistingTimeOpt, DuplicatedTimeOpt)) {
                    idx--;
                }
                rule = this.historicTransitions.get(idx).getTo();
            }
        }
        offsets[0] = rule.getRawOffset();
        offsets[1] = rule.getDSTSavings();
    }

    private TimeZoneRule findRuleInFinal(long time, boolean local, int NonExistingTimeOpt, int DuplicatedTimeOpt) {
        AnnualTimeZoneRule[] annualTimeZoneRuleArr = this.finalRules;
        if (annualTimeZoneRuleArr == null || annualTimeZoneRuleArr.length != 2 || annualTimeZoneRuleArr[0] == null || annualTimeZoneRuleArr[1] == null) {
            return null;
        }
        long base = time;
        if (local) {
            base -= (long) getLocalDelta(annualTimeZoneRuleArr[1].getRawOffset(), this.finalRules[1].getDSTSavings(), this.finalRules[0].getRawOffset(), this.finalRules[0].getDSTSavings(), NonExistingTimeOpt, DuplicatedTimeOpt);
        }
        AnnualTimeZoneRule[] annualTimeZoneRuleArr2 = this.finalRules;
        Date start0 = annualTimeZoneRuleArr2[0].getPreviousStart(base, annualTimeZoneRuleArr2[1].getRawOffset(), this.finalRules[1].getDSTSavings(), true);
        long base2 = time;
        if (local) {
            base2 -= (long) getLocalDelta(this.finalRules[0].getRawOffset(), this.finalRules[0].getDSTSavings(), this.finalRules[1].getRawOffset(), this.finalRules[1].getDSTSavings(), NonExistingTimeOpt, DuplicatedTimeOpt);
        }
        AnnualTimeZoneRule[] annualTimeZoneRuleArr3 = this.finalRules;
        Date start1 = annualTimeZoneRuleArr3[1].getPreviousStart(base2, annualTimeZoneRuleArr3[0].getRawOffset(), this.finalRules[0].getDSTSavings(), true);
        if (start0 != null && start1 != null) {
            return start0.after(start1) ? this.finalRules[0] : this.finalRules[1];
        }
        if (start0 != null) {
            return this.finalRules[0];
        }
        if (start1 != null) {
            return this.finalRules[1];
        }
        return null;
    }

    private static long getTransitionTime(TimeZoneTransition tzt, boolean local, int NonExistingTimeOpt, int DuplicatedTimeOpt) {
        long time = tzt.getTime();
        if (local) {
            return time + ((long) getLocalDelta(tzt.getFrom().getRawOffset(), tzt.getFrom().getDSTSavings(), tzt.getTo().getRawOffset(), tzt.getTo().getDSTSavings(), NonExistingTimeOpt, DuplicatedTimeOpt));
        }
        return time;
    }

    private static int getLocalDelta(int rawBefore, int dstBefore, int rawAfter, int dstAfter, int NonExistingTimeOpt, int DuplicatedTimeOpt) {
        int offsetBefore = rawBefore + dstBefore;
        int offsetAfter = rawAfter + dstAfter;
        boolean stdToDst = false;
        boolean dstToStd = dstBefore != 0 && dstAfter == 0;
        if (dstBefore == 0 && dstAfter != 0) {
            stdToDst = true;
        }
        if (offsetAfter - offsetBefore >= 0) {
            if (((NonExistingTimeOpt & 3) == 1 && dstToStd) || ((NonExistingTimeOpt & 3) == 3 && stdToDst)) {
                return offsetBefore;
            }
            if (((NonExistingTimeOpt & 3) != 1 || !stdToDst) && ((NonExistingTimeOpt & 3) != 3 || !dstToStd)) {
                return (NonExistingTimeOpt & 12) == 12 ? offsetBefore : offsetAfter;
            }
            return offsetAfter;
        } else if (((DuplicatedTimeOpt & 3) == 1 && dstToStd) || ((DuplicatedTimeOpt & 3) == 3 && stdToDst)) {
            return offsetAfter;
        } else {
            if (((DuplicatedTimeOpt & 3) != 1 || !stdToDst) && ((DuplicatedTimeOpt & 3) != 3 || !dstToStd)) {
                return (DuplicatedTimeOpt & 12) == 4 ? offsetBefore : offsetAfter;
            }
            return offsetBefore;
        }
    }

    @Override // android.icu.util.Freezable, android.icu.util.TimeZone
    public boolean isFrozen() {
        return this.isFrozen;
    }

    @Override // android.icu.util.Freezable, android.icu.util.TimeZone, android.icu.util.TimeZone
    public TimeZone freeze() {
        complete();
        this.isFrozen = true;
        return this;
    }

    @Override // android.icu.util.Freezable, android.icu.util.TimeZone, android.icu.util.TimeZone
    public TimeZone cloneAsThawed() {
        RuleBasedTimeZone tz = (RuleBasedTimeZone) super.cloneAsThawed();
        List<TimeZoneRule> list = this.historicRules;
        if (list != null) {
            tz.historicRules = new ArrayList(list);
        }
        AnnualTimeZoneRule[] annualTimeZoneRuleArr = this.finalRules;
        if (annualTimeZoneRuleArr != null) {
            tz.finalRules = (AnnualTimeZoneRule[]) annualTimeZoneRuleArr.clone();
        }
        tz.isFrozen = false;
        return tz;
    }
}
