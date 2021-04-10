package X;

import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Arrays;

/* renamed from: X.2am  reason: invalid class name and case insensitive filesystem */
public final class C15022am {
    public static int A0F = 1000;
    public static long A0G;
    public static AnonymousClass2aY A0H;
    public static boolean A0I;
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public boolean A04;
    public boolean A05;
    public AnonymousClass2ap[] A06;
    public int A07;
    public int A08;
    public int A09;
    public AnonymousClass2b7 A0A;
    public AnonymousClass2b7 A0B;
    public C15032at[] A0C;
    public boolean[] A0D;
    public final AnonymousClass2bA A0E;

    private final void A05(AnonymousClass2b7 r10) {
        for (int i = 0; i < this.A01; i++) {
            this.A0D[i] = false;
        }
        int i2 = 0;
        while (true) {
            i2++;
            if (i2 < (this.A01 << 1)) {
                C15032at A4D = r10.A4D();
                if (A4D != null) {
                    this.A0D[A4D.A04] = true;
                }
                boolean[] zArr = this.A0D;
                C15032at A4e = r10.A4e(this, zArr);
                if (A4e != null) {
                    int i3 = A4e.A04;
                    if (!zArr[i3]) {
                        zArr[i3] = true;
                        int i4 = -1;
                        float f = Float.MAX_VALUE;
                        for (int i5 = 0; i5 < this.A02; i5++) {
                            AnonymousClass2ap r1 = this.A06[i5];
                            if (r1.A02.A07 != AnonymousClass007.A00 && !r1.A04 && r1.A01.A2G(A4e)) {
                                float A3I = r1.A01.A3I(A4e);
                                if (A3I < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                                    float f2 = (-r1.A00) / A3I;
                                    if (f2 < f) {
                                        i4 = i5;
                                        f = f2;
                                    }
                                }
                            }
                        }
                        if (i4 > -1) {
                            AnonymousClass2ap r12 = this.A06[i4];
                            r12.A02.A03 = -1;
                            r12.A05(A4e);
                            C15032at r0 = r12.A02;
                            r0.A03 = i4;
                            r0.A04(this, r12);
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public final C15032at A08(Object obj) {
        C15032at r3 = null;
        if (obj != null) {
            if (this.A01 + 1 >= this.A00) {
                A03();
            }
            if (obj instanceof C14982ai) {
                C14982ai r5 = (C14982ai) obj;
                r3 = r5.A03;
                if (r3 == null) {
                    r5.A03();
                    r3 = r5.A03;
                }
                int i = r3.A04;
                if (i == -1 || i > this.A03 || this.A0E.A03[i] == null) {
                    if (i != -1) {
                        r3.A00();
                    }
                    int i2 = this.A03 + 1;
                    this.A03 = i2;
                    this.A01++;
                    r3.A04 = i2;
                    r3.A07 = AnonymousClass007.A00;
                    this.A0E.A03[i2] = r3;
                }
            }
        }
        return r3;
    }

    public final void A0A() {
        AnonymousClass2bA r6;
        C15032at[] r3;
        int i;
        int i2 = 0;
        while (true) {
            r6 = this.A0E;
            r3 = r6.A03;
            if (i2 >= r3.length) {
                break;
            }
            C15032at r0 = r3[i2];
            if (r0 != null) {
                r0.A00();
            }
            i2++;
        }
        r6.A01.A90(this.A0C, this.A09);
        this.A09 = 0;
        Arrays.fill(r3, (Object) null);
        this.A03 = 0;
        this.A0A.clear();
        this.A01 = 1;
        int i3 = 0;
        while (true) {
            i = this.A02;
            if (i3 >= i) {
                break;
            }
            i3++;
        }
        for (int i4 = 0; i4 < i; i4++) {
            AnonymousClass2ap[] r2 = this.A06;
            AnonymousClass2ap r1 = r2[i4];
            if (r1 != null) {
                r6.A02.A8z(r1);
            }
            r2[i4] = null;
        }
        this.A02 = 0;
        this.A0B = new AnonymousClass2ap(r6);
    }

    /* JADX WARNING: Removed duplicated region for block: B:119:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x016b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A0B(X.AnonymousClass2ap r20) {
        /*
        // Method dump skipped, instructions count: 388
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C15022am.A0B(X.2ap):void");
    }

    public final void A0C(C15032at r5, int i) {
        AnonymousClass2ap r2;
        AnonymousClass2b5 r1;
        float f;
        int i2 = r5.A03;
        if (i2 == -1) {
            r5.A03(this, (float) i);
            for (int i3 = 0; i3 < this.A03 + 1; i3++) {
            }
            return;
        }
        if (i2 != -1) {
            AnonymousClass2ap r12 = this.A06[i2];
            if (!r12.A04) {
                if (r12.A01.A3e() == 0) {
                    r12.A04 = true;
                } else {
                    r2 = A06();
                    if (i < 0) {
                        r2.A00 = (float) (-i);
                        r1 = r2.A01;
                        f = 1.0f;
                    } else {
                        r2.A00 = (float) i;
                        r1 = r2.A01;
                        f = -1.0f;
                    }
                    r1.A8m(r5, f);
                }
            }
            r12.A00 = (float) i;
            return;
        }
        r2 = A06();
        r2.A02 = r5;
        float f2 = (float) i;
        r5.A02 = f2;
        r2.A00 = f2;
        r2.A04 = true;
        A0B(r2);
    }

    public static final int A00(Object obj) {
        C15032at r0 = ((C14982ai) obj).A03;
        if (r0 != null) {
            return (int) (r0.A02 + 0.5f);
        }
        return 0;
    }

    public static final C15032at A01(C15022am r3) {
        if (r3.A01 + 1 >= r3.A00) {
            r3.A03();
        }
        C15032at A022 = r3.A02(AnonymousClass007.A03);
        int i = r3.A03 + 1;
        r3.A03 = i;
        r3.A01++;
        A022.A04 = i;
        r3.A0E.A03[i] = A022;
        return A022;
    }

    private C15032at A02(Integer num) {
        C15032at A19 = this.A0E.A01.A19();
        if (A19 == null) {
            A19 = new C15032at(num);
        } else {
            A19.A00();
        }
        A19.A07 = num;
        int i = this.A09;
        int i2 = A0F;
        if (i >= i2) {
            int i3 = i2 << 1;
            A0F = i3;
            this.A0C = (C15032at[]) Arrays.copyOf(this.A0C, i3);
        }
        C15032at[] r2 = this.A0C;
        int i4 = this.A09;
        this.A09 = i4 + 1;
        r2[i4] = A19;
        return A19;
    }

    private void A03() {
        int i = this.A07 << 1;
        this.A07 = i;
        this.A06 = (AnonymousClass2ap[]) Arrays.copyOf(this.A06, i);
        AnonymousClass2bA r2 = this.A0E;
        r2.A03 = (C15032at[]) Arrays.copyOf(r2.A03, this.A07);
        int i2 = this.A07;
        this.A0D = new boolean[i2];
        this.A00 = i2;
        this.A08 = i2;
    }

    private final void A04(AnonymousClass2ap r8) {
        int i;
        if (r8.A04) {
            r8.A02.A03(this, r8.A00);
        } else {
            AnonymousClass2ap[] r1 = this.A06;
            int i2 = this.A02;
            r1[i2] = r8;
            C15032at r12 = r8.A02;
            r12.A03 = i2;
            this.A02 = i2 + 1;
            r12.A04(this, r8);
        }
        if (this.A04) {
            int i3 = 0;
            while (i3 < this.A02) {
                if (this.A06[i3] == null) {
                    System.out.println("WTF");
                }
                AnonymousClass2ap[] r6 = this.A06;
                if (r6[i3] != null && r6[i3].A04) {
                    AnonymousClass2ap r2 = r6[i3];
                    r2.A02.A03(this, r2.A00);
                    this.A0E.A02.A8z(r2);
                    r6[i3] = null;
                    int i4 = i3 + 1;
                    int i5 = i4;
                    while (true) {
                        i = this.A02;
                        if (i4 >= i) {
                            break;
                        }
                        int i6 = i4 - 1;
                        r6[i6] = r6[i4];
                        if (r6[i6].A02.A03 == i4) {
                            r6[i6].A02.A03 = i6;
                        }
                        i5 = i4;
                        i4++;
                    }
                    if (i5 < i) {
                        r6[i5] = null;
                    }
                    this.A02 = i - 1;
                    i3--;
                }
                i3++;
            }
            this.A04 = false;
        }
    }

    private final C15032at A07(int i) {
        if (this.A01 + 1 >= this.A00) {
            A03();
        }
        C15032at A022 = A02(AnonymousClass007.A04);
        int i2 = this.A03 + 1;
        this.A03 = i2;
        this.A01++;
        A022.A04 = i2;
        A022.A05 = i;
        this.A0E.A03[i2] = A022;
        this.A0A.A1G(A022);
        return A022;
    }

    public final AnonymousClass2ap A06() {
        AnonymousClass2bA r1 = this.A0E;
        AnonymousClass2ap A19 = r1.A02.A19();
        if (A19 == null) {
            A19 = new AnonymousClass2ap(r1);
            A0G++;
        } else {
            A19.A02 = null;
            A19.A01.clear();
            A19.A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            A19.A04 = false;
        }
        C15032at.A0C++;
        return A19;
    }

    public final void A09() throws Exception {
        AnonymousClass2b7 r6 = this.A0A;
        if (!r6.isEmpty()) {
            if (this.A05) {
                for (int i = 0; i < this.A02; i++) {
                    if (this.A06[i].A04) {
                    }
                }
            }
            int i2 = 0;
            while (true) {
                if (i2 >= this.A02) {
                    break;
                }
                AnonymousClass2ap[] r1 = this.A06;
                Integer num = r1[i2].A02.A07;
                Integer num2 = AnonymousClass007.A00;
                if (num != num2 && r1[i2].A00 < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                    boolean z = false;
                    int i3 = 0;
                    do {
                        i3++;
                        int i4 = -1;
                        int i5 = -1;
                        float f = Float.MAX_VALUE;
                        int i6 = 0;
                        for (int i7 = 0; i7 < this.A02; i7++) {
                            AnonymousClass2ap r12 = this.A06[i7];
                            if (r12.A02.A07 != num2 && !r12.A04 && r12.A00 < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                                int A3e = r12.A01.A3e();
                                for (int i8 = 0; i8 < A3e; i8++) {
                                    AnonymousClass2b5 r0 = r12.A01;
                                    C15032at A5E = r0.A5E(i8);
                                    float A3I = r0.A3I(A5E);
                                    if (A3I > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                                        int i9 = 0;
                                        do {
                                            float f2 = A5E.A0B[i9] / A3I;
                                            if ((f2 < f && i9 == i6) || i9 > i6) {
                                                i5 = A5E.A04;
                                                i4 = i7;
                                                f = f2;
                                                i6 = i9;
                                            }
                                            i9++;
                                        } while (i9 < 9);
                                    }
                                }
                            }
                        }
                        if (i4 != -1) {
                            AnonymousClass2ap r13 = this.A06[i4];
                            r13.A02.A03 = -1;
                            r13.A05(this.A0E.A03[i5]);
                            C15032at r02 = r13.A02;
                            r02.A03 = i4;
                            r02.A04(this, r13);
                        } else {
                            z = true;
                        }
                        if (i3 > (this.A01 >> 1)) {
                            break;
                        }
                    } while (!z);
                } else {
                    i2++;
                }
            }
            A05(r6);
            for (int i10 = 0; i10 < this.A02; i10++) {
                AnonymousClass2ap r03 = this.A06[i10];
                r03.A02.A02 = r03.A00;
            }
            return;
        }
        for (int i11 = 0; i11 < this.A02; i11++) {
            AnonymousClass2ap r04 = this.A06[i11];
            r04.A02.A02 = r04.A00;
        }
    }

    public final void A0E(C15032at r7, C15032at r8, int i, int i2) {
        if (i2 == 8 && r8.A09 && r7.A03 == -1) {
            r7.A03(this, r8.A02 + ((float) i));
            return;
        }
        AnonymousClass2ap A062 = A06();
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i = -i;
                z = true;
            }
            A062.A00 = (float) i;
        }
        if (!z) {
            A062.A01.A8m(r7, -1.0f);
            A062.A01.A8m(r8, 1.0f);
        } else {
            A062.A01.A8m(r7, 1.0f);
            A062.A01.A8m(r8, -1.0f);
        }
        if (i2 != 8) {
            A062.A01.A8m(A07(i2), 1.0f);
            A062.A01.A8m(A07(i2), -1.0f);
        }
        A0B(A062);
    }

    public C15022am() {
        this.A04 = false;
        this.A03 = 0;
        this.A07 = 32;
        this.A00 = 32;
        this.A06 = null;
        this.A05 = false;
        this.A0D = new boolean[32];
        this.A01 = 1;
        this.A02 = 0;
        this.A08 = 32;
        this.A0C = new C15032at[A0F];
        this.A09 = 0;
        this.A06 = new AnonymousClass2ap[32];
        AnonymousClass2bA r1 = new AnonymousClass2bA();
        this.A0E = r1;
        this.A0A = new AnonymousClass2as(r1);
        this.A0B = new AnonymousClass2ap(r1);
    }

    public final void A0D(C15032at r7, C15032at r8, int i, float f, C15032at r11, C15032at r12, int i2, int i3) {
        float f2;
        int i4;
        AnonymousClass2ap A062 = A06();
        if (r8 == r11) {
            A062.A01.A8m(r7, 1.0f);
            A062.A01.A8m(r12, 1.0f);
            A062.A01.A8m(r8, -2.0f);
        } else {
            if (f == 0.5f) {
                A062.A01.A8m(r7, 1.0f);
                A062.A01.A8m(r8, -1.0f);
                A062.A01.A8m(r11, -1.0f);
                A062.A01.A8m(r12, 1.0f);
                if (i > 0 || i2 > 0) {
                    i4 = (-i) + i2;
                }
            } else {
                if (f <= AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                    A062.A01.A8m(r7, -1.0f);
                    A062.A01.A8m(r8, 1.0f);
                    f2 = (float) i;
                } else if (f >= 1.0f) {
                    A062.A01.A8m(r12, -1.0f);
                    A062.A01.A8m(r11, 1.0f);
                    i4 = -i2;
                } else {
                    float f3 = 1.0f - f;
                    A062.A01.A8m(r7, f3 * 1.0f);
                    A062.A01.A8m(r8, f3 * -1.0f);
                    A062.A01.A8m(r11, -1.0f * f);
                    A062.A01.A8m(r12, 1.0f * f);
                    if (i > 0 || i2 > 0) {
                        f2 = (((float) (-i)) * f3) + (((float) i2) * f);
                    }
                }
                A062.A00 = f2;
            }
            f2 = (float) i4;
            A062.A00 = f2;
        }
        if (i3 != 8) {
            A062.A01.A8m(A07(i3), 1.0f);
            A062.A01.A8m(A07(i3), -1.0f);
        }
        A0B(A062);
    }

    public final void A0F(C15032at r5, C15032at r6, int i, int i2) {
        AnonymousClass2ap A062 = A06();
        C15032at A012 = A01(this);
        A012.A05 = 0;
        A062.A06(r5, r6, A012, i);
        if (i2 != 8) {
            int A3I = (int) (A062.A01.A3I(A012) * -1.0f);
            A062.A01.A8m(A07(i2), (float) A3I);
        }
        A0B(A062);
    }

    public final void A0G(C15032at r5, C15032at r6, int i, int i2) {
        AnonymousClass2ap A062 = A06();
        C15032at A012 = A01(this);
        A012.A05 = 0;
        A062.A07(r5, r6, A012, i);
        if (i2 != 8) {
            int A3I = (int) (A062.A01.A3I(A012) * -1.0f);
            A062.A01.A8m(A07(i2), (float) A3I);
        }
        A0B(A062);
    }

    public final void A0H(C15032at r4, C15032at r5, C15032at r6, C15032at r7, float f) {
        AnonymousClass2ap A062 = A06();
        A062.A01.A8m(r4, -1.0f);
        A062.A01.A8m(r5, 1.0f);
        A062.A01.A8m(r6, f);
        A062.A01.A8m(r7, -f);
        A0B(A062);
    }
}
