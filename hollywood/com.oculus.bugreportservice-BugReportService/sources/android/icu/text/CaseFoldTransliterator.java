package android.icu.text;

import android.icu.impl.UCaseProps;
import android.icu.text.Transliterator;

class CaseFoldTransliterator extends Transliterator {
    private final UCaseProps csp = UCaseProps.INSTANCE;
    private ReplaceableContextIterator iter = new ReplaceableContextIterator();
    private StringBuilder result = new StringBuilder();

    static void register() {
        Transliterator.registerFactory("Any-CaseFold", new Transliterator.Factory() {
            /* class android.icu.text.CaseFoldTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String str) {
                return new CaseFoldTransliterator();
            }
        });
        Transliterator.registerSpecialInverse("CaseFold", "Upper", false);
    }

    public CaseFoldTransliterator() {
        super("Any-CaseFold", null);
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
                        int fullFolding = this.csp.toFullFolding(nextCaseMapCP, this.result, 0);
                        if (this.iter.didReachLimit() && z) {
                            position.start = this.iter.getCaseMapCPStart();
                            return;
                        } else if (fullFolding >= 0) {
                            if (fullFolding <= 31) {
                                i = this.iter.replace(this.result.toString());
                                this.result.setLength(0);
                            } else {
                                i = this.iter.replace(UTF16.valueOf(fullFolding));
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
