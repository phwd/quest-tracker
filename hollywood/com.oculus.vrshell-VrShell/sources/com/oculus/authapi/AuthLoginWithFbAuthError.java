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
    private final List<String> mAllEmails;
    @Nullable
    private final String mEmail;
    @Nullable
    private final String mExistingEmailConflict;
    @Nullable
    private final String mFbAccessToken;
    @Nullable
    private final String mFbUserId;
    @Nullable
    private final String mName;
    @Nullable
    private final String mProfilePicUri;
    @Nullable
    private final List<String> mUsernameSuggestions;

    AuthLoginWithFbAuthError(Bundle bundle) {
        super(bundle);
        this.mName = bundle.getString(ServiceContract.EXTRA_NAME);
        this.mFbUserId = bundle.getString(ServiceContract.EXTRA_FB_USER_ID);
        this.mFbAccessToken = bundle.getString(ServiceContract.EXTRA_FB_ACCESS_TOKEN);
        this.mEmail = bundle.getString("email");
        this.mProfilePicUri = bundle.getString(ServiceContract.EXTRA_PROFILE_PIC_URI);
        this.mUsernameSuggestions = unmodifiableListOrNull(bundle.getStringArrayList(ServiceContract.EXTRA_USERNAME_SUGGESTIONS));
        this.mAllEmails = unmodifiableListOrNull(bundle.getStringArrayList(ServiceContract.EXTRA_ALL_EMAILS));
        this.mExistingEmailConflict = bundle.getString(ServiceContract.EXTRA_EXISTING_EMAIL_CONFLICT);
    }

    @Nullable
    public String getName() {
        return this.mName;
    }

    @Nullable
    public String getFbUserId() {
        return this.mFbUserId;
    }

    @Nullable
    public String getFbAccessToken() {
        return this.mFbAccessToken;
    }

    @Nullable
    public String getEmail() {
        return this.mEmail;
    }

    @Nullable
    public String getProfilePicUri() {
        return this.mProfilePicUri;
    }

    @Nullable
    public List<String> getUsernameSuggestions() {
        return this.mUsernameSuggestions;
    }

    @Nullable
    public List<String> getAllEmails() {
        return this.mAllEmails;
    }

    @Nullable
    public String getExistingEmailConflict() {
        return this.mExistingEmailConflict;
    }

    @Nullable
    private static List<String> unmodifiableListOrNull(@Nullable List<String> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }
}
