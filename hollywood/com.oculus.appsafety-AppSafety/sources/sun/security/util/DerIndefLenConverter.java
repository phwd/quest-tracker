package sun.security.util;

import java.io.IOException;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
public class DerIndefLenConverter {
    private static final int CLASS_MASK = 192;
    private static final int FORM_MASK = 32;
    private static final int LEN_LONG = 128;
    private static final int LEN_MASK = 127;
    private static final int SKIP_EOC_BYTES = 2;
    private static final int TAG_MASK = 31;
    private byte[] data;
    private int dataPos;
    private int dataSize;
    private int index;
    private ArrayList<Object> ndefsList = new ArrayList<>();
    private byte[] newData;
    private int newDataPos;
    private int numOfTotalLenBytes = 0;
    private int unresolved = 0;

    private boolean isEOC(int tag) {
        return (tag & 31) == 0 && (tag & 32) == 0 && (tag & 192) == 0;
    }

    static boolean isLongForm(int lengthByte) {
        return (lengthByte & 128) == 128;
    }

    DerIndefLenConverter() {
    }

    static boolean isIndefinite(int lengthByte) {
        return isLongForm(lengthByte) && (lengthByte & 127) == 0;
    }

    private void parseTag() throws IOException {
        int i = this.dataPos;
        if (i != this.dataSize) {
            if (isEOC(this.data[i]) && this.data[this.dataPos + 1] == 0) {
                int numOfEncapsulatedLenBytes = 0;
                Object elem = null;
                int index2 = this.ndefsList.size() - 1;
                while (index2 >= 0) {
                    elem = this.ndefsList.get(index2);
                    if (elem instanceof Integer) {
                        break;
                    }
                    numOfEncapsulatedLenBytes += ((byte[]) elem).length - 3;
                    index2--;
                }
                if (index2 >= 0) {
                    byte[] sectionLenBytes = getLengthBytes((this.dataPos - ((Integer) elem).intValue()) + numOfEncapsulatedLenBytes);
                    this.ndefsList.set(index2, sectionLenBytes);
                    this.unresolved--;
                    this.numOfTotalLenBytes += sectionLenBytes.length - 3;
                } else {
                    throw new IOException("EOC does not have matching indefinite-length tag");
                }
            }
            this.dataPos++;
        }
    }

    private void writeTag() {
        int i = this.dataPos;
        if (i != this.dataSize) {
            byte[] bArr = this.data;
            this.dataPos = i + 1;
            byte b = bArr[i];
            if (isEOC(b)) {
                byte[] bArr2 = this.data;
                int i2 = this.dataPos;
                if (bArr2[i2] == 0) {
                    this.dataPos = i2 + 1;
                    writeTag();
                    return;
                }
            }
            byte[] bArr3 = this.newData;
            int i3 = this.newDataPos;
            this.newDataPos = i3 + 1;
            bArr3[i3] = (byte) b;
        }
    }

    private int parseLength() throws IOException {
        int curLen = 0;
        int i = this.dataPos;
        if (i == this.dataSize) {
            return 0;
        }
        byte[] bArr = this.data;
        this.dataPos = i + 1;
        int lenByte = bArr[i] & 255;
        if (isIndefinite(lenByte)) {
            this.ndefsList.add(new Integer(this.dataPos));
            this.unresolved++;
            return 0;
        } else if (!isLongForm(lenByte)) {
            return lenByte & 127;
        } else {
            int lenByte2 = lenByte & 127;
            if (lenByte2 > 4) {
                throw new IOException("Too much data");
            } else if (this.dataSize - this.dataPos >= lenByte2 + 1) {
                for (int i2 = 0; i2 < lenByte2; i2++) {
                    byte[] bArr2 = this.data;
                    int i3 = this.dataPos;
                    this.dataPos = i3 + 1;
                    curLen = (curLen << 8) + (bArr2[i3] & 255);
                }
                if (curLen >= 0) {
                    return curLen;
                }
                throw new IOException("Invalid length bytes");
            } else {
                throw new IOException("Too little data");
            }
        }
    }

    private void writeLengthAndValue() throws IOException {
        int i = this.dataPos;
        if (i != this.dataSize) {
            int curLen = 0;
            byte[] bArr = this.data;
            this.dataPos = i + 1;
            int lenByte = bArr[i] & 255;
            if (isIndefinite(lenByte)) {
                ArrayList<Object> arrayList = this.ndefsList;
                int i2 = this.index;
                this.index = i2 + 1;
                byte[] lenBytes = (byte[]) arrayList.get(i2);
                System.arraycopy(lenBytes, 0, this.newData, this.newDataPos, lenBytes.length);
                this.newDataPos += lenBytes.length;
                return;
            }
            if (isLongForm(lenByte)) {
                int lenByte2 = lenByte & 127;
                for (int i3 = 0; i3 < lenByte2; i3++) {
                    byte[] bArr2 = this.data;
                    int i4 = this.dataPos;
                    this.dataPos = i4 + 1;
                    curLen = (curLen << 8) + (bArr2[i4] & 255);
                }
                if (curLen < 0) {
                    throw new IOException("Invalid length bytes");
                }
            } else {
                curLen = lenByte & 127;
            }
            writeLength(curLen);
            writeValue(curLen);
        }
    }

