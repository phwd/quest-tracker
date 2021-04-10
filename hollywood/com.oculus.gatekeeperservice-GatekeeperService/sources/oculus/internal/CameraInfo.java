package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class CameraInfo implements Parcelable {
    public static final Parcelable.Creator<CameraInfo> CREATOR = new Parcelable.Creator<CameraInfo>() {
        /* class oculus.internal.CameraInfo.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public CameraInfo createFromParcel(Parcel in) {
            return new CameraInfo(in);
        }

        @Override // android.os.Parcelable.Creator
        public CameraInfo[] newArray(int size) {
            return new CameraInfo[size];
        }
    };
    private int bpp;
    private String description;
    private int height;
    private int id;
    private int position;
    private int width;

    public CameraInfo() {
    }

    private CameraInfo(Parcel in) {
        readFromParcel(in);
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.id);
        out.writeInt(this.position);
        out.writeInt(this.width);
        out.writeInt(this.height);
        out.writeInt(this.bpp);
        out.writeString(this.description);
    }

    public void readFromParcel(Parcel in) {
        this.id = in.readInt();
        this.position = in.readInt();
        this.width = in.readInt();
        this.height = in.readInt();
        this.bpp = in.readInt();
        this.description = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int pos) {
        this.position = pos;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public int getBPP() {
        return this.bpp;
    }

    public void setBPP(int bitsPerPixel) {
        this.bpp = bitsPerPixel;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }
}
