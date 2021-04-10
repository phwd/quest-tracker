package defpackage;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GetServiceRequest;
import com.oculus.os.Version;

/* renamed from: zF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5907zF1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        int i4 = 0;
        String str = null;
        IBinder iBinder = null;
        Scope[] scopeArr = null;
        Bundle bundle = null;
        Account account = null;
        Feature[] featureArr = null;
        Feature[] featureArr2 = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 2:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 3:
                    i3 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 4:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 5:
                    iBinder = AbstractC5588xO0.o(parcel, readInt);
                    break;
                case 6:
                    scopeArr = (Scope[]) AbstractC5588xO0.h(parcel, readInt, Scope.CREATOR);
                    break;
                case Version.VERSION_7:
                    bundle = AbstractC5588xO0.a(parcel, readInt);
                    break;
                case Version.VERSION_8:
                    account = (Account) AbstractC5588xO0.d(parcel, readInt, Account.CREATOR);
                    break;
                case Version.VERSION_9:
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
                case Version.VERSION_10:
                    featureArr = (Feature[]) AbstractC5588xO0.h(parcel, readInt, Feature.CREATOR);
                    break;
                case Version.VERSION_11:
                    featureArr2 = (Feature[]) AbstractC5588xO0.h(parcel, readInt, Feature.CREATOR);
                    break;
                case Version.VERSION_12:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case Version.VERSION_13:
                    i4 = AbstractC5588xO0.p(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new GetServiceRequest(i, i2, i3, str, iBinder, scopeArr, bundle, account, featureArr, featureArr2, z, i4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new GetServiceRequest[i];
    }
}
