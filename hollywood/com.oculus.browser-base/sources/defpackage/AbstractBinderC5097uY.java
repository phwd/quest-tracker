package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* renamed from: uY  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC5097uY extends Binder implements AbstractC5267vY {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f11417a = 0;

    public AbstractBinderC5097uY() {
        attachInterface(this, "org.chromium.base.process_launcher.IChildProcessService");
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        if (i == 1) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            boolean J2 = ((BinderC1500Yo) this).J(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeInt(J2 ? 1 : 0);
            return true;
        } else if (i == 2) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            AbstractC2198dZ dZVar = null;
            Bundle bundle = parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null;
            IBinder readStrongBinder = parcel.readStrongBinder();
            if (readStrongBinder != null) {
                IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("org.chromium.base.process_launcher.IParentProcess");
                if (queryLocalInterface == null || !(queryLocalInterface instanceof AbstractC2198dZ)) {
                    dZVar = new C2027cZ(readStrongBinder);
                } else {
                    dZVar = (AbstractC2198dZ) queryLocalInterface;
                }
            }
            ((BinderC1500Yo) this).y(bundle, dZVar, parcel.createBinderArrayList());
            return true;
        } else if (i == 3) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            ((BinderC1500Yo) this).q0();
            return true;
        } else if (i == 4) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            ((BinderC1500Yo) this).p0(parcel.readInt());
            return true;
        } else if (i == 5) {
            parcel.enforceInterface("org.chromium.base.process_launcher.IChildProcessService");
            ((BinderC1500Yo) this).w();
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("org.chromium.base.process_launcher.IChildProcessService");
            return true;
        }
    }
}
