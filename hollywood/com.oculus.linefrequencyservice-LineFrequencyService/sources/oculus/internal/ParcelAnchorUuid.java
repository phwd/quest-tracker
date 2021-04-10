package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class ParcelAnchorUuid implements Parcelable {
    public static final Parcelable.Creator<ParcelAnchorUuid> CREATOR = new Parcelable.Creator<ParcelAnchorUuid>() {
        /* class oculus.internal.ParcelAnchorUuid.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorUuid createFromParcel(Parcel in) {
            return new ParcelAnchorUuid(in);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorUuid[] newArray(int size) {
            return new ParcelAnchorUuid[size];
        }
    };

    public ParcelAnchorUuid() {
    }

    private ParcelAnchorUuid(Parcel in) {
        throw new UnsupportedOperationException("not implemented");
    }

    public void writeToParcel(Parcel out, int flags) {
        throw new UnsupportedOperationException("not implemented");
    }

    public void readFromParcel(Parcel in) {
        throw new UnsupportedOperationException("not implemented");
    }

    public int describeContents() {
        return 0;
    }
}
