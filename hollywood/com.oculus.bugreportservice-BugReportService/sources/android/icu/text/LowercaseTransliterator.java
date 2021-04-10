package android.icu.text;

import android.icu.impl.UCaseProps;
import android.icu.text.Transliterator;
import android.icu.util.ULocale;

class LowercaseTransliterator extends Transliterator {
    private int caseLocale;
    private final UCaseProps csp;
    private ReplaceableContextIterator iter;
    private final ULocale locale;
    private StringBuilder result;
    SourceTargetUtility sourceTargetUtility = null;

    static void register() {
        Transliterator.registerFactory("Any-Lower", new Transliterator.Factory() {
            /* class android.icu.text.LowercaseTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new LowercaseTransliterator(ULocale.US);
            }
        });
        Transliterator.registerSpecialInverse("Lower", "Upper", true);
    }

    public LowercaseTransliterator(ULocale uLocale) {
        super("Any-Lower", null);
        this.locale = uLocale;
        this.csp = UCaseProps.INSTANCE;
        this.iter = new ReplaceableContextIterator();
        this.result = new StringBuilder();
        this.caseLocale = UCaseProps.getCaseLocale(this.locale);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public synchronized void handleTransliterate(Replaceable replaceable, Transliterator.Position position, boolean z) {
        int i;
        if (this.csp != null) {
            if (position.start < position.limit) {
                this.iter.setText(replaceable);
                this.result.setLength(0);
                this.iter.setIndex(position.start);
                this.iter.setLimit(position.limit);
                this.iter.setContextLimits(position.contextStart, position.contextLimit);
                while (true) {
                    int nextCaseMapCP = this.iter.nextCaseMapCP();
                    if (nextCaseMapCP >= 0) {
                        int fullLower = this.csp.toFullLower(nextCaseMapCP, this.iter, this.result, this.caseLocale);
                        if (this.iter.didReachLimit() && z) {
                            position.start = this.iter.getCaseMapCPStart();
                            return;
                        } else if (fullLower >= 0) {
                            if (fullLower <= 31) {
                                i = this.iter.replace(this.result.toString());
                                this.result.setLength(0);
                            } else {
                                i = this.iter.replace(UTF16.valueOf(fullLower));
                            }
                            if (i != 0) {
                                position.limit += i;
                                position.contextLimit += i;
                            }
                        }
                    } else {
                        position.start = position.limit;
                        return;
                    }
                }
            }
        }
    }
}
