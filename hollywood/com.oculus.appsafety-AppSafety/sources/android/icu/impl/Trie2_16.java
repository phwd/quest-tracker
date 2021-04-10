package android.icu.impl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public final class Trie2_16 extends Trie2 {
    Trie2_16() {
    }

    public static Trie2_16 createFromSerialized(ByteBuffer bytes) throws IOException {
        return (Trie2_16) Trie2.createFromSerialized(bytes);
    }

    @Override // android.icu.impl.Trie2
    public final int get(int codePoint) {
        if (codePoint >= 0) {
            if (codePoint < 55296 || (codePoint > 56319 && codePoint <= 65535)) {
                return this.index[(this.index[codePoint >> 5] << 2) + (codePoint & 31)];
            } else if (codePoint <= 65535) {
                return this.index[(this.index[((codePoint - 55296) >> 5) + 2048] << 2) + (codePoint & 31)];
            } else if (codePoint < this.highStart) {
                int ix = this.index[(codePoint >> 11) + 2080] + ((codePoint >> 5) & 63);
                return this.index[(this.index[ix] << 2) + (codePoint & 31)];
            } else if (codePoint <= 1114111) {
                return this.index[this.highValueIndex];
            }
        }
        return this.errorValue;
    }

    @Override // android.icu.impl.Trie2
    public int getFromU16SingleLead(char codeUnit) {
        return this.index[(this.index[codeUnit >> 5] << 2) + (codeUnit & 31)];
    }

    public int serialize(OutputStream os) throws IOException {
        DataOutputStream dos = new DataOutputStream(os);
        int bytesWritten = 0 + serializeHeader(dos);
        for (int i = 0; i < this.dataLength; i++) {
            dos.writeChar(this.index[this.data16 + i]);
        }
        return bytesWritten + (this.dataLength * 2);
    }

    public int getSerializedLength() {
        return ((this.header.indexLength + this.dataLength) * 2) + 16;
    }

    /* access modifiers changed from: package-private */
    @Override // android.icu.impl.Trie2
    public int rangeEnd(int startingCP, int limit, int value) {
        char c;
        int block;
        int cp = startingCP;
        loop0:
        while (true) {
            if (cp >= limit) {
                break;
            }
            if (cp < 55296 || (cp > 56319 && cp <= 65535)) {
                c = 0;
                block = this.index[cp >> 5] << 2;
            } else if (cp < 65535) {
                c = 2048;
                block = this.index[((cp - 55296) >> 5) + 2048] << 2;
            } else if (cp < this.highStart) {
                c = this.index[(cp >> 11) + 2080];
                block = this.index[((cp >> 5) & 63) + c] << 2;
            } else if (value == this.index[this.highValueIndex]) {
                cp = limit;
            }
            if (c == this.index2NullOffset) {
                if (value != this.initialValue) {
                    break;
                }
                cp += 2048;
            } else if (block != this.dataNullOffset) {
                int startIx = (cp & 31) + block;
                int limitIx = block + 32;
                for (int ix = startIx; ix < limitIx; ix++) {
                    if (this.index[ix] != value) {
                        cp += ix - startIx;
                        break loop0;
                    }
                }
                cp += limitIx - startIx;
            } else if (value != this.initialValue) {
                break;
            } else {
                cp += 32;
            }
        }
        if (cp > limit) {
            cp = limit;
        }
        return cp - 1;
    }
}
