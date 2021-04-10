package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.AuthenticationExtensionsClientOutputs;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAssertionResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorAttestationResponse;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorErrorResponse;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;
import com.oculus.os.Version;

/* renamed from: zI1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5916zI1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        AuthenticatorAttestationResponse authenticatorAttestationResponse = null;
        AuthenticatorAssertionResponse authenticatorAssertionResponse = null;
        AuthenticatorErrorResponse authenticatorErrorResponse = null;
        AuthenticationExtensionsClientOutputs authenticationExtensionsClientOutputs = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 2:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 3:
                    bArr = AbstractC5588xO0.b(parcel, readInt);
                    break;
                case 4:
                    authenticatorAttestationResponse = (AuthenticatorAttestationResponse) AbstractC5588xO0.d(parcel, readInt, AuthenticatorAttestationResponse.CREATOR);
                    break;
                case 5:
                    authenticatorAssertionResponse = (AuthenticatorAssertionResponse) AbstractC5588xO0.d(parcel, readInt, AuthenticatorAssertionResponse.CREATOR);
                    break;
                case 6:
                    authenticatorErrorResponse = (AuthenticatorErrorResponse) AbstractC5588xO0.d(parcel, readInt, AuthenticatorErrorResponse.CREATOR);
                    break;
                case Version.VERSION_7:
                    authenticationExtensionsClientOutputs = (AuthenticationExtensionsClientOutputs) AbstractC5588xO0.d(parcel, readInt, AuthenticationExtensionsClientOutputs.CREATOR);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new PublicKeyCredential(str, str2, bArr, authenticatorAttestationResponse, authenticatorAssertionResponse, authenticatorErrorResponse, authenticationExtensionsClientOutputs);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new PublicKeyCredential[i];
    }
}
