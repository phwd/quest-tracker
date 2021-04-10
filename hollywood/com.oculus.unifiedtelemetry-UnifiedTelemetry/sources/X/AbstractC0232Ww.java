package X;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.Ww  reason: case insensitive filesystem */
public abstract class AbstractC0232Ww implements Closeable, q8 {
    public int A00;

    public int A0C() throws IOException, Wx {
        return A0W(0);
    }

    public abstract AbstractC0471q3 A0H();

    public String A0I() throws IOException, Wx {
        return A0e(null);
    }

    public abstract double A0J() throws IOException, Wx;

    public abstract float A0K() throws IOException, Wx;

    public abstract int A0L() throws IOException, Wx;

    public abstract long A0M() throws IOException, Wx;

    public abstract pw A0N();

    public abstract pw A0O();

    public abstract Integer A0P() throws IOException, Wx;

    public abstract Number A0Q() throws IOException, Wx;

    public abstract Object A0R() throws IOException, Wx;

    public abstract BigDecimal A0S() throws IOException, Wx;

    public abstract BigInteger A0T() throws IOException, Wx;

    public abstract int A0U() throws IOException, Wx;

    public abstract int A0V() throws IOException, Wx;

    public int A0W(int i) throws IOException, Wx {
        return i;
    }

    public long A0X(long j) throws IOException, Wx {
        return j;
    }

    public abstract AbstractC0232Ww A0Y() throws IOException, Wx;

    public abstract EnumC0470q2 A0Z();

    public abstract EnumC0470q2 A0a() throws IOException, Wx;

    public abstract EnumC0470q2 A0b() throws IOException, Wx;

    public abstract String A0c() throws IOException, Wx;

    public abstract String A0d() throws IOException, Wx;

    public abstract String A0e(String str) throws IOException, Wx;

    public abstract void A0f();

    public abstract boolean A0g();

    public abstract boolean A0h();

    public abstract byte[] A0i(C0465pq pqVar) throws IOException, Wx;

    public abstract char[] A0j() throws IOException, Wx;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    public long A0D() throws IOException, Wx {
        return A0X(0);
    }

    public boolean A0G(py pyVar) {
        if ((pyVar.getMask() & this.A00) != 0) {
            return true;
        }
        return false;
    }

    public byte A0B() throws IOException, Wx {
        int A0L = A0L();
        if (A0L >= -128 && A0L <= 255) {
            return (byte) A0L;
        }
        throw new Wx(AnonymousClass06.A05("Numeric value (", A0d(), ") out of range of Java byte"), A0N());
    }

    public short A0E() throws IOException, Wx {
        int A0L = A0L();
        if (A0L >= -32768 && A0L <= 32767) {
            return (short) A0L;
        }
        throw new Wx(AnonymousClass06.A05("Numeric value (", A0d(), ") out of range of Java short"), A0N());
    }

    public final boolean A0F() {
        if (A0Z() == EnumC0470q2.START_ARRAY) {
            return true;
        }
        return false;
    }

    public AbstractC0232Ww() {
    }

    public AbstractC0232Ww(int i) {
        this.A00 = 0;
    }
}
