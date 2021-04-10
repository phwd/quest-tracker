package com.facebook.acra.util;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.stetho.dumpapp.Framer;
import com.fasterxml.jackson.dataformat.smile.SmileConstants;
import com.google.common.base.Ascii;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/* access modifiers changed from: package-private */
@Nullsafe(Nullsafe.Mode.LOCAL)
public class UrlEncodingWriter extends FilterWriter {
    static final boolean THROW_ON_INVALID_INPUT = false;
    private static final int U16_SURROGATE_OFFSET = 56613888;
    private static final byte[] UPPER_CASE_DIGITS = {48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 51, SmileConstants.TOKEN_KEY_LONG_STRING, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    private static final byte UTF16_REPLACEMENT_BYTE = 63;
    private char mUtf16State = 0;

    private static int utf16GetSupplementary(char c, char c2) {
        return ((c << '\n') + c2) - U16_SURROGATE_OFFSET;
    }

    private static boolean utf16IsSurrogate(char c) {
        return (c & 63488) == 55296;
    }

    private static boolean utf16IsSurrogateLead(char c) {
        return (c & 1024) == 0;
    }

    private static boolean utf16IsSurrogateTrail(char c) {
        return (c & 1024) != 0;
    }

    public UrlEncodingWriter(Writer writer) {
        super(writer);
    }

    private void writeHexByte(byte b) throws IOException {
        this.out.write(37);
        this.out.write(UPPER_CASE_DIGITS[(b >> 4) & 15]);
        this.out.write(UPPER_CASE_DIGITS[b & Ascii.SI]);
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

    public void enforceCodepointBoundary() throws IOException {
        if (this.mUtf16State != 0) {
            writeHexByte(UTF16_REPLACEMENT_BYTE);
            resetState();
        }
    }

    private void resetState() {
        this.mUtf16State = 0;
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

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int i) throws IOException {
        char c = (char) i;
        if (this.mUtf16State == 0) {
            int i2 = 65535 & c;
            if (i2 < 128) {
                writeByte((byte) c);
            } else if (i2 < 2048) {
                writeHexByte((byte) ((c >> 6) | 192));
                writeHexByte((byte) ((c & '?') | 128));
            } else if (!utf16IsSurrogate(c)) {
                writeHexByte((byte) ((c >> '\f') | 224));
                writeHexByte((byte) (((c >> 6) & 63) | 128));
                writeHexByte((byte) ((c & '?') | 128));
            } else if (utf16IsSurrogateLead(c)) {
                this.mUtf16State = c;
            } else {
                writeHexByte(UTF16_REPLACEMENT_BYTE);
            }
        } else if (!utf16IsSurrogate(c) || !utf16IsSurrogateTrail(c)) {
            writeHexByte(UTF16_REPLACEMENT_BYTE);
            resetState();
            write(c);
        } else {
            int utf16GetSupplementary = utf16GetSupplementary(this.mUtf16State, c);
            writeHexByte((byte) ((utf16GetSupplementary >> 18) | 240));
            writeHexByte((byte) (((utf16GetSupplementary >> 12) & 63) | 128));
            writeHexByte((byte) (((utf16GetSupplementary >> 6) & 63) | 128));
            writeHexByte((byte) ((utf16GetSupplementary & 63) | 128));
            resetState();
        }
    }
}
