package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.login.LoginClient;

/* renamed from: X.1gy  reason: invalid class name */
public class AnonymousClass1gy implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new LoginClient.Request(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new LoginClient.Request[i];
    }
}
