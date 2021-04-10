package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.internal.vision.zzal;

/* renamed from: sD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4711sD1 extends QH1 {
    public final zzal h;

    public C4711sD1(Context context, zzal zzal) {
        super(context, "TextNativeHandle", "text");
        this.h = zzal;
        e();
    }

    @Override // defpackage.QH1
    public final Object b(PJ pj, Context context) {
        NC1 nc1;
        IBinder b = pj.b("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        BC1 bc1 = null;
        if (b == null) {
            nc1 = null;
        } else {
            IInterface queryLocalInterface = b.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            if (queryLocalInterface instanceof NC1) {
                nc1 = (NC1) queryLocalInterface;
            } else {
                nc1 = new NC1(b);
            }
        }
        if (nc1 == null) {
            return null;
        }
        BinderC0773Mq0 mq0 = new BinderC0773Mq0(context);
        zzal zzal = this.h;
        Parcel c = nc1.c();
        QE1.a(c, mq0);
        QE1.b(c, zzal);
        Parcel d = nc1.d(1, c);
        IBinder readStrongBinder = d.readStrongBinder();
        if (readStrongBinder != null) {
            IInterface queryLocalInterface2 = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizer");
            if (queryLocalInterface2 instanceof BC1) {
                bc1 = (BC1) queryLocalInterface2;
            } else {
                bc1 = new BC1(readStrongBinder);
            }
        }
        d.recycle();
        return bc1;
    }

    @Override // defpackage.QH1
    public final void c() {
        BC1 bc1 = (BC1) e();
        bc1.f(2, bc1.c());
    }
}
