package X;

import java.io.IOException;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.1iS  reason: invalid class name */
public final class AnonymousClass1iS extends AnonymousClass0JY {
    public int A00;
    public AbstractC00820Ju<AnonymousClass1iY> A01;
    public final AbstractC09461i1 A02;

    @Override // X.AnonymousClass0JY
    public final int A00() {
        return this.A00;
    }

    /* renamed from: A02 */
    public final AnonymousClass1iV A01() {
        if (AbstractC00820Ju.A04(this.A01)) {
            return new AnonymousClass1iV(this.A01, this.A00);
        }
        throw new C09521ij();
    }

    @Override // java.io.OutputStream, X.AnonymousClass0JY, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        AbstractC00820Ju.A03(this.A01);
        this.A01 = null;
        this.A00 = -1;
        super.close();
    }

    public AnonymousClass1iS(AbstractC09461i1 r3, int i) {
        C00740Ii.A01(Boolean.valueOf(i > 0));
        if (r3 != null) {
            this.A02 = r3;
            this.A00 = 0;
            this.A01 = AbstractC00820Ju.A01(r3.get(i), this.A02);
            return;
        }
        throw null;
    }

    @Override // java.io.OutputStream
    public final void write(int i) throws IOException {
        write(new byte[]{(byte) i});
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) throws IOException {
        if (i < 0 || i2 < 0 || i + i2 > bArr.length) {
            StringBuilder sb = new StringBuilder("length=");
            sb.append(bArr.length);
            sb.append("; regionStart=");
            sb.append(i);
            sb.append("; regionLength=");
            sb.append(i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        } else if (AbstractC00820Ju.A04(this.A01)) {
            int i3 = this.A00 + i2;
            if (AbstractC00820Ju.A04(this.A01)) {
                if (i3 > this.A01.A06().getSize()) {
                    AbstractC09461i1 r4 = this.A02;
                    AnonymousClass1iY r3 = (AnonymousClass1iY) r4.get(i3);
                    this.A01.A06().copy(0, r3, 0, this.A00);
                    this.A01.close();
                    this.A01 = AbstractC00820Ju.A01(r3, r4);
                }
                this.A01.A06().write(this.A00, bArr, i, i2);
                this.A00 += i2;
                return;
            }
            throw new C09521ij();
        } else {
            throw new C09521ij();
        }
    }
}
