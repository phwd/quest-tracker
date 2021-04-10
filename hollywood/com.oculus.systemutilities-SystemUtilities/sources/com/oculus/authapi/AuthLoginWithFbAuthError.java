package com.oculus.authapi;

import android.os.Bundle;
import java.util.Collections;
import java.util.List;

public final class AuthLoginWithFbAuthError extends AuthError {
    private final List<String> mAllEmails;
    private final String mEmail;
    private final String mExistingEmailConflict;
    private final String mFbAccessToken;
    private final String mFbUserId;
    private final String mName;
    private final String mProfilePicUri;
    private final List<String> mUsernameSuggestions;

    AuthLoginWithFbAuthError(Bundle resultData) {
        super(resultData);
        this.mName = resultData.getString("name");
        this.mFbUserId = resultData.getString("fb_user_id");
        this.mFbAccessToken = resultData.getString("fb_access_token");
        this.mEmail = resultData.getString("email");
        this.mProfilePicUri = resultData.getString("profile_pic_uri");
        this.mUsernameSuggestions = unmodifiableListOrNull(resultData.getStringArrayList("username_suggestions"));
        this.mAllEmails = unmodifiableListOrNull(resultData.getStringArrayList("all_emails"));
        this.mExistingEmailConflict = resultData.getString("existing_email_conflict");
    }

    public String getName() {
        return this.mName;
    }

    public String getFbUserId() {
        return this.mFbUserId;
    }

    public String getFbAccessToken() {
        return this.mFbAccessToken;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getProfilePicUri() {
        return this.mProfilePicUri;
    }

    public List<String> getUsernameSuggestions() {
        return this.mUsernameSuggestions;
    }

    public List<String> getAllEmails() {
        return this.mAllEmails;
    }

    public String getExistingEmailConflict() {
        return this.mExistingEmailConflict;
    }

    private static List<String> unmodifiableListOrNull(List<String> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }
}
