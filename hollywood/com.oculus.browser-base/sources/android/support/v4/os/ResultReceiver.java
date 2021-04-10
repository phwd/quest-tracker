package android.support.v4.os;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator CREATOR = new CM0();
    public AbstractC3564lZ F;

    public ResultReceiver(Parcel parcel) {
        AbstractC3564lZ lZVar;
        IBinder readStrongBinder = parcel.readStrongBinder();
        int i = DM0.f7885a;
        if (readStrongBinder == null) {
            lZVar = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof AbstractC3564lZ)) {
                lZVar = new C3393kZ(readStrongBinder);
            } else {
                lZVar = (AbstractC3564lZ) queryLocalInterface;
            }
        }
        this.F = lZVar;
    }

    public void b(int i, Bundle bundle) {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            if (this.F == null) {
                this.F = new DM0(this);
            }
            parcel.writeStrongBinder(this.F.asBinder());
        }
    }
}
