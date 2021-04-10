package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions;
import com.google.android.gms.fido.fido2.api.common.AuthenticatorSelectionCriteria;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialParameters;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRpEntity;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialUserEntity;
import com.google.android.gms.fido.fido2.api.common.TokenBinding;
import com.oculus.os.Version;
import java.util.ArrayList;

/* renamed from: sI1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4726sI1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        PublicKeyCredentialRpEntity publicKeyCredentialRpEntity = null;
        PublicKeyCredentialUserEntity publicKeyCredentialUserEntity = null;
        byte[] bArr = null;
        ArrayList arrayList = null;
        Double d = null;
        ArrayList arrayList2 = null;
        AuthenticatorSelectionCriteria authenticatorSelectionCriteria = null;
        Integer num = null;
        TokenBinding tokenBinding = null;
        String str = null;
        AuthenticationExtensions authenticationExtensions = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    publicKeyCredentialRpEntity = (PublicKeyCredentialRpEntity) AbstractC5588xO0.d(parcel, readInt, PublicKeyCredentialRpEntity.CREATOR);
                    break;
                case 3:
                    publicKeyCredentialUserEntity = (PublicKeyCredentialUserEntity) AbstractC5588xO0.d(parcel, readInt, PublicKeyCredentialUserEntity.CREATOR);
                    break;
                case 4:
                    bArr = AbstractC5588xO0.b(parcel, readInt);
                    break;
                case 5:
                    arrayList = AbstractC5588xO0.i(parcel, readInt, PublicKeyCredentialParameters.CREATOR);
                    break;
                case 6:
                    d = AbstractC5588xO0.m(parcel, readInt);
                    break;
                case Version.VERSION_7:
                    arrayList2 = AbstractC5588xO0.i(parcel, readInt, PublicKeyCredentialDescriptor.CREATOR);
                    break;
                case Version.VERSION_8:
                    authenticatorSelectionCriteria = (AuthenticatorSelectionCriteria) AbstractC5588xO0.d(parcel, readInt, AuthenticatorSelectionCriteria.CREATOR);
                    break;
                case Version.VERSION_9:
                    num = AbstractC5588xO0.q(parcel, readInt);
                    break;
                case Version.VERSION_10:
                    tokenBinding = (TokenBinding) AbstractC5588xO0.d(parcel, readInt, TokenBinding.CREATOR);
                    break;
                case Version.VERSION_11:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_12:
                    authenticationExtensions = (AuthenticationExtensions) AbstractC5588xO0.d(parcel, readInt, AuthenticationExtensions.CREATOR);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new PublicKeyCredentialCreationOptions(publicKeyCredentialRpEntity, publicKeyCredentialUserEntity, bArr, arrayList, d, arrayList2, authenticatorSelectionCriteria, num, tokenBinding, str, authenticationExtensions);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new PublicKeyCredentialCreationOptions[i];
    }
}
