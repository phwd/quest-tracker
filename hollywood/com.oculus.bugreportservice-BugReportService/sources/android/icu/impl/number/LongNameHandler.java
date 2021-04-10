package android.icu.impl.number;

import android.icu.impl.CurrencyData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.SimpleFormatterImpl;
import android.icu.impl.StandardPlural;
import android.icu.impl.UResource$Key;
import android.icu.impl.UResource$Sink;
import android.icu.impl.UResource$Table;
import android.icu.impl.UResource$Value;
import android.icu.impl.number.Modifier;
import android.icu.number.NumberFormatter;
import android.icu.text.NumberFormat;
import android.icu.text.PluralRules;
import android.icu.util.Currency;
import android.icu.util.ICUException;
import android.icu.util.MeasureUnit;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.util.EnumMap;
import java.util.Map;
import java.util.MissingResourceException;

public class LongNameHandler implements MicroPropsGenerator, ModifierStore {
    private static final int ARRAY_LENGTH;
    private static final int DNAM_INDEX;
    private static final int PER_INDEX;
    private final Map modifiers;
    private final MicroPropsGenerator parent;
    private final PluralRules rules;

    static {
        int i = StandardPlural.COUNT;
        DNAM_INDEX = i;
        PER_INDEX = i + 1;
        ARRAY_LENGTH = i + 2;
    }

    /* access modifiers changed from: private */
    public static int getIndex(String str) {
        if (str.equals("dnam")) {
            return DNAM_INDEX;
        }
        if (str.equals("per")) {
            return PER_INDEX;
        }
        return StandardPlural.fromString(str).ordinal();
    }

    private static String getWithPlural(String[] strArr, StandardPlural standardPlural) {
        String str = strArr[standardPlural.ordinal()];
        if (str == null) {
            str = strArr[StandardPlural.OTHER.ordinal()];
        }
        if (str != null) {
            return str;
        }
        throw new ICUException("Could not find data in 'other' plural variant");
    }

    /* access modifiers changed from: private */
    public static final class PluralTableSink extends UResource$Sink {
        String[] outArray;

        public PluralTableSink(String[] strArr) {
            this.outArray = strArr;
        }

        @Override // android.icu.impl.UResource$Sink
        public void put(UResource$Key uResource$Key, UResource$Value uResource$Value, boolean z) {
            UResource$Table table = uResource$Value.getTable();
            for (int i = 0; table.getKeyAndValue(i, uResource$Key, uResource$Value); i++) {
                int index = LongNameHandler.getIndex(uResource$Key.toString());
                if (this.outArray[index] == null) {
                    this.outArray[index] = uResource$Value.getString();
                }
            }
        }
    }

    private static void getMeasureData(ULocale uLocale, MeasureUnit measureUnit, NumberFormatter.UnitWidth unitWidth, String[] strArr) {
        PluralTableSink pluralTableSink = new PluralTableSink(strArr);
        ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b/unit", uLocale);
        StringBuilder sb = new StringBuilder();
        sb.append("units");
        if (unitWidth == NumberFormatter.UnitWidth.NARROW) {
            sb.append("Narrow");
        } else if (unitWidth == NumberFormatter.UnitWidth.SHORT) {
            sb.append("Short");
        }
        sb.append("/");
        sb.append(measureUnit.getType());
        sb.append("/");
        sb.append(measureUnit.getSubtype());
        try {
            iCUResourceBundle.getAllItemsWithFallback(sb.toString(), pluralTableSink);
        } catch (MissingResourceException e) {
            throw new IllegalArgumentException("No data for unit " + measureUnit + ", width " + unitWidth, e);
        }
    }

    private static void getCurrencyLongNameData(ULocale uLocale, Currency currency, String[] strArr) {
        for (Map.Entry entry : CurrencyData.provider.getInstance(uLocale, true).getUnitPatterns().entrySet()) {
            String str = (String) entry.getKey();
            strArr[getIndex(str)] = ((String) entry.getValue()).replace("{1}", currency.getName(uLocale, 2, str, (boolean[]) null));
        }
    }

    private static String getPerUnitFormat(ULocale uLocale, NumberFormatter.UnitWidth unitWidth) {
        ICUResourceBundle iCUResourceBundle = (ICUResourceBundle) UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b/unit", uLocale);
        StringBuilder sb = new StringBuilder();
        sb.append("units");
        if (unitWidth == NumberFormatter.UnitWidth.NARROW) {
            sb.append("Narrow");
        } else if (unitWidth == NumberFormatter.UnitWidth.SHORT) {
            sb.append("Short");
        }
        sb.append("/compound/per");
        try {
            return iCUResourceBundle.getStringWithFallback(sb.toString());
        } catch (MissingResourceException unused) {
            throw new IllegalArgumentException("Could not find x-per-y format for " + uLocale + ", width " + unitWidth);
        }
    }

    private LongNameHandler(Map map, PluralRules pluralRules, MicroPropsGenerator microPropsGenerator) {
        this.modifiers = map;
        this.rules = pluralRules;
        this.parent = microPropsGenerator;
    }

