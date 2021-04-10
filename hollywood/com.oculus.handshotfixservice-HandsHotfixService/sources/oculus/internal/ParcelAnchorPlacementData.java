package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class ParcelAnchorPlacementData implements Parcelable {
    public static final Parcelable.Creator<ParcelAnchorPlacementData> CREATOR = new Parcelable.Creator<ParcelAnchorPlacementData>() {
        /* class oculus.internal.ParcelAnchorPlacementData.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorPlacementData createFromParcel(Parcel in) {
            return new ParcelAnchorPlacementData(in);
        }

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorPlacementData[] newArray(int size) {
            return new ParcelAnchorPlacementData[size];
        }
    };

    public ParcelAnchorPlacementData() {
    }

    private ParcelAnchorPlacementData(Parcel in) {
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
