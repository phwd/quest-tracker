package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: DA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class DA1 extends Binder implements IInterface {
    public DA1(String str) {
        attachInterface(this, str);
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        boolean z;
        if (i > 16777215) {
            z = super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel.enforceInterface(getInterfaceDescriptor());
            z = false;
        }
        if (z) {
            return true;
        }
        return y0(i, parcel, parcel2, i2);
    }

    public abstract boolean y0(int i, Parcel parcel, Parcel parcel2, int i2);
}
