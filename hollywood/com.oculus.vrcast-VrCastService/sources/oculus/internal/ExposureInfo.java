package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class ExposureInfo implements Parcelable {
    public static final Parcelable.Creator<ExposureInfo> CREATOR = new Parcelable.Creator<ExposureInfo>() {
        /* class oculus.internal.ExposureInfo.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ExposureInfo createFromParcel(Parcel parcel) {
            return new ExposureInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ExposureInfo[] newArray(int i) {
            return new ExposureInfo[i];
        }
    };
    private double duration;
    private double gain;

    public int describeContents() {
        return 0;
    }

    public ExposureInfo() {
        this.duration = 0.0d;
        this.gain = 0.0d;
    }

    private ExposureInfo(Parcel parcel) {
        this.duration = 0.0d;
        this.gain = 0.0d;
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.duration);
        parcel.writeDouble(this.gain);
    }

    public void readFromParcel(Parcel parcel) {
        this.duration = parcel.readDouble();
        this.gain = parcel.readDouble();
    }
}
