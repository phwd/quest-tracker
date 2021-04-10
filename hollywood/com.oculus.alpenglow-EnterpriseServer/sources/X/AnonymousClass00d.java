package X;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.RatingCompat;

/* renamed from: X.00d  reason: invalid class name */
public class AnonymousClass00d implements Parcelable.Creator<RatingCompat> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // android.os.Parcelable.Creator
    public final RatingCompat createFromParcel(Parcel parcel) {
        return new RatingCompat(parcel.readInt(), parcel.readFloat());
    }

    /* Return type fixed from 'java.lang.Object[]' to match base method */
    @Override // android.os.Parcelable.Creator
    public final RatingCompat[] newArray(int i) {
        return new RatingCompat[i];
    }
}
