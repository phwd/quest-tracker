package X;

import android.os.Parcel;
import android.os.Parcelable;

public final class SO {
    public static final ClassLoader A00 = SO.class.getClassLoader();

    public static Parcelable A00(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return (Parcelable) creator.createFromParcel(parcel);
    }
}
