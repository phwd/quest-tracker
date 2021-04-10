package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;

public final class IGmsServiceBroker$Stub$zza implements IGmsServiceBroker {
    public final IBinder A00;

    public IGmsServiceBroker$Stub$zza(IBinder iBinder) {
        this.A00 = iBinder;
    }

    @Override // com.google.android.gms.common.internal.IGmsServiceBroker
    public final void A2s(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) {
        IBinder iBinder;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
            if (iGmsCallbacks != null) {
                iBinder = iGmsCallbacks.asBinder();
            } else {
                iBinder = null;
            }
            obtain.writeStrongBinder(iBinder);
            if (getServiceRequest != null) {
                obtain.writeInt(1);
                getServiceRequest.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.A00.transact(46, obtain, obtain2, 0);
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
