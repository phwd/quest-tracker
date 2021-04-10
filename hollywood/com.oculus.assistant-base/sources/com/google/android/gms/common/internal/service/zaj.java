package com.google.android.gms.common.internal.service;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.zaaa;
import com.google.android.gms.internal.base.zab;

public final class zaj extends zab implements zak {
    public zaj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.IClientTelemetryService");
    }

    @Override // com.google.android.gms.common.internal.service.zak
    public final void A68(zaaa zaaa) {
        Parcel obtain = Parcel.obtain();
        obtain.writeInterfaceToken(this.A01);
        if (zaaa == null) {
            obtain.writeInt(0);
        } else {
            obtain.writeInt(1);
            zaaa.writeToParcel(obtain, 0);
        }
        try {
            this.A00.transact(1, obtain, null, 1);
        } finally {
            obtain.recycle();
        }
    }
}
