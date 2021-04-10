package defpackage;

import android.os.IBinder;
import android.os.Parcel;
import android.view.Surface;
import org.chromium.base.UnguessableToken;
import org.chromium.content.common.SurfaceWrapper;

/* renamed from: IY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IY implements KY {

    /* renamed from: a  reason: collision with root package name */
    public IBinder f8232a;

    public IY(IBinder iBinder) {
        this.f8232a = iBinder;
    }

    @Override // defpackage.KY
    public void H(UnguessableToken unguessableToken, Surface surface) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.content.common.IGpuProcessCallback");
            if (unguessableToken != null) {
                obtain.writeInt(1);
                unguessableToken.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            if (surface != null) {
                obtain.writeInt(1);
                surface.writeToParcel(obtain, 0);
            } else {
                obtain.writeInt(0);
            }
            if (!this.f8232a.transact(1, obtain, null, 1)) {
                int i = JY.f8295a;
            }
        } finally {
            obtain.recycle();
        }
    }

    @Override // defpackage.KY
    public SurfaceWrapper S(int i) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("org.chromium.content.common.IGpuProcessCallback");
            obtain.writeInt(i);
            if (!this.f8232a.transact(2, obtain, obtain2, 0)) {
                int i2 = JY.f8295a;
            }
            obtain2.readException();
            return obtain2.readInt() != 0 ? (SurfaceWrapper) SurfaceWrapper.CREATOR.createFromParcel(obtain2) : null;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public IBinder asBinder() {
        return this.f8232a;
    }
}
