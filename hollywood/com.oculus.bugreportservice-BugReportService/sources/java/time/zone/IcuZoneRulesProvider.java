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
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import libcore.util.BasicLruCache;

public class IcuZoneRulesProvider extends ZoneRulesProvider {
    private final BasicLruCache cache = new ZoneRulesCache(8);

    /* access modifiers changed from: protected */
    @Override // java.time.zone.ZoneRulesProvider
    public Set provideZoneIds() {
        HashSet hashSet = new HashSet(TimeZone.getAvailableIDs(TimeZone.SystemTimeZoneType.ANY, null, null));
        hashSet.remove("GMT+0");
        hashSet.remove("GMT-0");
        return hashSet;
    }

    /* access modifiers changed from: protected */
    @Override // java.time.zone.ZoneRulesProvider
    public ZoneRules provideRules(String str, boolean z) {
        return (ZoneRules) this.cache.get(str);
    }

    static ZoneRules generateZoneRules(String str) {
        ZoneOffset zoneOffset;
        ZoneOffset zoneOffset2;
        int i;
        boolean z;
        AnnualTimeZoneRule annualTimeZoneRule;
        boolean z2;
        int i2;
        TimeZone frozenTimeZone = TimeZone.getFrozenTimeZone(str);
        verify(frozenTimeZone instanceof BasicTimeZone, str, "Unexpected time zone class " + frozenTimeZone.getClass());
        BasicTimeZone basicTimeZone = (BasicTimeZone) frozenTimeZone;
        boolean z3 = false;
        InitialTimeZoneRule initialTimeZoneRule = (InitialTimeZoneRule) basicTimeZone.getTimeZoneRules()[0];
        ZoneOffset millisToOffset = millisToOffset(initialTimeZoneRule.getRawOffset());
        ZoneOffset millisToOffset2 = millisToOffset(initialTimeZoneRule.getRawOffset() + initialTimeZoneRule.getDSTSavings());
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        TimeZoneTransition nextTransition = basicTimeZone.getNextTransition(Long.MIN_VALUE, false);
        int i3 = 0;
        AnnualTimeZoneRule annualTimeZoneRule2 = null;
        int i4 = 1;
        while (true) {
            if (nextTransition == null) {
                zoneOffset = millisToOffset2;
                zoneOffset2 = millisToOffset;
                i = i3;
                z = z3;
                annualTimeZoneRule = null;
                break;
            }
            TimeZoneRule from = nextTransition.getFrom();
            TimeZoneRule to = nextTransition.getTo();
            if (from.getRawOffset() != to.getRawOffset()) {
                zoneOffset2 = millisToOffset;
                zoneOffset = millisToOffset2;
                arrayList.add(new ZoneOffsetTransition(TimeUnit.MILLISECONDS.toSeconds(nextTransition.getTime()), millisToOffset(from.getRawOffset()), millisToOffset(to.getRawOffset())));
                z2 = true;
            } else {
                zoneOffset = millisToOffset2;
                zoneOffset2 = millisToOffset;
                z2 = false;
            }
            int rawOffset = from.getRawOffset() + from.getDSTSavings();
            int rawOffset2 = to.getRawOffset() + to.getDSTSavings();
            if (rawOffset != rawOffset2) {
                i2 = i4;
                i = i3;
                arrayList2.add(new ZoneOffsetTransition(TimeUnit.MILLISECONDS.toSeconds(nextTransition.getTime()), millisToOffset(rawOffset), millisToOffset(rawOffset2)));
                z2 = true;
            } else {
                i2 = i4;
                i = i3;
            }
            verify(z2, str, "Transition changed neither total nor raw offset.");
            if (!(to instanceof AnnualTimeZoneRule)) {
                verify(annualTimeZoneRule2 == null, str, "Unexpected rule after AnnualTimeZoneRule.");
                i3 = i;
            } else if (annualTimeZoneRule2 == null) {
                int dSTSavings = from.getDSTSavings();
                AnnualTimeZoneRule annualTimeZoneRule3 = (AnnualTimeZoneRule) to;
                verify(annualTimeZoneRule3.getEndYear() == Integer.MAX_VALUE, str, "AnnualTimeZoneRule is not permanent.");
                i3 = dSTSavings;
                annualTimeZoneRule2 = annualTimeZoneRule3;
            } else {
                annualTimeZoneRule = (AnnualTimeZoneRule) to;
                verify(annualTimeZoneRule.getEndYear() == Integer.MAX_VALUE, str, "AnnualTimeZoneRule is not permanent.");
                verify(basicTimeZone.getNextTransition(nextTransition.getTime(), false).getTo() == annualTimeZoneRule2, str, "Unexpected rule after 2 AnnualTimeZoneRules.");
                z = false;
            }
            verify(i2 <= 10000, str, "More than 10000 transitions.");
            nextTransition = basicTimeZone.getNextTransition(nextTransition.getTime(), false);
            i4 = i2 + 1;
            z3 = false;
            millisToOffset = zoneOffset2;
            millisToOffset2 = zoneOffset;
        }
        if (annualTimeZoneRule2 != null) {
            if (annualTimeZoneRule != null) {
                z = true;
            }
            verify(z, str, "Only one AnnualTimeZoneRule.");
            arrayList3.add(toZoneOffsetTransitionRule(annualTimeZoneRule2, i));
            arrayList3.add(toZoneOffsetTransitionRule(annualTimeZoneRule, annualTimeZoneRule2.getDSTSavings()));
        }
        return ZoneRules.of(zoneOffset2, zoneOffset, arrayList, arrayList2, arrayList3);
    }

