package com.oculus.common.fbaccountsmanager;

import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import java.util.Optional;

@TargetApi(25)
public class MessengerVrAccountResult {
    @Nullable
    private Boolean mHasCheckedHasAcknowledgedMessenger;
    @Nullable
    private Boolean mHasSeenNewUserAuthenticationDialog;
    @Nullable
    private Boolean mMessengerIsAuthenticated;
    @Nullable
    private String mUserId;
    @Nullable
    private String mUserName;
    @Nullable
    private String mUserProfilePictureUrl;

    public Optional<String> getUserId() {
        return Optional.ofNullable(this.mUserId);
    }

    /* access modifiers changed from: package-private */
    public void setUserId(@Nullable String str) {
        this.mUserId = str;
    }

    public Optional<String> getUserName() {
        return Optional.ofNullable(this.mUserName);
    }

    /* access modifiers changed from: package-private */
    public void setUserName(@Nullable String str) {
        this.mUserName = str;
    }

    public Optional<String> getUserProfilePictureUrl() {
        return Optional.ofNullable(this.mUserProfilePictureUrl);
    }

    /* access modifiers changed from: package-private */
    public void setUserProfilePictureUrl(@Nullable String str) {
        this.mUserProfilePictureUrl = str;
    }

    public Optional<Boolean> getMessengerIsAuthenticated() {
        return Optional.ofNullable(this.mMessengerIsAuthenticated);
    }

    /* access modifiers changed from: package-private */
    public void setMessengerIsAuthenticated(@Nullable Boolean bool) {
        this.mMessengerIsAuthenticated = bool;
    }

    public Optional<Boolean> getHasSeenNewUserAuthenticationDialog() {
        return Optional.ofNullable(this.mHasSeenNewUserAuthenticationDialog);
    }

    /* access modifiers changed from: package-private */
    public void setHasSeenNewUserAuthenticationDialog(@Nullable Boolean bool) {
        this.mHasSeenNewUserAuthenticationDialog = bool;
    }

    public Optional<Boolean> getHasCheckedHasAcknowledgedMessenger() {
        return Optional.ofNullable(this.mHasCheckedHasAcknowledgedMessenger);
    }

    /* access modifiers changed from: package-private */
    public void setHasCheckedHasAcknowledgedMessenger(@Nullable Boolean bool) {
        this.mHasCheckedHasAcknowledgedMessenger = bool;
    }
}
