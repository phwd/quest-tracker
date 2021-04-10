package android.icu.text;

import android.icu.impl.coll.Collation;
import android.icu.text.SearchIterator;
import android.icu.util.ULocale;
import android.support.v4.view.InputDeviceCompat;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Locale;

public final class StringSearch extends SearchIterator {
    private static int CE_LEVEL2_BASE = 5;
    private static int CE_LEVEL3_BASE = 327680;
    private static final int CE_MATCH = -1;
    private static final int CE_NO_MATCH = 0;
    private static final int CE_SKIP_PATN = 2;
    private static final int CE_SKIP_TARG = 1;
    private static final int INITIAL_ARRAY_SIZE_ = 256;
    private static final int PRIMARYORDERMASK = -65536;
    private static final int SECONDARYORDERMASK = 65280;
    private static final int TERTIARYORDERMASK = 255;
    int ceMask_;
    private RuleBasedCollator collator_;
    private Normalizer2 nfd_;
    private Pattern pattern_;
    private int strength_;
    private CollationElementIterator textIter_;
    private CollationPCE textProcessedIter_;
    private boolean toShift_;
    private CollationElementIterator utilIter_;
    int variableTop_;

    public StringSearch(String pattern, CharacterIterator target, RuleBasedCollator collator, BreakIterator breakiter) {
        super(target, breakiter);
        if (!collator.getNumericCollation()) {
            this.collator_ = collator;
            this.strength_ = collator.getStrength();
            this.ceMask_ = getMask(this.strength_);
            this.toShift_ = collator.isAlternateHandlingShifted();
            this.variableTop_ = collator.getVariableTop();
            this.nfd_ = Normalizer2.getNFDInstance();
            this.pattern_ = new Pattern(pattern);
            this.search_.setMatchedLength(0);
            this.search_.matchedIndex_ = -1;
            this.utilIter_ = null;
            this.textIter_ = new CollationElementIterator(target, collator);
            this.textProcessedIter_ = null;
            ULocale collLocale = collator.getLocale(ULocale.VALID_LOCALE);
            this.search_.internalBreakIter_ = BreakIterator.getCharacterInstance(collLocale == null ? ULocale.ROOT : collLocale);
            this.search_.internalBreakIter_.setText((CharacterIterator) target.clone());
            initialize();
            return;
        }
        throw new UnsupportedOperationException("Numeric collation is not supported by StringSearch");
    }

    public StringSearch(String pattern, CharacterIterator target, RuleBasedCollator collator) {
        this(pattern, target, collator, null);
    }

    public StringSearch(String pattern, CharacterIterator target, Locale locale) {
        this(pattern, target, ULocale.forLocale(locale));
    }

    public StringSearch(String pattern, CharacterIterator target, ULocale locale) {
        this(pattern, target, (RuleBasedCollator) Collator.getInstance(locale), null);
    }

    public StringSearch(String pattern, String target) {
        this(pattern, new StringCharacterIterator(target), (RuleBasedCollator) Collator.getInstance(), null);
    }

    public RuleBasedCollator getCollator() {
        return this.collator_;
    }

    public void setCollator(RuleBasedCollator collator) {
        if (collator != null) {
            this.collator_ = collator;
            this.ceMask_ = getMask(this.collator_.getStrength());
            ULocale collLocale = collator.getLocale(ULocale.VALID_LOCALE);
            this.search_.internalBreakIter_ = BreakIterator.getCharacterInstance(collLocale == null ? ULocale.ROOT : collLocale);
            this.search_.internalBreakIter_.setText((CharacterIterator) this.search_.text().clone());
            this.toShift_ = collator.isAlternateHandlingShifted();
            this.variableTop_ = collator.getVariableTop();
            this.textIter_ = new CollationElementIterator(this.pattern_.text_, collator);
            this.utilIter_ = new CollationElementIterator(this.pattern_.text_, collator);
            initialize();
            return;
        }
        throw new IllegalArgumentException("Collator can not be null");
    }

