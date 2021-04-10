package defpackage;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import org.chromium.base.task.PostTask;

/* renamed from: Y30  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Y30 extends Binder implements Z30 {
    public Y30() {
        attachInterface(this, "org.chromium.IsReadyToPayServiceCallback");
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.IsReadyToPayServiceCallback");
            boolean z = parcel.readInt() != 0;
            ServiceConnectionC2121d40 d40 = (ServiceConnectionC2121d40) this;
            AbstractC1950c40 c40 = d40.b;
            if (c40 != null) {
                PostTask.c(Zo1.f9374a, new S5((Z5) c40, z));
                d40.b = null;
                if (d40.c) {
                    d40.f9745a.unbindService(d40);
                    d40.c = false;
                }
                d40.e.removeCallbacksAndMessages(null);
            }
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("org.chromium.IsReadyToPayServiceCallback");
            return true;
        }
    }
}
