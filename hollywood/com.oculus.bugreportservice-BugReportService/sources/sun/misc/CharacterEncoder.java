package sun.misc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public abstract class CharacterEncoder {
    protected PrintStream pStream;

    /* access modifiers changed from: protected */
    public abstract int bytesPerAtom();

    /* access modifiers changed from: protected */
    public abstract int bytesPerLine();

    /* access modifiers changed from: protected */
    public abstract void encodeAtom(OutputStream outputStream, byte[] bArr, int i, int i2);

    /* access modifiers changed from: protected */
    public void encodeBufferSuffix(OutputStream outputStream) {
    }

    /* access modifiers changed from: protected */
    public abstract void encodeLinePrefix(OutputStream outputStream, int i);

    /* access modifiers changed from: protected */
    public abstract void encodeLineSuffix(OutputStream outputStream);

    /* access modifiers changed from: protected */
    public void encodeBufferPrefix(OutputStream outputStream) {
        this.pStream = new PrintStream(outputStream);
    }

    /* access modifiers changed from: protected */
    public int readFully(InputStream inputStream, byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            int read = inputStream.read();
            if (read == -1) {
                return i;
            }
            bArr[i] = (byte) read;
        }
        return bArr.length;
    }

    public void encode(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[bytesPerLine()];
        encodeBufferPrefix(outputStream);
        while (true) {
            int readFully = readFully(inputStream, bArr);
            if (readFully == 0) {
                break;
            }
            encodeLinePrefix(outputStream, readFully);
            int i = 0;
            while (i < readFully) {
                if (bytesPerAtom() + i <= readFully) {
                    encodeAtom(outputStream, bArr, i, bytesPerAtom());
                } else {
                    encodeAtom(outputStream, bArr, i, readFully - i);
                }
                i += bytesPerAtom();
            }
            if (readFully < bytesPerLine()) {
                break;
            }
            encodeLineSuffix(outputStream);
        }
        encodeBufferSuffix(outputStream);
    }

    public String encode(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encode(new ByteArrayInputStream(bArr), byteArrayOutputStream);
            byteArrayOutputStream.toString("8859_1");
            throw null;
        } catch (Exception unused) {
            throw new Error("CharacterEncoder.encode internal error");
        }
    }

    public void encodeBuffer(InputStream inputStream, OutputStream outputStream) {
        int readFully;
        byte[] bArr = new byte[bytesPerLine()];
        encodeBufferPrefix(outputStream);
        do {
            readFully = readFully(inputStream, bArr);
            if (readFully == 0) {
                break;
            }
            encodeLinePrefix(outputStream, readFully);
            int i = 0;
            while (i < readFully) {
                if (bytesPerAtom() + i <= readFully) {
                    encodeAtom(outputStream, bArr, i, bytesPerAtom());
                } else {
                    encodeAtom(outputStream, bArr, i, readFully - i);
                }
                i += bytesPerAtom();
            }
            encodeLineSuffix(outputStream);
        } while (readFully >= bytesPerLine());
        encodeBufferSuffix(outputStream);
    }

    public String encodeBuffer(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            encodeBuffer(new ByteArrayInputStream(bArr), byteArrayOutputStream);
            byteArrayOutputStream.toString();
            throw null;
        } catch (Exception unused) {
            throw new Error("CharacterEncoder.encodeBuffer internal error");
        }
    }
}
