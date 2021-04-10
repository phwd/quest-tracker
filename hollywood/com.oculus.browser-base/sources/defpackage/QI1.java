package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.fido.fido2.api.common.AuthenticationExtensions;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialDescriptor;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialRequestOptions;
import com.google.android.gms.fido.fido2.api.common.TokenBinding;
import com.oculus.os.Version;
import java.util.ArrayList;

/* renamed from: QI1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class QI1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        byte[] bArr = null;
        Double d = null;
        String str = null;
        ArrayList arrayList = null;
        Integer num = null;
        TokenBinding tokenBinding = null;
        String str2 = null;
        AuthenticationExtensions authenticationExtensions = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    bArr = AbstractC5588xO0.b(parcel, readInt);
                    break;
                case 3:
                    d = AbstractC5588xO0.m(parcel, readInt);
                    break;
                case 4:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 5:
                    arrayList = AbstractC5588xO0.i(parcel, readInt, PublicKeyCredentialDescriptor.CREATOR);
                    break;
                case 6:
                    num = AbstractC5588xO0.q(parcel, readInt);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    tokenBinding = (TokenBinding) AbstractC5588xO0.d(parcel, readInt, TokenBinding.CREATOR);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    authenticationExtensions = (AuthenticationExtensions) AbstractC5588xO0.d(parcel, readInt, AuthenticationExtensions.CREATOR);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new PublicKeyCredentialRequestOptions(bArr, d, str, arrayList, num, tokenBinding, str2, authenticationExtensions);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new PublicKeyCredentialRequestOptions[i];
    }
}
