package android.icu.impl;

import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;

public class ICUResourceTableAccess {
    public static String getTableString(String path, ULocale locale, String tableName, String itemName, String defaultValue) {
        return getTableString((ICUResourceBundle) UResourceBundle.getBundleInstance(path, locale.getBaseName()), tableName, (String) null, itemName, defaultValue);
    }

    public static String getTableString(ICUResourceBundle bundle, String tableName, String subtableName, String item, String defaultValue) {
        String result = null;
        while (true) {
            try {
                ICUResourceBundle table = bundle.findWithFallback(tableName);
                if (table != null) {
                    ICUResourceBundle stable = table;
                    if (subtableName != null) {
                        stable = table.findWithFallback(subtableName);
                    }
                    if (stable != null && (result = stable.findStringWithFallback(item)) != null) {
                        break;
                    }
                    if (subtableName == null) {
                        String currentName = null;
                        if (tableName.equals("Countries")) {
                            currentName = LocaleIDs.getCurrentCountryID(item);
                        } else if (tableName.equals("Languages")) {
                            currentName = LocaleIDs.getCurrentLanguageID(item);
                        }
                        if (!(currentName == null || (result = table.findStringWithFallback(currentName)) == null)) {
                            break;
                        }
                    }
                    String fallbackLocale = table.findStringWithFallback("Fallback");
                    if (fallbackLocale == null) {
                        return defaultValue;
                    }
                    if (fallbackLocale.length() == 0) {
                        fallbackLocale = "root";
                    }
                    if (fallbackLocale.equals(table.getULocale().getName())) {
                        return defaultValue;
                    }
                    bundle = (ICUResourceBundle) UResourceBundle.getBundleInstance(bundle.getBaseName(), fallbackLocale);
                } else {
                    return defaultValue;
                }
            } catch (Exception e) {
            }
        }
        return (result == null || result.length() <= 0) ? defaultValue : result;
    }
}
