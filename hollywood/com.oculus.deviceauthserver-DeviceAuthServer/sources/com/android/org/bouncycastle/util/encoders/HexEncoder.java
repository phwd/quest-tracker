package com.android.org.bouncycastle.util.encoders;

import com.google.common.primitives.UnsignedBytes;
import java.io.IOException;
import java.io.OutputStream;

public class HexEncoder implements Encoder {
    protected final byte[] decodingTable = new byte[128];
    protected final byte[] encodingTable = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

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
                byte[] bArr3 = this.decodingTable;
                bArr3[65] = bArr3[97];
                bArr3[66] = bArr3[98];
                bArr3[67] = bArr3[99];
                bArr3[68] = bArr3[100];
                bArr3[69] = bArr3[101];
                bArr3[70] = bArr3[102];
                return;
            }
        }
    }

    public HexEncoder() {
        initialiseDecodingTable();
    }

    @Override // com.android.org.bouncycastle.util.encoders.Encoder
    public int encode(byte[] data, int off, int length, OutputStream out) throws IOException {
        for (int i = off; i < off + length; i++) {
            int v = data[i] & UnsignedBytes.MAX_VALUE;
            out.write(this.encodingTable[v >>> 4]);
            out.write(this.encodingTable[v & 15]);
        }
        return length * 2;
    }

    private static boolean ignore(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    @Override // com.android.org.bouncycastle.util.encoders.Encoder
    public int decode(byte[] data, int off, int length, OutputStream out) throws IOException {
        int outLen = 0;
        int end = off + length;
        while (end > off && ignore((char) data[end - 1])) {
            end--;
        }
        int i = off;
        while (i < end) {
            while (i < end && ignore((char) data[i])) {
                i++;
            }
            int i2 = i + 1;
            byte b1 = this.decodingTable[data[i]];
            while (i2 < end && ignore((char) data[i2])) {
                i2++;
            }
            int i3 = i2 + 1;
            byte b2 = this.decodingTable[data[i2]];
            if ((b1 | b2) >= 0) {
                out.write((b1 << 4) | b2);
                outLen++;
                i = i3;
            } else {
                throw new IOException("invalid characters encountered in Hex data");
            }
        }
        return outLen;
    }

    @Override // com.android.org.bouncycastle.util.encoders.Encoder
    public int decode(String data, OutputStream out) throws IOException {
        int length = 0;
        int end = data.length();
        while (end > 0 && ignore(data.charAt(end - 1))) {
            end--;
        }
        int i = 0;
        while (i < end) {
            while (i < end && ignore(data.charAt(i))) {
                i++;
            }
            int i2 = i + 1;
            byte b1 = this.decodingTable[data.charAt(i)];
            while (i2 < end && ignore(data.charAt(i2))) {
                i2++;
            }
            int i3 = i2 + 1;
            byte b2 = this.decodingTable[data.charAt(i2)];
            if ((b1 | b2) >= 0) {
                out.write((b1 << 4) | b2);
                length++;
                i = i3;
            } else {
                throw new IOException("invalid characters encountered in Hex string");
            }
        }
        return length;
    }
}
