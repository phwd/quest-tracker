package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import java.util.List;

/* renamed from: tY  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4927tY implements AbstractC5267vY {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f11348a;

    public C4927tY(IBinder iBinder) {
        this.f11348a = iBinder;
    }

    @Override // defpackage.AbstractC5267vY
    public boolean J(String str) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
            obtain.writeString(str);
            boolean z = false;
            if (!this.f11348a.transact(1, obtain, obtain2, 0)) {
                int i = AbstractBinderC5097uY.f11417a;
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

    public IBinder asBinder() {
        return this.f11348a;
    }

    @Override // defpackage.AbstractC5267vY
    public void p0(int i) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
            obtain.writeInt(i);
            if (!this.f11348a.transact(4, obtain, null, 1)) {
                int i2 = AbstractBinderC5097uY.f11417a;
            }
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.AbstractC5267vY
    public void q0() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
            if (!this.f11348a.transact(3, obtain, null, 1)) {
                int i = AbstractBinderC5097uY.f11417a;
            }
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.AbstractC5267vY
    public void w() {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
            if (!this.f11348a.transact(5, obtain, null, 1)) {
                int i = AbstractBinderC5097uY.f11417a;
            }
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.AbstractC5267vY
    public void y(Bundle bundle, AbstractC2198dZ dZVar, List list) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.base.process_launcher.IChildProcessService");
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            obtain.writeStrongBinder((BinderC5143uo) dZVar);
            obtain.writeBinderList(list);
            if (!this.f11348a.transact(2, obtain, null, 1)) {
                int i = AbstractBinderC5097uY.f11417a;
            }
        } finally {
            obtain.recycle();
        }
    }
}
