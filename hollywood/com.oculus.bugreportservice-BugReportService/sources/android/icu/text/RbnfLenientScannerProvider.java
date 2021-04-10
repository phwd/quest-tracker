package android.icu.text;

import android.icu.util.ULocale;

public interface RbnfLenientScannerProvider {
    RbnfLenientScanner get(ULocale uLocale, String str);
}
