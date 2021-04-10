package com.google.android.gms.internal.common;

import X.RO;
import X.RZ;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.BaseGmsClient$zze;
import com.google.android.gms.common.internal.zzc;

public class zza extends Binder implements IInterface {
    public final IBinder asBinder() {
        return this;
    }

    public boolean A01(int i, Parcel parcel, Parcel parcel2, int i2) {
        Parcelable parcelable;
        Parcelable parcelable2;
        if (!(this instanceof BaseGmsClient$zze)) {
            return false;
        }
        BaseGmsClient$zze baseGmsClient$zze = (BaseGmsClient$zze) this;
        if (i == 1) {
            int readInt = parcel.readInt();
            IBinder readStrongBinder = parcel.readStrongBinder();
            Parcelable.Creator creator = Bundle.CREATOR;
            if (parcel.readInt() == 0) {
                parcelable = null;
            } else {
                parcelable = (Parcelable) creator.createFromParcel(parcel);
            }
            baseGmsClient$zze.A02(readInt, readStrongBinder, (Bundle) parcelable);
        } else if (i == 2) {
            parcel.readInt();
            Parcelable.Creator creator2 = Bundle.CREATOR;
            if (parcel.readInt() != 0) {
                creator2.createFromParcel(parcel);
            }
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        } else if (i != 3) {
            return false;
        } else {
            int readInt2 = parcel.readInt();
            IBinder readStrongBinder2 = parcel.readStrongBinder();
            Parcelable.Creator creator3 = zzc.CREATOR;
            if (parcel.readInt() == 0) {
                parcelable2 = null;
            } else {
                parcelable2 = (Parcelable) creator3.createFromParcel(parcel);
            }
            zzc zzc = (zzc) parcelable2;
            RO ro = baseGmsClient$zze.A00;
            RZ.A02(ro, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            RZ.A01(zzc);
            ro.A0L = zzc;
            baseGmsClient$zze.A02(readInt2, readStrongBinder2, zzc.A01);
        }
        parcel2.writeNoException();
        return true;
    }

    public zza(String str) {
        attachInterface(this, str);
    }

    @Override // android.os.Binder
    public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i <= 16777215) {
            parcel.enforceInterface(getInterfaceDescriptor());
        } else if (super.onTransact(i, parcel, parcel2, i2)) {
            return true;
        }
        return A01(i, parcel, parcel2, i2);
    }
}
