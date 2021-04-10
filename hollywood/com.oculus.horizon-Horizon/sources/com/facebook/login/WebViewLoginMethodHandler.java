package com.facebook.login;

import X.AnonymousClass1h0;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.internal.WebDialog;

public final class WebViewLoginMethodHandler extends LoginMethodHandler {
    public static final Parcelable.Creator<WebViewLoginMethodHandler> CREATOR = new AnonymousClass1h0();
    public WebDialog A00;
    public String A01;

    public final int describeContents() {
        return 0;
    }

    @Override // com.facebook.login.LoginMethodHandler
    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.A01);
    }

    public WebViewLoginMethodHandler(Parcel parcel) {
        super(parcel);
        this.A01 = parcel.readString();
    }

    public WebViewLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }
}
