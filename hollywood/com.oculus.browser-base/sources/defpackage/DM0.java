package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.support.v4.os.ResultReceiver;
import java.util.Objects;

/* renamed from: DM0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DM0 extends Binder implements AbstractC3564lZ {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f7885a = 0;
    public final /* synthetic */ ResultReceiver b;

    public DM0(ResultReceiver resultReceiver) {
        this.b = resultReceiver;
        attachInterface(this, "android.support.v4.os.IResultReceiver");
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("android.support.v4.os.IResultReceiver");
            int readInt = parcel.readInt();
            Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
            Objects.requireNonNull(this.b);
            this.b.b(readInt, bundle);
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("android.support.v4.os.IResultReceiver");
            return true;
        }
    }
}
