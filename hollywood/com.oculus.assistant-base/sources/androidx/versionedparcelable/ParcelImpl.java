package androidx.versionedparcelable;

import X.AbstractC00303l;
import X.C00273h;
import X.C0665eH;
import android.os.Parcel;
import android.os.Parcelable;

public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator CREATOR = new C00273h();
    public final AbstractC00303l A00;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        new C0665eH(parcel).A08(this.A00);
    }

    public ParcelImpl(Parcel parcel) {
        this.A00 = new C0665eH(parcel).A04();
    }
}
