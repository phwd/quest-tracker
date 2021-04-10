package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzn;
import com.google.android.gms.vision.face.internal.client.zze;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: sN  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4735sN implements AbstractC4224pN {
    public final int F;
    public final boolean G;
    public final JN H;

    public C4735sN(KN kn) {
        int i;
        boolean z;
        boolean z2;
        Context applicationContext = ContextUtils.getApplicationContext();
        int min = Math.min(kn.d, 32);
        this.F = min;
        boolean z3 = kn.e;
        this.G = z3;
        boolean z4 = false;
        int i2 = z3 ? 0 : 1;
        if (i2 == 0 || i2 == 1 || i2 == 2) {
            z = min == 1;
            i = 1;
        } else {
            try {
                StringBuilder sb = new StringBuilder(25);
                sb.append("Invalid mode: ");
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
            } catch (IllegalArgumentException e) {
                AbstractC1220Ua0.a("FaceDetectionImpl", "Unexpected exception " + e, new Object[0]);
                i2 = 0;
                z = false;
                i = 0;
            }
        }
        zze zze = new zze();
        zze.F = i2;
        zze.G = i;
        zze.H = 0;
        zze.I = z;
        zze.f9685J = true;
        zze.K = -1.0f;
        if (i2 == 2 || i != 2) {
            z2 = true;
        } else {
            Log.e("FaceDetector", "Contour is not supported for non-SELFIE mode.");
            z2 = false;
        }
        if (zze.G == 2 && zze.H == 1) {
            Log.e("FaceDetector", "Classification is not supported with contour.");
        } else {
            z4 = z2;
        }
        if (z4) {
            this.H = new JN(new TE1(applicationContext, zze), null);
            return;
        }
        throw new IllegalArgumentException("Invalid build options");
    }

    @Override // defpackage.AbstractC0543Ix
    public void Y(C5475wl0 wl0) {
        this.H.a();
    }

    @Override // java.io.Closeable, defpackage.AbstractC3313k30, java.lang.AutoCloseable
    public void close() {
        this.H.a();
    }

    @Override // defpackage.AbstractC4224pN
    public void u(C0509Ih ih, FN fn) {
        ByteBuffer byteBuffer;
        C4053oN[] f;
        int i;
        if (!this.H.c.a()) {
            AbstractC1220Ua0.a("FaceDetectionImpl", "FaceDetector is not operational", new Object[0]);
            KN kn = new KN();
            kn.e = this.G;
            kn.d = this.F;
            new C4565rN(kn).u(ih, fn);
            return;
        }
        GT b = AbstractC0692Lh.b(ih);
        if (b == null) {
            AbstractC1220Ua0.a("FaceDetectionImpl", "Error converting Mojom Bitmap to Frame", new Object[0]);
            fn.a(new AN[0]);
            return;
        }
        JN jn = this.H;
        Objects.requireNonNull(jn);
        Bitmap bitmap = b.c;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int i2 = width * height;
            byteBuffer = ByteBuffer.allocateDirect(((((height + 1) / 2) * ((width + 1) / 2)) << 1) + i2);
            int i3 = i2;
            for (int i4 = 0; i4 < i2; i4++) {
                int i5 = i4 % width;
                int i6 = i4 / width;
                int pixel = bitmap.getPixel(i5, i6);
                float red = (float) Color.red(pixel);
                float green = (float) Color.green(pixel);
                float blue = (float) Color.blue(pixel);
                byteBuffer.put(i4, (byte) ((int) ((0.114f * blue) + (0.587f * green) + (0.299f * red))));
                if (i6 % 2 == 0 && i5 % 2 == 0) {
                    int i7 = i3 + 1;
                    byteBuffer.put(i3, (byte) ((int) ((blue * 0.5f) + (-0.331f * green) + (-0.169f * red) + 128.0f)));
                    i3 = i7 + 1;
                    byteBuffer.put(i7, (byte) ((int) ((blue * -0.081f) + (green * -0.419f) + (red * 0.5f) + 128.0f)));
                }
            }
        } else {
            byteBuffer = b.a();
        }
        synchronized (jn.d) {
            if (jn.e) {
                f = jn.c.f(byteBuffer, zzn.x(b));
            } else {
                throw new RuntimeException("Cannot use detector after release()");
            }
        }
        HashSet hashSet = new HashSet();
        SparseArray sparseArray = new SparseArray(f.length);
        int i8 = 0;
        for (C4053oN oNVar : f) {
            int i9 = oNVar.f10544a;
            i8 = Math.max(i8, i9);
            if (hashSet.contains(Integer.valueOf(i9))) {
                i9 = i8 + 1;
                i8 = i9;
            }
            hashSet.add(Integer.valueOf(i9));
            RE1 re1 = jn.b;
            Objects.requireNonNull(re1);
            synchronized (RE1.f8821a) {
                Integer num = (Integer) re1.c.get(i9);
                if (num != null) {
                    i = num.intValue();
                } else {
                    int i10 = RE1.b;
                    RE1.b = i10 + 1;
                    re1.c.append(i9, Integer.valueOf(i10));
                    re1.d.append(i10, Integer.valueOf(i9));
                    i = i10;
                }
            }
            sparseArray.append(i, oNVar);
        }
        AN[] anArr = new AN[sparseArray.size()];
        for (int i11 = 0; i11 < sparseArray.size(); i11++) {
            anArr[i11] = new AN();
            C4053oN oNVar2 = (C4053oN) sparseArray.valueAt(i11);
            Objects.requireNonNull(oNVar2);
            PointF pointF = oNVar2.b;
            PointF pointF2 = new PointF(pointF.x - (oNVar2.c / 2.0f), pointF.y - (oNVar2.d / 2.0f));
            anArr[i11].d = new C4048oK0();
            anArr[i11].d.d = pointF2.x;
            anArr[i11].d.e = pointF2.y;
            anArr[i11].d.f = oNVar2.c;
            anArr[i11].d.g = oNVar2.d;
            List list = oNVar2.e;
            ArrayList arrayList = new ArrayList(list.size());
            for (int i12 = 0; i12 < list.size(); i12++) {
                C4859t60 t60 = (C4859t60) list.get(i12);
                int i13 = t60.b;
                if (i13 == 4 || i13 == 10 || i13 == 0 || i13 == 6) {
                    C4689s60 s60 = new C4689s60();
                    C4883tE0[] te0Arr = new C4883tE0[1];
                    s60.d = te0Arr;
                    te0Arr[0] = new C4883tE0();
                    C4883tE0[] te0Arr2 = s60.d;
                    C4883tE0 te0 = te0Arr2[0];
                    PointF pointF3 = t60.f11323a;
                    te0.d = pointF3.x;
                    te0Arr2[0].e = pointF3.y;
                    if (i13 == 4) {
                        s60.e = 1;
                    } else if (i13 == 10) {
                        s60.e = 1;
                    } else if (i13 == 0) {
                        s60.e = 0;
                    } else {
                        s60.e = 2;
                        arrayList.add(s60);
                    }
                    arrayList.add(s60);
                }
            }
            anArr[i11].e = (C4689s60[]) arrayList.toArray(new C4689s60[arrayList.size()]);
        }
        fn.a(anArr);
    }
}
