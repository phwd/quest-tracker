package androidx.versionedparcelable;

import X.AnonymousClass02C;
import X.AnonymousClass0CW;
import X.AnonymousClass0Ce;
import X.C05170uY;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.RestrictTo;

@SuppressLint({"BanParcelableUsage"})
@RestrictTo({AnonymousClass02C.LIBRARY})
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new AnonymousClass0CW();
    public final AnonymousClass0Ce A00;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        new C05170uY(parcel).A0C(this.A00);
    }

    public ParcelImpl(Parcel parcel) {
        this.A00 = new C05170uY(parcel).A05();
    }
}
