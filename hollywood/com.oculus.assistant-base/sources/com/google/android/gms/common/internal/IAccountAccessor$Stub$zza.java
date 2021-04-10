package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.common.zzb;

public final class IAccountAccessor$Stub$zza extends zzb implements IAccountAccessor {
    @Override // com.google.android.gms.common.internal.IAccountAccessor
    public final Account A6F() {
        Parcelable parcelable;
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.A01);
        obtain = Parcel.obtain();
        try {
            this.A00.transact(2, obtain, obtain, 0);
            obtain.readException();
            obtain.recycle();
            Parcelable.Creator creator = Account.CREATOR;
            if (obtain.readInt() == 0) {
                parcelable = null;
            } else {
                parcelable = (Parcelable) creator.createFromParcel(obtain);
            }
            return (Account) parcelable;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            obtain.recycle();
        }
    }

    public IAccountAccessor$Stub$zza(IBinder iBinder) {
        super(iBinder);
    }
}
