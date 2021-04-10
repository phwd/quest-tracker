package android.icu.impl;

import android.icu.text.TimeZoneNames;
import android.icu.util.ULocale;

public class TimeZoneNamesFactoryImpl extends TimeZoneNames.Factory {
    @Override // android.icu.text.TimeZoneNames.Factory
    public TimeZoneNames getTimeZoneNames(ULocale locale) {
        return new TimeZoneNamesImpl(locale);
    }
}
