package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: nZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC3906nZ extends Binder implements IInterface {
    public AbstractBinderC3906nZ() {
        attachInterface(this, "android.support.customtabs.trusted.ITrustedWebActivityCallback");
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 2) {
            parcel.enforceInterface("android.support.customtabs.trusted.ITrustedWebActivityCallback");
            ((BinderC2927ho1) this).f10103a.a(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
            parcel2.writeNoException();
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("android.support.customtabs.trusted.ITrustedWebActivityCallback");
            return true;
        }
    }
}
