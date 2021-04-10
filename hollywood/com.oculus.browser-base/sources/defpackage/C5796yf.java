package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zze;
import com.google.android.gms.vision.barcode.Barcode;
import com.oculus.os.Version;
import org.chromium.base.ContextUtils;

/* renamed from: yf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5796yf implements AbstractC5626xf {
    public C1173Tf F;

    public C5796yf(C1234Uf uf) {
        int[] iArr = uf.d;
        int i = 0;
        if (iArr != null && iArr.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (true) {
                int[] iArr2 = uf.d;
                if (i2 >= iArr2.length) {
                    break;
                }
                if (iArr2[i2] == 0) {
                    i3 |= 4096;
                } else if (iArr2[i2] == 1) {
                    i3 |= 1;
                } else if (iArr2[i2] == 2) {
                    i3 |= 2;
                } else if (iArr2[i2] == 3) {
                    i3 |= 4;
                } else if (iArr2[i2] == 4) {
                    i3 |= 8;
                } else if (iArr2[i2] == 5) {
                    i3 |= 16;
                } else if (iArr2[i2] == 6) {
                    i3 |= 32;
                } else if (iArr2[i2] == 7) {
                    i3 |= 64;
                } else if (iArr2[i2] == 8) {
                    i3 |= 128;
                } else if (iArr2[i2] == 9) {
                    i3 |= 2048;
                } else if (iArr2[i2] == 10) {
                    i3 |= 256;
                } else if (iArr2[i2] == 12) {
                    i3 |= 512;
                } else if (iArr2[i2] == 13) {
                    i3 |= 1024;
                } else {
                    StringBuilder i4 = AbstractC2531fV.i("Unsupported barcode format hint: ");
                    i4.append(uf.d[i2]);
                    AbstractC1220Ua0.a("BarcodeDetectionImpl", i4.toString(), new Object[0]);
                }
                i2++;
            }
            i = i3;
        }
        Context applicationContext = ContextUtils.getApplicationContext();
        zze zze = new zze();
        zze.F = i;
        this.F = new C1173Tf(new C5230vG1(applicationContext, zze), null);
    }

    @Override // defpackage.AbstractC5626xf
    public void O(C0509Ih ih, C0929Pf pf) {
        int i;
        if (!this.F.b.a()) {
            AbstractC1220Ua0.a("BarcodeDetectionImpl", "BarcodeDetector is not operational", new Object[0]);
            pf.a(new C0625Kf[0]);
            return;
        }
        GT b = AbstractC0692Lh.b(ih);
        if (b == null) {
            AbstractC1220Ua0.a("BarcodeDetectionImpl", "Error converting Mojom Bitmap to Frame", new Object[0]);
            pf.a(new C0625Kf[0]);
            return;
        }
        SparseArray b2 = this.F.b(b);
        C0625Kf[] kfArr = new C0625Kf[b2.size()];
        for (int i2 = 0; i2 < b2.size(); i2++) {
            kfArr[i2] = new C0625Kf();
            Barcode barcode = (Barcode) b2.valueAt(i2);
            kfArr[i2].d = barcode.G;
            int i3 = Integer.MIN_VALUE;
            int i4 = 0;
            int i5 = Integer.MAX_VALUE;
            int i6 = Integer.MAX_VALUE;
            int i7 = Integer.MIN_VALUE;
            while (true) {
                Point[] pointArr = barcode.f9678J;
                if (i4 < pointArr.length) {
                    Point point = pointArr[i4];
                    i5 = Math.min(i5, point.x);
                    i3 = Math.max(i3, point.x);
                    i6 = Math.min(i6, point.y);
                    i7 = Math.max(i7, point.y);
                    i4++;
                } else {
                    Rect rect = new Rect(i5, i6, i3, i7);
                    kfArr[i2].e = new C4048oK0();
                    kfArr[i2].e.d = (float) rect.left;
                    kfArr[i2].e.e = (float) rect.top;
                    kfArr[i2].e.f = (float) rect.width();
                    kfArr[i2].e.g = (float) rect.height();
                    Point[] pointArr2 = barcode.f9678J;
                    kfArr[i2].g = new C4883tE0[pointArr2.length];
                    for (int i8 = 0; i8 < pointArr2.length; i8++) {
                        kfArr[i2].g[i8] = new C4883tE0();
                        kfArr[i2].g[i8].d = (float) pointArr2[i8].x;
                        kfArr[i2].g[i8].e = (float) pointArr2[i8].y;
                    }
                    C0625Kf kf = kfArr[i2];
                    int i9 = barcode.F;
                    int i10 = 1;
                    if (i9 != 1) {
                        i10 = 2;
                        if (i9 != 2) {
                            switch (i9) {
                                case 4:
                                    i = 3;
                                    continue;
                                case Version.VERSION_8:
                                    i = 4;
                                    continue;
                                case Version.VERSION_16:
                                    i = 5;
                                    continue;
                                case Version.VERSION_32:
                                    i = 6;
                                    continue;
                                case 64:
                                    i = 7;
                                    continue;
                                case 128:
                                    i = 8;
                                    continue;
                                case 256:
                                    i = 10;
                                    continue;
                                case 512:
                                    i = 12;
                                    continue;
                                case 1024:
                                    i = 13;
                                    continue;
                                case 2048:
                                    i = 9;
                                    continue;
                                case 4096:
                                    i = 0;
                                    continue;
                                default:
                                    i = 11;
                                    continue;
                            }
                            kf.f = i;
                        }
                    }
                    i = i10;
                    kf.f = i;
                }
            }
        }
        pf.a(kfArr);
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        this.F.a();
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
        this.F.a();
    }
}
