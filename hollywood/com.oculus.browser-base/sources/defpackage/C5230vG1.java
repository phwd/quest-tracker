package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.vision.zze;

/* renamed from: vG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5230vG1 extends QH1 {
    public final zze h;

    public C5230vG1(Context context, zze zze) {
        super(context, "BarcodeNativeHandle", "barcode");
        this.h = zze;
        e();
    }

    @Override // defpackage.QH1
    public final Object b(PJ pj, Context context) {
        C4552rH1 rh1;
        IBinder b = pj.b("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator");
        C1639aH1 ah1 = null;
        if (b == null) {
            rh1 = null;
        } else {
            IInterface queryLocalInterface = b.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
            if (queryLocalInterface instanceof C4552rH1) {
                rh1 = (C4552rH1) queryLocalInterface;
            } else {
                rh1 = new C4552rH1(b);
            }
        }
        if (rh1 == null) {
            return null;
        }
        BinderC0773Mq0 mq0 = new BinderC0773Mq0(context);
        zze zze = this.h;
        Parcel c = rh1.c();
        QE1.a(c, mq0);
        QE1.b(c, zze);
        Parcel d = rh1.d(1, c);
        IBinder readStrongBinder = d.readStrongBinder();
        if (readStrongBinder != null) {
            IInterface queryLocalInterface2 = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetector");
            if (queryLocalInterface2 instanceof C1639aH1) {
                ah1 = (C1639aH1) queryLocalInterface2;
            } else {
                ah1 = new C1639aH1(readStrongBinder);
            }
        }
        d.recycle();
        return ah1;
    }

    @Override // defpackage.QH1
    public final void c() {
        if (a()) {
            C1639aH1 ah1 = (C1639aH1) e();
            ah1.f(3, ah1.c());
        }
    }
}
