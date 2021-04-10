package X;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.0iS  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02300iS implements Closeable, AbstractC03700oK, Flushable {
    public AbstractC03660oG A00;

    public abstract AbstractC02300iS A08();

    public abstract AbstractC02300iS A09(AbstractC03650oF v);

    public abstract AbstractC03650oF A0A();

    public abstract void A0C(Object obj) throws IOException, C03620oC;

    public abstract void A0D(String str) throws IOException, C02310iT;

    public abstract void A0E() throws IOException, C02310iT;

    public abstract void A0F() throws IOException, C02310iT;

    public abstract void A0G() throws IOException, C02310iT;

    public abstract void A0H() throws IOException, C02310iT;

    public abstract void A0I() throws IOException, C02310iT;

    public abstract void A0J(char c) throws IOException, C02310iT;

    public abstract void A0K(double d) throws IOException, C02310iT;

    public abstract void A0L(float f) throws IOException, C02310iT;

    public abstract void A0M(int i) throws IOException, C02310iT;

    public abstract void A0N(long j) throws IOException, C02310iT;

    public abstract void A0O(AnonymousClass0o2 v, byte[] bArr, int i, int i2) throws IOException, C02310iT;

    public abstract void A0P(AbstractC03670oH v) throws IOException, C02310iT;

    public abstract void A0Q(AbstractC03670oH v) throws IOException, C02310iT;

    public abstract void A0R(String str) throws IOException, C02310iT;

    public abstract void A0S(String str) throws IOException, C02310iT, UnsupportedOperationException;

    public abstract void A0T(String str) throws IOException, C02310iT;

    public abstract void A0U(String str) throws IOException, C02310iT;

    public abstract void A0V(BigDecimal bigDecimal) throws IOException, C02310iT;

    public abstract void A0W(BigInteger bigInteger) throws IOException, C02310iT;

    public abstract void A0Y(boolean z) throws IOException, C02310iT;

    public abstract void A0Z(char[] cArr, int i, int i2) throws IOException, C02310iT;

    public abstract void A0a(char[] cArr, int i, int i2) throws IOException, C02310iT;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    @Override // java.io.Flushable
    public abstract void flush() throws IOException;

    @Override // X.AbstractC03700oK
    public abstract C03690oJ version();

    public void A0B(AbstractC03670oH r2) throws IOException, C02310iT {
        A0T(r2.getValue());
    }

    public void A0X(short s) throws IOException, C02310iT {
        A0M(s);
    }
}
