package android.icu.text;

import android.icu.impl.CharacterIteration;
import android.icu.impl.ICUBinary;
import android.icu.impl.ICUDebug;
import android.icu.impl.RBBIDataWrapper;
import android.icu.impl.Trie2;
import android.icu.lang.UCharacter;
import android.icu.lang.UProperty;
import android.icu.text.DictionaryBreakEngine;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RuleBasedBreakIterator extends BreakIterator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String RBBI_DEBUG_ARG = "rbbi";
    private static final int RBBI_END = 2;
    private static final int RBBI_RUN = 1;
    private static final int RBBI_START = 0;
    private static final int START_STATE = 1;
    private static final int STOP_STATE = 0;
    private static final boolean TRACE = (ICUDebug.enabled(RBBI_DEBUG_ARG) && ICUDebug.value(RBBI_DEBUG_ARG).indexOf("trace") >= 0);
    @Deprecated
    public static final String fDebugEnv = (ICUDebug.enabled(RBBI_DEBUG_ARG) ? ICUDebug.value(RBBI_DEBUG_ARG) : null);
    private static final List<LanguageBreakEngine> gAllBreakEngines = new ArrayList();
    private static final UnhandledBreakEngine gUnhandledBreakEngine = new UnhandledBreakEngine();
    private static final int kMaxLookaheads = 8;
    private BreakCache fBreakCache;
    private List<LanguageBreakEngine> fBreakEngines;
    private DictionaryCache fDictionaryCache;
    private int fDictionaryCharCount;
    private boolean fDone;
    private LookAheadResults fLookAheadMatches;
    private int fPosition;
    @Deprecated
    public RBBIDataWrapper fRData;
    private int fRuleStatusIndex;
    private CharacterIterator fText;

    static {
        gAllBreakEngines.add(gUnhandledBreakEngine);
    }

    private RuleBasedBreakIterator() {
        this.fText = new StringCharacterIterator("");
        this.fBreakCache = new BreakCache();
        this.fDictionaryCache = new DictionaryCache();
        this.fLookAheadMatches = new LookAheadResults();
        this.fDictionaryCharCount = 0;
        synchronized (gAllBreakEngines) {
            this.fBreakEngines = new ArrayList(gAllBreakEngines);
        }
    }

    public static RuleBasedBreakIterator getInstanceFromCompiledRules(InputStream is) throws IOException {
        RuleBasedBreakIterator This = new RuleBasedBreakIterator();
        This.fRData = RBBIDataWrapper.get(ICUBinary.getByteBufferFromInputStreamAndCloseStream(is));
        return This;
    }

    @Deprecated
    public static RuleBasedBreakIterator getInstanceFromCompiledRules(ByteBuffer bytes) throws IOException {
        RuleBasedBreakIterator This = new RuleBasedBreakIterator();
        This.fRData = RBBIDataWrapper.get(bytes);
        return This;
    }

    public RuleBasedBreakIterator(String rules) {
        this();
        try {
            ByteArrayOutputStream ruleOS = new ByteArrayOutputStream();
            compileRules(rules, ruleOS);
            this.fRData = RBBIDataWrapper.get(ByteBuffer.wrap(ruleOS.toByteArray()));
        } catch (IOException e) {
            throw new RuntimeException("RuleBasedBreakIterator rule compilation internal error: " + e.getMessage());
        }
    }

    @Override // android.icu.text.BreakIterator
    public Object clone() {
        RuleBasedBreakIterator result = (RuleBasedBreakIterator) super.clone();
        CharacterIterator characterIterator = this.fText;
        if (characterIterator != null) {
            result.fText = (CharacterIterator) characterIterator.clone();
        }
        synchronized (gAllBreakEngines) {
            result.fBreakEngines = new ArrayList(gAllBreakEngines);
        }
        result.fLookAheadMatches = new LookAheadResults();
        Objects.requireNonNull(result);
        result.fBreakCache = new BreakCache(this.fBreakCache);
        Objects.requireNonNull(result);
        result.fDictionaryCache = new DictionaryCache(this.fDictionaryCache);
        return result;
    }

    public boolean equals(Object that) {
        CharacterIterator characterIterator;
        if (that == null) {
            return false;
        }
        if (this == that) {
            return true;
        }
        try {
            RuleBasedBreakIterator other = (RuleBasedBreakIterator) that;
            if (this.fRData != other.fRData && (this.fRData == null || other.fRData == null)) {
                return false;
            }
            if (this.fRData != null && other.fRData != null && !this.fRData.fRuleSource.equals(other.fRData.fRuleSource)) {
                return false;
            }
            if (this.fText == null && other.fText == null) {
                return true;
            }
            if (!(this.fText == null || (characterIterator = other.fText) == null)) {
                if (this.fText.equals(characterIterator)) {
                    if (this.fPosition == other.fPosition) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public String toString() {
        RBBIDataWrapper rBBIDataWrapper = this.fRData;
        if (rBBIDataWrapper != null) {
            return rBBIDataWrapper.fRuleSource;
        }
        return "";
    }

    public int hashCode() {
        return this.fRData.fRuleSource.hashCode();
    }

    @Deprecated
    public void dump(PrintStream out) {
        if (out == null) {
            out = System.out;
        }
        this.fRData.dump(out);
    }

    public static void compileRules(String rules, OutputStream ruleBinary) throws IOException {
        RBBIRuleBuilder.compileRules(rules, ruleBinary);
    }

    @Override // android.icu.text.BreakIterator
    public int first() {
        CharacterIterator characterIterator = this.fText;
        if (characterIterator == null) {
            return -1;
        }
        characterIterator.first();
        int start = this.fText.getIndex();
        if (!this.fBreakCache.seek(start)) {
            this.fBreakCache.populateNear(start);
        }
        this.fBreakCache.current();
        return this.fPosition;
    }

    @Override // android.icu.text.BreakIterator
    public int last() {
        CharacterIterator characterIterator = this.fText;
        if (characterIterator == null) {
            return -1;
        }
        int endPos = characterIterator.getEndIndex();
        isBoundary(endPos);
        if (this.fPosition != endPos) {
        }
        return endPos;
    }

    @Override // android.icu.text.BreakIterator
    public int next(int n) {
        int result = 0;
        if (n > 0) {
            while (n > 0 && result != -1) {
                result = next();
                n--;
            }
            return result;
        } else if (n >= 0) {
            return current();
        } else {
            while (n < 0 && result != -1) {
                result = previous();
                n++;
            }
            return result;
        }
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
    public int previous() {
        this.fBreakCache.previous();
        if (this.fDone) {
            return -1;
        }
        return this.fPosition;
    }

    @Override // android.icu.text.BreakIterator
    public int following(int startPos) {
        if (startPos < this.fText.getBeginIndex()) {
            return first();
        }
        this.fBreakCache.following(CISetIndex32(this.fText, startPos));
        if (this.fDone) {
            return -1;
        }
        return this.fPosition;
    }

    @Override // android.icu.text.BreakIterator
    public int preceding(int offset) {
        CharacterIterator characterIterator = this.fText;
        if (characterIterator == null || offset > characterIterator.getEndIndex()) {
            return last();
        }
        if (offset < this.fText.getBeginIndex()) {
            return first();
        }
        this.fBreakCache.preceding(offset);
        if (this.fDone) {
            return -1;
        }
        return this.fPosition;
    }

    protected static final void checkOffset(int offset, CharacterIterator text) {
        if (offset < text.getBeginIndex() || offset > text.getEndIndex()) {
            throw new IllegalArgumentException("offset out of bounds");
        }
    }

    @Override // android.icu.text.BreakIterator
    public boolean isBoundary(int offset) {
        checkOffset(offset, this.fText);
        int adjustedOffset = CISetIndex32(this.fText, offset);
        boolean result = false;
        if (this.fBreakCache.seek(adjustedOffset) || this.fBreakCache.populateNear(adjustedOffset)) {
            result = this.fBreakCache.current() == offset;
        }
        if (!result) {
            next();
        }
        return result;
    }

    @Override // android.icu.text.BreakIterator
    public int current() {
        if (this.fText != null) {
            return this.fPosition;
        }
        return -1;
    }

    @Override // android.icu.text.BreakIterator
    public int getRuleStatus() {
        return this.fRData.fStatusTable[this.fRuleStatusIndex + this.fRData.fStatusTable[this.fRuleStatusIndex]];
    }

    @Override // android.icu.text.BreakIterator
    public int getRuleStatusVec(int[] fillInArray) {
        int numStatusVals = this.fRData.fStatusTable[this.fRuleStatusIndex];
        if (fillInArray != null) {
            int numToCopy = Math.min(numStatusVals, fillInArray.length);
            for (int i = 0; i < numToCopy; i++) {
                fillInArray[i] = this.fRData.fStatusTable[this.fRuleStatusIndex + i + 1];
            }
        }
        return numStatusVals;
    }

    @Override // android.icu.text.BreakIterator
    public CharacterIterator getText() {
        return this.fText;
    }

    @Override // android.icu.text.BreakIterator
    public void setText(CharacterIterator newText) {
        if (newText != null) {
            this.fBreakCache.reset(newText.getBeginIndex(), 0);
        } else {
            this.fBreakCache.reset();
        }
        this.fDictionaryCache.reset();
        this.fText = newText;
        first();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private LanguageBreakEngine getLanguageBreakEngine(int c) {
        LanguageBreakEngine eng;
        for (LanguageBreakEngine candidate : this.fBreakEngines) {
            if (candidate.handles(c)) {
                return candidate;
            }
        }
        synchronized (gAllBreakEngines) {
            for (LanguageBreakEngine candidate2 : gAllBreakEngines) {
                if (candidate2.handles(c)) {
                    this.fBreakEngines.add(candidate2);
                    return candidate2;
                }
            }
            int script = UCharacter.getIntPropertyValue(c, UProperty.SCRIPT);
            if (script == 22 || script == 20) {
                script = 17;
            }
            if (script == 17) {
                eng = new CjkBreakEngine(false);
            } else if (script == 18) {
                eng = new CjkBreakEngine(true);
            } else if (script == 23) {
                eng = new KhmerBreakEngine();
            } else if (script == 24) {
                eng = new LaoBreakEngine();
            } else if (script == 28) {
                eng = new BurmeseBreakEngine();
            } else if (script != 38) {
                try {
                    gUnhandledBreakEngine.handleChar(c);
                    eng = gUnhandledBreakEngine;
                } catch (IOException e) {
                    eng = null;
                }
            } else {
                eng = new ThaiBreakEngine();
            }
            if (!(eng == null || eng == gUnhandledBreakEngine)) {
                gAllBreakEngines.add(eng);
                this.fBreakEngines.add(eng);
            }
            return eng;
        }
    }

    /* access modifiers changed from: private */
    public static class LookAheadResults {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        int[] fKeys = new int[8];
        int[] fPositions = new int[8];
        int fUsedSlotLimit = 0;

        LookAheadResults() {
        }

        /* access modifiers changed from: package-private */
        public int getPosition(int key) {
            for (int i = 0; i < this.fUsedSlotLimit; i++) {
                if (this.fKeys[i] == key) {
                    return this.fPositions[i];
                }
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public void setPosition(int key, int position) {
            int i = 0;
            while (i < this.fUsedSlotLimit) {
                if (this.fKeys[i] == key) {
                    this.fPositions[i] = position;
                    return;
                }
                i++;
            }
            if (i >= 8) {
                i = 7;
            }
            this.fKeys[i] = key;
            this.fPositions[i] = position;
            this.fUsedSlotLimit = i + 1;
        }

        /* access modifiers changed from: package-private */
        public void reset() {
            this.fUsedSlotLimit = 0;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int handleNext() {
        int mode;
        int mode2;
        int pos;
        int lookaheadResult;
        int mode3;
        if (TRACE) {
            System.out.println("Handle Next   pos      char  state category");
        }
        this.fRuleStatusIndex = 0;
        this.fDictionaryCharCount = 0;
        CharacterIterator text = this.fText;
        Trie2 trie = this.fRData.fTrie;
        short[] stateTable = this.fRData.fFTable.fTable;
        int initialPosition = this.fPosition;
        text.setIndex(initialPosition);
        int result = initialPosition;
        int c = text.current();
        if (c < 55296 || (c = CharacterIteration.nextTrail32(text, c)) != Integer.MAX_VALUE) {
            short s = 1;
            int row = this.fRData.getRowIndex(1);
            short category = 3;
            int mode4 = 1;
            if ((this.fRData.fFTable.fFlags & 2) != 0) {
                category = 2;
                mode4 = 0;
                if (TRACE) {
                    System.out.print("            " + RBBIDataWrapper.intToString(text.getIndex(), 5));
                    System.out.print(RBBIDataWrapper.intToHexString(c, 10));
                    System.out.println(RBBIDataWrapper.intToString(1, 7) + RBBIDataWrapper.intToString(2, 6));
                }
            }
            this.fLookAheadMatches.reset();
            int mode5 = mode4;
            while (true) {
                if (s == 0) {
                    break;
                }
                if (c == Integer.MAX_VALUE) {
                    if (mode5 == 2) {
                        break;
                    }
                    mode = 2;
                    category = 1;
                } else if (mode5 == 1) {
                    short category2 = (short) trie.get(c);
                    if ((category2 & 16384) != 0) {
                        this.fDictionaryCharCount++;
                        category2 = (short) (category2 & -16385);
                    }
                    if (TRACE) {
                        PrintStream printStream = System.out;
                        StringBuilder sb = new StringBuilder();
                        sb.append("            ");
                        mode3 = mode5;
                        sb.append(RBBIDataWrapper.intToString(text.getIndex(), 5));
                        printStream.print(sb.toString());
                        System.out.print(RBBIDataWrapper.intToHexString(c, 10));
                        System.out.println(RBBIDataWrapper.intToString(s, 7) + RBBIDataWrapper.intToString(category2, 6));
                    } else {
                        mode3 = mode5;
                    }
                    int c2 = text.next();
                    if (c2 >= 55296) {
                        c = CharacterIteration.nextTrail32(text, c2);
                        category = category2;
                        mode = mode3;
                    } else {
                        c = c2;
                        category = category2;
                        mode = mode3;
                    }
                } else {
                    mode = 1;
                }
                s = stateTable[row + 4 + category];
                row = this.fRData.getRowIndex(s);
                if (stateTable[row + 0] == -1) {
                    result = text.getIndex();
                    if (c >= 65536 && c <= 1114111) {
                        result--;
                    }
                    this.fRuleStatusIndex = stateTable[row + 2];
                }
                short s2 = stateTable[row + 0];
                if (s2 <= 0 || (lookaheadResult = this.fLookAheadMatches.getPosition(s2)) < 0) {
                    short s3 = stateTable[row + 1];
                    if (s3 != 0) {
                        int pos2 = text.getIndex();
                        if (c < 65536 || c > 1114111) {
                            pos = pos2;
                        } else {
                            pos = pos2 - 1;
                        }
                        mode2 = mode;
                        this.fLookAheadMatches.setPosition(s3, pos);
                    } else {
                        mode2 = mode;
                    }
                    mode5 = mode2;
                } else {
                    this.fRuleStatusIndex = stateTable[row + 2];
                    this.fPosition = lookaheadResult;
                    return lookaheadResult;
                }
            }
            if (result == initialPosition) {
                if (TRACE) {
                    System.out.println("Iterator did not move. Advancing by 1.");
                }
                text.setIndex(initialPosition);
                CharacterIteration.next32(text);
                result = text.getIndex();
                this.fRuleStatusIndex = 0;
            }
            this.fPosition = result;
            if (TRACE) {
                System.out.println("result = " + result);
            }
            return result;
        }
        this.fDone = true;
        return -1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private int handleSafePrevious(int fromPosition) {
        CharacterIterator text = this.fText;
        Trie2 trie = this.fRData.fTrie;
        short[] stateTable = this.fRData.fRTable.fTable;
        CISetIndex32(text, fromPosition);
        if (TRACE) {
            System.out.print("Handle Previous   pos   char  state category");
        }
        if (text.getIndex() == text.getBeginIndex()) {
            return -1;
        }
        short s = 1;
        int row = this.fRData.getRowIndex(1);
        for (int c = CharacterIteration.previous32(text); c != Integer.MAX_VALUE; c = CharacterIteration.previous32(text)) {
            short category = (short) (((short) trie.get(c)) & -16385);
            if (TRACE) {
                PrintStream printStream = System.out;
                printStream.print("            " + RBBIDataWrapper.intToString(text.getIndex(), 5));
                System.out.print(RBBIDataWrapper.intToHexString(c, 10));
                PrintStream printStream2 = System.out;
                printStream2.println(RBBIDataWrapper.intToString(s, 7) + RBBIDataWrapper.intToString(category, 6));
            }
            s = stateTable[row + 4 + category];
            row = this.fRData.getRowIndex(s);
            if (s == 0) {
                break;
            }
        }
        int result = text.getIndex();
        if (TRACE) {
            PrintStream printStream3 = System.out;
            printStream3.println("result = " + result);
        }
        return result;
    }

    private static int CISetIndex32(CharacterIterator ci, int index) {
        if (index <= ci.getBeginIndex()) {
            ci.first();
        } else if (index >= ci.getEndIndex()) {
            ci.setIndex(ci.getEndIndex());
        } else if (Character.isLowSurrogate(ci.setIndex(index)) && !Character.isHighSurrogate(ci.previous())) {
            ci.next();
        }
        return ci.getIndex();
    }

    /* access modifiers changed from: package-private */
    public class DictionaryCache {
        static final /* synthetic */ boolean $assertionsDisabled = false;
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
        public boolean following(int fromPos) {
            if (fromPos >= this.fLimit || fromPos < this.fStart) {
                this.fPositionInCache = -1;
                return false;
            }
            int i = this.fPositionInCache;
            if (i < 0 || i >= this.fBreaks.size() || this.fBreaks.elementAt(this.fPositionInCache) != fromPos) {
                this.fPositionInCache = 0;
                while (this.fPositionInCache < this.fBreaks.size()) {
                    int r = this.fBreaks.elementAt(this.fPositionInCache);
                    if (r > fromPos) {
                        this.fBoundary = r;
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
        public boolean preceding(int fromPos) {
            int i;
            if (fromPos <= this.fStart || fromPos > (i = this.fLimit)) {
                this.fPositionInCache = -1;
                return false;
            }
            if (fromPos == i) {
                this.fPositionInCache = this.fBreaks.size() - 1;
                if (this.fPositionInCache >= 0) {
                }
            }
            int i2 = this.fPositionInCache;
            if (i2 > 0 && i2 < this.fBreaks.size() && this.fBreaks.elementAt(this.fPositionInCache) == fromPos) {
                this.fPositionInCache--;
                int r = this.fBreaks.elementAt(this.fPositionInCache);
                this.fBoundary = r;
                this.fStatusIndex = r == this.fStart ? this.fFirstRuleStatusIndex : this.fOtherRuleStatusIndex;
                return true;
            } else if (this.fPositionInCache == 0) {
                this.fPositionInCache = -1;
                return false;
            } else {
                this.fPositionInCache = this.fBreaks.size() - 1;
                while (true) {
                    int i3 = this.fPositionInCache;
                    if (i3 >= 0) {
                        int r2 = this.fBreaks.elementAt(i3);
                        if (r2 < fromPos) {
                            this.fBoundary = r2;
                            this.fStatusIndex = r2 == this.fStart ? this.fFirstRuleStatusIndex : this.fOtherRuleStatusIndex;
                            return true;
                        }
                        this.fPositionInCache--;
                    } else {
                        this.fPositionInCache = -1;
                        return false;
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void populateDictionary(int startPos, int endPos, int firstRuleStatus, int otherRuleStatus) {
            if (endPos - startPos > 1) {
                reset();
                this.fFirstRuleStatusIndex = firstRuleStatus;
                this.fOtherRuleStatusIndex = otherRuleStatus;
                int foundBreakCount = 0;
                RuleBasedBreakIterator.this.fText.setIndex(startPos);
                int c = CharacterIteration.current32(RuleBasedBreakIterator.this.fText);
                int category = (short) RuleBasedBreakIterator.this.fRData.fTrie.get(c);
                while (true) {
                    int current = RuleBasedBreakIterator.this.fText.getIndex();
                    if (current < endPos && (category & 16384) == 0) {
                        c = CharacterIteration.next32(RuleBasedBreakIterator.this.fText);
                        category = (short) RuleBasedBreakIterator.this.fRData.fTrie.get(c);
                    } else if (current >= endPos) {
                        break;
                    } else {
                        LanguageBreakEngine lbe = RuleBasedBreakIterator.this.getLanguageBreakEngine(c);
                        if (lbe != null) {
                            foundBreakCount += lbe.findBreaks(RuleBasedBreakIterator.this.fText, startPos, endPos, this.fBreaks);
                        }
                        c = CharacterIteration.current32(RuleBasedBreakIterator.this.fText);
                        category = (short) RuleBasedBreakIterator.this.fRData.fTrie.get(c);
                    }
                }
                if (foundBreakCount > 0) {
                    if (startPos < this.fBreaks.elementAt(0)) {
                        this.fBreaks.offer(startPos);
                    }
                    if (endPos > this.fBreaks.peek()) {
                        this.fBreaks.push(endPos);
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

        DictionaryCache(DictionaryCache src) {
            try {
                this.fBreaks = (DictionaryBreakEngine.DequeI) src.fBreaks.clone();
                this.fPositionInCache = src.fPositionInCache;
                this.fStart = src.fStart;
                this.fLimit = src.fLimit;
                this.fFirstRuleStatusIndex = src.fFirstRuleStatusIndex;
                this.fOtherRuleStatusIndex = src.fOtherRuleStatusIndex;
                this.fBoundary = src.fBoundary;
                this.fStatusIndex = src.fStatusIndex;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public class BreakCache {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        static final int CACHE_SIZE = 128;
        static final boolean RetainCachePosition = false;
        static final boolean UpdateCachePosition = true;
        int[] fBoundaries;
        int fBufIdx;
        int fEndBufIdx;
        DictionaryBreakEngine.DequeI fSideBuffer;
        int fStartBufIdx;
        short[] fStatuses;
        int fTextIdx;

        BreakCache() {
            this.fBoundaries = new int[128];
            this.fStatuses = new short[128];
            this.fSideBuffer = new DictionaryBreakEngine.DequeI();
            reset();
        }

        /* access modifiers changed from: package-private */
        public void reset(int pos, int ruleStatus) {
            this.fStartBufIdx = 0;
            this.fEndBufIdx = 0;
            this.fTextIdx = pos;
            this.fBufIdx = 0;
            this.fBoundaries[0] = pos;
            this.fStatuses[0] = (short) ruleStatus;
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
            this.fTextIdx = RuleBasedBreakIterator.this.fPosition = this.fBoundaries[this.fBufIdx];
            RuleBasedBreakIterator.this.fRuleStatusIndex = this.fStatuses[this.fBufIdx];
        }

        /* access modifiers changed from: package-private */
        public void previous() {
            int initialBufIdx = this.fBufIdx;
            int i = this.fBufIdx;
            boolean z = true;
            if (i == this.fStartBufIdx) {
                populatePreceding();
            } else {
                this.fBufIdx = modChunkSize(i - 1);
                this.fTextIdx = this.fBoundaries[this.fBufIdx];
            }
            RuleBasedBreakIterator ruleBasedBreakIterator = RuleBasedBreakIterator.this;
            if (this.fBufIdx != initialBufIdx) {
                z = false;
            }
            ruleBasedBreakIterator.fDone = z;
            RuleBasedBreakIterator.this.fPosition = this.fTextIdx;
            RuleBasedBreakIterator.this.fRuleStatusIndex = this.fStatuses[this.fBufIdx];
        }

        /* access modifiers changed from: package-private */
        public void following(int startPos) {
            if (startPos == this.fTextIdx || seek(startPos) || populateNear(startPos)) {
                RuleBasedBreakIterator.this.fDone = false;
                next();
            }
        }

        /* access modifiers changed from: package-private */
        public void preceding(int startPos) {
            if (startPos != this.fTextIdx && !seek(startPos) && !populateNear(startPos)) {
                return;
            }
            if (startPos == this.fTextIdx) {
                previous();
            } else {
                current();
            }
        }

        /* access modifiers changed from: package-private */
        public int current() {
            RuleBasedBreakIterator.this.fPosition = this.fTextIdx;
            RuleBasedBreakIterator.this.fRuleStatusIndex = this.fStatuses[this.fBufIdx];
            RuleBasedBreakIterator.this.fDone = false;
            return this.fTextIdx;
        }

        /* access modifiers changed from: package-private */
        public boolean populateNear(int position) {
            int[] iArr;
            int i;
            int i2;
            int[] iArr2 = this.fBoundaries;
            if (position < iArr2[this.fStartBufIdx] - 15 || position > iArr2[this.fEndBufIdx] + 15) {
                int aBoundary = RuleBasedBreakIterator.this.fText.getBeginIndex();
                int ruleStatusIndex = 0;
                if (position > aBoundary + 20) {
                    int backupPos = RuleBasedBreakIterator.this.handleSafePrevious(position);
                    if (backupPos > aBoundary) {
                        RuleBasedBreakIterator.this.fPosition = backupPos;
                        aBoundary = RuleBasedBreakIterator.this.handleNext();
                        if (aBoundary == backupPos + 1 || (aBoundary == backupPos + 2 && Character.isHighSurrogate(RuleBasedBreakIterator.this.fText.setIndex(backupPos)) && Character.isLowSurrogate(RuleBasedBreakIterator.this.fText.next()))) {
                            aBoundary = RuleBasedBreakIterator.this.handleNext();
                        }
                    }
                    ruleStatusIndex = RuleBasedBreakIterator.this.fRuleStatusIndex;
                }
                reset(aBoundary, ruleStatusIndex);
            }
            int[] iArr3 = this.fBoundaries;
            if (iArr3[this.fEndBufIdx] < position) {
                do {
                    int[] iArr4 = this.fBoundaries;
                    int i3 = this.fEndBufIdx;
                    if (iArr4[i3] >= position) {
                        this.fBufIdx = i3;
                        this.fTextIdx = iArr4[this.fBufIdx];
                        while (this.fTextIdx > position) {
                            previous();
                        }
                        return true;
                    }
                } while (populateFollowing());
                return false;
            } else if (iArr3[this.fStartBufIdx] <= position) {
                return true;
            } else {
                while (true) {
                    iArr = this.fBoundaries;
                    i = this.fStartBufIdx;
                    if (iArr[i] <= position) {
                        break;
                    }
                    populatePreceding();
                }
                this.fBufIdx = i;
                this.fTextIdx = iArr[this.fBufIdx];
                while (true) {
                    i2 = this.fTextIdx;
                    if (i2 >= position) {
                        break;
                    }
                    next();
                }
                if (i2 > position) {
                    previous();
                }
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean populateFollowing() {
            int pos;
            int[] iArr = this.fBoundaries;
            int i = this.fEndBufIdx;
            int fromPosition = iArr[i];
            short s = this.fStatuses[i];
            if (RuleBasedBreakIterator.this.fDictionaryCache.following(fromPosition)) {
                addFollowing(RuleBasedBreakIterator.this.fDictionaryCache.fBoundary, RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex, true);
                return true;
            }
            RuleBasedBreakIterator.this.fPosition = fromPosition;
            int pos2 = RuleBasedBreakIterator.this.handleNext();
            if (pos2 == -1) {
                return false;
            }
            int ruleStatusIdx = RuleBasedBreakIterator.this.fRuleStatusIndex;
            if (RuleBasedBreakIterator.this.fDictionaryCharCount > 0) {
                RuleBasedBreakIterator.this.fDictionaryCache.populateDictionary(fromPosition, pos2, s, ruleStatusIdx);
                if (RuleBasedBreakIterator.this.fDictionaryCache.following(fromPosition)) {
                    addFollowing(RuleBasedBreakIterator.this.fDictionaryCache.fBoundary, RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex, true);
                    return true;
                }
            }
            addFollowing(pos2, ruleStatusIdx, true);
            for (int count = 0; count < 6 && (pos = RuleBasedBreakIterator.this.handleNext()) != -1 && RuleBasedBreakIterator.this.fDictionaryCharCount <= 0; count++) {
                addFollowing(pos, RuleBasedBreakIterator.this.fRuleStatusIndex, false);
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean populatePreceding() {
            int positionStatusIdx;
            int position;
            int textBegin = RuleBasedBreakIterator.this.fText.getBeginIndex();
            int fromPosition = this.fBoundaries[this.fStartBufIdx];
            if (fromPosition == textBegin) {
                return false;
            }
            if (RuleBasedBreakIterator.this.fDictionaryCache.preceding(fromPosition)) {
                addPreceding(RuleBasedBreakIterator.this.fDictionaryCache.fBoundary, RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex, true);
                return true;
            }
            int backupPosition = fromPosition;
            do {
                int backupPosition2 = backupPosition - 30;
                if (backupPosition2 <= textBegin) {
                    backupPosition = textBegin;
                } else {
                    backupPosition = RuleBasedBreakIterator.this.handleSafePrevious(backupPosition2);
                }
                if (backupPosition == -1 || backupPosition == textBegin) {
                    position = textBegin;
                    positionStatusIdx = 0;
                    continue;
                } else {
                    RuleBasedBreakIterator.this.fPosition = backupPosition;
                    position = RuleBasedBreakIterator.this.handleNext();
                    if (position == backupPosition + 1 || (position == backupPosition + 2 && Character.isHighSurrogate(RuleBasedBreakIterator.this.fText.setIndex(backupPosition)) && Character.isLowSurrogate(RuleBasedBreakIterator.this.fText.next()))) {
                        position = RuleBasedBreakIterator.this.handleNext();
                    }
                    positionStatusIdx = RuleBasedBreakIterator.this.fRuleStatusIndex;
                    continue;
                }
            } while (position >= fromPosition);
            this.fSideBuffer.removeAllElements();
            this.fSideBuffer.push(position);
            this.fSideBuffer.push(positionStatusIdx);
            do {
                int prevPosition = RuleBasedBreakIterator.this.fPosition = position;
                position = RuleBasedBreakIterator.this.handleNext();
                positionStatusIdx = RuleBasedBreakIterator.this.fRuleStatusIndex;
                if (position == -1) {
                    break;
                }
                boolean segmentHandledByDictionary = false;
                if (RuleBasedBreakIterator.this.fDictionaryCharCount != 0) {
                    RuleBasedBreakIterator.this.fDictionaryCache.populateDictionary(prevPosition, position, positionStatusIdx, positionStatusIdx);
                    while (RuleBasedBreakIterator.this.fDictionaryCache.following(prevPosition)) {
                        position = RuleBasedBreakIterator.this.fDictionaryCache.fBoundary;
                        positionStatusIdx = RuleBasedBreakIterator.this.fDictionaryCache.fStatusIndex;
                        segmentHandledByDictionary = true;
                        if (position >= fromPosition) {
                            break;
                        }
                        this.fSideBuffer.push(position);
                        this.fSideBuffer.push(positionStatusIdx);
                        prevPosition = position;
                    }
                }
                if (!segmentHandledByDictionary && position < fromPosition) {
                    this.fSideBuffer.push(position);
                    this.fSideBuffer.push(positionStatusIdx);
                    continue;
                }
            } while (position < fromPosition);
            boolean success = false;
            if (!this.fSideBuffer.isEmpty()) {
                addPreceding(this.fSideBuffer.pop(), this.fSideBuffer.pop(), true);
                success = true;
            }
            while (!this.fSideBuffer.isEmpty()) {
                if (!addPreceding(this.fSideBuffer.pop(), this.fSideBuffer.pop(), false)) {
                    break;
                }
            }
            return success;
        }

        /* access modifiers changed from: package-private */
        public void addFollowing(int position, int ruleStatusIdx, boolean update) {
            int nextIdx = modChunkSize(this.fEndBufIdx + 1);
            int i = this.fStartBufIdx;
            if (nextIdx == i) {
                this.fStartBufIdx = modChunkSize(i + 6);
            }
            this.fBoundaries[nextIdx] = position;
            this.fStatuses[nextIdx] = (short) ruleStatusIdx;
            this.fEndBufIdx = nextIdx;
            if (update) {
                this.fBufIdx = nextIdx;
                this.fTextIdx = position;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean addPreceding(int position, int ruleStatusIdx, boolean update) {
            int nextIdx = modChunkSize(this.fStartBufIdx - 1);
            int i = this.fEndBufIdx;
            if (nextIdx == i) {
                if (this.fBufIdx == i && !update) {
                    return false;
                }
                this.fEndBufIdx = modChunkSize(this.fEndBufIdx - 1);
            }
            this.fBoundaries[nextIdx] = position;
            this.fStatuses[nextIdx] = (short) ruleStatusIdx;
            this.fStartBufIdx = nextIdx;
            if (update) {
                this.fBufIdx = nextIdx;
                this.fTextIdx = position;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean seek(int pos) {
            int[] iArr = this.fBoundaries;
            int i = this.fStartBufIdx;
            if (pos >= iArr[i]) {
                int i2 = this.fEndBufIdx;
                if (pos <= iArr[i2]) {
                    if (pos == iArr[i]) {
                        this.fBufIdx = i;
                        this.fTextIdx = iArr[this.fBufIdx];
                        return true;
                    } else if (pos == iArr[i2]) {
                        this.fBufIdx = i2;
                        this.fTextIdx = iArr[this.fBufIdx];
                        return true;
                    } else {
                        int min = this.fStartBufIdx;
                        int max = this.fEndBufIdx;
                        while (min != max) {
                            int probe = modChunkSize(((min + max) + (min > max ? 128 : 0)) / 2);
                            if (this.fBoundaries[probe] > pos) {
                                max = probe;
                            } else {
                                min = modChunkSize(probe + 1);
                            }
                        }
                        this.fBufIdx = modChunkSize(max - 1);
                        this.fTextIdx = this.fBoundaries[this.fBufIdx];
                        return true;
                    }
                }
            }
            return false;
        }

        BreakCache(BreakCache src) {
            this.fBoundaries = new int[128];
            this.fStatuses = new short[128];
            this.fSideBuffer = new DictionaryBreakEngine.DequeI();
            this.fStartBufIdx = src.fStartBufIdx;
            this.fEndBufIdx = src.fEndBufIdx;
            this.fTextIdx = src.fTextIdx;
            this.fBufIdx = src.fBufIdx;
            this.fBoundaries = (int[]) src.fBoundaries.clone();
            this.fStatuses = (short[]) src.fStatuses.clone();
            this.fSideBuffer = new DictionaryBreakEngine.DequeI();
        }

        /* access modifiers changed from: package-private */
        public void dumpCache() {
            System.out.printf("fTextIdx:%d   fBufIdx:%d%n", Integer.valueOf(this.fTextIdx), Integer.valueOf(this.fBufIdx));
            int i = this.fStartBufIdx;
            while (true) {
                System.out.printf("%d  %d%n", Integer.valueOf(i), Integer.valueOf(this.fBoundaries[i]));
                if (i != this.fEndBufIdx) {
                    i = modChunkSize(i + 1);
                } else {
                    return;
                }
            }
        }

        private final int modChunkSize(int index) {
            return index & 127;
        }
    }
}
