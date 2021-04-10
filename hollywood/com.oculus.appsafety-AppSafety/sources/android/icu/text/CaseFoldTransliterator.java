package android.icu.text;

import android.icu.impl.UCaseProps;
import android.icu.lang.UCharacter;
import android.icu.text.Transliterator;

class CaseFoldTransliterator extends Transliterator {
    static final String _ID = "Any-CaseFold";
    static SourceTargetUtility sourceTargetUtility = null;
    private final UCaseProps csp = UCaseProps.INSTANCE;
    private ReplaceableContextIterator iter = new ReplaceableContextIterator();
    private StringBuilder result = new StringBuilder();

    static void register() {
        Transliterator.registerFactory(_ID, new Transliterator.Factory() {
            /* class android.icu.text.CaseFoldTransliterator.AnonymousClass1 */

            @Override // android.icu.text.Transliterator.Factory
            public Transliterator getInstance(String ID) {
                return new CaseFoldTransliterator();
            }
        });
        Transliterator.registerSpecialInverse("CaseFold", "Upper", false);
    }

    public CaseFoldTransliterator() {
        super(_ID, null);
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.Transliterator
    public synchronized void handleTransliterate(Replaceable text, Transliterator.Position offsets, boolean isIncremental) {
        int delta;
        if (this.csp != null) {
            if (offsets.start < offsets.limit) {
                this.iter.setText(text);
                this.result.setLength(0);
                this.iter.setIndex(offsets.start);
                this.iter.setLimit(offsets.limit);
                this.iter.setContextLimits(offsets.contextStart, offsets.contextLimit);
                while (true) {
                    int c = this.iter.nextCaseMapCP();
                    if (c >= 0) {
                        int c2 = this.csp.toFullFolding(c, this.result, 0);
                        if (this.iter.didReachLimit() && isIncremental) {
                            offsets.start = this.iter.getCaseMapCPStart();
                            return;
                        } else if (c2 >= 0) {
                            if (c2 <= 31) {
                                delta = this.iter.replace(this.result.toString());
                                this.result.setLength(0);
                            } else {
                                delta = this.iter.replace(UTF16.valueOf(c2));
                            }
                            if (delta != 0) {
                                offsets.limit += delta;
                                offsets.contextLimit += delta;
                            }
                        }
                    } else {
                        offsets.start = offsets.limit;
                        return;
                    }
                }
            }
        }
    }

    @Override // android.icu.text.Transliterator
    public void addSourceTargetSet(UnicodeSet inputFilter, UnicodeSet sourceSet, UnicodeSet targetSet) {
        synchronized (UppercaseTransliterator.class) {
            if (sourceTargetUtility == null) {
                sourceTargetUtility = new SourceTargetUtility(new Transform<String, String>() {
                    /* class android.icu.text.CaseFoldTransliterator.AnonymousClass2 */

                    public String transform(String source) {
                        return UCharacter.foldCase(source, true);
                    }
                });
            }
        }
        sourceTargetUtility.addSourceTargetSet(this, inputFilter, sourceSet, targetSet);
    }
}
