package com.facebook.acra.util;

import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class UrlEncodingWriter extends FilterWriter {
    private static final byte[] UPPER_CASE_DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    private char mUtf16State = 0;

    private static boolean utf16IsSurrogate(char c) {
        return (c & 63488) == 55296;
    }

    public UrlEncodingWriter(Writer writer) {
        super(writer);
    }

    private void writeHexByte(byte b) throws IOException {
        this.out.write(37);
        this.out.write(UPPER_CASE_DIGITS[(b >> 4) & 15]);
        this.out.write(UPPER_CASE_DIGITS[b & 15]);
    }

    @Override // java.io.FilterWriter, java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() throws IOException {
        flush();
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

    @Override // java.io.FilterWriter, java.io.Writer
    public final void write(int i) throws IOException {
        char c = (char) i;
        boolean z = true;
        if (this.mUtf16State != 0) {
            if (utf16IsSurrogate(c)) {
                if ((c & 1024) == 0) {
                    z = false;
                }
                if (z) {
                    int i2 = ((this.mUtf16State << '\n') + c) - 56613888;
                    writeHexByte((byte) ((i2 >> 18) | 240));
                    writeHexByte((byte) (((i2 >> 12) & 63) | 128));
                    writeHexByte((byte) (((i2 >> 6) & 63) | 128));
                    writeHexByte((byte) ((i2 & 63) | 128));
                    this.mUtf16State = 0;
                    return;
                }
            }
            writeHexByte((byte) 63);
            this.mUtf16State = 0;
            write(c);
            return;
        }
        int i3 = 65535 & c;
        if (i3 < 128) {
            byte b = (byte) c;
            if ((b >= 97 && b <= 122) || ((b >= 65 && b <= 90) || ((b >= 48 && b <= 57) || b == 46 || b == 45 || b == 42 || b == 95))) {
                this.out.write(b);
            } else if (b == 32) {
                this.out.write(43);
            } else {
                writeHexByte(b);
            }
        } else if (i3 < 2048) {
            writeHexByte((byte) ((c >> 6) | 192));
            writeHexByte((byte) ((c & '?') | 128));
        } else if (utf16IsSurrogate(c)) {
            if ((c & 1024) != 0) {
                z = false;
            }
            if (z) {
                this.mUtf16State = c;
            } else {
                writeHexByte((byte) 63);
            }
        } else {
            writeHexByte((byte) ((c >> '\f') | 224));
            writeHexByte((byte) (((c >> 6) & 63) | 128));
            writeHexByte((byte) ((c & '?') | 128));
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Flushable
    public final void flush() throws IOException {
        if (this.mUtf16State != 0) {
            writeHexByte((byte) 63);
            this.mUtf16State = 0;
        }
        super.flush();
    }
}
