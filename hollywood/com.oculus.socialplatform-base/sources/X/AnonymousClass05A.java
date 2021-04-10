package X;

import android.graphics.Path;
import android.util.Log;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.05A  reason: invalid class name */
public class AnonymousClass05A {
    public char A00;
    public float[] A01;

    public AnonymousClass05A(char c, float[] fArr) {
        this.A00 = c;
        this.A01 = fArr;
    }

    public AnonymousClass05A(AnonymousClass05A r5) {
        this.A00 = r5.A00;
        float[] fArr = r5.A01;
        int length = fArr.length - 0;
        int min = Math.min(length, length);
        float[] fArr2 = new float[length];
        System.arraycopy(fArr, 0, fArr2, 0, min);
        this.A01 = fArr2;
    }

    public static void A00(Path path, float f, float f2, float f3, float f4, float f5, float f6, float f7, boolean z, boolean z2) {
        double d;
        double d2;
        double radians = Math.toRadians((double) f7);
        double cos = Math.cos(radians);
        double sin = Math.sin(radians);
        double d3 = (double) f;
        double d4 = d3;
        double d5 = (double) f2;
        double d6 = (d3 * cos) + (d5 * sin);
        double d7 = (double) f5;
        double d8 = d6 / d7;
        double d9 = (double) f6;
        double d10 = ((((double) (-f)) * sin) + (d5 * cos)) / d9;
        double d11 = (double) f4;
        double d12 = ((((double) f3) * cos) + (d11 * sin)) / d7;
        double d13 = ((((double) (-f3)) * sin) + (d11 * cos)) / d9;
        double d14 = d8 - d12;
        double d15 = d10 - d13;
        double d16 = (d8 + d12) / 2.0d;
        double d17 = (d10 + d13) / 2.0d;
        double d18 = (d14 * d14) + (d15 * d15);
        if (d18 == 0.0d) {
            Log.w("PathParser", " Points are coincident");
            return;
        }
        double d19 = (1.0d / d18) - 0.25d;
        if (d19 < 0.0d) {
            StringBuilder sb = new StringBuilder();
            sb.append("Points are too far apart ");
            sb.append(d18);
            Log.w("PathParser", sb.toString());
            float sqrt = (float) (Math.sqrt(d18) / 1.99999d);
            A00(path, f, f2, f3, f4, f5 * sqrt, f6 * sqrt, f7, z, z2);
            return;
        }
        double sqrt2 = Math.sqrt(d19);
        double d20 = d14 * sqrt2;
        double d21 = sqrt2 * d15;
        if (z == z2) {
            d = d16 - d21;
            d2 = d17 + d20;
        } else {
            d = d16 + d21;
            d2 = d17 - d20;
        }
        double atan2 = Math.atan2(d10 - d2, d8 - d);
        double atan22 = Math.atan2(d13 - d2, d12 - d) - atan2;
        boolean z3 = false;
        if (atan22 >= 0.0d) {
            z3 = true;
        }
        if (z2 != z3) {
            atan22 = atan22 > 0.0d ? atan22 - 6.283185307179586d : atan22 + 6.283185307179586d;
        }
        double d22 = d * d7;
        double d23 = d2 * d9;
        double d24 = (d22 * cos) - (d23 * sin);
        double d25 = (d22 * sin) + (d23 * cos);
        int ceil = (int) Math.ceil(Math.abs((atan22 * 4.0d) / 3.141592653589793d));
        double cos2 = Math.cos(atan2);
        double sin2 = Math.sin(atan2);
        double d26 = -d7;
        double d27 = d26 * cos;
        double d28 = d9 * sin;
        double d29 = (d27 * sin2) - (d28 * cos2);
        double d30 = d26 * sin;
        double d31 = d9 * cos;
        double d32 = (sin2 * d30) + (cos2 * d31);
        double d33 = atan22 / ((double) ceil);
        int i = 0;
        while (i < ceil) {
            double d34 = atan2 + d33;
            double sin3 = Math.sin(d34);
            double cos3 = Math.cos(d34);
            double d35 = (d24 + ((d7 * cos) * cos3)) - (d28 * sin3);
            double d36 = d25 + (d7 * sin * cos3) + (d31 * sin3);
            double d37 = (d27 * sin3) - (d28 * cos3);
            double d38 = (sin3 * d30) + (cos3 * d31);
            double d39 = d34 - atan2;
            double tan = Math.tan(d39 / 2.0d);
            double sin4 = (Math.sin(d39) * (Math.sqrt(((tan * 3.0d) * tan) + 4.0d) - 1.0d)) / 3.0d;
            path.rLineTo(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            path.cubicTo((float) (d4 + (d29 * sin4)), (float) (d5 + (d32 * sin4)), (float) (d35 - (sin4 * d37)), (float) (d36 - (sin4 * d38)), (float) d35, (float) d36);
            i++;
            d5 = d36;
            atan2 = d34;
            d32 = d38;
            d29 = d37;
            d4 = d35;
        }
    }

    public static void A01(AnonymousClass05A[] r36, Path path) {
        int i;
        int i2;
        float f;
        int i3;
        float f2;
        int i4;
        float f3;
        float f4;
        int i5;
        float f5;
        float f6;
        float f7;
        float f8;
        float[] fArr = new float[6];
        char c = 'm';
        for (int i6 = 0; i6 < r36.length; i6++) {
            AnonymousClass05A r0 = r36[i6];
            char c2 = r0.A00;
            float[] fArr2 = r0.A01;
            float f9 = fArr[0];
            float f10 = fArr[1];
            float f11 = fArr[2];
            float f12 = fArr[3];
            float f13 = fArr[4];
            float f14 = fArr[5];
            switch (c2) {
                case 'A':
                case 'a':
                    i = 7;
                    break;
                case 'C':
                case 'c':
                    i = 6;
                    break;
                case 'H':
                case 'V':
                case 'h':
                case 'v':
                    i = 1;
                    break;
                case 'Q':
                case 'S':
                case 'q':
                case 's':
                    i = 4;
                    break;
                case 'Z':
                case 'z':
                    path.close();
                    f9 = f13;
                    path.moveTo(f9, f14);
                    f11 = f9;
                    f10 = f14;
                    f12 = f14;
                default:
                    i = 2;
                    break;
            }
            int i7 = 0;
            while (i7 < fArr2.length) {
                if (c2 == 'A') {
                    int i8 = i7 + 5;
                    float f15 = fArr2[i8];
                    int i9 = i7 + 6;
                    float f16 = fArr2[i9];
                    float f17 = fArr2[i7];
                    float f18 = fArr2[i7 + 1];
                    float f19 = fArr2[i7 + 2];
                    boolean z = false;
                    if (fArr2[i7 + 3] != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                        z = true;
                    }
                    boolean z2 = false;
                    if (fArr2[i7 + 4] != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                        z2 = true;
                    }
                    A00(path, f9, f10, f15, f16, f17, f18, f19, z, z2);
                    f9 = fArr2[i8];
                    f10 = fArr2[i9];
                    f12 = f10;
                    f11 = f9;
                } else if (c2 == 'C') {
                    int i10 = i7 + 2;
                    int i11 = i7 + 3;
                    int i12 = i7 + 4;
                    int i13 = i7 + 5;
                    path.cubicTo(fArr2[i7], fArr2[i7 + 1], fArr2[i10], fArr2[i11], fArr2[i12], fArr2[i13]);
                    f9 = fArr2[i12];
                    f10 = fArr2[i13];
                    f11 = fArr2[i10];
                    f12 = fArr2[i11];
                } else if (c2 != 'H') {
                    if (c2 == 'Q') {
                        int i14 = i7 + 1;
                        int i15 = i7 + 2;
                        i2 = i7 + 3;
                        path.quadTo(fArr2[i7], fArr2[i14], fArr2[i15], fArr2[i2]);
                        f11 = fArr2[i7];
                        f12 = fArr2[i14];
                        f9 = fArr2[i15];
                    } else if (c2 == 'V') {
                        path.lineTo(f9, fArr2[i7]);
                        f10 = fArr2[i7];
                    } else if (c2 != 'a') {
                        if (c2 == 'c') {
                            int i16 = i7 + 2;
                            i5 = i7 + 3;
                            i4 = i7 + 4;
                            i3 = i7 + 5;
                            path.rCubicTo(fArr2[i7], fArr2[i7 + 1], fArr2[i16], fArr2[i5], fArr2[i4], fArr2[i3]);
                            f4 = fArr2[i16];
                        } else if (c2 == 'h') {
                            path.rLineTo(fArr2[i7], AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                            f9 += fArr2[i7];
                        } else if (c2 == 'q') {
                            int i17 = i7 + 1;
                            i4 = i7 + 2;
                            i3 = i7 + 3;
                            path.rQuadTo(fArr2[i7], fArr2[i17], fArr2[i4], fArr2[i3]);
                            f11 = fArr2[i7] + f9;
                            f3 = fArr2[i17];
                            f12 = f3 + f10;
                            f2 = fArr2[i4];
                            f9 += f2;
                            f = fArr2[i3];
                            f10 += f;
                        } else if (c2 == 'v') {
                            path.rLineTo(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, fArr2[i7]);
                            f = fArr2[i7];
                            f10 += f;
                        } else if (c2 != 'L') {
                            if (c2 == 'M') {
                                f9 = fArr2[i7];
                                int i18 = i7 + 1;
                                f10 = fArr2[i18];
                                float f20 = fArr2[i7];
                                float f21 = fArr2[i18];
                                if (i7 > 0) {
                                    path.lineTo(f20, f21);
                                } else {
                                    path.moveTo(f20, f21);
                                }
                            } else if (c2 == 'S') {
                                if (c == 'c' || c == 's' || c == 'C' || c == 'S') {
                                    f9 = (f9 * 2.0f) - f11;
                                    f10 = (f10 * 2.0f) - f12;
                                }
                                int i19 = i7 + 1;
                                int i20 = i7 + 2;
                                int i21 = i7 + 3;
                                path.cubicTo(f9, f10, fArr2[i7], fArr2[i19], fArr2[i20], fArr2[i21]);
                                f11 = fArr2[i7];
                                f12 = fArr2[i19];
                                f9 = fArr2[i20];
                                f10 = fArr2[i21];
                            } else if (c2 == 'T') {
                                if (c == 'q' || c == 't' || c == 'Q' || c == 'T') {
                                    f9 = (f9 * 2.0f) - f11;
                                    f10 = (f10 * 2.0f) - f12;
                                }
                                int i22 = i7 + 1;
                                path.quadTo(f9, f10, fArr2[i7], fArr2[i22]);
                                float f22 = fArr2[i7];
                                float f23 = fArr2[i22];
                                f12 = f10;
                                f11 = f9;
                                f9 = f22;
                                f10 = f23;
                            } else if (c2 == 'l') {
                                i3 = i7 + 1;
                                path.rLineTo(fArr2[i7], fArr2[i3]);
                                f2 = fArr2[i7];
                                f9 += f2;
                                f = fArr2[i3];
                                f10 += f;
                            } else if (c2 == 'm') {
                                f9 += fArr2[i7];
                                int i23 = i7 + 1;
                                f10 += fArr2[i23];
                                float f24 = fArr2[i7];
                                float f25 = fArr2[i23];
                                if (i7 > 0) {
                                    path.rLineTo(f24, f25);
                                } else {
                                    path.rMoveTo(f24, f25);
                                }
                            } else if (c2 == 's') {
                                if (c == 'c' || c == 's' || c == 'C' || c == 'S') {
                                    f5 = f9 - f11;
                                    f6 = f10 - f12;
                                } else {
                                    f5 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                                    f6 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                                }
                                i5 = i7 + 1;
                                i4 = i7 + 2;
                                i3 = i7 + 3;
                                path.rCubicTo(f5, f6, fArr2[i7], fArr2[i5], fArr2[i4], fArr2[i3]);
                                f4 = fArr2[i7];
                            } else if (c2 == 't') {
                                if (c == 'q' || c == 't' || c == 'Q' || c == 'T') {
                                    f7 = f9 - f11;
                                    f8 = f10 - f12;
                                } else {
                                    f8 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                                    f7 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
                                }
                                int i24 = i7 + 1;
                                path.rQuadTo(f7, f8, fArr2[i7], fArr2[i24]);
                                f11 = f7 + f9;
                                f12 = f8 + f10;
                                f9 += fArr2[i7];
                                f10 += fArr2[i24];
                            }
                            f14 = f10;
                            f13 = f9;
                        } else {
                            i2 = i7 + 1;
                            path.lineTo(fArr2[i7], fArr2[i2]);
                            f9 = fArr2[i7];
                        }
                        f11 = f4 + f9;
                        f3 = fArr2[i5];
                        f12 = f3 + f10;
                        f2 = fArr2[i4];
                        f9 += f2;
                        f = fArr2[i3];
                        f10 += f;
                    } else {
                        int i25 = i7 + 5;
                        float f26 = fArr2[i25] + f9;
                        int i26 = i7 + 6;
                        float f27 = fArr2[i26] + f10;
                        float f28 = fArr2[i7];
                        float f29 = fArr2[i7 + 1];
                        float f30 = fArr2[i7 + 2];
                        boolean z3 = false;
                        if (fArr2[i7 + 3] != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                            z3 = true;
                        }
                        boolean z4 = false;
                        if (fArr2[i7 + 4] != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                            z4 = true;
                        }
                        A00(path, f9, f10, f26, f27, f28, f29, f30, z3, z4);
                        f9 += fArr2[i25];
                        f10 += fArr2[i26];
                        f12 = f10;
                        f11 = f9;
                    }
                    f10 = fArr2[i2];
                } else {
                    path.lineTo(fArr2[i7], f10);
                    f9 = fArr2[i7];
                }
                i7 += i;
                c = c2;
            }
            fArr[0] = f9;
            fArr[1] = f10;
            fArr[2] = f11;
            fArr[3] = f12;
            fArr[4] = f13;
            fArr[5] = f14;
            c = r36[i6].A00;
        }
    }
}
