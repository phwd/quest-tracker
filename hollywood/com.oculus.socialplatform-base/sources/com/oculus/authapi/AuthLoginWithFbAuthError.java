package com.oculus.authapi;

import android.os.Bundle;
import com.oculus.auth.service.contract.ServiceContract;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public final class AuthLoginWithFbAuthError extends AuthError {
    @Nullable
    public final List<String> mAllEmails;
    @Nullable
    public final String mEmail;
    @Nullable
    public final String mExistingEmailConflict;
    @Nullable
    public final String mFbAccessToken;
    @Nullable
    public final String mFbUserId;
    @Nullable
    public final String mName;
    @Nullable
    public final String mProfilePicUri;
    @Nullable
    public final List<String> mUsernameSuggestions;

    @Nullable
    public static List<String> unmodifiableListOrNull(@Nullable List<String> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public AuthLoginWithFbAuthError(Bundle bundle) {
        super(bundle);
        this.mName = bundle.getString("name");
        this.mFbUserId = bundle.getString(ServiceContract.EXTRA_FB_USER_ID);
        this.mFbAccessToken = bundle.getString(ServiceContract.EXTRA_FB_ACCESS_TOKEN);
        this.mEmail = bundle.getString("email");
        this.mProfilePicUri = bundle.getString(ServiceContract.EXTRA_PROFILE_PIC_URI);
        this.mUsernameSuggestions = unmodifiableListOrNull(bundle.getStringArrayList(ServiceContract.EXTRA_USERNAME_SUGGESTIONS));
        this.mAllEmails = unmodifiableListOrNull(bundle.getStringArrayList(ServiceContract.EXTRA_ALL_EMAILS));
        this.mExistingEmailConflict = bundle.getString(ServiceContract.EXTRA_EXISTING_EMAIL_CONFLICT);
    }

    @Nullable
    public List<String> getAllEmails() {
        return this.mAllEmails;
    }

    @Nullable
    public String getEmail() {
        return this.mEmail;
    }

    @Nullable
    public String getExistingEmailConflict() {
        return this.mExistingEmailConflict;
    }

    @Nullable
    public String getFbAccessToken() {
        return this.mFbAccessToken;
    }

    @Nullable
    public String getFbUserId() {
        return this.mFbUserId;
    }

    @Nullable
    public String getName() {
        return this.mName;
    }

    @Nullable
    public String getProfilePicUri() {
        return this.mProfilePicUri;
    }

    @Nullable
    public List<String> getUsernameSuggestions() {
        return this.mUsernameSuggestions;
    }
}
