package X;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.login.WebViewLoginMethodHandler;

/* renamed from: X.1h0  reason: invalid class name */
public class AnonymousClass1h0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        return new WebViewLoginMethodHandler(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new WebViewLoginMethodHandler[i];
    }
}
