package defpackage;

import android.os.IBinder;
import android.os.Parcel;

/* renamed from: cZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2027cZ implements AbstractC2198dZ {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f9613a;

    public C2027cZ(IBinder iBinder) {
        this.f9613a = iBinder;
    }

    @Override // defpackage.AbstractC2198dZ
    public void N(int i) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.base.process_launcher.IParentProcess");
            obtain.writeInt(i);
            if (!this.f9613a.transact(1, obtain, null, 1)) {
                int i2 = BinderC5143uo.f11436a;
            }
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.AbstractC2198dZ
    public void a() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.base.process_launcher.IParentProcess");
            if (!this.f9613a.transact(3, obtain, obtain2, 0)) {
                int i = BinderC5143uo.f11436a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public IBinder asBinder() {
        return this.f9613a;
    }

    @Override // defpackage.AbstractC2198dZ
    public void j(int i, long j) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.base.process_launcher.IParentProcess");
            obtain.writeInt(i);
            obtain.writeLong(j);
            if (!this.f9613a.transact(4, obtain, null, 1)) {
                int i2 = BinderC5143uo.f11436a;
            }
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.AbstractC2198dZ
    public void j0(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.base.process_launcher.IParentProcess");
            obtain.writeString(str);
            if (!this.f9613a.transact(2, obtain, obtain2, 0)) {
                int i = BinderC5143uo.f11436a;
            }
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
