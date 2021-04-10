package X;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.0gp  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC04100gp implements Closeable, AbstractC04870jo {
    public int A00;

    public int A0C() throws IOException, C04110gq {
        return A0X(0);
    }

    public boolean A0F() throws IOException, C04110gq {
        return A0i(false);
    }

    public abstract AnonymousClass0jj A0I();

    public String A0J() throws IOException, C04110gq {
        return A0f(null);
    }

    public abstract double A0K() throws IOException, C04110gq;

    public abstract float A0L() throws IOException, C04110gq;

    public abstract int A0M() throws IOException, C04110gq;

    public abstract long A0N() throws IOException, C04110gq;

    public abstract AnonymousClass0jc A0O();

    public abstract AnonymousClass0jc A0P();

    public abstract Integer A0Q() throws IOException, C04110gq;

    public abstract Number A0R() throws IOException, C04110gq;

    public abstract Object A0S() throws IOException, C04110gq;

    public abstract BigDecimal A0T() throws IOException, C04110gq;

    public abstract BigInteger A0U() throws IOException, C04110gq;

    public abstract int A0V() throws IOException, C04110gq;

    public abstract int A0W() throws IOException, C04110gq;

    public int A0X(int i) throws IOException, C04110gq {
        return i;
    }

    public long A0Y(long j) throws IOException, C04110gq {
        return j;
    }

    public abstract AbstractC04100gp A0Z() throws IOException, C04110gq;

    public abstract EnumC04820ji A0a();

    public abstract EnumC04820ji A0b() throws IOException, C04110gq;

    public abstract EnumC04820ji A0c() throws IOException, C04110gq;

    public abstract String A0d() throws IOException, C04110gq;

    public abstract String A0e() throws IOException, C04110gq;

    public abstract String A0f(String str) throws IOException, C04110gq;

    public abstract boolean A0g();

    public abstract boolean A0h();

    public boolean A0i(boolean z) throws IOException, C04110gq {
        return z;
    }

    public abstract byte[] A0j(C04780jW v) throws IOException, C04110gq;

    public abstract char[] A0k() throws IOException, C04110gq;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    public long A0D() throws IOException, C04110gq {
        return A0Y(0);
    }

    public boolean A0H(AnonymousClass0je r3) {
        if ((r3.getMask() & this.A00) != 0) {
            return true;
        }
        return false;
    }

    public byte A0B() throws IOException, C04110gq {
        int A0M = A0M();
        if (A0M >= -128 && A0M <= 255) {
            return (byte) A0M;
        }
        throw new C04110gq(AnonymousClass006.A07("Numeric value (", A0e(), ") out of range of Java byte"), A0O());
    }

    public short A0E() throws IOException, C04110gq {
        int A0M = A0M();
        if (A0M >= -32768 && A0M <= 32767) {
            return (short) A0M;
        }
        throw new C04110gq(AnonymousClass006.A07("Numeric value (", A0e(), ") out of range of Java short"), A0O());
    }

    public final boolean A0G() {
        if (A0a() == EnumC04820ji.START_ARRAY) {
            return true;
        }
        return false;
    }

    public AbstractC04100gp() {
    }

    public AbstractC04100gp(int i) {
        this.A00 = 0;
    }
}
