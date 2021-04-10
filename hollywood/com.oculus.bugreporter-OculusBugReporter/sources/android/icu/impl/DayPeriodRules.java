package android.icu.impl;

import android.icu.impl.UResource;
import android.icu.util.ICUException;
import android.icu.util.ULocale;
import java.util.HashMap;
import java.util.Map;

public final class DayPeriodRules {
    private static final DayPeriodRulesData DATA = loadData();
    private DayPeriod[] dayPeriodForHour;
    private boolean hasMidnight;
    private boolean hasNoon;

    public enum DayPeriod {
        MIDNIGHT,
        NOON,
        MORNING1,
        AFTERNOON1,
        EVENING1,
        NIGHT1,
        MORNING2,
        AFTERNOON2,
        EVENING2,
        NIGHT2,
        AM,
        PM;
        
        public static DayPeriod[] VALUES = values();

        /* access modifiers changed from: private */
        public static DayPeriod fromStringOrNull(CharSequence str) {
            if ("midnight".contentEquals(str)) {
                return MIDNIGHT;
            }
            if ("noon".contentEquals(str)) {
                return NOON;
            }
            if ("morning1".contentEquals(str)) {
                return MORNING1;
            }
            if ("afternoon1".contentEquals(str)) {
                return AFTERNOON1;
            }
            if ("evening1".contentEquals(str)) {
                return EVENING1;
            }
            if ("night1".contentEquals(str)) {
                return NIGHT1;
            }
            if ("morning2".contentEquals(str)) {
                return MORNING2;
            }
            if ("afternoon2".contentEquals(str)) {
                return AFTERNOON2;
            }
            if ("evening2".contentEquals(str)) {
                return EVENING2;
            }
            if ("night2".contentEquals(str)) {
                return NIGHT2;
            }
            if ("am".contentEquals(str)) {
                return AM;
            }
            if ("pm".contentEquals(str)) {
                return PM;
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public enum CutoffType {
        BEFORE,
        AFTER,
        FROM,
        AT;

        /* access modifiers changed from: private */
        public static CutoffType fromStringOrNull(CharSequence str) {
            if ("from".contentEquals(str)) {
                return FROM;
            }
            if ("before".contentEquals(str)) {
                return BEFORE;
            }
            if ("after".contentEquals(str)) {
                return AFTER;
            }
            if ("at".contentEquals(str)) {
                return AT;
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static final class DayPeriodRulesData {
        Map<String, Integer> localesToRuleSetNumMap;
        int maxRuleSetNum;
        DayPeriodRules[] rules;

        private DayPeriodRulesData() {
            this.localesToRuleSetNumMap = new HashMap();
            this.maxRuleSetNum = -1;
        }
    }

    /* access modifiers changed from: private */
    public static final class DayPeriodRulesDataSink extends UResource.Sink {
        private CutoffType cutoffType;
        private int[] cutoffs;
        private DayPeriodRulesData data;
        private DayPeriod period;
        private int ruleSetNum;

        private DayPeriodRulesDataSink(DayPeriodRulesData data2) {
            this.cutoffs = new int[25];
            this.data = data2;
        }

        @Override // android.icu.impl.UResource.Sink
        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table dayPeriodData = value.getTable();
            for (int i = 0; dayPeriodData.getKeyAndValue(i, key, value); i++) {
                if (key.contentEquals("locales")) {
                    UResource.Table locales = value.getTable();
                    for (int j = 0; locales.getKeyAndValue(j, key, value); j++) {
                        this.data.localesToRuleSetNumMap.put(key.toString(), Integer.valueOf(DayPeriodRules.parseSetNum(value.getString())));
                    }
                } else if (key.contentEquals("rules")) {
                    processRules(value.getTable(), key, value);
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:25:0x008e, code lost:
            r2 = r11.data.rules[r11.ruleSetNum].dayPeriodForHour;
            r3 = r2.length;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x009b, code lost:
            if (r4 >= r3) goto L_0x00ac;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x009f, code lost:
            if (r2[r4] == null) goto L_0x00a4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a1, code lost:
            r4 = r4 + 1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:31:0x00ab, code lost:
            throw new android.icu.util.ICUException("Rules in data don't cover all 24 hours (they should).");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x00ac, code lost:
            r0 = r0 + 1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void processRules(android.icu.impl.UResource.Table r12, android.icu.impl.UResource.Key r13, android.icu.impl.UResource.Value r14) {
            /*
            // Method dump skipped, instructions count: 177
            */
            throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.DayPeriodRules.DayPeriodRulesDataSink.processRules(android.icu.impl.UResource$Table, android.icu.impl.UResource$Key, android.icu.impl.UResource$Value):void");
        }

        private void addCutoff(CutoffType type, String hourStr) {
            if (type != null) {
                int hour = parseHour(hourStr);
                int[] iArr = this.cutoffs;
                iArr[hour] = iArr[hour] | (1 << type.ordinal());
                return;
            }
            throw new ICUException("Cutoff type not recognized.");
        }

        private void setDayPeriodForHoursFromCutoffs() {
            DayPeriodRules rule = this.data.rules[this.ruleSetNum];
            for (int startHour = 0; startHour <= 24; startHour++) {
                if ((this.cutoffs[startHour] & (1 << CutoffType.AT.ordinal())) > 0) {
                    if (startHour == 0 && this.period == DayPeriod.MIDNIGHT) {
                        rule.hasMidnight = true;
                    } else if (startHour == 12 && this.period == DayPeriod.NOON) {
                        rule.hasNoon = true;
                    } else {
                        throw new ICUException("AT cutoff must only be set for 0:00 or 12:00.");
                    }
                }
                if ((this.cutoffs[startHour] & (1 << CutoffType.FROM.ordinal())) > 0 || (this.cutoffs[startHour] & (1 << CutoffType.AFTER.ordinal())) > 0) {
                    int hour = startHour + 1;
                    while (hour != startHour) {
                        if (hour == 25) {
                            hour = 0;
                        }
                        if ((this.cutoffs[hour] & (1 << CutoffType.BEFORE.ordinal())) > 0) {
                            rule.add(startHour, hour, this.period);
                        } else {
                            hour++;
                        }
                    }
                    throw new ICUException("FROM/AFTER cutoffs must have a matching BEFORE cutoff.");
                }
            }
        }

        private static int parseHour(String str) {
            int firstColonPos = str.indexOf(58);
            if (firstColonPos < 0 || !str.substring(firstColonPos).equals(":00")) {
                throw new ICUException("Cutoff time must end in \":00\".");
            }
            String hourStr = str.substring(0, firstColonPos);
            if (firstColonPos == 1 || firstColonPos == 2) {
                int hour = Integer.parseInt(hourStr);
                if (hour >= 0 && hour <= 24) {
                    return hour;
                }
                throw new ICUException("Cutoff hour must be between 0 and 24, inclusive.");
            }
            throw new ICUException("Cutoff time must begin with h: or hh:");
        }
    }

    /* access modifiers changed from: private */
    public static class DayPeriodRulesCountSink extends UResource.Sink {
        private DayPeriodRulesData data;

        private DayPeriodRulesCountSink(DayPeriodRulesData data2) {
            this.data = data2;
        }

        @Override // android.icu.impl.UResource.Sink
        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table rules = value.getTable();
            for (int i = 0; rules.getKeyAndValue(i, key, value); i++) {
                int setNum = DayPeriodRules.parseSetNum(key.toString());
                if (setNum > this.data.maxRuleSetNum) {
                    this.data.maxRuleSetNum = setNum;
                }
            }
        }
    }

    private DayPeriodRules() {
        this.hasMidnight = false;
        this.hasNoon = false;
        this.dayPeriodForHour = new DayPeriod[24];
    }

    public static DayPeriodRules getInstance(ULocale locale) {
        String localeCode = locale.getBaseName();
        if (localeCode.isEmpty()) {
            localeCode = "root";
        }
        Integer ruleSetNum = null;
        while (ruleSetNum == null) {
            ruleSetNum = DATA.localesToRuleSetNumMap.get(localeCode);
            if (ruleSetNum != null) {
                break;
            }
            localeCode = ULocale.getFallback(localeCode);
            if (localeCode.isEmpty()) {
                break;
            }
        }
        if (ruleSetNum == null || DATA.rules[ruleSetNum.intValue()] == null) {
            return null;
        }
        return DATA.rules[ruleSetNum.intValue()];
    }

    public double getMidPointForDayPeriod(DayPeriod dayPeriod) {
        int startHour = getStartHourForDayPeriod(dayPeriod);
        int endHour = getEndHourForDayPeriod(dayPeriod);
        double midPoint = ((double) (startHour + endHour)) / 2.0d;
        if (startHour <= endHour) {
            return midPoint;
        }
        double midPoint2 = midPoint + 12.0d;
        if (midPoint2 >= 24.0d) {
            return midPoint2 - 24.0d;
        }
        return midPoint2;
    }

    private static DayPeriodRulesData loadData() {
        DayPeriodRulesData data = new DayPeriodRulesData();
        ICUResourceBundle rb = ICUResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "dayPeriods", ICUResourceBundle.ICU_DATA_CLASS_LOADER, true);
        rb.getAllItemsWithFallback("rules", new DayPeriodRulesCountSink(data));
        data.rules = new DayPeriodRules[(data.maxRuleSetNum + 1)];
        rb.getAllItemsWithFallback("", new DayPeriodRulesDataSink(data));
        return data;
    }

    private int getStartHourForDayPeriod(DayPeriod dayPeriod) throws IllegalArgumentException {
        if (dayPeriod == DayPeriod.MIDNIGHT) {
            return 0;
        }
        if (dayPeriod == DayPeriod.NOON) {
            return 12;
        }
        DayPeriod[] dayPeriodArr = this.dayPeriodForHour;
        if (dayPeriodArr[0] == dayPeriod && dayPeriodArr[23] == dayPeriod) {
            for (int i = 22; i >= 1; i--) {
                if (this.dayPeriodForHour[i] != dayPeriod) {
                    return i + 1;
                }
            }
        } else {
            for (int i2 = 0; i2 <= 23; i2++) {
                if (this.dayPeriodForHour[i2] == dayPeriod) {
                    return i2;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private int getEndHourForDayPeriod(DayPeriod dayPeriod) {
        if (dayPeriod == DayPeriod.MIDNIGHT) {
            return 0;
        }
        if (dayPeriod == DayPeriod.NOON) {
            return 12;
        }
        DayPeriod[] dayPeriodArr = this.dayPeriodForHour;
        if (dayPeriodArr[0] == dayPeriod && dayPeriodArr[23] == dayPeriod) {
            for (int i = 1; i <= 22; i++) {
                if (this.dayPeriodForHour[i] != dayPeriod) {
                    return i;
                }
            }
        } else {
            for (int i2 = 23; i2 >= 0; i2--) {
                if (this.dayPeriodForHour[i2] == dayPeriod) {
                    return i2 + 1;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public boolean hasMidnight() {
        return this.hasMidnight;
    }

    public boolean hasNoon() {
        return this.hasNoon;
    }

    public DayPeriod getDayPeriodForHour(int hour) {
        return this.dayPeriodForHour[hour];
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void add(int startHour, int limitHour, DayPeriod period) {
        int i = startHour;
        while (i != limitHour) {
            if (i == 24) {
                i = 0;
            }
            this.dayPeriodForHour[i] = period;
            i++;
        }
    }

    /* access modifiers changed from: private */
    public static int parseSetNum(String setNumStr) {
        if (setNumStr.startsWith("set")) {
            return Integer.parseInt(setNumStr.substring(3));
        }
        throw new ICUException("Set number should start with \"set\".");
    }
}
