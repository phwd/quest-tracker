package com.oculus.common.fbaccountsmanager;

import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import java.util.Optional;

@TargetApi(25)
public class MessengerVrAccountResult {
    @Nullable
    public Boolean mHasCheckedHasAcknowledgedMessenger;
    @Nullable
    public Boolean mHasSeenNewUserAuthenticationDialog;
    @Nullable
    public Boolean mMessengerIsAuthenticated;
    @Nullable
    public String mUserId;
    @Nullable
    public String mUserName;
    @Nullable
    public String mUserProfilePictureUrl;

    public Optional<Boolean> getHasCheckedHasAcknowledgedMessenger() {
        return Optional.ofNullable(this.mHasCheckedHasAcknowledgedMessenger);
    }

    public Optional<Boolean> getHasSeenNewUserAuthenticationDialog() {
        return Optional.ofNullable(this.mHasSeenNewUserAuthenticationDialog);
    }

    public Optional<Boolean> getMessengerIsAuthenticated() {
        return Optional.ofNullable(this.mMessengerIsAuthenticated);
    }

    public Optional<String> getUserId() {
        return Optional.ofNullable(this.mUserId);
    }

    public Optional<String> getUserName() {
        return Optional.ofNullable(this.mUserName);
    }

    public Optional<String> getUserProfilePictureUrl() {
        return Optional.ofNullable(this.mUserProfilePictureUrl);
    }

    public void setHasCheckedHasAcknowledgedMessenger(@Nullable Boolean bool) {
        this.mHasCheckedHasAcknowledgedMessenger = bool;
    }

    public void setHasSeenNewUserAuthenticationDialog(@Nullable Boolean bool) {
        this.mHasSeenNewUserAuthenticationDialog = bool;
    }

    public void setMessengerIsAuthenticated(@Nullable Boolean bool) {
        this.mMessengerIsAuthenticated = bool;
    }

    public void setUserId(@Nullable String str) {
        this.mUserId = str;
    }

    public void setUserName(@Nullable String str) {
        this.mUserName = str;
    }

    public void setUserProfilePictureUrl(@Nullable String str) {
        this.mUserProfilePictureUrl = str;
    }
}
