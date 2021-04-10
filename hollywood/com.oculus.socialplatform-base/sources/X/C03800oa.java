package X;

import java.io.Writer;

/* renamed from: X.0oa  reason: invalid class name and case insensitive filesystem */
public final class C03800oa extends Writer {
    public final C03970ou A00;

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
    }

    public C03800oa(C03930oq r2) {
        this.A00 = new C03970ou(r2);
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
        C03970ou r4 = this.A00;
        char c = (char) i;
        if (r4.A02 >= 0) {
            C03970ou.A02(r4, 16);
        }
        r4.A04 = null;
        r4.A09 = null;
        char[] cArr = r4.A07;
        if (r4.A00 >= cArr.length) {
            C03970ou.A01(r4, 1);
            cArr = r4.A07;
        }
        int i2 = r4.A00;
        r4.A00 = i2 + 1;
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
