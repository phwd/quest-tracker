package X;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GetServiceRequest;
import com.oculus.aidl.OVRServiceInterface;

/* renamed from: X.Ru  reason: case insensitive filesystem */
public final class C0339Ru implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i;
        int i2;
        int A00 = C0326Rd.A00(parcel);
        String str = null;
        IBinder iBinder = null;
        Scope[] scopeArr = null;
        Bundle bundle = null;
        Account account = null;
        Feature[] featureArr = null;
        Feature[] featureArr2 = null;
        String str2 = null;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        boolean z = false;
        int i6 = 0;
        boolean z2 = false;
        while (parcel.dataPosition() < A00) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i3 = C0326Rd.A01(parcel, readInt);
                    continue;
                case 2:
                    i4 = C0326Rd.A01(parcel, readInt);
                    continue;
                case 3:
                    i5 = C0326Rd.A01(parcel, readInt);
                    continue;
                case 4:
                    str = C0326Rd.A04(parcel, readInt);
                    continue;
                case 5:
                    i = C0326Rd.A02(parcel, readInt);
                    i2 = parcel.dataPosition();
                    if (i != 0) {
                        iBinder = parcel.readStrongBinder();
                        break;
                    } else {
                        iBinder = null;
                        continue;
                    }
                case 6:
                    scopeArr = (Scope[]) C0326Rd.A09(parcel, readInt, Scope.CREATOR);
                    continue;
                case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                    i = C0326Rd.A02(parcel, readInt);
                    i2 = parcel.dataPosition();
                    if (i != 0) {
                        bundle = parcel.readBundle();
                        break;
                    } else {
                        bundle = null;
                        continue;
                    }
                case 8:
                    account = (Account) C0326Rd.A03(parcel, readInt, Account.CREATOR);
                    continue;
                case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                default:
                    C0326Rd.A06(parcel, readInt);
                    continue;
                case 10:
                    featureArr = (Feature[]) C0326Rd.A09(parcel, readInt, Feature.CREATOR);
                    continue;
                case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                    featureArr2 = (Feature[]) C0326Rd.A09(parcel, readInt, Feature.CREATOR);
                    continue;
                case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipPassthrough /*{ENCODED_INT: 12}*/:
                    z = C0326Rd.A08(parcel, readInt);
                    continue;
                case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipMicrophoneMuted /*{ENCODED_INT: 13}*/:
                    i6 = C0326Rd.A01(parcel, readInt);
                    continue;
                case OVRServiceInterface.Stub.TRANSACTION_setSystemVoipSuppressed /*{ENCODED_INT: 14}*/:
                    z2 = C0326Rd.A08(parcel, readInt);
                    continue;
                case OVRServiceInterface.Stub.TRANSACTION_getSystemVoipData /*{ENCODED_INT: 15}*/:
                    str2 = C0326Rd.A04(parcel, readInt);
                    continue;
            }
            parcel.setDataPosition(i2 + i);
        }
        C0326Rd.A05(parcel, A00);
        return new GetServiceRequest(i3, i4, i5, str, iBinder, scopeArr, bundle, account, featureArr, featureArr2, z, i6, z2, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
