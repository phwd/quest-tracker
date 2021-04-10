package com.facebook.mobileconfigservice.service;

import android.os.IBinder;
import android.os.Parcel;

public final class IMobileConfig$Stub$Proxy implements IMobileConfig {
    public IBinder A00;

    public IMobileConfig$Stub$Proxy(IBinder iBinder) {
        this.A00 = iBinder;
    }

    @Override // com.facebook.mobileconfigservice.service.IMobileConfig
    public final void A5E(String str, String str2, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.facebook.mobileconfigservice.service.IMobileConfig");
            obtain.writeString(str);
            obtain.writeString(str2);
            obtain.writeInt(i);
            this.A00.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.A00;
    }
}
