package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class VrCastDevice implements Parcelable {
    public static final Parcelable.Creator<VrCastDevice> CREATOR = new Parcelable.Creator<VrCastDevice>() {
        /* class oculus.internal.VrCastDevice.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public VrCastDevice createFromParcel(Parcel parcel) {
            return new VrCastDevice(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public VrCastDevice[] newArray(int i) {
            return new VrCastDevice[i];
        }
    };
    public final String id;
    public final String name;
    public final int state;
    public final int type;

    public int describeContents() {
        return 0;
    }

    public VrCastDevice(String str, String str2, int i, int i2) {
        this.name = str;
        this.id = str2;
        this.state = i;
        this.type = i2;
    }

    protected VrCastDevice(Parcel parcel) {
        this.name = parcel.readString();
        this.id = parcel.readString();
        this.state = parcel.readInt();
        this.type = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.id);
        parcel.writeInt(this.state);
        parcel.writeInt(this.type);
    }
}
