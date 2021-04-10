package android.icu.impl;

import android.icu.impl.ICUBinary;
import android.icu.impl.Trie2;
import android.icu.text.UnicodeSet;
import android.icu.util.ICUUncheckedIOException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;

public final class UBiDiProps {
    private static final int BIDI_CONTROL_SHIFT = 11;
    private static final int BPT_MASK = 768;
    private static final int BPT_SHIFT = 8;
    private static final int CLASS_MASK = 31;
    private static final String DATA_FILE_NAME = "ubidi.icu";
    private static final String DATA_NAME = "ubidi";
    private static final String DATA_TYPE = "icu";
    private static final int ESC_MIRROR_DELTA = -4;
    private static final int FMT = 1114195049;
    public static final UBiDiProps INSTANCE;
    private static final int IS_MIRRORED_SHIFT = 12;
    private static final int IX_JG_LIMIT = 5;
    private static final int IX_JG_LIMIT2 = 7;
    private static final int IX_JG_START = 4;
    private static final int IX_JG_START2 = 6;
    private static final int IX_MAX_VALUES = 15;
    private static final int IX_MIRROR_LENGTH = 3;
    private static final int IX_TOP = 16;
    private static final int IX_TRIE_SIZE = 2;
    private static final int JOIN_CONTROL_SHIFT = 10;
    private static final int JT_MASK = 224;
    private static final int JT_SHIFT = 5;
    private static final int MAX_JG_MASK = 16711680;
    private static final int MAX_JG_SHIFT = 16;
    private static final int MIRROR_DELTA_SHIFT = 13;
    private static final int MIRROR_INDEX_SHIFT = 21;
    private int[] indexes;
    private byte[] jgArray;
    private byte[] jgArray2;
    private int[] mirrors;
    private Trie2_16 trie;

    private UBiDiProps() throws IOException {
        readData(ICUBinary.getData(DATA_FILE_NAME));
    }

    private void readData(ByteBuffer bytes) throws IOException {
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
                    this.mirrors = ICUBinary.getInts(bytes, count2, 0);
                }
                int[] iArr = this.indexes;
                this.jgArray = new byte[(iArr[5] - iArr[4])];
                bytes.get(this.jgArray);
                int[] iArr2 = this.indexes;
                this.jgArray2 = new byte[(iArr2[7] - iArr2[6])];
                bytes.get(this.jgArray2);
                return;
            }
            throw new IOException("ubidi.icu: not enough bytes for the trie");
        }
        throw new IOException("indexes[0] too small in ubidi.icu");
    }

    /* access modifiers changed from: private */
    public static final class IsAcceptable implements ICUBinary.Authenticate {
        private IsAcceptable() {
        }

        @Override // android.icu.impl.ICUBinary.Authenticate
        public boolean isDataVersionAcceptable(byte[] version) {
            return version[0] == 2;
        }
    }

    public final void addPropertyStarts(UnicodeSet set) {
        Iterator<Trie2.Range> trieIterator = this.trie.iterator();
        while (trieIterator.hasNext()) {
            Trie2.Range range = trieIterator.next();
            if (range.leadSurrogate) {
                break;
            }
            set.add(range.startCodePoint);
        }
        int length = this.indexes[3];
        for (int i = 0; i < length; i++) {
            int c = getMirrorCodePoint(this.mirrors[i]);
            set.add(c, c + 1);
        }
        int[] iArr = this.indexes;
        int start = iArr[4];
        int limit = iArr[5];
        byte[] jga = this.jgArray;
        while (true) {
            int length2 = limit - start;
            byte prev = 0;
            for (int i2 = 0; i2 < length2; i2++) {
                byte jg = jga[i2];
                if (jg != prev) {
                    set.add(start);
                    prev = jg;
                }
                start++;
            }
            if (prev != 0) {
                set.add(limit);
            }
            int[] iArr2 = this.indexes;
            if (limit == iArr2[5]) {
                start = iArr2[6];
                limit = iArr2[7];
                jga = this.jgArray2;
            } else {
                return;
            }
        }
    }

    public final int getMaxValue(int which) {
        int max = this.indexes[15];
        if (which == 4096) {
            return max & 31;
        }
        if (which == 4117) {
            return (max & 768) >> 8;
        }
        if (which == 4102) {
            return (MAX_JG_MASK & max) >> 16;
        }
        if (which != 4103) {
            return -1;
        }
        return (max & 224) >> 5;
    }

    public final int getClass(int c) {
        return getClassFromProps(this.trie.get(c));
    }

    public final boolean isMirrored(int c) {
        return getFlagFromProps(this.trie.get(c), 12);
    }

    private final int getMirror(int c, int props) {
        int delta = getMirrorDeltaFromProps(props);
        if (delta != -4) {
            return c + delta;
        }
        int length = this.indexes[3];
        for (int i = 0; i < length; i++) {
            int m = this.mirrors[i];
            int c2 = getMirrorCodePoint(m);
            if (c == c2) {
                return getMirrorCodePoint(this.mirrors[getMirrorIndex(m)]);
            }
            if (c < c2) {
                break;
            }
        }
        return c;
    }

    public final int getMirror(int c) {
        return getMirror(c, this.trie.get(c));
    }

    public final boolean isBidiControl(int c) {
        return getFlagFromProps(this.trie.get(c), 11);
    }

    public final boolean isJoinControl(int c) {
        return getFlagFromProps(this.trie.get(c), 10);
    }

    public final int getJoiningType(int c) {
        return (this.trie.get(c) & 224) >> 5;
    }

    public final int getJoiningGroup(int c) {
        int[] iArr = this.indexes;
        int start = iArr[4];
        int limit = iArr[5];
        if (start <= c && c < limit) {
            return this.jgArray[c - start] & 255;
        }
        int[] iArr2 = this.indexes;
        int start2 = iArr2[6];
        int limit2 = iArr2[7];
        if (start2 > c || c >= limit2) {
            return 0;
        }
        return this.jgArray2[c - start2] & 255;
    }

    public final int getPairedBracketType(int c) {
        return (this.trie.get(c) & 768) >> 8;
    }

    public final int getPairedBracket(int c) {
        int props = this.trie.get(c);
        if ((props & 768) == 0) {
            return c;
        }
        return getMirror(c, props);
    }

    private static final int getClassFromProps(int props) {
        return props & 31;
    }

    private static final boolean getFlagFromProps(int props, int shift) {
        return ((props >> shift) & 1) != 0;
    }

    private static final int getMirrorDeltaFromProps(int props) {
        return ((short) props) >> 13;
    }

    private static final int getMirrorCodePoint(int m) {
        return 2097151 & m;
    }

    private static final int getMirrorIndex(int m) {
        return m >>> 21;
    }

    static {
        try {
            INSTANCE = new UBiDiProps();
        } catch (IOException e) {
            throw new ICUUncheckedIOException(e);
        }
    }
}
