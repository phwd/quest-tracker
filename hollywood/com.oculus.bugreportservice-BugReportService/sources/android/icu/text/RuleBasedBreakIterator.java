package android.icu.text;

import android.icu.impl.CharacterIteration;
import android.icu.impl.ICUDebug;
import android.icu.impl.RBBIDataWrapper;
import android.icu.impl.Trie2;
import android.icu.lang.UCharacter;
import android.icu.text.DictionaryBreakEngine;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RuleBasedBreakIterator extends BreakIterator {
    private static final boolean TRACE = (ICUDebug.enabled("rbbi") && ICUDebug.value("rbbi").indexOf("trace") >= 0);
    public static final String fDebugEnv = (ICUDebug.enabled("rbbi") ? ICUDebug.value("rbbi") : null);
    private static final List gAllBreakEngines = new ArrayList();
    private static final UnhandledBreakEngine gUnhandledBreakEngine = new UnhandledBreakEngine();
    private BreakCache fBreakCache = new BreakCache();
    private List fBreakEngines;
    private DictionaryCache fDictionaryCache = new DictionaryCache();
    private int fDictionaryCharCount = 0;
    private boolean fDone;
    private LookAheadResults fLookAheadMatches = new LookAheadResults();
    private int fPosition;
    public RBBIDataWrapper fRData;
    private int fRuleStatusIndex;
    private CharacterIterator fText = new StringCharacterIterator("");

    private RuleBasedBreakIterator() {
        synchronized (gAllBreakEngines) {
            this.fBreakEngines = new ArrayList(gAllBreakEngines);
        }
    }

    public static RuleBasedBreakIterator getInstanceFromCompiledRules(ByteBuffer byteBuffer) {
        RuleBasedBreakIterator ruleBasedBreakIterator = new RuleBasedBreakIterator();
        ruleBasedBreakIterator.fRData = RBBIDataWrapper.get(byteBuffer);
        return ruleBasedBreakIterator;
    }

    @Override // android.icu.text.BreakIterator
    public Object clone() {
        RuleBasedBreakIterator ruleBasedBreakIterator = (RuleBasedBreakIterator) super.clone();
        CharacterIterator characterIterator = this.fText;
        if (characterIterator != null) {
            ruleBasedBreakIterator.fText = (CharacterIterator) characterIterator.clone();
        }
        synchronized (gAllBreakEngines) {
            ruleBasedBreakIterator.fBreakEngines = new ArrayList(gAllBreakEngines);
        }
        ruleBasedBreakIterator.fLookAheadMatches = new LookAheadResults();
        Objects.requireNonNull(ruleBasedBreakIterator);
        ruleBasedBreakIterator.fBreakCache = new BreakCache(this.fBreakCache);
        Objects.requireNonNull(ruleBasedBreakIterator);
        ruleBasedBreakIterator.fDictionaryCache = new DictionaryCache(this.fDictionaryCache);
        return ruleBasedBreakIterator;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        try {
            RuleBasedBreakIterator ruleBasedBreakIterator = (RuleBasedBreakIterator) obj;
            if (this.fRData != ruleBasedBreakIterator.fRData && (this.fRData == null || ruleBasedBreakIterator.fRData == null)) {
                return false;
            }
            if (this.fRData != null && ruleBasedBreakIterator.fRData != null && !this.fRData.fRuleSource.equals(ruleBasedBreakIterator.fRData.fRuleSource)) {
                return false;
            }
            if (this.fText == null && ruleBasedBreakIterator.fText == null) {
                return true;
            }
            if (this.fText == null || ruleBasedBreakIterator.fText == null) {
                return false;
            }
            if (!this.fText.equals(ruleBasedBreakIterator.fText)) {
                return false;
            }
            return this.fPosition == ruleBasedBreakIterator.fPosition;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public String toString() {
        RBBIDataWrapper rBBIDataWrapper = this.fRData;
        return rBBIDataWrapper != null ? rBBIDataWrapper.fRuleSource : "";
    }

    public int hashCode() {
        return this.fRData.fRuleSource.hashCode();
    }

    static {
        gAllBreakEngines.add(gUnhandledBreakEngine);
    }

    @Override // android.icu.text.BreakIterator
    public int first() {
        CharacterIterator characterIterator = this.fText;
        if (characterIterator == null) {
            return -1;
        }
        characterIterator.first();
        int index = this.fText.getIndex();
        if (!this.fBreakCache.seek(index)) {
            this.fBreakCache.populateNear(index);
        }
        this.fBreakCache.current();
        return this.fPosition;
    }

    @Override // android.icu.text.BreakIterator
    public int next() {
        this.fBreakCache.next();
        if (this.fDone) {
            return -1;
        }
        return this.fPosition;
    }

    @Override // android.icu.text.BreakIterator
    public CharacterIterator getText() {
        return this.fText;
    }

    @Override // android.icu.text.BreakIterator
    public void setText(CharacterIterator characterIterator) {
        if (characterIterator != null) {
            this.fBreakCache.reset(characterIterator.getBeginIndex(), 0);
        } else {
            this.fBreakCache.reset();
        }
        this.fDictionaryCache.reset();
        this.fText = characterIterator;
        first();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private LanguageBreakEngine getLanguageBreakEngine(int i) {
        LanguageBreakEngine languageBreakEngine;
        for (LanguageBreakEngine languageBreakEngine2 : this.fBreakEngines) {
            if (languageBreakEngine2.handles(i)) {
                return languageBreakEngine2;
            }
        }
        synchronized (gAllBreakEngines) {
            for (LanguageBreakEngine languageBreakEngine3 : gAllBreakEngines) {
                if (languageBreakEngine3.handles(i)) {
                    this.fBreakEngines.add(languageBreakEngine3);
                    return languageBreakEngine3;
                }
            }
            int intPropertyValue = UCharacter.getIntPropertyValue(i, 4106);
            if (intPropertyValue == 22 || intPropertyValue == 20) {
                intPropertyValue = 17;
            }
            if (intPropertyValue == 17) {
                languageBreakEngine = new CjkBreakEngine(false);
            } else if (intPropertyValue == 18) {
                languageBreakEngine = new CjkBreakEngine(true);
            } else if (intPropertyValue == 23) {
                languageBreakEngine = new KhmerBreakEngine();
            } else if (intPropertyValue == 24) {
                languageBreakEngine = new LaoBreakEngine();
            } else if (intPropertyValue == 28) {
                languageBreakEngine = new BurmeseBreakEngine();
            } else if (intPropertyValue != 38) {
                try {
                    gUnhandledBreakEngine.handleChar(i);
                    languageBreakEngine = gUnhandledBreakEngine;
                } catch (IOException unused) {
                    languageBreakEngine = null;
                }
            } else {
                languageBreakEngine = new ThaiBreakEngine();
            }
            if (!(languageBreakEngine == null || languageBreakEngine == gUnhandledBreakEngine)) {
                gAllBreakEngines.add(languageBreakEngine);
                this.fBreakEngines.add(languageBreakEngine);
            }
            return languageBreakEngine;
        }
    }

    /* access modifiers changed from: private */
    public static class LookAheadResults {
        int[] fKeys = new int[8];
        int[] fPositions = new int[8];
        int fUsedSlotLimit = 0;

        LookAheadResults() {
        }

        /* access modifiers changed from: package-private */
        public int getPosition(int i) {
            for (int i2 = 0; i2 < this.fUsedSlotLimit; i2++) {
                if (this.fKeys[i2] == i) {
                    return this.fPositions[i2];
                }
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public void setPosition(int i, int i2) {
            int i3 = 0;
            while (i3 < this.fUsedSlotLimit) {
                if (this.fKeys[i3] == i) {
                    this.fPositions[i3] = i2;
                    return;
                }
                i3++;
            }
            if (i3 >= 8) {
                i3 = 7;
            }
            this.fKeys[i3] = i;
            this.fPositions[i3] = i2;
            this.fUsedSlotLimit = i3 + 1;
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.fUsedSlotLimit = 0;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0121  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0130 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int handleNext() {
        /*
        // Method dump skipped, instructions count: 374
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.text.RuleBasedBreakIterator.handleNext():int");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int handleSafePrevious(int i) {
        CharacterIterator characterIterator = this.fText;
        RBBIDataWrapper rBBIDataWrapper = this.fRData;
        Trie2 trie2 = rBBIDataWrapper.fTrie;
        short[] sArr = rBBIDataWrapper.fRTable.fTable;
        CISetIndex32(characterIterator, i);
        if (TRACE) {
            System.out.print("Handle Previous   pos   char  state category");
        }
        if (characterIterator.getIndex() == characterIterator.getBeginIndex()) {
            return -1;
        }
        int rowIndex = this.fRData.getRowIndex(1);
        for (int previous32 = CharacterIteration.previous32(characterIterator); previous32 != Integer.MAX_VALUE; previous32 = CharacterIteration.previous32(characterIterator)) {
            short s = (short) (((short) trie2.get(previous32)) & -16385);
            if (!TRACE) {
                short s2 = sArr[rowIndex + 4 + s];
                rowIndex = this.fRData.getRowIndex(s2);
                if (s2 == 0) {
                    break;
                }
            } else {
                PrintStream printStream = System.out;
                printStream.print("            " + RBBIDataWrapper.intToString(characterIterator.getIndex(), 5));
                PrintStream printStream2 = System.out;
                RBBIDataWrapper.intToHexString(previous32, 10);
                throw null;
            }
        }
        int index = characterIterator.getIndex();
        if (TRACE) {
            PrintStream printStream3 = System.out;
            printStream3.println("result = " + index);
        }
        return index;
    }

    private static int CISetIndex32(CharacterIterator characterIterator, int i) {
        if (i <= characterIterator.getBeginIndex()) {
            characterIterator.first();
        } else if (i >= characterIterator.getEndIndex()) {
            characterIterator.setIndex(characterIterator.getEndIndex());
        } else if (Character.isLowSurrogate(characterIterator.setIndex(i)) && !Character.isHighSurrogate(characterIterator.previous())) {
            characterIterator.next();
        }
        return characterIterator.getIndex();
    }

    /* access modifiers changed from: package-private */
    public class DictionaryCache {
        int fBoundary;
        DictionaryBreakEngine.DequeI fBreaks;
        int fFirstRuleStatusIndex;
        int fLimit;
        int fOtherRuleStatusIndex;
        int fPositionInCache;
        int fStart;
        int fStatusIndex;

        /* access modifiers changed from: package-private */
        public void reset() {
            this.fPositionInCache = -1;
            this.fStart = 0;
            this.fLimit = 0;
            this.fFirstRuleStatusIndex = 0;
            this.fOtherRuleStatusIndex = 0;
            this.fBreaks.removeAllElements();
        }

        /* access modifiers changed from: package-private */
        public boolean following(int i) {
            if (i >= this.fLimit || i < this.fStart) {
                this.fPositionInCache = -1;
                return false;
            }
            int i2 = this.fPositionInCache;
            if (i2 < 0 || i2 >= this.fBreaks.size() || this.fBreaks.elementAt(this.fPositionInCache) != i) {
                this.fPositionInCache = 0;
                while (this.fPositionInCache < this.fBreaks.size()) {
                    int elementAt = this.fBreaks.elementAt(this.fPositionInCache);
                    if (elementAt > i) {
                        this.fBoundary = elementAt;
                        this.fStatusIndex = this.fOtherRuleStatusIndex;
                        return true;
                    }
                    this.fPositionInCache++;
                }
                this.fPositionInCache = -1;
                return false;
            }
            this.fPositionInCache++;
            if (this.fPositionInCache >= this.fBreaks.size()) {
                this.fPositionInCache = -1;
                return false;
            }
            this.fBoundary = this.fBreaks.elementAt(this.fPositionInCache);
            this.fStatusIndex = this.fOtherRuleStatusIndex;
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean preceding(int i) {
            int i2;
            if (i <= this.fStart || i > (i2 = this.fLimit)) {
                this.fPositionInCache = -1;
                return false;
            }
            if (i == i2) {
                this.fPositionInCache = this.fBreaks.size() - 1;
                int i3 = this.fPositionInCache;
            }
            int i4 = this.fPositionInCache;
            if (i4 > 0 && i4 < this.fBreaks.size() && this.fBreaks.elementAt(this.fPositionInCache) == i) {
                this.fPositionInCache--;
                int elementAt = this.fBreaks.elementAt(this.fPositionInCache);
                this.fBoundary = elementAt;
                this.fStatusIndex = elementAt == this.fStart ? this.fFirstRuleStatusIndex : this.fOtherRuleStatusIndex;
                return true;
            } else if (this.fPositionInCache == 0) {
                this.fPositionInCache = -1;
                return false;
            } else {
                int size = this.fBreaks.size();
                while (true) {
                    this.fPositionInCache = size - 1;
                    int i5 = this.fPositionInCache;
                    if (i5 >= 0) {
                        int elementAt2 = this.fBreaks.elementAt(i5);
                        if (elementAt2 < i) {
                            this.fBoundary = elementAt2;
                            this.fStatusIndex = elementAt2 == this.fStart ? this.fFirstRuleStatusIndex : this.fOtherRuleStatusIndex;
                            return true;
                        }
                        size = this.fPositionInCache;
                    } else {
                        this.fPositionInCache = -1;
                        return false;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void populateDictionary(int i, int i2, int i3, int i4) {
            int i5;
            if (i2 - i > 1) {
                reset();
                this.fFirstRuleStatusIndex = i3;
                this.fOtherRuleStatusIndex = i4;
                RuleBasedBreakIterator.this.fText.setIndex(i);
                int current32 = CharacterIteration.current32(RuleBasedBreakIterator.this.fText);
                short s = (short) RuleBasedBreakIterator.this.fRData.fTrie.get(current32);
                int i6 = 0;
                while (true) {
                    int index = RuleBasedBreakIterator.this.fText.getIndex();
                    if (index < i2 && (s & 16384) == 0) {
                        current32 = CharacterIteration.next32(RuleBasedBreakIterator.this.fText);
                        i5 = RuleBasedBreakIterator.this.fRData.fTrie.get(current32);
                    } else if (index >= i2) {
                        break;
                    } else {
                        LanguageBreakEngine languageBreakEngine = RuleBasedBreakIterator.this.getLanguageBreakEngine(current32);
                        if (languageBreakEngine != null) {
                            i6 += languageBreakEngine.findBreaks(RuleBasedBreakIterator.this.fText, i, i2, this.fBreaks);
                        }
                        current32 = CharacterIteration.current32(RuleBasedBreakIterator.this.fText);
                        i5 = RuleBasedBreakIterator.this.fRData.fTrie.get(current32);
                    }
                    s = (short) i5;
                }
                if (i6 > 0) {
                    if (i < this.fBreaks.elementAt(0)) {
                        this.fBreaks.offer(i);
                    }
                    if (i2 > this.fBreaks.peek()) {
                        this.fBreaks.push(i2);
                    }
                    this.fPositionInCache = 0;
                    this.fStart = this.fBreaks.elementAt(0);
                    this.fLimit = this.fBreaks.peek();
                }
            }
        }

        DictionaryCache() {
            this.fPositionInCache = -1;
            this.fBreaks = new DictionaryBreakEngine.DequeI();
        }

        DictionaryCache(DictionaryCache dictionaryCache) {
            try {
                this.fBreaks = (DictionaryBreakEngine.DequeI) dictionaryCache.fBreaks.clone();
                this.fPositionInCache = dictionaryCache.fPositionInCache;
                this.fStart = dictionaryCache.fStart;
                this.fLimit = dictionaryCache.fLimit;
                this.fFirstRuleStatusIndex = dictionaryCache.fFirstRuleStatusIndex;
                this.fOtherRuleStatusIndex = dictionaryCache.fOtherRuleStatusIndex;
                this.fBoundary = dictionaryCache.fBoundary;
                this.fStatusIndex = dictionaryCache.fStatusIndex;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public class BreakCache {
        int[] fBoundaries;
        int fBufIdx;
        int fEndBufIdx;
        DictionaryBreakEngine.DequeI fSideBuffer;
        int fStartBufIdx;
        short[] fStatuses;
        int fTextIdx;

        private final int modChunkSize(int i) {
            return i & 127;
        }

        BreakCache() {
            this.fBoundaries = new int[128];
            this.fStatuses = new short[128];
            this.fSideBuffer = new DictionaryBreakEngine.DequeI();
            reset();
        }

        /* access modifiers changed from: package-private */
        public void reset(int i, int i2) {
            this.fStartBufIdx = 0;
            this.fEndBufIdx = 0;
            this.fTextIdx = i;
            this.fBufIdx = 0;
            this.fBoundaries[0] = i;
            this.fStatuses[0] = (short) i2;
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            reset(0, 0);
        }

        /* access modifiers changed from: package-private */
        public void next() {
            int i = this.fBufIdx;
            if (i == this.fEndBufIdx) {
                RuleBasedBreakIterator.this.fDone = !populateFollowing();
                RuleBasedBreakIterator.this.fPosition = this.fTextIdx;
                RuleBasedBreakIterator.this.fRuleStatusIndex = this.fStatuses[this.fBufIdx];
                return;
            }
            this.fBufIdx = modChunkSize(i + 1);
            RuleBasedBreakIterator ruleBasedBreakIterator = RuleBasedBreakIterator.this;
            int i2 = this.fBoundaries[this.fBufIdx];
            ruleBasedBreakIterator.fPosition = i2;
            this.fTextIdx = i2;
            RuleBasedBreakIterator.this.fRuleStatusIndex = this.fStatuses[this.fBufIdx];
        }

        /* access modifiers changed from: package-private */
        public void previous() {
            int i = this.fBufIdx;
            if (i == this.fStartBufIdx) {
                populatePreceding();
            } else {
                this.fBufIdx = modChunkSize(i - 1);
                this.fTextIdx = this.fBoundaries[this.fBufIdx];
            }
            RuleBasedBreakIterator.this.fDone = this.fBufIdx == i;
            RuleBasedBreakIterator.this.fPosition = this.fTextIdx;
            RuleBasedBreakIterator.this.fRuleStatusIndex = this.fStatuses[this.fBufIdx];
        }

        /* access modifiers changed from: package-private */
        public int current() {
            RuleBasedBreakIterator.this.fPosition = this.fTextIdx;
            RuleBasedBreakIterator.this.fRuleStatusIndex = this.fStatuses[this.fBufIdx];
            RuleBasedBreakIterator.this.fDone = false;
            return this.fTextIdx;
        }

        /* access modifiers changed from: package-private */
        public boolean populateNear(int i) {
            int[] iArr;
            int i2;
            int i3;
            int i4;
            int[] iArr2 = this.fBoundaries;
            if (i < iArr2[this.fStartBufIdx] - 15 || i > iArr2[this.fEndBufIdx] + 15) {
                int beginIndex = RuleBasedBreakIterator.this.fText.getBeginIndex();
                if (i > beginIndex + 20) {
                    int handleSafePrevious = RuleBasedBreakIterator.this.handleSafePrevious(i);
                    if (handleSafePrevious > beginIndex) {
                        RuleBasedBreakIterator.this.fPosition = handleSafePrevious;
                        beginIndex = RuleBasedBreakIterator.this.handleNext();
                        if (beginIndex == handleSafePrevious + 1 || (beginIndex == handleSafePrevious + 2 && Character.isHighSurrogate(RuleBasedBreakIterator.this.fText.setIndex(handleSafePrevious)) && Character.isLowSurrogate(RuleBasedBreakIterator.this.fText.next()))) {
                            beginIndex = RuleBasedBreakIterator.this.handleNext();
                        }
                    }
                    i4 = RuleBasedBreakIterator.this.fRuleStatusIndex;
                } else {
                    i4 = 0;
                }
                reset(beginIndex, i4);
            }
            int[] iArr3 = this.fBoundaries;
            if (iArr3[this.fEndBufIdx] < i) {
                do {
                    int[] iArr4 = this.fBoundaries;
                    int i5 = this.fEndBufIdx;
                    if (iArr4[i5] >= i) {
                        this.fBufIdx = i5;
                        this.fTextIdx = iArr4[this.fBufIdx];
                        while (this.fTextIdx > i) {
                            previous();
                        }
                        return true;
                    }
                } while (populateFollowing());
                return false;
            }
            if (iArr3[this.fStartBufIdx] > i) {
                while (true) {
                    iArr = this.fBoundaries;
                    i2 = this.fStartBufIdx;
                    if (iArr[i2] <= i) {
                        break;
                    }
                    populatePreceding();
                }
                this.fBufIdx = i2;
                this.fTextIdx = iArr[this.fBufIdx];
                while (true) {
                    i3 = this.fTextIdx;
                    if (i3 >= i) {
                        break;
                    }
                    next();
                }
                if (i3 > i) {
                    previous();
                }
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean populateFollowing() {
            int handleNext;
            int[] iArr = this.fBoundaries;
            int i = this.fEndBufIdx;
            int i2 = iArr[i];
            short s = this.fStatuses[i];
            if (RuleBasedBreakIterator.this.fDictionaryCache.following(i2)) {
                addFollowing(RuleBasedBreakIterator.this.fDictionaryCache.fBoundary, RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex, true);
                return true;
            }
            RuleBasedBreakIterator.this.fPosition = i2;
            int handleNext2 = RuleBasedBreakIterator.this.handleNext();
            if (handleNext2 == -1) {
                return false;
            }
            int i3 = RuleBasedBreakIterator.this.fRuleStatusIndex;
            if (RuleBasedBreakIterator.this.fDictionaryCharCount > 0) {
                RuleBasedBreakIterator.this.fDictionaryCache.populateDictionary(i2, handleNext2, s, i3);
                if (RuleBasedBreakIterator.this.fDictionaryCache.following(i2)) {
                    addFollowing(RuleBasedBreakIterator.this.fDictionaryCache.fBoundary, RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex, true);
                    return true;
                }
            }
            addFollowing(handleNext2, i3, true);
            for (int i4 = 0; i4 < 6 && (handleNext = RuleBasedBreakIterator.this.handleNext()) != -1 && RuleBasedBreakIterator.this.fDictionaryCharCount <= 0; i4++) {
                addFollowing(handleNext, RuleBasedBreakIterator.this.fRuleStatusIndex, false);
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean populatePreceding() {
            int i;
            int i2;
            boolean z;
            int beginIndex = RuleBasedBreakIterator.this.fText.getBeginIndex();
            int i3 = this.fBoundaries[this.fStartBufIdx];
            if (i3 == beginIndex) {
                return false;
            }
            boolean z2 = true;
            if (RuleBasedBreakIterator.this.fDictionaryCache.preceding(i3)) {
                addPreceding(RuleBasedBreakIterator.this.fDictionaryCache.fBoundary, RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex, true);
                return true;
            }
            int i4 = i3;
            do {
                int i5 = i4 - 30;
                if (i5 <= beginIndex) {
                    i4 = beginIndex;
                } else {
                    i4 = RuleBasedBreakIterator.this.handleSafePrevious(i5);
                }
                if (i4 == -1 || i4 == beginIndex) {
                    i2 = beginIndex;
                    i = 0;
                    continue;
                } else {
                    RuleBasedBreakIterator.this.fPosition = i4;
                    i2 = RuleBasedBreakIterator.this.handleNext();
                    if (i2 == i4 + 1 || (i2 == i4 + 2 && Character.isHighSurrogate(RuleBasedBreakIterator.this.fText.setIndex(i4)) && Character.isLowSurrogate(RuleBasedBreakIterator.this.fText.next()))) {
                        i2 = RuleBasedBreakIterator.this.handleNext();
                    }
                    i = RuleBasedBreakIterator.this.fRuleStatusIndex;
                    continue;
                }
            } while (i2 >= i3);
            this.fSideBuffer.removeAllElements();
            this.fSideBuffer.push(i2);
            this.fSideBuffer.push(i);
            do {
                RuleBasedBreakIterator.this.fPosition = i2;
                int handleNext = RuleBasedBreakIterator.this.handleNext();
                int i6 = RuleBasedBreakIterator.this.fRuleStatusIndex;
                if (handleNext == -1) {
                    break;
                }
                if (RuleBasedBreakIterator.this.fDictionaryCharCount != 0) {
                    RuleBasedBreakIterator.this.fDictionaryCache.populateDictionary(i2, handleNext, i, i6);
                    i = i6;
                    z = false;
                    while (true) {
                        if (!RuleBasedBreakIterator.this.fDictionaryCache.following(i2)) {
                            break;
                        }
                        handleNext = RuleBasedBreakIterator.this.fDictionaryCache.fBoundary;
                        i = RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex;
                        if (handleNext >= i3) {
                            z = true;
                            break;
                        }
                        this.fSideBuffer.push(handleNext);
                        this.fSideBuffer.push(i);
                        i2 = handleNext;
                        z = true;
                    }
                    i2 = handleNext;
                } else {
                    i2 = handleNext;
                    i = i6;
                    z = false;
                }
                if (!z && i2 < i3) {
                    this.fSideBuffer.push(i2);
                    this.fSideBuffer.push(i);
                    continue;
                }
            } while (i2 < i3);
            if (!this.fSideBuffer.isEmpty()) {
                addPreceding(this.fSideBuffer.pop(), this.fSideBuffer.pop(), true);
            } else {
                z2 = false;
            }
            while (!this.fSideBuffer.isEmpty()) {
                if (!addPreceding(this.fSideBuffer.pop(), this.fSideBuffer.pop(), false)) {
                    break;
                }
            }
            return z2;
        }

        /* access modifiers changed from: package-private */
        public void addFollowing(int i, int i2, boolean z) {
            int modChunkSize = modChunkSize(this.fEndBufIdx + 1);
            int i3 = this.fStartBufIdx;
            if (modChunkSize == i3) {
                this.fStartBufIdx = modChunkSize(i3 + 6);
            }
            this.fBoundaries[modChunkSize] = i;
            this.fStatuses[modChunkSize] = (short) i2;
            this.fEndBufIdx = modChunkSize;
            if (z) {
                this.fBufIdx = modChunkSize;
                this.fTextIdx = i;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean addPreceding(int i, int i2, boolean z) {
            int modChunkSize = modChunkSize(this.fStartBufIdx - 1);
            int i3 = this.fEndBufIdx;
            if (modChunkSize == i3) {
                if (this.fBufIdx == i3 && !z) {
                    return false;
                }
                this.fEndBufIdx = modChunkSize(this.fEndBufIdx - 1);
            }
            this.fBoundaries[modChunkSize] = i;
            this.fStatuses[modChunkSize] = (short) i2;
            this.fStartBufIdx = modChunkSize;
            if (z) {
                this.fBufIdx = modChunkSize;
                this.fTextIdx = i;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean seek(int i) {
            int[] iArr = this.fBoundaries;
            int i2 = this.fStartBufIdx;
            if (i >= iArr[i2]) {
                int i3 = this.fEndBufIdx;
                if (i <= iArr[i3]) {
                    if (i == iArr[i2]) {
                        this.fBufIdx = i2;
                        this.fTextIdx = iArr[this.fBufIdx];
                        return true;
                    } else if (i == iArr[i3]) {
                        this.fBufIdx = i3;
                        this.fTextIdx = iArr[this.fBufIdx];
                        return true;
                    } else {
                        while (i2 != i3) {
                            int modChunkSize = modChunkSize(((i2 + i3) + (i2 > i3 ? 128 : 0)) / 2);
                            if (this.fBoundaries[modChunkSize] > i) {
                                i3 = modChunkSize;
                            } else {
                                i2 = modChunkSize(modChunkSize + 1);
                            }
                        }
                        this.fBufIdx = modChunkSize(i3 - 1);
                        this.fTextIdx = this.fBoundaries[this.fBufIdx];
                        return true;
                    }
                }
            }
            return false;
        }

        BreakCache(BreakCache breakCache) {
            this.fBoundaries = new int[128];
            this.fStatuses = new short[128];
            this.fSideBuffer = new DictionaryBreakEngine.DequeI();
            this.fStartBufIdx = breakCache.fStartBufIdx;
            this.fEndBufIdx = breakCache.fEndBufIdx;
            this.fTextIdx = breakCache.fTextIdx;
            this.fBufIdx = breakCache.fBufIdx;
            this.fBoundaries = (int[]) breakCache.fBoundaries.clone();
            this.fStatuses = (short[]) breakCache.fStatuses.clone();
            this.fSideBuffer = new DictionaryBreakEngine.DequeI();
        }
    }
}
