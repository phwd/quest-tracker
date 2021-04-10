package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.login.GetTokenLoginMethodHandler;

/* renamed from: X.1gv  reason: invalid class name and case insensitive filesystem */
public class C09471gv implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new GetTokenLoginMethodHandler(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new GetTokenLoginMethodHandler[i];
    }
}
