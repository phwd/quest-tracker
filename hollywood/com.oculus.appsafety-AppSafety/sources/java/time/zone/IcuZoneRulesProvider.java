package java.time.zone;

import android.icu.util.AnnualTimeZoneRule;
import android.icu.util.BasicTimeZone;
import android.icu.util.DateTimeRule;
import android.icu.util.InitialTimeZoneRule;
import android.icu.util.TimeZone;
import android.icu.util.TimeZoneRule;
import android.icu.util.TimeZoneTransition;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.zone.ZoneOffsetTransitionRule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import libcore.util.BasicLruCache;

public class IcuZoneRulesProvider extends ZoneRulesProvider {
    private static final int MAX_TRANSITIONS = 10000;
    private static final int SECONDS_IN_DAY = 86400;
    private final BasicLruCache<String, ZoneRules> cache = new ZoneRulesCache(8);

    /* access modifiers changed from: protected */
    @Override // java.time.zone.ZoneRulesProvider
    public Set<String> provideZoneIds() {
        Set<String> zoneIds = new HashSet<>(TimeZone.getAvailableIDs(TimeZone.SystemTimeZoneType.ANY, null, null));
        zoneIds.remove("GMT+0");
        zoneIds.remove("GMT-0");
        return zoneIds;
    }

    /* access modifiers changed from: protected */
    @Override // java.time.zone.ZoneRulesProvider
    public ZoneRules provideRules(String zoneId, boolean forCaching) {
        return this.cache.get(zoneId);
    }

    /* access modifiers changed from: protected */
    @Override // java.time.zone.ZoneRulesProvider
    public NavigableMap<String, ZoneRules> provideVersions(String zoneId) {
        return new TreeMap(Collections.singletonMap(TimeZone.getTZDataVersion(), provideRules(zoneId, false)));
    }

    static ZoneRules generateZoneRules(String zoneId) {
        ZoneOffset baseStandardOffset;
        List<ZoneOffsetTransition> standardOffsetTransitionList;
        boolean z;
        ZoneOffset baseWallOffset;
        AnnualTimeZoneRule last2;
        AnnualTimeZoneRule last22;
        InitialTimeZoneRule initial;
        boolean hadEffect;
        TimeZone timeZone = TimeZone.getFrozenTimeZone(zoneId);
        verify(timeZone instanceof BasicTimeZone, zoneId, "Unexpected time zone class " + ((Object) timeZone.getClass()));
        BasicTimeZone tz = (BasicTimeZone) timeZone;
        TimeZoneRule[] rules = tz.getTimeZoneRules();
        boolean z2 = false;
        InitialTimeZoneRule initial2 = (InitialTimeZoneRule) rules[0];
        ZoneOffset baseStandardOffset2 = millisToOffset(initial2.getRawOffset());
        ZoneOffset baseWallOffset2 = millisToOffset(initial2.getRawOffset() + initial2.getDSTSavings());
        List<ZoneOffsetTransition> standardOffsetTransitionList2 = new ArrayList<>();
        List<ZoneOffsetTransition> transitionList = new ArrayList<>();
        List<ZoneOffsetTransitionRule> lastRules = new ArrayList<>();
        int preLastDstSavings = 0;
        AnnualTimeZoneRule last1 = null;
        AnnualTimeZoneRule last23 = null;
        TimeZoneTransition transition = tz.getNextTransition(Long.MIN_VALUE, false);
        int transitionCount = 1;
        while (true) {
            if (transition == null) {
                baseStandardOffset = baseStandardOffset2;
                standardOffsetTransitionList = standardOffsetTransitionList2;
                z = z2;
                baseWallOffset = baseWallOffset2;
                last2 = last23;
                break;
            }
            TimeZoneRule from = transition.getFrom();
            TimeZoneRule to = transition.getTo();
            boolean hadEffect2 = false;
            if (from.getRawOffset() != to.getRawOffset()) {
                initial = initial2;
                baseStandardOffset = baseStandardOffset2;
                last22 = last23;
                standardOffsetTransitionList2.add(new ZoneOffsetTransition(TimeUnit.MILLISECONDS.toSeconds(transition.getTime()), millisToOffset(from.getRawOffset()), millisToOffset(to.getRawOffset())));
                hadEffect2 = true;
            } else {
                initial = initial2;
                baseStandardOffset = baseStandardOffset2;
                last22 = last23;
            }
            int fromTotalOffset = from.getRawOffset() + from.getDSTSavings();
            int toTotalOffset = to.getRawOffset() + to.getDSTSavings();
            if (fromTotalOffset != toTotalOffset) {
                baseWallOffset = baseWallOffset2;
                standardOffsetTransitionList = standardOffsetTransitionList2;
                transitionList.add(new ZoneOffsetTransition(TimeUnit.MILLISECONDS.toSeconds(transition.getTime()), millisToOffset(fromTotalOffset), millisToOffset(toTotalOffset)));
                hadEffect = true;
            } else {
                baseWallOffset = baseWallOffset2;
                standardOffsetTransitionList = standardOffsetTransitionList2;
                hadEffect = hadEffect2;
            }
            verify(hadEffect, zoneId, "Transition changed neither total nor raw offset.");
            if (!(to instanceof AnnualTimeZoneRule)) {
                verify(last1 == null, zoneId, "Unexpected rule after AnnualTimeZoneRule.");
            } else if (last1 == null) {
                int preLastDstSavings2 = from.getDSTSavings();
                AnnualTimeZoneRule last12 = (AnnualTimeZoneRule) to;
                verify(last12.getEndYear() == Integer.MAX_VALUE, zoneId, "AnnualTimeZoneRule is not permanent.");
                preLastDstSavings = preLastDstSavings2;
                last1 = last12;
            } else {
                last2 = (AnnualTimeZoneRule) to;
                verify(last2.getEndYear() == Integer.MAX_VALUE, zoneId, "AnnualTimeZoneRule is not permanent.");
                verify(tz.getNextTransition(transition.getTime(), false).getTo() == last1, zoneId, "Unexpected rule after 2 AnnualTimeZoneRules.");
                z = false;
            }
            verify(transitionCount <= 10000, zoneId, "More than 10000 transitions.");
            transition = tz.getNextTransition(transition.getTime(), false);
            transitionCount++;
            z2 = false;
            baseWallOffset2 = baseWallOffset;
            timeZone = timeZone;
            rules = rules;
            initial2 = initial;
            baseStandardOffset2 = baseStandardOffset;
            standardOffsetTransitionList2 = standardOffsetTransitionList;
            last23 = last22;
        }
        if (last1 != null) {
            if (last2 != null) {
                z = true;
            }
            verify(z, zoneId, "Only one AnnualTimeZoneRule.");
            lastRules.add(toZoneOffsetTransitionRule(last1, preLastDstSavings));
            lastRules.add(toZoneOffsetTransitionRule(last2, last1.getDSTSavings()));
        }
        return ZoneRules.of(baseStandardOffset, baseWallOffset, standardOffsetTransitionList, transitionList, lastRules);
    }

