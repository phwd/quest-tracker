package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.login.LoginClient;

/* renamed from: X.1gx  reason: invalid class name */
public class AnonymousClass1gx implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new LoginClient(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new LoginClient[i];
    }
}
