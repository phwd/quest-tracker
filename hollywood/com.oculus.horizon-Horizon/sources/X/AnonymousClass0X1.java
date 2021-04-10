package X;

import com.oculus.defaultapps.DefaultAppsSetupManager;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0X1  reason: invalid class name */
public final class AnonymousClass0X1 extends C09120zR {
    public static final Reader A04 = new C09060zI();
    public static final Object A05 = new Object();
    public int A00 = 0;
    public Object[] A01 = new Object[32];
    public int[] A02 = new int[32];
    public String[] A03 = new String[32];

    @Override // java.io.Closeable, java.lang.AutoCloseable, X.C09120zR
    public final void close() throws IOException {
        this.A01 = new Object[]{A05};
        this.A00 = 1;
    }

    public AnonymousClass0X1(AbstractC08820ye r3) {
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

    public static void A02(AnonymousClass0X1 r7, Object obj) {
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

    @Override // X.C09120zR
    public final Integer A0G() throws IOException {
        int i = this.A00;
        if (i == 0) {
            return AnonymousClass007.A0J;
        }
        Object[] objArr = this.A01;
        Object obj = objArr[i - 1];
        if (obj instanceof Iterator) {
            boolean z = objArr[i - 2] instanceof C03100c7;
            Iterator it = (Iterator) obj;
            if (it.hasNext()) {
                if (z) {
                    return AnonymousClass007.A0E;
                }
                A02(this, it.next());
                return A0G();
            } else if (z) {
                return AnonymousClass007.A0D;
            } else {
                return AnonymousClass007.A01;
            }
        } else if (obj instanceof C03100c7) {
            return AnonymousClass007.A0C;
        } else {
            if (obj instanceof C03140cI) {
                return AnonymousClass007.A00;
            }
            if (obj instanceof C03090c6) {
                Object obj2 = ((C03090c6) obj).A00;
                if (obj2 instanceof String) {
                    return AnonymousClass007.A0F;
                }
                if (obj2 instanceof Boolean) {
                    return AnonymousClass007.A0H;
                }
                if (obj2 instanceof Number) {
                    return AnonymousClass007.A0G;
                }
            } else if (obj instanceof AnonymousClass0c8) {
                return AnonymousClass007.A0I;
            } else {
                if (obj == A05) {
                    throw new IllegalStateException("JsonReader is closed");
                }
            }
            throw new AssertionError();
        }
    }

    @Override // X.C09120zR
    public final String A0H() {
        StringBuilder sb = new StringBuilder(DefaultAppsSetupManager.EMPTY_PACKAGE_LIST_SENTINEL);
        int i = 0;
        while (i < this.A00) {
            Object[] objArr = this.A01;
            if (objArr[i] instanceof C03140cI) {
                i++;
                if (objArr[i] instanceof Iterator) {
                    sb.append('[');
                    sb.append(this.A02[i]);
                    sb.append(']');
                }
            } else if (objArr[i] instanceof C03100c7) {
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

    @Override // X.C09120zR
    public final String A0I() throws IOException {
        A01(this, AnonymousClass007.A0E);
        Map.Entry entry = (Map.Entry) ((Iterator) this.A01[this.A00 - 1]).next();
        String str = (String) entry.getKey();
        this.A03[this.A00 - 1] = str;
        A02(this, entry.getValue());
        return str;
    }

    @Override // X.C09120zR
    public final void A0L() throws IOException {
        A01(this, AnonymousClass007.A00);
        A02(this, ((C03140cI) this.A01[this.A00 - 1]).iterator());
        this.A02[this.A00 - 1] = 0;
    }

    @Override // X.C09120zR
    public final void A0M() throws IOException {
        A01(this, AnonymousClass007.A0C);
        A02(this, ((C03100c7) this.A01[this.A00 - 1]).A00.entrySet().iterator());
    }

    @Override // X.C09120zR
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

    @Override // X.C09120zR
    public final void A0O() throws IOException {
        A01(this, AnonymousClass007.A0D);
        A00();
        A00();
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A02;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // X.C09120zR
    public final void A0P() throws IOException {
        A01(this, AnonymousClass007.A0I);
        A00();
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A02;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
    }

    @Override // X.C09120zR
    public final boolean A0S() throws IOException {
        boolean parseBoolean;
        A01(this, AnonymousClass007.A0H);
        C03090c6 r2 = (C03090c6) A00();
        Object obj = r2.A00;
        if (obj instanceof Boolean) {
            parseBoolean = ((Boolean) obj).booleanValue();
        } else {
            parseBoolean = Boolean.parseBoolean(r2.A04());
        }
        int i = this.A00;
        if (i > 0) {
            int[] iArr = this.A02;
            int i2 = i - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return parseBoolean;
    }

    public static void A01(AnonymousClass0X1 r6, Integer num) throws IOException {
        if (r6.A0G() != num) {
            throw new IllegalStateException(AnonymousClass006.A09("Expected ", AnonymousClass0zT.A00(num), " but was ", AnonymousClass0zT.A00(r6.A0G()), AnonymousClass006.A05(" at path ", r6.A0H())));
        }
    }

    @Override // X.C09120zR
    public final double A0C() throws IOException {
        double parseDouble;
        Integer A0G = A0G();
        Integer num = AnonymousClass007.A0G;
        if (A0G == num || A0G == AnonymousClass007.A0F) {
            C03090c6 r1 = (C03090c6) this.A01[this.A00 - 1];
            if (r1.A00 instanceof Number) {
                parseDouble = r1.A03().doubleValue();
            } else {
                parseDouble = Double.parseDouble(r1.A04());
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
        throw new IllegalStateException(AnonymousClass006.A09("Expected ", AnonymousClass0zT.A00(num), " but was ", AnonymousClass0zT.A00(A0G), AnonymousClass006.A05(" at path ", A0H())));
    }

    @Override // X.C09120zR
    public final int A0D() throws IOException {
        int parseInt;
        Integer A0G = A0G();
        Integer num = AnonymousClass007.A0G;
        if (A0G == num || A0G == AnonymousClass007.A0F) {
            C03090c6 r1 = (C03090c6) this.A01[this.A00 - 1];
            if (r1.A00 instanceof Number) {
                parseInt = r1.A03().intValue();
            } else {
                parseInt = Integer.parseInt(r1.A04());
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
        throw new IllegalStateException(AnonymousClass006.A09("Expected ", AnonymousClass0zT.A00(num), " but was ", AnonymousClass0zT.A00(A0G), AnonymousClass006.A05(" at path ", A0H())));
    }

    @Override // X.C09120zR
    public final long A0F() throws IOException {
        long parseLong;
        Integer A0G = A0G();
        Integer num = AnonymousClass007.A0G;
        if (A0G == num || A0G == AnonymousClass007.A0F) {
            C03090c6 r1 = (C03090c6) this.A01[this.A00 - 1];
            if (r1.A00 instanceof Number) {
                parseLong = r1.A03().longValue();
            } else {
                parseLong = Long.parseLong(r1.A04());
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
        throw new IllegalStateException(AnonymousClass006.A09("Expected ", AnonymousClass0zT.A00(num), " but was ", AnonymousClass0zT.A00(A0G), AnonymousClass006.A05(" at path ", A0H())));
    }

    @Override // X.C09120zR
    public final String A0J() throws IOException {
        Integer A0G = A0G();
        Integer num = AnonymousClass007.A0F;
        if (A0G == num || A0G == AnonymousClass007.A0G) {
            String A042 = ((C03090c6) A00()).A04();
            int i = this.A00;
            if (i > 0) {
                int[] iArr = this.A02;
                int i2 = i - 1;
                iArr[i2] = iArr[i2] + 1;
            }
            return A042;
        }
        throw new IllegalStateException(AnonymousClass006.A09("Expected ", AnonymousClass0zT.A00(num), " but was ", AnonymousClass0zT.A00(A0G), AnonymousClass006.A05(" at path ", A0H())));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0017  */
    @Override // X.C09120zR
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0Q() throws java.io.IOException {
        /*
            r4 = this;
            java.lang.Integer r1 = r4.A0G()
            java.lang.Integer r0 = X.AnonymousClass007.A0E
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
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0X1.A0Q():void");
    }

    @Override // X.C09120zR
    public final boolean A0R() throws IOException {
        Integer A0G = A0G();
        if (A0G == AnonymousClass007.A0D || A0G == AnonymousClass007.A01) {
            return false;
        }
        return true;
    }

    @Override // X.C09120zR
    public final String toString() {
        return getClass().getSimpleName();
    }
}
