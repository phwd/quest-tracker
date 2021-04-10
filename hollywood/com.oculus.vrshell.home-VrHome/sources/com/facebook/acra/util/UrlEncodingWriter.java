package com.facebook.acra.util;

import com.adobe.xmp.options.PropertyOptions;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/* access modifiers changed from: package-private */
public class UrlEncodingWriter extends FilterWriter {
    private static final byte[] UPPER_CASE_DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    private char mUtf16State = 0;

    public UrlEncodingWriter(Writer out) {
        super(out);
    }

    private void writeHexByte(byte b) throws IOException {
        this.out.write(37);
        this.out.write(UPPER_CASE_DIGITS[(b >> 4) & 15]);
        this.out.write(UPPER_CASE_DIGITS[b & 15]);
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

    private static int utf16GetSupplementary(char lead, char trail) {
        return ((lead << '\n') + trail) - 56613888;
    }

    private static boolean utf16IsSurrogateLead(char c) {
        return (c & 1024) == 0;
    }

    private static boolean utf16IsSurrogateTrail(char c) {
        return (c & 1024) != 0;
    }

    private static boolean utf16IsSurrogate(char c) {
        return (63488 & c) == 55296;
    }

    public void enforceCodepointBoundary() throws IOException {
        if (this.mUtf16State != 0) {
            writeHexByte((byte) 63);
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
    public void write(String str, int offset, int count) throws IOException {
        synchronized (this.lock) {
            for (int i = offset; i < offset + count; i++) {
                write(str.charAt(i));
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] buf, int offset, int count) throws IOException {
        synchronized (this.lock) {
            for (int i = offset; i < offset + count; i++) {
                write(buf[i]);
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int oneChar) throws IOException {
        char c = (char) oneChar;
        if (this.mUtf16State != 0) {
            if (!utf16IsSurrogate(c) || !utf16IsSurrogateTrail(c)) {
                writeHexByte((byte) 63);
                resetState();
                write(c);
                return;
            }
            int codepoint = utf16GetSupplementary(this.mUtf16State, c);
            writeHexByte((byte) ((codepoint >> 18) | 240));
            writeHexByte((byte) (((codepoint >> 12) & 63) | PropertyOptions.HAS_TYPE));
            writeHexByte((byte) (((codepoint >> 6) & 63) | PropertyOptions.HAS_TYPE));
            writeHexByte((byte) ((codepoint & 63) | PropertyOptions.HAS_TYPE));
            resetState();
        } else if ((c & 65535) < 128) {
            writeByte((byte) c);
        } else if ((c & 65535) < 2048) {
            writeHexByte((byte) ((c >> 6) | 192));
            writeHexByte((byte) ((c & '?') | PropertyOptions.HAS_TYPE));
        } else if (!utf16IsSurrogate(c)) {
            writeHexByte((byte) ((c >> '\f') | 224));
            writeHexByte((byte) (((c >> 6) & 63) | PropertyOptions.HAS_TYPE));
            writeHexByte((byte) ((c & '?') | PropertyOptions.HAS_TYPE));
        } else if (utf16IsSurrogateLead(c)) {
            this.mUtf16State = c;
        } else {
            writeHexByte((byte) 63);
        }
    }
}
