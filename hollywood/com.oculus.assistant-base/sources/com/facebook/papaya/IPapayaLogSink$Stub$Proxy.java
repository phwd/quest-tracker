package com.facebook.papaya;

import android.os.IBinder;
import android.os.Parcel;

public final class IPapayaLogSink$Stub$Proxy implements IPapayaLogSink {
    public IBinder A00;

    public IPapayaLogSink$Stub$Proxy(IBinder iBinder) {
        this.A00 = iBinder;
    }

    public final IBinder asBinder() {
        return this.A00;
    }

    @Override // com.facebook.papaya.IPapayaLogSink
    public final void event(long j, long j2, long j3, int i, String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.facebook.papaya.IPapayaLogSink");
            obtain.writeLong(j);
            obtain.writeLong(j2);
            obtain.writeLong(j3);
            obtain.writeInt(i);
            obtain.writeString(str);
            this.A00.transact(2, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.facebook.papaya.IPapayaLogSink
    public final void log(long j, long j2, long j3, int i, String str, int i2, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.facebook.papaya.IPapayaLogSink");
            obtain.writeLong(j);
            obtain.writeLong(j2);
            obtain.writeLong(j3);
            obtain.writeInt(i);
            obtain.writeString(str);
            obtain.writeInt(i2);
            obtain.writeString(str2);
            this.A00.transact(1, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
