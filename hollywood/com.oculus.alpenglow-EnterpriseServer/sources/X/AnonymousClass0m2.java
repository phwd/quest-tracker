package X;

import java.io.Writer;

/* renamed from: X.0m2  reason: invalid class name */
public final class AnonymousClass0m2 extends Writer {
    public final C06220mM A00;

    @Override // java.io.Closeable, java.io.Writer, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // java.io.Writer, java.io.Flushable
    public final void flush() {
    }

    public AnonymousClass0m2(AnonymousClass0mI r2) {
        this.A00 = new C06220mM(r2);
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public final Writer append(char c) {
        write(c);
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public final Writer append(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        this.A00.A04(charSequence2, 0, charSequence2.length());
        return this;
    }

    @Override // java.lang.Appendable, java.io.Writer, java.io.Writer
    public final Writer append(CharSequence charSequence, int i, int i2) {
        String charSequence2 = charSequence.subSequence(i, i2).toString();
        this.A00.A04(charSequence2, 0, charSequence2.length());
        return this;
    }

    @Override // java.io.Writer
    public final void write(int i) {
        C06220mM r4 = this.A00;
        char c = (char) i;
        if (r4.A01 >= 0) {
            C06220mM.A02(r4, 16);
        }
        r4.A03 = null;
        r4.A07 = null;
        char[] cArr = r4.A06;
        if (r4.A00 >= cArr.length) {
            C06220mM.A01(r4, 1);
            cArr = r4.A06;
        }
        int i2 = r4.A00;
        r4.A00 = i2 + 1;
        cArr[i2] = c;
    }

    @Override // java.io.Writer
    public final void write(String str) {
        this.A00.A04(str, 0, str.length());
    }

    @Override // java.io.Writer
    public final void write(String str, int i, int i2) {
        this.A00.A04(str, i, i2);
    }

    @Override // java.io.Writer
    public final void write(char[] cArr) {
        this.A00.A05(cArr, 0, cArr.length);
    }

    @Override // java.io.Writer
    public final void write(char[] cArr, int i, int i2) {
        this.A00.A05(cArr, i, i2);
    }
}
