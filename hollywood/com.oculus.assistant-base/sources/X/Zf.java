package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.library.model.Image;

public final class Zf implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new Image(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new Image[i];
    }
}
