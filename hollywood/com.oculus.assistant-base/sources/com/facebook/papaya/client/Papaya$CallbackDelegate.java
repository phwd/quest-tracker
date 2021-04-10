package com.facebook.papaya.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import com.facebook.papaya.IPapayaCallback;

public final class Papaya$CallbackDelegate extends Binder implements IPapayaCallback {
    public final IBinder asBinder() {
        return this;
    }

    @Override // com.facebook.papaya.IPapayaCallback
    public final synchronized void onExecutorComplete(String str) {
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("com.facebook.papaya.IPapayaCallback");
            onExecutorComplete(parcel.readString());
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("com.facebook.papaya.IPapayaCallback");
            return true;
        }
    }

    public Papaya$CallbackDelegate() {
        this(0);
    }

    public Papaya$CallbackDelegate(int i) {
        attachInterface(this, "com.facebook.papaya.IPapayaCallback");
    }
}
