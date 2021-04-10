package android.icu.impl;

import android.icu.impl.ICUBinary;
import java.io.PrintStream;
import java.nio.ByteBuffer;

public final class StringPrepDataReader implements ICUBinary.Authenticate {
    private static final byte[] DATA_FORMAT_VERSION = {3, 2, 5, 2};
    private static final boolean debug = ICUDebug.enabled("NormalizerDataReader");
    private ByteBuffer byteBuffer;
    private int unicodeVersion;

    public StringPrepDataReader(ByteBuffer byteBuffer2) {
        if (debug) {
            PrintStream printStream = System.out;
            printStream.println("Bytes in buffer " + byteBuffer2.remaining());
        }
        this.byteBuffer = byteBuffer2;
        this.unicodeVersion = ICUBinary.readHeader(this.byteBuffer, 1397772880, this);
        if (debug) {
            PrintStream printStream2 = System.out;
            printStream2.println("Bytes left in byteBuffer " + this.byteBuffer.remaining());
        }
    }

    public char[] read(int i) {
        return ICUBinary.getChars(this.byteBuffer, i, 0);
    }

    @Override // android.icu.impl.ICUBinary.Authenticate
    public boolean isDataVersionAcceptable(byte[] bArr) {
        byte b = bArr[0];
        byte[] bArr2 = DATA_FORMAT_VERSION;
        return b == bArr2[0] && bArr[2] == bArr2[2] && bArr[3] == bArr2[3];
    }

    public int[] readIndexes(int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = this.byteBuffer.getInt();
        }
        return iArr;
    }

    public byte[] getUnicodeVersion() {
        return ICUBinary.getVersionByteArrayFromCompactInt(this.unicodeVersion);
    }
}
