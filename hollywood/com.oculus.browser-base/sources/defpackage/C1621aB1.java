package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Scope;
import com.oculus.os.Version;
import java.util.ArrayList;

/* renamed from: aB1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1621aB1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        Uri uri = null;
        String str5 = null;
        String str6 = null;
        ArrayList arrayList = null;
        String str7 = null;
        String str8 = null;
        int i = 0;
        long j = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 2:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 3:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 4:
                    str3 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 5:
                    str4 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 6:
                    uri = (Uri) AbstractC5588xO0.d(parcel, readInt, Uri.CREATOR);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    str5 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    j = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    str6 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                    arrayList = AbstractC5588xO0.i(parcel, readInt, Scope.CREATOR);
                    break;
                case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                    str7 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                    str8 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new GoogleSignInAccount(i, str, str2, str3, str4, uri, str5, j, str6, arrayList, str7, str8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}
