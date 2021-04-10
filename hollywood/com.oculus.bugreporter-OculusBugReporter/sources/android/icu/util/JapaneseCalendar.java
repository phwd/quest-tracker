package android.icu.util;

import android.icu.impl.CalType;
import android.icu.impl.EraRules;
import java.util.Date;
import java.util.Locale;

public class JapaneseCalendar extends GregorianCalendar {
    @Deprecated
    public static final int CURRENT_ERA = REIWA;
    private static final EraRules ERA_RULES;
    private static final int GREGORIAN_EPOCH = 1970;
    public static final int HEISEI = 235;
    public static final int MEIJI = 232;
    public static final int REIWA = 236;
    public static final int SHOWA = 234;
    public static final int TAISHO = 233;
    private static final long serialVersionUID = -2977189902603704691L;

    public JapaneseCalendar() {
    }

    public JapaneseCalendar(TimeZone zone) {
        super(zone);
    }

    public JapaneseCalendar(Locale aLocale) {
        super(aLocale);
    }

    public JapaneseCalendar(ULocale locale) {
        super(locale);
    }

    public JapaneseCalendar(TimeZone zone, Locale aLocale) {
        super(zone, aLocale);
    }

    public JapaneseCalendar(TimeZone zone, ULocale locale) {
        super(zone, locale);
    }

    public JapaneseCalendar(Date date) {
        this();
        setTime(date);
    }

    public JapaneseCalendar(int era, int year, int month, int date) {
        super(year, month, date);
        set(0, era);
    }

    public JapaneseCalendar(int year, int month, int date) {
        super(year, month, date);
        set(0, CURRENT_ERA);
    }

    public JapaneseCalendar(int year, int month, int date, int hour, int minute, int second) {
        super(year, month, date, hour, minute, second);
        set(0, CURRENT_ERA);
    }

    static {
        boolean includeTentativeEra;
        String eraConf = System.getProperty("ICU_ENABLE_TENTATIVE_ERA");
        if (eraConf == null) {
            eraConf = System.getenv("ICU_ENABLE_TENTATIVE_ERA");
        }
        if (eraConf != null) {
            includeTentativeEra = eraConf.equalsIgnoreCase("true");
        } else {
            includeTentativeEra = System.getProperty("jdk.calendar.japanese.supplemental.era") != null;
        }
        ERA_RULES = EraRules.getInstance(CalType.JAPANESE, includeTentativeEra);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.GregorianCalendar, android.icu.util.Calendar
    public int handleGetExtendedYear() {
        if (newerField(19, 1) == 19 && newerField(19, 0) == 19) {
            return internalGet(19, GREGORIAN_EPOCH);
        }
        return (internalGet(1, 1) + ERA_RULES.getStartYear(internalGet(0, CURRENT_ERA))) - 1;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.Calendar
    public int getDefaultMonthInYear(int extendedYear) {
        int[] eraStart = ERA_RULES.getStartDate(internalGet(0, CURRENT_ERA), null);
        if (extendedYear == eraStart[0]) {
            return eraStart[1] - 1;
        }
        return super.getDefaultMonthInYear(extendedYear);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.Calendar
    public int getDefaultDayInMonth(int extendedYear, int month) {
        int[] eraStart = ERA_RULES.getStartDate(internalGet(0, CURRENT_ERA), null);
        if (extendedYear == eraStart[0] && month == eraStart[1] - 1) {
            return eraStart[2];
        }
        return super.getDefaultDayInMonth(extendedYear, month);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.GregorianCalendar, android.icu.util.Calendar
    public void handleComputeFields(int julianDay) {
        super.handleComputeFields(julianDay);
        int year = internalGet(19);
        int eraIdx = ERA_RULES.getEraIndex(year, internalGet(2) + 1, internalGet(5));
        internalSet(0, eraIdx);
        internalSet(1, (year - ERA_RULES.getStartYear(eraIdx)) + 1);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.util.GregorianCalendar, android.icu.util.Calendar
    public int handleGetLimit(int field, int limitType) {
        if (field != 0) {
            if (field == 1) {
                if (limitType == 0 || limitType == 1 || limitType == 2) {
                    return 1;
                }
                if (limitType == 3) {
                    return super.handleGetLimit(field, 3) - ERA_RULES.getStartYear(CURRENT_ERA);
                }
            }
            return super.handleGetLimit(field, limitType);
        } else if (limitType == 0 || limitType == 1) {
            return 0;
        } else {
            return CURRENT_ERA;
        }
    }

    @Override // android.icu.util.GregorianCalendar, android.icu.util.Calendar
    public String getType() {
        return "japanese";
    }

    @Override // android.icu.util.Calendar
    @Deprecated
    public boolean haveDefaultCentury() {
        return false;
    }

    @Override // android.icu.util.GregorianCalendar, android.icu.util.Calendar
    public int getActualMaximum(int field) {
        if (field != 1) {
            return super.getActualMaximum(field);
        }
        int era = get(0);
        if (era == CURRENT_ERA) {
            return handleGetLimit(1, 3);
        }
        int[] nextEraStart = ERA_RULES.getStartDate(era + 1, null);
        int nextEraYear = nextEraStart[0];
        int nextEraMonth = nextEraStart[1];
        int nextEraDate = nextEraStart[2];
        int maxYear = (nextEraYear - ERA_RULES.getStartYear(era)) + 1;
        if (nextEraMonth == 1 && nextEraDate == 1) {
            return maxYear - 1;
        }
        return maxYear;
    }
}
