package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.BrowserPublicKeyCredentialRequestOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions;

/* renamed from: gH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2673gH1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        PublicKeyCredentialRequestOptions publicKeyCredentialRequestOptions = null;
        Uri uri = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i = 65535 & readInt;
            if (i == 2) {
                publicKeyCredentialRequestOptions = (PublicKeyCredentialRequestOptions) AbstractC5588xO0.d(parcel, readInt, PublicKeyCredentialRequestOptions.CREATOR);
            } else if (i != 3) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                uri = (Uri) AbstractC5588xO0.d(parcel, readInt, Uri.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new BrowserPublicKeyCredentialRequestOptions(publicKeyCredentialRequestOptions, uri);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new BrowserPublicKeyCredentialRequestOptions[i];
    }
}
