package com.oculus.authapi;

import android.os.Bundle;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class AuthFetchFbInfoForAccountLinkingResult {
    @Nullable
    private final String mEmail;
    @Nullable
    private final String mName;
    @Nullable
    private final String mProfilePicUri;

    AuthFetchFbInfoForAccountLinkingResult(Bundle resultData) {
        this.mName = resultData.getString("name");
        this.mEmail = resultData.getString("email");
        this.mProfilePicUri = resultData.getString(ServiceContract.EXTRA_PROFILE_PIC_URI);
    }

    @Nullable
    public String getName() {
        return this.mName;
    }

    @Nullable
    public String getEmail() {
        return this.mEmail;
    }

    @Nullable
    public String getProfilePicUri() {
        return this.mProfilePicUri;
    }
}
