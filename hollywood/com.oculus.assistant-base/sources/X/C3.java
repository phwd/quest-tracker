package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.common.gcmcompat.OneoffTask;

public final class C3 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new OneoffTask(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new OneoffTask[i];
    }
}
