package android.icu.util;

import android.icu.util.ULocale;
import java.util.Date;

@Deprecated
public class DangiCalendar extends ChineseCalendar {
    private static final int DANGI_EPOCH_YEAR = -2332;
    private static final TimeZone KOREA_ZONE;
    private static final long serialVersionUID = 8156297445349501985L;

    static {
        InitialTimeZoneRule initialTimeZone = new InitialTimeZoneRule("GMT+8", 28800000, 0);
        TimeZoneRule rule1897 = new TimeArrayTimeZoneRule("Korean 1897", 25200000, 0, new long[]{-2302128000000L}, 1);
        TimeZoneRule rule1898to1911 = new TimeArrayTimeZoneRule("Korean 1898-1911", 28800000, 0, new long[]{-2270592000000L}, 1);
        TimeZoneRule ruleFrom1912 = new TimeArrayTimeZoneRule("Korean 1912-", 32400000, 0, new long[]{-1829088000000L}, 1);
        RuleBasedTimeZone tz = new RuleBasedTimeZone("KOREA_ZONE", initialTimeZone);
        tz.addTransitionRule(rule1897);
        tz.addTransitionRule(rule1898to1911);
        tz.addTransitionRule(ruleFrom1912);
        tz.freeze();
        KOREA_ZONE = tz;
    }

    @Deprecated
    public DangiCalendar() {
        this(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
    }

    @Deprecated
    public DangiCalendar(Date date) {
        this(TimeZone.getDefault(), ULocale.getDefault(ULocale.Category.FORMAT));
        setTime(date);
    }

    @Deprecated
    public DangiCalendar(TimeZone zone, ULocale locale) {
        super(zone, locale, (int) DANGI_EPOCH_YEAR, KOREA_ZONE);
    }

    @Override // android.icu.util.ChineseCalendar, android.icu.util.Calendar
    @Deprecated
    public String getType() {
        return "dangi";
    }
}
