package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.login.KatanaProxyLoginMethodHandler;

/* renamed from: X.1gw  reason: invalid class name */
public class AnonymousClass1gw implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new KatanaProxyLoginMethodHandler(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new KatanaProxyLoginMethodHandler[i];
    }
}
