package com.oculus.authapi;

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
}
