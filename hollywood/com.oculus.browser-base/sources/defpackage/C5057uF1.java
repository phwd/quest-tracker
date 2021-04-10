package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.face.internal.client.FaceParcel;
import com.google.android.gms.vision.face.internal.client.LandmarkParcel;
import com.google.android.gms.vision.face.internal.client.zza;
import com.oculus.os.Version;

/* renamed from: uF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5057uF1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        LandmarkParcel[] landmarkParcelArr = null;
        zza[] zzaArr = null;
        int i = 0;
        int i2 = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        float f9 = 0.0f;
        float f10 = 0.0f;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 2:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 3:
                    f = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case 4:
                    f2 = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case 5:
                    f3 = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case 6:
                    f4 = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case Version.VERSION_7:
                    f5 = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case Version.VERSION_8:
                    f6 = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case Version.VERSION_9:
                    landmarkParcelArr = (LandmarkParcel[]) AbstractC5588xO0.h(parcel, readInt, LandmarkParcel.CREATOR);
                    break;
                case Version.VERSION_10:
                    f8 = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case Version.VERSION_11:
                    f9 = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case Version.VERSION_12:
                    f10 = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case Version.VERSION_13:
                    zzaArr = (zza[]) AbstractC5588xO0.h(parcel, readInt, zza.CREATOR);
                    break;
                case Version.VERSION_14:
                    f7 = AbstractC5588xO0.n(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new FaceParcel(i, i2, f, f2, f3, f4, f5, f6, f7, landmarkParcelArr, f8, f9, f10, zzaArr);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new FaceParcel[i];
    }
}
