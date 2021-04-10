package com.facebook.login;

import X.C09391dV;
import X.C09471gv;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient;
import java.util.ArrayList;
import java.util.Date;

public final class GetTokenLoginMethodHandler extends LoginMethodHandler {
    public static final Parcelable.Creator<GetTokenLoginMethodHandler> CREATOR = new C09471gv();
    public C09391dV A00;

    public final int describeContents() {
        return 0;
    }

    public final void A04(LoginClient.Request request, Bundle bundle) {
        AccessToken accessToken;
        AccessTokenSource accessTokenSource = AccessTokenSource.FACEBOOK_APPLICATION_SERVICE;
        String str = request.A04;
        Date bundleLongAsDate = Utility.getBundleLongAsDate(bundle, NativeProtocol.EXTRA_EXPIRES_SECONDS_SINCE_EPOCH, new Date(0));
        ArrayList<String> stringArrayList = bundle.getStringArrayList(NativeProtocol.EXTRA_PERMISSIONS);
        String string = bundle.getString(NativeProtocol.EXTRA_ACCESS_TOKEN);
        if (Utility.isNullOrEmpty(string)) {
            accessToken = null;
        } else {
            accessToken = new AccessToken(string, str, bundle.getString(NativeProtocol.EXTRA_USER_ID), stringArrayList, null, accessTokenSource, bundleLongAsDate, new Date());
        }
        LoginClient loginClient = this.A01;
        loginClient.A06(LoginClient.Result.A00(loginClient.A04, accessToken));
    }

    public GetTokenLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public GetTokenLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }
}
