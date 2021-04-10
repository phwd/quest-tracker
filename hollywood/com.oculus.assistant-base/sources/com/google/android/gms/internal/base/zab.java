package com.google.android.gms.internal.base;

import android.os.IBinder;
import android.os.IInterface;

public class zab implements IInterface {
    public final IBinder A00;
    public final String A01;

    public zab(IBinder iBinder, String str) {
        this.A00 = iBinder;
        this.A01 = str;
    }

    public final IBinder asBinder() {
        return this.A00;
    }
}
