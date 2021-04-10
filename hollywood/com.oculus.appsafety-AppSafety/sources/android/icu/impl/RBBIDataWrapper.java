package android.icu.impl;

import android.icu.impl.ICUBinary;
import android.icu.impl.number.Padder;
import android.icu.text.RuleBasedBreakIterator;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class RBBIDataWrapper {
    public static final int ACCEPTING = 0;
    public static final int DATA_FORMAT = 1114794784;
    public static final int DH_CATCOUNT = 3;
    public static final int DH_FORMATVERSION = 1;
    public static final int DH_FTABLE = 4;
    public static final int DH_FTABLELEN = 5;
    public static final int DH_LENGTH = 2;
    public static final int DH_MAGIC = 0;
    public static final int DH_RTABLE = 6;
    public static final int DH_RTABLELEN = 7;
    public static final int DH_RULESOURCE = 10;
    public static final int DH_RULESOURCELEN = 11;
    public static final int DH_SIZE = 20;
    public static final int DH_STATUSTABLE = 12;
    public static final int DH_STATUSTABLELEN = 13;
    public static final int DH_TRIE = 8;
    public static final int DH_TRIELEN = 9;
    public static final int FORMAT_VERSION = 83886080;
    private static final IsAcceptable IS_ACCEPTABLE = new IsAcceptable();
    public static final int LOOKAHEAD = 1;
    public static final int NEXTSTATES = 4;
    public static final int RBBI_BOF_REQUIRED = 2;
    public static final int RBBI_LOOKAHEAD_HARD_BREAK = 1;
    public static final int RESERVED = 3;
    public static final int TAGIDX = 2;
    public RBBIStateTable fFTable;
    public RBBIDataHeader fHeader;
    public RBBIStateTable fRTable;
    public String fRuleSource;
    public int[] fStatusTable;
    public Trie2 fTrie;

    public static final class RBBIDataHeader {
        public int fCatCount;
        int fFTable;
        int fFTableLen;
        byte[] fFormatVersion = new byte[4];
        int fLength;
        int fMagic = 0;
        int fRTable;
        int fRTableLen;
        int fRuleSource;
        int fRuleSourceLen;
        int fStatusTable;
        int fStatusTableLen;
        int fTrie;
        int fTrieLen;
    }

    public static class RBBIStateTable {
        public int fFlags;
        public int fNumStates;
        public int fReserved;
        public int fRowLen;
        public short[] fTable;

        static RBBIStateTable get(ByteBuffer bytes, int length) throws IOException {
            if (length == 0) {
                return null;
            }
            if (length >= 16) {
                RBBIStateTable This = new RBBIStateTable();
                This.fNumStates = bytes.getInt();
                This.fRowLen = bytes.getInt();
                This.fFlags = bytes.getInt();
                This.fReserved = bytes.getInt();
                int lengthOfShorts = length - 16;
                This.fTable = ICUBinary.getShorts(bytes, lengthOfShorts / 2, lengthOfShorts & 1);
                return This;
            }
            throw new IOException("Invalid RBBI state table length.");
        }

        public int put(DataOutputStream bytes) throws IOException {
            bytes.writeInt(this.fNumStates);
            bytes.writeInt(this.fRowLen);
            bytes.writeInt(this.fFlags);
            bytes.writeInt(this.fReserved);
            int tableLen = (this.fRowLen * this.fNumStates) / 2;
            for (int i = 0; i < tableLen; i++) {
                bytes.writeShort(this.fTable[i]);
            }
            int bytesWritten = (this.fRowLen * this.fNumStates) + 16;
            while (bytesWritten % 8 != 0) {
                bytes.writeByte(0);
                bytesWritten++;
            }
            return bytesWritten;
        }

        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof RBBIStateTable)) {
                return false;
            }
            RBBIStateTable otherST = (RBBIStateTable) other;
            if (this.fNumStates == otherST.fNumStates && this.fRowLen == otherST.fRowLen && this.fFlags == otherST.fFlags && this.fReserved == otherST.fReserved) {
                return Arrays.equals(this.fTable, otherST.fTable);
            }
            return false;
        }
    }

    public static boolean equals(RBBIStateTable left, RBBIStateTable right) {
        if (left == right) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.equals(right);
    }

    /* access modifiers changed from: private */
    public static final class IsAcceptable implements ICUBinary.Authenticate {
        private IsAcceptable() {
        }

        @Override // android.icu.impl.ICUBinary.Authenticate
        public boolean isDataVersionAcceptable(byte[] version) {
            return (((version[0] << 24) + (version[1] << 16)) + (version[2] << 8)) + version[3] == 83886080;
        }
    }

    public int getRowIndex(int state) {
        return (this.fHeader.fCatCount + 4) * state;
    }

    RBBIDataWrapper() {
    }

    public static RBBIDataWrapper get(ByteBuffer bytes) throws IOException {
        RBBIDataWrapper This = new RBBIDataWrapper();
        ICUBinary.readHeader(bytes, DATA_FORMAT, IS_ACCEPTABLE);
        This.fHeader = new RBBIDataHeader();
        This.fHeader.fMagic = bytes.getInt();
        This.fHeader.fFormatVersion[0] = bytes.get();
        This.fHeader.fFormatVersion[1] = bytes.get();
        This.fHeader.fFormatVersion[2] = bytes.get();
        This.fHeader.fFormatVersion[3] = bytes.get();
        This.fHeader.fLength = bytes.getInt();
        This.fHeader.fCatCount = bytes.getInt();
        This.fHeader.fFTable = bytes.getInt();
        This.fHeader.fFTableLen = bytes.getInt();
        This.fHeader.fRTable = bytes.getInt();
        This.fHeader.fRTableLen = bytes.getInt();
        This.fHeader.fTrie = bytes.getInt();
        This.fHeader.fTrieLen = bytes.getInt();
        This.fHeader.fRuleSource = bytes.getInt();
        This.fHeader.fRuleSourceLen = bytes.getInt();
        This.fHeader.fStatusTable = bytes.getInt();
        This.fHeader.fStatusTableLen = bytes.getInt();
        ICUBinary.skipBytes(bytes, 24);
        if (This.fHeader.fMagic != 45472 || !IS_ACCEPTABLE.isDataVersionAcceptable(This.fHeader.fFormatVersion)) {
            throw new IOException("Break Iterator Rule Data Magic Number Incorrect, or unsupported data version.");
        } else if (This.fHeader.fFTable < 80 || This.fHeader.fFTable > This.fHeader.fLength) {
            throw new IOException("Break iterator Rule data corrupt");
        } else {
            ICUBinary.skipBytes(bytes, This.fHeader.fFTable - 80);
            int pos = This.fHeader.fFTable;
            This.fFTable = RBBIStateTable.get(bytes, This.fHeader.fFTableLen);
            ICUBinary.skipBytes(bytes, This.fHeader.fRTable - (pos + This.fHeader.fFTableLen));
            int pos2 = This.fHeader.fRTable;
            This.fRTable = RBBIStateTable.get(bytes, This.fHeader.fRTableLen);
            ICUBinary.skipBytes(bytes, This.fHeader.fTrie - (pos2 + This.fHeader.fRTableLen));
            int pos3 = This.fHeader.fTrie;
            bytes.mark();
            This.fTrie = Trie2.createFromSerialized(bytes);
            bytes.reset();
            if (pos3 <= This.fHeader.fStatusTable) {
                ICUBinary.skipBytes(bytes, This.fHeader.fStatusTable - pos3);
                int pos4 = This.fHeader.fStatusTable;
                This.fStatusTable = ICUBinary.getInts(bytes, This.fHeader.fStatusTableLen / 4, 3 & This.fHeader.fStatusTableLen);
                int pos5 = pos4 + This.fHeader.fStatusTableLen;
                if (pos5 <= This.fHeader.fRuleSource) {
                    ICUBinary.skipBytes(bytes, This.fHeader.fRuleSource - pos5);
                    int pos6 = This.fHeader.fRuleSource;
                    This.fRuleSource = ICUBinary.getString(bytes, This.fHeader.fRuleSourceLen / 2, 1 & This.fHeader.fRuleSourceLen);
                    if (RuleBasedBreakIterator.fDebugEnv != null && RuleBasedBreakIterator.fDebugEnv.indexOf("data") >= 0) {
                        This.dump(System.out);
                    }
                    return This;
                }
                throw new IOException("Break iterator Rule data corrupt");
            }
            throw new IOException("Break iterator Rule data corrupt");
        }
    }

    public void dump(PrintStream out) {
        if (this.fFTable != null) {
            out.println("RBBI Data Wrapper dump ...");
            out.println();
            out.println("Forward State Table");
            dumpTable(out, this.fFTable);
            out.println("Reverse State Table");
            dumpTable(out, this.fRTable);
            dumpCharCategories(out);
            out.println("Source Rules: " + this.fRuleSource);
            return;
        }
        throw new NullPointerException();
    }

    public static String intToString(int n, int width) {
        StringBuilder dest = new StringBuilder(width);
        dest.append(n);
        while (dest.length() < width) {
            dest.insert(0, ' ');
        }
        return dest.toString();
    }

    public static String intToHexString(int n, int width) {
        StringBuilder dest = new StringBuilder(width);
        dest.append(Integer.toHexString(n));
        while (dest.length() < width) {
            dest.insert(0, ' ');
        }
        return dest.toString();
    }

    private void dumpTable(PrintStream out, RBBIStateTable table) {
        if (table == null || table.fTable.length == 0) {
            out.println("  -- null -- ");
            return;
        }
        StringBuilder header = new StringBuilder(" Row  Acc Look  Tag");
        for (int n = 0; n < this.fHeader.fCatCount; n++) {
            header.append(intToString(n, 5));
        }
        out.println(header.toString());
        for (int n2 = 0; n2 < header.length(); n2++) {
            out.print("-");
        }
        out.println();
        for (int state = 0; state < table.fNumStates; state++) {
            dumpRow(out, table, state);
        }
        out.println();
    }

    private void dumpRow(PrintStream out, RBBIStateTable table, int state) {
        StringBuilder dest = new StringBuilder((this.fHeader.fCatCount * 5) + 20);
        dest.append(intToString(state, 4));
        int row = getRowIndex(state);
        if (table.fTable[row + 0] != 0) {
            dest.append(intToString(table.fTable[row + 0], 5));
        } else {
            dest.append("     ");
        }
        if (table.fTable[row + 1] != 0) {
            dest.append(intToString(table.fTable[row + 1], 5));
        } else {
            dest.append("     ");
        }
        dest.append(intToString(table.fTable[row + 2], 5));
        for (int col = 0; col < this.fHeader.fCatCount; col++) {
            dest.append(intToString(table.fTable[row + 4 + col], 5));
        }
        out.println(dest);
    }

    private void dumpCharCategories(PrintStream out) {
        int category;
        int n = this.fHeader.fCatCount;
        String[] catStrings = new String[(n + 1)];
        int rangeStart = 0;
        int rangeEnd = 0;
        int lastCat = -1;
        int[] lastNewline = new int[(n + 1)];
        for (int category2 = 0; category2 <= this.fHeader.fCatCount; category2++) {
            catStrings[category2] = "";
        }
        out.println("\nCharacter Categories");
        out.println("--------------------");
        int char32 = 0;
        while (true) {
            if (char32 > 1114111) {
                break;
            }
            category = this.fTrie.get(char32) & -16385;
            if (category < 0 || category > this.fHeader.fCatCount) {
                out.println("Error, bad category " + Integer.toHexString(category) + " for char " + Integer.toHexString(char32));
            } else {
                if (category == lastCat) {
                    rangeEnd = char32;
                } else {
                    if (lastCat >= 0) {
                        if (catStrings[lastCat].length() > lastNewline[lastCat] + 70) {
                            lastNewline[lastCat] = catStrings[lastCat].length() + 10;
                            catStrings[lastCat] = catStrings[lastCat] + "\n       ";
                        }
                        catStrings[lastCat] = catStrings[lastCat] + Padder.FALLBACK_PADDING_STRING + Integer.toHexString(rangeStart);
                        if (rangeEnd != rangeStart) {
                            catStrings[lastCat] = catStrings[lastCat] + "-" + Integer.toHexString(rangeEnd);
                        }
                    }
                    lastCat = category;
                    rangeEnd = char32;
                    rangeStart = char32;
                }
                char32++;
            }
        }
        out.println("Error, bad category " + Integer.toHexString(category) + " for char " + Integer.toHexString(char32));
        catStrings[lastCat] = catStrings[lastCat] + Padder.FALLBACK_PADDING_STRING + Integer.toHexString(rangeStart);
        if (rangeEnd != rangeStart) {
            catStrings[lastCat] = catStrings[lastCat] + "-" + Integer.toHexString(rangeEnd);
        }
        for (int category3 = 0; category3 <= this.fHeader.fCatCount; category3++) {
            out.println(intToString(category3, 5) + "  " + catStrings[category3]);
        }
        out.println();
    }
}
