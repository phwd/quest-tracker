package com.oculus.authapi;

import android.os.Bundle;
import com.oculus.auth.service.contract.ServiceContract;
import java.util.ArrayList;
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

    public AuthLoginWithFbAuthError(Bundle bundle) {
        super(bundle);
        List<String> unmodifiableList;
        List<String> unmodifiableList2;
        this.mName = bundle.getString("name");
        this.mFbUserId = bundle.getString(ServiceContract.EXTRA_FB_USER_ID);
        this.mFbAccessToken = bundle.getString(ServiceContract.EXTRA_FB_ACCESS_TOKEN);
        this.mEmail = bundle.getString("email");
        this.mProfilePicUri = bundle.getString(ServiceContract.EXTRA_PROFILE_PIC_URI);
        ArrayList<String> stringArrayList = bundle.getStringArrayList(ServiceContract.EXTRA_USERNAME_SUGGESTIONS);
        if (stringArrayList == null) {
            unmodifiableList = null;
        } else {
            unmodifiableList = Collections.unmodifiableList(stringArrayList);
        }
        this.mUsernameSuggestions = unmodifiableList;
        ArrayList<String> stringArrayList2 = bundle.getStringArrayList(ServiceContract.EXTRA_ALL_EMAILS);
        if (stringArrayList2 == null) {
            unmodifiableList2 = null;
        } else {
            unmodifiableList2 = Collections.unmodifiableList(stringArrayList2);
        }
        this.mAllEmails = unmodifiableList2;
        this.mExistingEmailConflict = bundle.getString(ServiceContract.EXTRA_EXISTING_EMAIL_CONFLICT);
    }
}
