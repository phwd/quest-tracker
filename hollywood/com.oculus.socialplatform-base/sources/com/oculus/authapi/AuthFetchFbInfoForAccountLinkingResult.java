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
    public final String mEmail;
    @Nullable
    public final String mName;
    @Nullable
    public final String mProfilePicUri;

    public AuthFetchFbInfoForAccountLinkingResult(Bundle bundle) {
        this.mName = bundle.getString("name");
        this.mEmail = bundle.getString("email");
        this.mProfilePicUri = bundle.getString(ServiceContract.EXTRA_PROFILE_PIC_URI);
    }

    @Nullable
    public String getEmail() {
        return this.mEmail;
    }

    @Nullable
    public String getName() {
        return this.mName;
    }

    @Nullable
    public String getProfilePicUri() {
        return this.mProfilePicUri;
    }
}
