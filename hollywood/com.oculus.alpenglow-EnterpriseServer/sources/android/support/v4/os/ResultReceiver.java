package android.support.v4.os;

import X.AnonymousClass01J;
import X.AnonymousClass02D;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.os.IResultReceiver;
import androidx.annotation.RestrictTo;

@SuppressLint({"BanParcelableUsage"})
@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
public class ResultReceiver implements Parcelable {
    public static final Parcelable.Creator<ResultReceiver> CREATOR = new AnonymousClass01J();
    public IResultReceiver A00;

    public class MyResultReceiver extends IResultReceiver.Stub {
        public MyResultReceiver() {
        }

        @Override // android.support.v4.os.IResultReceiver
        public final void A7b(int i, Bundle bundle) {
        }
    }

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
        this.A00 = IResultReceiver.Stub.asInterface(parcel.readStrongBinder());
    }
}
