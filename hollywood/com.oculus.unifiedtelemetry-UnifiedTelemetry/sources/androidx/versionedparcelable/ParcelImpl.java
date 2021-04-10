package androidx.versionedparcelable;

import X.AnonymousClass2C;
import X.CR;
import X.CZ;
import X.Ze;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@SuppressLint({"BanParcelableUsage"})
@RestrictTo({AnonymousClass2C.LIBRARY})
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new CR();
    public final CZ A00;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        new Ze(parcel).A0C(this.A00);
    }

    public ParcelImpl(Parcel parcel) {
        this.A00 = new Ze(parcel).A05();
    }
}
