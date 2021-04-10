package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class ParcelAnchorUuid implements Parcelable {
    public static final Parcelable.Creator<ParcelAnchorUuid> CREATOR = new Parcelable.Creator<ParcelAnchorUuid>() {
        /* class oculus.internal.ParcelAnchorUuid.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorUuid createFromParcel(Parcel parcel) {
            new ParcelAnchorUuid(parcel);
            throw null;
        }

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorUuid[] newArray(int i) {
            return new ParcelAnchorUuid[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public ParcelAnchorUuid() {
    }

    private ParcelAnchorUuid(Parcel parcel) {
        throw new UnsupportedOperationException("not implemented");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new UnsupportedOperationException("not implemented");
    }
}
