package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class ParcelAnchorPlacementData implements Parcelable {
    public static final Parcelable.Creator<ParcelAnchorPlacementData> CREATOR = new Parcelable.Creator<ParcelAnchorPlacementData>() {
        /* class oculus.internal.ParcelAnchorPlacementData.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorPlacementData createFromParcel(Parcel parcel) {
            new ParcelAnchorPlacementData(parcel);
            throw null;
        }

        @Override // android.os.Parcelable.Creator
        public ParcelAnchorPlacementData[] newArray(int i) {
            return new ParcelAnchorPlacementData[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public ParcelAnchorPlacementData() {
    }

    private ParcelAnchorPlacementData(Parcel parcel) {
        throw new UnsupportedOperationException("not implemented");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new UnsupportedOperationException("not implemented");
    }
}
