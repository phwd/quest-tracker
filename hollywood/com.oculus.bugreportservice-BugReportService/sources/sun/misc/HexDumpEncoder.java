package sun.misc;

import java.io.OutputStream;
import java.io.PrintStream;

public class HexDumpEncoder extends CharacterEncoder {
    private int currentByte;
    private int offset;
    private byte[] thisLine = new byte[16];
    private int thisLineLength;

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterEncoder
    public int bytesPerAtom() {
        return 1;
    }

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterEncoder
    public int bytesPerLine() {
        return 16;
    }

    static void hexDigit(PrintStream printStream, byte b) {
        char c = (char) ((b >> 4) & 15);
        printStream.write((char) (c > '\t' ? (c - '\n') + 65 : c + '0'));
        char c2 = (char) (b & 15);
        printStream.write((char) (c2 > '\t' ? (c2 - '\n') + 65 : c2 + '0'));
    }

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterEncoder
    public void encodeBufferPrefix(OutputStream outputStream) {
        this.offset = 0;
        super.encodeBufferPrefix(outputStream);
    }

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterEncoder
    public void encodeLinePrefix(OutputStream outputStream, int i) {
        hexDigit(this.pStream, (byte) ((this.offset >>> 8) & 255));
        hexDigit(this.pStream, (byte) (this.offset & 255));
        this.pStream.print(": ");
        this.currentByte = 0;
        this.thisLineLength = i;
    }

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterEncoder
    public void encodeAtom(OutputStream outputStream, byte[] bArr, int i, int i2) {
        this.thisLine[this.currentByte] = bArr[i];
        hexDigit(this.pStream, bArr[i]);
        this.pStream.print(" ");
        this.currentByte++;
        if (this.currentByte == 8) {
            this.pStream.print("  ");
        }
    }

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterEncoder
    public void encodeLineSuffix(OutputStream outputStream) {
        int i = this.thisLineLength;
        if (i < 16) {
            while (i < 16) {
                this.pStream.print("   ");
                if (i == 7) {
                    this.pStream.print("  ");
                }
                i++;
            }
        }
        this.pStream.print(" ");
        for (int i2 = 0; i2 < this.thisLineLength; i2++) {
            byte[] bArr = this.thisLine;
            if (bArr[i2] < 32 || bArr[i2] > 122) {
                this.pStream.print(".");
            } else {
                this.pStream.write(bArr[i2]);
            }
        }
        this.pStream.println();
        this.offset += this.thisLineLength;
    }
}
