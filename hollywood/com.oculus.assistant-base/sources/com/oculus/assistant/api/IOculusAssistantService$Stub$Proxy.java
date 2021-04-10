package com.oculus.assistant.api;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

public final class IOculusAssistantService$Stub$Proxy implements IOculusAssistantService {
    public IBinder A00;

    public IOculusAssistantService$Stub$Proxy(IBinder iBinder) {
        this.A00 = iBinder;
    }

    @Override // com.oculus.assistant.api.IOculusAssistantService
    public final void A4X(String str, Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.oculus.assistant.api.IOculusAssistantService");
            obtain.writeString(str);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            this.A00.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.oculus.assistant.api.IOculusAssistantService
    public final String A5D(String str, IOculusAssistantSubscriber iOculusAssistantSubscriber) {
        IBinder iBinder;
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.oculus.assistant.api.IOculusAssistantService");
            obtain.writeString(str);
            if (iOculusAssistantSubscriber != null) {
                iBinder = iOculusAssistantSubscriber.asBinder();
            } else {
                iBinder = null;
            }
            obtain.writeStrongBinder(iBinder);
            this.A00.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.oculus.assistant.api.IOculusAssistantService
    public final void A5K(String str, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.oculus.assistant.api.IOculusAssistantService");
            obtain.writeString(str);
            obtain.writeString(str2);
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
