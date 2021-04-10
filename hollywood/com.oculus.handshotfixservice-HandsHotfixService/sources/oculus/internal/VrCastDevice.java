package oculus.internal;

import android.os.Parcel;
import android.os.Parcelable;

public final class VrCastDevice implements Parcelable {
    public static final Parcelable.Creator<VrCastDevice> CREATOR = new Parcelable.Creator<VrCastDevice>() {
        /* class oculus.internal.VrCastDevice.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public VrCastDevice createFromParcel(Parcel in) {
            return new VrCastDevice(in);
        }

        @Override // android.os.Parcelable.Creator
        public VrCastDevice[] newArray(int size) {
            return new VrCastDevice[size];
        }
    };
    public static final int TYPE_GOOGLE_CAST = 2;
    public static final int TYPE_MIRACAST = 1;
    public final String id;
    public final String name;
    public final int state;
    public final int type;

    public VrCastDevice(String name2, String id2, int state2, int type2) {
        this.name = name2;
        this.id = id2;
        this.state = state2;
        this.type = type2;
    }

    protected VrCastDevice(Parcel source) {
        this.name = source.readString();
        this.id = source.readString();
        this.state = source.readInt();
        this.type = source.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.name);
        parcel.writeString(this.id);
        parcel.writeInt(this.state);
        parcel.writeInt(this.type);
    }
}
