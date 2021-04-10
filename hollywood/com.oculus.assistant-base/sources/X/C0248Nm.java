package X;

import java.io.Writer;

/* renamed from: X.Nm  reason: case insensitive filesystem */
public final class C0248Nm extends Writer {
    public final O0 A00;

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
    }

    public C0248Nm(C0258Nw nw) {
        this.A00 = new O0(nw);
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public final Writer append(char c) {
        write(c);
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public final Writer append(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        this.A00.A07(charSequence2, 0, charSequence2.length());
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public final Writer append(CharSequence charSequence, int i, int i2) {
        String charSequence2 = charSequence.subSequence(i, i2).toString();
        this.A00.A07(charSequence2, 0, charSequence2.length());
        return this;
    }

    @Override // java.io.Writer
    public final void write(int i) {
        O0 o0 = this.A00;
        char c = (char) i;
        if (o0.A02 >= 0) {
            O0.A02(o0, 16);
        }
        o0.A04 = null;
        o0.A09 = null;
        char[] cArr = o0.A07;
        if (o0.A00 >= cArr.length) {
            O0.A01(o0, 1);
            cArr = o0.A07;
        }
        int i2 = o0.A00;
        o0.A00 = i2 + 1;
        cArr[i2] = c;
    }

    @Override // java.io.Writer
    public final void write(String str) {
        this.A00.A07(str, 0, str.length());
    }

    @Override // java.io.Writer
    public final void write(String str, int i, int i2) {
        this.A00.A07(str, i, i2);
    }

    @Override // java.io.Writer
    public final void write(char[] cArr) {
        this.A00.A08(cArr, 0, cArr.length);
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) {
        this.A00.A08(cArr, i, i2);
    }
}
