package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class CameraInfo implements Parcelable {
    public static final Parcelable.Creator<CameraInfo> CREATOR = new Parcelable.Creator<CameraInfo>() {
        /* class oculus.internal.CameraInfo.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public CameraInfo createFromParcel(Parcel parcel) {
            return new CameraInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CameraInfo[] newArray(int i) {
            return new CameraInfo[i];
        }
    };
    private int bpp;
    private String description;
    private int height;
    private int id;
    private int position;
    private int width;

    public int describeContents() {
        return 0;
    }

    public CameraInfo() {
    }

    private CameraInfo(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeInt(this.position);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeInt(this.bpp);
        parcel.writeString(this.description);
    }

    public void readFromParcel(Parcel parcel) {
        this.id = parcel.readInt();
        this.position = parcel.readInt();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.bpp = parcel.readInt();
        this.description = parcel.readString();
    }
}
