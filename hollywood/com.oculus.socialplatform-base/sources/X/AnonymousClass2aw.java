package X;

import com.facebook.acra.util.JavaProcFileReader;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Arrays;

/* renamed from: X.2aw  reason: invalid class name */
public final class AnonymousClass2aw implements AnonymousClass2b5 {
    public int A00 = 0;
    public int A01 = 8;
    public int A02 = -1;
    public int A03 = -1;
    public boolean A04 = false;
    public float[] A05 = new float[8];
    public int[] A06 = new int[8];
    public int[] A07 = new int[8];
    public final AnonymousClass2ap A08;
    public final AnonymousClass2bA A09;

    @Override // X.AnonymousClass2b5
    public final void A8m(C15032at r10, float f) {
        int length;
        if (f == AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            A91(r10, true);
            return;
        }
        int i = this.A02;
        if (i == -1) {
            this.A02 = 0;
            this.A05[0] = f;
            this.A06[0] = r10.A04;
            this.A07[0] = -1;
            r10.A06++;
            r10.A01(this.A08);
            this.A00++;
            if (!this.A04) {
                int i2 = this.A03 + 1;
                this.A03 = i2;
                length = this.A06.length;
                if (i2 < length) {
                    return;
                }
            } else {
                return;
            }
        } else {
            int i3 = 0;
            int i4 = -1;
            while (i != -1 && i3 < this.A00) {
                int[] iArr = this.A06;
                int i5 = iArr[i];
                int i6 = r10.A04;
                if (i5 == i6) {
                    this.A05[i] = f;
                    return;
                }
                if (iArr[i] < i6) {
                    i4 = i;
                }
                i = this.A07[i];
                i3++;
            }
            int i7 = this.A03;
            int i8 = i7 + 1;
            if (this.A04) {
                int[] iArr2 = this.A06;
                if (iArr2[i7] != -1) {
                    i7 = iArr2.length;
                }
            } else {
                i7 = i8;
            }
            int[] iArr3 = this.A06;
            int length2 = iArr3.length;
            if (i7 >= length2 && this.A00 < length2) {
                int i9 = 0;
                while (true) {
                    if (i9 >= length2) {
                        break;
                    } else if (iArr3[i9] == -1) {
                        i7 = i9;
                        break;
                    } else {
                        i9++;
                    }
                }
            }
            if (i7 >= length2) {
                i7 = length2;
                int i10 = this.A01 << 1;
                this.A01 = i10;
                this.A04 = false;
                this.A03 = length2 - 1;
                this.A05 = Arrays.copyOf(this.A05, i10);
                this.A06 = Arrays.copyOf(this.A06, this.A01);
                this.A07 = Arrays.copyOf(this.A07, this.A01);
            }
            this.A06[i7] = r10.A04;
            this.A05[i7] = f;
            int[] iArr4 = this.A07;
            if (i4 != -1) {
                iArr4[i7] = iArr4[i4];
                iArr4[i4] = i7;
            } else {
                iArr4[i7] = this.A02;
                this.A02 = i7;
            }
            r10.A06++;
            r10.A01(this.A08);
            int i11 = this.A00 + 1;
            this.A00 = i11;
            if (!this.A04) {
                this.A03++;
            }
            length = this.A06.length;
            if (i11 >= length) {
                this.A04 = true;
            }
            if (this.A03 < length) {
                return;
            }
        }
        this.A04 = true;
        this.A03 = length - 1;
    }

    @Override // X.AnonymousClass2b5
    public final boolean A2G(C15032at r7) {
        int i = this.A02;
        if (i != -1) {
            int i2 = 0;
            while (i != -1 && i2 < this.A00) {
                if (this.A06[i] == r7.A04) {
                    return true;
                }
                i = this.A07[i];
                i2++;
            }
        }
        return false;
    }

