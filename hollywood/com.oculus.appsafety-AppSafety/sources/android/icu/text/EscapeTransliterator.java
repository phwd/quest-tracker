package android.icu.text;

import android.icu.impl.Utility;
import android.icu.text.Transliterator;

class EscapeTransliterator extends Transliterator {
    private boolean grokSupplementals;
    private int minDigits;
    private String prefix;
    private int radix;
    private String suffix;
    private EscapeTransliterator supplementalHandler;

    static void register() {
        Transliterator.registerFactory("Any-Hex/Unicode", new Transliterator.Factory() {
            /* class android.icu.text.EscapeTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new EscapeTransliterator("Any-Hex/Unicode", "U+", "", 16, 4, true, null);
            }
        });
        Transliterator.registerFactory("Any-Hex/Java", new Transliterator.Factory() {
            /* class android.icu.text.EscapeTransliterator.AnonymousClass2 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new EscapeTransliterator("Any-Hex/Java", "\\u", "", 16, 4, false, null);
            }
        });
        Transliterator.registerFactory("Any-Hex/C", new Transliterator.Factory() {
            /* class android.icu.text.EscapeTransliterator.AnonymousClass3 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new EscapeTransliterator("Any-Hex/C", "\\u", "", 16, 4, true, new EscapeTransliterator("", "\\U", "", 16, 8, true, null));
            }
        });
        Transliterator.registerFactory("Any-Hex/XML", new Transliterator.Factory() {
            /* class android.icu.text.EscapeTransliterator.AnonymousClass4 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new EscapeTransliterator("Any-Hex/XML", "&#x", ";", 16, 1, true, null);
            }
        });
        Transliterator.registerFactory("Any-Hex/XML10", new Transliterator.Factory() {
            /* class android.icu.text.EscapeTransliterator.AnonymousClass5 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new EscapeTransliterator("Any-Hex/XML10", "&#", ";", 10, 1, true, null);
            }
        });
        Transliterator.registerFactory("Any-Hex/Perl", new Transliterator.Factory() {
            /* class android.icu.text.EscapeTransliterator.AnonymousClass6 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new EscapeTransliterator("Any-Hex/Perl", "\\x{", "}", 16, 1, true, null);
            }
        });
        Transliterator.registerFactory("Any-Hex/Plain", new Transliterator.Factory() {
            /* class android.icu.text.EscapeTransliterator.AnonymousClass7 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new EscapeTransliterator("Any-Hex/Plain", "", "", 16, 4, true, null);
            }
        });
        Transliterator.registerFactory("Any-Hex", new Transliterator.Factory() {
            /* class android.icu.text.EscapeTransliterator.AnonymousClass8 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new EscapeTransliterator("Any-Hex", "\\u", "", 16, 4, false, null);
            }
        });
    }

    EscapeTransliterator(String ID, String prefix2, String suffix2, int radix2, int minDigits2, boolean grokSupplementals2, EscapeTransliterator supplementalHandler2) {
        super(ID, null);
        this.prefix = prefix2;
        this.suffix = suffix2;
        this.radix = radix2;
        this.minDigits = minDigits2;
        this.grokSupplementals = grokSupplementals2;
        this.supplementalHandler = supplementalHandler2;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public void handleTransliterate(Replaceable text, Transliterator.Position pos, boolean incremental) {
        int start = pos.start;
        int limit = pos.limit;
        StringBuilder buf = new StringBuilder(this.prefix);
        int prefixLen = this.prefix.length();
        boolean redoPrefix = false;
        while (start < limit) {
            int c = this.grokSupplementals ? text.char32At(start) : text.charAt(start);
            int charLen = this.grokSupplementals ? UTF16.getCharCount(c) : 1;
            if ((-65536 & c) == 0 || this.supplementalHandler == null) {
                if (redoPrefix) {
                    buf.setLength(0);
                    buf.append(this.prefix);
                    redoPrefix = false;
                } else {
                    buf.setLength(prefixLen);
                }
                Utility.appendNumber(buf, c, this.radix, this.minDigits);
                buf.append(this.suffix);
            } else {
                buf.setLength(0);
                buf.append(this.supplementalHandler.prefix);
                EscapeTransliterator escapeTransliterator = this.supplementalHandler;
                Utility.appendNumber(buf, c, escapeTransliterator.radix, escapeTransliterator.minDigits);
                buf.append(this.supplementalHandler.suffix);
                redoPrefix = true;
            }
            text.replace(start, start + charLen, buf.toString());
            start += buf.length();
            limit += buf.length() - charLen;
        }
        pos.contextLimit += limit - pos.limit;
        pos.limit = limit;
        pos.start = start;
    }

    @Override // android.icu.text.Transliterator
    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        sourceSet.addAll(getFilterAsUnicodeSet(inputFilter));
        for (EscapeTransliterator it = this; it != null; it = it.supplementalHandler) {
            if (inputFilter.size() != 0) {
                targetSet.addAll(it.prefix);
                targetSet.addAll(it.suffix);
                StringBuilder buffer = new StringBuilder();
                int i = 0;
                while (true) {
                    int i2 = it.radix;
                    if (i >= i2) {
                        break;
                    }
                    Utility.appendNumber(buffer, i, i2, it.minDigits);
                    i++;
                }
                targetSet.addAll(buffer.toString());
            }
        }
    }
}
