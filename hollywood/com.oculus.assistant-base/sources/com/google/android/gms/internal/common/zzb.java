package com.google.android.gms.internal.common;

import android.os.IBinder;
import android.os.IInterface;

public class zzb implements IInterface {
    public final IBinder A00;
    public final String A01 = "com.google.android.gms.common.internal.IAccountAccessor";

    public zzb(IBinder iBinder) {
        this.A00 = iBinder;
    }

    public final IBinder asBinder() {
        return this.A00;
    }
}
