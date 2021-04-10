package android.icu.text;

import android.icu.impl.SimpleFilteredSentenceBreakIterator;
import android.icu.util.ULocale;

public abstract class FilteredBreakIteratorBuilder {
    public abstract BreakIterator wrapIteratorWithFilter(BreakIterator breakIterator);

    public static final FilteredBreakIteratorBuilder getInstance(ULocale uLocale) {
        return new SimpleFilteredSentenceBreakIterator.Builder(uLocale);
    }

    protected FilteredBreakIteratorBuilder() {
    }
}
