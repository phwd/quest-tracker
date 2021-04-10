package android.icu.impl;

import android.icu.impl.ICUBinary;
import android.icu.text.DateTimePatternGenerator;
import android.icu.text.UTF16;
import android.icu.text.UnicodeSet;
import android.icu.util.CodePointMap;
import android.icu.util.CodePointTrie;
import android.icu.util.ICUUncheckedIOException;
import android.icu.util.MutableCodePointTrie;
import android.icu.util.VersionInfo;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public final class Normalizer2Impl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int CANON_HAS_COMPOSITIONS = 1073741824;
    private static final int CANON_HAS_SET = 2097152;
    private static final int CANON_NOT_SEGMENT_STARTER = Integer.MIN_VALUE;
    private static final int CANON_VALUE_MASK = 2097151;
    public static final int COMP_1_LAST_TUPLE = 32768;
    public static final int COMP_1_TRAIL_LIMIT = 13312;
    public static final int COMP_1_TRAIL_MASK = 32766;
    public static final int COMP_1_TRAIL_SHIFT = 9;
    public static final int COMP_1_TRIPLE = 1;
    public static final int COMP_2_TRAIL_MASK = 65472;
    public static final int COMP_2_TRAIL_SHIFT = 6;
    private static final int DATA_FORMAT = 1316121906;
    public static final int DELTA_SHIFT = 3;
    public static final int DELTA_TCCC_0 = 0;
    public static final int DELTA_TCCC_1 = 2;
    public static final int DELTA_TCCC_GT_1 = 4;
    public static final int DELTA_TCCC_MASK = 6;
    public static final int HAS_COMP_BOUNDARY_AFTER = 1;
    public static final int INERT = 1;
    private static final IsAcceptable IS_ACCEPTABLE = new IsAcceptable();
    public static final int IX_COUNT = 20;
    public static final int IX_EXTRA_DATA_OFFSET = 1;
    public static final int IX_LIMIT_NO_NO = 12;
    public static final int IX_MIN_COMP_NO_MAYBE_CP = 9;
    public static final int IX_MIN_DECOMP_NO_CP = 8;
    public static final int IX_MIN_LCCC_CP = 18;
    public static final int IX_MIN_MAYBE_YES = 13;
    public static final int IX_MIN_NO_NO = 11;
    public static final int IX_MIN_NO_NO_COMP_BOUNDARY_BEFORE = 15;
    public static final int IX_MIN_NO_NO_COMP_NO_MAYBE_CC = 16;
    public static final int IX_MIN_NO_NO_EMPTY = 17;
    public static final int IX_MIN_YES_NO = 10;
    public static final int IX_MIN_YES_NO_MAPPINGS_ONLY = 14;
    public static final int IX_NORM_TRIE_OFFSET = 0;
    public static final int IX_RESERVED3_OFFSET = 3;
    public static final int IX_SMALL_FCD_OFFSET = 2;
    public static final int IX_TOTAL_SIZE = 7;
    public static final int JAMO_L = 2;
    public static final int JAMO_VT = 65024;
    public static final int MAPPING_HAS_CCC_LCCC_WORD = 128;
    public static final int MAPPING_HAS_RAW_MAPPING = 64;
    public static final int MAPPING_LENGTH_MASK = 31;
    public static final int MAX_DELTA = 64;
    public static final int MIN_NORMAL_MAYBE_YES = 64512;
    public static final int MIN_YES_YES_WITH_CC = 65026;
    public static final int OFFSET_SHIFT = 1;
    private static final CodePointMap.ValueFilter segmentStarterMapper = new CodePointMap.ValueFilter() {
        /* class android.icu.impl.Normalizer2Impl.AnonymousClass1 */

        @Override // android.icu.util.CodePointMap.ValueFilter
        public int apply(int value) {
            return Integer.MIN_VALUE & value;
        }
    };
    private CodePointTrie canonIterData;
    private ArrayList<UnicodeSet> canonStartSets;
    private int centerNoNoDelta;
    private VersionInfo dataVersion;
    private String extraData;
    private int limitNoNo;
    private String maybeYesCompositions;
    private int minCompNoMaybeCP;
    private int minDecompNoCP;
    private int minLcccCP;
    private int minMaybeYes;
    private int minNoNo;
    private int minNoNoCompBoundaryBefore;
    private int minNoNoCompNoMaybeCC;
    private int minNoNoEmpty;
    private int minYesNo;
    private int minYesNoMappingsOnly;
    private CodePointTrie.Fast16 normTrie;
    private byte[] smallFCD;

    public static final class Hangul {
        public static final int HANGUL_BASE = 44032;
        public static final int HANGUL_COUNT = 11172;
        public static final int HANGUL_END = 55203;
        public static final int HANGUL_LIMIT = 55204;
        public static final int JAMO_L_BASE = 4352;
        public static final int JAMO_L_COUNT = 19;
        public static final int JAMO_L_END = 4370;
        public static final int JAMO_L_LIMIT = 4371;
        public static final int JAMO_T_BASE = 4519;
        public static final int JAMO_T_COUNT = 28;
        public static final int JAMO_T_END = 4546;
        public static final int JAMO_VT_COUNT = 588;
        public static final int JAMO_V_BASE = 4449;
        public static final int JAMO_V_COUNT = 21;
        public static final int JAMO_V_END = 4469;
        public static final int JAMO_V_LIMIT = 4470;

        public static boolean isHangul(int c) {
            return 44032 <= c && c < 55204;
        }

        public static boolean isHangulLV(int c) {
            int c2 = c - HANGUL_BASE;
            return c2 >= 0 && c2 < 11172 && c2 % 28 == 0;
        }

        public static boolean isJamoL(int c) {
            return 4352 <= c && c < 4371;
        }

        public static boolean isJamoV(int c) {
            return 4449 <= c && c < 4470;
        }

        public static boolean isJamoT(int c) {
            int t = c - 4519;
            return t > 0 && t < 28;
        }

        public static boolean isJamo(int c) {
            return 4352 <= c && c <= 4546 && (c <= 4370 || ((4449 <= c && c <= 4469) || 4519 < c));
        }

        public static int decompose(int c, Appendable buffer) {
            int c2 = c - HANGUL_BASE;
            try {
                int c22 = c2 % 28;
                int c3 = c2 / 28;
                buffer.append((char) ((c3 / 21) + JAMO_L_BASE));
                buffer.append((char) ((c3 % 21) + JAMO_V_BASE));
                if (c22 == 0) {
                    return 2;
                }
                buffer.append((char) (c22 + JAMO_T_BASE));
                return 3;
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }

        public static void getRawDecomposition(int c, Appendable buffer) {
            int c2 = c - HANGUL_BASE;
            try {
                int c22 = c2 % 28;
                if (c22 == 0) {
                    int c3 = c2 / 28;
                    buffer.append((char) ((c3 / 21) + JAMO_L_BASE));
                    buffer.append((char) ((c3 % 21) + JAMO_V_BASE));
                    return;
                }
                buffer.append((char) (c - c22));
                buffer.append((char) (c22 + JAMO_T_BASE));
            } catch (IOException e) {
                throw new ICUUncheckedIOException(e);
            }
        }
    }

    public static final class ReorderingBuffer implements Appendable {
        private final Appendable app;
        private final boolean appIsStringBuilder;
        private int codePointLimit;
        private int codePointStart;
        private final Normalizer2Impl impl;
        private int lastCC;
        private int reorderStart;
        private final StringBuilder str;

        public ReorderingBuffer(Normalizer2Impl ni, Appendable dest, int destCapacity) {
            this.impl = ni;
            this.app = dest;
            if (this.app instanceof StringBuilder) {
                this.appIsStringBuilder = true;
                this.str = (StringBuilder) dest;
                this.str.ensureCapacity(destCapacity);
                this.reorderStart = 0;
                if (this.str.length() == 0) {
                    this.lastCC = 0;
                    return;
                }
                setIterator();
                this.lastCC = previousCC();
                if (this.lastCC > 1) {
                    do {
                    } while (previousCC() > 1);
                }
                this.reorderStart = this.codePointLimit;
                return;
            }
            this.appIsStringBuilder = false;
            this.str = new StringBuilder();
            this.reorderStart = 0;
            this.lastCC = 0;
        }

        public boolean isEmpty() {
            return this.str.length() == 0;
        }

        public int length() {
            return this.str.length();
        }

        public int getLastCC() {
            return this.lastCC;
        }

        public StringBuilder getStringBuilder() {
            return this.str;
        }

        public boolean equals(CharSequence s, int start, int limit) {
            StringBuilder sb = this.str;
            return UTF16Plus.equal(sb, 0, sb.length(), s, start, limit);
        }

        public void append(int c, int cc) {
            if (this.lastCC <= cc || cc == 0) {
                this.str.appendCodePoint(c);
                this.lastCC = cc;
                if (cc <= 1) {
                    this.reorderStart = this.str.length();
                    return;
                }
                return;
            }
            insert(c, cc);
        }

        public void append(CharSequence s, int start, int limit, boolean isNFD, int leadCC, int trailCC) {
            int leadCC2;
            if (start != limit) {
                if (this.lastCC <= leadCC || leadCC == 0) {
                    if (trailCC <= 1) {
                        this.reorderStart = this.str.length() + (limit - start);
                    } else if (leadCC <= 1) {
                        this.reorderStart = this.str.length() + 1;
                    }
                    this.str.append(s, start, limit);
                    this.lastCC = trailCC;
                    return;
                }
                int c = Character.codePointAt(s, start);
                int start2 = start + Character.charCount(c);
                insert(c, leadCC);
                while (start2 < limit) {
                    int c2 = Character.codePointAt(s, start2);
                    start2 += Character.charCount(c2);
                    if (start2 >= limit) {
                        leadCC2 = trailCC;
                    } else if (isNFD) {
                        leadCC2 = Normalizer2Impl.getCCFromYesOrMaybe(this.impl.getNorm16(c2));
                    } else {
                        Normalizer2Impl normalizer2Impl = this.impl;
                        leadCC2 = normalizer2Impl.getCC(normalizer2Impl.getNorm16(c2));
                    }
                    append(c2, leadCC2);
                }
            }
        }

        @Override // java.lang.Appendable
        public ReorderingBuffer append(char c) {
            this.str.append(c);
            this.lastCC = 0;
            this.reorderStart = this.str.length();
            return this;
        }

        public void appendZeroCC(int c) {
            this.str.appendCodePoint(c);
            this.lastCC = 0;
            this.reorderStart = this.str.length();
        }

        @Override // java.lang.Appendable
        public ReorderingBuffer append(CharSequence s) {
            if (s.length() != 0) {
                this.str.append(s);
                this.lastCC = 0;
                this.reorderStart = this.str.length();
            }
            return this;
        }

        @Override // java.lang.Appendable
        public ReorderingBuffer append(CharSequence s, int start, int limit) {
            if (start != limit) {
                this.str.append(s, start, limit);
                this.lastCC = 0;
                this.reorderStart = this.str.length();
            }
            return this;
        }

        public void flush() {
            if (this.appIsStringBuilder) {
                this.reorderStart = this.str.length();
            } else {
                try {
                    this.app.append(this.str);
                    this.str.setLength(0);
                    this.reorderStart = 0;
                } catch (IOException e) {
                    throw new ICUUncheckedIOException(e);
                }
            }
            this.lastCC = 0;
        }

        public ReorderingBuffer flushAndAppendZeroCC(CharSequence s, int start, int limit) {
            if (this.appIsStringBuilder) {
                this.str.append(s, start, limit);
                this.reorderStart = this.str.length();
            } else {
                try {
                    this.app.append(this.str).append(s, start, limit);
                    this.str.setLength(0);
                    this.reorderStart = 0;
                } catch (IOException e) {
                    throw new ICUUncheckedIOException(e);
                }
            }
            this.lastCC = 0;
            return this;
        }

        public void remove() {
            this.str.setLength(0);
            this.lastCC = 0;
            this.reorderStart = 0;
        }

        public void removeSuffix(int suffixLength) {
            int oldLength = this.str.length();
            this.str.delete(oldLength - suffixLength, oldLength);
            this.lastCC = 0;
            this.reorderStart = this.str.length();
        }

        private void insert(int c, int cc) {
            setIterator();
            skipPrevious();
            do {
            } while (previousCC() > cc);
            if (c <= 65535) {
                this.str.insert(this.codePointLimit, (char) c);
                if (cc <= 1) {
                    this.reorderStart = this.codePointLimit + 1;
                    return;
                }
                return;
            }
            this.str.insert(this.codePointLimit, Character.toChars(c));
            if (cc <= 1) {
                this.reorderStart = this.codePointLimit + 2;
            }
        }

        private void setIterator() {
            this.codePointStart = this.str.length();
        }

        private void skipPrevious() {
            int i = this.codePointStart;
            this.codePointLimit = i;
            this.codePointStart = this.str.offsetByCodePoints(i, -1);
        }

        private int previousCC() {
            int i = this.codePointStart;
            this.codePointLimit = i;
            if (this.reorderStart >= i) {
                return 0;
            }
            int c = this.str.codePointBefore(i);
            this.codePointStart -= Character.charCount(c);
            return this.impl.getCCFromYesOrMaybeCP(c);
        }
    }

    public static final class UTF16Plus {
        public static boolean isLeadSurrogate(int c) {
            return (c & -1024) == 55296;
        }

        public static boolean isTrailSurrogate(int c) {
            return (c & -1024) == 56320;
        }

        public static boolean isSurrogate(int c) {
            return (c & -2048) == 55296;
        }

        public static boolean isSurrogateLead(int c) {
            return (c & 1024) == 0;
        }

        public static boolean equal(CharSequence s1, CharSequence s2) {
            if (s1 == s2) {
                return true;
            }
            int length = s1.length();
            if (length != s2.length()) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (s1.charAt(i) != s2.charAt(i)) {
                    return false;
                }
            }
            return true;
        }

        public static boolean equal(CharSequence s1, int start1, int limit1, CharSequence s2, int start2, int limit2) {
            if (limit1 - start1 != limit2 - start2) {
                return false;
            }
            if (s1 == s2 && start1 == start2) {
                return true;
            }
            while (start1 < limit1) {
                int start12 = start1 + 1;
                int start22 = start2 + 1;
                if (s1.charAt(start1) != s2.charAt(start2)) {
                    return false;
                }
                start1 = start12;
                start2 = start22;
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static final class IsAcceptable implements ICUBinary.Authenticate {
        private IsAcceptable() {
        }

        @Override // android.icu.impl.ICUBinary.Authenticate
        public boolean isDataVersionAcceptable(byte[] version) {
            return version[0] == 4;
        }
    }

    public Normalizer2Impl load(ByteBuffer bytes) {
        try {
            this.dataVersion = ICUBinary.readHeaderAndDataVersion(bytes, DATA_FORMAT, IS_ACCEPTABLE);
            int indexesLength = bytes.getInt() / 4;
            if (indexesLength > 18) {
                int[] inIndexes = new int[indexesLength];
                inIndexes[0] = indexesLength * 4;
                for (int i = 1; i < indexesLength; i++) {
                    inIndexes[i] = bytes.getInt();
                }
                this.minDecompNoCP = inIndexes[8];
                this.minCompNoMaybeCP = inIndexes[9];
                this.minLcccCP = inIndexes[18];
                this.minYesNo = inIndexes[10];
                this.minYesNoMappingsOnly = inIndexes[14];
                this.minNoNo = inIndexes[11];
                this.minNoNoCompBoundaryBefore = inIndexes[15];
                this.minNoNoCompNoMaybeCC = inIndexes[16];
                this.minNoNoEmpty = inIndexes[17];
                this.limitNoNo = inIndexes[12];
                this.minMaybeYes = inIndexes[13];
                this.centerNoNoDelta = ((this.minMaybeYes >> 3) - 64) - 1;
                int offset = inIndexes[0];
                int nextOffset = inIndexes[1];
                int triePosition = bytes.position();
                this.normTrie = CodePointTrie.Fast16.fromBinary(bytes);
                int trieLength = bytes.position() - triePosition;
                if (trieLength <= nextOffset - offset) {
                    ICUBinary.skipBytes(bytes, (nextOffset - offset) - trieLength);
                    int numChars = (inIndexes[2] - nextOffset) / 2;
                    if (numChars != 0) {
                        this.maybeYesCompositions = ICUBinary.getString(bytes, numChars, 0);
                        this.extraData = this.maybeYesCompositions.substring((MIN_NORMAL_MAYBE_YES - this.minMaybeYes) >> 1);
                    }
                    this.smallFCD = new byte[256];
                    bytes.get(this.smallFCD);
                    return this;
                }
                throw new ICUUncheckedIOException("Normalizer2 data: not enough bytes for normTrie");
            }
            throw new ICUUncheckedIOException("Normalizer2 data: not enough indexes");
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }

    public Normalizer2Impl load(String name) {
        return load(ICUBinary.getRequiredData(name));
    }

    public void addLcccChars(UnicodeSet set) {
        int start = 0;
        CodePointMap.Range range = new CodePointMap.Range();
        while (this.normTrie.getRange(start, CodePointMap.RangeOption.FIXED_LEAD_SURROGATES, 1, null, range)) {
            int end = range.getEnd();
            int norm16 = range.getValue();
            if (norm16 > 64512 && norm16 != 65024) {
                set.add(start, end);
            } else if (this.minNoNoCompNoMaybeCC <= norm16 && norm16 < this.limitNoNo && getFCD16(start) > 255) {
                set.add(start, end);
            }
            start = end + 1;
        }
    }

    public void addPropertyStarts(UnicodeSet set) {
        int start = 0;
        CodePointMap.Range range = new CodePointMap.Range();
        while (this.normTrie.getRange(start, CodePointMap.RangeOption.FIXED_LEAD_SURROGATES, 1, null, range)) {
            int end = range.getEnd();
            int value = range.getValue();
            set.add(start);
            if (start != end && isAlgorithmicNoNo(value) && (value & 6) > 2) {
                int prevFCD16 = getFCD16(start);
                while (true) {
                    start++;
                    if (start > end) {
                        break;
                    }
                    int fcd16 = getFCD16(start);
                    if (fcd16 != prevFCD16) {
                        set.add(start);
                        prevFCD16 = fcd16;
                    }
                }
            }
            start = end + 1;
        }
        for (int c = Hangul.HANGUL_BASE; c < 55204; c += 28) {
            set.add(c);
            set.add(c + 1);
        }
        set.add(Hangul.HANGUL_LIMIT);
    }

    public void addCanonIterPropertyStarts(UnicodeSet set) {
        ensureCanonIterData();
        CodePointMap.Range range = new CodePointMap.Range();
        for (int start = 0; this.canonIterData.getRange(start, segmentStarterMapper, range); start = range.getEnd() + 1) {
            set.add(start);
        }
    }

    public synchronized Normalizer2Impl ensureCanonIterData() {
        int start;
        int start2;
        if (this.canonIterData == null) {
            MutableCodePointTrie mutableTrie = new MutableCodePointTrie(0, 0);
            this.canonStartSets = new ArrayList<>();
            int start3 = 0;
            CodePointMap.Range range = new CodePointMap.Range();
            while (this.normTrie.getRange(start3, CodePointMap.RangeOption.FIXED_LEAD_SURROGATES, 1, null, range)) {
                int end = range.getEnd();
                int norm16 = range.getValue();
                if (!isInert(norm16)) {
                    if (this.minYesNo > norm16 || norm16 >= this.minNoNo) {
                        int c = start3;
                        while (c <= end) {
                            int oldValue = mutableTrie.get(c);
                            int newValue = oldValue;
                            if (isMaybeOrNonZeroCC(norm16)) {
                                newValue |= Integer.MIN_VALUE;
                                if (norm16 < 64512) {
                                    newValue |= 1073741824;
                                    start = start3;
                                } else {
                                    start = start3;
                                }
                            } else if (norm16 < this.minYesNo) {
                                newValue |= 1073741824;
                                start = start3;
                            } else {
                                int c2 = c;
                                int norm16_2 = norm16;
                                if (isDecompNoAlgorithmic(norm16_2)) {
                                    c2 = mapAlgorithmic(c2, norm16_2);
                                    norm16_2 = getRawNorm16(c2);
                                }
                                if (norm16_2 > this.minYesNo) {
                                    int mapping = norm16_2 >> 1;
                                    int firstUnit = this.extraData.charAt(mapping);
                                    int length = firstUnit & 31;
                                    if (!((firstUnit & 128) == 0 || c != c2 || (this.extraData.charAt(mapping - 1) & 255) == 0)) {
                                        newValue |= Integer.MIN_VALUE;
                                    }
                                    if (length != 0) {
                                        int mapping2 = mapping + 1;
                                        int limit = mapping2 + length;
                                        int c22 = this.extraData.codePointAt(mapping2);
                                        addToStartSet(mutableTrie, c, c22);
                                        if (norm16_2 >= this.minNoNo) {
                                            while (true) {
                                                int charCount = Character.charCount(c22) + mapping2;
                                                mapping2 = charCount;
                                                if (charCount >= limit) {
                                                    break;
                                                }
                                                c22 = this.extraData.codePointAt(mapping2);
                                                int c2Value = mutableTrie.get(c22);
                                                if ((c2Value & Integer.MIN_VALUE) == 0) {
                                                    start2 = start3;
                                                    mutableTrie.set(c22, c2Value | Integer.MIN_VALUE);
                                                } else {
                                                    start2 = start3;
                                                }
                                                start3 = start2;
                                            }
                                            start = start3;
                                        } else {
                                            start = start3;
                                        }
                                    } else {
                                        start = start3;
                                    }
                                } else {
                                    start = start3;
                                    addToStartSet(mutableTrie, c, c2);
                                }
                            }
                            if (newValue != oldValue) {
                                mutableTrie.set(c, newValue);
                            }
                            c++;
                            start3 = start;
                        }
                        start3 = end + 1;
                    }
                }
                start3 = end + 1;
            }
            this.canonIterData = mutableTrie.buildImmutable(CodePointTrie.Type.SMALL, CodePointTrie.ValueWidth.BITS_32);
        }
        return this;
    }

    public int getNorm16(int c) {
        if (UTF16Plus.isLeadSurrogate(c)) {
            return 1;
        }
        return this.normTrie.get(c);
    }

    public int getRawNorm16(int c) {
        return this.normTrie.get(c);
    }

    public int getCompQuickCheck(int norm16) {
        if (norm16 < this.minNoNo || 65026 <= norm16) {
            return 1;
        }
        if (this.minMaybeYes <= norm16) {
            return 2;
        }
        return 0;
    }

    public boolean isAlgorithmicNoNo(int norm16) {
        return this.limitNoNo <= norm16 && norm16 < this.minMaybeYes;
    }

    public boolean isCompNo(int norm16) {
        return this.minNoNo <= norm16 && norm16 < this.minMaybeYes;
    }

    public boolean isDecompYes(int norm16) {
        return norm16 < this.minYesNo || this.minMaybeYes <= norm16;
    }

    public int getCC(int norm16) {
        if (norm16 >= 64512) {
            return getCCFromNormalYesOrMaybe(norm16);
        }
        if (norm16 < this.minNoNo || this.limitNoNo <= norm16) {
            return 0;
        }
        return getCCFromNoNo(norm16);
    }

    public static int getCCFromNormalYesOrMaybe(int norm16) {
        return (norm16 >> 1) & 255;
    }

    public static int getCCFromYesOrMaybe(int norm16) {
        if (norm16 >= 64512) {
            return getCCFromNormalYesOrMaybe(norm16);
        }
        return 0;
    }

    public int getCCFromYesOrMaybeCP(int c) {
        if (c < this.minCompNoMaybeCP) {
            return 0;
        }
        return getCCFromYesOrMaybe(getNorm16(c));
    }

    public int getFCD16(int c) {
        if (c < this.minDecompNoCP) {
            return 0;
        }
        if (c > 65535 || singleLeadMightHaveNonZeroFCD16(c)) {
            return getFCD16FromNormData(c);
        }
        return 0;
    }

    public boolean singleLeadMightHaveNonZeroFCD16(int lead) {
        byte bits = this.smallFCD[lead >> 8];
        if (bits == 0 || ((bits >> ((lead >> 5) & 7)) & 1) == 0) {
            return false;
        }
        return true;
    }

    public int getFCD16FromNormData(int c) {
        int norm16 = getNorm16(c);
        if (norm16 >= this.limitNoNo) {
            if (norm16 >= 64512) {
                int norm162 = getCCFromNormalYesOrMaybe(norm16);
                return (norm162 << 8) | norm162;
            } else if (norm16 >= this.minMaybeYes) {
                return 0;
            } else {
                int deltaTrailCC = norm16 & 6;
                if (deltaTrailCC <= 2) {
                    return deltaTrailCC >> 1;
                }
                norm16 = getRawNorm16(mapAlgorithmic(c, norm16));
            }
        }
        if (norm16 <= this.minYesNo || isHangulLVT(norm16)) {
            return 0;
        }
        int mapping = norm16 >> 1;
        int firstUnit = this.extraData.charAt(mapping);
        int fcd16 = firstUnit >> 8;
        if ((firstUnit & 128) != 0) {
            return fcd16 | (this.extraData.charAt(mapping - 1) & 65280);
        }
        return fcd16;
    }

    public String getDecomposition(int c) {
        if (c >= this.minDecompNoCP) {
            int norm16 = getNorm16(c);
            int norm162 = norm16;
            if (!isMaybeOrNonZeroCC(norm16)) {
                int decomp = -1;
                if (isDecompNoAlgorithmic(norm162)) {
                    int mapAlgorithmic = mapAlgorithmic(c, norm162);
                    c = mapAlgorithmic;
                    decomp = mapAlgorithmic;
                    norm162 = getRawNorm16(c);
                }
                if (norm162 < this.minYesNo) {
                    if (decomp < 0) {
                        return null;
                    }
                    return UTF16.valueOf(decomp);
                } else if (isHangulLV(norm162) || isHangulLVT(norm162)) {
                    StringBuilder buffer = new StringBuilder();
                    Hangul.decompose(c, buffer);
                    return buffer.toString();
                } else {
                    int mapping = norm162 >> 1;
                    int mapping2 = mapping + 1;
                    return this.extraData.substring(mapping2, mapping2 + (this.extraData.charAt(mapping) & 31));
                }
            }
        }
        return null;
    }

    public String getRawDecomposition(int c) {
        if (c < this.minDecompNoCP) {
            return null;
        }
        int norm16 = getNorm16(c);
        if (isDecompYes(norm16)) {
            return null;
        }
        if (isHangulLV(norm16) || isHangulLVT(norm16)) {
            StringBuilder buffer = new StringBuilder();
            Hangul.getRawDecomposition(c, buffer);
            return buffer.toString();
        } else if (isDecompNoAlgorithmic(norm16)) {
            return UTF16.valueOf(mapAlgorithmic(c, norm16));
        } else {
            int mapping = norm16 >> 1;
            int firstUnit = this.extraData.charAt(mapping);
            int mLength = firstUnit & 31;
            if ((firstUnit & 64) != 0) {
                int rawMapping = (mapping - ((firstUnit >> 7) & 1)) - 1;
                char rm0 = this.extraData.charAt(rawMapping);
                if (rm0 <= 31) {
                    return this.extraData.substring(rawMapping - rm0, rawMapping);
                }
                StringBuilder buffer2 = new StringBuilder(mLength - 1).append(rm0);
                int mapping2 = mapping + 3;
                buffer2.append((CharSequence) this.extraData, mapping2, (mapping2 + mLength) - 2);
                return buffer2.toString();
            }
            int mapping3 = mapping + 1;
            return this.extraData.substring(mapping3, mapping3 + mLength);
        }
    }

    public boolean isCanonSegmentStarter(int c) {
        return this.canonIterData.get(c) >= 0;
    }

    public boolean getCanonStartSet(int c, UnicodeSet set) {
        int canonValue = this.canonIterData.get(c) & Integer.MAX_VALUE;
        if (canonValue == 0) {
            return false;
        }
        set.clear();
        int value = 2097151 & canonValue;
        if ((2097152 & canonValue) != 0) {
            set.addAll(this.canonStartSets.get(value));
        } else if (value != 0) {
            set.add(value);
        }
        if ((1073741824 & canonValue) != 0) {
            int norm16 = getRawNorm16(c);
            if (norm16 == 2) {
                int syllable = ((c - 4352) * Hangul.JAMO_VT_COUNT) + Hangul.HANGUL_BASE;
                set.add(syllable, (syllable + Hangul.JAMO_VT_COUNT) - 1);
            } else {
                addComposites(getCompositionsList(norm16), set);
            }
        }
        return true;
    }

    public Appendable decompose(CharSequence s, StringBuilder dest) {
        decompose(s, 0, s.length(), dest, s.length());
        return dest;
    }

    public void decompose(CharSequence s, int src, int limit, StringBuilder dest, int destLengthEstimate) {
        if (destLengthEstimate < 0) {
            destLengthEstimate = limit - src;
        }
        dest.setLength(0);
        decompose(s, src, limit, new ReorderingBuffer(this, dest, destLengthEstimate));
    }

    public int decompose(CharSequence s, int src, int limit, ReorderingBuffer buffer) {
        int cc;
        int minNoCP = this.minDecompNoCP;
        int c = 0;
        int norm16 = 0;
        int prevBoundary = src;
        int prevCC = 0;
        while (true) {
            while (src != limit) {
                char charAt = s.charAt(src);
                c = charAt;
                if (charAt >= minNoCP) {
                    int bmpGet = this.normTrie.bmpGet(c);
                    norm16 = bmpGet;
                    if (!isMostDecompYesAndZeroCC(bmpGet)) {
                        if (!UTF16Plus.isLeadSurrogate(c)) {
                            break;
                        }
                        if (src + 1 != limit) {
                            char c2 = s.charAt(src + 1);
                            if (Character.isLowSurrogate(c2)) {
                                c = Character.toCodePoint((char) c, c2);
                                norm16 = this.normTrie.suppGet(c);
                                if (!isMostDecompYesAndZeroCC(norm16)) {
                                    break;
                                }
                                src += 2;
                            }
                        }
                        src++;
                    }
                }
                src++;
            }
            if (src != src) {
                if (buffer != null) {
                    buffer.flushAndAppendZeroCC(s, src, src);
                } else {
                    prevCC = 0;
                    prevBoundary = src;
                }
            }
            if (src == limit) {
                return src;
            }
            src += Character.charCount(c);
            if (buffer != null) {
                decompose(c, norm16, buffer);
            } else if (!isDecompYes(norm16) || (prevCC > (cc = getCCFromYesOrMaybe(norm16)) && cc != 0)) {
                return prevBoundary;
            } else {
                prevCC = cc;
                if (cc <= 1) {
                    prevBoundary = src;
                }
            }
        }
        return prevBoundary;
    }

    public void decomposeAndAppend(CharSequence s, boolean doDecompose, ReorderingBuffer buffer) {
        int prevCC;
        int limit = s.length();
        if (limit != 0) {
            if (doDecompose) {
                decompose(s, 0, limit, buffer);
                return;
            }
            int c = Character.codePointAt(s, 0);
            int src = 0;
            int firstCC = getCC(getNorm16(c));
            int cc = firstCC;
            int prevCC2 = firstCC;
            while (true) {
                if (cc == 0) {
                    prevCC = prevCC2;
                    break;
                }
                prevCC2 = cc;
                src += Character.charCount(c);
                if (src >= limit) {
                    prevCC = prevCC2;
                    break;
                } else {
                    c = Character.codePointAt(s, src);
                    cc = getCC(getNorm16(c));
                }
            }
            buffer.append(s, 0, src, false, firstCC, prevCC);
            buffer.append(s, src, limit);
        }
    }

    /* JADX INFO: Multiple debug info for r0v2 'prevBoundary'  int: [D('prevSrc' int), D('src' int)] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean compose(java.lang.CharSequence r19, int r20, int r21, boolean r22, boolean r23, android.icu.impl.Normalizer2Impl.ReorderingBuffer r24) {
        /*
        // Method dump skipped, instructions count: 520
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.Normalizer2Impl.compose(java.lang.CharSequence, int, int, boolean, boolean, android.icu.impl.Normalizer2Impl$ReorderingBuffer):boolean");
    }

    /* JADX INFO: Multiple debug info for r12v2 'src'  int: [D('prevSrc' int), D('src' int)] */
    public int composeQuickCheck(CharSequence s, int prevSrc, int limit, boolean onlyContiguous, boolean doSpan) {
        int c;
        int qcResult = 0;
        int prevBoundary = prevSrc;
        int minNoMaybeCP = this.minCompNoMaybeCP;
        while (true) {
            while (prevSrc != limit) {
                int c2 = s.charAt(prevSrc);
                if (c2 >= minNoMaybeCP) {
                    int bmpGet = this.normTrie.bmpGet(c2);
                    int norm16 = bmpGet;
                    if (!isCompYesAndZeroCC(bmpGet)) {
                        int src = prevSrc + 1;
                        if (UTF16Plus.isLeadSurrogate(c2)) {
                            if (src != limit) {
                                char c22 = s.charAt(src);
                                if (Character.isLowSurrogate(c22)) {
                                    src++;
                                    norm16 = this.normTrie.suppGet(Character.toCodePoint((char) c2, c22));
                                    if (!isCompYesAndZeroCC(norm16)) {
                                    }
                                }
                            }
                            prevSrc = src;
                        }
                        int prevNorm16 = 1;
                        if (prevBoundary != prevSrc) {
                            prevBoundary = prevSrc;
                            if (!norm16HasCompBoundaryBefore(norm16)) {
                                int c3 = Character.codePointBefore(s, prevSrc);
                                int n16 = getNorm16(c3);
                                if (!norm16HasCompBoundaryAfter(n16, onlyContiguous)) {
                                    prevBoundary -= Character.charCount(c3);
                                    prevNorm16 = n16;
                                }
                            }
                        }
                        if (!isMaybeOrNonZeroCC(norm16)) {
                            break;
                        }
                        int cc = getCCFromYesOrMaybe(norm16);
                        if (onlyContiguous && cc != 0 && getTrailCCFromCompYesAndZeroCC(prevNorm16) > cc) {
                            break;
                        }
                        while (true) {
                            if (norm16 < 65026) {
                                if (doSpan) {
                                    return prevBoundary << 1;
                                }
                                qcResult = 1;
                            }
                            if (src == limit) {
                                return (src << 1) | qcResult;
                            }
                            c = Character.codePointAt(s, src);
                            norm16 = getNorm16(c);
                            if (isMaybeOrNonZeroCC(norm16) && (cc <= (cc = getCCFromYesOrMaybe(norm16)) || cc == 0)) {
                                src += Character.charCount(c);
                            }
                        }
                        if (!isCompYesAndZeroCC(norm16)) {
                            break;
                        }
                        prevBoundary = src;
                        prevSrc = src + Character.charCount(c);
                    }
                }
                prevSrc++;
            }
            return (prevSrc << 1) | qcResult;
        }
        return prevBoundary << 1;
    }

    public void composeAndAppend(CharSequence s, boolean doCompose, boolean onlyContiguous, ReorderingBuffer buffer) {
        int firstStarterInSrc;
        int src = 0;
        int limit = s.length();
        if (!buffer.isEmpty() && (firstStarterInSrc = findNextCompBoundary(s, 0, limit, onlyContiguous)) != 0) {
            int lastStarterInDest = findPreviousCompBoundary(buffer.getStringBuilder(), buffer.length(), onlyContiguous);
            StringBuilder middle = new StringBuilder((buffer.length() - lastStarterInDest) + firstStarterInSrc + 16);
            middle.append((CharSequence) buffer.getStringBuilder(), lastStarterInDest, buffer.length());
            buffer.removeSuffix(buffer.length() - lastStarterInDest);
            middle.append(s, 0, firstStarterInSrc);
            compose(middle, 0, middle.length(), onlyContiguous, true, buffer);
            src = firstStarterInSrc;
        }
        if (doCompose) {
            compose(s, src, limit, onlyContiguous, true, buffer);
        } else {
            buffer.append(s, src, limit);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:50:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00db  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int makeFCD(java.lang.CharSequence r18, int r19, int r20, android.icu.impl.Normalizer2Impl.ReorderingBuffer r21) {
        /*
        // Method dump skipped, instructions count: 254
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.Normalizer2Impl.makeFCD(java.lang.CharSequence, int, int, android.icu.impl.Normalizer2Impl$ReorderingBuffer):int");
    }

    public void makeFCDAndAppend(CharSequence s, boolean doMakeFCD, ReorderingBuffer buffer) {
        int firstBoundaryInSrc;
        int src = 0;
        int limit = s.length();
        if (!buffer.isEmpty() && (firstBoundaryInSrc = findNextFCDBoundary(s, 0, limit)) != 0) {
            int lastBoundaryInDest = findPreviousFCDBoundary(buffer.getStringBuilder(), buffer.length());
            StringBuilder middle = new StringBuilder((buffer.length() - lastBoundaryInDest) + firstBoundaryInSrc + 16);
            middle.append((CharSequence) buffer.getStringBuilder(), lastBoundaryInDest, buffer.length());
            buffer.removeSuffix(buffer.length() - lastBoundaryInDest);
            middle.append(s, 0, firstBoundaryInSrc);
            makeFCD(middle, 0, middle.length(), buffer);
            src = firstBoundaryInSrc;
        }
        if (doMakeFCD) {
            makeFCD(s, src, limit, buffer);
        } else {
            buffer.append(s, src, limit);
        }
    }

    public boolean hasDecompBoundaryBefore(int c) {
        return c < this.minLcccCP || (c <= 65535 && !singleLeadMightHaveNonZeroFCD16(c)) || norm16HasDecompBoundaryBefore(getNorm16(c));
    }

    public boolean norm16HasDecompBoundaryBefore(int norm16) {
        if (norm16 < this.minNoNoCompNoMaybeCC) {
            return true;
        }
        if (norm16 < this.limitNoNo) {
            int mapping = norm16 >> 1;
            if ((this.extraData.charAt(mapping) & 128) == 0 || (this.extraData.charAt(mapping - 1) & 65280) == 0) {
                return true;
            }
            return false;
        } else if (norm16 <= 64512 || norm16 == 65024) {
            return true;
        } else {
            return false;
        }
    }

    public boolean hasDecompBoundaryAfter(int c) {
        if (c < this.minDecompNoCP) {
            return true;
        }
        if (c > 65535 || singleLeadMightHaveNonZeroFCD16(c)) {
            return norm16HasDecompBoundaryAfter(getNorm16(c));
        }
        return true;
    }

    public boolean norm16HasDecompBoundaryAfter(int norm16) {
        if (norm16 <= this.minYesNo || isHangulLVT(norm16)) {
            return true;
        }
        if (norm16 < this.limitNoNo) {
            int mapping = norm16 >> 1;
            int firstUnit = this.extraData.charAt(mapping);
            if (firstUnit > 511) {
                return false;
            }
            if (firstUnit <= 255 || (firstUnit & 128) == 0 || (this.extraData.charAt(mapping - 1) & 65280) == 0) {
                return true;
            }
            return false;
        } else if (isMaybeOrNonZeroCC(norm16)) {
            if (norm16 <= 64512 || norm16 == 65024) {
                return true;
            }
            return false;
        } else if ((norm16 & 6) <= 2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDecompInert(int c) {
        return isDecompYesAndZeroCC(getNorm16(c));
    }

    public boolean hasCompBoundaryBefore(int c) {
        return c < this.minCompNoMaybeCP || norm16HasCompBoundaryBefore(getNorm16(c));
    }

    public boolean hasCompBoundaryAfter(int c, boolean onlyContiguous) {
        return norm16HasCompBoundaryAfter(getNorm16(c), onlyContiguous);
    }

    public boolean isCompInert(int c, boolean onlyContiguous) {
        int norm16 = getNorm16(c);
        return isCompYesAndZeroCC(norm16) && (norm16 & 1) != 0 && (!onlyContiguous || isInert(norm16) || this.extraData.charAt(norm16 >> 1) <= 511);
    }

    public boolean hasFCDBoundaryBefore(int c) {
        return hasDecompBoundaryBefore(c);
    }

    public boolean hasFCDBoundaryAfter(int c) {
        return hasDecompBoundaryAfter(c);
    }

    public boolean isFCDInert(int c) {
        return getFCD16(c) <= 1;
    }

    private boolean isMaybe(int norm16) {
        return this.minMaybeYes <= norm16 && norm16 <= 65024;
    }

    private boolean isMaybeOrNonZeroCC(int norm16) {
        return norm16 >= this.minMaybeYes;
    }

    private static boolean isInert(int norm16) {
        return norm16 == 1;
    }

    private static boolean isJamoL(int norm16) {
        return norm16 == 2;
    }

    private static boolean isJamoVT(int norm16) {
        return norm16 == 65024;
    }

    private int hangulLVT() {
        return this.minYesNoMappingsOnly | 1;
    }

    private boolean isHangulLV(int norm16) {
        return norm16 == this.minYesNo;
    }

    private boolean isHangulLVT(int norm16) {
        return norm16 == hangulLVT();
    }

    private boolean isCompYesAndZeroCC(int norm16) {
        return norm16 < this.minNoNo;
    }

    private boolean isDecompYesAndZeroCC(int norm16) {
        return norm16 < this.minYesNo || norm16 == 65024 || (this.minMaybeYes <= norm16 && norm16 <= 64512);
    }

    private boolean isMostDecompYesAndZeroCC(int norm16) {
        return norm16 < this.minYesNo || norm16 == 64512 || norm16 == 65024;
    }

    private boolean isDecompNoAlgorithmic(int norm16) {
        return norm16 >= this.limitNoNo;
    }

    private int getCCFromNoNo(int norm16) {
        int mapping = norm16 >> 1;
        if ((this.extraData.charAt(mapping) & 128) != 0) {
            return this.extraData.charAt(mapping - 1) & 255;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getTrailCCFromCompYesAndZeroCC(int norm16) {
        if (norm16 <= this.minYesNo) {
            return 0;
        }
        return this.extraData.charAt(norm16 >> 1) >> '\b';
    }

    private int mapAlgorithmic(int c, int norm16) {
        return ((norm16 >> 3) + c) - this.centerNoNoDelta;
    }

    private int getCompositionsListForDecompYes(int norm16) {
        if (norm16 < 2 || 64512 <= norm16) {
            return -1;
        }
        int i = norm16 - this.minMaybeYes;
        int norm162 = i;
        if (i < 0) {
            norm162 += MIN_NORMAL_MAYBE_YES;
        }
        return norm162 >> 1;
    }

    private int getCompositionsListForComposite(int norm16) {
        int list = ((MIN_NORMAL_MAYBE_YES - this.minMaybeYes) + norm16) >> 1;
        return list + 1 + (this.maybeYesCompositions.charAt(list) & 31);
    }

    private int getCompositionsListForMaybe(int norm16) {
        return (norm16 - this.minMaybeYes) >> 1;
    }

    private int getCompositionsList(int norm16) {
        if (isDecompYes(norm16)) {
            return getCompositionsListForDecompYes(norm16);
        }
        return getCompositionsListForComposite(norm16);
    }

    private int decomposeShort(CharSequence s, int src, int limit, boolean stopAtCompBoundary, boolean onlyContiguous, ReorderingBuffer buffer) {
        while (src < limit) {
            int c = Character.codePointAt(s, src);
            if (stopAtCompBoundary && c < this.minCompNoMaybeCP) {
                return src;
            }
            int norm16 = getNorm16(c);
            if (stopAtCompBoundary && norm16HasCompBoundaryBefore(norm16)) {
                return src;
            }
            src += Character.charCount(c);
            decompose(c, norm16, buffer);
            if (stopAtCompBoundary && norm16HasCompBoundaryAfter(norm16, onlyContiguous)) {
                return src;
            }
        }
        return src;
    }

    private void decompose(int c, int norm16, ReorderingBuffer buffer) {
        int leadCC;
        if (norm16 >= this.limitNoNo) {
            if (isMaybeOrNonZeroCC(norm16)) {
                buffer.append(c, getCCFromYesOrMaybe(norm16));
                return;
            } else {
                c = mapAlgorithmic(c, norm16);
                norm16 = getRawNorm16(c);
            }
        }
        if (norm16 < this.minYesNo) {
            buffer.append(c, 0);
        } else if (isHangulLV(norm16) || isHangulLVT(norm16)) {
            Hangul.decompose(c, buffer);
        } else {
            int mapping = norm16 >> 1;
            int firstUnit = this.extraData.charAt(mapping);
            int length = firstUnit & 31;
            int trailCC = firstUnit >> 8;
            if ((firstUnit & 128) != 0) {
                leadCC = this.extraData.charAt(mapping - 1) >> '\b';
            } else {
                leadCC = 0;
            }
            int mapping2 = mapping + 1;
            buffer.append(this.extraData, mapping2, mapping2 + length, true, leadCC, trailCC);
        }
    }

    private static int combine(String compositions, int list, int trail) {
        int firstUnit;
        if (trail < 13312) {
            int key1 = trail << 1;
            while (true) {
                firstUnit = compositions.charAt(list);
                if (key1 <= firstUnit) {
                    break;
                }
                list += (firstUnit & 1) + 2;
            }
            if (key1 != (firstUnit & COMP_1_TRAIL_MASK)) {
                return -1;
            }
            if ((firstUnit & 1) != 0) {
                return (compositions.charAt(list + 1) << 16) | compositions.charAt(list + 2);
            }
            return compositions.charAt(list + 1);
        }
        int key12 = COMP_1_TRAIL_LIMIT + ((trail >> 9) & -2);
        int key2 = (trail << 6) & DateTimePatternGenerator.MATCH_ALL_FIELDS_LENGTH;
        while (true) {
            int firstUnit2 = compositions.charAt(list);
            if (key12 > firstUnit2) {
                list += (firstUnit2 & 1) + 2;
            } else if (key12 != (firstUnit2 & COMP_1_TRAIL_MASK)) {
                break;
            } else {
                int secondUnit = compositions.charAt(list + 1);
                if (key2 > secondUnit) {
                    if ((32768 & firstUnit2) != 0) {
                        break;
                    }
                    list += 3;
                } else if (key2 == (65472 & secondUnit)) {
                    return ((-65473 & secondUnit) << 16) | compositions.charAt(list + 2);
                }
            }
        }
        return -1;
    }

    private void addComposites(int list, UnicodeSet set) {
        int firstUnit;
        int compositeAndFwd;
        do {
            firstUnit = this.maybeYesCompositions.charAt(list);
            if ((firstUnit & 1) == 0) {
                compositeAndFwd = this.maybeYesCompositions.charAt(list + 1);
                list += 2;
            } else {
                compositeAndFwd = ((this.maybeYesCompositions.charAt(list + 1) & '?') << 16) | this.maybeYesCompositions.charAt(list + 2);
                list += 3;
            }
            int composite = compositeAndFwd >> 1;
            if ((compositeAndFwd & 1) != 0) {
                addComposites(getCompositionsListForComposite(getRawNorm16(composite)), set);
            }
            set.add(composite);
        } while ((32768 & firstUnit) == 0);
    }

    private void recompose(ReorderingBuffer buffer, int recomposeStartIndex, boolean onlyContiguous) {
        char prev;
        char t;
        StringBuilder sb = buffer.getStringBuilder();
        int p = recomposeStartIndex;
        if (p != sb.length()) {
            int compositionsList = -1;
            int starter = -1;
            boolean starterIsSupplementary = false;
            int prevCC = 0;
            while (true) {
                int c = sb.codePointAt(p);
                p += Character.charCount(c);
                int norm16 = getNorm16(c);
                int cc = getCCFromYesOrMaybe(norm16);
                if (isMaybe(norm16) && compositionsList >= 0 && (prevCC < cc || prevCC == 0)) {
                    if (isJamoVT(norm16)) {
                        if (c < 4519 && (prev = (char) (sb.charAt(starter) - 4352)) < 19) {
                            int pRemove = p - 1;
                            char syllable = (char) ((((prev * 21) + (c - 4449)) * 28) + Hangul.HANGUL_BASE);
                            if (p != sb.length() && (t = (char) (sb.charAt(p) - Hangul.JAMO_T_BASE)) < 28) {
                                p++;
                                syllable = (char) (syllable + t);
                            }
                            sb.setCharAt(starter, syllable);
                            sb.delete(pRemove, p);
                            p = pRemove;
                        }
                        if (p == sb.length()) {
                            break;
                        }
                        compositionsList = -1;
                    } else {
                        int compositeAndFwd = combine(this.maybeYesCompositions, compositionsList, c);
                        if (compositeAndFwd >= 0) {
                            int composite = compositeAndFwd >> 1;
                            int pRemove2 = p - Character.charCount(c);
                            sb.delete(pRemove2, p);
                            p = pRemove2;
                            if (starterIsSupplementary) {
                                if (composite > 65535) {
                                    sb.setCharAt(starter, UTF16.getLeadSurrogate(composite));
                                    sb.setCharAt(starter + 1, UTF16.getTrailSurrogate(composite));
                                } else {
                                    sb.setCharAt(starter, (char) c);
                                    sb.deleteCharAt(starter + 1);
                                    starterIsSupplementary = false;
                                    p--;
                                }
                            } else if (composite > 65535) {
                                starterIsSupplementary = true;
                                sb.setCharAt(starter, UTF16.getLeadSurrogate(composite));
                                sb.insert(starter + 1, UTF16.getTrailSurrogate(composite));
                                p++;
                            } else {
                                sb.setCharAt(starter, (char) composite);
                            }
                            if (p == sb.length()) {
                                break;
                            }
                            compositionsList = (compositeAndFwd & 1) != 0 ? getCompositionsListForComposite(getRawNorm16(composite)) : -1;
                        }
                    }
                }
                prevCC = cc;
                if (p == sb.length()) {
                    break;
                } else if (cc == 0) {
                    int compositionsListForDecompYes = getCompositionsListForDecompYes(norm16);
                    compositionsList = compositionsListForDecompYes;
                    if (compositionsListForDecompYes >= 0) {
                        if (c <= 65535) {
                            starterIsSupplementary = false;
                            starter = p - 1;
                        } else {
                            starterIsSupplementary = true;
                            starter = p - 2;
                        }
                    }
                } else if (onlyContiguous) {
                    compositionsList = -1;
                }
            }
            buffer.flush();
        }
    }

    public int composePair(int a, int b) {
        int list;
        int norm16 = getNorm16(a);
        if (isInert(norm16)) {
            return -1;
        }
        if (norm16 < this.minYesNoMappingsOnly) {
            if (isJamoL(norm16)) {
                int b2 = b - 4449;
                if (b2 < 0 || b2 >= 21) {
                    return -1;
                }
                return ((((a - 4352) * 21) + b2) * 28) + Hangul.HANGUL_BASE;
            } else if (isHangulLV(norm16)) {
                int b3 = b - 4519;
                if (b3 <= 0 || b3 >= 28) {
                    return -1;
                }
                return a + b3;
            } else {
                list = ((MIN_NORMAL_MAYBE_YES - this.minMaybeYes) + norm16) >> 1;
                if (norm16 > this.minYesNo) {
                    list += (this.maybeYesCompositions.charAt(list) & 31) + 1;
                }
            }
        } else if (norm16 < this.minMaybeYes || 64512 <= norm16) {
            return -1;
        } else {
            list = getCompositionsListForMaybe(norm16);
        }
        if (b < 0 || 1114111 < b) {
            return -1;
        }
        return combine(this.maybeYesCompositions, list, b) >> 1;
    }

    private boolean hasCompBoundaryBefore(int c, int norm16) {
        return c < this.minCompNoMaybeCP || norm16HasCompBoundaryBefore(norm16);
    }

    private boolean norm16HasCompBoundaryBefore(int norm16) {
        return norm16 < this.minNoNoCompNoMaybeCC || isAlgorithmicNoNo(norm16);
    }

    private boolean hasCompBoundaryBefore(CharSequence s, int src, int limit) {
        return src == limit || hasCompBoundaryBefore(Character.codePointAt(s, src));
    }

    private boolean norm16HasCompBoundaryAfter(int norm16, boolean onlyContiguous) {
        return (norm16 & 1) != 0 && (!onlyContiguous || isTrailCC01ForCompBoundaryAfter(norm16));
    }

    private boolean hasCompBoundaryAfter(CharSequence s, int start, int p, boolean onlyContiguous) {
        return start == p || hasCompBoundaryAfter(Character.codePointBefore(s, p), onlyContiguous);
    }

    private boolean isTrailCC01ForCompBoundaryAfter(int norm16) {
        return isInert(norm16) || (!isDecompNoAlgorithmic(norm16) ? this.extraData.charAt(norm16 >> 1) <= 511 : (norm16 & 6) <= 2);
    }

    private int findPreviousCompBoundary(CharSequence s, int p, boolean onlyContiguous) {
        while (p > 0) {
            int c = Character.codePointBefore(s, p);
            int norm16 = getNorm16(c);
            if (!norm16HasCompBoundaryAfter(norm16, onlyContiguous)) {
                p -= Character.charCount(c);
                if (hasCompBoundaryBefore(c, norm16)) {
                    break;
                }
            } else {
                break;
            }
        }
        return p;
    }

    private int findNextCompBoundary(CharSequence s, int p, int limit, boolean onlyContiguous) {
        while (p < limit) {
            int c = Character.codePointAt(s, p);
            int norm16 = this.normTrie.get(c);
            if (!hasCompBoundaryBefore(c, norm16)) {
                p += Character.charCount(c);
                if (norm16HasCompBoundaryAfter(norm16, onlyContiguous)) {
                    break;
                }
            } else {
                break;
            }
        }
        return p;
    }

    private int findPreviousFCDBoundary(CharSequence s, int p) {
        while (p > 0) {
            int c = Character.codePointBefore(s, p);
            if (c < this.minDecompNoCP) {
                break;
            }
            int norm16 = getNorm16(c);
            if (!norm16HasDecompBoundaryAfter(norm16)) {
                p -= Character.charCount(c);
                if (norm16HasDecompBoundaryBefore(norm16)) {
                    break;
                }
            } else {
                break;
            }
        }
        return p;
    }

    private int findNextFCDBoundary(CharSequence s, int p, int limit) {
        while (p < limit) {
            int c = Character.codePointAt(s, p);
            if (c < this.minLcccCP) {
                break;
            }
            int norm16 = getNorm16(c);
            if (!norm16HasDecompBoundaryBefore(norm16)) {
                p += Character.charCount(c);
                if (norm16HasDecompBoundaryAfter(norm16)) {
                    break;
                }
            } else {
                break;
            }
        }
        return p;
    }

    private int getPreviousTrailCC(CharSequence s, int start, int p) {
        if (start == p) {
            return 0;
        }
        return getFCD16(Character.codePointBefore(s, p));
    }

    private void addToStartSet(MutableCodePointTrie mutableTrie, int origin, int decompLead) {
        UnicodeSet set;
        int canonValue = mutableTrie.get(decompLead);
        if ((4194303 & canonValue) != 0 || origin == 0) {
            if ((canonValue & 2097152) == 0) {
                int firstOrigin = canonValue & 2097151;
                mutableTrie.set(decompLead, 2097152 | (-2097152 & canonValue) | this.canonStartSets.size());
                ArrayList<UnicodeSet> arrayList = this.canonStartSets;
                UnicodeSet unicodeSet = new UnicodeSet();
                set = unicodeSet;
                arrayList.add(unicodeSet);
                if (firstOrigin != 0) {
                    set.add(firstOrigin);
                }
            } else {
                set = this.canonStartSets.get(canonValue & 2097151);
            }
            set.add(origin);
            return;
        }
        mutableTrie.set(decompLead, canonValue | origin);
    }
}
