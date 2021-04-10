package com.google.android.gms.common.internal;

import X.C0327Re;
import X.C0339Ru;
import android.accounts.Account;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class GetServiceRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C0339Ru();
    public Account A00;
    public Bundle A01;
    public IBinder A02;
    public String A03;
    public boolean A04;
    public Feature[] A05;
    public Feature[] A06;
    public Scope[] A07;
    public int A08;
    public int A09;
    public boolean A0A;
    public final int A0B;
    public final int A0C;
    public final String A0D;

    public final void writeToParcel(Parcel parcel, int i) {
        int A002 = C0327Re.A00(parcel, 20293);
        C0327Re.A02(parcel, 1, this.A0B);
        C0327Re.A02(parcel, 2, this.A0C);
        C0327Re.A02(parcel, 3, this.A08);
        C0327Re.A05(parcel, 4, this.A03);
        IBinder iBinder = this.A02;
        if (iBinder != null) {
            int A003 = C0327Re.A00(parcel, 5);
            parcel.writeStrongBinder(iBinder);
            C0327Re.A01(parcel, A003);
        }
        C0327Re.A07(parcel, 6, this.A07, i);
        Bundle bundle = this.A01;
        if (bundle != null) {
            int A004 = C0327Re.A00(parcel, 7);
            parcel.writeBundle(bundle);
            C0327Re.A01(parcel, A004);
        }
        C0327Re.A04(parcel, 8, this.A00, i);
        C0327Re.A07(parcel, 10, this.A05, i);
        C0327Re.A07(parcel, 11, this.A06, i);
        boolean z = this.A0A;
        C0327Re.A03(parcel, 12, 4);
        parcel.writeInt(z ? 1 : 0);
        C0327Re.A02(parcel, 13, this.A09);
        boolean z2 = this.A04;
        C0327Re.A03(parcel, 14, 4);
        parcel.writeInt(z2 ? 1 : 0);
        C0327Re.A05(parcel, 15, this.A0D);
        C0327Re.A01(parcel, A002);
    }

    public GetServiceRequest(int i, int i2, int i3, String str, IBinder iBinder, Scope[] scopeArr, Bundle bundle, Account account, Feature[] featureArr, Feature[] featureArr2, boolean z, int i4, boolean z2, String str2) {
        IAccountAccessor iAccountAccessor$Stub$zza;
        this.A0B = i;
        this.A0C = i2;
        this.A08 = i3;
        if ("com.google.android.gms".equals(str)) {
            this.A03 = "com.google.android.gms";
        } else {
            this.A03 = str;
        }
        if (i < 2) {
            Account account2 = null;
            if (iBinder != null) {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
                if (queryLocalInterface instanceof IAccountAccessor) {
                    iAccountAccessor$Stub$zza = (IAccountAccessor) queryLocalInterface;
                } else {
                    iAccountAccessor$Stub$zza = new IAccountAccessor$Stub$zza(iBinder);
                }
                if (iAccountAccessor$Stub$zza != null) {
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        account2 = iAccountAccessor$Stub$zza.A6F();
                    } catch (RemoteException unused) {
                        Log.w("AccountAccessor", "Remote account accessor probably died");
                    } finally {
                        Binder.restoreCallingIdentity(clearCallingIdentity);
                    }
                }
                account2 = null;
            }
            this.A00 = account2;
        } else {
            this.A02 = iBinder;
            this.A00 = account;
        }
        this.A07 = scopeArr;
        this.A01 = bundle;
        this.A05 = featureArr;
        this.A06 = featureArr2;
        this.A0A = z;
        this.A09 = i4;
        this.A04 = z2;
        this.A0D = str2;
    }

    public GetServiceRequest(int i, String str) {
        this.A0B = 6;
        this.A08 = 12451000;
        this.A0C = i;
        this.A0A = true;
        this.A0D = str;
    }
}
