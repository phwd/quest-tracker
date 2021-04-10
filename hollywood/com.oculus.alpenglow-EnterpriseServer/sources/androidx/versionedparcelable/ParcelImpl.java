package androidx.versionedparcelable;

import X.AnonymousClass02D;
import X.AnonymousClass0HD;
import X.AnonymousClass0HL;
import X.C03320c1;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@SuppressLint({"BanParcelableUsage"})
@RestrictTo({AnonymousClass02D.LIBRARY})
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new AnonymousClass0HD();
    public final AnonymousClass0HL A00;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        new C03320c1(parcel).A0C(this.A00);
    }

    public ParcelImpl(Parcel parcel) {
        this.A00 = new C03320c1(parcel).A05();
    }
}