    private void writeLength(int curLen) {
        if (curLen < 128) {
            byte[] bArr = this.newData;
            int i = this.newDataPos;
            this.newDataPos = i + 1;
            bArr[i] = (byte) curLen;
        } else if (curLen < 256) {
            byte[] bArr2 = this.newData;
            int i2 = this.newDataPos;
            this.newDataPos = i2 + 1;
            bArr2[i2] = -127;
            int i3 = this.newDataPos;
            this.newDataPos = i3 + 1;
            bArr2[i3] = (byte) curLen;
        } else if (curLen < 65536) {
            byte[] bArr3 = this.newData;
            int i4 = this.newDataPos;
            this.newDataPos = i4 + 1;
            bArr3[i4] = -126;
            int i5 = this.newDataPos;
            this.newDataPos = i5 + 1;
            bArr3[i5] = (byte) (curLen >> 8);
            int i6 = this.newDataPos;
            this.newDataPos = i6 + 1;
            bArr3[i6] = (byte) curLen;
        } else if (curLen < 16777216) {
            byte[] bArr4 = this.newData;
            int i7 = this.newDataPos;
            this.newDataPos = i7 + 1;
            bArr4[i7] = -125;
            int i8 = this.newDataPos;
            this.newDataPos = i8 + 1;
            bArr4[i8] = (byte) (curLen >> 16);
            int i9 = this.newDataPos;
            this.newDataPos = i9 + 1;
            bArr4[i9] = (byte) (curLen >> 8);
            int i10 = this.newDataPos;
            this.newDataPos = i10 + 1;
            bArr4[i10] = (byte) curLen;
        } else {
            byte[] bArr5 = this.newData;
            int i11 = this.newDataPos;
            this.newDataPos = i11 + 1;
            bArr5[i11] = -124;
            int i12 = this.newDataPos;
            this.newDataPos = i12 + 1;
            bArr5[i12] = (byte) (curLen >> 24);
            int i13 = this.newDataPos;
            this.newDataPos = i13 + 1;
            bArr5[i13] = (byte) (curLen >> 16);
            int i14 = this.newDataPos;
            this.newDataPos = i14 + 1;
            bArr5[i14] = (byte) (curLen >> 8);
            int i15 = this.newDataPos;
            this.newDataPos = i15 + 1;
            bArr5[i15] = (byte) curLen;
        }
    }

    private byte[] getLengthBytes(int curLen) {
        if (curLen < 128) {
            int i = 0 + 1;
            return new byte[]{(byte) curLen};
        } else if (curLen < 256) {
            byte[] lenBytes = new byte[2];
            int index2 = 0 + 1;
            lenBytes[0] = -127;
            int i2 = index2 + 1;
            lenBytes[index2] = (byte) curLen;
            return lenBytes;
        } else if (curLen < 65536) {
            byte[] lenBytes2 = new byte[3];
            int index3 = 0 + 1;
            lenBytes2[0] = -126;
            int index4 = index3 + 1;
            lenBytes2[index3] = (byte) (curLen >> 8);
            int i3 = index4 + 1;
            lenBytes2[index4] = (byte) curLen;
            return lenBytes2;
        } else if (curLen < 16777216) {
            byte[] lenBytes3 = new byte[4];
            int index5 = 0 + 1;
            lenBytes3[0] = -125;
            int index6 = index5 + 1;
            lenBytes3[index5] = (byte) (curLen >> 16);
            int index7 = index6 + 1;
            lenBytes3[index6] = (byte) (curLen >> 8);
            int i4 = index7 + 1;
            lenBytes3[index7] = (byte) curLen;
            return lenBytes3;
        } else {
            byte[] lenBytes4 = new byte[5];
            int index8 = 0 + 1;
            lenBytes4[0] = -124;
            int index9 = index8 + 1;
            lenBytes4[index8] = (byte) (curLen >> 24);
            int index10 = index9 + 1;
            lenBytes4[index9] = (byte) (curLen >> 16);
            int index11 = index10 + 1;
            lenBytes4[index10] = (byte) (curLen >> 8);
            int i5 = index11 + 1;
            lenBytes4[index11] = (byte) curLen;
            return lenBytes4;
        }
    }

    private int getNumOfLenBytes(int len) {
        if (len < 128) {
            return 1;
        }
        if (len < 256) {
            return 2;
        }
        if (len < 65536) {
            return 3;
        }
        if (len < 16777216) {
            return 4;
        }
        return 5;
    }

    private void parseValue(int curLen) {
        this.dataPos += curLen;
    }

    private void writeValue(int curLen) {
        for (int i = 0; i < curLen; i++) {
            byte[] bArr = this.newData;
            int i2 = this.newDataPos;
            this.newDataPos = i2 + 1;
            byte[] bArr2 = this.data;
            int i3 = this.dataPos;
            this.dataPos = i3 + 1;
            bArr[i2] = bArr2[i3];
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] convert(byte[] indefData) throws IOException {
        this.data = indefData;
        this.dataPos = 0;
        this.index = 0;
        this.dataSize = this.data.length;
        int unused = 0;
        while (true) {
            if (this.dataPos >= this.dataSize) {
                break;
            }
            parseTag();
            parseValue(parseLength());
            if (this.unresolved == 0) {
                int i = this.dataSize;
                int i2 = this.dataPos;
                unused = i - i2;
                this.dataSize = i2;
                break;
            }
        }
        if (this.unresolved == 0) {
            this.newData = new byte[(this.dataSize + this.numOfTotalLenBytes + unused)];
            this.dataPos = 0;
            this.newDataPos = 0;
            this.index = 0;
            while (true) {
                int i3 = this.dataPos;
                int i4 = this.dataSize;
                if (i3 < i4) {
                    writeTag();
                    writeLengthAndValue();
                } else {
                    System.arraycopy(indefData, i4, this.newData, this.numOfTotalLenBytes + i4, unused);
                    return this.newData;
                }
            }
        } else {
            throw new IOException("not all indef len BER resolved");
        }
    }
}
