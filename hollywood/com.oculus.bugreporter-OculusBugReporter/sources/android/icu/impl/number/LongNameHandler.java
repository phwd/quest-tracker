package android.icu.impl.number;

import android.icu.impl.CurrencyData;
import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.SimpleFormatterImpl;
import android.icu.impl.StandardPlural;
import android.icu.impl.UResource;
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
    private static final int ARRAY_LENGTH = (StandardPlural.COUNT + 2);
    private static final int DNAM_INDEX = StandardPlural.COUNT;
    private static final int PER_INDEX = (StandardPlural.COUNT + 1);
    private final Map<StandardPlural, SimpleModifier> modifiers;
    private final MicroPropsGenerator parent;
    private final PluralRules rules;

    /* access modifiers changed from: private */
    public static int getIndex(String pluralKeyword) {
        if (pluralKeyword.equals("dnam")) {
            return DNAM_INDEX;
        }
        if (pluralKeyword.equals("per")) {
            return PER_INDEX;
        }
        return StandardPlural.fromString(pluralKeyword).ordinal();
    }

    private static String getWithPlural(String[] strings, StandardPlural plural) {
        String result = strings[plural.ordinal()];
        if (result == null) {
            result = strings[StandardPlural.OTHER.ordinal()];
        }
        if (result != null) {
            return result;
        }
        throw new ICUException("Could not find data in 'other' plural variant");
    }

    /* access modifiers changed from: private */
    public static final class PluralTableSink extends UResource.Sink {
        String[] outArray;

        public PluralTableSink(String[] outArray2) {
            this.outArray = outArray2;
        }

        @Override // android.icu.impl.UResource.Sink
        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Table pluralsTable = value.getTable();
            for (int i = 0; pluralsTable.getKeyAndValue(i, key, value); i++) {
                int index = LongNameHandler.getIndex(key.toString());
                if (this.outArray[index] == null) {
                    this.outArray[index] = value.getString();
                }
            }
        }
    }

    private static void getMeasureData(ULocale locale, MeasureUnit unit, NumberFormatter.UnitWidth width, String[] outArray) {
        PluralTableSink sink = new PluralTableSink(outArray);
        ICUResourceBundle resource = (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_UNIT_BASE_NAME, locale);
        StringBuilder key = new StringBuilder();
        key.append("units");
        if (width == NumberFormatter.UnitWidth.NARROW) {
            key.append("Narrow");
        } else if (width == NumberFormatter.UnitWidth.SHORT) {
            key.append("Short");
        }
        key.append("/");
        key.append(unit.getType());
        key.append("/");
        key.append(unit.getSubtype());
        try {
            resource.getAllItemsWithFallback(key.toString(), sink);
        } catch (MissingResourceException e) {
            throw new IllegalArgumentException("No data for unit " + ((Object) unit) + ", width " + ((Object) width), e);
        }
    }

    private static void getCurrencyLongNameData(ULocale locale, Currency currency, String[] outArray) {
        for (Map.Entry<String, String> e : CurrencyData.provider.getInstance(locale, true).getUnitPatterns().entrySet()) {
            String pluralKeyword = e.getKey();
            outArray[getIndex(pluralKeyword)] = e.getValue().replace("{1}", currency.getName(locale, 2, pluralKeyword, (boolean[]) null));
        }
    }

    private static String getPerUnitFormat(ULocale locale, NumberFormatter.UnitWidth width) {
        ICUResourceBundle resource = (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_UNIT_BASE_NAME, locale);
        StringBuilder key = new StringBuilder();
        key.append("units");
        if (width == NumberFormatter.UnitWidth.NARROW) {
            key.append("Narrow");
        } else if (width == NumberFormatter.UnitWidth.SHORT) {
            key.append("Short");
        }
        key.append("/compound/per");
        try {
            return resource.getStringWithFallback(key.toString());
        } catch (MissingResourceException e) {
            throw new IllegalArgumentException("Could not find x-per-y format for " + ((Object) locale) + ", width " + ((Object) width));
        }
    }

    private LongNameHandler(Map<StandardPlural, SimpleModifier> modifiers2, PluralRules rules2, MicroPropsGenerator parent2) {
        this.modifiers = modifiers2;
        this.rules = rules2;
        this.parent = parent2;
    }

    public static String getUnitDisplayName(ULocale locale, MeasureUnit unit, NumberFormatter.UnitWidth width) {
        String[] measureData = new String[ARRAY_LENGTH];
        getMeasureData(locale, unit, width, measureData);
        return measureData[DNAM_INDEX];
    }

    public static LongNameHandler forCurrencyLongNames(ULocale locale, Currency currency, PluralRules rules2, MicroPropsGenerator parent2) {
        String[] simpleFormats = new String[ARRAY_LENGTH];
        getCurrencyLongNameData(locale, currency, simpleFormats);
        LongNameHandler result = new LongNameHandler(new EnumMap<>(StandardPlural.class), rules2, parent2);
        result.simpleFormatsToModifiers(simpleFormats, null);
        return result;
    }

    public static LongNameHandler forMeasureUnit(ULocale locale, MeasureUnit unit, MeasureUnit perUnit, NumberFormatter.UnitWidth width, PluralRules rules2, MicroPropsGenerator parent2) {
        if (perUnit != null) {
            MeasureUnit simplified = MeasureUnit.resolveUnitPerUnit(unit, perUnit);
            if (simplified == null) {
                return forCompoundUnit(locale, unit, perUnit, width, rules2, parent2);
            }
            unit = simplified;
        }
        String[] simpleFormats = new String[ARRAY_LENGTH];
        getMeasureData(locale, unit, width, simpleFormats);
        LongNameHandler result = new LongNameHandler(new EnumMap<>(StandardPlural.class), rules2, parent2);
        result.simpleFormatsToModifiers(simpleFormats, null);
        return result;
    }

    private static LongNameHandler forCompoundUnit(ULocale locale, MeasureUnit unit, MeasureUnit perUnit, NumberFormatter.UnitWidth width, PluralRules rules2, MicroPropsGenerator parent2) {
        String rawPerUnitFormat;
        String[] primaryData = new String[ARRAY_LENGTH];
        getMeasureData(locale, unit, width, primaryData);
        String[] secondaryData = new String[ARRAY_LENGTH];
        getMeasureData(locale, perUnit, width, secondaryData);
        int i = PER_INDEX;
        if (secondaryData[i] != null) {
            rawPerUnitFormat = secondaryData[i];
        } else {
            String rawPerUnitFormat2 = getPerUnitFormat(locale, width);
            StringBuilder sb = new StringBuilder();
            rawPerUnitFormat = SimpleFormatterImpl.formatCompiledPattern(SimpleFormatterImpl.compileToStringMinMaxArguments(rawPerUnitFormat2, sb, 2, 2), "{0}", SimpleFormatterImpl.getTextWithNoArguments(SimpleFormatterImpl.compileToStringMinMaxArguments(getWithPlural(secondaryData, StandardPlural.ONE), sb, 1, 1)).trim());
        }
        LongNameHandler result = new LongNameHandler(new EnumMap<>(StandardPlural.class), rules2, parent2);
        result.multiSimpleFormatsToModifiers(primaryData, rawPerUnitFormat, null);
        return result;
    }

    private void simpleFormatsToModifiers(String[] simpleFormats, NumberFormat.Field field) {
        StringBuilder sb = new StringBuilder();
        for (StandardPlural plural : StandardPlural.VALUES) {
            String compiled = SimpleFormatterImpl.compileToStringMinMaxArguments(getWithPlural(simpleFormats, plural), sb, 0, 1);
            Modifier.Parameters parameters = new Modifier.Parameters();
            parameters.obj = this;
            parameters.signum = 0;
            parameters.plural = plural;
            this.modifiers.put(plural, new SimpleModifier(compiled, field, false, parameters));
        }
    }

    private void multiSimpleFormatsToModifiers(String[] leadFormats, String trailFormat, NumberFormat.Field field) {
        StringBuilder sb = new StringBuilder();
        String trailCompiled = SimpleFormatterImpl.compileToStringMinMaxArguments(trailFormat, sb, 1, 1);
        for (StandardPlural plural : StandardPlural.VALUES) {
            String compoundCompiled = SimpleFormatterImpl.compileToStringMinMaxArguments(SimpleFormatterImpl.formatCompiledPattern(trailCompiled, getWithPlural(leadFormats, plural)), sb, 0, 1);
            Modifier.Parameters parameters = new Modifier.Parameters();
            parameters.obj = this;
            parameters.signum = 0;
            parameters.plural = plural;
            this.modifiers.put(plural, new SimpleModifier(compoundCompiled, field, false, parameters));
        }
    }

    @Override // android.icu.impl.number.MicroPropsGenerator
    public MicroProps processQuantity(DecimalQuantity quantity) {
        MicroProps micros = this.parent.processQuantity(quantity);
        DecimalQuantity copy = quantity.createCopy();
        micros.rounder.apply(copy);
        micros.modOuter = this.modifiers.get(copy.getStandardPlural(this.rules));
        return micros;
    }

    @Override // android.icu.impl.number.ModifierStore
    public Modifier getModifier(int signum, StandardPlural plural) {
        return this.modifiers.get(plural);
    }
}