    @Override // X.AnonymousClass2b5
    public final void A2f(float f) {
        int i = this.A02;
        int i2 = 0;
        while (i != -1 && i2 < this.A00) {
            float[] fArr = this.A05;
            fArr[i] = fArr[i] / f;
            i = this.A07[i];
            i2++;
        }
    }

    @Override // X.AnonymousClass2b5
    public final float A3I(C15032at r5) {
        int i = this.A02;
        int i2 = 0;
        while (i != -1 && i2 < this.A00) {
            if (this.A06[i] == r5.A04) {
                return this.A05[i];
            }
            i = this.A07[i];
            i2++;
        }
        return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    }

    @Override // X.AnonymousClass2b5
    public final int A3e() {
        return this.A00;
    }

    @Override // X.AnonymousClass2b5
    public final C15032at A5E(int i) {
        int i2 = this.A02;
        int i3 = 0;
        while (i2 != -1 && i3 < this.A00) {
            if (i3 == i) {
                return this.A09.A03[this.A06[i2]];
            }
            i2 = this.A07[i2];
            i3++;
        }
        return null;
    }

    @Override // X.AnonymousClass2b5
    public final float A5F(int i) {
        int i2 = this.A02;
        int i3 = 0;
        while (i2 != -1 && i3 < this.A00) {
            if (i3 == i) {
                return this.A05[i2];
            }
            i2 = this.A07[i2];
            i3++;
        }
        return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    }

    @Override // X.AnonymousClass2b5
    public final void A5o() {
        int i = this.A02;
        int i2 = 0;
        while (i != -1 && i2 < this.A00) {
            float[] fArr = this.A05;
            fArr[i] = fArr[i] * -1.0f;
            i = this.A07[i];
            i2++;
        }
    }

    @Override // X.AnonymousClass2b5
    public final float A91(C15032at r11, boolean z) {
        int i = this.A02;
        if (i != -1) {
            int i2 = 0;
            int i3 = -1;
            while (i != -1) {
                int i4 = this.A00;
                if (i2 >= i4) {
                    break;
                }
                int[] iArr = this.A06;
                if (iArr[i] == r11.A04) {
                    int[] iArr2 = this.A07;
                    if (i == i) {
                        this.A02 = iArr2[i];
                    } else {
                        iArr2[i3] = iArr2[i];
                    }
                    if (z) {
                        r11.A02(this.A08);
                    }
                    r11.A06--;
                    this.A00 = i4 - 1;
                    iArr[i] = -1;
                    if (this.A04) {
                        this.A03 = i;
                    }
                    return this.A05[i];
                }
                i2++;
                i3 = i;
                i = this.A07[i];
            }
        }
        return AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    }

    @Override // X.AnonymousClass2b5
    public final float AAy(AnonymousClass2ap r7, boolean z) {
        C15032at r0 = r7.A02;
        float A3I = A3I(r0);
        A91(r0, z);
        AnonymousClass2b5 r4 = r7.A01;
        int A3e = r4.A3e();
        for (int i = 0; i < A3e; i++) {
            C15032at A5E = r4.A5E(i);
            A1B(A5E, r4.A3I(A5E) * A3I, z);
        }
        return A3I;
    }

    @Override // X.AnonymousClass2b5
    public final void clear() {
        int i = this.A02;
        int i2 = 0;
        while (i != -1 && i2 < this.A00) {
            C15032at r1 = this.A09.A03[this.A06[i]];
            if (r1 != null) {
                r1.A02(this.A08);
            }
            i = this.A07[i];
            i2++;
        }
        this.A02 = -1;
        this.A03 = -1;
        this.A04 = false;
        this.A00 = 0;
    }

