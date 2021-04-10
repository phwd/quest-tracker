package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class TrackedObjectInfo implements Parcelable {
    public static final Parcelable.Creator<TrackedObjectInfo> CREATOR = new Parcelable.Creator<TrackedObjectInfo>() {
        /* class oculus.internal.TrackedObjectInfo.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public TrackedObjectInfo createFromParcel(Parcel parcel) {
            return new TrackedObjectInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public TrackedObjectInfo[] newArray(int i) {
            return new TrackedObjectInfo[i];
        }
    };
    private Vector3d dimensions;
    private String name;
    private int type;

    public static class Vector3d {
        double x;
        double y;
        double z;
    }

    public int describeContents() {
        return 0;
    }

    public TrackedObjectInfo() {
        this.dimensions = new Vector3d();
    }

    private TrackedObjectInfo(Parcel parcel) {
        this.dimensions = new Vector3d();
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.type);
        parcel.writeString(this.name);
        parcel.writeDouble(this.dimensions.x);
        parcel.writeDouble(this.dimensions.y);
        parcel.writeDouble(this.dimensions.z);
    }

    public void readFromParcel(Parcel parcel) {
        this.type = parcel.readInt();
        this.name = parcel.readString();
        this.dimensions.x = parcel.readDouble();
        this.dimensions.y = parcel.readDouble();
        this.dimensions.z = parcel.readDouble();
    }
}
