package android.icu.impl;

import android.icu.impl.LocaleDisplayNamesImpl;
import android.icu.util.ULocale;

public class ICULangDataTables extends LocaleDisplayNamesImpl.ICUDataTables {
    @Override // android.icu.impl.LocaleDisplayNamesImpl.DataTables, android.icu.impl.LocaleDisplayNamesImpl.ICUDataTables
    public /* bridge */ /* synthetic */ LocaleDisplayNamesImpl.DataTable get(ULocale uLocale, boolean z) {
        return super.get(uLocale, z);
    }

    public ICULangDataTables() {
        super(ICUData.ICU_LANG_BASE_NAME);
    }
}
