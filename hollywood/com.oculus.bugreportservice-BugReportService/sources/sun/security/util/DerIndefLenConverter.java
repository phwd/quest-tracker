package sun.security.util;

import java.io.IOException;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
public class DerIndefLenConverter {
    private byte[] data;
    private int dataPos;
    private int dataSize;
    private int index;
    private ArrayList ndefsList = new ArrayList();
    private byte[] newData;
    private int newDataPos;
    private int numOfTotalLenBytes = 0;
    private int unresolved = 0;

    private byte[] getLengthBytes(int i) {
        if (i < 128) {
            return new byte[]{(byte) i};
        } else if (i < 256) {
            return new byte[]{-127, (byte) i};
        } else if (i < 65536) {
            return new byte[]{-126, (byte) (i >> 8), (byte) i};
        } else if (i < 16777216) {
            return new byte[]{-125, (byte) (i >> 16), (byte) (i >> 8), (byte) i};
        } else {
            return new byte[]{-124, (byte) (i >> 24), (byte) (i >> 16), (byte) (i >> 8), (byte) i};
        }
    }

    private boolean isEOC(int i) {
        return (i & 31) == 0 && (i & 32) == 0 && (i & 192) == 0;
    }

    static boolean isLongForm(int i) {
        return (i & 128) == 128;
    }

    DerIndefLenConverter() {
    }

    static boolean isIndefinite(int i) {
        return isLongForm(i) && (i & 127) == 0;
    }

