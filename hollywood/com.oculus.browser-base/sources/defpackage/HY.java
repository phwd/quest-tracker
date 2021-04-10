package defpackage;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.google.android.gms.common.internal.GetServiceRequest;

/* renamed from: HY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class HY implements IInterface {

    /* renamed from: a  reason: collision with root package name */
    public final IBinder f8162a;

    public HY(IBinder iBinder) {
        this.f8162a = iBinder;
    }

    public final IBinder asBinder() {
        return this.f8162a;
    }

    public final void c(GY gy, GetServiceRequest getServiceRequest) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
            obtain.writeStrongBinder((AbstractBinderC2658gC1) gy);
            obtain.writeInt(1);
            getServiceRequest.writeToParcel(obtain, 0);
            this.f8162a.transact(46, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
