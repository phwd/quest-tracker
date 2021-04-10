package X;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Scope;
import com.oculus.aidl.OVRServiceInterface;
import java.util.ArrayList;

public final class QN implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int A00 = C0326Rd.A00(parcel);
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
        long j = 0;
        int i = 0;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = C0326Rd.A01(parcel, readInt);
                    break;
                case 2:
                    str = C0326Rd.A04(parcel, readInt);
                    break;
                case 3:
                    str2 = C0326Rd.A04(parcel, readInt);
                    break;
                case 4:
                    str3 = C0326Rd.A04(parcel, readInt);
                    break;
                case 5:
                    str4 = C0326Rd.A04(parcel, readInt);
                    break;
                case 6:
                    uri = (Uri) C0326Rd.A03(parcel, readInt, Uri.CREATOR);
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                    str5 = C0326Rd.A04(parcel, readInt);
                    break;
                case 8:
                    C0326Rd.A07(parcel, readInt, 8);
                    j = parcel.readLong();
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                    str6 = C0326Rd.A04(parcel, readInt);
                    break;
                case 10:
                    Parcelable.Creator creator = Scope.CREATOR;
                    int A02 = C0326Rd.A02(parcel, readInt);
                    int dataPosition = parcel.dataPosition();
                    if (A02 != 0) {
                        arrayList = parcel.createTypedArrayList(creator);
                        parcel.setDataPosition(dataPosition + A02);
                        break;
                    } else {
                        arrayList = null;
                        break;
                    }
                case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                    str7 = C0326Rd.A04(parcel, readInt);
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                    str8 = C0326Rd.A04(parcel, readInt);
                    break;
                default:
                    C0326Rd.A06(parcel, readInt);
                    break;
            }
        }
        C0326Rd.A05(parcel, A00);
        return new GoogleSignInAccount(i, str, str2, str3, str4, uri, str5, j, str6, arrayList, str7, str8);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GoogleSignInAccount[i];
    }
}
