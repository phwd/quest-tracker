package com.android.org.bouncycastle.util.encoders;

import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.OutputStream;

public class Base64Encoder implements Encoder {
    protected final byte[] decodingTable = new byte[128];
    protected final byte[] encodingTable = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    protected byte padding = 61;

    /* access modifiers changed from: protected */
    public void initialiseDecodingTable() {
        int i = 0;
        while (true) {
            byte[] bArr = this.decodingTable;
            if (i >= bArr.length) {
                break;
            }
            bArr[i] = -1;
            i++;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.encodingTable;
            if (i2 < bArr2.length) {
                this.decodingTable[bArr2[i2]] = (byte) i2;
                i2++;
            } else {
                return;
            }
        }
    }

    public Base64Encoder() {
        initialiseDecodingTable();
    }

    @Override // com.android.org.bouncycastle.util.encoders.Encoder
    public int encode(byte[] data, int off, int length, OutputStream out) throws IOException {
        int modulus = length % 3;
        int dataLength = length - modulus;
        for (int i = off; i < off + dataLength; i += 3) {
            int a1 = data[i] & UnsignedBytes.MAX_VALUE;
            int a2 = data[i + 1] & UnsignedBytes.MAX_VALUE;
            int a3 = data[i + 2] & UnsignedBytes.MAX_VALUE;
            out.write(this.encodingTable[(a1 >>> 2) & 63]);
            out.write(this.encodingTable[((a1 << 4) | (a2 >>> 4)) & 63]);
            out.write(this.encodingTable[((a2 << 2) | (a3 >>> 6)) & 63]);
            out.write(this.encodingTable[a3 & 63]);
        }
        if (modulus != 0) {
            if (modulus == 1) {
                int d1 = data[off + dataLength] & UnsignedBytes.MAX_VALUE;
                out.write(this.encodingTable[(d1 >>> 2) & 63]);
                out.write(this.encodingTable[(d1 << 4) & 63]);
                out.write(this.padding);
                out.write(this.padding);
            } else if (modulus == 2) {
                int d12 = data[off + dataLength] & UnsignedBytes.MAX_VALUE;
                int d2 = data[off + dataLength + 1] & UnsignedBytes.MAX_VALUE;
                out.write(this.encodingTable[(d12 >>> 2) & 63]);
                out.write(this.encodingTable[((d12 << 4) | (d2 >>> 4)) & 63]);
                out.write(this.encodingTable[(d2 << 2) & 63]);
                out.write(this.padding);
            }
        }
        int i2 = 4;
        int i3 = (dataLength / 3) * 4;
        if (modulus == 0) {
            i2 = 0;
        }
        return i3 + i2;
    }

