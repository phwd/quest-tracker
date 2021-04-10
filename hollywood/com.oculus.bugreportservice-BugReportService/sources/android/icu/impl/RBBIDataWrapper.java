package android.icu.impl;

import android.icu.impl.ICUBinary;
import android.icu.text.RuleBasedBreakIterator;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class RBBIDataWrapper {
    private static final IsAcceptable IS_ACCEPTABLE = new IsAcceptable();
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

        static RBBIStateTable get(ByteBuffer byteBuffer, int i) {
            if (i == 0) {
                return null;
            }
            if (i >= 16) {
                RBBIStateTable rBBIStateTable = new RBBIStateTable();
                rBBIStateTable.fNumStates = byteBuffer.getInt();
                rBBIStateTable.fRowLen = byteBuffer.getInt();
                rBBIStateTable.fFlags = byteBuffer.getInt();
                rBBIStateTable.fReserved = byteBuffer.getInt();
                int i2 = i - 16;
                rBBIStateTable.fTable = ICUBinary.getShorts(byteBuffer, i2 / 2, i2 & 1);
                return rBBIStateTable;
            }
            throw new IOException("Invalid RBBI state table length.");
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof RBBIStateTable)) {
                return false;
            }
            RBBIStateTable rBBIStateTable = (RBBIStateTable) obj;
            if (this.fNumStates == rBBIStateTable.fNumStates && this.fRowLen == rBBIStateTable.fRowLen && this.fFlags == rBBIStateTable.fFlags && this.fReserved == rBBIStateTable.fReserved) {
                return Arrays.equals(this.fTable, rBBIStateTable.fTable);
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static final class IsAcceptable implements ICUBinary.Authenticate {
        private IsAcceptable() {
        }

        @Override // android.icu.impl.ICUBinary.Authenticate
        public boolean isDataVersionAcceptable(byte[] bArr) {
            return (((bArr[0] << 24) + (bArr[1] << 16)) + (bArr[2] << 8)) + bArr[3] == 83886080;
        }
    }

    public int getRowIndex(int i) {
        return i * (this.fHeader.fCatCount + 4);
    }

    RBBIDataWrapper() {
    }

    public static RBBIDataWrapper get(ByteBuffer byteBuffer) {
        RBBIDataWrapper rBBIDataWrapper = new RBBIDataWrapper();
        ICUBinary.readHeader(byteBuffer, 1114794784, IS_ACCEPTABLE);
        rBBIDataWrapper.fHeader = new RBBIDataHeader();
        rBBIDataWrapper.fHeader.fMagic = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fFormatVersion[0] = byteBuffer.get();
        rBBIDataWrapper.fHeader.fFormatVersion[1] = byteBuffer.get();
        rBBIDataWrapper.fHeader.fFormatVersion[2] = byteBuffer.get();
        rBBIDataWrapper.fHeader.fFormatVersion[3] = byteBuffer.get();
        rBBIDataWrapper.fHeader.fLength = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fCatCount = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fFTable = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fFTableLen = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fRTable = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fRTableLen = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fTrie = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fTrieLen = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fRuleSource = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fRuleSourceLen = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fStatusTable = byteBuffer.getInt();
        rBBIDataWrapper.fHeader.fStatusTableLen = byteBuffer.getInt();
        ICUBinary.skipBytes(byteBuffer, 24);
        RBBIDataHeader rBBIDataHeader = rBBIDataWrapper.fHeader;
        if (rBBIDataHeader.fMagic != 45472 || !IS_ACCEPTABLE.isDataVersionAcceptable(rBBIDataHeader.fFormatVersion)) {
            throw new IOException("Break Iterator Rule Data Magic Number Incorrect, or unsupported data version.");
        }
        RBBIDataHeader rBBIDataHeader2 = rBBIDataWrapper.fHeader;
        int i = rBBIDataHeader2.fFTable;
        if (i < 80 || i > rBBIDataHeader2.fLength) {
            throw new IOException("Break iterator Rule data corrupt");
        }
        ICUBinary.skipBytes(byteBuffer, i - 80);
        RBBIDataHeader rBBIDataHeader3 = rBBIDataWrapper.fHeader;
        int i2 = rBBIDataHeader3.fFTable;
        rBBIDataWrapper.fFTable = RBBIStateTable.get(byteBuffer, rBBIDataHeader3.fFTableLen);
        RBBIDataHeader rBBIDataHeader4 = rBBIDataWrapper.fHeader;
        ICUBinary.skipBytes(byteBuffer, rBBIDataHeader4.fRTable - (i2 + rBBIDataHeader4.fFTableLen));
        RBBIDataHeader rBBIDataHeader5 = rBBIDataWrapper.fHeader;
        int i3 = rBBIDataHeader5.fRTable;
        rBBIDataWrapper.fRTable = RBBIStateTable.get(byteBuffer, rBBIDataHeader5.fRTableLen);
        RBBIDataHeader rBBIDataHeader6 = rBBIDataWrapper.fHeader;
        ICUBinary.skipBytes(byteBuffer, rBBIDataHeader6.fTrie - (i3 + rBBIDataHeader6.fRTableLen));
        int i4 = rBBIDataWrapper.fHeader.fTrie;
        byteBuffer.mark();
        rBBIDataWrapper.fTrie = Trie2.createFromSerialized(byteBuffer);
        byteBuffer.reset();
        int i5 = rBBIDataWrapper.fHeader.fStatusTable;
        if (i4 <= i5) {
            ICUBinary.skipBytes(byteBuffer, i5 - i4);
            RBBIDataHeader rBBIDataHeader7 = rBBIDataWrapper.fHeader;
            int i6 = rBBIDataHeader7.fStatusTable;
            int i7 = rBBIDataHeader7.fStatusTableLen;
            rBBIDataWrapper.fStatusTable = ICUBinary.getInts(byteBuffer, i7 / 4, i7 & 3);
            RBBIDataHeader rBBIDataHeader8 = rBBIDataWrapper.fHeader;
            int i8 = i6 + rBBIDataHeader8.fStatusTableLen;
            int i9 = rBBIDataHeader8.fRuleSource;
            if (i8 <= i9) {
                ICUBinary.skipBytes(byteBuffer, i9 - i8);
                RBBIDataHeader rBBIDataHeader9 = rBBIDataWrapper.fHeader;
                int i10 = rBBIDataHeader9.fRuleSource;
                int i11 = rBBIDataHeader9.fRuleSourceLen;
                rBBIDataWrapper.fRuleSource = ICUBinary.getString(byteBuffer, i11 / 2, i11 & 1);
                String str = RuleBasedBreakIterator.fDebugEnv;
                if (str == null || str.indexOf("data") < 0) {
                    return rBBIDataWrapper;
                }
                rBBIDataWrapper.dump(System.out);
                throw null;
            }
            throw new IOException("Break iterator Rule data corrupt");
        }
        throw new IOException("Break iterator Rule data corrupt");
    }

    public void dump(PrintStream printStream) {
        if (this.fFTable == null) {
            throw new NullPointerException();
        }
        printStream.println("RBBI Data Wrapper dump ...");
        printStream.println();
        printStream.println("Forward State Table");
        dumpTable(printStream, this.fFTable);
        printStream.println("Reverse State Table");
        dumpTable(printStream, this.fRTable);
        dumpCharCategories(printStream);
        throw null;
    }

    public static String intToString(int i, int i2) {
        StringBuilder sb = new StringBuilder(i2);
        sb.append(i);
        while (sb.length() < i2) {
            sb.insert(0, ' ');
        }
        return sb.toString();
    }

    public static String intToHexString(int i, int i2) {
        new StringBuilder(i2);
        Integer.toHexString(i);
        throw null;
    }

    private void dumpTable(PrintStream printStream, RBBIStateTable rBBIStateTable) {
        if (rBBIStateTable == null || rBBIStateTable.fTable.length == 0) {
            printStream.println("  -- null -- ");
            return;
        }
        StringBuilder sb = new StringBuilder(" Row  Acc Look  Tag");
        for (int i = 0; i < this.fHeader.fCatCount; i++) {
            sb.append(intToString(i, 5));
        }
        printStream.println(sb.toString());
        for (int i2 = 0; i2 < sb.length(); i2++) {
            printStream.print("-");
        }
        printStream.println();
        for (int i3 = 0; i3 < rBBIStateTable.fNumStates; i3++) {
            dumpRow(printStream, rBBIStateTable, i3);
        }
        printStream.println();
    }

    private void dumpRow(PrintStream printStream, RBBIStateTable rBBIStateTable, int i) {
        StringBuilder sb = new StringBuilder((this.fHeader.fCatCount * 5) + 20);
        sb.append(intToString(i, 4));
        int rowIndex = getRowIndex(i);
        short[] sArr = rBBIStateTable.fTable;
        int i2 = rowIndex + 0;
        if (sArr[i2] != 0) {
            sb.append(intToString(sArr[i2], 5));
        } else {
            sb.append("     ");
        }
        short[] sArr2 = rBBIStateTable.fTable;
        int i3 = rowIndex + 1;
        if (sArr2[i3] != 0) {
            sb.append(intToString(sArr2[i3], 5));
        } else {
            sb.append("     ");
        }
        sb.append(intToString(rBBIStateTable.fTable[rowIndex + 2], 5));
        for (int i4 = 0; i4 < this.fHeader.fCatCount; i4++) {
            sb.append(intToString(rBBIStateTable.fTable[rowIndex + 4 + i4], 5));
        }
        printStream.println(sb);
    }

    private void dumpCharCategories(PrintStream printStream) {
        int i = this.fHeader.fCatCount + 1;
        String[] strArr = new String[i];
        int[] iArr = new int[i];
        for (int i2 = 0; i2 <= this.fHeader.fCatCount; i2++) {
            strArr[i2] = "";
        }
        printStream.println("\nCharacter Categories");
        printStream.println("--------------------");
        int i3 = -1;
        int i4 = 0;
        for (int i5 = 0; i5 <= 1114111; i5++) {
            int i6 = this.fTrie.get(i5) & -16385;
            if (i6 < 0 || i6 > this.fHeader.fCatCount) {
                new StringBuilder().append("Error, bad category ");
                Integer.toHexString(i6);
                throw null;
            }
            if (i6 != i3) {
                if (i3 >= 0) {
                    if (strArr[i3].length() > iArr[i3] + 70) {
                        iArr[i3] = strArr[i3].length() + 10;
                        strArr[i3] = strArr[i3] + "\n       ";
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(strArr[i3]);
                    sb.append(" ");
                    Integer.toHexString(i4);
                    throw null;
                }
                i4 = i5;
                i3 = i6;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(strArr[i3]);
        sb2.append(" ");
        Integer.toHexString(i4);
        throw null;
    }
}
