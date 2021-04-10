package defpackage;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;

/* renamed from: iZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC3052iZ extends Binder implements AbstractC3222jZ {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f10143a = 0;

    public AbstractBinderC3052iZ() {
        attachInterface(this, "android.support.customtabs.IPostMessageService");
    }

    public IBinder asBinder() {
        return this;
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
        Bundle bundle = null;
        if (i == 2) {
            parcel.enforceInterface("android.support.customtabs.IPostMessageService");
            AbstractC5947zY c = AbstractBinderC5777yY.c(parcel.readStrongBinder());
            if (parcel.readInt() != 0) {
                bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
            }
            ((C5607xY) c).d(bundle);
            parcel2.writeNoException();
            return true;
        } else if (i == 3) {
            parcel.enforceInterface("android.support.customtabs.IPostMessageService");
            AbstractC5947zY c2 = AbstractBinderC5777yY.c(parcel.readStrongBinder());
            String readString = parcel.readString();
            if (parcel.readInt() != 0) {
                bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
            }
            ((C5607xY) c2).f(readString, bundle);
            parcel2.writeNoException();
            return true;
        } else if (i != 1598968902) {
            return super.onTransact(i, parcel, parcel2, i2);
        } else {
            parcel2.writeString("android.support.customtabs.IPostMessageService");
            return true;
        }
    }
}
