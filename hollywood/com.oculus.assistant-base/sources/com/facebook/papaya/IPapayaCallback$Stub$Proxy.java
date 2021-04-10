package com.facebook.papaya;

import android.os.IBinder;
import android.os.Parcel;

public final class IPapayaCallback$Stub$Proxy implements IPapayaCallback {
    public IBinder A00;

    public IPapayaCallback$Stub$Proxy(IBinder iBinder) {
        this.A00 = iBinder;
    }

    public final IBinder asBinder() {
        return this.A00;
    }

    @Override // com.facebook.papaya.IPapayaCallback
    public final void onExecutorComplete(String str) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.facebook.papaya.IPapayaCallback");
            obtain.writeString(str);
            this.A00.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
