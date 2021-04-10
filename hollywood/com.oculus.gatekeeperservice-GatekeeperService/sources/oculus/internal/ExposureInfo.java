package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class ExposureInfo implements Parcelable {
    public static final Parcelable.Creator<ExposureInfo> CREATOR = new Parcelable.Creator<ExposureInfo>() {
        /* class oculus.internal.ExposureInfo.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public ExposureInfo createFromParcel(Parcel in) {
            return new ExposureInfo(in);
        }

        @Override // android.os.Parcelable.Creator
        public ExposureInfo[] newArray(int size) {
            return new ExposureInfo[size];
        }
    };
    private double duration;
    private double gain;

    public ExposureInfo() {
        this.duration = 0.0d;
        this.gain = 0.0d;
    }

    private ExposureInfo(Parcel in) {
        this.duration = 0.0d;
        this.gain = 0.0d;
        readFromParcel(in);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeDouble(this.duration);
        out.writeDouble(this.gain);
    }

    public void readFromParcel(Parcel in) {
        this.duration = in.readDouble();
        this.gain = in.readDouble();
    }
}