    private boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    @Override // com.android.org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] data, int off, int length, OutputStream out) throws IOException {
        int end = off + length;
        while (end > off && ignore((char) data[end - 1])) {
            end--;
        }
        if (end == 0) {
            return 0;
        }
        int i = 0;
        int finish = end;
        while (finish > off && i != 4) {
            if (!ignore((char) data[finish - 1])) {
                i++;
            }
            finish--;
        }
        int outLen = 0;
        int i2 = nextI(data, off, finish);
        while (i2 < finish) {
            byte b1 = this.decodingTable[data[i2]];
            int i3 = nextI(data, i2 + 1, finish);
            int i4 = i3 + 1;
            byte b2 = this.decodingTable[data[i3]];
            int i5 = nextI(data, i4, finish);
            int i6 = i5 + 1;
            byte b3 = this.decodingTable[data[i5]];
            int i7 = nextI(data, i6, finish);
            int i8 = i7 + 1;
            byte b4 = this.decodingTable[data[i7]];
            if ((b1 | b2 | b3 | b4) >= 0) {
                out.write((b1 << 2) | (b2 >> 4));
                out.write((b2 << 4) | (b3 >> 2));
                out.write((b3 << 6) | b4);
                outLen += 3;
                i2 = nextI(data, i8, finish);
            } else {
                throw new IOException("invalid characters encountered in base64 data");
            }
        }
        int e0 = nextI(data, i2, end);
        int e1 = nextI(data, e0 + 1, end);
        int e2 = nextI(data, e1 + 1, end);
        return outLen + decodeLastBlock(out, (char) data[e0], (char) data[e1], (char) data[e2], (char) data[nextI(data, e2 + 1, end)]);
    }

    private int nextI(byte[] data, int i, int finish) {
        while (i < finish && ignore((char) data[i])) {
            i++;
        }
        return i;
    }

    @Override // com.android.org.bouncycastle.util.encoders.Encoder
    public int decode(String data, OutputStream out) throws IOException {
        int end = data.length();
        while (end > 0 && ignore(data.charAt(end - 1))) {
            end--;
        }
        if (end == 0) {
            return 0;
        }
        int i = 0;
        int finish = end;
        while (finish > 0 && i != 4) {
            if (!ignore(data.charAt(finish - 1))) {
                i++;
            }
            finish--;
        }
        int length = 0;
        int i2 = nextI(data, 0, finish);
        while (i2 < finish) {
            byte b1 = this.decodingTable[data.charAt(i2)];
            int i3 = nextI(data, i2 + 1, finish);
            int i4 = i3 + 1;
            byte b2 = this.decodingTable[data.charAt(i3)];
            int i5 = nextI(data, i4, finish);
            int i6 = i5 + 1;
            byte b3 = this.decodingTable[data.charAt(i5)];
            int i7 = nextI(data, i6, finish);
            int i8 = i7 + 1;
            byte b4 = this.decodingTable[data.charAt(i7)];
            if ((b1 | b2 | b3 | b4) >= 0) {
                out.write((b1 << 2) | (b2 >> 4));
                out.write((b2 << 4) | (b3 >> 2));
                out.write((b3 << 6) | b4);
                length += 3;
                i2 = nextI(data, i8, finish);
            } else {
                throw new IOException("invalid characters encountered in base64 data");
            }
        }
        int e0 = nextI(data, i2, end);
        int e1 = nextI(data, e0 + 1, end);
        int e2 = nextI(data, e1 + 1, end);
        return length + decodeLastBlock(out, data.charAt(e0), data.charAt(e1), data.charAt(e2), data.charAt(nextI(data, e2 + 1, end)));
    }

    private int decodeLastBlock(OutputStream out, char c1, char c2, char c3, char c4) throws IOException {
        byte b = this.padding;
        if (c3 == b) {
            if (c4 == b) {
                byte[] bArr = this.decodingTable;
                byte b1 = bArr[c1];
                byte b2 = bArr[c2];
                if ((b1 | b2) >= 0) {
                    out.write((b1 << 2) | (b2 >> 4));
                    return 1;
                }
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else if (c4 == b) {
            byte[] bArr2 = this.decodingTable;
            byte b12 = bArr2[c1];
            byte b22 = bArr2[c2];
            byte b3 = bArr2[c3];
            if ((b12 | b22 | b3) >= 0) {
                out.write((b12 << 2) | (b22 >> 4));
                out.write((b22 << 4) | (b3 >> 2));
                return 2;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else {
            byte[] bArr3 = this.decodingTable;
            byte b13 = bArr3[c1];
            byte b23 = bArr3[c2];
            byte b32 = bArr3[c3];
            byte b4 = bArr3[c4];
            if ((b13 | b23 | b32 | b4) >= 0) {
                out.write((b13 << 2) | (b23 >> 4));
                out.write((b23 << 4) | (b32 >> 2));
                out.write((b32 << 6) | b4);
                return 3;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        }
    }

    private int nextI(String data, int i, int finish) {
        while (i < finish && ignore(data.charAt(i))) {
            i++;
        }
        return i;
    }
}
