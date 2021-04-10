package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.AuthenticationExtensionsClientOutputs;
import com.google.android.gms.fido.fido2.api.common.UvmEntries;

/* renamed from: ZE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ZE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        UvmEntries uvmEntries = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            if ((65535 & readInt) != 1) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                uvmEntries = (UvmEntries) AbstractC5588xO0.d(parcel, readInt, UvmEntries.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new AuthenticationExtensionsClientOutputs(uvmEntries);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new AuthenticationExtensionsClientOutputs[i];
    }
}
