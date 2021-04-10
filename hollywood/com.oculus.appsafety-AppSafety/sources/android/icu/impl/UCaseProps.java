package android.icu.impl;

import android.icu.impl.ICUBinary;
import android.icu.impl.Trie2;
import android.icu.text.DateTimePatternGenerator;
import android.icu.text.UTF16;
import android.icu.text.UnicodeSet;
import android.icu.util.ICUUncheckedIOException;
import android.icu.util.ULocale;
import java.io.IOException;
import java.io.ObjectStreamConstants;
import java.net.HttpURLConnection;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Locale;

public final class UCaseProps {
    private static final int ABOVE = 64;
    private static final int CLOSURE_MAX_LENGTH = 15;
    private static final String DATA_FILE_NAME = "ucase.icu";
    private static final String DATA_NAME = "ucase";
    private static final String DATA_TYPE = "icu";
    private static final int DELTA_SHIFT = 7;
    private static final int DOT_MASK = 96;
    private static final int EXCEPTION = 8;
    private static final int EXC_CLOSURE = 6;
    private static final int EXC_CONDITIONAL_FOLD = 32768;
    private static final int EXC_CONDITIONAL_SPECIAL = 16384;
    private static final int EXC_DELTA = 4;
    private static final int EXC_DELTA_IS_NEGATIVE = 1024;
    private static final int EXC_DOT_SHIFT = 7;
    private static final int EXC_DOUBLE_SLOTS = 256;
    private static final int EXC_FOLD = 1;
    private static final int EXC_FULL_MAPPINGS = 7;
    private static final int EXC_LOWER = 0;
    private static final int EXC_NO_SIMPLE_CASE_FOLDING = 512;
    private static final int EXC_SENSITIVE = 2048;
    private static final int EXC_SHIFT = 4;
    private static final int EXC_TITLE = 3;
    private static final int EXC_UPPER = 2;
    private static final int FMT = 1665225541;
    static final int FOLD_CASE_OPTIONS_MASK = 7;
    private static final int FULL_LOWER = 15;
    static final int IGNORABLE = 4;
    public static final UCaseProps INSTANCE;
    private static final int IX_EXC_LENGTH = 3;
    private static final int IX_TOP = 16;
    private static final int IX_TRIE_SIZE = 2;
    private static final int IX_UNFOLD_LENGTH = 4;
    public static final int LOC_DUTCH = 5;
    static final int LOC_GREEK = 4;
    static final int LOC_LITHUANIAN = 3;
    public static final int LOC_ROOT = 1;
    static final int LOC_TURKISH = 2;
    public static final int LOWER = 1;
    public static final int MAX_STRING_LENGTH = 31;
    public static final int NONE = 0;
    private static final int OTHER_ACCENT = 96;
    private static final int SENSITIVE = 16;
    private static final int SOFT_DOTTED = 32;
    public static final int TITLE = 3;
    public static final int TYPE_MASK = 3;
    private static final int UNFOLD_ROWS = 0;
    private static final int UNFOLD_ROW_WIDTH = 1;
    private static final int UNFOLD_STRING_WIDTH = 2;
    public static final int UPPER = 2;
    public static final StringBuilder dummyStringBuilder = new StringBuilder();
    private static final byte[] flagsOffset = {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 1, 2, 2, 3, 2, 3, 3, 4, 2, 3, 3, 4, 3, 4, 4, 5, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 2, 3, 3, 4, 3, 4, 4, 5, 3, 4, 4, 5, 4, 5, 5, 6, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 3, 4, 4, 5, 4, 5, 5, 6, 4, 5, 5, 6, 5, 6, 6, 7, 4, 5, 5, 6, 5, 6, 6, 7, 5, 6, 6, 7, 6, 7, 7, 8};
    private static final String iDot = "i̇";
    private static final String iDotAcute = "i̇́";
    private static final String iDotGrave = "i̇̀";
    private static final String iDotTilde = "i̇̃";
    private static final String iOgonekDot = "į̇";
    private static final String jDot = "j̇";
    private String exceptions;
    private int[] indexes;
    private Trie2_16 trie;
    private char[] unfold;

