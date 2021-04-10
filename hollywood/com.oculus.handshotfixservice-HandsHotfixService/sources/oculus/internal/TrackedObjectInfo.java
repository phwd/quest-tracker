package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class TrackedObjectInfo implements Parcelable {
    public static final Parcelable.Creator<TrackedObjectInfo> CREATOR = new Parcelable.Creator<TrackedObjectInfo>() {
        /* class oculus.internal.TrackedObjectInfo.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public TrackedObjectInfo createFromParcel(Parcel in) {
            return new TrackedObjectInfo(in);
        }

        @Override // android.os.Parcelable.Creator
        public TrackedObjectInfo[] newArray(int size) {
            return new TrackedObjectInfo[size];
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

    public TrackedObjectInfo() {
        this.dimensions = new Vector3d();
    }

    private TrackedObjectInfo(Parcel in) {
        this.dimensions = new Vector3d();
        readFromParcel(in);
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.type);
        out.writeString(this.name);
        out.writeDouble(this.dimensions.x);
        out.writeDouble(this.dimensions.y);
        out.writeDouble(this.dimensions.z);
    }

    public void readFromParcel(Parcel in) {
        this.type = in.readInt();
        this.name = in.readString();
        this.dimensions.x = in.readDouble();
        this.dimensions.y = in.readDouble();
        this.dimensions.z = in.readDouble();
    }

    public int describeContents() {
        return 0;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int newType) {
        this.type = newType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public Vector3d getDimensions() {
        return this.dimensions;
    }

    public void setDimensions(Vector3d newDimensions) {
        this.dimensions = newDimensions;
    }
}
