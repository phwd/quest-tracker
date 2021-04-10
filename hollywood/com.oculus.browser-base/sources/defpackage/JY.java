package defpackage;

import J.N;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.view.Surface;
import org.chromium.base.UnguessableToken;
import org.chromium.content.common.SurfaceWrapper;

/* renamed from: JY  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class JY extends Binder implements KY {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f8295a = 0;

    public JY() {
        attachInterface(this, "org.chromium.content.common.IGpuProcessCallback");
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.content.common.IGpuProcessCallback");
            Surface surface = null;
            UnguessableToken unguessableToken = parcel.readInt() != 0 ? (UnguessableToken) UnguessableToken.CREATOR.createFromParcel(parcel) : null;
            if (parcel.readInt() != 0) {
                surface = (Surface) Surface.CREATOR.createFromParcel(parcel);
            }
            N.M$O7xE3y(unguessableToken, surface);
            return true;
        } else if (i == 2) {
            parcel.enforceInterface("org.chromium.content.common.IGpuProcessCallback");
            SurfaceWrapper S = ((BinderC4583rW) this).S(parcel.readInt());
            parcel2.writeNoException();
            if (S != null) {
                parcel2.writeInt(1);
                S.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("org.chromium.content.common.IGpuProcessCallback");
            return true;
        }
    }
}
