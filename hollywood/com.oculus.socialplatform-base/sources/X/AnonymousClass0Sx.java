package X;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0Sx  reason: invalid class name */
public abstract class AnonymousClass0Sx extends AbstractC02300iS {
    public C02260iN A00 = new C02260iN(0, null);
    public int A01;
    public AbstractC03650oF A02;
    public boolean A03;

    public abstract void A0b(String str) throws IOException, C02310iT;

    @Override // X.AbstractC02300iS, java.io.Flushable
    public abstract void flush() throws IOException;

    @Override // X.AbstractC02300iS
    public final AbstractC02300iS A08() {
        if (super.A00 == null) {
            super.A00 = new C02240iK();
        }
        return this;
    }

    @Override // X.AbstractC02300iS
    public final void A0C(Object obj) throws IOException, C03620oC {
        boolean z;
        long j;
        int i;
        short byteValue;
        if (obj == null) {
            A0G();
            return;
        }
        AbstractC03650oF r0 = this.A02;
        if (r0 != null) {
            r0.A02(this, obj);
        } else if (obj instanceof String) {
            A0U((String) obj);
        } else {
            if (obj instanceof Number) {
                Number number = (Number) obj;
                if (number instanceof Integer) {
                    i = number.intValue();
                } else {
                    if (number instanceof Long) {
                        j = number.longValue();
                    } else if (number instanceof Double) {
                        A0K(number.doubleValue());
                        return;
                    } else if (number instanceof Float) {
                        A0L(number.floatValue());
                        return;
                    } else {
                        if (number instanceof Short) {
                            byteValue = number.shortValue();
                        } else if (number instanceof Byte) {
                            byteValue = (short) number.byteValue();
                        } else if (number instanceof BigInteger) {
                            A0W((BigInteger) number);
                            return;
                        } else if (number instanceof BigDecimal) {
                            A0V((BigDecimal) number);
                            return;
                        } else if (number instanceof AtomicInteger) {
                            i = ((AtomicInteger) number).get();
                        } else if (number instanceof AtomicLong) {
                            j = ((AtomicLong) number).get();
                        }
                        A0X(byteValue);
                        return;
                    }
                    A0N(j);
                    return;
                }
                A0M(i);
                return;
            } else if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                A0O(AnonymousClass0o3.A01, bArr, 0, bArr.length);
                return;
            } else {
                if (obj instanceof Boolean) {
                    z = ((Boolean) obj).booleanValue();
                } else if (obj instanceof AtomicBoolean) {
                    z = ((AtomicBoolean) obj).get();
                }
                A0Y(z);
                return;
            }
            throw new IllegalStateException(AnonymousClass006.A09("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed ", obj.getClass().getName(), ")"));
        }
    }

    @Override // X.AbstractC02300iS
    public final void A0D(String str) throws IOException, C02310iT {
        A0b("write raw value");
        A0T(str);
    }

    public final boolean A0c(AnonymousClass0o7 r3) {
        if ((r3.getMask() & this.A01) != 0) {
            return true;
        }
        return false;
    }

    public AnonymousClass0Sx(int i, AbstractC03650oF r5) {
        this.A01 = i;
        this.A02 = r5;
        this.A03 = A0c(AnonymousClass0o7.WRITE_NUMBERS_AS_STRINGS);
    }

    @Override // X.AbstractC02300iS
    public final AbstractC03650oF A0A() {
        return this.A02;
    }

    @Override // X.AbstractC02300iS
    public void A0P(AbstractC03670oH r2) throws IOException, C02310iT {
        A0R(r2.getValue());
    }

    @Override // X.AbstractC02300iS
    public void A0Q(AbstractC03670oH r2) throws IOException, C02310iT {
        A0U(r2.getValue());
    }

    @Override // java.io.Closeable, X.AbstractC02300iS, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // X.AbstractC02300iS, X.AbstractC03700oK
    public final C03690oJ version() {
        return C03980ov.A01(getClass());
    }

    @Override // X.AbstractC02300iS
    public final AbstractC02300iS A09(AbstractC03650oF r1) {
        this.A02 = r1;
        return this;
    }
}
