package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.zzae;
import com.google.android.gms.internal.cast.zzdb;
import com.oculus.os.Version;

/* renamed from: HF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HF1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        ApplicationMetadata applicationMetadata = null;
        zzae zzae = null;
        double d = 0.0d;
        double d2 = 0.0d;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    d = AbstractC5588xO0.l(parcel, readInt);
                    break;
                case 3:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case 4:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 5:
                    applicationMetadata = (ApplicationMetadata) AbstractC5588xO0.d(parcel, readInt, ApplicationMetadata.CREATOR);
                    break;
                case 6:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    zzae = (zzae) AbstractC5588xO0.d(parcel, readInt, zzae.CREATOR);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    d2 = AbstractC5588xO0.l(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new zzdb(d, z, i, applicationMetadata, i2, zzae, d2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzdb[i];
    }
}