    private static void verify(boolean check, String zoneId, String message) {
        if (!check) {
            throw new ZoneRulesException(String.format("Failed verification of zone %s: %s", zoneId, message));
        }
    }

    private static ZoneOffsetTransitionRule toZoneOffsetTransitionRule(AnnualTimeZoneRule rule, int dstSavingMillisBefore) {
        int dayOfMonthIndicator;
        boolean timeEndOfDay;
        LocalTime time;
        ZoneOffsetTransitionRule.TimeDefinition timeDefinition;
        DateTimeRule dateTimeRule = rule.getRule();
        Month month = Month.JANUARY.plus((long) dateTimeRule.getRuleMonth());
        DayOfWeek dayOfWeek = DayOfWeek.SATURDAY.plus((long) dateTimeRule.getRuleDayOfWeek());
        int dateRuleType = dateTimeRule.getDateRuleType();
        if (dateRuleType == 0) {
            dayOfMonthIndicator = dateTimeRule.getRuleDayOfMonth();
            dayOfWeek = null;
        } else if (dateRuleType == 1) {
            throw new ZoneRulesException("Date rule type DOW is unsupported");
        } else if (dateRuleType == 2) {
            dayOfMonthIndicator = dateTimeRule.getRuleDayOfMonth();
        } else if (dateRuleType == 3) {
            dayOfMonthIndicator = ((-month.maxLength()) + dateTimeRule.getRuleDayOfMonth()) - 1;
        } else {
            throw new ZoneRulesException("Unexpected date rule type: " + dateTimeRule.getDateRuleType());
        }
        int secondOfDay = (int) TimeUnit.MILLISECONDS.toSeconds((long) dateTimeRule.getRuleMillisInDay());
        if (secondOfDay == SECONDS_IN_DAY) {
            time = LocalTime.MIDNIGHT;
            timeEndOfDay = true;
        } else {
            time = LocalTime.ofSecondOfDay((long) secondOfDay);
            timeEndOfDay = false;
        }
        int timeRuleType = dateTimeRule.getTimeRuleType();
        if (timeRuleType == 0) {
            timeDefinition = ZoneOffsetTransitionRule.TimeDefinition.WALL;
        } else if (timeRuleType == 1) {
            timeDefinition = ZoneOffsetTransitionRule.TimeDefinition.STANDARD;
        } else if (timeRuleType == 2) {
            timeDefinition = ZoneOffsetTransitionRule.TimeDefinition.UTC;
        } else {
            throw new ZoneRulesException("Unexpected time rule type " + dateTimeRule.getTimeRuleType());
        }
        return ZoneOffsetTransitionRule.of(month, dayOfMonthIndicator, dayOfWeek, time, timeEndOfDay, timeDefinition, millisToOffset(rule.getRawOffset()), millisToOffset(rule.getRawOffset() + dstSavingMillisBefore), millisToOffset(rule.getRawOffset() + rule.getDSTSavings()));
    }

    private static ZoneOffset millisToOffset(int offset) {
        return ZoneOffset.ofTotalSeconds((int) TimeUnit.MILLISECONDS.toSeconds((long) offset));
    }

    private static class ZoneRulesCache extends BasicLruCache<String, ZoneRules> {
        ZoneRulesCache(int maxSize) {
            super(maxSize);
        }

        /* access modifiers changed from: protected */
        public ZoneRules create(String zoneId) {
            String canonicalId = TimeZone.getCanonicalID(zoneId);
            if (!canonicalId.equals(zoneId)) {
                return (ZoneRules) get(canonicalId);
            }
            return IcuZoneRulesProvider.generateZoneRules(zoneId);
        }
    }
}
