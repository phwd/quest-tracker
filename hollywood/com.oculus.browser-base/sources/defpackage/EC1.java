package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.zzad;

/* renamed from: EC1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class EC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        try {
            return zzad.b(parcel.readString());
        } catch (QC1 e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new zzad[i];
    }
}
