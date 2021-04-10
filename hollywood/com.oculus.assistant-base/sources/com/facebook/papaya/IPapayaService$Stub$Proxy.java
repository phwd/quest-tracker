package com.facebook.papaya;

import android.os.IBinder;
import android.os.Parcel;

public final class IPapayaService$Stub$Proxy implements IPapayaService {
    public IBinder A00;

    public IPapayaService$Stub$Proxy(IBinder iBinder) {
        this.A00 = iBinder;
    }

    @Override // com.facebook.papaya.IPapayaService
    public final void A1P(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.facebook.papaya.IPapayaService");
            obtain.writeString(str);
            this.A00.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.facebook.papaya.IPapayaService
    public final boolean A5C(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.facebook.papaya.IPapayaService");
            obtain.writeString(str);
            boolean z = false;
            this.A00.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                z = true;
            }
            return z;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.A00;
    }

    @Override // com.facebook.papaya.IPapayaService
    public final void run() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.facebook.papaya.IPapayaService");
            this.A00.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.facebook.papaya.IPapayaService
    public final void stop() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.facebook.papaya.IPapayaService");
            this.A00.transact(5, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
