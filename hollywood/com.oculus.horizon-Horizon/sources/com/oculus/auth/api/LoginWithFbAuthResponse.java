package com.oculus.auth.api;

import com.google.gson.annotations.SerializedName;
import com.oculus.auth.service.contract.ServiceContract;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class LoginWithFbAuthResponse {
    @SerializedName(ServiceContract.EXTRA_EXISTING_EMAIL_CONFLICT)
    @Nullable
    public final String existingEmailConflict;
    @SerializedName("login_session")
    @Nullable
    public final LoginSession loginSession;
    @SerializedName("prefilled_account_info")
    @Nullable
    public final AccountInfo prefilledAccountInfo;

    public static class AccountInfo {
        @SerializedName(ServiceContract.EXTRA_ALL_EMAILS)
        public final ArrayList<String> allEmails;
        public final String email;
        @SerializedName(ServiceContract.EXTRA_FB_ACCESS_TOKEN)
        public final String fbAccessToken;
        @SerializedName("facebook_id")
        public final String fbUserId;
        public final String name;
        @SerializedName(ServiceContract.EXTRA_PROFILE_PIC_URI)
        public final String profilePicUri;
        @SerializedName(ServiceContract.EXTRA_USERNAME_SUGGESTIONS)
        public final ArrayList<String> usernameSuggestions;
    }

    public static class LoginSession {
        @SerializedName("access_token")
        public final String accessToken;
        @SerializedName("uid")
        public final String userId;
    }
}
