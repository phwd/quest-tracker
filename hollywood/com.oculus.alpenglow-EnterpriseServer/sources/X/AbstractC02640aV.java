package X;

import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/* renamed from: X.0aV  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02640aV implements Closeable, AbstractC05990ll, Flushable {
    public AbstractC05950lh A00;

    public abstract AbstractC02640aV A08();

    public abstract void A09(Object obj) throws IOException, C05910ld;

    public abstract void A0A(String str) throws IOException, C02650aW;

    public abstract void A0B() throws IOException, C02650aW;

    public abstract void A0C() throws IOException, C02650aW;

    public abstract void A0D() throws IOException, C02650aW;

    public abstract void A0E() throws IOException, C02650aW;

    public abstract void A0F() throws IOException, C02650aW;

    public abstract void A0G(char c) throws IOException, C02650aW;

    public abstract void A0H(double d) throws IOException, C02650aW;

    public abstract void A0I(float f) throws IOException, C02650aW;

    public abstract void A0J(int i) throws IOException, C02650aW;

    public abstract void A0K(long j) throws IOException, C02650aW;

    public abstract void A0L(C05830lU v, byte[] bArr, int i, int i2) throws IOException, C02650aW;

    public abstract void A0N(AbstractC05960li v) throws IOException, C02650aW;

    public abstract void A0O(AbstractC05960li v) throws IOException, C02650aW;

    public abstract void A0P(String str) throws IOException, C02650aW;

    public abstract void A0Q(String str) throws IOException, C02650aW, UnsupportedOperationException;

    public abstract void A0R(String str) throws IOException, C02650aW;

    public abstract void A0S(String str) throws IOException, C02650aW;

    public abstract void A0T(BigDecimal bigDecimal) throws IOException, C02650aW;

    public abstract void A0U(BigInteger bigInteger) throws IOException, C02650aW;

    public abstract void A0W(boolean z) throws IOException, C02650aW;

    public abstract void A0X(char[] cArr, int i, int i2) throws IOException, C02650aW;

    public abstract void A0Y(char[] cArr, int i, int i2) throws IOException, C02650aW;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    @Override // java.io.Flushable
    public abstract void flush() throws IOException;

    public void A0M(AbstractC05960li r2) throws IOException, C02650aW {
        A0R(r2.getValue());
    }

    public void A0V(short s) throws IOException, C02650aW {
        A0J(s);
    }
}
