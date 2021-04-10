package X;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

public final class TO extends lk {
    public static final Reader A04 = new Zq();
    public static final Object A05 = new Object();
    public int A00 = 0;
    public Object[] A01 = new Object[32];
    public int[] A02 = new int[32];
    public String[] A03 = new String[32];

    @Override // java.io.Closeable, X.lk, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01 = new Object[]{A05};
        this.A00 = 1;
    }

    public TO(M4 m4) {
        super(A04);
        A02(this, m4);
    }

    private Object A00() {
        Object[] objArr = this.A01;
        int i = this.A00 - 1;
        this.A00 = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    public static void A02(TO to, Object obj) {
        int i = to.A00;
        Object[] objArr = to.A01;
        if (i == objArr.length) {
            int i2 = i << 1;
            Object[] objArr2 = new Object[i2];
            int[] iArr = new int[i2];
            String[] strArr = new String[i2];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(to.A02, 0, iArr, 0, to.A00);
            System.arraycopy(to.A03, 0, strArr, 0, to.A00);
            to.A01 = objArr2;
            objArr = objArr2;
            to.A02 = iArr;
            to.A03 = strArr;
        }
        int i3 = to.A00;
        to.A00 = i3 + 1;
        objArr[i3] = obj;
    }

    @Override // X.lk
    public final Integer A0G() throws IOException {
        int i = this.A00;
        if (i == 0) {
            return AnonymousClass07.A09;
        }
        Object[] objArr = this.A01;
        Object obj = objArr[i - 1];
        if (obj instanceof Iterator) {
            boolean z = objArr[i - 2] instanceof U2;
            Iterator it = (Iterator) obj;
            if (it.hasNext()) {
                if (z) {
                    return AnonymousClass07.A04;
                }
                A02(this, it.next());
                return A0G();
            } else if (z) {
                return AnonymousClass07.A03;
            } else {
                return AnonymousClass07.A01;
            }
        } else if (obj instanceof U2) {
            return AnonymousClass07.A02;
        } else {
            if (obj instanceof U5) {
                return AnonymousClass07.A00;
            }
            if (obj instanceof U1) {
                Object obj2 = ((U1) obj).A00;
                if (obj2 instanceof String) {
                    return AnonymousClass07.A05;
                }
                if (obj2 instanceof Boolean) {
                    return AnonymousClass07.A07;
                }
                if (obj2 instanceof Number) {
                    return AnonymousClass07.A06;
                }
            } else if (obj instanceof U3) {
                return AnonymousClass07.A08;
            } else {
                if (obj == A05) {
                    throw new IllegalStateException("JsonReader is closed");
                }
            }
            throw new AssertionError();
        }
    }

    @Override // X.lk
    public final String A0H() {
        StringBuilder sb = new StringBuilder("$");
        int i = 0;
        while (i < this.A00) {
            Object[] objArr = this.A01;
            if (objArr[i] instanceof U5) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.A02[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof U2) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('.');
                    String[] strArr = this.A03;
                    if (strArr[i] != null) {
                        sb.append(strArr[i]);
                    }
                }
            }
            i++;
        }
        return sb.toString();
    }

    @Override // X.lk
    public final String A0I() throws IOException {
        A01(this, AnonymousClass07.A04);
        Map.Entry entry = (Map.Entry) ((Iterator) this.A01[this.A00 - 1]).next();
        String str = (String) entry.getKey();
        this.A03[this.A00 - 1] = str;
        A02(this, entry.getValue());
        return str;
    }

    @Override // X.lk
    public final void A0L() throws IOException {
        A01(this, AnonymousClass07.A00);
        A02(this, ((U5) this.A01[this.A00 - 1]).iterator());
        this.A02[this.A00 - 1] = 0;
    }

    @Override // X.lk
    public final void A0M() throws IOException {
        A01(this, AnonymousClass07.A02);
        A02(this, ((U2) this.A01[this.A00 - 1]).A00.entrySet().iterator());
    }

    @Override // X.lk
    public final void A0N() throws IOException {
        A01(this, AnonymousClass07.A01);
        A00();
        A00();
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A02;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // X.lk
    public final void A0O() throws IOException {
        A01(this, AnonymousClass07.A03);
        A00();
        A00();
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A02;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // X.lk
    public final void A0P() throws IOException {
        A01(this, AnonymousClass07.A08);
        A00();
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A02;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // X.lk
    public final boolean A0S() throws IOException {
        boolean parseBoolean;
        A01(this, AnonymousClass07.A07);
        U1 u1 = (U1) A00();
        Object obj = u1.A00;
        if (obj instanceof Boolean) {
            parseBoolean = ((Boolean) obj).booleanValue();
        } else {
            parseBoolean = Boolean.parseBoolean(u1.A03());
        }
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A02;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return parseBoolean;
    }

    public static void A01(TO to, Integer num) throws IOException {
        if (to.A0G() != num) {
            throw new IllegalStateException(AnonymousClass06.A07("Expected ", mj.A00(num), " but was ", mj.A00(to.A0G()), AnonymousClass06.A04(" at path ", to.A0H())));
        }
    }

    @Override // X.lk
    public final double A0C() throws IOException {
        double parseDouble;
        Integer A0G = A0G();
        Integer num = AnonymousClass07.A06;
        if (A0G == num || A0G == AnonymousClass07.A05) {
            U1 u1 = (U1) this.A01[this.A00 - 1];
            if (u1.A00 instanceof Number) {
                parseDouble = u1.A04().doubleValue();
            } else {
                parseDouble = Double.parseDouble(u1.A03());
            }
            if (this.A07 || (!Double.isNaN(parseDouble) && !Double.isInfinite(parseDouble))) {
                A00();
                int i = this.A00;
                if (i > 0) {
                    int[] iArr = this.A02;
                    int i2 = i - 1;
                    iArr[i2] = iArr[i2] + 1;
                }
                return parseDouble;
            }
            StringBuilder sb = new StringBuilder("JSON forbids NaN and infinities: ");
            sb.append(parseDouble);
            throw new NumberFormatException(sb.toString());
        }
        throw new IllegalStateException(AnonymousClass06.A07("Expected ", mj.A00(num), " but was ", mj.A00(A0G), AnonymousClass06.A04(" at path ", A0H())));
    }

    @Override // X.lk
    public final int A0D() throws IOException {
        int parseInt;
        Integer A0G = A0G();
        Integer num = AnonymousClass07.A06;
        if (A0G == num || A0G == AnonymousClass07.A05) {
            U1 u1 = (U1) this.A01[this.A00 - 1];
            if (u1.A00 instanceof Number) {
                parseInt = u1.A04().intValue();
            } else {
                parseInt = Integer.parseInt(u1.A03());
            }
            A00();
            int i = this.A00;
            if (i > 0) {
                int[] iArr = this.A02;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return parseInt;
        }
        throw new IllegalStateException(AnonymousClass06.A07("Expected ", mj.A00(num), " but was ", mj.A00(A0G), AnonymousClass06.A04(" at path ", A0H())));
    }

    @Override // X.lk
    public final long A0F() throws IOException {
        long parseLong;
        Integer A0G = A0G();
        Integer num = AnonymousClass07.A06;
        if (A0G == num || A0G == AnonymousClass07.A05) {
            U1 u1 = (U1) this.A01[this.A00 - 1];
            if (u1.A00 instanceof Number) {
                parseLong = u1.A04().longValue();
            } else {
                parseLong = Long.parseLong(u1.A03());
            }
            A00();
            int i = this.A00;
            if (i > 0) {
                int[] iArr = this.A02;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return parseLong;
        }
        throw new IllegalStateException(AnonymousClass06.A07("Expected ", mj.A00(num), " but was ", mj.A00(A0G), AnonymousClass06.A04(" at path ", A0H())));
    }

    @Override // X.lk
    public final String A0J() throws IOException {
        Integer A0G = A0G();
        Integer num = AnonymousClass07.A05;
        if (A0G == num || A0G == AnonymousClass07.A06) {
            String A032 = ((M4) A00()).A03();
            int i = this.A00;
            if (i > 0) {
                int[] iArr = this.A02;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return A032;
        }
        throw new IllegalStateException(AnonymousClass06.A07("Expected ", mj.A00(num), " but was ", mj.A00(A0G), AnonymousClass06.A04(" at path ", A0H())));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0017  */
    @Override // X.lk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0Q() throws java.io.IOException {
        /*
            r4 = this;
            java.lang.Integer r1 = r4.A0G()
            java.lang.Integer r0 = X.AnonymousClass07.A04
            java.lang.String r2 = "null"
            if (r1 != r0) goto L_0x0022
            r4.A0I()
            java.lang.String[] r1 = r4.A03
            int r3 = r4.A00
            int r0 = r3 + -2
        L_0x0013:
            r1[r0] = r2
        L_0x0015:
            if (r3 <= 0) goto L_0x0021
            int[] r2 = r4.A02
            int r1 = r3 + -1
            r0 = r2[r1]
            int r0 = r0 + 1
            r2[r1] = r0
        L_0x0021:
            return
        L_0x0022:
            r4.A00()
            int r3 = r4.A00
            if (r3 <= 0) goto L_0x0015
            java.lang.String[] r1 = r4.A03
            int r0 = r3 + -1
            goto L_0x0013
        */
        throw new UnsupportedOperationException("Method not decompiled: X.TO.A0Q():void");
    }

    @Override // X.lk
    public final boolean A0R() throws IOException {
        Integer A0G = A0G();
        if (A0G == AnonymousClass07.A03 || A0G == AnonymousClass07.A01) {
            return false;
        }
        return true;
    }

    @Override // X.lk
    public final String toString() {
        return getClass().getSimpleName();
    }
}
