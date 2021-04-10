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

    AuthLoginWithFbAuthError(Bundle bundle) {
        super(bundle);
        this.mName = bundle.getString("name");
        this.mFbUserId = bundle.getString("fb_user_id");
        this.mFbAccessToken = bundle.getString("fb_access_token");
        this.mEmail = bundle.getString("email");
        this.mProfilePicUri = bundle.getString("profile_pic_uri");
        this.mUsernameSuggestions = unmodifiableListOrNull(bundle.getStringArrayList("username_suggestions"));
        this.mAllEmails = unmodifiableListOrNull(bundle.getStringArrayList("all_emails"));
        this.mExistingEmailConflict = bundle.getString("existing_email_conflict");
    }

    private static List<String> unmodifiableListOrNull(List<String> list) {
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }
}
