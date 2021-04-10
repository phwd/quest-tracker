package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;

/* renamed from: VG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class VG1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        PublicKeyCredentialCreationOptions publicKeyCredentialCreationOptions = null;
        Uri uri = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                publicKeyCredentialCreationOptions = (PublicKeyCredentialCreationOptions) AbstractC5588xO0.d(parcel, readInt, PublicKeyCredentialCreationOptions.CREATOR);
            } else if (i != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                uri = (Uri) AbstractC5588xO0.d(parcel, readInt, Uri.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new BrowserPublicKeyCredentialCreationOptions(publicKeyCredentialCreationOptions, uri);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new BrowserPublicKeyCredentialCreationOptions[i];
    }
}
