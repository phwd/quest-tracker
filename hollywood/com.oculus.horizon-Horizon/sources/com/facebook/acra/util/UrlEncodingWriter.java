package com.facebook.acra.util;

import com.facebook.FacebookSdk;
import com.facebook.infer.annotation.Nullsafe;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class UrlEncodingWriter extends FilterWriter {
    public static final boolean THROW_ON_INVALID_INPUT = false;
    public static final int U16_SURROGATE_OFFSET = 56613888;
    public static final byte[] UPPER_CASE_DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    public static final byte UTF16_REPLACEMENT_BYTE = 63;
    public char mUtf16State = 0;

    private void resetState() {
        this.mUtf16State = 0;
    }

    public static int utf16GetSupplementary(char c, char c2) {
        return ((c << '\n') + c2) - U16_SURROGATE_OFFSET;
    }

    public static boolean utf16IsSurrogate(char c) {
        return (c & 63488) == 55296;
    }

    public static boolean utf16IsSurrogateLead(char c) {
        return (c & 1024) == 0;
    }

    public static boolean utf16IsSurrogateTrail(char c) {
        return (c & 1024) != 0;
    }

    private void writeByte(byte b) throws IOException {
        if ((b >= 97 && b <= 122) || ((b >= 65 && b <= 90) || ((b >= 48 && b <= 57) || b == 46 || b == 45 || b == 42 || b == 95))) {
            this.out.write(b);
        } else if (b == 32) {
            this.out.write(43);
        } else {
            writeHexByte(b);
        }
    }

    private void writeHexByte(byte b) throws IOException {
        this.out.write(37);
        Writer writer = this.out;
        byte[] bArr = UPPER_CASE_DIGITS;
        writer.write(bArr[(b >> 4) & 15]);
        this.out.write(bArr[b & 15]);
    }

    public void enforceCodepointBoundary() throws IOException {
        if (this.mUtf16State != 0) {
            writeHexByte(UTF16_REPLACEMENT_BYTE);
            this.mUtf16State = 0;
        }
    }

    public UrlEncodingWriter(Writer writer) {
        super(writer);
    }

    @Override // java.io.FilterWriter, java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() throws IOException {
        flush();
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        enforceCodepointBoundary();
        super.flush();
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int i) throws IOException {
        int i2;
        char c = (char) i;
        char c2 = this.mUtf16State;
        if (c2 == 0) {
            int i3 = 65535 & c;
            if (i3 < 128) {
                writeByte((byte) c);
                return;
            }
            if (i3 < 2048) {
                i2 = (c >> 6) | 192;
            } else if (!utf16IsSurrogate(c)) {
                writeHexByte((byte) ((c >> '\f') | 224));
                i2 = ((c >> 6) & 63) | FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE;
            } else if (utf16IsSurrogateLead(c)) {
                this.mUtf16State = c;
                return;
            } else {
                writeHexByte(UTF16_REPLACEMENT_BYTE);
                return;
            }
            writeHexByte((byte) i2);
            writeHexByte((byte) ((c & '?') | FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE));
        } else if (!utf16IsSurrogate(c) || !utf16IsSurrogateTrail(c)) {
            writeHexByte(UTF16_REPLACEMENT_BYTE);
            this.mUtf16State = 0;
            write(c);
        } else {
            int utf16GetSupplementary = utf16GetSupplementary(c2, c);
            writeHexByte((byte) ((utf16GetSupplementary >> 18) | 240));
            writeHexByte((byte) (((utf16GetSupplementary >> 12) & 63) | FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE));
            writeHexByte((byte) (((utf16GetSupplementary >> 6) & 63) | FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE));
            writeHexByte((byte) ((utf16GetSupplementary & 63) | FacebookSdk.DEFAULT_MAXIMUM_POOL_SIZE));
            this.mUtf16State = 0;
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String str, int i, int i2) throws IOException {
        synchronized (this.lock) {
            for (int i3 = i; i3 < i + i2; i3++) {
                write(str.charAt(i3));
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cArr, int i, int i2) throws IOException {
        synchronized (this.lock) {
            for (int i3 = i; i3 < i + i2; i3++) {
                write(cArr[i3]);
            }
        }
    }
}
