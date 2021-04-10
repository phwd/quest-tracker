package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.oculus.library.model.InputDevice;

/* renamed from: X.Zi  reason: case insensitive filesystem */
public final class C0454Zi implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new InputDevice(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new InputDevice[i];
    }
}
