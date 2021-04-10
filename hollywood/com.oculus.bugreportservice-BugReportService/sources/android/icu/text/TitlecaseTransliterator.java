package android.icu.text;

import android.icu.impl.UCaseProps;
import android.icu.text.Transliterator;
import android.icu.util.ULocale;

class TitlecaseTransliterator extends Transliterator {
    private int caseLocale;
    private final UCaseProps csp;
    private ReplaceableContextIterator iter;
    private final ULocale locale;
    private StringBuilder result;
    SourceTargetUtility sourceTargetUtility = null;

    static void register() {
        Transliterator.registerFactory("Any-Title", new Transliterator.Factory() {
            /* class android.icu.text.TitlecaseTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new TitlecaseTransliterator(ULocale.US);
            }
        });
        Transliterator.registerSpecialInverse("Title", "Lower", false);
    }

    public TitlecaseTransliterator(ULocale uLocale) {
        super("Any-Title", null);
        this.locale = uLocale;
        setMaximumContextLength(2);
        this.csp = UCaseProps.INSTANCE;
        this.iter = new ReplaceableContextIterator();
        this.result = new StringBuilder();
        this.caseLocale = UCaseProps.getCaseLocale(this.locale);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0029, code lost:
        r0 = true;
     */
    @Override // android.icu.text.Transliterator
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void handleTransliterate(android.icu.text.Replaceable r8, android.icu.text.Transliterator.Position r9, boolean r10) {
        /*
        // Method dump skipped, instructions count: 200
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.TitlecaseTransliterator.handleTransliterate(android.icu.text.Replaceable, android.icu.text.Transliterator$Position, boolean):void");
    }
}
