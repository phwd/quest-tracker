package com.oculus.authapi;

import android.os.Bundle;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AuthFbLoginResult {
    private final String mAccessToken;
    private final String mUid;

    AuthFbLoginResult(Bundle bundle) throws AuthError {
        String string = bundle.getString(ServiceContract.EXTRA_UID);
        if (string != null) {
            String string2 = bundle.getString("access_token");
            if (string2 != null) {
                this.mUid = string;
                this.mAccessToken = string2;
                return;
            }
            throw new AuthError(-7, "Access token missing from FB login result", "Access token missing from FB login result");
        }
        throw new AuthError(-7, "UID missing from FB login result", "UID missing from FB login result");
    }

    public String getUid() {
        return this.mUid;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }
}
