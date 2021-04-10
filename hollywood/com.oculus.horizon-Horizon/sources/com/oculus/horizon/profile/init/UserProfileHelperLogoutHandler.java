package com.oculus.horizon.profile.init;

import com.facebook.ultralight.Dependencies;
import com.oculus.auth.handler.LogoutHandler;
import com.oculus.horizon.profile.UserProfileHelper;
import javax.inject.Inject;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_profile_UserProfileHelper_ULSEP_BINDING_ID"})
public class UserProfileHelperLogoutHandler implements LogoutHandler {
    public final UserProfileHelper mUserProfileHelper;

    @Override // com.oculus.auth.handler.LogoutHandler
    public final void beforeLogout() {
        this.mUserProfileHelper.mPrefs.edit().clear().apply();
    }

    @Inject
    public UserProfileHelperLogoutHandler(UserProfileHelper userProfileHelper) {
        this.mUserProfileHelper = userProfileHelper;
    }
}
