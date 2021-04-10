package android.icu.text;

import android.icu.impl.UCaseProps;
import android.icu.text.Transliterator;
import android.icu.util.ULocale;

class UppercaseTransliterator extends Transliterator {
    private int caseLocale;
    private final UCaseProps csp;
    private ReplaceableContextIterator iter;
    private final ULocale locale;
    private StringBuilder result;
    SourceTargetUtility sourceTargetUtility = null;

    static void register() {
        Transliterator.registerFactory("Any-Upper", new Transliterator.Factory() {
            /* class android.icu.text.UppercaseTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new UppercaseTransliterator(ULocale.US);
            }
        });
    }

    public UppercaseTransliterator(ULocale uLocale) {
        super("Any-Upper", null);
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
                        int fullUpper = this.csp.toFullUpper(nextCaseMapCP, this.iter, this.result, this.caseLocale);
                        if (this.iter.didReachLimit() && z) {
                            position.start = this.iter.getCaseMapCPStart();
                            return;
                        } else if (fullUpper >= 0) {
                            if (fullUpper <= 31) {
                                i = this.iter.replace(this.result.toString());
                                this.result.setLength(0);
                            } else {
                                i = this.iter.replace(UTF16.valueOf(fullUpper));
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
