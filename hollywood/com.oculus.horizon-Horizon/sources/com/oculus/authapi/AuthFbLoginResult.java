package com.oculus.authapi;

import android.os.Bundle;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AuthFbLoginResult {
    public final String mAccessToken;
    public final String mUid;

    public AuthFbLoginResult(Bundle bundle) throws AuthError {
        String str;
        String string = bundle.getString("uid");
        if (string != null) {
            String string2 = bundle.getString("access_token");
            if (string2 != null) {
                this.mUid = string;
                this.mAccessToken = string2;
                return;
            }
            str = "Access token missing from FB login result";
        } else {
            str = "UID missing from FB login result";
        }
        throw new AuthError(-7, str, str);
    }
}
