package android.icu.text;

import android.icu.util.CaseInsensitiveString;
import android.icu.util.UResourceBundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class Transliterator implements StringTransform {
    private static Map displayNameCache = Collections.synchronizedMap(new HashMap());
    private static TransliteratorRegistry registry = new TransliteratorRegistry();
    private String ID;
    private UnicodeSet filter;
    private int maximumContextLength = 0;

    public interface Factory {
        Transliterator getInstance(String str);
    }

    /* access modifiers changed from: protected */
    public abstract void handleTransliterate(Replaceable replaceable, Position position, boolean z);

    public static class Position {
        public int contextLimit;
        public int contextStart;
        public int limit;
        public int start;

        public Position() {
            this(0, 0, 0, 0);
        }

        public Position(int i, int i2, int i3) {
            this(i, i2, i3, i2);
        }

        public Position(int i, int i2, int i3, int i4) {
            this.contextStart = i;
            this.contextLimit = i2;
            this.start = i3;
            this.limit = i4;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Position)) {
                return false;
            }
            Position position = (Position) obj;
            if (this.contextStart == position.contextStart && this.contextLimit == position.contextLimit && this.start == position.start && this.limit == position.limit) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.contextStart), Integer.valueOf(this.contextLimit), Integer.valueOf(this.start), Integer.valueOf(this.limit));
        }

        public String toString() {
            return "[cs=" + this.contextStart + ", s=" + this.start + ", l=" + this.limit + ", cl=" + this.contextLimit + "]";
        }
    }

    protected Transliterator(String str, UnicodeFilter unicodeFilter) {
        if (str != null) {
            this.ID = str;
            setFilter(unicodeFilter);
            return;
        }
        throw new NullPointerException();
    }

    public final int transliterate(Replaceable replaceable, int i, int i2) {
        if (i < 0 || i2 < i || replaceable.length() < i2) {
            return -1;
        }
        Position position = new Position(i, i2, i);
        filteredTransliterate(replaceable, position, false, true);
        return position.limit;
    }

    public final void transliterate(Replaceable replaceable) {
        transliterate(replaceable, 0, replaceable.length());
    }

    public final String transliterate(String str) {
        ReplaceableString replaceableString = new ReplaceableString(str);
        transliterate(replaceableString);
        return replaceableString.toString();
    }

    private void filteredTransliterate(Replaceable replaceable, Position position, boolean z, boolean z2) {
        boolean z3;
        if (this.filter != null || z2) {
            int i = position.limit;
            do {
                if (this.filter != null) {
                    while (true) {
                        int i2 = position.start;
                        if (i2 >= i) {
                            break;
                        }
                        UnicodeSet unicodeSet = this.filter;
                        int char32At = replaceable.char32At(i2);
                        if (unicodeSet.contains(char32At)) {
                            break;
                        }
                        position.start += UTF16.getCharCount(char32At);
                    }
                    position.limit = position.start;
                    while (true) {
                        int i3 = position.limit;
                        if (i3 >= i) {
                            break;
                        }
                        UnicodeSet unicodeSet2 = this.filter;
                        int char32At2 = replaceable.char32At(i3);
                        if (!unicodeSet2.contains(char32At2)) {
                            break;
                        }
                        position.limit += UTF16.getCharCount(char32At2);
                    }
                }
                int i4 = position.start;
                int i5 = position.limit;
                if (i4 == i5) {
                    break;
                }
                z3 = i5 < i ? false : z;
                if (!z2 || !z3) {
                    int i6 = position.limit;
                    handleTransliterate(replaceable, position, z3);
                    int i7 = position.limit;
                    int i8 = i7 - i6;
                    if (z3 || position.start == i7) {
                        i += i8;
                    } else {
                        throw new RuntimeException("ERROR: Incomplete non-incremental transliteration by " + getID());
                    }
                } else {
                    int i9 = position.start;
                    int i10 = position.limit;
                    int i11 = i10 - i9;
                    int length = replaceable.length();
                    replaceable.copy(i9, i10, length);
                    int i12 = position.start;
                    int i13 = i9;
                    int i14 = length;
                    int i15 = 0;
                    int i16 = 0;
                    while (true) {
                        int charCount = UTF16.getCharCount(replaceable.char32At(i12));
                        i12 += charCount;
                        if (i12 > i10) {
                            break;
                        }
                        i15 += charCount;
                        position.limit = i12;
                        handleTransliterate(replaceable, position, true);
                        int i17 = position.limit;
                        int i18 = i17 - i12;
                        int i19 = position.start;
                        if (i19 != i17) {
                            int i20 = (i14 + i18) - (i17 - i13);
                            replaceable.replace(i13, i17, "");
                            replaceable.copy(i20, i20 + i15, i13);
                            position.start = i13;
                            position.limit = i12;
                            position.contextLimit -= i18;
                        } else {
                            i14 += i18 + i15;
                            i10 += i18;
                            i16 += i18;
                            i12 = i19;
                            i13 = i12;
                            i15 = 0;
                        }
                    }
                    int i21 = length + i16;
                    i += i16;
                    replaceable.replace(i21, i11 + i21, "");
                    position.start = i13;
                }
                if (this.filter == null) {
                    break;
                }
            } while (!z3);
            position.limit = i;
            return;
        }
        handleTransliterate(replaceable, position, z);
    }

    public void filteredTransliterate(Replaceable replaceable, Position position, boolean z) {
        filteredTransliterate(replaceable, position, z, false);
    }

    public final int getMaximumContextLength() {
        return this.maximumContextLength;
    }

    /* access modifiers changed from: protected */
    public void setMaximumContextLength(int i) {
        if (i >= 0) {
            this.maximumContextLength = i;
            return;
        }
        throw new IllegalArgumentException("Invalid context length " + i);
    }

    public final String getID() {
        return this.ID;
    }

    /* access modifiers changed from: protected */
    public final void setID(String str) {
        this.ID = str;
    }

    public final UnicodeFilter getFilter() {
        return this.filter;
    }

    public void setFilter(UnicodeFilter unicodeFilter) {
        if (unicodeFilter == null) {
            this.filter = null;
            return;
        }
        try {
            UnicodeSet unicodeSet = new UnicodeSet((UnicodeSet) unicodeFilter);
            unicodeSet.freeze();
            this.filter = unicodeSet;
        } catch (Exception unused) {
            this.filter = new UnicodeSet();
            unicodeFilter.addMatchSetTo(this.filter);
            this.filter.freeze();
        }
    }

    public static final Transliterator getInstance(String str) {
        return getInstance(str, 0);
    }

    public static Transliterator getInstance(String str, int i) {
        Transliterator transliterator;
        StringBuffer stringBuffer = new StringBuffer();
        ArrayList arrayList = new ArrayList();
        UnicodeSet[] unicodeSetArr = new UnicodeSet[1];
        if (TransliteratorIDParser.parseCompoundID(str, i, stringBuffer, arrayList, unicodeSetArr)) {
            List instantiateList = TransliteratorIDParser.instantiateList(arrayList);
            if (arrayList.size() > 1 || stringBuffer.indexOf(";") >= 0) {
                transliterator = new CompoundTransliterator(instantiateList);
            } else {
                transliterator = (Transliterator) instantiateList.get(0);
            }
            transliterator.setID(stringBuffer.toString());
            if (unicodeSetArr[0] != null) {
                transliterator.setFilter(unicodeSetArr[0]);
            }
            return transliterator;
        }
        throw new IllegalArgumentException("Invalid ID " + str);
    }

    static Transliterator getBasicInstance(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer();
        Transliterator transliterator = registry.get(str, stringBuffer);
        if (stringBuffer.length() != 0) {
            transliterator = getInstance(stringBuffer.toString(), 0);
        }
        if (!(transliterator == null || str2 == null)) {
            transliterator.setID(str2);
        }
        return transliterator;
    }

    public static void registerClass(String str, Class cls, String str2) {
        registry.put(str, cls, true);
        if (str2 != null) {
            displayNameCache.put(new CaseInsensitiveString(str), str2);
        }
    }

    public static void registerFactory(String str, Factory factory) {
        registry.put(str, factory, true);
    }

    public static void registerInstance(Transliterator transliterator) {
        registry.put(transliterator.getID(), transliterator, true);
    }

    static void registerInstance(Transliterator transliterator, boolean z) {
        registry.put(transliterator.getID(), transliterator, z);
    }

    static void registerSpecialInverse(String str, String str2, boolean z) {
        TransliteratorIDParser.registerSpecialInverse(str, str2, z);
    }

    public static final Enumeration getAvailableSources() {
        return registry.getAvailableSources();
    }

    public static final Enumeration getAvailableTargets(String str) {
        return registry.getAvailableTargets(str);
    }

    public static final Enumeration getAvailableVariants(String str, String str2) {
        return registry.getAvailableVariants(str, str2);
    }

    static {
        int i;
        UResourceBundle uResourceBundle = UResourceBundle.getBundleInstance("android/icu/impl/data/icudt63b/translit", "root").get("RuleBasedTransliteratorIDs");
        int size = uResourceBundle.getSize();
        for (int i2 = 0; i2 < size; i2++) {
            UResourceBundle uResourceBundle2 = uResourceBundle.get(i2);
            String key = uResourceBundle2.getKey();
            if (key.indexOf("-t-") < 0) {
                UResourceBundle uResourceBundle3 = uResourceBundle2.get(0);
                String key2 = uResourceBundle3.getKey();
                if (key2.equals("file") || key2.equals("internal")) {
                    String string = uResourceBundle3.getString("resource");
                    String string2 = uResourceBundle3.getString("direction");
                    char charAt = string2.charAt(0);
                    if (charAt == 'F') {
                        i = 0;
                    } else if (charAt == 'R') {
                        i = 1;
                    } else {
                        throw new RuntimeException("Can't parse direction: " + string2);
                    }
                    registry.put(key, string, i, !key2.equals("internal"));
                } else if (key2.equals("alias")) {
                    registry.put(key, uResourceBundle3.getString(), true);
                } else {
                    throw new RuntimeException("Unknow type: " + key2);
                }
            }
        }
        registerSpecialInverse("Null", "Null", false);
        registerClass("Any-Null", NullTransliterator.class, null);
        RemoveTransliterator.register();
        EscapeTransliterator.register();
        UnescapeTransliterator.register();
        LowercaseTransliterator.register();
        UppercaseTransliterator.register();
        TitlecaseTransliterator.register();
        CaseFoldTransliterator.register();
        UnicodeNameTransliterator.register();
        NameUnicodeTransliterator.register();
        NormalizationTransliterator.register();
        BreakTransliterator.register();
        AnyTransliterator.register();
    }
}