    private void parseTag() {
        int i = this.dataPos;
        if (i != this.dataSize) {
            if (isEOC(this.data[i]) && this.data[this.dataPos + 1] == 0) {
                int i2 = 0;
                Object obj = null;
                int size = this.ndefsList.size() - 1;
                while (size >= 0) {
                    obj = this.ndefsList.get(size);
                    if (obj instanceof Integer) {
                        break;
                    }
                    i2 += ((byte[]) obj).length - 3;
                    size--;
                }
                if (size >= 0) {
                    byte[] lengthBytes = getLengthBytes((this.dataPos - ((Integer) obj).intValue()) + i2);
                    this.ndefsList.set(size, lengthBytes);
                    this.unresolved--;
                    this.numOfTotalLenBytes += lengthBytes.length - 3;
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

    private int parseLength() {
        int i = this.dataPos;
        if (i == this.dataSize) {
            return 0;
        }
        byte[] bArr = this.data;
        this.dataPos = i + 1;
        int i2 = bArr[i] & 255;
        if (isIndefinite(i2)) {
            this.ndefsList.add(new Integer(this.dataPos));
            this.unresolved++;
            return 0;
        } else if (!isLongForm(i2)) {
            return i2 & 127;
        } else {
            int i3 = i2 & 127;
            if (i3 > 4) {
                throw new IOException("Too much data");
            } else if (this.dataSize - this.dataPos >= i3 + 1) {
                int i4 = 0;
                for (int i5 = 0; i5 < i3; i5++) {
                    byte[] bArr2 = this.data;
                    int i6 = this.dataPos;
                    this.dataPos = i6 + 1;
                    i4 = (i4 << 8) + (bArr2[i6] & 255);
                }
                if (i4 >= 0) {
                    return i4;
                }
                throw new IOException("Invalid length bytes");
            } else {
                throw new IOException("Too little data");
            }
        }
    }

    private void writeLengthAndValue() {
        int i;
        int i2 = this.dataPos;
        if (i2 != this.dataSize) {
            byte[] bArr = this.data;
            this.dataPos = i2 + 1;
            int i3 = bArr[i2] & 255;
            if (isIndefinite(i3)) {
                ArrayList arrayList = this.ndefsList;
                int i4 = this.index;
                this.index = i4 + 1;
                byte[] bArr2 = (byte[]) arrayList.get(i4);
                System.arraycopy(bArr2, 0, this.newData, this.newDataPos, bArr2.length);
                this.newDataPos += bArr2.length;
                return;
            }
            if (isLongForm(i3)) {
                int i5 = i3 & 127;
                i = 0;
                for (int i6 = 0; i6 < i5; i6++) {
                    byte[] bArr3 = this.data;
                    int i7 = this.dataPos;
                    this.dataPos = i7 + 1;
                    i = (i << 8) + (bArr3[i7] & 255);
                }
                if (i < 0) {
                    throw new IOException("Invalid length bytes");
                }
            } else {
                i = i3 & 127;
            }
            writeLength(i);
            writeValue(i);
        }
    }

    private void writeLength(int i) {
        if (i < 128) {
            byte[] bArr = this.newData;
            int i2 = this.newDataPos;
            this.newDataPos = i2 + 1;
            bArr[i2] = (byte) i;
        } else if (i < 256) {
            byte[] bArr2 = this.newData;
            int i3 = this.newDataPos;
            this.newDataPos = i3 + 1;
            bArr2[i3] = -127;
            int i4 = this.newDataPos;
            this.newDataPos = i4 + 1;
            bArr2[i4] = (byte) i;
        } else if (i < 65536) {
            byte[] bArr3 = this.newData;
            int i5 = this.newDataPos;
            this.newDataPos = i5 + 1;
            bArr3[i5] = -126;
            int i6 = this.newDataPos;
            this.newDataPos = i6 + 1;
            bArr3[i6] = (byte) (i >> 8);
            int i7 = this.newDataPos;
            this.newDataPos = i7 + 1;
            bArr3[i7] = (byte) i;
        } else if (i < 16777216) {
            byte[] bArr4 = this.newData;
            int i8 = this.newDataPos;
            this.newDataPos = i8 + 1;
            bArr4[i8] = -125;
            int i9 = this.newDataPos;
            this.newDataPos = i9 + 1;
            bArr4[i9] = (byte) (i >> 16);
            int i10 = this.newDataPos;
            this.newDataPos = i10 + 1;
            bArr4[i10] = (byte) (i >> 8);
            int i11 = this.newDataPos;
            this.newDataPos = i11 + 1;
            bArr4[i11] = (byte) i;
        } else {
            byte[] bArr5 = this.newData;
            int i12 = this.newDataPos;
            this.newDataPos = i12 + 1;
            bArr5[i12] = -124;
            int i13 = this.newDataPos;
            this.newDataPos = i13 + 1;
            bArr5[i13] = (byte) (i >> 24);
            int i14 = this.newDataPos;
            this.newDataPos = i14 + 1;
            bArr5[i14] = (byte) (i >> 16);
            int i15 = this.newDataPos;
            this.newDataPos = i15 + 1;
            bArr5[i15] = (byte) (i >> 8);
            int i16 = this.newDataPos;
            this.newDataPos = i16 + 1;
            bArr5[i16] = (byte) i;
        }
    }

    private void parseValue(int i) {
        this.dataPos += i;
    }

    private void writeValue(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            byte[] bArr = this.newData;
            int i3 = this.newDataPos;
            this.newDataPos = i3 + 1;
            byte[] bArr2 = this.data;
            int i4 = this.dataPos;
            this.dataPos = i4 + 1;
            bArr[i3] = bArr2[i4];
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] convert(byte[] bArr) {
        int i;
        this.data = bArr;
        this.dataPos = 0;
        this.index = 0;
        this.dataSize = this.data.length;
        while (true) {
            if (this.dataPos >= this.dataSize) {
                i = 0;
                break;
            }
            parseTag();
            parseValue(parseLength());
            if (this.unresolved == 0) {
                int i2 = this.dataSize;
                int i3 = this.dataPos;
                i = i2 - i3;
                this.dataSize = i3;
                break;
            }
        }
        if (this.unresolved == 0) {
            this.newData = new byte[(this.dataSize + this.numOfTotalLenBytes + i)];
            this.dataPos = 0;
            this.newDataPos = 0;
            this.index = 0;
            while (true) {
                int i4 = this.dataPos;
                int i5 = this.dataSize;
                if (i4 < i5) {
                    writeTag();
                    writeLengthAndValue();
                } else {
                    System.arraycopy(bArr, i5, this.newData, this.numOfTotalLenBytes + i5, i);
                    return this.newData;
                }
            }
        } else {
            throw new IOException("not all indef len BER resolved");
        }
    }
}
