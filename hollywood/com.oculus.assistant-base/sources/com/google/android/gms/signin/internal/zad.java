package com.google.android.gms.signin.internal;

import X.SO;
import android.os.Parcel;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zaa;
import com.oculus.aidl.OVRServiceInterface;

public abstract class zad extends zaa implements zae {
    @Override // com.google.android.gms.internal.base.zaa
    public final boolean A00(int i, Parcel parcel, Parcel parcel2, int i2) {
        switch (i) {
            case 3:
                A64((ConnectionResult) SO.A00(parcel, ConnectionResult.CREATOR), (zaa) SO.A00(parcel, zaa.CREATOR));
                break;
            case 4:
                A65((Status) SO.A00(parcel, Status.CREATOR));
                break;
            case 5:
            default:
                return false;
            case 6:
                A6E((Status) SO.A00(parcel, Status.CREATOR));
                break;
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                A66((Status) SO.A00(parcel, Status.CREATOR), (GoogleSignInAccount) SO.A00(parcel, GoogleSignInAccount.CREATOR));
                break;
            case 8:
                A6C((zak) SO.A00(parcel, zak.CREATOR));
                break;
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                A6A((zai) SO.A00(parcel, zai.CREATOR));
                break;
        }
        parcel2.writeNoException();
        return true;
    }
}