    public final String toString() {
        int i = this.A02;
        String str = "";
        int i2 = 0;
        while (i != -1 && i2 < this.A00) {
            String A072 = AnonymousClass006.A07(str, JavaProcFileReader.LS_SYMLINK_ARROW);
            StringBuilder sb = new StringBuilder();
            sb.append(A072);
            sb.append(this.A05[i]);
            sb.append(" : ");
            String obj = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(obj);
            sb2.append(this.A09.A03[this.A06[i]]);
            str = sb2.toString();
            i = this.A07[i];
            i2++;
        }
        return str;
    }

    public AnonymousClass2aw(AnonymousClass2ap r4, AnonymousClass2bA r5) {
        this.A08 = r4;
        this.A09 = r5;
    }

    @Override // X.AnonymousClass2b5
    public final void A1B(C15032at r13, float f, boolean z) {
        int i;
        float f2 = -0.001f;
        if (f <= f2 || f >= 0.001f) {
            int i2 = this.A02;
            if (i2 == -1) {
                this.A02 = 0;
                this.A05[0] = f;
                this.A06[0] = r13.A04;
                this.A07[0] = -1;
                r13.A06++;
                r13.A01(this.A08);
                this.A00++;
                if (!this.A04) {
                    i = this.A03 + 1;
                    this.A03 = i;
                } else {
                    return;
                }
            } else {
                int i3 = 0;
                int i4 = -1;
                while (i2 != -1) {
                    int i5 = this.A00;
                    if (i3 >= i5) {
                        break;
                    }
                    int[] iArr = this.A06;
                    int i6 = iArr[i2];
                    int i7 = r13.A04;
                    if (i6 == i7) {
                        float[] fArr = this.A05;
                        float f3 = fArr[i2] + f;
                        if (f3 > f2 && f3 < 0.001f) {
                            f3 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                        }
                        fArr[i2] = f3;
                        if (f3 == AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                            int[] iArr2 = this.A07;
                            if (i2 == i2) {
                                this.A02 = iArr2[i2];
                            } else {
                                iArr2[i4] = iArr2[i2];
                            }
                            if (z) {
                                r13.A02(this.A08);
                            }
                            if (this.A04) {
                                this.A03 = i2;
                            }
                            r13.A06--;
                            this.A00 = i5 - 1;
                            return;
                        }
                        return;
                    }
                    if (iArr[i2] < i7) {
                        i4 = i2;
                    }
                    i2 = this.A07[i2];
                    i3++;
                }
                int i8 = this.A03;
                int i9 = i8 + 1;
                if (this.A04) {
                    int[] iArr3 = this.A06;
                    if (iArr3[i8] != -1) {
                        i8 = iArr3.length;
                    }
                } else {
                    i8 = i9;
                }
                int[] iArr4 = this.A06;
                int length = iArr4.length;
                if (i8 >= length && this.A00 < length) {
                    int i10 = 0;
                    while (true) {
                        if (i10 >= length) {
                            break;
                        } else if (iArr4[i10] == -1) {
                            i8 = i10;
                            break;
                        } else {
                            i10++;
                        }
                    }
                }
                if (i8 >= length) {
                    i8 = length;
                    int i11 = this.A01 << 1;
                    this.A01 = i11;
                    this.A04 = false;
                    this.A03 = length - 1;
                    this.A05 = Arrays.copyOf(this.A05, i11);
                    this.A06 = Arrays.copyOf(this.A06, this.A01);
                    this.A07 = Arrays.copyOf(this.A07, this.A01);
                }
                this.A06[i8] = r13.A04;
                this.A05[i8] = f;
                int[] iArr5 = this.A07;
                if (i4 != -1) {
                    iArr5[i8] = iArr5[i4];
                    iArr5[i4] = i8;
                } else {
                    iArr5[i8] = this.A02;
                    this.A02 = i8;
                }
                r13.A06++;
                r13.A01(this.A08);
                this.A00++;
                if (!this.A04) {
                    this.A03++;
                }
                i = this.A03;
            }
            int length2 = this.A06.length;
            if (i >= length2) {
                this.A04 = true;
                this.A03 = length2 - 1;
            }
        }
    }
}
