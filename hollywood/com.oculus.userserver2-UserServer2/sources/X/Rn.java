package X;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Rn implements Closeable, AbstractC00138d {
    public final int A00() throws IOException, C0124Ro {
        Number A06;
        AnonymousClass6z r2 = (AnonymousClass6z) this;
        if (((B3) r2).A00 == AnonymousClass9p.VALUE_NUMBER_INT) {
            C00239v r0 = r2.A03;
            A06 = (Number) r0.A02[r2.A00];
        } else {
            A06 = r2.A06();
        }
        return A06.intValue();
    }

    public abstract int A01() throws IOException, C0124Ro;

    public abstract int A02() throws IOException, C0124Ro;

    public final Rn A03() throws IOException, C0124Ro {
        B3 b3 = (B3) this;
        AnonymousClass9p r1 = b3.A00;
        if (r1 != AnonymousClass9p.START_OBJECT && r1 != AnonymousClass9p.START_ARRAY) {
            return b3;
        }
        int i = 1;
        while (true) {
            AnonymousClass9p A04 = b3.A04();
            if (A04 == null) {
                throw new RuntimeException("Internal error: this code path should never get executed");
            }
            int i2 = AnonymousClass8R.A00[A04.ordinal()];
            if (i2 == 1 || i2 == 2) {
                i++;
            } else if ((i2 == 3 || i2 == 4) && i - 1 == 0) {
                return b3;
            }
        }
    }

    public abstract AnonymousClass9p A04() throws IOException, C0124Ro;

    public final Number A06() throws IOException, C0124Ro {
        AnonymousClass6z r3 = (AnonymousClass6z) this;
        AnonymousClass9p r2 = ((B3) r3).A00;
        if (r2 == null || !r2.isNumeric()) {
            StringBuilder sb = new StringBuilder("Current token (");
            sb.append(r2);
            sb.append(") not numeric, can not use numeric value accessors");
            String obj = sb.toString();
            AnonymousClass9u r1 = r3.A01;
            if (r1 == null) {
                r1 = AnonymousClass9u.A01;
            }
            throw new C0124Ro(obj, r1);
        }
        C00239v r0 = r3.A03;
        Object obj2 = r0.A02[r3.A00];
        if (obj2 instanceof Number) {
            return (Number) obj2;
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.indexOf(46) >= 0) {
                return Double.valueOf(Double.parseDouble(str));
            }
            return Long.valueOf(Long.parseLong(str));
        } else if (obj2 == null) {
            return null;
        } else {
            throw new IllegalStateException(AnonymousClass06.A03("Internal error: entry should be a Number, but is of type ", obj2.getClass().getName()));
        }
    }

    public final Object A07() throws IOException, C0124Ro {
        AnonymousClass6z r2 = (AnonymousClass6z) this;
        if (((B3) r2).A00 != AnonymousClass9p.VALUE_EMBEDDED_OBJECT) {
            return null;
        }
        C00239v r0 = r2.A03;
        return r0.A02[r2.A00];
    }

    public abstract String A08() throws IOException, C0124Ro;

    public abstract String A09() throws IOException, C0124Ro;

    public abstract boolean A0B();

    public abstract char[] A0C() throws IOException, C0124Ro;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    public final Integer A05() throws IOException, C0124Ro {
        Number A06 = A06();
        if (!(A06 instanceof Integer)) {
            if (A06 instanceof Long) {
                return AnonymousClass07.A01;
            }
            if (A06 instanceof Double) {
                return AnonymousClass07.A04;
            }
            if (A06 instanceof BigDecimal) {
                return AnonymousClass07.A05;
            }
            if (A06 instanceof BigInteger) {
                return AnonymousClass07.A02;
            }
            if (A06 instanceof Float) {
                return AnonymousClass07.A03;
            }
            if (!(A06 instanceof Short)) {
                return null;
            }
        }
        return AnonymousClass07.A00;
    }

    public final BigDecimal A0A() throws IOException, C0124Ro {
        Number A06 = A06();
        if (A06 instanceof BigDecimal) {
            return (BigDecimal) A06;
        }
        switch (A05().intValue()) {
            case 0:
            case 1:
                return BigDecimal.valueOf(A06.longValue());
            case 2:
                return new BigDecimal((BigInteger) A06);
            default:
                return BigDecimal.valueOf(A06.doubleValue());
        }
    }

    public Rn() {
    }

    public Rn(int i) {
    }
}
