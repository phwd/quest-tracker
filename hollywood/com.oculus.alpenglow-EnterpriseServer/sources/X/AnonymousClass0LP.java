package X;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.0LP  reason: invalid class name */
public abstract class AnonymousClass0LP extends AbstractC02640aV {
    public AnonymousClass0aQ A00 = new AnonymousClass0aQ(0, null);
    public int A01;
    public AbstractC05940lg A02;
    public boolean A03;

    public abstract void A0Z(String str) throws IOException, C02650aW;

    @Override // X.AbstractC02640aV, java.io.Flushable
    public abstract void flush() throws IOException;

    @Override // X.AbstractC02640aV
    public final AbstractC02640aV A08() {
        if (super.A00 == null) {
            super.A00 = new C02600aN();
        }
        return this;
    }

    @Override // X.AbstractC02640aV
    public final void A09(Object obj) throws IOException, C05910ld {
        boolean z;
        long j;
        int i;
        short byteValue;
        if (obj == null) {
            A0D();
            return;
        }
        AbstractC05940lg r0 = this.A02;
        if (r0 != null) {
            r0.A02(this, obj);
        } else if (obj instanceof String) {
            A0S((String) obj);
        } else {
            if (obj instanceof Number) {
                Number number = (Number) obj;
                if (number instanceof Integer) {
                    i = number.intValue();
                } else {
                    if (number instanceof Long) {
                        j = number.longValue();
                    } else if (number instanceof Double) {
                        A0H(number.doubleValue());
                        return;
                    } else if (number instanceof Float) {
                        A0I(number.floatValue());
                        return;
                    } else {
                        if (number instanceof Short) {
                            byteValue = number.shortValue();
                        } else if (number instanceof Byte) {
                            byteValue = (short) number.byteValue();
                        } else if (number instanceof BigInteger) {
                            A0U((BigInteger) number);
                            return;
                        } else if (number instanceof BigDecimal) {
                            A0T((BigDecimal) number);
                            return;
                        } else if (number instanceof AtomicInteger) {
                            i = ((AtomicInteger) number).get();
                        } else if (number instanceof AtomicLong) {
                            j = ((AtomicLong) number).get();
                        }
                        A0V(byteValue);
                        return;
                    }
                    A0K(j);
                    return;
                }
                A0J(i);
                return;
            } else if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                A0L(C05840lV.A01, bArr, 0, bArr.length);
                return;
            } else {
                if (obj instanceof Boolean) {
                    z = ((Boolean) obj).booleanValue();
                } else if (obj instanceof AtomicBoolean) {
                    z = ((AtomicBoolean) obj).get();
                }
                A0W(z);
                return;
            }
            throw new IllegalStateException(AnonymousClass006.A07("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed ", obj.getClass().getName(), ")"));
        }
    }

    @Override // X.AbstractC02640aV
    public final void A0A(String str) throws IOException, C02650aW {
        A0Z("write raw value");
        A0R(str);
    }

    public final boolean A0a(EnumC05870lY r3) {
        if ((r3.getMask() & this.A01) != 0) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC02640aV, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public AnonymousClass0LP(int i, AbstractC05940lg r5) {
        this.A01 = i;
        this.A02 = r5;
        this.A03 = A0a(EnumC05870lY.WRITE_NUMBERS_AS_STRINGS);
    }

    @Override // X.AbstractC02640aV
    public void A0N(AbstractC05960li r2) throws IOException, C02650aW {
        A0P(r2.getValue());
    }

    @Override // X.AbstractC02640aV
    public void A0O(AbstractC05960li r2) throws IOException, C02650aW {
        A0S(r2.getValue());
    }
}
