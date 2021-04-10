package com.oculus.horizon.profile.init;

import X.AbstractC06640p5;
import X.AnonymousClass0DC;
import X.AnonymousClass117;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Optional;
import com.oculus.auth.handler.LoginHandler;
import com.oculus.horizon.profile.UserProfileHelper;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_profile_UserProfileHelper_ULSEP_BINDING_ID"})
public class UserProfileHelperLoginHandler implements LoginHandler {
    @Inject
    @Eager
    public final UserProfileHelper mUserProfileHelper;

    @Override // com.oculus.auth.handler.LoginHandler
    public final AnonymousClass0DC<Void> afterLoginAsync() {
        UserProfileHelper userProfileHelper = this.mUserProfileHelper;
        UserProfileHelper.Editor editor = new UserProfileHelper.Editor(userProfileHelper.mPrefs);
        editor.mHasOptedOutHSW = Optional.of(false);
        UserProfileHelper.Editor.A00(editor).apply();
        UserProfileHelper.Editor.A01(editor);
        return AnonymousClass0DC.A04(null);
    }

    @Inject
    public UserProfileHelperLoginHandler(AbstractC06640p5 r2) {
        this.mUserProfileHelper = (UserProfileHelper) AnonymousClass117.A00(68, r2);
    }
}
