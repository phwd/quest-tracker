package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class ParcelAnchorHandle implements Parcelable {
    public static final Parcelable.Creator<ParcelAnchorHandle> CREATOR = new Parcelable.Creator<ParcelAnchorHandle>() {
        /* class oculus.internal.ParcelAnchorHandle.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorHandle createFromParcel(Parcel parcel) {
            new ParcelAnchorHandle(parcel);
            throw null;
        }

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorHandle[] newArray(int i) {
            return new ParcelAnchorHandle[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public ParcelAnchorHandle() {
    }

    private ParcelAnchorHandle(Parcel parcel) {
        throw new UnsupportedOperationException("not implemented");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new UnsupportedOperationException("not implemented");
    }
}
