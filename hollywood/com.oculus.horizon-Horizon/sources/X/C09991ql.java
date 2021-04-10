package X;

import java.io.IOException;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.1ql  reason: invalid class name and case insensitive filesystem */
public final class C09991ql extends AnonymousClass1rW {
    public int A00;
    public AnonymousClass1qa<AbstractC10321rv> A01;
    public final AbstractC10131rK A02;

    public final C10021qp A00() {
        if (AnonymousClass1qa.A02(this.A01)) {
            return new C10021qp(this.A01, this.A00);
        }
        throw new C10511tf();
    }

    @Override // java.io.OutputStream, java.io.Closeable, X.AnonymousClass1rW, java.lang.AutoCloseable
    public final void close() {
        AnonymousClass1qa<AbstractC10321rv> r0 = this.A01;
        if (r0 != null) {
            r0.close();
        }
        this.A01 = null;
        this.A00 = -1;
        super.close();
    }

    public C09991ql(AbstractC10131rK r3, int i) {
        AnonymousClass0KU.A01(Boolean.valueOf(i > 0));
        if (r3 != null) {
            this.A02 = r3;
            this.A00 = 0;
            this.A01 = AnonymousClass1qa.A01(r3.get(i), this.A02);
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
        }
        if (AnonymousClass1qa.A02(this.A01)) {
            int i3 = this.A00 + i2;
            if (AnonymousClass1qa.A02(this.A01)) {
                if (i3 > this.A01.A04().getSize()) {
                    AbstractC10131rK r4 = this.A02;
                    AbstractC10321rv r3 = (AbstractC10321rv) r4.get(i3);
                    this.A01.A04().copy(0, r3, 0, this.A00);
                    this.A01.close();
                    this.A01 = AnonymousClass1qa.A01(r3, r4);
                }
                this.A01.A04().write(this.A00, bArr, i, i2);
                this.A00 += i2;
                return;
            }
        }
        throw new C10511tf();
    }
}
