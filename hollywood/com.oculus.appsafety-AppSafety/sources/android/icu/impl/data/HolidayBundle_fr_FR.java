package android.icu.impl.data;

import android.icu.util.EasterHoliday;
import android.icu.util.Holiday;
import android.icu.util.SimpleHoliday;
import java.util.ListResourceBundle;

public class HolidayBundle_fr_FR extends ListResourceBundle {
    private static final Object[][] fContents = {new Object[]{"holidays", fHolidays}};
    private static final Holiday[] fHolidays = {SimpleHoliday.NEW_YEARS_DAY, new SimpleHoliday(4, 1, 0, "Labor Day"), new SimpleHoliday(4, 8, 0, "Victory Day"), new SimpleHoliday(6, 14, 0, "Bastille Day"), SimpleHoliday.ASSUMPTION, SimpleHoliday.ALL_SAINTS_DAY, new SimpleHoliday(10, 11, 0, "Armistice Day"), SimpleHoliday.CHRISTMAS, EasterHoliday.EASTER_SUNDAY, EasterHoliday.EASTER_MONDAY, EasterHoliday.ASCENSION, EasterHoliday.WHIT_SUNDAY, EasterHoliday.WHIT_MONDAY};

    @Override // java.util.ListResourceBundle
    public synchronized Object[][] getContents() {
        return fContents;
    }
}
