package android.icu.number;

import android.icu.impl.number.DecimalFormatProperties;
import android.icu.text.DecimalFormatSymbols;

/* access modifiers changed from: package-private */
public final class NumberPropertyMapper {
    public static UnlocalizedNumberFormatter create(DecimalFormatProperties decimalFormatProperties, DecimalFormatSymbols decimalFormatSymbols, DecimalFormatProperties decimalFormatProperties2) {
        return (UnlocalizedNumberFormatter) NumberFormatter.with().macros(oldToNew(decimalFormatProperties, decimalFormatSymbols, decimalFormatProperties2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0103, code lost:
        if (r9 > 999) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x012e, code lost:
        if (r14 > r4) goto L_0x0128;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.icu.impl.number.MacroProps oldToNew(android.icu.impl.number.DecimalFormatProperties r19, android.icu.text.DecimalFormatSymbols r20, android.icu.impl.number.DecimalFormatProperties r21) {
        /*
        // Method dump skipped, instructions count: 683
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.number.NumberPropertyMapper.oldToNew(android.icu.impl.number.DecimalFormatProperties, android.icu.text.DecimalFormatSymbols, android.icu.impl.number.DecimalFormatProperties):android.icu.impl.number.MacroProps");
    }
}
