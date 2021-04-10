package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class FrameSetConsumer implements Parcelable {
    public static final Parcelable.Creator<FrameSetConsumer> CREATOR = new Parcelable.Creator<FrameSetConsumer>() {
        /* class oculus.internal.FrameSetConsumer.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public FrameSetConsumer createFromParcel(Parcel parcel) {
            return new FrameSetConsumer(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public FrameSetConsumer[] newArray(int i) {
            return new FrameSetConsumer[i];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel parcel) {
    }

    public void writeToParcel(Parcel parcel, int i) {
    }

    public FrameSetConsumer() {
    }

    private FrameSetConsumer(Parcel parcel) {
        readFromParcel(parcel);
    }
}
