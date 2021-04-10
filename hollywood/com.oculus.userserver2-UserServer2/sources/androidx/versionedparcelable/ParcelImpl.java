package androidx.versionedparcelable;

import X.AbstractC0059Eo;
import X.AnonymousClass2O;
import X.C0053Ef;
import X.TK;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@SuppressLint({"BanParcelableUsage"})
@RestrictTo({AnonymousClass2O.LIBRARY})
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new C0053Ef();
    public final AbstractC0059Eo A00;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        new TK(parcel).A08(this.A00);
    }

    public ParcelImpl(Parcel parcel) {
        this.A00 = new TK(parcel).A04();
    }
}
