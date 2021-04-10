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

    AuthFetchFbInfoForAccountLinkingResult(Bundle bundle) {
        this.mName = bundle.getString(ServiceContract.EXTRA_NAME);
        this.mEmail = bundle.getString("email");
        this.mProfilePicUri = bundle.getString(ServiceContract.EXTRA_PROFILE_PIC_URI);
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
