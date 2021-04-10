package android.icu.text;

import android.icu.impl.CacheValue;
import android.icu.impl.ICUDebug;
import android.icu.util.ICUCloneNotSupportedException;
import android.icu.util.ULocale;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.MissingResourceException;

public abstract class BreakIterator implements Cloneable {
    private static final boolean DEBUG = ICUDebug.enabled("breakiterator");
    private static final CacheValue[] iterCache = new CacheValue[5];
    private static BreakIteratorServiceShim shim;
    private ULocale actualLocale;
    private ULocale validLocale;

    public abstract int first();

    public abstract CharacterIterator getText();

    public abstract int next();

    public abstract void setText(CharacterIterator characterIterator);

    protected BreakIterator() {
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new ICUCloneNotSupportedException(e);
        }
    }

    public void setText(String str) {
        setText(new StringCharacterIterator(str));
    }

    public static BreakIterator getWordInstance(ULocale uLocale) {
        return getBreakInstance(uLocale, 1);
    }

    public static BreakIterator getSentenceInstance(ULocale uLocale) {
        return getBreakInstance(uLocale, 3);
    }

    public static BreakIterator getBreakInstance(ULocale uLocale, int i) {
        BreakIteratorCache breakIteratorCache;
        if (uLocale != null) {
            CacheValue[] cacheValueArr = iterCache;
            if (cacheValueArr[i] != null && (breakIteratorCache = (BreakIteratorCache) cacheValueArr[i].get()) != null && breakIteratorCache.getLocale().equals(uLocale)) {
                return breakIteratorCache.createBreakInstance();
            }
            BreakIterator createBreakIterator = getShim().createBreakIterator(uLocale, i);
            iterCache[i] = CacheValue.getInstance(new BreakIteratorCache(uLocale, createBreakIterator));
            return createBreakIterator;
        }
        throw new NullPointerException("Specified locale is null");
    }

    /* access modifiers changed from: private */
    public static final class BreakIteratorCache {
        private BreakIterator iter;
        private ULocale where;

        BreakIteratorCache(ULocale uLocale, BreakIterator breakIterator) {
            this.where = uLocale;
            this.iter = (BreakIterator) breakIterator.clone();
        }

        /* access modifiers changed from: package-private */
        public ULocale getLocale() {
            return this.where;
        }

        /* access modifiers changed from: package-private */
        public BreakIterator createBreakInstance() {
            return (BreakIterator) this.iter.clone();
        }
    }

    /* access modifiers changed from: package-private */
    public static abstract class BreakIteratorServiceShim {
        public abstract BreakIterator createBreakIterator(ULocale uLocale, int i);

        BreakIteratorServiceShim() {
        }
    }

    private static BreakIteratorServiceShim getShim() {
        if (shim == null) {
            try {
                shim = (BreakIteratorServiceShim) Class.forName("android.icu.text.BreakIteratorFactory").newInstance();
            } catch (MissingResourceException e) {
                throw e;
            } catch (Exception e2) {
                if (DEBUG) {
                    e2.printStackTrace();
                }
                throw new RuntimeException(e2.getMessage());
            }
        }
        return shim;
    }

    /* access modifiers changed from: package-private */
    public final void setLocale(ULocale uLocale, ULocale uLocale2) {
        boolean z = true;
        boolean z2 = uLocale == null;
        if (uLocale2 != null) {
            z = false;
        }
        if (z2 == z) {
            this.validLocale = uLocale;
            this.actualLocale = uLocale2;
            return;
        }
        throw new IllegalArgumentException();
    }
}
