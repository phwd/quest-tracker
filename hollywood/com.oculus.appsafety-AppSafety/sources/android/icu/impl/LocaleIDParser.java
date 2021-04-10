package android.icu.impl;

import android.icu.impl.locale.AsciiUtil;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public final class LocaleIDParser {
    private static final char COMMA = ',';
    private static final char DONE = 65535;
    private static final char DOT = '.';
    private static final char HYPHEN = '-';
    private static final char ITEM_SEPARATOR = ';';
    private static final char KEYWORD_ASSIGN = '=';
    private static final char KEYWORD_SEPARATOR = '@';
    private static final char UNDERSCORE = '_';
    String baseName;
    private StringBuilder buffer;
    private boolean canonicalize;
    private boolean hadCountry;
    private char[] id;
    private int index;
    Map<String, String> keywords;

    public LocaleIDParser(String localeID) {
        this(localeID, false);
    }

    public LocaleIDParser(String localeID, boolean canonicalize2) {
        this.id = localeID.toCharArray();
        this.index = 0;
        this.buffer = new StringBuilder(this.id.length + 5);
        this.canonicalize = canonicalize2;
    }

    private void reset() {
        this.index = 0;
        this.buffer = new StringBuilder(this.id.length + 5);
    }

    private void append(char c) {
        this.buffer.append(c);
    }

    private void addSeparator() {
        append(UNDERSCORE);
    }

    private String getString(int start) {
        return this.buffer.substring(start);
    }

    private void set(int pos, String s) {
        StringBuilder sb = this.buffer;
        sb.delete(pos, sb.length());
        this.buffer.insert(pos, s);
    }

    private void append(String s) {
        this.buffer.append(s);
    }

    private char next() {
        int i = this.index;
        char[] cArr = this.id;
        if (i == cArr.length) {
            this.index = i + 1;
            return 65535;
        }
        this.index = i + 1;
        return cArr[i];
    }

    private void skipUntilTerminatorOrIDSeparator() {
        do {
        } while (!isTerminatorOrIDSeparator(next()));
        this.index--;
    }

    private boolean atTerminator() {
        int i = this.index;
        char[] cArr = this.id;
        return i >= cArr.length || isTerminator(cArr[i]);
    }

    private boolean isTerminator(char c) {
        return c == '@' || c == 65535 || c == '.';
    }

    private boolean isTerminatorOrIDSeparator(char c) {
        return c == '_' || c == '-' || isTerminator(c);
    }

    private boolean haveExperimentalLanguagePrefix() {
        char c;
        char[] cArr = this.id;
        if (cArr.length <= 2 || ((c = cArr[1]) != '-' && c != '_')) {
            return false;
        }
        char c2 = this.id[0];
        if (c2 == 'x' || c2 == 'X' || c2 == 'i' || c2 == 'I') {
            return true;
        }
        return false;
    }

    private boolean haveKeywordAssign() {
        int i = this.index;
        while (true) {
            char[] cArr = this.id;
            if (i >= cArr.length) {
                return false;
            }
            if (cArr[i] == '=') {
                return true;
            }
            i++;
        }
    }

    private int parseLanguage() {
        String lang;
        int startLength = this.buffer.length();
        if (haveExperimentalLanguagePrefix()) {
            append(AsciiUtil.toLower(this.id[0]));
            append(HYPHEN);
            this.index = 2;
        }
        while (true) {
            char c = next();
            if (isTerminatorOrIDSeparator(c)) {
                break;
            }
            append(AsciiUtil.toLower(c));
        }
        this.index--;
        if (this.buffer.length() - startLength == 3 && (lang = LocaleIDs.threeToTwoLetterLanguage(getString(0))) != null) {
            set(0, lang);
        }
        return 0;
    }

    private void skipLanguage() {
        if (haveExperimentalLanguagePrefix()) {
            this.index = 2;
        }
        skipUntilTerminatorOrIDSeparator();
    }

    private int parseScript() {
        if (atTerminator()) {
            return this.buffer.length();
        }
        int oldIndex = this.index;
        this.index++;
        int oldBlen = this.buffer.length();
        boolean firstPass = true;
        while (true) {
            char c = next();
            if (isTerminatorOrIDSeparator(c) || !AsciiUtil.isAlpha(c)) {
                this.index--;
            } else if (firstPass) {
                addSeparator();
                append(AsciiUtil.toUpper(c));
                firstPass = false;
            } else {
                append(AsciiUtil.toLower(c));
            }
        }
        this.index--;
        if (this.index - oldIndex == 5) {
            return oldBlen + 1;
        }
        this.index = oldIndex;
        StringBuilder sb = this.buffer;
        sb.delete(oldBlen, sb.length());
        return oldBlen;
    }

    private void skipScript() {
        char c;
        if (!atTerminator()) {
            int oldIndex = this.index;
            this.index++;
            do {
                c = next();
                if (isTerminatorOrIDSeparator(c)) {
                    break;
                }
            } while (AsciiUtil.isAlpha(c));
            this.index--;
            if (this.index - oldIndex != 5) {
                this.index = oldIndex;
            }
        }
    }

    private int parseCountry() {
        String region;
        if (atTerminator()) {
            return this.buffer.length();
        }
        int oldIndex = this.index;
        this.index++;
        int oldBlen = this.buffer.length();
        boolean firstPass = true;
        while (true) {
            char c = next();
            if (isTerminatorOrIDSeparator(c)) {
                break;
            }
            if (firstPass) {
                this.hadCountry = true;
                addSeparator();
                oldBlen++;
                firstPass = false;
            }
            append(AsciiUtil.toUpper(c));
        }
        this.index--;
        int charsAppended = this.buffer.length() - oldBlen;
        if (charsAppended == 0) {
            return oldBlen;
        }
        if (charsAppended < 2 || charsAppended > 3) {
            this.index = oldIndex;
            int oldBlen2 = oldBlen - 1;
            StringBuilder sb = this.buffer;
            sb.delete(oldBlen2, sb.length());
            this.hadCountry = false;
            return oldBlen2;
        } else if (charsAppended != 3 || (region = LocaleIDs.threeToTwoLetterRegion(getString(oldBlen))) == null) {
            return oldBlen;
        } else {
            set(oldBlen, region);
            return oldBlen;
        }
    }

    private void skipCountry() {
        if (!atTerminator()) {
            char[] cArr = this.id;
            int i = this.index;
            if (cArr[i] == '_' || cArr[i] == '-') {
                this.index++;
            }
            int oldIndex = this.index;
            skipUntilTerminatorOrIDSeparator();
            int charsSkipped = this.index - oldIndex;
            if (charsSkipped < 2 || charsSkipped > 3) {
                this.index = oldIndex;
            }
        }
    }

    private int parseVariant() {
        int oldBlen = this.buffer.length();
        boolean start = true;
        boolean needSeparator = true;
        boolean skipping = false;
        boolean firstPass = true;
        while (true) {
            char c = next();
            if (c == 65535) {
                break;
            } else if (c == '.') {
                start = false;
                skipping = true;
            } else if (c == '@') {
                if (haveKeywordAssign()) {
                    break;
                }
                skipping = false;
                start = false;
                needSeparator = true;
            } else if (start) {
                start = false;
                if (!(c == '_' || c == '-')) {
                    this.index--;
                }
            } else if (!skipping) {
                if (needSeparator) {
                    needSeparator = false;
                    if (firstPass && !this.hadCountry) {
                        addSeparator();
                        oldBlen++;
                    }
                    addSeparator();
                    if (firstPass) {
                        oldBlen++;
                        firstPass = false;
                    }
                }
                char c2 = AsciiUtil.toUpper(c);
                if (c2 == '-' || c2 == ',') {
                    c2 = UNDERSCORE;
                }
                append(c2);
            }
        }
        this.index--;
        return oldBlen;
    }

    public String getLanguage() {
        reset();
        return getString(parseLanguage());
    }

    public String getScript() {
        reset();
        skipLanguage();
        return getString(parseScript());
    }

    public String getCountry() {
        reset();
        skipLanguage();
        skipScript();
        return getString(parseCountry());
    }

    public String getVariant() {
        reset();
        skipLanguage();
        skipScript();
        skipCountry();
        return getString(parseVariant());
    }

    public String[] getLanguageScriptCountryVariant() {
        reset();
        return new String[]{getString(parseLanguage()), getString(parseScript()), getString(parseCountry()), getString(parseVariant())};
    }

    public void setBaseName(String baseName2) {
        this.baseName = baseName2;
    }

    public void parseBaseName() {
        String str = this.baseName;
        if (str != null) {
            set(0, str);
            return;
        }
        reset();
        parseLanguage();
        parseScript();
        parseCountry();
        parseVariant();
        int len = this.buffer.length();
        if (len > 0 && this.buffer.charAt(len - 1) == '_') {
            this.buffer.deleteCharAt(len - 1);
        }
    }

    public String getBaseName() {
        String str = this.baseName;
        if (str != null) {
            return str;
        }
        parseBaseName();
        return getString(0);
    }

    public String getName() {
        parseBaseName();
        parseKeywords();
        return getString(0);
    }

    private boolean setToKeywordStart() {
        int i = this.index;
        while (true) {
            char[] cArr = this.id;
            if (i >= cArr.length) {
                return false;
            }
            if (cArr[i] != '@') {
                i++;
            } else if (this.canonicalize) {
                int i2 = i + 1;
                int j = i2;
                while (true) {
                    char[] cArr2 = this.id;
                    if (j >= cArr2.length) {
                        return false;
                    }
                    if (cArr2[j] == '=') {
                        this.index = i2;
                        return true;
                    }
                    j++;
                }
            } else {
                int i3 = i + 1;
                if (i3 >= cArr.length) {
                    return false;
                }
                this.index = i3;
                return true;
            }
        }
    }

    private static boolean isDoneOrKeywordAssign(char c) {
        return c == 65535 || c == '=';
    }

    private static boolean isDoneOrItemSeparator(char c) {
        return c == 65535 || c == ';';
    }

    private String getKeyword() {
        int start = this.index;
        do {
        } while (!isDoneOrKeywordAssign(next()));
        this.index--;
        return AsciiUtil.toLowerString(new String(this.id, start, this.index - start).trim());
    }

    private String getValue() {
        int start = this.index;
        do {
        } while (!isDoneOrItemSeparator(next()));
        this.index--;
        return new String(this.id, start, this.index - start).trim();
    }

    private Comparator<String> getKeyComparator() {
        return new Comparator<String>() {
            /* class android.icu.impl.LocaleIDParser.AnonymousClass1 */

            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        };
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0040, code lost:
        if (r0.containsKey(r1) != false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, java.lang.String> getKeywordMap() {
        /*
            r6 = this;
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.keywords
            if (r0 != 0) goto L_0x0058
            r0 = 0
            boolean r1 = r6.setToKeywordStart()
            if (r1 == 0) goto L_0x004e
        L_0x000b:
            java.lang.String r1 = r6.getKeyword()
            int r2 = r1.length()
            if (r2 != 0) goto L_0x0016
            goto L_0x004e
        L_0x0016:
            char r2 = r6.next()
            r3 = 61
            if (r2 == r3) goto L_0x0024
            r3 = 65535(0xffff, float:9.1834E-41)
            if (r2 != r3) goto L_0x0046
            goto L_0x004e
        L_0x0024:
            java.lang.String r3 = r6.getValue()
            int r4 = r3.length()
            if (r4 != 0) goto L_0x002f
            goto L_0x0046
        L_0x002f:
            if (r0 != 0) goto L_0x003c
            java.util.TreeMap r4 = new java.util.TreeMap
            java.util.Comparator r5 = r6.getKeyComparator()
            r4.<init>(r5)
            r0 = r4
            goto L_0x0043
        L_0x003c:
            boolean r4 = r0.containsKey(r1)
            if (r4 == 0) goto L_0x0043
            goto L_0x0046
        L_0x0043:
            r0.put(r1, r3)
        L_0x0046:
            char r1 = r6.next()
            r2 = 59
            if (r1 == r2) goto L_0x000b
        L_0x004e:
            if (r0 == 0) goto L_0x0052
            r1 = r0
            goto L_0x0056
        L_0x0052:
            java.util.Map r1 = java.util.Collections.emptyMap()
        L_0x0056:
            r6.keywords = r1
        L_0x0058:
            java.util.Map<java.lang.String, java.lang.String> r0 = r6.keywords
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.LocaleIDParser.getKeywordMap():java.util.Map");
    }

    private int parseKeywords() {
        int oldBlen = this.buffer.length();
        Map<String, String> m = getKeywordMap();
        if (m.isEmpty()) {
            return oldBlen;
        }
        boolean first = true;
        for (Map.Entry<String, String> e : m.entrySet()) {
            append(first ? KEYWORD_SEPARATOR : ITEM_SEPARATOR);
            first = false;
            append(e.getKey());
            append(KEYWORD_ASSIGN);
            append(e.getValue());
        }
        if (!first) {
            return oldBlen + 1;
        }
        return oldBlen;
    }

    public Iterator<String> getKeywords() {
        Map<String, String> m = getKeywordMap();
        if (m.isEmpty()) {
            return null;
        }
        return m.keySet().iterator();
    }

    public String getKeywordValue(String keywordName) {
        Map<String, String> m = getKeywordMap();
        if (m.isEmpty()) {
            return null;
        }
        return m.get(AsciiUtil.toLowerString(keywordName.trim()));
    }

    public void defaultKeywordValue(String keywordName, String value) {
        setKeywordValue(keywordName, value, false);
    }

    public void setKeywordValue(String keywordName, String value) {
        setKeywordValue(keywordName, value, true);
    }

    private void setKeywordValue(String keywordName, String value, boolean reset) {
        if (keywordName != null) {
            String keywordName2 = AsciiUtil.toLowerString(keywordName.trim());
            if (keywordName2.length() != 0) {
                if (value != null) {
                    value = value.trim();
                    if (value.length() == 0) {
                        throw new IllegalArgumentException("value must not be empty");
                    }
                }
                Map<String, String> m = getKeywordMap();
                if (m.isEmpty()) {
                    if (value != null) {
                        this.keywords = new TreeMap(getKeyComparator());
                        this.keywords.put(keywordName2, value.trim());
                    }
                } else if (!reset && m.containsKey(keywordName2)) {
                } else {
                    if (value != null) {
                        m.put(keywordName2, value);
                        return;
                    }
                    m.remove(keywordName2);
                    if (m.isEmpty()) {
                        this.keywords = Collections.emptyMap();
                    }
                }
            } else {
                throw new IllegalArgumentException("keyword must not be empty");
            }
        } else if (reset) {
            this.keywords = Collections.emptyMap();
        }
    }
}
