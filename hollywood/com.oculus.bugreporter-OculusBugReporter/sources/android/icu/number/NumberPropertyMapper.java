package android.icu.number;

import android.icu.impl.number.DecimalFormatProperties;
import android.icu.impl.number.PatternStringParser;
import android.icu.text.DecimalFormatSymbols;

/* access modifiers changed from: package-private */
public final class NumberPropertyMapper {
    NumberPropertyMapper() {
    }

    public static UnlocalizedNumberFormatter create(DecimalFormatProperties properties, DecimalFormatSymbols symbols) {
        return (UnlocalizedNumberFormatter) NumberFormatter.with().macros(oldToNew(properties, symbols, null));
    }

    public static UnlocalizedNumberFormatter create(DecimalFormatProperties properties, DecimalFormatSymbols symbols, DecimalFormatProperties exportedProperties) {
        return (UnlocalizedNumberFormatter) NumberFormatter.with().macros(oldToNew(properties, symbols, exportedProperties));
    }

    public static UnlocalizedNumberFormatter create(String pattern, DecimalFormatSymbols symbols) {
        return create(PatternStringParser.parseToProperties(pattern), symbols);
    }

    /* JADX WARNING: Removed duplicated region for block: B:115:0x0190  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x01bd  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01ca  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x01d8  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0280  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x028c  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x02be  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0334  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00d9 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x011c A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0146  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.icu.impl.number.MacroProps oldToNew(android.icu.impl.number.DecimalFormatProperties r29, android.icu.text.DecimalFormatSymbols r30, android.icu.impl.number.DecimalFormatProperties r31) {
        /*
        // Method dump skipped, instructions count: 823
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.number.NumberPropertyMapper.oldToNew(android.icu.impl.number.DecimalFormatProperties, android.icu.text.DecimalFormatSymbols, android.icu.impl.number.DecimalFormatProperties):android.icu.impl.number.MacroProps");
    }
}
