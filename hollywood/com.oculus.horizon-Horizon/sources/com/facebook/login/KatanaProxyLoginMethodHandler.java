package com.facebook.login;

import X.AnonymousClass1gw;
import android.os.Parcel;
import android.os.Parcelable;

public final class KatanaProxyLoginMethodHandler extends LoginMethodHandler {
    public static final Parcelable.Creator<KatanaProxyLoginMethodHandler> CREATOR = new AnonymousClass1gw();

    public final int describeContents() {
        return 0;
    }

    public KatanaProxyLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public KatanaProxyLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }
}
