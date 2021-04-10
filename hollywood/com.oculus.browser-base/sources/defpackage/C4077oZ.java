package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: oZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4077oZ implements AbstractC4419qZ {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f10558a;

    public C4077oZ(IBinder iBinder) {
        this.f10558a = iBinder;
    }

    public IBinder asBinder() {
        return this.f10558a;
    }

    public Bundle c(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
            obtain.writeInt(1);
            bundle.writeToParcel(obtain, 0);
            if (!this.f10558a.transact(6, obtain, obtain2, 0)) {
                int i = AbstractBinderC4248pZ.f11073a;
            }
            obtain2.readException();
            return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public void d(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
            obtain.writeInt(1);
            bundle.writeToParcel(obtain, 0);
            if (!this.f10558a.transact(3, obtain, obtain2, 0)) {
                int i = AbstractBinderC4248pZ.f11073a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public Bundle e0() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
            if (!this.f10558a.transact(7, obtain, obtain2, 0)) {
                int i = AbstractBinderC4248pZ.f11073a;
            }
            obtain2.readException();
            return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public Bundle f(String str, Bundle bundle, IBinder iBinder) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
            obtain.writeString(str);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            obtain.writeStrongBinder(iBinder);
            if (!this.f10558a.transact(9, obtain, obtain2, 0)) {
                int i = AbstractBinderC4248pZ.f11073a;
            }
            obtain2.readException();
            return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public int y0() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
            if (!this.f10558a.transact(4, obtain, obtain2, 0)) {
                int i = AbstractBinderC4248pZ.f11073a;
            }
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public Bundle z0(Bundle bundle) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.customtabs.trusted.ITrustedWebActivityService");
            obtain.writeInt(1);
            bundle.writeToParcel(obtain, 0);
            if (!this.f10558a.transact(2, obtain, obtain2, 0)) {
                int i = AbstractBinderC4248pZ.f11073a;
            }
            obtain2.readException();
            return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
