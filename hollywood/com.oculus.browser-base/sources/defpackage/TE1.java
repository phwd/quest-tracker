package defpackage;

import android.content.Context;
import android.graphics.PointF;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.internal.vision.zzn;
import com.google.android.gms.vision.face.internal.client.FaceParcel;
import com.google.android.gms.vision.face.internal.client.LandmarkParcel;
import com.google.android.gms.vision.face.internal.client.zza;
import com.google.android.gms.vision.face.internal.client.zze;
import java.nio.ByteBuffer;

/* renamed from: TE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class TE1 extends QH1 {
    public final zze h;

    public TE1(Context context, zze zze) {
        super(context, "FaceNativeHandle", "face");
        this.h = zze;
        e();
    }

    @Override // defpackage.QH1
    public final Object b(PJ pj, Context context) {
        AbstractC2161dH1 dh1;
        boolean z = false;
        if (PJ.a(context, "com.google.android.gms.vision.dynamite.face") > PJ.d(context, "com.google.android.gms.vision.dynamite", false)) {
            z = true;
        }
        if (z) {
            dh1 = AbstractBinderC5063uH1.c(pj.b("com.google.android.gms.vision.face.NativeFaceDetectorV2Creator"));
        } else {
            dh1 = AbstractBinderC5063uH1.c(pj.b("com.google.android.gms.vision.face.ChimeraNativeFaceDetectorCreator"));
        }
        SG1 sg1 = null;
        if (dh1 == null) {
            return null;
        }
        BinderC0773Mq0 mq0 = new BinderC0773Mq0(context);
        zze zze = this.h;
        LH1 lh1 = (LH1) dh1;
        Parcel c = lh1.c();
        QE1.a(c, mq0);
        QE1.b(c, zze);
        Parcel d = lh1.d(1, c);
        IBinder readStrongBinder = d.readStrongBinder();
        if (readStrongBinder != null) {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.gms.vision.face.internal.client.INativeFaceDetector");
            if (queryLocalInterface instanceof SG1) {
                sg1 = (SG1) queryLocalInterface;
            } else {
                sg1 = new SG1(readStrongBinder);
            }
        }
        d.recycle();
        return sg1;
    }

    @Override // defpackage.QH1
    public final void c() {
        SG1 sg1 = (SG1) e();
        sg1.f(3, sg1.c());
    }

    public final C4053oN[] f(ByteBuffer byteBuffer, zzn zzn) {
        C4053oN[] oNVarArr;
        FaceParcel[] faceParcelArr;
        C4859t60[] t60Arr;
        CA[] caArr;
        int i = 0;
        if (!a()) {
            return new C4053oN[0];
        }
        try {
            BinderC0773Mq0 mq0 = new BinderC0773Mq0(byteBuffer);
            SG1 sg1 = (SG1) e();
            Parcel c = sg1.c();
            QE1.a(c, mq0);
            QE1.b(c, zzn);
            Parcel d = sg1.d(1, c);
            FaceParcel[] faceParcelArr2 = (FaceParcel[]) d.createTypedArray(FaceParcel.CREATOR);
            d.recycle();
            C4053oN[] oNVarArr2 = new C4053oN[faceParcelArr2.length];
            int i2 = 0;
            while (i2 < faceParcelArr2.length) {
                FaceParcel faceParcel = faceParcelArr2[i2];
                int i3 = faceParcel.G;
                PointF pointF = new PointF(faceParcel.H, faceParcel.I);
                float f = faceParcel.f9684J;
                float f2 = faceParcel.K;
                float f3 = faceParcel.L;
                float f4 = faceParcel.M;
                float f5 = faceParcel.N;
                LandmarkParcel[] landmarkParcelArr = faceParcel.O;
                if (landmarkParcelArr == null) {
                    oNVarArr = oNVarArr2;
                    faceParcelArr = faceParcelArr2;
                    t60Arr = new C4859t60[i];
                } else {
                    t60Arr = new C4859t60[landmarkParcelArr.length];
                    int i4 = i;
                    while (i4 < landmarkParcelArr.length) {
                        LandmarkParcel landmarkParcel = landmarkParcelArr[i4];
                        t60Arr[i4] = new C4859t60(new PointF(landmarkParcel.G, landmarkParcel.H), landmarkParcel.I);
                        i4++;
                        faceParcelArr2 = faceParcelArr2;
                        oNVarArr2 = oNVarArr2;
                        landmarkParcelArr = landmarkParcelArr;
                    }
                    oNVarArr = oNVarArr2;
                    faceParcelArr = faceParcelArr2;
                }
                zza[] zzaArr = faceParcel.S;
                if (zzaArr == null) {
                    caArr = new CA[0];
                } else {
                    CA[] caArr2 = new CA[zzaArr.length];
                    for (int i5 = 0; i5 < zzaArr.length; i5++) {
                        zza zza = zzaArr[i5];
                        caArr2[i5] = new CA(zza.F, zza.G);
                    }
                    caArr = caArr2;
                }
                oNVarArr[i2] = new C4053oN(i3, pointF, f, f2, f3, f4, f5, t60Arr, caArr, faceParcel.P, faceParcel.Q, faceParcel.R);
                i2++;
                faceParcelArr2 = faceParcelArr;
                oNVarArr2 = oNVarArr;
                i = 0;
            }
            return oNVarArr2;
        } catch (RemoteException e) {
            Log.e("FaceNativeHandle", "Could not call native face detector", e);
            return new C4053oN[0];
        }
    }
}
