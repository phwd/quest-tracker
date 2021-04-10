package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.common.zza;

public abstract class zzo extends zza implements zzm {
    @Override // com.google.android.gms.internal.common.zza
    public final boolean A01(int i, Parcel parcel, Parcel parcel2, int i2) {
        IBinder asBinder;
        if (i == 1) {
            IObjectWrapper A6H = A6H();
            parcel2.writeNoException();
            if (A6H == null) {
                asBinder = null;
            } else {
                asBinder = A6H.asBinder();
            }
            parcel2.writeStrongBinder(asBinder);
            return true;
        } else if (i != 2) {
            return false;
        } else {
            int A6I = A6I();
            parcel2.writeNoException();
            parcel2.writeInt(A6I);
            return true;
        }
    }

    public zzo() {
        super("com.google.android.gms.common.internal.ICertData");
    }
}
