package android.icu.impl.data;

import android.icu.util.EasterHoliday;
import android.icu.util.Holiday;
import android.icu.util.SimpleHoliday;
import java.util.ListResourceBundle;

public class HolidayBundle_el_GR extends ListResourceBundle {
    private static final Object[][] fContents = {new Object[]{"holidays", fHolidays}};
    private static final Holiday[] fHolidays = {SimpleHoliday.NEW_YEARS_DAY, SimpleHoliday.EPIPHANY, new SimpleHoliday(2, 25, 0, "Independence Day"), SimpleHoliday.MAY_DAY, SimpleHoliday.ASSUMPTION, new SimpleHoliday(9, 28, 0, "Ochi Day"), SimpleHoliday.CHRISTMAS, SimpleHoliday.BOXING_DAY, new EasterHoliday(-2, true, "Good Friday"), new EasterHoliday(0, true, "Easter Sunday"), new EasterHoliday(1, true, "Easter Monday"), new EasterHoliday(50, true, "Whit Monday")};

    @Override // java.util.ListResourceBundle
    public synchronized Object[][] getContents() {
        return fContents;
    }
}
