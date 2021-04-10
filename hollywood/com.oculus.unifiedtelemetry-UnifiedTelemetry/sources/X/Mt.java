package X;

import com.facebook.acra.util.UrlEncodingWriter;
import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class Mt extends FilterWriter {
    public static final byte[] A01 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    public char A00 = 0;

    private void A00(byte b) throws IOException {
        this.out.write(37);
        Writer writer = this.out;
        byte[] bArr = A01;
        writer.write(bArr[(b >> 4) & 15]);
        this.out.write(bArr[b & 15]);
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Flushable
    public final void flush() throws IOException {
        if (this.A00 != 0) {
            A00(UrlEncodingWriter.UTF16_REPLACEMENT_BYTE);
            this.A00 = 0;
        }
        super.flush();
    }

    public Mt(Writer writer) {
        super(writer);
    }

    @Override // java.io.FilterWriter, java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() throws IOException {
        flush();
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public final void write(int i) throws IOException {
        byte b;
        int i2;
        char c = (char) i;
        char c2 = this.A00;
        if (c2 == 0) {
            int i3 = 65535 & c;
            if (i3 < 128) {
                b = (byte) c;
                if ((b >= 97 && b <= 122) || ((b >= 65 && b <= 90) || ((b >= 48 && b <= 57) || b == 46 || b == 45 || b == 42 || b == 95))) {
                    this.out.write(b);
                    return;
                } else if (b == 32) {
                    this.out.write(43);
                    return;
                }
            } else {
                if (i3 < 2048) {
                    i2 = (c >> 6) | 192;
                } else if ((c & 63488) != 55296) {
                    A00((byte) ((c >> '\f') | 224));
                    i2 = ((c >> 6) & 63) | 128;
                } else if ((c & 1024) == 0) {
                    this.A00 = c;
                    return;
                } else {
                    A00(UrlEncodingWriter.UTF16_REPLACEMENT_BYTE);
                    return;
                }
                A00((byte) i2);
                b = (byte) ((c & '?') | 128);
            }
            A00(b);
        } else if ((c & 63488) != 55296 || (c & 1024) == 0) {
            A00(UrlEncodingWriter.UTF16_REPLACEMENT_BYTE);
            this.A00 = 0;
            write(c);
        } else {
            int i4 = ((c2 << '\n') + c) - UrlEncodingWriter.U16_SURROGATE_OFFSET;
            A00((byte) ((i4 >> 18) | 240));
            A00((byte) (((i4 >> 12) & 63) | 128));
            A00((byte) (((i4 >> 6) & 63) | 128));
            A00((byte) ((i4 & 63) | 128));
            this.A00 = 0;
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public final void write(String str, int i, int i2) throws IOException {
        synchronized (this.lock) {
            for (int i3 = i; i3 < i + i2; i3++) {
                write(str.charAt(i3));
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public final void write(char[] cArr, int i, int i2) throws IOException {
        synchronized (this.lock) {
            for (int i3 = i; i3 < i + i2; i3++) {
                write(cArr[i3]);
            }
        }
    }
}
