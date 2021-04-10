package android.icu.impl;

import android.icu.impl.ICUBinary;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;

public final class StringPrepDataReader implements ICUBinary.Authenticate {
    private static final int DATA_FORMAT_ID = 1397772880;
    private static final byte[] DATA_FORMAT_VERSION = {3, 2, 5, 2};
    private static final boolean debug = ICUDebug.enabled("NormalizerDataReader");
    private ByteBuffer byteBuffer;
    private int unicodeVersion;

    public StringPrepDataReader(ByteBuffer bytes) throws IOException {
        if (debug) {
            PrintStream printStream = System.out;
            printStream.println("Bytes in buffer " + bytes.remaining());
        }
        this.byteBuffer = bytes;
        this.unicodeVersion = ICUBinary.readHeader(this.byteBuffer, DATA_FORMAT_ID, this);
        if (debug) {
            PrintStream printStream2 = System.out;
            printStream2.println("Bytes left in byteBuffer " + this.byteBuffer.remaining());
        }
    }

    public char[] read(int length) throws IOException {
        return ICUBinary.getChars(this.byteBuffer, length, 0);
    }

    @Override // android.icu.impl.ICUBinary.Authenticate
    public boolean isDataVersionAcceptable(byte[] version) {
        byte b = version[0];
        byte[] bArr = DATA_FORMAT_VERSION;
        return b == bArr[0] && version[2] == bArr[2] && version[3] == bArr[3];
    }

    public int[] readIndexes(int length) throws IOException {
        int[] indexes = new int[length];
        for (int i = 0; i < length; i++) {
            indexes[i] = this.byteBuffer.getInt();
        }
        return indexes;
    }

    public byte[] getUnicodeVersion() {
        return ICUBinary.getVersionByteArrayFromCompactInt(this.unicodeVersion);
    }
}
