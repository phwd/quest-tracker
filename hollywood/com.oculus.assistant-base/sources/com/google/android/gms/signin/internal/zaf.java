package com.google.android.gms.signin.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.internal.base.zab;

public final class zaf extends zab implements zag {
    public zaf(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    @Override // com.google.android.gms.signin.internal.zag
    public final void A6B(zaj zaj, zae zae) {
        IBinder asBinder;
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.A01);
        if (zaj == null) {
            obtain.writeInt(0);
        } else {
            obtain.writeInt(1);
            zaj.writeToParcel(obtain, 0);
        }
        if (zae == null) {
            asBinder = null;
        } else {
            asBinder = zae.asBinder();
        }
        obtain.writeStrongBinder(asBinder);
        Parcel obtain2 = Parcel.obtain();
        try {
            this.A00.transact(12, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain.recycle();
            obtain2.recycle();
        }
    }
}
