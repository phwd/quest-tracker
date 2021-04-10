package defpackage;

import java.io.Writer;

@Deprecated
/* renamed from: Wa0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1342Wa0 extends Writer {
    public final String F;
    public StringBuilder G = new StringBuilder(128);

    public C1342Wa0(String str) {
        this.F = str;
    }

    public final void Y() {
        if (this.G.length() > 0) {
            this.G.toString();
            StringBuilder sb = this.G;
            sb.delete(0, sb.length());
        }
    }

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public void close() {
        Y();
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() {
        Y();
    }

    @Override // java.io.Writer
    public void write(char[] cArr, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            char c = cArr[i + i3];
            if (c == '\n') {
                Y();
            } else {
                this.G.append(c);
            }
        }
    }
}
