package android.icu.impl.number.range;

import android.icu.impl.ICUData;
import android.icu.impl.ICUResourceBundle;
import android.icu.impl.StandardPlural;
import android.icu.impl.UResource;
import android.icu.util.ULocale;
import android.icu.util.UResourceBundle;
import java.util.MissingResourceException;

public class StandardPluralRanges {
    StandardPlural[] flatTriples;
    int numTriples = 0;

    /* access modifiers changed from: private */
    public static final class PluralRangesDataSink extends UResource.Sink {
        StandardPluralRanges output;

        PluralRangesDataSink(StandardPluralRanges output2) {
            this.output = output2;
        }

        @Override // android.icu.impl.UResource.Sink
        public void put(UResource.Key key, UResource.Value value, boolean noFallback) {
            UResource.Array entriesArray = value.getArray();
            this.output.setCapacity(entriesArray.getSize());
            for (int i = 0; entriesArray.getValue(i, value); i++) {
                UResource.Array pluralFormsArray = value.getArray();
                pluralFormsArray.getValue(0, value);
                StandardPlural first = StandardPlural.fromString(value.getString());
                pluralFormsArray.getValue(1, value);
                StandardPlural second = StandardPlural.fromString(value.getString());
                pluralFormsArray.getValue(2, value);
                this.output.addPluralRange(first, second, StandardPlural.fromString(value.getString()));
            }
        }
    }

    private static void getPluralRangesData(ULocale locale, StandardPluralRanges out) {
        StringBuilder sb = new StringBuilder();
        ICUResourceBundle resource = (ICUResourceBundle) UResourceBundle.getBundleInstance(ICUData.ICU_BASE_NAME, "pluralRanges");
        sb.append("locales/");
        sb.append(locale.getLanguage());
        try {
            String set = resource.getStringWithFallback(sb.toString());
            sb.setLength(0);
            sb.append("rules/");
            sb.append(set);
            resource.getAllItemsWithFallback(sb.toString(), new PluralRangesDataSink(out));
        } catch (MissingResourceException e) {
        }
    }

    public StandardPluralRanges(ULocale locale) {
        getPluralRangesData(locale, this);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addPluralRange(StandardPlural first, StandardPlural second, StandardPlural result) {
        StandardPlural[] standardPluralArr = this.flatTriples;
        int i = this.numTriples;
        standardPluralArr[i * 3] = first;
        standardPluralArr[(i * 3) + 1] = second;
        standardPluralArr[(i * 3) + 2] = result;
        this.numTriples = i + 1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCapacity(int length) {
        this.flatTriples = new StandardPlural[(length * 3)];
    }

    public StandardPlural resolve(StandardPlural first, StandardPlural second) {
        for (int i = 0; i < this.numTriples; i++) {
            StandardPlural[] standardPluralArr = this.flatTriples;
            if (first == standardPluralArr[i * 3] && second == standardPluralArr[(i * 3) + 1]) {
                return standardPluralArr[(i * 3) + 2];
            }
        }
        return StandardPlural.OTHER;
    }
}
