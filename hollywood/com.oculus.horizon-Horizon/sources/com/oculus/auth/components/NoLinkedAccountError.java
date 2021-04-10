package com.oculus.auth.components;

import android.os.Bundle;
import com.oculus.auth.api.LoginWithFbAuthResponse;
import com.oculus.auth.service.contract.ServiceContract;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class NoLinkedAccountError extends MarshallableError {
    public final LoginWithFbAuthResponse.AccountInfo mAccountInfo;
    @Nullable
    public final String mExistingEmailConflict;

    public NoLinkedAccountError(LoginWithFbAuthResponse.AccountInfo accountInfo, @Nullable String str) {
        super(-13);
        this.mAccountInfo = accountInfo;
        this.mExistingEmailConflict = str;
    }

    @Override // com.oculus.auth.components.MarshallableError
    public Bundle marshal() {
        Bundle marshal = super.marshal();
        marshal.putString("name", this.mAccountInfo.name);
        marshal.putString(ServiceContract.EXTRA_FB_USER_ID, this.mAccountInfo.fbUserId);
        marshal.putString(ServiceContract.EXTRA_FB_ACCESS_TOKEN, this.mAccountInfo.fbAccessToken);
        marshal.putString("email", this.mAccountInfo.email);
        marshal.putString(ServiceContract.EXTRA_PROFILE_PIC_URI, this.mAccountInfo.profilePicUri);
        marshal.putStringArrayList(ServiceContract.EXTRA_USERNAME_SUGGESTIONS, this.mAccountInfo.usernameSuggestions);
        marshal.putStringArrayList(ServiceContract.EXTRA_ALL_EMAILS, this.mAccountInfo.allEmails);
        marshal.putString(ServiceContract.EXTRA_EXISTING_EMAIL_CONFLICT, this.mExistingEmailConflict);
        return marshal;
    }
}
