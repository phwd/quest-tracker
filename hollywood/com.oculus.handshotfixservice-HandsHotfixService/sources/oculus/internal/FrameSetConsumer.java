package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class FrameSetConsumer implements Parcelable {
    public static final Parcelable.Creator<FrameSetConsumer> CREATOR = new Parcelable.Creator<FrameSetConsumer>() {
        /* class oculus.internal.FrameSetConsumer.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public FrameSetConsumer createFromParcel(Parcel in) {
            return new FrameSetConsumer(in);
        }

        @Override // android.os.Parcelable.Creator
        public FrameSetConsumer[] newArray(int size) {
            return new FrameSetConsumer[size];
        }
    };

    public FrameSetConsumer() {
    }

    private FrameSetConsumer(Parcel in) {
        readFromParcel(in);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
    }

    public void readFromParcel(Parcel in) {
    }
}
