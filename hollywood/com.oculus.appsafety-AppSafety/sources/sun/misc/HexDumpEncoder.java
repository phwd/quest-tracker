package sun.misc;

import android.icu.impl.number.Padder;
import android.icu.text.PluralRules;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class HexDumpEncoder extends CharacterEncoder {
    private int currentByte;
    private int offset;
    private byte[] thisLine = new byte[16];
    private int thisLineLength;

    static void hexDigit(PrintStream p, byte x) {
        char c;
        char c2;
        char c3 = (char) ((x >> 4) & 15);
        if (c3 > '\t') {
            c = (char) ((c3 - '\n') + 65);
        } else {
            c = (char) (c3 + '0');
        }
        p.write(c);
        char c4 = (char) (x & 15);
        if (c4 > '\t') {
            c2 = (char) ((c4 - '\n') + 65);
        } else {
            c2 = (char) (c4 + '0');
        }
        p.write(c2);
    }

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

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterEncoder
    public void encodeBufferPrefix(OutputStream o) throws IOException {
        this.offset = 0;
        super.encodeBufferPrefix(o);
    }

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterEncoder
    public void encodeLinePrefix(OutputStream o, int len) throws IOException {
        hexDigit(this.pStream, (byte) ((this.offset >>> 8) & 255));
        hexDigit(this.pStream, (byte) (this.offset & 255));
        this.pStream.print(PluralRules.KEYWORD_RULE_SEPARATOR);
        this.currentByte = 0;
        this.thisLineLength = len;
    }

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterEncoder
    public void encodeAtom(OutputStream o, byte[] buf, int off, int len) throws IOException {
        this.thisLine[this.currentByte] = buf[off];
        hexDigit(this.pStream, buf[off]);
        this.pStream.print(Padder.FALLBACK_PADDING_STRING);
        this.currentByte++;
        if (this.currentByte == 8) {
            this.pStream.print("  ");
        }
    }

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterEncoder
    public void encodeLineSuffix(OutputStream o) throws IOException {
        if (this.thisLineLength < 16) {
            for (int i = this.thisLineLength; i < 16; i++) {
                this.pStream.print("   ");
                if (i == 7) {
                    this.pStream.print("  ");
                }
            }
        }
        this.pStream.print(Padder.FALLBACK_PADDING_STRING);
        for (int i2 = 0; i2 < this.thisLineLength; i2++) {
            byte[] bArr = this.thisLine;
            if (bArr[i2] < 32 || bArr[i2] > 122) {
                this.pStream.print(".");
            } else {
                this.pStream.write(this.thisLine[i2]);
            }
        }
        this.pStream.println();
        this.offset += this.thisLineLength;
    }
}
