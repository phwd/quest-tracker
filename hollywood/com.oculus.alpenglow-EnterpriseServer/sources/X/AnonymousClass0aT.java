package X;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.0aT  reason: invalid class name */
public abstract class AnonymousClass0aT implements Closeable, AbstractC05990ll {
    public abstract double A03() throws IOException, C02630aU;

    public abstract float A04() throws IOException, C02630aU;

    public int A05() throws IOException, C02630aU {
        return A09(0);
    }

    public abstract int A06() throws IOException, C02630aU;

    public abstract int A07() throws IOException, C02630aU;

    public abstract int A08() throws IOException, C02630aU;

    public int A09(int i) throws IOException, C02630aU {
        return i;
    }

    public abstract long A0B() throws IOException, C02630aU;

    public long A0C(long j) throws IOException, C02630aU {
        return j;
    }

    public abstract C05880lZ A0D();

    public abstract C05880lZ A0E();

    public abstract AnonymousClass0aT A0F() throws IOException, C02630aU;

    public abstract EnumC05930lf A0G();

    public abstract EnumC05930lf A0H() throws IOException, C02630aU;

    public abstract AbstractC05940lg A0I();

    public abstract Integer A0J() throws IOException, C02630aU;

    public abstract Number A0K() throws IOException, C02630aU;

    public Object A0L() {
        return null;
    }

    public abstract Object A0M() throws IOException, C02630aU;

    public String A0N() throws IOException, C02630aU {
        return A0Q(null);
    }

    public abstract String A0O() throws IOException, C02630aU;

    public abstract String A0P() throws IOException, C02630aU;

    public abstract String A0Q(String str) throws IOException, C02630aU;

    public abstract BigDecimal A0R() throws IOException, C02630aU;

    public abstract BigInteger A0S() throws IOException, C02630aU;

    public abstract void A0U();

    public abstract boolean A0W();

    public abstract boolean A0X();

    public abstract byte[] A0Y(C05830lU v) throws IOException, C02630aU;

    public abstract char[] A0Z() throws IOException, C02630aU;

    public abstract EnumC05930lf A0a() throws IOException, C02630aU;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    public long A0A() throws IOException, C02630aU {
        return A0C(0);
    }

    public byte A02() throws IOException, C02630aU {
        int A06 = A06();
        if (A06 >= -128 && A06 <= 255) {
            return (byte) A06;
        }
        throw new C02630aU(AnonymousClass006.A07("Numeric value (", A0P(), ") out of range of Java byte"), A0D());
    }

    public short A0T() throws IOException, C02630aU {
        int A06 = A06();
        if (A06 >= -32768 && A06 <= 32767) {
            return (short) A06;
        }
        throw new C02630aU(AnonymousClass006.A07("Numeric value (", A0P(), ") out of range of Java short"), A0D());
    }

    public final boolean A0V() {
        if (A0G() == EnumC05930lf.START_ARRAY) {
            return true;
        }
        return false;
    }

    public AnonymousClass0aT() {
    }

    public AnonymousClass0aT(int i) {
    }
}
