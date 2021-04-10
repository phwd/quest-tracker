package com.facebook.papaya.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import com.facebook.papaya.IPapayaLogSink;
import java.util.HashMap;
import java.util.Map;

public final class Papaya$LogDelegate extends Binder implements IPapayaLogSink {
    public final Map A00;

    public final IBinder asBinder() {
        return this;
    }

    @Override // com.facebook.papaya.IPapayaLogSink
    public final synchronized void event(long j, long j2, long j3, int i, String str) {
        for (ILogSink iLogSink : this.A00.values()) {
            iLogSink.event(j, j2, j3, i, str);
        }
    }

    @Override // com.facebook.papaya.IPapayaLogSink
    public final synchronized void log(long j, long j2, long j3, int i, String str, int i2, String str2) {
        for (ILogSink iLogSink : this.A00.values()) {
            iLogSink.log(j, j2, j3, i, str, i2, str2);
        }
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("com.facebook.papaya.IPapayaLogSink");
            log(parcel.readLong(), parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString());
        } else if (i == 2) {
            parcel.enforceInterface("com.facebook.papaya.IPapayaLogSink");
            event(parcel.readLong(), parcel.readLong(), parcel.readLong(), parcel.readInt(), parcel.readString());
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("com.facebook.papaya.IPapayaLogSink");
            return true;
        }
        parcel2.writeNoException();
        return true;
    }

    public Papaya$LogDelegate() {
        this(0);
        this.A00 = new HashMap();
    }

    public Papaya$LogDelegate(int i) {
        attachInterface(this, "com.facebook.papaya.IPapayaLogSink");
    }
}