    public String getPattern() {
        return this.pattern_.text_;
    }

    public void setPattern(String pattern) {
        if (pattern == null || pattern.length() <= 0) {
            throw new IllegalArgumentException("Pattern to search for can not be null or of length 0");
        }
        this.pattern_.text_ = pattern;
        initialize();
    }

    public boolean isCanonical() {
        return this.search_.isCanonicalMatch_;
    }

    public void setCanonical(boolean allowCanonical) {
        this.search_.isCanonicalMatch_ = allowCanonical;
    }

    @Override // android.icu.text.SearchIterator
    public void setTarget(CharacterIterator text) {
        super.setTarget(text);
        this.textIter_.setText(text);
    }

    @Override // android.icu.text.SearchIterator
    public int getIndex() {
        int result = this.textIter_.getOffset();
        if (isOutOfBounds(this.search_.beginIndex(), this.search_.endIndex(), result)) {
            return -1;
        }
        return result;
    }

    @Override // android.icu.text.SearchIterator
    public void setIndex(int position) {
        super.setIndex(position);
        this.textIter_.setOffset(position);
    }

    @Override // android.icu.text.SearchIterator
    public void reset() {
        boolean sameCollAttribute = true;
        int newStrength = this.collator_.getStrength();
        if ((this.strength_ < 3 && newStrength >= 3) || (this.strength_ >= 3 && newStrength < 3)) {
            sameCollAttribute = false;
        }
        this.strength_ = this.collator_.getStrength();
        int ceMask = getMask(this.strength_);
        if (this.ceMask_ != ceMask) {
            this.ceMask_ = ceMask;
            sameCollAttribute = false;
        }
        boolean shift = this.collator_.isAlternateHandlingShifted();
        if (this.toShift_ != shift) {
            this.toShift_ = shift;
            sameCollAttribute = false;
        }
        int varTop = this.collator_.getVariableTop();
        if (this.variableTop_ != varTop) {
            this.variableTop_ = varTop;
            sameCollAttribute = false;
        }
        if (!sameCollAttribute) {
            initialize();
        }
        this.textIter_.setText(this.search_.text());
        this.search_.setMatchedLength(0);
        this.search_.matchedIndex_ = -1;
        this.search_.isOverlap_ = false;
        this.search_.isCanonicalMatch_ = false;
        this.search_.elementComparisonType_ = SearchIterator.ElementComparisonType.STANDARD_ELEMENT_COMPARISON;
        this.search_.isForwardSearching_ = true;
        this.search_.reset_ = true;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.SearchIterator
    public int handleNext(int position) {
        if (this.pattern_.CELength_ == 0) {
            this.search_.matchedIndex_ = this.search_.matchedIndex_ == -1 ? getIndex() : this.search_.matchedIndex_ + 1;
            this.search_.setMatchedLength(0);
            this.textIter_.setOffset(this.search_.matchedIndex_);
            if (this.search_.matchedIndex_ == this.search_.endIndex()) {
                this.search_.matchedIndex_ = -1;
            }
            return -1;
        }
        if (this.search_.matchedLength() <= 0) {
            this.search_.matchedIndex_ = position - 1;
        }
        this.textIter_.setOffset(position);
        if (this.search_.isCanonicalMatch_) {
            handleNextCanonical();
        } else {
            handleNextExact();
        }
        if (this.search_.matchedIndex_ == -1) {
            this.textIter_.setOffset(this.search_.endIndex());
        } else {
            this.textIter_.setOffset(this.search_.matchedIndex_);
        }
        return this.search_.matchedIndex_;
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.SearchIterator
    public int handlePrevious(int position) {
        if (this.pattern_.CELength_ == 0) {
            this.search_.matchedIndex_ = this.search_.matchedIndex_ == -1 ? getIndex() : this.search_.matchedIndex_;
            if (this.search_.matchedIndex_ == this.search_.beginIndex()) {
                setMatchNotFound();
            } else {
                SearchIterator.Search search = this.search_;
                search.matchedIndex_--;
                this.textIter_.setOffset(this.search_.matchedIndex_);
                this.search_.setMatchedLength(0);
            }
        } else {
            this.textIter_.setOffset(position);
            if (this.search_.isCanonicalMatch_) {
                handlePreviousCanonical();
            } else {
                handlePreviousExact();
            }
        }
        return this.search_.matchedIndex_;
    }

    private static int getMask(int strength) {
        if (strength == 0) {
            return -65536;
        }
        if (strength != 1) {
            return -1;
        }
        return InputDeviceCompat.SOURCE_ANY;
    }

    private int getCE(int sourcece) {
        int sourcece2 = sourcece & this.ceMask_;
        if (this.toShift_) {
            if (this.variableTop_ <= sourcece2) {
                return sourcece2;
            }
            if (this.strength_ >= 3) {
                return sourcece2 & -65536;
            }
            return 0;
        } else if (this.strength_ < 3 || sourcece2 != 0) {
            return sourcece2;
        } else {
            return 65535;
        }
    }

    private static int[] addToIntArray(int[] destination, int offset, int value, int increments) {
        int newlength = destination.length;
        if (offset + 1 == newlength) {
            int[] temp = new int[(newlength + increments)];
            System.arraycopy((Object) destination, 0, (Object) temp, 0, offset);
            destination = temp;
        }
        destination[offset] = value;
        return destination;
    }

    private static long[] addToLongArray(long[] destination, int offset, int destinationlength, long value, int increments) {
        if (offset + 1 == destinationlength) {
            long[] temp = new long[(destinationlength + increments)];
            System.arraycopy((Object) destination, 0, (Object) temp, 0, offset);
            destination = temp;
        }
        destination[offset] = value;
        return destination;
    }

    private int initializePatternCETable() {
        int[] cetable = new int[256];
        int patternlength = this.pattern_.text_.length();
        CollationElementIterator coleiter = this.utilIter_;
        if (coleiter == null) {
            coleiter = new CollationElementIterator(this.pattern_.text_, this.collator_);
            this.utilIter_ = coleiter;
        } else {
            coleiter.setText(this.pattern_.text_);
        }
        int offset = 0;
        int result = 0;
        while (true) {
            int ce = coleiter.next();
            if (ce != -1) {
                int newce = getCE(ce);
                if (newce != 0) {
                    int[] temp = addToIntArray(cetable, offset, newce, (patternlength - coleiter.getOffset()) + 1);
                    offset++;
                    cetable = temp;
                }
                result += coleiter.getMaxExpansion(ce) - 1;
            } else {
                cetable[offset] = 0;
                Pattern pattern = this.pattern_;
                pattern.CE_ = cetable;
                pattern.CELength_ = offset;
                return result;
            }
        }
    }

    private int initializePatternPCETable() {
        CollationElementIterator coleiter;
        long[] pcetable = new long[256];
        int pcetablesize = pcetable.length;
        int patternlength = this.pattern_.text_.length();
        CollationElementIterator coleiter2 = this.utilIter_;
        if (coleiter2 == null) {
            CollationElementIterator coleiter3 = new CollationElementIterator(this.pattern_.text_, this.collator_);
            this.utilIter_ = coleiter3;
            coleiter = coleiter3;
        } else {
            coleiter2.setText(this.pattern_.text_);
            coleiter = coleiter2;
        }
        CollationPCE iter = new CollationPCE(coleiter);
        int offset = 0;
        while (true) {
            long pce = iter.nextProcessed(null);
            if (pce != -1) {
                long[] temp = addToLongArray(pcetable, offset, pcetablesize, pce, (patternlength - coleiter.getOffset()) + 1);
                offset++;
                pcetable = temp;
            } else {
                pcetable[offset] = 0;
                Pattern pattern = this.pattern_;
                pattern.PCE_ = pcetable;
                pattern.PCELength_ = offset;
                return 0;
            }
        }
    }

    private int initializePattern() {
        this.pattern_.PCE_ = null;
        return initializePatternCETable();
    }

    private void initialize() {
        initializePattern();
    }

    /* access modifiers changed from: protected */
    @Override // android.icu.text.SearchIterator
    @Deprecated
    public void setMatchNotFound() {
        super.setMatchNotFound();
        if (this.search_.isForwardSearching_) {
            this.textIter_.setOffset(this.search_.text().getEndIndex());
        } else {
            this.textIter_.setOffset(0);
        }
    }

    private static final boolean isOutOfBounds(int textstart, int textlimit, int offset) {
        return offset < textstart || offset > textlimit;
    }

    private boolean checkIdentical(int start, int end) {
        if (this.strength_ != 15) {
            return true;
        }
        String textstr = getString(this.targetText, start, end - start);
        if (Normalizer.quickCheck(textstr, Normalizer.NFD, 0) == Normalizer.NO) {
            textstr = Normalizer.decompose(textstr, false);
        }
        String patternstr = this.pattern_.text_;
        if (Normalizer.quickCheck(patternstr, Normalizer.NFD, 0) == Normalizer.NO) {
            patternstr = Normalizer.decompose(patternstr, false);
        }
        return textstr.equals(patternstr);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean initTextProcessedIter() {
        CollationPCE collationPCE = this.textProcessedIter_;
        if (collationPCE == null) {
            this.textProcessedIter_ = new CollationPCE(this.textIter_);
            return true;
        }
        collationPCE.init(this.textIter_);
        return true;
    }

    private int nextBoundaryAfter(int startIndex) {
        BreakIterator breakiterator = this.search_.breakIter();
        if (breakiterator == null) {
            breakiterator = this.search_.internalBreakIter_;
        }
        if (breakiterator != null) {
            return breakiterator.following(startIndex);
        }
        return startIndex;
    }

    private boolean isBreakBoundary(int index) {
        BreakIterator breakiterator = this.search_.breakIter();
        if (breakiterator == null) {
            breakiterator = this.search_.internalBreakIter_;
        }
        return breakiterator != null && breakiterator.isBoundary(index);
    }

    private static int compareCE64s(long targCE, long patCE, SearchIterator.ElementComparisonType compareType) {
        if (targCE == patCE) {
            return -1;
        }
        if (compareType == SearchIterator.ElementComparisonType.STANDARD_ELEMENT_COMPARISON) {
            return 0;
        }
        long targCEshifted = targCE >>> 32;
        long patCEshifted = patCE >>> 32;
        int targLev1 = (int) (targCEshifted & Collation.MAX_PRIMARY);
        int patLev1 = (int) (patCEshifted & Collation.MAX_PRIMARY);
        if (targLev1 == patLev1) {
            int targLev2 = (int) (targCEshifted & 65535);
            int patLev2 = (int) (patCEshifted & 65535);
            if (targLev2 == patLev2) {
                int targLev3 = (int) (targCE & Collation.MAX_PRIMARY);
                int patLev3 = (int) (patCE & Collation.MAX_PRIMARY);
                if (targLev3 == patLev3 || patLev3 == CE_LEVEL3_BASE) {
                    return -1;
                }
                if (compareType == SearchIterator.ElementComparisonType.ANY_BASE_WEIGHT_IS_WILDCARD && targLev3 == CE_LEVEL3_BASE) {
                    return -1;
                }
                return 0;
            } else if (targLev2 == 0) {
                return 1;
            } else {
                if (patLev2 == 0 && compareType == SearchIterator.ElementComparisonType.ANY_BASE_WEIGHT_IS_WILDCARD) {
                    return 2;
                }
                if (patLev2 == CE_LEVEL2_BASE) {
                    return -1;
                }
                if (compareType == SearchIterator.ElementComparisonType.ANY_BASE_WEIGHT_IS_WILDCARD && targLev2 == CE_LEVEL2_BASE) {
                    return -1;
                }
                return 0;
            }
        } else if (targLev1 == 0) {
            return 1;
        } else {
            if (patLev1 == 0 && compareType == SearchIterator.ElementComparisonType.ANY_BASE_WEIGHT_IS_WILDCARD) {
                return 2;
            }
            return 0;
        }
    }

    /* access modifiers changed from: private */
    public static class Match {
        int limit_;
        int start_;

        private Match() {
            this.start_ = -1;
            this.limit_ = -1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x008a, code lost:
        if (r5.ce_ != -1) goto L_0x0090;
     */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01bc  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01c4  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x01c8 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x01ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean search(int r30, android.icu.text.StringSearch.Match r31) {
        /*
        // Method dump skipped, instructions count: 569
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.StringSearch.search(int, android.icu.text.StringSearch$Match):boolean");
    }

    private static int codePointAt(CharacterIterator iter, int index) {
        int currentIterIndex = iter.getIndex();
        char codeUnit = iter.setIndex(index);
        int cp = codeUnit;
        if (Character.isHighSurrogate(codeUnit)) {
            char nextUnit = iter.next();
            if (Character.isLowSurrogate(nextUnit)) {
                cp = Character.toCodePoint(codeUnit, nextUnit);
            }
        }
        iter.setIndex(currentIterIndex);
        return cp;
    }

    private static int codePointBefore(CharacterIterator iter, int index) {
        int currentIterIndex = iter.getIndex();
        iter.setIndex(index);
        char codeUnit = iter.previous();
        int cp = codeUnit;
        if (Character.isLowSurrogate(codeUnit)) {
            char prevUnit = iter.previous();
            if (Character.isHighSurrogate(prevUnit)) {
                cp = Character.toCodePoint(prevUnit, codeUnit);
            }
        }
        iter.setIndex(currentIterIndex);
        return cp;
    }

    /* JADX INFO: Multiple debug info for r8v7 int: [D('mLimit' int), D('maxLimit' int)] */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x014f, code lost:
        if (r23.nfd_.hasBoundaryAfter(codePointBefore(r23.targetText, r8)) != false) goto L_0x0158;
     */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x015c  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0172  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean searchBackwards(int r24, android.icu.text.StringSearch.Match r25) {
        /*
        // Method dump skipped, instructions count: 520
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.StringSearch.searchBackwards(int, android.icu.text.StringSearch$Match):boolean");
    }

    private boolean handleNextExact() {
        return handleNextCommonImpl();
    }

    private boolean handleNextCanonical() {
        return handleNextCommonImpl();
    }

    private boolean handleNextCommonImpl() {
        int textOffset = this.textIter_.getOffset();
        Match match = new Match();
        if (search(textOffset, match)) {
            this.search_.matchedIndex_ = match.start_;
            this.search_.setMatchedLength(match.limit_ - match.start_);
            return true;
        }
        setMatchNotFound();
        return false;
    }

    private boolean handlePreviousExact() {
        return handlePreviousCommonImpl();
    }

    private boolean handlePreviousCanonical() {
        return handlePreviousCommonImpl();
    }

    private boolean handlePreviousCommonImpl() {
        int textOffset;
        if (!this.search_.isOverlap_) {
            textOffset = this.textIter_.getOffset();
        } else if (this.search_.matchedIndex_ != -1) {
            textOffset = (this.search_.matchedIndex_ + this.search_.matchedLength()) - 1;
        } else {
            initializePatternPCETable();
            if (!initTextProcessedIter()) {
                setMatchNotFound();
                return false;
            }
            for (int nPCEs = 0; nPCEs < this.pattern_.PCELength_ - 1 && this.textProcessedIter_.nextProcessed(null) != -1; nPCEs++) {
            }
            textOffset = this.textIter_.getOffset();
        }
        Match match = new Match();
        if (searchBackwards(textOffset, match)) {
            this.search_.matchedIndex_ = match.start_;
            this.search_.setMatchedLength(match.limit_ - match.start_);
            return true;
        }
        setMatchNotFound();
        return false;
    }

    private static final String getString(CharacterIterator text, int start, int length) {
        StringBuilder result = new StringBuilder(length);
        int offset = text.getIndex();
        text.setIndex(start);
        for (int i = 0; i < length; i++) {
            result.append(text.current());
            text.next();
        }
        text.setIndex(offset);
        return result.toString();
    }

    /* access modifiers changed from: private */
    public static final class Pattern {
        int CELength_ = 0;
        int[] CE_;
        int PCELength_ = 0;
        long[] PCE_;
        String text_;

        protected Pattern(String pattern) {
            this.text_ = pattern;
        }
    }

    /* access modifiers changed from: private */
    public static class CollationPCE {
        private static final int BUFFER_GROW = 8;
        private static final int CONTINUATION_MARKER = 192;
        private static final int DEFAULT_BUFFER_SIZE = 16;
        private static final int PRIMARYORDERMASK = -65536;
        public static final long PROCESSED_NULLORDER = -1;
        private CollationElementIterator cei_;
        private boolean isShifted_;
        private PCEBuffer pceBuffer_ = new PCEBuffer();
        private int strength_;
        private boolean toShift_;
        private int variableTop_;

        public static final class Range {
            int ixHigh_;
            int ixLow_;
        }

        public CollationPCE(CollationElementIterator iter) {
            init(iter);
        }

        public void init(CollationElementIterator iter) {
            this.cei_ = iter;
            init(iter.getRuleBasedCollator());
        }

        private void init(RuleBasedCollator coll) {
            this.strength_ = coll.getStrength();
            this.toShift_ = coll.isAlternateHandlingShifted();
            this.isShifted_ = false;
            this.variableTop_ = coll.getVariableTop();
        }

        private long processCE(int ce) {
            long secondary = 0;
            long tertiary = 0;
            long quaternary = 0;
            int i = this.strength_;
            if (i != 0) {
                if (i != 1) {
                    tertiary = (long) CollationElementIterator.tertiaryOrder(ce);
                }
                secondary = (long) CollationElementIterator.secondaryOrder(ce);
            }
            long primary = (long) CollationElementIterator.primaryOrder(ce);
            if ((!this.toShift_ || this.variableTop_ <= ce || primary == 0) && (!this.isShifted_ || primary != 0)) {
                if (this.strength_ >= 3) {
                    quaternary = 65535;
                }
                this.isShifted_ = false;
            } else if (primary == 0) {
                return 0;
            } else {
                if (this.strength_ >= 3) {
                    quaternary = primary;
                }
                tertiary = 0;
                secondary = 0;
                primary = 0;
                this.isShifted_ = true;
            }
            return (primary << 48) | (secondary << 32) | (tertiary << 16) | quaternary;
        }

        public long nextProcessed(Range range) {
            int low;
            int high;
            long result;
            this.pceBuffer_.reset();
            while (true) {
                low = this.cei_.getOffset();
                int ce = this.cei_.next();
                high = this.cei_.getOffset();
                if (ce != -1) {
                    result = processCE(ce);
                    if (result != 0) {
                        break;
                    }
                } else {
                    result = -1;
                    break;
                }
            }
            if (range != null) {
                range.ixLow_ = low;
                range.ixHigh_ = high;
            }
            return result;
        }

        public long previousProcessed(Range range) {
            while (this.pceBuffer_.empty()) {
                RCEBuffer rceb = new RCEBuffer();
                boolean finish = false;
                while (true) {
                    int high = this.cei_.getOffset();
                    int ce = this.cei_.previous();
                    int low = this.cei_.getOffset();
                    if (ce != -1) {
                        rceb.put(ce, low, high);
                        if ((-65536 & ce) != 0 && !isContinuation(ce)) {
                            break;
                        }
                    } else if (rceb.empty()) {
                        finish = true;
                    }
                }
                if (finish) {
                    break;
                }
                while (!rceb.empty()) {
                    RCEI rcei = rceb.get();
                    long result = processCE(rcei.ce_);
                    if (result != 0) {
                        this.pceBuffer_.put(result, rcei.low_, rcei.high_);
                    }
                }
            }
            if (!this.pceBuffer_.empty()) {
                PCEI pcei = this.pceBuffer_.get();
                if (range != null) {
                    range.ixLow_ = pcei.low_;
                    range.ixHigh_ = pcei.high_;
                }
                return pcei.ce_;
            } else if (range == null) {
                return -1;
            } else {
                range.ixLow_ = -1;
                range.ixHigh_ = -1;
                return -1;
            }
        }

        private static boolean isContinuation(int ce) {
            return (ce & 192) == 192;
        }

        /* access modifiers changed from: private */
        public static final class PCEI {
            long ce_;
            int high_;
            int low_;

            private PCEI() {
            }
        }

        /* access modifiers changed from: private */
        public static final class PCEBuffer {
            private int bufferIndex_;
            private PCEI[] buffer_;

            private PCEBuffer() {
                this.buffer_ = new PCEI[16];
                this.bufferIndex_ = 0;
            }

            /* access modifiers changed from: package-private */
            public void reset() {
                this.bufferIndex_ = 0;
            }

            /* access modifiers changed from: package-private */
            public boolean empty() {
                return this.bufferIndex_ <= 0;
            }

            /* access modifiers changed from: package-private */
            public void put(long ce, int ixLow, int ixHigh) {
                int i = this.bufferIndex_;
                PCEI[] pceiArr = this.buffer_;
                if (i >= pceiArr.length) {
                    PCEI[] newBuffer = new PCEI[(pceiArr.length + 8)];
                    System.arraycopy(pceiArr, 0, newBuffer, 0, pceiArr.length);
                    this.buffer_ = newBuffer;
                }
                this.buffer_[this.bufferIndex_] = new PCEI();
                PCEI[] pceiArr2 = this.buffer_;
                int i2 = this.bufferIndex_;
                pceiArr2[i2].ce_ = ce;
                pceiArr2[i2].low_ = ixLow;
                pceiArr2[i2].high_ = ixHigh;
                this.bufferIndex_ = i2 + 1;
            }

            /* access modifiers changed from: package-private */
            public PCEI get() {
                int i = this.bufferIndex_;
                if (i <= 0) {
                    return null;
                }
                PCEI[] pceiArr = this.buffer_;
                int i2 = i - 1;
                this.bufferIndex_ = i2;
                return pceiArr[i2];
            }
        }

        /* access modifiers changed from: private */
        public static final class RCEI {
            int ce_;
            int high_;
            int low_;

            private RCEI() {
            }
        }

        /* access modifiers changed from: private */
        public static final class RCEBuffer {
            private int bufferIndex_;
            private RCEI[] buffer_;

            private RCEBuffer() {
                this.buffer_ = new RCEI[16];
                this.bufferIndex_ = 0;
            }

            /* access modifiers changed from: package-private */
            public boolean empty() {
                return this.bufferIndex_ <= 0;
            }

            /* access modifiers changed from: package-private */
            public void put(int ce, int ixLow, int ixHigh) {
                int i = this.bufferIndex_;
                RCEI[] rceiArr = this.buffer_;
                if (i >= rceiArr.length) {
                    RCEI[] newBuffer = new RCEI[(rceiArr.length + 8)];
                    System.arraycopy(rceiArr, 0, newBuffer, 0, rceiArr.length);
                    this.buffer_ = newBuffer;
                }
                this.buffer_[this.bufferIndex_] = new RCEI();
                RCEI[] rceiArr2 = this.buffer_;
                int i2 = this.bufferIndex_;
                rceiArr2[i2].ce_ = ce;
                rceiArr2[i2].low_ = ixLow;
                rceiArr2[i2].high_ = ixHigh;
                this.bufferIndex_ = i2 + 1;
            }

            /* access modifiers changed from: package-private */
            public RCEI get() {
                int i = this.bufferIndex_;
                if (i <= 0) {
                    return null;
                }
                RCEI[] rceiArr = this.buffer_;
                int i2 = i - 1;
                this.bufferIndex_ = i2;
                return rceiArr[i2];
            }
        }
    }

    /* access modifiers changed from: private */
    public static class CEI {
        long ce_;
        int highIndex_;
        int lowIndex_;

        private CEI() {
        }
    }

    /* access modifiers changed from: private */
    public static class CEBuffer {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final int CEBUFFER_EXTRA = 32;
        static final int MAX_TARGET_IGNORABLES_PER_PAT_JAMO_L = 8;
        static final int MAX_TARGET_IGNORABLES_PER_PAT_OTHER = 3;
        int bufSize_;
        CEI[] buf_;
        int firstIx_;
        int limitIx_;
        StringSearch strSearch_;

        CEBuffer(StringSearch ss) {
            String patText;
            this.strSearch_ = ss;
            this.bufSize_ = ss.pattern_.PCELength_ + 32;
            if (!(ss.search_.elementComparisonType_ == SearchIterator.ElementComparisonType.STANDARD_ELEMENT_COMPARISON || (patText = ss.pattern_.text_) == null)) {
                for (int i = 0; i < patText.length(); i++) {
                    if (MIGHT_BE_JAMO_L(patText.charAt(i))) {
                        this.bufSize_ += 8;
                    } else {
                        this.bufSize_ += 3;
                    }
                }
            }
            this.firstIx_ = 0;
            this.limitIx_ = 0;
            if (ss.initTextProcessedIter()) {
                this.buf_ = new CEI[this.bufSize_];
            }
        }

        /* access modifiers changed from: package-private */
        public CEI get(int index) {
            int i = index % this.bufSize_;
            if (index >= this.firstIx_ && index < this.limitIx_) {
                return this.buf_[i];
            }
            int i2 = this.limitIx_;
            if (index != i2) {
                return null;
            }
            this.limitIx_ = i2 + 1;
            int i3 = this.limitIx_;
            int i4 = this.firstIx_;
            if (i3 - i4 >= this.bufSize_) {
                this.firstIx_ = i4 + 1;
            }
            CollationPCE.Range range = new CollationPCE.Range();
            CEI[] ceiArr = this.buf_;
            if (ceiArr[i] == null) {
                ceiArr[i] = new CEI();
            }
            this.buf_[i].ce_ = this.strSearch_.textProcessedIter_.nextProcessed(range);
            this.buf_[i].lowIndex_ = range.ixLow_;
            this.buf_[i].highIndex_ = range.ixHigh_;
            return this.buf_[i];
        }

        /* access modifiers changed from: package-private */
        public CEI getPrevious(int index) {
            int i = index % this.bufSize_;
            if (index >= this.firstIx_ && index < this.limitIx_) {
                return this.buf_[i];
            }
            int i2 = this.limitIx_;
            if (index != i2) {
                return null;
            }
            this.limitIx_ = i2 + 1;
            int i3 = this.limitIx_;
            int i4 = this.firstIx_;
            if (i3 - i4 >= this.bufSize_) {
                this.firstIx_ = i4 + 1;
            }
            CollationPCE.Range range = new CollationPCE.Range();
            CEI[] ceiArr = this.buf_;
            if (ceiArr[i] == null) {
                ceiArr[i] = new CEI();
            }
            this.buf_[i].ce_ = this.strSearch_.textProcessedIter_.previousProcessed(range);
            this.buf_[i].lowIndex_ = range.ixLow_;
            this.buf_[i].highIndex_ = range.ixHigh_;
            return this.buf_[i];
        }

        static boolean MIGHT_BE_JAMO_L(char c) {
            return (c >= 4352 && c <= 4446) || (c >= 12593 && c <= 12622) || (c >= 12645 && c <= 12678);
        }
    }
}
