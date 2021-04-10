package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.login.LoginClient;

/* renamed from: X.1gz  reason: invalid class name */
public class AnonymousClass1gz implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new LoginClient.Result(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new LoginClient.Result[i];
    }
}
