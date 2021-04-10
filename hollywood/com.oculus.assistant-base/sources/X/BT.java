package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.auth.viewercontext.ViewerContext;

public final class BT implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new ViewerContext(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new ViewerContext[i];
    }
}