    public interface ContextIterator {
        int next();

        void reset(int i);
    }

    private UCaseProps() throws IOException {
        readData(ICUBinary.getRequiredData(DATA_FILE_NAME));
    }

    private final void readData(ByteBuffer bytes) throws IOException {
        ICUBinary.readHeader(bytes, FMT, new IsAcceptable());
        int count = bytes.getInt();
        if (count >= 16) {
            this.indexes = new int[count];
            this.indexes[0] = count;
            for (int i = 1; i < count; i++) {
                this.indexes[i] = bytes.getInt();
            }
            this.trie = Trie2_16.createFromSerialized(bytes);
            int expectedTrieLength = this.indexes[2];
            int trieLength = this.trie.getSerializedLength();
            if (trieLength <= expectedTrieLength) {
                ICUBinary.skipBytes(bytes, expectedTrieLength - trieLength);
                int count2 = this.indexes[3];
                if (count2 > 0) {
                    this.exceptions = ICUBinary.getString(bytes, count2, 0);
                }
                int count3 = this.indexes[4];
                if (count3 > 0) {
                    this.unfold = ICUBinary.getChars(bytes, count3, 0);
                    return;
                }
                return;
            }
            throw new IOException("ucase.icu: not enough bytes for the trie");
        }
        throw new IOException("indexes[0] too small in ucase.icu");
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

    public final void addPropertyStarts(UnicodeSet set) {
        Iterator<Trie2.Range> trieIterator = this.trie.iterator();
        while (trieIterator.hasNext()) {
            Trie2.Range range = trieIterator.next();
            if (!range.leadSurrogate) {
                set.add(range.startCodePoint);
            } else {
                return;
            }
        }
    }

    private static final int getExceptionsOffset(int props) {
        return props >> 4;
    }

    static final boolean propsHasException(int props) {
        return (props & 8) != 0;
    }

    static {
        try {
            INSTANCE = new UCaseProps();
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }

    private static final boolean hasSlot(int flags, int index) {
        return ((1 << index) & flags) != 0;
    }

    private static final byte slotOffset(int flags, int index) {
        return flagsOffset[flags & ((1 << index) - 1)];
    }

    private final long getSlotValueAndOffset(int excWord, int index, int excOffset) {
        int excOffset2;
        long value;
        if ((excWord & 256) == 0) {
            excOffset2 = excOffset + slotOffset(excWord, index);
            value = (long) this.exceptions.charAt(excOffset2);
        } else {
            int excOffset3 = excOffset + (slotOffset(excWord, index) * 2);
            int excOffset4 = excOffset3 + 1;
            excOffset2 = excOffset4;
            value = (((long) this.exceptions.charAt(excOffset3)) << 16) | ((long) this.exceptions.charAt(excOffset4));
        }
        return (((long) excOffset2) << 32) | value;
    }

    private final int getSlotValue(int excWord, int index, int excOffset) {
        if ((excWord & 256) == 0) {
            return this.exceptions.charAt(excOffset + slotOffset(excWord, index));
        }
        int excOffset2 = excOffset + (slotOffset(excWord, index) * 2);
        return (this.exceptions.charAt(excOffset2) << 16) | this.exceptions.charAt(excOffset2 + 1);
    }

    public final int tolower(int c) {
        int props = this.trie.get(c);
        if (propsHasException(props)) {
            int excOffset = getExceptionsOffset(props);
            int excOffset2 = excOffset + 1;
            int excWord = this.exceptions.charAt(excOffset);
            if (hasSlot(excWord, 4) && isUpperOrTitleFromProps(props)) {
                int delta = getSlotValue(excWord, 4, excOffset2);
                return (excWord & 1024) == 0 ? c + delta : c - delta;
            } else if (hasSlot(excWord, 0)) {
                return getSlotValue(excWord, 0, excOffset2);
            } else {
                return c;
            }
        } else if (isUpperOrTitleFromProps(props)) {
            return c + getDelta(props);
        } else {
            return c;
        }
    }

    public final int toupper(int c) {
        int props = this.trie.get(c);
        if (propsHasException(props)) {
            int excOffset = getExceptionsOffset(props);
            int excOffset2 = excOffset + 1;
            int excWord = this.exceptions.charAt(excOffset);
            if (hasSlot(excWord, 4) && getTypeFromProps(props) == 1) {
                int delta = getSlotValue(excWord, 4, excOffset2);
                return (excWord & 1024) == 0 ? c + delta : c - delta;
            } else if (hasSlot(excWord, 2)) {
                return getSlotValue(excWord, 2, excOffset2);
            } else {
                return c;
            }
        } else if (getTypeFromProps(props) == 1) {
            return c + getDelta(props);
        } else {
            return c;
        }
    }

    public final int totitle(int c) {
        int index;
        int props = this.trie.get(c);
        if (propsHasException(props)) {
            int excOffset = getExceptionsOffset(props);
            int excOffset2 = excOffset + 1;
            int excWord = this.exceptions.charAt(excOffset);
            if (!hasSlot(excWord, 4) || getTypeFromProps(props) != 1) {
                if (hasSlot(excWord, 3)) {
                    index = 3;
                } else if (!hasSlot(excWord, 2)) {
                    return c;
                } else {
                    index = 2;
                }
                return getSlotValue(excWord, index, excOffset2);
            }
            int delta = getSlotValue(excWord, 4, excOffset2);
            return (excWord & 1024) == 0 ? c + delta : c - delta;
        } else if (getTypeFromProps(props) == 1) {
            return c + getDelta(props);
        } else {
            return c;
        }
    }

    public final void addCaseClosure(int c, UnicodeSet set) {
        int closureOffset;
        int closureLength;
        int delta;
        int c2 = c;
        if (c2 == 73) {
            set.add(105);
        } else if (c2 == 105) {
            set.add(73);
        } else if (c2 == 304) {
            set.add(iDot);
        } else if (c2 != 305) {
            int props = this.trie.get(c2);
            if (!propsHasException(props)) {
                if (!(getTypeFromProps(props) == 0 || (delta = getDelta(props)) == 0)) {
                    set.add(c2 + delta);
                }
                return;
            }
            int excOffset = getExceptionsOffset(props);
            int excOffset2 = excOffset + 1;
            int excWord = this.exceptions.charAt(excOffset);
            for (int index = 0; index <= 3; index++) {
                if (hasSlot(excWord, index)) {
                    excOffset2 = excOffset2;
                    c2 = getSlotValue(excWord, index, excOffset2);
                    set.add(c2);
                }
            }
            if (hasSlot(excWord, 4)) {
                excOffset2 = excOffset2;
                int delta2 = getSlotValue(excWord, 4, excOffset2);
                set.add((excWord & 1024) == 0 ? c2 + delta2 : c2 - delta2);
            }
            if (hasSlot(excWord, 6)) {
                excOffset2 = excOffset2;
                long value = getSlotValueAndOffset(excWord, 6, excOffset2);
                closureLength = ((int) value) & 15;
                closureOffset = ((int) (value >> 32)) + 1;
            } else {
                closureLength = 0;
                closureOffset = 0;
            }
            if (hasSlot(excWord, 7)) {
                long value2 = getSlotValueAndOffset(excWord, 7, excOffset2);
                int fullLength = 65535 & ((int) value2);
                int excOffset3 = ((int) (value2 >> 32)) + 1 + (fullLength & 15);
                int fullLength2 = fullLength >> 4;
                int length = fullLength2 & 15;
                if (length != 0) {
                    set.add(this.exceptions.substring(excOffset3, excOffset3 + length));
                    excOffset3 += length;
                }
                int fullLength3 = fullLength2 >> 4;
                closureOffset = excOffset3 + (fullLength3 & 15) + (fullLength3 >> 4);
            }
            int limit = closureOffset + closureLength;
            int index2 = closureOffset;
            while (index2 < limit) {
                int c3 = this.exceptions.codePointAt(index2);
                set.add(c3);
                index2 += UTF16.getCharCount(c3);
            }
        }
    }

    /* JADX INFO: Multiple debug info for r7v2 char: [D('unfoldOffset' int), D('c2' int)] */
    private final int strcmpMax(String s, int c2, int max) {
        int length = s.length();
        int max2 = max - length;
        int c1 = 0;
        while (true) {
            int i1 = c1 + 1;
            int c12 = s.charAt(c1);
            char[] cArr = this.unfold;
            int unfoldOffset = c2 + 1;
            char c = cArr[c2];
            if (c == 0) {
                return 1;
            }
            int c13 = c12 - c;
            if (c13 != 0) {
                return c13;
            }
            length--;
            if (length > 0) {
                c1 = i1;
                c2 = unfoldOffset;
            } else if (max2 == 0 || cArr[unfoldOffset] == 0) {
                return 0;
            } else {
                return -max2;
            }
        }
    }

    public final boolean addStringCaseClosure(String s, UnicodeSet set) {
        int length;
        if (this.unfold == null || s == null || (length = s.length()) <= 1) {
            return false;
        }
        char[] cArr = this.unfold;
        char c = cArr[0];
        char c2 = cArr[1];
        char c3 = cArr[2];
        if (length > c3) {
            return false;
        }
        int start = 0;
        int limit = c;
        while (start < limit) {
            int i = (start + limit) / 2;
            int unfoldOffset = (i + 1) * c2;
            int result = strcmpMax(s, unfoldOffset, c3);
            if (result == 0) {
                int i2 = c3;
                while (i2 < c2) {
                    char[] cArr2 = this.unfold;
                    if (cArr2[unfoldOffset + i2] == 0) {
                        break;
                    }
                    int c4 = UTF16.charAt(cArr2, unfoldOffset, cArr2.length, i2);
                    set.add(c4);
                    addCaseClosure(c4, set);
                    i2 += UTF16.getCharCount(c4);
                }
                return true;
            } else if (result < 0) {
                limit = i;
            } else {
                start = i + 1;
            }
        }
        return false;
    }

    public final int getType(int c) {
        return getTypeFromProps(this.trie.get(c));
    }

    public final int getTypeOrIgnorable(int c) {
        return getTypeAndIgnorableFromProps(this.trie.get(c));
    }

    public final int getDotType(int c) {
        int props = this.trie.get(c);
        if (!propsHasException(props)) {
            return props & 96;
        }
        return (this.exceptions.charAt(getExceptionsOffset(props)) >> 7) & 96;
    }

    public final boolean isSoftDotted(int c) {
        return getDotType(c) == 32;
    }

    public final boolean isCaseSensitive(int c) {
        int props = this.trie.get(c);
        return !propsHasException(props) ? (props & 16) != 0 : (this.exceptions.charAt(getExceptionsOffset(props)) & 2048) != 0;
    }

    static final class LatinCase {
        static final byte EXC = Byte.MIN_VALUE;
        static final char LIMIT = 384;
        static final char LONG_S = 383;
        static final byte[] TO_LOWER_NORMAL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Byte.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 0, 32, 32, 32, 32, 32, 32, 32, Byte.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, Byte.MIN_VALUE, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, Byte.MIN_VALUE, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, -121, 1, 0, 1, 0, 1, 0, Byte.MIN_VALUE};
        static final byte[] TO_LOWER_TR_LT = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 32, 32, 32, 32, 32, 32, 32, Byte.MIN_VALUE, Byte.MIN_VALUE, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Byte.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, Byte.MIN_VALUE, Byte.MIN_VALUE, 32, 32, 32, 32, 32, 32, 32, 32, 32, 0, 32, 32, 32, 32, 32, 32, 32, Byte.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, Byte.MIN_VALUE, 0, 1, 0, 1, 0, Byte.MIN_VALUE, 0, Byte.MIN_VALUE, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, Byte.MIN_VALUE, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, -121, 1, 0, 1, 0, 1, 0, Byte.MIN_VALUE};
        static final byte[] TO_UPPER_NORMAL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Byte.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Byte.MIN_VALUE, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, 0, -32, -32, -32, -32, -32, -32, -32, ObjectStreamConstants.TC_RESET, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, Byte.MIN_VALUE, 0, -1, 0, -1, 0, -1, 0, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, Byte.MIN_VALUE, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, 0, -1, 0, -1, 0, -1, Byte.MIN_VALUE};
        static final byte[] TO_UPPER_TR = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -32, -32, -32, -32, -32, -32, -32, -32, Byte.MIN_VALUE, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Byte.MIN_VALUE, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, Byte.MIN_VALUE, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, -32, 0, -32, -32, -32, -32, -32, -32, -32, ObjectStreamConstants.TC_RESET, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, Byte.MIN_VALUE, 0, -1, 0, -1, 0, -1, 0, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, Byte.MIN_VALUE, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, -1, 0, 0, -1, 0, -1, 0, -1, Byte.MIN_VALUE};

        LatinCase() {
        }
    }

    public static final int getCaseLocale(Locale locale) {
        return getCaseLocale(locale.getLanguage());
    }

    public static final int getCaseLocale(ULocale locale) {
        return getCaseLocale(locale.getLanguage());
    }

    private static final int getCaseLocale(String language) {
        if (language.length() == 2) {
            if (language.equals("en") || language.charAt(0) > 't') {
                return 1;
            }
            if (language.equals("tr") || language.equals("az")) {
                return 2;
            }
            if (language.equals("el")) {
                return 4;
            }
            if (language.equals("lt")) {
                return 3;
            }
            if (language.equals("nl")) {
                return 5;
            }
        } else if (language.length() == 3) {
            if (language.equals("tur") || language.equals("aze")) {
                return 2;
            }
            if (language.equals("ell")) {
                return 4;
            }
            if (language.equals("lit")) {
                return 3;
            }
            if (language.equals("nld")) {
                return 5;
            }
        }
        return 1;
    }

    private final boolean isFollowedByCasedLetter(ContextIterator iter, int dir) {
        int type;
        if (iter == null) {
            return false;
        }
        iter.reset(dir);
        do {
            int c = iter.next();
            if (c < 0) {
                return false;
            }
            type = getTypeOrIgnorable(c);
        } while ((type & 4) != 0);
        if (type != 0) {
            return true;
        }
        return false;
    }

    private final boolean isPrecededBySoftDotted(ContextIterator iter) {
        int dotType;
        if (iter == null) {
            return false;
        }
        iter.reset(-1);
        do {
            int c = iter.next();
            if (c < 0) {
                return false;
            }
            dotType = getDotType(c);
            if (dotType == 32) {
                return true;
            }
        } while (dotType == 96);
        return false;
    }

    private final boolean isPrecededBy_I(ContextIterator iter) {
        int c;
        if (iter == null) {
            return false;
        }
        iter.reset(-1);
        do {
            c = iter.next();
            if (c < 0) {
                return false;
            }
            if (c == 73) {
                return true;
            }
        } while (getDotType(c) == 96);
        return false;
    }

    private final boolean isFollowedByMoreAbove(ContextIterator iter) {
        int dotType;
        if (iter == null) {
            return false;
        }
        iter.reset(1);
        do {
            int c = iter.next();
            if (c < 0) {
                return false;
            }
            dotType = getDotType(c);
            if (dotType == 64) {
                return true;
            }
        } while (dotType == 96);
        return false;
    }

    private final boolean isFollowedByDotAbove(ContextIterator iter) {
        int c;
        if (iter == null) {
            return false;
        }
        iter.reset(1);
        do {
            c = iter.next();
            if (c < 0) {
                return false;
            }
            if (c == 775) {
                return true;
            }
        } while (getDotType(c) == 96);
        return false;
    }

    public final int toFullLower(int c, ContextIterator iter, Appendable out, int caseLocale) {
        int result = c;
        int props = this.trie.get(c);
        if (propsHasException(props)) {
            int excOffset = getExceptionsOffset(props);
            int excOffset2 = excOffset + 1;
            int excWord = this.exceptions.charAt(excOffset);
            if ((excWord & 16384) != 0) {
                if (caseLocale == 3 && (((c == 73 || c == 74 || c == 302) && isFollowedByMoreAbove(iter)) || c == 204 || c == 205 || c == 296)) {
                    if (c == 73) {
                        out.append(iDot);
                        return 2;
                    } else if (c == 74) {
                        out.append(jDot);
                        return 2;
                    } else if (c == 204) {
                        out.append(iDotGrave);
                        return 3;
                    } else if (c == 205) {
                        out.append(iDotAcute);
                        return 3;
                    } else if (c == 296) {
                        out.append(iDotTilde);
                        return 3;
                    } else if (c != 302) {
                        return 0;
                    } else {
                        try {
                            out.append(iOgonekDot);
                            return 2;
                        } catch (IOException e) {
                            throw new ICUUncheckedIOException(e);
                        }
                    }
                } else if (caseLocale == 2 && c == 304) {
                    return 105;
                } else {
                    if (caseLocale == 2 && c == 775 && isPrecededBy_I(iter)) {
                        return 0;
                    }
                    if (caseLocale == 2 && c == 73 && !isFollowedByDotAbove(iter)) {
                        return HttpURLConnection.HTTP_USE_PROXY;
                    }
                    if (c == 304) {
                        try {
                            out.append(iDot);
                            return 2;
                        } catch (IOException e2) {
                            throw new ICUUncheckedIOException(e2);
                        }
                    } else if (c == 931 && !isFollowedByCasedLetter(iter, 1) && isFollowedByCasedLetter(iter, -1)) {
                        return 962;
                    }
                }
            } else if (hasSlot(excWord, 7)) {
                long value = getSlotValueAndOffset(excWord, 7, excOffset2);
                int full = ((int) value) & 15;
                if (full != 0) {
                    int excOffset3 = ((int) (value >> 32)) + 1;
                    try {
                        out.append(this.exceptions, excOffset3, excOffset3 + full);
                        return full;
                    } catch (IOException e3) {
                        throw new ICUUncheckedIOException(e3);
                    }
                }
            }
            if (hasSlot(excWord, 4) && isUpperOrTitleFromProps(props)) {
                int delta = getSlotValue(excWord, 4, excOffset2);
                return (excWord & 1024) == 0 ? c + delta : c - delta;
            } else if (hasSlot(excWord, 0)) {
                result = getSlotValue(excWord, 0, excOffset2);
            }
        } else if (isUpperOrTitleFromProps(props)) {
            result = c + getDelta(props);
        }
        return result == c ? ~result : result;
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00e2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int toUpperOrTitle(int r18, android.icu.impl.UCaseProps.ContextIterator r19, java.lang.Appendable r20, int r21, boolean r22) {
        /*
        // Method dump skipped, instructions count: 228
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.UCaseProps.toUpperOrTitle(int, android.icu.impl.UCaseProps$ContextIterator, java.lang.Appendable, int, boolean):int");
    }

    public final int toFullUpper(int c, ContextIterator iter, Appendable out, int caseLocale) {
        return toUpperOrTitle(c, iter, out, caseLocale, true);
    }

    public final int toFullTitle(int c, ContextIterator iter, Appendable out, int caseLocale) {
        return toUpperOrTitle(c, iter, out, caseLocale, false);
    }

    public final int fold(int c, int options) {
        int index;
        int props = this.trie.get(c);
        if (propsHasException(props)) {
            int excOffset = getExceptionsOffset(props);
            int excOffset2 = excOffset + 1;
            int excWord = this.exceptions.charAt(excOffset);
            if ((32768 & excWord) != 0) {
                if ((options & 7) == 0) {
                    if (c == 73) {
                        return 105;
                    }
                    if (c == 304) {
                        return c;
                    }
                } else if (c == 73) {
                    return HttpURLConnection.HTTP_USE_PROXY;
                } else {
                    if (c == 304) {
                        return 105;
                    }
                }
            }
            if ((excWord & 512) != 0) {
                return c;
            }
            if (!hasSlot(excWord, 4) || !isUpperOrTitleFromProps(props)) {
                if (hasSlot(excWord, 1)) {
                    index = 1;
                } else if (!hasSlot(excWord, 0)) {
                    return c;
                } else {
                    index = 0;
                }
                return getSlotValue(excWord, index, excOffset2);
            }
            int delta = getSlotValue(excWord, 4, excOffset2);
            return (excWord & 1024) == 0 ? c + delta : c - delta;
        } else if (isUpperOrTitleFromProps(props)) {
            return c + getDelta(props);
        } else {
            return c;
        }
    }

    public final int toFullFolding(int c, Appendable out, int options) {
        int index;
        int result = c;
        int props = this.trie.get(c);
        if (propsHasException(props)) {
            int excOffset = getExceptionsOffset(props);
            int excOffset2 = excOffset + 1;
            int excWord = this.exceptions.charAt(excOffset);
            if ((32768 & excWord) != 0) {
                if ((options & 7) == 0) {
                    if (c == 73) {
                        return 105;
                    }
                    if (c == 304) {
                        try {
                            out.append(iDot);
                            return 2;
                        } catch (IOException e) {
                            throw new ICUUncheckedIOException(e);
                        }
                    }
                } else if (c == 73) {
                    return HttpURLConnection.HTTP_USE_PROXY;
                } else {
                    if (c == 304) {
                        return 105;
                    }
                }
            } else if (hasSlot(excWord, 7)) {
                long value = getSlotValueAndOffset(excWord, 7, excOffset2);
                int full = ((int) value) & DateTimePatternGenerator.MATCH_ALL_FIELDS_LENGTH;
                int excOffset3 = (full & 15) + ((int) (value >> 32)) + 1;
                int full2 = (full >> 4) & 15;
                if (full2 != 0) {
                    try {
                        out.append(this.exceptions, excOffset3, excOffset3 + full2);
                        return full2;
                    } catch (IOException e2) {
                        throw new ICUUncheckedIOException(e2);
                    }
                }
            }
            if ((excWord & 512) != 0) {
                return ~c;
            }
            if (!hasSlot(excWord, 4) || !isUpperOrTitleFromProps(props)) {
                if (hasSlot(excWord, 1)) {
                    index = 1;
                } else if (!hasSlot(excWord, 0)) {
                    return ~c;
                } else {
                    index = 0;
                }
                result = getSlotValue(excWord, index, excOffset2);
            } else {
                int delta = getSlotValue(excWord, 4, excOffset2);
                return (excWord & 1024) == 0 ? c + delta : c - delta;
            }
        } else if (isUpperOrTitleFromProps(props)) {
            result = c + getDelta(props);
        }
        return result == c ? ~result : result;
    }

    public final boolean hasBinaryProperty(int c, int which) {
        if (which == 22) {
            return 1 == getType(c);
        }
        if (which == 27) {
            return isSoftDotted(c);
        }
        if (which == 30) {
            return 2 == getType(c);
        }
        if (which == 34) {
            return isCaseSensitive(c);
        }
        if (which != 55) {
            switch (which) {
                case 49:
                    return getType(c) != 0;
                case 50:
                    return (getTypeOrIgnorable(c) >> 2) != 0;
                case 51:
                    dummyStringBuilder.setLength(0);
                    return toFullLower(c, null, dummyStringBuilder, 1) >= 0;
                case 52:
                    dummyStringBuilder.setLength(0);
                    return toFullUpper(c, null, dummyStringBuilder, 1) >= 0;
                case 53:
                    dummyStringBuilder.setLength(0);
                    return toFullTitle(c, null, dummyStringBuilder, 1) >= 0;
                default:
                    return false;
            }
        } else {
            dummyStringBuilder.setLength(0);
            return toFullLower(c, null, dummyStringBuilder, 1) >= 0 || toFullUpper(c, null, dummyStringBuilder, 1) >= 0 || toFullTitle(c, null, dummyStringBuilder, 1) >= 0;
        }
    }

    static Trie2_16 getTrie() {
        return INSTANCE.trie;
    }

    static final int getTypeFromProps(int props) {
        return props & 3;
    }

    private static final int getTypeAndIgnorableFromProps(int props) {
        return props & 7;
    }

    static final boolean isUpperOrTitleFromProps(int props) {
        return (props & 2) != 0;
    }

    static final int getDelta(int props) {
        return ((short) props) >> 7;
    }
}
