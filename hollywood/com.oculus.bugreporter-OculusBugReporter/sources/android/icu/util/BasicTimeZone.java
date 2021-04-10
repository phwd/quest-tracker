package android.icu.util;

import android.icu.impl.Grego;
import java.util.BitSet;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public abstract class BasicTimeZone extends TimeZone {
    @Deprecated
    protected static final int FORMER_LATTER_MASK = 12;
    @Deprecated
    public static final int LOCAL_DST = 3;
    @Deprecated
    public static final int LOCAL_FORMER = 4;
    @Deprecated
    public static final int LOCAL_LATTER = 12;
    @Deprecated
    public static final int LOCAL_STD = 1;
    private static final long MILLIS_PER_YEAR = 31536000000L;
    @Deprecated
    protected static final int STD_DST_MASK = 3;
    private static final long serialVersionUID = -3204278532246180932L;

    public abstract TimeZoneTransition getNextTransition(long j, boolean z);

    public abstract TimeZoneTransition getPreviousTransition(long j, boolean z);

    public abstract TimeZoneRule[] getTimeZoneRules();

    public boolean hasEquivalentTransitions(TimeZone tz, long start, long end) {
        return hasEquivalentTransitions(tz, start, end, false);
    }

    public boolean hasEquivalentTransitions(TimeZone tz, long start, long end, boolean ignoreDstAmount) {
        if (this == tz) {
            return true;
        }
        if (!(tz instanceof BasicTimeZone)) {
            return false;
        }
        int[] offsets1 = new int[2];
        int[] offsets2 = new int[2];
        getOffset(start, false, offsets1);
        tz.getOffset(start, false, offsets2);
        if (ignoreDstAmount) {
            if (offsets1[0] + offsets1[1] != offsets2[0] + offsets2[1] || ((offsets1[1] != 0 && offsets2[1] == 0) || (offsets1[1] == 0 && offsets2[1] != 0))) {
                return false;
            }
        } else if (!(offsets1[0] == offsets2[0] && offsets1[1] == offsets2[1])) {
            return false;
        }
        long time = start;
        while (true) {
            TimeZoneTransition tr1 = getNextTransition(time, false);
            TimeZoneTransition tr2 = ((BasicTimeZone) tz).getNextTransition(time, false);
            if (ignoreDstAmount) {
                while (tr1 != null && tr1.getTime() <= end && tr1.getFrom().getRawOffset() + tr1.getFrom().getDSTSavings() == tr1.getTo().getRawOffset() + tr1.getTo().getDSTSavings() && tr1.getFrom().getDSTSavings() != 0 && tr1.getTo().getDSTSavings() != 0) {
                    tr1 = getNextTransition(tr1.getTime(), false);
                }
                while (tr2 != null && tr2.getTime() <= end && tr2.getFrom().getRawOffset() + tr2.getFrom().getDSTSavings() == tr2.getTo().getRawOffset() + tr2.getTo().getDSTSavings() && tr2.getFrom().getDSTSavings() != 0 && tr2.getTo().getDSTSavings() != 0) {
                    tr2 = ((BasicTimeZone) tz).getNextTransition(tr2.getTime(), false);
                }
            }
            boolean inRange1 = false;
            boolean inRange2 = false;
            if (tr1 != null && tr1.getTime() <= end) {
                inRange1 = true;
            }
            if (tr2 != null && tr2.getTime() <= end) {
                inRange2 = true;
            }
            if (!inRange1 && !inRange2) {
                return true;
            }
            if (!inRange1 || !inRange2 || tr1.getTime() != tr2.getTime()) {
                return false;
            }
            if (ignoreDstAmount) {
                if (tr1.getTo().getRawOffset() + tr1.getTo().getDSTSavings() != tr2.getTo().getRawOffset() + tr2.getTo().getDSTSavings() || ((tr1.getTo().getDSTSavings() != 0 && tr2.getTo().getDSTSavings() == 0) || (tr1.getTo().getDSTSavings() == 0 && tr2.getTo().getDSTSavings() != 0))) {
                    return false;
                }
            } else if (tr1.getTo().getRawOffset() != tr2.getTo().getRawOffset() || tr1.getTo().getDSTSavings() != tr2.getTo().getDSTSavings()) {
                return false;
            }
            time = tr1.getTime();
        }
        return false;
    }

    public TimeZoneRule[] getTimeZoneRules(long start) {
        long time;
        boolean z;
        AnnualTimeZoneRule ar;
        TimeZoneTransition tzt;
        boolean bFinalStd;
        BasicTimeZone basicTimeZone = this;
        TimeZoneRule[] all = getTimeZoneRules();
        TimeZoneTransition tzt2 = basicTimeZone.getPreviousTransition(start, true);
        if (tzt2 == null) {
            return all;
        }
        BitSet isProcessed = new BitSet(all.length);
        List<TimeZoneRule> filteredRules = new LinkedList<>();
        TimeZoneRule initial = new InitialTimeZoneRule(tzt2.getTo().getName(), tzt2.getTo().getRawOffset(), tzt2.getTo().getDSTSavings());
        filteredRules.add(initial);
        boolean z2 = false;
        isProcessed.set(0);
        for (int i = 1; i < all.length; i++) {
            if (all[i].getNextStart(start, initial.getRawOffset(), initial.getDSTSavings(), false) == null) {
                isProcessed.set(i);
            }
        }
        long time2 = start;
        boolean bFinalStd2 = false;
        boolean bFinalDst = false;
        while (true) {
            if (bFinalStd2 && bFinalDst) {
                break;
            }
            TimeZoneTransition tzt3 = basicTimeZone.getNextTransition(time2, z2);
            if (tzt3 == null) {
                break;
            }
            time2 = tzt3.getTime();
            TimeZoneRule toRule = tzt3.getTo();
            int ruleIdx = 1;
            while (ruleIdx < all.length && !all[ruleIdx].equals(toRule)) {
                ruleIdx++;
            }
            if (ruleIdx >= all.length) {
                throw new IllegalStateException("The rule was not found");
            } else if (!isProcessed.get(ruleIdx)) {
                if (toRule instanceof TimeArrayTimeZoneRule) {
                    TimeArrayTimeZoneRule tar = (TimeArrayTimeZoneRule) toRule;
                    time = time2;
                    long t = start;
                    while (true) {
                        tzt = basicTimeZone.getNextTransition(t, z2);
                        if (tzt != null && !tzt.getTo().equals(tar)) {
                            t = tzt.getTime();
                            z2 = false;
                            basicTimeZone = this;
                        }
                    }
                    if (tzt == null) {
                        bFinalStd = bFinalStd2;
                    } else if (tar.getFirstStart(tzt.getFrom().getRawOffset(), tzt.getFrom().getDSTSavings()).getTime() > start) {
                        filteredRules.add(tar);
                        bFinalStd = bFinalStd2;
                    } else {
                        long[] times = tar.getStartTimes();
                        int timeType = tar.getTimeType();
                        int idx = 0;
                        while (true) {
                            if (idx >= times.length) {
                                bFinalStd = bFinalStd2;
                                break;
                            }
                            long t2 = times[idx];
                            if (timeType == 1) {
                                bFinalStd = bFinalStd2;
                                t2 -= (long) tzt.getFrom().getRawOffset();
                            } else {
                                bFinalStd = bFinalStd2;
                            }
                            if (timeType == 0) {
                                t2 -= (long) tzt.getFrom().getDSTSavings();
                            }
                            if (t2 > start) {
                                break;
                            }
                            idx++;
                            bFinalStd2 = bFinalStd;
                        }
                        int asize = times.length - idx;
                        if (asize > 0) {
                            long[] newtimes = new long[asize];
                            System.arraycopy((Object) times, idx, (Object) newtimes, 0, asize);
                            filteredRules.add(new TimeArrayTimeZoneRule(tar.getName(), tar.getRawOffset(), tar.getDSTSavings(), newtimes, tar.getTimeType()));
                        }
                    }
                    bFinalStd2 = bFinalStd;
                    z = false;
                } else {
                    time = time2;
                    if (toRule instanceof AnnualTimeZoneRule) {
                        AnnualTimeZoneRule ar2 = (AnnualTimeZoneRule) toRule;
                        if (ar2.getFirstStart(tzt3.getFrom().getRawOffset(), tzt3.getFrom().getDSTSavings()).getTime() == tzt3.getTime()) {
                            filteredRules.add(ar2);
                            ar = ar2;
                            z = false;
                        } else {
                            int[] dfields = new int[6];
                            ar = ar2;
                            Grego.timeToFields(tzt3.getTime(), dfields);
                            z = false;
                            filteredRules.add(new AnnualTimeZoneRule(ar.getName(), ar.getRawOffset(), ar.getDSTSavings(), ar.getRule(), dfields[0], ar.getEndYear()));
                        }
                        if (ar.getEndYear() == Integer.MAX_VALUE) {
                            if (ar.getDSTSavings() == 0) {
                                bFinalStd2 = true;
                            } else {
                                bFinalDst = true;
                                bFinalStd2 = bFinalStd2;
                            }
                        }
                    } else {
                        z = false;
                    }
                    bFinalStd2 = bFinalStd2;
                }
                isProcessed.set(ruleIdx);
                basicTimeZone = this;
                z2 = z;
                time2 = time;
            }
        }
        return (TimeZoneRule[]) filteredRules.toArray(new TimeZoneRule[filteredRules.size()]);
    }

    /* JADX INFO: Multiple debug info for r9v10 'initialRaw'  int: [D('initialRaw' int), D('initialRule' android.icu.util.TimeZoneRule)] */
    public TimeZoneRule[] getSimpleTimeZoneRulesNear(long date) {
        TimeZoneRule initialRule;
        int i;
        String initialName;
        int initialRaw;
        int initialDst;
        long nextTransitionTime;
        int initialDst2;
        TimeZoneTransition tr;
        AnnualTimeZoneRule[] annualRules;
        int initialDst3;
        AnnualTimeZoneRule[] annualRules2 = null;
        TimeZoneTransition tr2 = getNextTransition(date, false);
        if (tr2 != null) {
            String initialName2 = tr2.getFrom().getName();
            int initialRaw2 = tr2.getFrom().getRawOffset();
            int initialDst4 = tr2.getFrom().getDSTSavings();
            long nextTransitionTime2 = tr2.getTime();
            if (((tr2.getFrom().getDSTSavings() != 0 || tr2.getTo().getDSTSavings() == 0) && (tr2.getFrom().getDSTSavings() == 0 || tr2.getTo().getDSTSavings() != 0)) || date + MILLIS_PER_YEAR <= nextTransitionTime2) {
                initialRaw = initialRaw2;
                initialName = initialName2;
                initialDst = initialDst4;
            } else {
                AnnualTimeZoneRule[] annualRules3 = new AnnualTimeZoneRule[2];
                int[] dtfields = Grego.timeToFields(((long) tr2.getFrom().getRawOffset()) + nextTransitionTime2 + ((long) tr2.getFrom().getDSTSavings()), null);
                annualRules3[0] = new AnnualTimeZoneRule(tr2.getTo().getName(), initialRaw2, tr2.getTo().getDSTSavings(), new DateTimeRule(dtfields[1], Grego.getDayOfWeekInMonth(dtfields[0], dtfields[1], dtfields[2]), dtfields[3], dtfields[5], 0), dtfields[0], Integer.MAX_VALUE);
                if (tr2.getTo().getRawOffset() == initialRaw2) {
                    tr = getNextTransition(nextTransitionTime2, false);
                    if (tr != null) {
                        if (tr.getFrom().getDSTSavings() != 0 || tr.getTo().getDSTSavings() == 0) {
                            if (tr.getFrom().getDSTSavings() == 0) {
                                annualRules = annualRules3;
                                nextTransitionTime = nextTransitionTime2;
                                initialDst2 = initialDst4;
                                initialRaw = initialRaw2;
                            } else if (tr.getTo().getDSTSavings() != 0) {
                                annualRules = annualRules3;
                                nextTransitionTime = nextTransitionTime2;
                                initialDst2 = initialDst4;
                                initialRaw = initialRaw2;
                            }
                        }
                        if (nextTransitionTime2 + MILLIS_PER_YEAR > tr.getTime()) {
                            annualRules = annualRules3;
                            dtfields = Grego.timeToFields(((long) tr.getFrom().getDSTSavings()) + tr.getTime() + ((long) tr.getFrom().getRawOffset()), dtfields);
                            AnnualTimeZoneRule secondRule = new AnnualTimeZoneRule(tr.getTo().getName(), tr.getTo().getRawOffset(), tr.getTo().getDSTSavings(), new DateTimeRule(dtfields[1], Grego.getDayOfWeekInMonth(dtfields[0], dtfields[1], dtfields[2]), dtfields[3], dtfields[5], 0), dtfields[0] - 1, Integer.MAX_VALUE);
                            nextTransitionTime = nextTransitionTime2;
                            initialDst2 = initialDst4;
                            initialRaw = initialRaw2;
                            Date d = secondRule.getPreviousStart(date, tr.getFrom().getRawOffset(), tr.getFrom().getDSTSavings(), true);
                            if (d != null && d.getTime() <= date && initialRaw == tr.getTo().getRawOffset() && initialDst2 == tr.getTo().getDSTSavings()) {
                                annualRules[1] = secondRule;
                            }
                        } else {
                            annualRules = annualRules3;
                            nextTransitionTime = nextTransitionTime2;
                            initialDst2 = initialDst4;
                            initialRaw = initialRaw2;
                        }
                    } else {
                        annualRules = annualRules3;
                        nextTransitionTime = nextTransitionTime2;
                        initialDst2 = initialDst4;
                        initialRaw = initialRaw2;
                    }
                } else {
                    annualRules = annualRules3;
                    nextTransitionTime = nextTransitionTime2;
                    initialDst2 = initialDst4;
                    initialRaw = initialRaw2;
                    tr = tr2;
                }
                if (annualRules[1] == null) {
                    TimeZoneTransition tr3 = getPreviousTransition(date, true);
                    if (tr3 != null) {
                        if (tr3.getFrom().getDSTSavings() != 0 || tr3.getTo().getDSTSavings() == 0) {
                            if (tr3.getFrom().getDSTSavings() == 0) {
                                initialDst3 = initialDst2;
                            } else if (tr3.getTo().getDSTSavings() != 0) {
                                initialDst3 = initialDst2;
                            }
                        }
                        int[] dtfields2 = Grego.timeToFields(tr3.getTime() + ((long) tr3.getFrom().getRawOffset()) + ((long) tr3.getFrom().getDSTSavings()), dtfields);
                        initialDst3 = initialDst2;
                        AnnualTimeZoneRule secondRule2 = new AnnualTimeZoneRule(tr3.getTo().getName(), initialRaw, initialDst3, new DateTimeRule(dtfields2[1], Grego.getDayOfWeekInMonth(dtfields2[0], dtfields2[1], dtfields2[2]), dtfields2[3], dtfields2[5], 0), annualRules[0].getStartYear() - 1, Integer.MAX_VALUE);
                        if (secondRule2.getNextStart(date, tr3.getFrom().getRawOffset(), tr3.getFrom().getDSTSavings(), false).getTime() > nextTransitionTime) {
                            annualRules[1] = secondRule2;
                        }
                    } else {
                        initialDst3 = initialDst2;
                    }
                } else {
                    initialDst3 = initialDst2;
                }
                if (annualRules[1] == null) {
                    annualRules2 = null;
                    initialName = initialName2;
                    initialDst = initialDst3;
                } else {
                    initialName = annualRules[0].getName();
                    int initialRaw3 = annualRules[0].getRawOffset();
                    initialDst = annualRules[0].getDSTSavings();
                    initialRaw = initialRaw3;
                    annualRules2 = annualRules;
                }
            }
            initialRule = new InitialTimeZoneRule(initialName, initialRaw, initialDst);
            i = 1;
        } else {
            TimeZoneTransition tr4 = getPreviousTransition(date, true);
            if (tr4 != null) {
                initialRule = new InitialTimeZoneRule(tr4.getTo().getName(), tr4.getTo().getRawOffset(), tr4.getTo().getDSTSavings());
                i = 1;
            } else {
                int[] offsets = new int[2];
                getOffset(date, false, offsets);
                i = 1;
                initialRule = new InitialTimeZoneRule(getID(), offsets[0], offsets[1]);
            }
        }
        if (annualRules2 == null) {
            TimeZoneRule[] result = new TimeZoneRule[i];
            result[0] = initialRule;
            return result;
        }
        TimeZoneRule[] result2 = new TimeZoneRule[3];
        result2[0] = initialRule;
        result2[i] = annualRules2[0];
        result2[2] = annualRules2[i];
        return result2;
    }

    @Deprecated
    public void getOffsetFromLocal(long date, int nonExistingTimeOpt, int duplicatedTimeOpt, int[] offsets) {
        throw new IllegalStateException("Not implemented");
    }

    protected BasicTimeZone() {
    }

    @Deprecated
    protected BasicTimeZone(String ID) {
        super(ID);
    }
}
