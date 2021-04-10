package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class ParcelAnchorHandle implements Parcelable {
    public static final Parcelable.Creator<ParcelAnchorHandle> CREATOR = new Parcelable.Creator<ParcelAnchorHandle>() {
        /* class oculus.internal.ParcelAnchorHandle.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorHandle createFromParcel(Parcel in) {
            return new ParcelAnchorHandle(in);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorHandle[] newArray(int size) {
            return new ParcelAnchorHandle[size];
        }
    };

    public ParcelAnchorHandle() {
    }

    private ParcelAnchorHandle(Parcel in) {
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