    private static void verify(boolean z, String str, String str2) {
        if (!z) {
            throw new ZoneRulesException(String.format("Failed verification of zone %s: %s", str, str2));
        }
    }

    private static ZoneOffsetTransitionRule toZoneOffsetTransitionRule(AnnualTimeZoneRule annualTimeZoneRule, int i) {
        DayOfWeek dayOfWeek;
        int i2;
        boolean z;
        LocalTime localTime;
        ZoneOffsetTransitionRule.TimeDefinition timeDefinition;
        DateTimeRule rule = annualTimeZoneRule.getRule();
        Month plus = Month.JANUARY.plus((long) rule.getRuleMonth());
        DayOfWeek plus2 = DayOfWeek.SATURDAY.plus((long) rule.getRuleDayOfWeek());
        int dateRuleType = rule.getDateRuleType();
        if (dateRuleType == 0) {
            dayOfWeek = null;
            i2 = rule.getRuleDayOfMonth();
        } else if (dateRuleType != 1) {
            if (dateRuleType == 2) {
                i2 = rule.getRuleDayOfMonth();
            } else if (dateRuleType == 3) {
                i2 = ((-plus.maxLength()) + rule.getRuleDayOfMonth()) - 1;
            } else {
                throw new ZoneRulesException("Unexpected date rule type: " + rule.getDateRuleType());
            }
            dayOfWeek = plus2;
        } else {
            throw new ZoneRulesException("Date rule type DOW is unsupported");
        }
        int seconds = (int) TimeUnit.MILLISECONDS.toSeconds((long) rule.getRuleMillisInDay());
        if (seconds == 86400) {
            localTime = LocalTime.MIDNIGHT;
            z = true;
        } else {
            z = false;
            localTime = LocalTime.ofSecondOfDay((long) seconds);
        }
        int timeRuleType = rule.getTimeRuleType();
        if (timeRuleType == 0) {
            timeDefinition = ZoneOffsetTransitionRule.TimeDefinition.WALL;
        } else if (timeRuleType == 1) {
            timeDefinition = ZoneOffsetTransitionRule.TimeDefinition.STANDARD;
        } else if (timeRuleType == 2) {
            timeDefinition = ZoneOffsetTransitionRule.TimeDefinition.UTC;
        } else {
            throw new ZoneRulesException("Unexpected time rule type " + rule.getTimeRuleType());
        }
        return ZoneOffsetTransitionRule.of(plus, i2, dayOfWeek, localTime, z, timeDefinition, millisToOffset(annualTimeZoneRule.getRawOffset()), millisToOffset(annualTimeZoneRule.getRawOffset() + i), millisToOffset(annualTimeZoneRule.getRawOffset() + annualTimeZoneRule.getDSTSavings()));
    }

    private static ZoneOffset millisToOffset(int i) {
        return ZoneOffset.ofTotalSeconds((int) TimeUnit.MILLISECONDS.toSeconds((long) i));
    }

    private static class ZoneRulesCache extends BasicLruCache {
        ZoneRulesCache(int i) {
            super(i);
        }

        /* access modifiers changed from: protected */
        public ZoneRules create(String str) {
            String canonicalID = TimeZone.getCanonicalID(str);
            if (!canonicalID.equals(str)) {
                return (ZoneRules) get(canonicalID);
            }
            return IcuZoneRulesProvider.generateZoneRules(str);
        }
    }
}
