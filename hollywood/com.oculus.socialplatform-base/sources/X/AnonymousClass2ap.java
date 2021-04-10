package X;

import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: X.2ap  reason: invalid class name */
public class AnonymousClass2ap implements AnonymousClass2b7 {
    public float A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public AnonymousClass2b5 A01;
    public C15032at A02 = null;
    public ArrayList<C15032at> A03 = new ArrayList<>();
    public boolean A04 = false;

    public final void A06(C15032at r5, C15032at r6, C15032at r7, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i = -i;
                z = true;
            }
            this.A00 = (float) i;
        }
        if (!z) {
            this.A01.A8m(r5, -1.0f);
            this.A01.A8m(r6, 1.0f);
            this.A01.A8m(r7, 1.0f);
            return;
        }
        this.A01.A8m(r5, 1.0f);
        this.A01.A8m(r6, -1.0f);
        this.A01.A8m(r7, -1.0f);
    }

    public final void A07(C15032at r5, C15032at r6, C15032at r7, int i) {
        boolean z = false;
        if (i != 0) {
            if (i < 0) {
                i = -i;
                z = true;
            }
            this.A00 = (float) i;
        }
        if (!z) {
            this.A01.A8m(r5, -1.0f);
            this.A01.A8m(r6, 1.0f);
            this.A01.A8m(r7, -1.0f);
            return;
        }
        this.A01.A8m(r5, 1.0f);
        this.A01.A8m(r6, -1.0f);
        this.A01.A8m(r7, 1.0f);
    }

    public static C15032at A00(AnonymousClass2ap r9, boolean[] zArr, C15032at r11) {
        AnonymousClass2b5 r7 = r9.A01;
        int A3e = r7.A3e();
        C15032at r8 = null;
        float f = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        for (int i = 0; i < A3e; i++) {
            float A5F = r7.A5F(i);
            if (A5F < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                C15032at A5E = r7.A5E(i);
                if ((zArr == null || !zArr[A5E.A04]) && A5E != r11 && ((A5E.A07 == AnonymousClass007.A03 || A5E.A07 == AnonymousClass007.A04) && A5F < f)) {
                    f = A5F;
                    r8 = A5E;
                }
            }
        }
        return r8;
    }

    public final void A03(C15022am r18, AnonymousClass2ap r19, boolean z) {
        if (!(this instanceof AnonymousClass2as)) {
            this.A00 += r19.A00 * this.A01.AAy(r19, z);
            if (z) {
                r19.A02.A02(this);
            }
            if (this.A02 != null && this.A01.A3e() == 0) {
                this.A04 = true;
                r18.A04 = true;
                return;
            }
            return;
        }
        AnonymousClass2as r6 = (AnonymousClass2as) this;
        C15032at r5 = r19.A02;
        if (r5 != null) {
            AnonymousClass2b5 r4 = r19.A01;
            int A3e = r4.A3e();
            for (int i = 0; i < A3e; i++) {
                C15032at A5E = r4.A5E(i);
                float A5F = r4.A5F(i);
                AnonymousClass2b3 r11 = r6.A02;
                r11.A01 = A5E;
                boolean z2 = true;
                int i2 = 0;
                if (A5E.A08) {
                    int i3 = 0;
                    do {
                        float[] fArr = A5E.A0A;
                        float f = fArr[i3] + (r5.A0A[i3] * A5F);
                        fArr[i3] = f;
                        if (Math.abs(f) < 1.0E-4f) {
                            fArr[i3] = 0.0f;
                        } else {
                            z2 = false;
                        }
                        i3++;
                    } while (i3 < 9);
                    if (z2) {
                        AnonymousClass2as.A02(r11.A02, A5E);
                    }
                } else {
                    do {
                        float f2 = r5.A0A[i2];
                        if (f2 != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                            float f3 = f2 * A5F;
                            if (Math.abs(f3) < 1.0E-4f) {
                                f3 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                            }
                            A5E.A0A[i2] = f3;
                        } else {
                            A5E.A0A[i2] = 0.0f;
                        }
                        i2++;
                    } while (i2 < 9);
                    AnonymousClass2as.A01(r6, A5E);
                }
                ((AnonymousClass2ap) r6).A00 += r19.A00 * A5F;
            }
            AnonymousClass2as.A02(r6, r5);
        }
    }

    public final void A04(C15022am r5, C15032at r6, boolean z) {
        if (r6.A09) {
            AnonymousClass2b5 r3 = this.A01;
            this.A00 += r6.A02 * r3.A3I(r6);
            r3.A91(r6, z);
            if (z) {
                r6.A02(this);
            }
            if (r3.A3e() == 0) {
                this.A04 = true;
                r5.A04 = true;
            }
        }
    }

    public final void A05(C15032at r5) {
        C15032at r1 = this.A02;
        if (r1 != null) {
            this.A01.A8m(r1, -1.0f);
            this.A02.A03 = -1;
            this.A02 = null;
        }
        AnonymousClass2b5 r2 = this.A01;
        float A91 = r2.A91(r5, true) * -1.0f;
        this.A02 = r5;
        if (A91 != 1.0f) {
            this.A00 /= A91;
            r2.A2f(A91);
        }
    }

    @Override // X.AnonymousClass2b7
    public final void A1G(C15032at r5) {
        if (!(this instanceof AnonymousClass2as)) {
            int i = r5.A05;
            float f = 1.0f;
            if (i != 1) {
                if (i == 2) {
                    f = 1000.0f;
                } else if (i == 3) {
                    f = 1000000.0f;
                } else if (i == 4) {
                    f = 1.0E9f;
                } else if (i == 5) {
                    f = 1.0E12f;
                }
            }
            this.A01.A8m(r5, f);
            return;
        }
        AnonymousClass2as r3 = (AnonymousClass2as) this;
        r3.A02.A01 = r5;
        Arrays.fill(r5.A0A, (float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        r5.A0A[r5.A05] = 1.0f;
        AnonymousClass2as.A01(r3, r5);
    }

    @Override // X.AnonymousClass2b7
    public final C15032at A4D() {
        return this.A02;
    }

    @Override // X.AnonymousClass2b7
    public final C15032at A4e(C15022am r11, boolean[] zArr) {
        if (!(this instanceof AnonymousClass2as)) {
            return A00(this, zArr, null);
        }
        AnonymousClass2as r8 = (AnonymousClass2as) this;
        int i = -1;
        for (int i2 = 0; i2 < r8.A01; i2++) {
            C15032at[] r1 = r8.A03;
            C15032at r9 = r1[i2];
            if (!zArr[r9.A04]) {
                r8.A02.A01 = r9;
                if (i == -1) {
                    int i3 = 8;
                    while (true) {
                        float f = r9.A0A[i3];
                        if (f > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                            break;
                        }
                        if (f < AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                            break;
                        }
                        i3--;
                        if (i3 < 0) {
                            break;
                        }
                    }
                } else {
                    C15032at r4 = r1[i];
                    int i4 = 8;
                    while (true) {
                        float f2 = r4.A0A[i4];
                        float f3 = r9.A0A[i4];
                        if (f3 == f2) {
                            i4--;
                            if (i4 < 0) {
                                break;
                            }
                        } else if (f3 >= f2) {
                        }
                    }
                }
                i = i2;
            }
        }
        if (i == -1) {
            return null;
        }
        return r8.A03[i];
    }

    @Override // X.AnonymousClass2b7
    public final void A5f(AnonymousClass2b7 r6) {
        if (r6 instanceof AnonymousClass2ap) {
            AnonymousClass2ap r62 = (AnonymousClass2ap) r6;
            this.A02 = null;
            this.A01.clear();
            int i = 0;
            while (true) {
                AnonymousClass2b5 r1 = r62.A01;
                if (i < r1.A3e()) {
                    this.A01.A1B(r1.A5E(i), r1.A5F(i), true);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    @Override // X.AnonymousClass2b7
    public final void clear() {
        if (!(this instanceof AnonymousClass2as)) {
            this.A01.clear();
            this.A02 = null;
            this.A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            return;
        }
        AnonymousClass2as r1 = (AnonymousClass2as) this;
        r1.A01 = 0;
        ((AnonymousClass2ap) r1).A00 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    }

    @Override // X.AnonymousClass2b7
    public final boolean isEmpty() {
        if (!(this instanceof AnonymousClass2as)) {
            if (this.A02 == null && this.A00 == AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z && this.A01.A3e() == 0) {
                return true;
            }
            return false;
        } else if (((AnonymousClass2as) this).A01 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0084  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toString() {
        /*
        // Method dump skipped, instructions count: 192
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass2ap.toString():java.lang.String");
    }

    public AnonymousClass2ap() {
    }

    public AnonymousClass2ap(AnonymousClass2bA r3) {
        this.A01 = new AnonymousClass2aw(this, r3);
    }
}
