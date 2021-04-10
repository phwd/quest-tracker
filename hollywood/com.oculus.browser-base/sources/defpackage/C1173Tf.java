package defpackage;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.internal.vision.zzn;
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;

/* renamed from: Tf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1173Tf extends AbstractC4712sE {
    public final C5230vG1 b;

    public C1173Tf(C5230vG1 vg1, SE1 se1) {
        this.b = vg1;
    }

    @Override // defpackage.AbstractC4712sE
    public final void a() {
        super.a();
        this.b.d();
    }

    public final SparseArray b(GT gt) {
        Barcode[] barcodeArr;
        zzn x = zzn.x(gt);
        Bitmap bitmap = gt.c;
        if (bitmap != null) {
            C5230vG1 vg1 = this.b;
            if (!vg1.a()) {
                barcodeArr = new Barcode[0];
            } else {
                try {
                    BinderC0773Mq0 mq0 = new BinderC0773Mq0(bitmap);
                    C1639aH1 ah1 = (C1639aH1) vg1.e();
                    Parcel c = ah1.c();
                    QE1.a(c, mq0);
                    QE1.b(c, x);
                    Parcel d = ah1.d(2, c);
                    Barcode[] barcodeArr2 = (Barcode[]) d.createTypedArray(Barcode.CREATOR);
                    d.recycle();
                    barcodeArr = barcodeArr2;
                } catch (RemoteException e) {
                    Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
                    barcodeArr = new Barcode[0];
                }
            }
            if (barcodeArr == null) {
                throw new IllegalArgumentException("Internal barcode detector error; check logcat output.");
            }
        } else {
            ByteBuffer a2 = gt.a();
            C5230vG1 vg12 = this.b;
            if (!vg12.a()) {
                barcodeArr = new Barcode[0];
            } else {
                try {
                    BinderC0773Mq0 mq02 = new BinderC0773Mq0(a2);
                    C1639aH1 ah12 = (C1639aH1) vg12.e();
                    Parcel c2 = ah12.c();
                    QE1.a(c2, mq02);
                    QE1.b(c2, x);
                    Parcel d2 = ah12.d(1, c2);
                    Barcode[] barcodeArr3 = (Barcode[]) d2.createTypedArray(Barcode.CREATOR);
                    d2.recycle();
                    barcodeArr = barcodeArr3;
                } catch (RemoteException e2) {
                    Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e2);
                    barcodeArr = new Barcode[0];
                }
            }
        }
        SparseArray sparseArray = new SparseArray(barcodeArr.length);
        for (Barcode barcode : barcodeArr) {
            sparseArray.append(barcode.G.hashCode(), barcode);
        }
        return sparseArray;
    }
}
