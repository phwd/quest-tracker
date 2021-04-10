package android.support.v4.os;

import X.AnonymousClass0P;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;

public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator CREATOR = new AnonymousClass0P();
    public IResultReceiver A00;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        synchronized (this) {
            IResultReceiver iResultReceiver = this.A00;
            if (iResultReceiver == null) {
                iResultReceiver = new MyResultReceiver();
                this.A00 = iResultReceiver;
            }
            parcel.writeStrongBinder(iResultReceiver.asBinder());
        }
    }

    public ResultReceiver(Parcel parcel) {
        IResultReceiver iResultReceiver$Stub$Proxy;
        IBinder readStrongBinder = parcel.readStrongBinder();
        if (readStrongBinder == null) {
            iResultReceiver$Stub$Proxy = null;
        } else {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("android.support.v4.os.IResultReceiver");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IResultReceiver)) {
                iResultReceiver$Stub$Proxy = new IResultReceiver$Stub$Proxy(readStrongBinder);
            } else {
                iResultReceiver$Stub$Proxy = (IResultReceiver) queryLocalInterface;
            }
        }
        this.A00 = iResultReceiver$Stub$Proxy;
    }

    public final class MyResultReceiver extends Binder implements IResultReceiver {
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            if (i == 1) {
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                parcel.readInt();
                if (parcel.readInt() != 0) {
                    Bundle.CREATOR.createFromParcel(parcel);
                    return true;
                }
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("android.support.v4.os.IResultReceiver");
            }
            return true;
        }

        public MyResultReceiver() {
            attachInterface(this, "android.support.v4.os.IResultReceiver");
        }

        public MyResultReceiver() {
            this();
        }
    }
}
