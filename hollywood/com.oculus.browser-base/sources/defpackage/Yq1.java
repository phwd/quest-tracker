package defpackage;

import java.io.FilterWriter;
import java.io.Writer;

/* renamed from: Yq1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Yq1 extends FilterWriter {
    public static final byte[] F = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    public char G = 0;

    public Yq1(Writer writer) {
        super(writer);
    }

    public final void Y(byte b) {
        ((FilterWriter) this).out.write(37);
        Writer writer = ((FilterWriter) this).out;
        byte[] bArr = F;
        writer.write(bArr[(b >> 4) & 15]);
        ((FilterWriter) this).out.write(bArr[b & 15]);
    }

    @Override // java.io.FilterWriter, java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() {
        flush();
    }

    @Override // java.io.FilterWriter, java.io.Writer, java.io.Flushable
    public void flush() {
        if (this.G != 0) {
            Y((byte) 63);
            this.G = 0;
        }
        super.flush();
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(String str, int i, int i2) {
        synchronized (((FilterWriter) this).lock) {
            for (int i3 = i; i3 < i + i2; i3++) {
                write(str.charAt(i3));
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        synchronized (((FilterWriter) this).lock) {
            for (int i3 = i; i3 < i + i2; i3++) {
                write(cArr[i3]);
            }
        }
    }

    @Override // java.io.FilterWriter, java.io.Writer
    public void write(int i) {
        char c = (char) i;
        char c2 = this.G;
        boolean z = true;
        if (c2 != 0) {
            if ((63488 & c) == 55296) {
                if ((c & 1024) == 0) {
                    z = false;
                }
                if (z) {
                    int i2 = ((c2 << '\n') + c) - 56613888;
                    Y((byte) ((i2 >> 18) | 240));
                    Y((byte) (((i2 >> 12) & 63) | 128));
                    Y((byte) (((i2 >> 6) & 63) | 128));
                    Y((byte) ((i2 & 63) | 128));
                    this.G = 0;
                    return;
                }
            }
            Y((byte) 63);
            this.G = 0;
            write(c);
            return;
        }
        int i3 = 65535 & c;
        if (i3 < 128) {
            byte b = (byte) c;
            if ((b >= 97 && b <= 122) || ((b >= 65 && b <= 90) || ((b >= 48 && b <= 57) || b == 46 || b == 45 || b == 42 || b == 95))) {
                ((FilterWriter) this).out.write(b);
            } else if (b == 32) {
                ((FilterWriter) this).out.write(43);
            } else {
                Y(b);
            }
        } else if (i3 < 2048) {
            Y((byte) ((c >> 6) | 192));
            Y((byte) ((c & '?') | 128));
        } else {
            if ((c & 63488) == 55296) {
                if ((c & 1024) != 0) {
                    z = false;
                }
                if (z) {
                    this.G = c;
                } else {
                    Y((byte) 63);
                }
            } else {
                Y((byte) ((c >> '\f') | 224));
                Y((byte) (((c >> 6) & 63) | 128));
                Y((byte) ((c & '?') | 128));
            }
        }
    }
}
