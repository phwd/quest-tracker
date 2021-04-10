package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzae;
import com.google.android.gms.internal.vision.zzag;
import com.google.android.gms.internal.vision.zzal;
import com.google.android.gms.internal.vision.zzn;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.Objects;
import org.chromium.base.ContextUtils;

/* renamed from: Vf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1297Vf1 implements AbstractC1236Uf1 {
    public C0386Gg1 F = new C0386Gg1(new C4711sD1(ContextUtils.getApplicationContext(), new zzal()), null);

    @Override // defpackage.AbstractC1236Uf1
    public void R(C0509Ih ih, C2220dg1 dg1) {
        byte[] bArr;
        int i;
        zzae[] zzaeArr;
        int i2;
        String str;
        int i3;
        if (!this.F.b.a()) {
            AbstractC1220Ua0.a("TextDetectionImpl", "TextDetector is not operational", new Object[0]);
            dg1.a(new C1358Wf1[0]);
            return;
        }
        GT b = AbstractC0692Lh.b(ih);
        if (b == null) {
            AbstractC1220Ua0.a("TextDetectionImpl", "Error converting Mojom Bitmap to Frame", new Object[0]);
            dg1.a(new C1358Wf1[0]);
            return;
        }
        C0386Gg1 gg1 = this.F;
        Objects.requireNonNull(gg1);
        zzag zzag = new zzag(new Rect());
        zzn x = zzn.x(b);
        Bitmap bitmap = b.c;
        if (bitmap == null) {
            FT ft = b.f8091a;
            ByteBuffer a2 = b.a();
            int i4 = ft.c;
            int i5 = x.F;
            int i6 = x.G;
            if (!a2.hasArray() || a2.arrayOffset() != 0) {
                byte[] bArr2 = new byte[a2.capacity()];
                a2.get(bArr2);
                bArr = bArr2;
            } else {
                bArr = a2.array();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new YuvImage(bArr, i4, i5, i6, null).compressToJpeg(new Rect(0, 0, i5, i6), 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }
        Bitmap bitmap2 = bitmap;
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        if (x.f9675J != 0) {
            Matrix matrix = new Matrix();
            int i7 = x.f9675J;
            if (i7 == 0) {
                i3 = 0;
            } else if (i7 == 1) {
                i3 = 90;
            } else if (i7 == 2) {
                i3 = 180;
            } else if (i7 == 3) {
                i3 = 270;
            } else {
                throw new IllegalArgumentException("Unsupported rotation degree.");
            }
            matrix.postRotate((float) i3);
            i = 1;
            bitmap2 = Bitmap.createBitmap(bitmap2, 0, 0, width, height, matrix, false);
        } else {
            i = 1;
        }
        int i8 = x.f9675J;
        if (i8 == i || i8 == 3) {
            x.F = height;
            x.G = width;
        }
        if (!zzag.F.isEmpty()) {
            Rect rect = zzag.F;
            FT ft2 = b.f8091a;
            int i9 = ft2.f8017a;
            int i10 = ft2.b;
            int i11 = x.f9675J;
            if (i11 == i) {
                rect = new Rect(i10 - rect.bottom, rect.left, i10 - rect.top, rect.right);
            } else if (i11 == 2) {
                rect = new Rect(i9 - rect.right, i10 - rect.bottom, i9 - rect.left, i10 - rect.top);
            } else if (i11 == 3) {
                rect = new Rect(rect.top, i9 - rect.right, rect.bottom, i9 - rect.left);
            }
            zzag.F.set(rect);
        }
        x.f9675J = 0;
        C4711sD1 sd1 = gg1.b;
        if (!sd1.a()) {
            zzaeArr = new zzae[0];
        } else {
            try {
                BinderC0773Mq0 mq0 = new BinderC0773Mq0(bitmap2);
                BC1 bc1 = (BC1) sd1.e();
                Parcel c = bc1.c();
                QE1.a(c, mq0);
                QE1.b(c, x);
                QE1.b(c, zzag);
                Parcel d = bc1.d(3, c);
                zzae[] zzaeArr2 = (zzae[]) d.createTypedArray(zzae.CREATOR);
                d.recycle();
                zzaeArr = zzaeArr2;
            } catch (RemoteException e) {
                Log.e("TextNativeHandle", "Error calling native text recognizer", e);
                zzaeArr = new zzae[0];
            }
        }
        SparseArray sparseArray = new SparseArray();
        for (zzae zzae : zzaeArr) {
            SparseArray sparseArray2 = (SparseArray) sparseArray.get(zzae.O);
            if (sparseArray2 == null) {
                sparseArray2 = new SparseArray();
                sparseArray.append(zzae.O, sparseArray2);
            }
            sparseArray2.append(zzae.P, zzae);
        }
        SparseArray sparseArray3 = new SparseArray(sparseArray.size());
        for (int i12 = 0; i12 < sparseArray.size(); i12++) {
            sparseArray3.append(sparseArray.keyAt(i12), new C0992Qf1((SparseArray) sparseArray.valueAt(i12)));
        }
        C1358Wf1[] wf1Arr = new C1358Wf1[sparseArray3.size()];
        int i13 = 0;
        while (i13 < sparseArray3.size()) {
            wf1Arr[i13] = new C1358Wf1();
            C0992Qf1 qf1 = (C0992Qf1) sparseArray3.valueAt(i13);
            C1358Wf1 wf1 = wf1Arr[i13];
            zzae[] zzaeArr3 = qf1.f8775a;
            if (zzaeArr3.length == 0) {
                str = "";
                i2 = 0;
            } else {
                i2 = 0;
                StringBuilder sb = new StringBuilder(zzaeArr3[0].f9673J);
                for (int i14 = i; i14 < qf1.f8775a.length; i14++) {
                    sb.append("\n");
                    sb.append(qf1.f8775a[i14].f9673J);
                }
                str = sb.toString();
            }
            wf1.d = str;
            if (qf1.c == null) {
                Point[] a3 = qf1.a();
                int length = a3.length;
                int i15 = Integer.MIN_VALUE;
                int i16 = Integer.MAX_VALUE;
                int i17 = Integer.MAX_VALUE;
                int i18 = Integer.MIN_VALUE;
                for (int i19 = i2; i19 < length; i19++) {
                    Point point = a3[i19];
                    i16 = Math.min(i16, point.x);
                    i18 = Math.max(i18, point.x);
                    i17 = Math.min(i17, point.y);
                    i15 = Math.max(i15, point.y);
                }
                qf1.c = new Rect(i16, i17, i18, i15);
            }
            Rect rect2 = qf1.c;
            wf1Arr[i13].e = new C4048oK0();
            wf1Arr[i13].e.d = (float) rect2.left;
            wf1Arr[i13].e.e = (float) rect2.top;
            wf1Arr[i13].e.f = (float) rect2.width();
            wf1Arr[i13].e.g = (float) rect2.height();
            Point[] a4 = qf1.a();
            wf1Arr[i13].f = new C4883tE0[a4.length];
            for (int i20 = i2; i20 < a4.length; i20++) {
                wf1Arr[i13].f[i20] = new C4883tE0();
                wf1Arr[i13].f[i20].d = (float) a4[i20].x;
                wf1Arr[i13].f[i20].e = (float) a4[i20].y;
            }
            i13++;
            i = 1;
        }
        dg1.a(wf1Arr);
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
