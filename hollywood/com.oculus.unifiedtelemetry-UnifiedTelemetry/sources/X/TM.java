package X;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public final class TM extends mm {
    public static final U1 A03 = new U1("closed");
    public static final Writer A04 = new C0415hO();
    public M4 A00 = U3.A00;
    public String A01;
    public final List<M4> A02 = new ArrayList();

    @Override // X.mm, java.io.Flushable
    public final void flush() throws IOException {
    }

    public TM() {
        super(A04);
    }

    private M4 A00() {
        List<M4> list = this.A02;
        return list.get(list.size() - 1);
    }

    private void A01(M4 m4) {
        if (this.A01 != null) {
            if (!(m4 instanceof U3) || this.A04) {
                ((U2) A00()).A00.put(this.A01, m4);
            }
            this.A01 = null;
        } else if (this.A02.isEmpty()) {
            this.A00 = m4;
        } else {
            M4 A002 = A00();
            if (A002 instanceof U5) {
                ((U5) A002).A00.add(m4);
                return;
            }
            throw new IllegalStateException();
        }
    }

    @Override // X.mm
    public final mm A07() throws IOException {
        U5 u5 = new U5();
        A01(u5);
        this.A02.add(u5);
        return this;
    }

    @Override // X.mm
    public final mm A08() throws IOException {
        U2 u2 = new U2();
        A01(u2);
        this.A02.add(u2);
        return this;
    }

    @Override // X.mm
    public final mm A09() throws IOException {
        List<M4> list = this.A02;
        if (list.isEmpty() || this.A01 != null || !(A00() instanceof U5)) {
            throw new IllegalStateException();
        }
        list.remove(list.size() - 1);
        return this;
    }

    @Override // X.mm
    public final mm A0A() throws IOException {
        List<M4> list = this.A02;
        if (list.isEmpty() || this.A01 != null || !(A00() instanceof U2)) {
            throw new IllegalStateException();
        }
        list.remove(list.size() - 1);
        return this;
    }

    @Override // X.mm
    public final mm A0B() throws IOException {
        A01(U3.A00);
        return this;
    }

    @Override // X.mm
    public final mm A0D(Boolean bool) throws IOException {
        if (bool == null) {
            A0B();
            return this;
        }
        A01(new U1(bool));
        return this;
    }

    @Override // X.mm
    public final mm A0E(Number number) throws IOException {
        if (number == null) {
            A0B();
            return this;
        }
        if (!this.A03) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                StringBuilder sb = new StringBuilder("JSON forbids NaN and infinities: ");
                sb.append(number);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        A01(new U1(number));
        return this;
    }

    @Override // X.mm
    public final mm A0F(String str) throws IOException {
        if (this.A02.isEmpty() || this.A01 != null || !(A00() instanceof U2)) {
            throw new IllegalStateException();
        }
        this.A01 = str;
        return this;
    }

    @Override // X.mm
    public final mm A0G(String str) throws IOException {
        if (str == null) {
            A0B();
            return this;
        }
        A01(new U1(str));
        return this;
    }

    public final M4 A0I() {
        List<M4> list = this.A02;
        if (list.isEmpty()) {
            return this.A00;
        }
        StringBuilder sb = new StringBuilder("Expected one JSON element but was ");
        sb.append(list);
        throw new IllegalStateException(sb.toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.mm
    public final void close() throws IOException {
        List<M4> list = this.A02;
        if (list.isEmpty()) {
            list.add(A03);
            return;
        }
        throw new IOException("Incomplete document");
    }

    @Override // X.mm
    public final mm A0C(long j) throws IOException {
        A01(new U1(Long.valueOf(j)));
        return this;
    }

    @Override // X.mm
    public final mm A0H(boolean z) throws IOException {
        A01(new U1(Boolean.valueOf(z)));
        return this;
    }
}
