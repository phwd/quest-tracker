package defpackage;

import android.app.Notification;
import android.os.IBinder;
import android.os.Parcel;
import org.chromium.webapk.lib.runtime_library.IWebApkApi;

/* renamed from: sZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4759sZ implements AbstractC5099uZ {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f11280a;

    public C4759sZ(IBinder iBinder) {
        this.f11280a = iBinder;
    }

    public IBinder asBinder() {
        return this.f11280a;
    }

    public void c(String str, int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IWebApkApi.Stub.DESCRIPTOR);
            obtain.writeString(str);
            obtain.writeInt(i);
            if (!this.f11280a.transact(3, obtain, obtain2, 0)) {
                int i2 = AbstractBinderC4929tZ.f11349a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public int d() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IWebApkApi.Stub.DESCRIPTOR);
            if (!this.f11280a.transact(1, obtain, obtain2, 0)) {
                int i = AbstractBinderC4929tZ.f11349a;
            }
            obtain2.readException();
            return obtain2.readInt();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public void e0(String str, int i, Notification notification, String str2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IWebApkApi.Stub.DESCRIPTOR);
            obtain.writeString(str);
            obtain.writeInt(i);
            if (notification != null) {
                obtain.writeInt(1);
                notification.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            obtain.writeString(str2);
            if (!this.f11280a.transact(5, obtain, obtain2, 0)) {
                int i2 = AbstractBinderC4929tZ.f11349a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public boolean f() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken(IWebApkApi.Stub.DESCRIPTOR);
            boolean z = false;
            if (!this.f11280a.transact(4, obtain, obtain2, 0)) {
                int i = AbstractBinderC4929tZ.f11349a;
            }
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                z = true;
            }
            return z;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
