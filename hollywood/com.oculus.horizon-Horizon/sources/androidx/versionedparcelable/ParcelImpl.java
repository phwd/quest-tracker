package androidx.versionedparcelable;

import X.AnonymousClass02C;
import X.AnonymousClass0CS;
import X.AnonymousClass0Ca;
import X.AnonymousClass0rN;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@SuppressLint({"BanParcelableUsage"})
@RestrictTo({AnonymousClass02C.LIBRARY})
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new AnonymousClass0CS();
    public final AnonymousClass0Ca A00;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        new AnonymousClass0rN(parcel).A0C(this.A00);
    }

    public ParcelImpl(Parcel parcel) {
        this.A00 = new AnonymousClass0rN(parcel).A05();
    }
}