    public static LongNameHandler forCurrencyLongNames(ULocale uLocale, Currency currency, PluralRules pluralRules, MicroPropsGenerator microPropsGenerator) {
        String[] strArr = new String[ARRAY_LENGTH];
        getCurrencyLongNameData(uLocale, currency, strArr);
        LongNameHandler longNameHandler = new LongNameHandler(new EnumMap(StandardPlural.class), pluralRules, microPropsGenerator);
        longNameHandler.simpleFormatsToModifiers(strArr, null);
        return longNameHandler;
    }

    public static LongNameHandler forMeasureUnit(ULocale uLocale, MeasureUnit measureUnit, MeasureUnit measureUnit2, NumberFormatter.UnitWidth unitWidth, PluralRules pluralRules, MicroPropsGenerator microPropsGenerator) {
        if (measureUnit2 != null) {
            MeasureUnit resolveUnitPerUnit = MeasureUnit.resolveUnitPerUnit(measureUnit, measureUnit2);
            if (resolveUnitPerUnit == null) {
                return forCompoundUnit(uLocale, measureUnit, measureUnit2, unitWidth, pluralRules, microPropsGenerator);
            }
            measureUnit = resolveUnitPerUnit;
        }
        String[] strArr = new String[ARRAY_LENGTH];
        getMeasureData(uLocale, measureUnit, unitWidth, strArr);
        LongNameHandler longNameHandler = new LongNameHandler(new EnumMap(StandardPlural.class), pluralRules, microPropsGenerator);
        longNameHandler.simpleFormatsToModifiers(strArr, null);
        return longNameHandler;
    }

    private static LongNameHandler forCompoundUnit(ULocale uLocale, MeasureUnit measureUnit, MeasureUnit measureUnit2, NumberFormatter.UnitWidth unitWidth, PluralRules pluralRules, MicroPropsGenerator microPropsGenerator) {
        String str;
        String[] strArr = new String[ARRAY_LENGTH];
        getMeasureData(uLocale, measureUnit, unitWidth, strArr);
        String[] strArr2 = new String[ARRAY_LENGTH];
        getMeasureData(uLocale, measureUnit2, unitWidth, strArr2);
        int i = PER_INDEX;
        if (strArr2[i] != null) {
            str = strArr2[i];
        } else {
            String perUnitFormat = getPerUnitFormat(uLocale, unitWidth);
            StringBuilder sb = new StringBuilder();
            str = SimpleFormatterImpl.formatCompiledPattern(SimpleFormatterImpl.compileToStringMinMaxArguments(perUnitFormat, sb, 2, 2), "{0}", SimpleFormatterImpl.getTextWithNoArguments(SimpleFormatterImpl.compileToStringMinMaxArguments(getWithPlural(strArr2, StandardPlural.ONE), sb, 1, 1)).trim());
        }
        LongNameHandler longNameHandler = new LongNameHandler(new EnumMap(StandardPlural.class), pluralRules, microPropsGenerator);
        longNameHandler.multiSimpleFormatsToModifiers(strArr, str, null);
        return longNameHandler;
    }

    private void simpleFormatsToModifiers(String[] strArr, NumberFormat.Field field) {
        StringBuilder sb = new StringBuilder();
        for (StandardPlural standardPlural : StandardPlural.VALUES) {
            String compileToStringMinMaxArguments = SimpleFormatterImpl.compileToStringMinMaxArguments(getWithPlural(strArr, standardPlural), sb, 0, 1);
            Modifier.Parameters parameters = new Modifier.Parameters();
            parameters.obj = this;
            parameters.signum = 0;
            parameters.plural = standardPlural;
            this.modifiers.put(standardPlural, new SimpleModifier(compileToStringMinMaxArguments, field, false, parameters));
        }
    }

    private void multiSimpleFormatsToModifiers(String[] strArr, String str, NumberFormat.Field field) {
        StringBuilder sb = new StringBuilder();
        String compileToStringMinMaxArguments = SimpleFormatterImpl.compileToStringMinMaxArguments(str, sb, 1, 1);
        for (StandardPlural standardPlural : StandardPlural.VALUES) {
            String compileToStringMinMaxArguments2 = SimpleFormatterImpl.compileToStringMinMaxArguments(SimpleFormatterImpl.formatCompiledPattern(compileToStringMinMaxArguments, getWithPlural(strArr, standardPlural)), sb, 0, 1);
            Modifier.Parameters parameters = new Modifier.Parameters();
            parameters.obj = this;
            parameters.signum = 0;
            parameters.plural = standardPlural;
            this.modifiers.put(standardPlural, new SimpleModifier(compileToStringMinMaxArguments2, field, false, parameters));
        }
    }

    @Override // android.icu.impl.number.MicroPropsGenerator
    public MicroProps processQuantity(DecimalQuantity decimalQuantity) {
        MicroProps processQuantity = this.parent.processQuantity(decimalQuantity);
        DecimalQuantity createCopy = decimalQuantity.createCopy();
        processQuantity.rounder.apply(createCopy);
        processQuantity.modOuter = (Modifier) this.modifiers.get(createCopy.getStandardPlural(this.rules));
        return processQuantity;
    }
}
