package com.oculus.socialplatform.accounts.providerclient;

import android.annotation.TargetApi;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import java.util.Optional;

@TargetApi(25)
@Nullsafe(Nullsafe.Mode.LOCAL)
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

    public final Optional<Boolean> A00() {
        return Optional.ofNullable(this.mHasCheckedHasAcknowledgedMessenger);
    }

    public final Optional<Boolean> A01() {
        return Optional.ofNullable(this.mHasSeenNewUserAuthenticationDialog);
    }

    public final Optional<Boolean> A02() {
        return Optional.ofNullable(this.mMessengerIsAuthenticated);
    }

    public final Optional<String> A03() {
        return Optional.ofNullable(this.mUserId);
    }
}
