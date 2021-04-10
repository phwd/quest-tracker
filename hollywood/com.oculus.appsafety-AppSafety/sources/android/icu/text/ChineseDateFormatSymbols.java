package android.icu.text;

import android.icu.impl.ICUResourceBundle;
import android.icu.util.Calendar;
import android.icu.util.ChineseCalendar;
import android.icu.util.ULocale;
import java.util.Locale;

@Deprecated
public class ChineseDateFormatSymbols extends DateFormatSymbols {
    static final long serialVersionUID = 6827816119783952890L;
    String[] isLeapMonth;

    @Deprecated
    public ChineseDateFormatSymbols() {
        this(ULocale.getDefault(ULocale.Category.FORMAT));
    }

    @Deprecated
    public ChineseDateFormatSymbols(Locale locale) {
        super(ChineseCalendar.class, ULocale.forLocale(locale));
    }

    @Deprecated
    public ChineseDateFormatSymbols(ULocale locale) {
        super(ChineseCalendar.class, locale);
    }

    @Deprecated
    public ChineseDateFormatSymbols(Calendar cal, Locale locale) {
        super((Class<? extends Calendar>) cal.getClass(), locale);
    }

    @Deprecated
    public ChineseDateFormatSymbols(Calendar cal, ULocale locale) {
        super((Class<? extends Calendar>) cal.getClass(), locale);
    }

    @Deprecated
    public String getLeapMonth(int leap) {
        return this.isLeapMonth[leap];
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.DateFormatSymbols
    @Deprecated
    public void initializeData(ULocale loc, ICUResourceBundle b, String calendarType) {
        super.initializeData(loc, b, calendarType);
        initializeIsLeapMonth();
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.text.DateFormatSymbols
    public void initializeData(DateFormatSymbols dfs) {
        super.initializeData(dfs);
        if (dfs instanceof ChineseDateFormatSymbols) {
            this.isLeapMonth = ((ChineseDateFormatSymbols) dfs).isLeapMonth;
        } else {
            initializeIsLeapMonth();
        }
    }

    private void initializeIsLeapMonth() {
        this.isLeapMonth = new String[2];
        String[] strArr = this.isLeapMonth;
        String str = "";
        strArr[0] = str;
        if (this.leapMonthPatterns != null) {
            str = this.leapMonthPatterns[0].replace("{0}", str);
        }
        strArr[1] = str;
    }
}
