package X;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.0iQ  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02280iQ implements Closeable, AbstractC03700oK {
    public int A00;

    public int A0G() throws IOException, C02290iR {
        return A0f(0);
    }

    public boolean A0J() throws IOException, C02290iR {
        return A0q(false);
    }

    public int A0M(Writer writer) throws IOException {
        return -1;
    }

    public abstract AbstractC03650oF A0N();

    public Object A0O() {
        return null;
    }

    public String A0P() throws IOException, C02290iR {
        return A0n(null);
    }

    public abstract double A0R() throws IOException, C02290iR;

    public abstract float A0S() throws IOException, C02290iR;

    public abstract int A0T() throws IOException, C02290iR;

    public abstract long A0U() throws IOException, C02290iR;

    public abstract AnonymousClass0o8 A0V();

    public abstract AnonymousClass0o8 A0W();

    public abstract Integer A0X() throws IOException, C02290iR;

    public abstract Number A0Y() throws IOException, C02290iR;

    public abstract Object A0Z() throws IOException, C02290iR;

    public abstract BigDecimal A0a() throws IOException, C02290iR;

    public abstract BigInteger A0b() throws IOException, C02290iR;

    public double A0c(double d) throws IOException, C02290iR {
        return d;
    }

    public abstract int A0d() throws IOException, C02290iR;

    public abstract int A0e() throws IOException, C02290iR;

    public int A0f(int i) throws IOException, C02290iR {
        return i;
    }

    public long A0g(long j) throws IOException, C02290iR {
        return j;
    }

    public abstract AbstractC02280iQ A0h() throws IOException, C02290iR;

    public abstract EnumC03640oE A0i();

    public abstract EnumC03640oE A0j() throws IOException, C02290iR;

    public abstract EnumC03640oE A0k() throws IOException, C02290iR;

    public abstract String A0l() throws IOException, C02290iR;

    public abstract String A0m() throws IOException, C02290iR;

    public abstract String A0n(String str) throws IOException, C02290iR;

    public abstract boolean A0o();

    public abstract boolean A0p();

    public boolean A0q(boolean z) throws IOException, C02290iR {
        return z;
    }

    public abstract byte[] A0r(AnonymousClass0o2 v) throws IOException, C02290iR;

    public abstract char[] A0s() throws IOException, C02290iR;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    @Override // X.AbstractC03700oK
    public abstract C03690oJ version();

    public double A0F() throws IOException, C02290iR {
        return A0c(0.0d);
    }

    public long A0H() throws IOException, C02290iR {
        return A0g(0);
    }

    public boolean A0L(EnumC03610oA r3) {
        if ((r3.getMask() & this.A00) != 0) {
            return true;
        }
        return false;
    }

    public byte A0E() throws IOException, C02290iR {
        int A0T = A0T();
        if (A0T >= -128 && A0T <= 255) {
            return (byte) A0T;
        }
        throw new C02290iR(AnonymousClass006.A09("Numeric value (", A0m(), ") out of range of Java byte"), A0V());
    }

    public short A0I() throws IOException, C02290iR {
        int A0T = A0T();
        if (A0T >= -32768 && A0T <= 32767) {
            return (short) A0T;
        }
        throw new C02290iR(AnonymousClass006.A09("Numeric value (", A0m(), ") out of range of Java short"), A0V());
    }

    public final boolean A0K() {
        if (A0i() == EnumC03640oE.START_ARRAY) {
            return true;
        }
        return false;
    }

    public String A0Q() throws IOException, C02290iR {
        if (A0j() == EnumC03640oE.VALUE_STRING) {
            return A0m();
        }
        return null;
    }

    public AbstractC02280iQ() {
    }

    public AbstractC02280iQ(int i) {
        this.A00 = 0;
    }
}
