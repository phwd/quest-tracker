package X;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0dw  reason: invalid class name and case insensitive filesystem */
public final class C01410dw extends AnonymousClass14I {
    public static final Reader A04 = new AnonymousClass149();
    public static final Object A05 = new Object();
    public int A00 = 0;
    public Object[] A01 = new Object[32];
    public int[] A02 = new int[32];
    public String[] A03 = new String[32];

    @Override // java.io.Closeable, X.AnonymousClass14I, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A01 = new Object[]{A05};
        this.A00 = 1;
    }

    public C01410dw(AnonymousClass13R r3) {
        super(A04);
        A02(this, r3);
    }

    private Object A00() {
        Object[] objArr = this.A01;
        int i = this.A00 - 1;
        this.A00 = i;
        Object obj = objArr[i];
        objArr[i] = null;
        return obj;
    }

    public static void A02(C01410dw r7, Object obj) {
        int i = r7.A00;
        Object[] objArr = r7.A01;
        if (i == objArr.length) {
            int i2 = i << 1;
            Object[] objArr2 = new Object[i2];
            int[] iArr = new int[i2];
            String[] strArr = new String[i2];
            System.arraycopy(objArr, 0, objArr2, 0, i);
            System.arraycopy(r7.A02, 0, iArr, 0, r7.A00);
            System.arraycopy(r7.A03, 0, strArr, 0, r7.A00);
            r7.A01 = objArr2;
            objArr = objArr2;
            r7.A02 = iArr;
            r7.A03 = strArr;
        }
        int i3 = r7.A00;
        r7.A00 = i3 + 1;
        objArr[i3] = obj;
    }

    @Override // X.AnonymousClass14I
    public final Integer A0G() throws IOException {
        int i = this.A00;
        if (i == 0) {
            return AnonymousClass007.A0A;
        }
        Object[] objArr = this.A01;
        Object obj = objArr[i - 1];
        if (obj instanceof Iterator) {
            boolean z = objArr[i - 2] instanceof AnonymousClass0eT;
            Iterator it = (Iterator) obj;
            if (it.hasNext()) {
                if (z) {
                    return AnonymousClass007.A05;
                }
                A02(this, it.next());
                return A0G();
            } else if (z) {
                return AnonymousClass007.A04;
            } else {
                return AnonymousClass007.A01;
            }
        } else if (obj instanceof AnonymousClass0eT) {
            return AnonymousClass007.A03;
        } else {
            if (obj instanceof AnonymousClass0eW) {
                return AnonymousClass007.A00;
            }
            if (obj instanceof AnonymousClass0eS) {
                Object obj2 = ((AnonymousClass0eS) obj).A00;
                if (obj2 instanceof String) {
                    return AnonymousClass007.A06;
                }
                if (obj2 instanceof Boolean) {
                    return AnonymousClass007.A08;
                }
                if (obj2 instanceof Number) {
                    return AnonymousClass007.A07;
                }
                throw new AssertionError();
            } else if (obj instanceof AnonymousClass0eU) {
                return AnonymousClass007.A09;
            } else {
                if (obj == A05) {
                    throw new IllegalStateException("JsonReader is closed");
                }
                throw new AssertionError();
            }
        }
    }

    @Override // X.AnonymousClass14I
    public final String A0H() {
        StringBuilder sb = new StringBuilder("$");
        int i = 0;
        while (i < this.A00) {
            Object[] objArr = this.A01;
            if (objArr[i] instanceof AnonymousClass0eW) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.A02[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof AnonymousClass0eT) {
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

    @Override // X.AnonymousClass14I
    public final String A0I() throws IOException {
        A01(this, AnonymousClass007.A05);
        Map.Entry entry = (Map.Entry) ((Iterator) this.A01[this.A00 - 1]).next();
        String str = (String) entry.getKey();
        this.A03[this.A00 - 1] = str;
        A02(this, entry.getValue());
        return str;
    }

    @Override // X.AnonymousClass14I
    public final void A0L() throws IOException {
        A01(this, AnonymousClass007.A00);
        A02(this, ((AnonymousClass0eW) this.A01[this.A00 - 1]).iterator());
        this.A02[this.A00 - 1] = 0;
    }

    @Override // X.AnonymousClass14I
    public final void A0M() throws IOException {
        A01(this, AnonymousClass007.A03);
        A02(this, ((AnonymousClass0eT) this.A01[this.A00 - 1]).A00.entrySet().iterator());
    }

    @Override // X.AnonymousClass14I
    public final void A0N() throws IOException {
        A01(this, AnonymousClass007.A01);
        A00();
        A00();
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A02;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // X.AnonymousClass14I
    public final void A0O() throws IOException {
        A01(this, AnonymousClass007.A04);
        A00();
        A00();
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A02;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // X.AnonymousClass14I
    public final void A0P() throws IOException {
        A01(this, AnonymousClass007.A09);
        A00();
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A02;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // X.AnonymousClass14I
    public final boolean A0S() throws IOException {
        boolean parseBoolean;
        A01(this, AnonymousClass007.A08);
        AnonymousClass0eS r2 = (AnonymousClass0eS) A00();
        Object obj = r2.A00;
        if (obj instanceof Boolean) {
            parseBoolean = ((Boolean) obj).booleanValue();
        } else {
            parseBoolean = Boolean.parseBoolean(r2.A03());
        }
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A02;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return parseBoolean;
    }

    public static void A01(C01410dw r6, Integer num) throws IOException {
        if (r6.A0G() != num) {
            throw new IllegalStateException(AnonymousClass006.A0C("Expected ", AnonymousClass14K.A00(num), " but was ", AnonymousClass14K.A00(r6.A0G()), AnonymousClass006.A07(" at path ", r6.A0H())));
        }
    }

    @Override // X.AnonymousClass14I
    public final double A0C() throws IOException {
        double parseDouble;
        Integer A0G = A0G();
        Integer num = AnonymousClass007.A07;
        if (A0G == num || A0G == AnonymousClass007.A06) {
            AnonymousClass0eS r1 = (AnonymousClass0eS) this.A01[this.A00 - 1];
            if (r1.A00 instanceof Number) {
                parseDouble = r1.A02().doubleValue();
            } else {
                parseDouble = Double.parseDouble(r1.A03());
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
            StringBuilder sb = new StringBuilder();
            sb.append("JSON forbids NaN and infinities: ");
            sb.append(parseDouble);
            throw new NumberFormatException(sb.toString());
        }
        throw new IllegalStateException(AnonymousClass006.A0C("Expected ", AnonymousClass14K.A00(num), " but was ", AnonymousClass14K.A00(A0G), AnonymousClass006.A07(" at path ", A0H())));
    }

    @Override // X.AnonymousClass14I
    public final int A0D() throws IOException {
        int parseInt;
        Integer A0G = A0G();
        Integer num = AnonymousClass007.A07;
        if (A0G == num || A0G == AnonymousClass007.A06) {
            AnonymousClass0eS r1 = (AnonymousClass0eS) this.A01[this.A00 - 1];
            if (r1.A00 instanceof Number) {
                parseInt = r1.A02().intValue();
            } else {
                parseInt = Integer.parseInt(r1.A03());
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
        throw new IllegalStateException(AnonymousClass006.A0C("Expected ", AnonymousClass14K.A00(num), " but was ", AnonymousClass14K.A00(A0G), AnonymousClass006.A07(" at path ", A0H())));
    }

    @Override // X.AnonymousClass14I
    public final long A0F() throws IOException {
        long parseLong;
        Integer A0G = A0G();
        Integer num = AnonymousClass007.A07;
        if (A0G == num || A0G == AnonymousClass007.A06) {
            AnonymousClass0eS r1 = (AnonymousClass0eS) this.A01[this.A00 - 1];
            if (r1.A00 instanceof Number) {
                parseLong = r1.A02().longValue();
            } else {
                parseLong = Long.parseLong(r1.A03());
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
        throw new IllegalStateException(AnonymousClass006.A0C("Expected ", AnonymousClass14K.A00(num), " but was ", AnonymousClass14K.A00(A0G), AnonymousClass006.A07(" at path ", A0H())));
    }

    @Override // X.AnonymousClass14I
    public final String A0J() throws IOException {
        Integer A0G = A0G();
        Integer num = AnonymousClass007.A06;
        if (A0G == num || A0G == AnonymousClass007.A07) {
            String A032 = ((AnonymousClass0eS) A00()).A03();
            int i = this.A00;
            if (i > 0) {
                int[] iArr = this.A02;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return A032;
        }
        throw new IllegalStateException(AnonymousClass006.A0C("Expected ", AnonymousClass14K.A00(num), " but was ", AnonymousClass14K.A00(A0G), AnonymousClass006.A07(" at path ", A0H())));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0017  */
    @Override // X.AnonymousClass14I
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0Q() throws java.io.IOException {
        /*
            r4 = this;
            java.lang.Integer r1 = r4.A0G()
            java.lang.Integer r0 = X.AnonymousClass007.A05
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
        throw new UnsupportedOperationException("Method not decompiled: X.C01410dw.A0Q():void");
    }

    @Override // X.AnonymousClass14I
    public final boolean A0R() throws IOException {
        Integer A0G = A0G();
        if (A0G == AnonymousClass007.A04 || A0G == AnonymousClass007.A01) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass14I
    public final String toString() {
        return getClass().getSimpleName();
    }
}
